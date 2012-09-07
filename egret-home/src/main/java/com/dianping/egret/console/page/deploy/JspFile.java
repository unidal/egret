package com.dianping.egret.console.page.deploy;

public enum JspFile {
	VIEW("/jsp/console/deploy.jsp"),

	;

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
