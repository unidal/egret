package com.dianping.egret.console.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.egret.agent.page.deploy.Action;
import com.site.helper.Threads;
import com.site.helper.Threads.Task;

public class DeployService {
	private Map<String, DeployInfo> m_infos = new HashMap<String, DeployInfo>();

	public synchronized boolean deploy(List<String> hosts, String id) {
		if (!m_infos.containsKey(id)) {
			DeployInfo info = new DeployInfo(id, hosts);
			DeployTask task = new DeployTask(info);

			m_infos.put(id, info);
			Threads.forGroup("Egret").start(task);
			return true;
		}

		return false;
	}

	public HostPlan getCurrentHostPlan(String id) {
		DeployInfo info = m_infos.get(id);

		if (info == null) {
			return null;
		} else {
			return info.getCurrentPlan();
		}
	}

	public List<HostPlan> getHostPlans(String id) {
		DeployInfo info = m_infos.get(id);

		if (info == null) {
			return Collections.emptyList();
		} else {
			return info.getPlans();
		}
	}

	public int getMessages(String id, int offset, StringBuilder sb) {
		DeployInfo info = m_infos.get(id);

		if (info == null) {
			return 0;
		}

		return info.getMessage(offset, sb);
	}

	static class DeployInfo {
		private String m_id;

		private List<HostPlan> m_plans = new ArrayList<HostPlan>();

		private List<String> m_messages = new ArrayList<String>();

		private HostPlan m_currentPlan;

		public DeployInfo(String id, List<String> hosts) {
			m_id = id;

			int index = 0;

			for (String host : hosts) {
				HostPlan plan = new HostPlan(index++, host).setStatus("todo").setStepWeights(10, 70, 20);

				m_plans.add(plan);

				if (m_currentPlan == null) {
					m_currentPlan = plan;
				}
			}
		}

		public void addMessage(String message) {
			m_messages.add(message);
		}

		public HostPlan getCurrentPlan() {
			return m_currentPlan;
		}

		public String getId() {
			return m_id;
		}

		public int getMessage(int offset, StringBuilder sb) {
			int len = m_messages.size();

			for (int i = offset; i < len; i++) {
				sb.append(m_messages.get(i)).append("<br>\\r\\n");
			}

			return Math.max(0, len - offset);
		}

		public List<HostPlan> getPlans() {
			return m_plans;
		}

		public void setCurrentPlan(HostPlan currentPlan) {
			m_currentPlan = currentPlan;
		}
	}

	static class DeployTask implements Task {
		private DeployInfo m_info;

		public DeployTask(DeployInfo info) {
			m_info = info;
		}

		private boolean activate(String id, HostPlan plan) {
			return doAction(Action.ACTIVATE, id, plan);
		}

		private boolean commit(String id, HostPlan plan) {
			return doAction(Action.COMMIT, id, plan);
		}

		private boolean doAction(Action action, String id, HostPlan plan) {
			String host = plan.getHost();
			String url = String.format("http://%s:3473/egret/agent/deploy?op=%s&version=%s", host, action.getName(), id);
			BufferedReader reader = null;

			try {
				InputStream in = new URL(url).openStream();

				plan.setStatus("doing");
				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

				while (true) {
					String line = reader.readLine();

					if (line == null) {
						break;
					}

					m_info.addMessage(line);
				}

				plan.setStatus("success");
				return true;
			} catch (Throwable e) {
				m_info.addMessage(e.toString());
				plan.setStatus("failed");
				return false;
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// ignore it
					}
				}

				plan.nextStep();
			}
		}

		@Override
		public String getName() {
			return getClass().getSimpleName();
		}

		private boolean prepare(String id, HostPlan plan) {
			return doAction(Action.PREPARE, id, plan);
		}

		private boolean rollback(String id, HostPlan plan) {
			return doAction(Action.ROLLBACK, id, plan);
		}

		@Override
		public void run() {
			int hosts = m_info.getPlans().size();

			for (int i = 0; i < hosts; i++) {
				HostPlan plan = m_info.getPlans().get(i);
				String id = m_info.getId();

				m_info.setCurrentPlan(plan);

				if (prepare(id, plan)) {
					if (activate(id, plan)) {
						if (commit(id, plan)) {
							m_info.addMessage("Commit successfully.");
						} else {
							m_info.addMessage("Commit failed.");
						}
					} else {
						if (rollback(id, plan)) {
							m_info.addMessage("Rollback successfully.");
						} else {
							m_info.addMessage("Rollback failed.");
						}
					}
				} else {
					plan.nextStep(); // skip one step

					if (rollback(id, plan)) {
						m_info.addMessage("Rollback successfully.");
					} else {
						m_info.addMessage("Rollback failed.");
					}
				}
			}
		}

		@Override
		public void shutdown() {
		}
	}
}
