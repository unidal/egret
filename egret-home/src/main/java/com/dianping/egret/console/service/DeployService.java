package com.dianping.egret.console.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.egret.agent.page.deploy.Action;
import com.site.helper.Threads;
import com.site.helper.Threads.Task;

public class DeployService {
	private Map<String, DeployInfo> m_infos = new HashMap<String, DeployInfo>();

	public synchronized boolean deploy(List<String> hosts, String plan) {
		if (!m_infos.containsKey(plan)) {
			DeployInfo info = new DeployInfo(plan, hosts);
			DeployTask task = new DeployTask(info);

			m_infos.put(plan, info);
			Threads.forGroup("Egret").start(task);
			return true;
		}

		return false;
	}

	public List<String> getHostsByPlan(String plan) {
		DeployInfo info = m_infos.get(plan);

		if (info == null) {
			return new ArrayList<String>();
		}
		return info.getHosts();
	}

	public int getLog(String plan, int offset, StringBuilder sb) {
		DeployInfo info = m_infos.get(plan);

		if (info == null) {
			return 0;
		}

		try {
			return info.getLog(offset, sb);
		} finally {
			if (info.isComplete()) {
				//m_infos.remove(plan); //TODO
			}
		}
	}

	public int getProgress(String plan) {
		DeployInfo info = m_infos.get(plan);

		if (info == null) {
			return 0;
		}

		return info.getProgress();
	}

	static class DeployInfo {
		private String m_plan;

		private List<String> m_hosts;

		private int m_progress;

		private List<String> m_logs = new ArrayList<String>();

		public DeployInfo(String plan, List<String> hosts) {
			m_plan = plan;
			m_hosts = hosts;
		}

		public void addLog(String log) {
			m_logs.add(log);
		}

		public List<String> getHosts() {
			return m_hosts;
		}

		public int getLog(int offset, StringBuilder sb) {
			int len = m_logs.size();

			for (int i = offset; i < len; i++) {
				sb.append(m_logs.get(i)).append("<br>\\r\\n");
			}

			return Math.max(0, len - offset);
		}

		public String getPlan() {
			return m_plan;
		}

		public int getProgress() {
			return m_progress;
		}

		public boolean isComplete() {
			return m_progress == 100;
		}

		public void setProgress(int progress) {
			m_progress = progress;
		}
	}

	static class DeployTask implements Task {
		private DeployInfo m_info;

		public DeployTask(DeployInfo info) {
			m_info = info;
		}

		private boolean activate(String plan, String host) {
			return doAction(Action.ACTIVATE, plan, host);
		}

		private boolean commit(String plan, String host) {
			return doAction(Action.COMMIT, plan, host);
		}

		private boolean doAction(Action action, String plan, String host) {
			String url = String.format("http://%s:3473/egret/agent/deploy?op=%s&version=%s", host, action.getName(), plan);
			BufferedReader reader = null;

			try {
				InputStream in = new URL(url).openStream();

				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

				while (true) {
					String line = reader.readLine();

					if (line == null) {
						break;
					}

					m_info.addLog(line);
				}

				return true;
			} catch (Exception e) {
				m_info.addLog(e.toString());
				return false;
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// ignore it
					}
				}
			}
		}

		@Override
		public String getName() {
			return getClass().getSimpleName();
		}

		private boolean prepare(String plan, String host) {
			return doAction(Action.PREPARE, plan, host);
		}

		private boolean rollback(String plan, String host) {
			return doAction(Action.ROLLBACK, plan, host);
		}

		@Override
		public void run() {
			int hosts = m_info.getHosts().size();

			for (int i = 0; i < hosts; i++) {
				String host = m_info.getHosts().get(i);
				String plan = m_info.getPlan();

				updateProgress(hosts, i, 0);

				if (prepare(plan, host)) {
					updateProgress(hosts, i, 0.1);

					if (activate(plan, host)) {
						updateProgress(hosts, i, 0.8);

						if (commit(plan, host)) {
							m_info.addLog("Commit successfully.");
						} else {
							m_info.addLog("Commit failed.");
						}
					} else {
						if (rollback(plan, host)) {
							m_info.addLog("Rollback successfully.");
						} else {
							m_info.addLog("Rollback failed.");
						}
					}
				} else {
					if (rollback(plan, host)) {
						m_info.addLog("Rollback successfully.");
					} else {
						m_info.addLog("Rollback failed.");
					}
				}

				updateProgress(hosts, i, 1);
			}
		}

		@Override
		public void shutdown() {
		}

		private void updateProgress(int hosts, int index, double value) {
			double progress = (index + value) / hosts;

			m_info.setProgress((int) (progress * 100));
		}
	}
}
