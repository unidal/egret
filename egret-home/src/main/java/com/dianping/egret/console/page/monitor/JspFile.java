package com.dianping.egret.console.page.monitor;

public enum JspFile {
	VIEW("/jsp/console/monitor.jsp"),

	;

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
