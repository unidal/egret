package com.dianping.egret.console.page.deploy;

import java.util.List;

import com.dianping.egret.console.ConsolePage;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<ConsolePage, Action, Context> {
	private List<String> m_hosts;

	private String m_plan;

	private String m_log;

	private int m_progress;

	private int m_offset;

	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.VIEW;
	}

	public List<String> getHosts() {
		return m_hosts;
	}

	public String getLog() {
		return m_log;
	}

	public int getOffset() {
		return m_offset;
	}

	public String getPlan() {
		return m_plan;
	}

	public int getProgress() {
		return m_progress;
	}

	public void setHosts(List<String> hosts) {
		m_hosts = hosts;
	}

	public void setLog(String log) {
		m_log = log;
	}

	public void setOffset(int offset) {
		this.m_offset = offset;
	}

	public void setPlan(String plan) {
		m_plan = plan;
	}

	public void setProgress(int progress) {
		m_progress = progress;
	}
}
