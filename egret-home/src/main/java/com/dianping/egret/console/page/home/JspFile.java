package com.dianping.egret.console.page.home;

public enum JspFile {
	HOME("/jsp/console/home.jsp"),

	PROJECT("/jsp/console/project.jsp"),

	ABOUT("/jsp/console/about.jsp"),

	DEPLOY("/jsp/console/deploy.jsp");

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
