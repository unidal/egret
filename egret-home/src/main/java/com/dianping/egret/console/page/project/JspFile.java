package com.dianping.egret.console.page.project;

public enum JspFile {
	VIEW("/jsp/console/project.jsp"),

	;

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
