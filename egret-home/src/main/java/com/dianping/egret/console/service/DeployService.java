package com.dianping.egret.console.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.site.helper.Threads;
import com.site.helper.Threads.Task;

public class DeployService {
	private Map<String, DeployInfo> m_infos = new HashMap<String, DeployInfo>();

	public synchronized boolean deploy(List<String> hosts, String plan) {
		if (m_infos.containsKey(plan)) {
			DeployInfo info = new DeployInfo(plan, hosts);
			DeployTask task = new DeployTask(info);

			m_infos.put(plan, info);
			Threads.forGroup("Egret").start(task);
			return true;
		}

		return false;
	}

	public String getLog(String plan, int offset) {
		DeployInfo info = m_infos.get(plan);

		if (info == null) {
			return null;
		}

		return info.getLog(offset);
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

		public String getLog(int offset) {
			return null;
		}

		public List<String> getLogs() {
			return m_logs;
		}

		public String getPlan() {
			return m_plan;
		}

		public int getProgress() {
			return m_progress;
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

		@Override
		public String getName() {
			return getClass().getSimpleName();
		}

		@Override
		public void run() {
			while (true) {
				// TODO
			}
		}

		@Override
		public void shutdown() {
		}
	}
}
