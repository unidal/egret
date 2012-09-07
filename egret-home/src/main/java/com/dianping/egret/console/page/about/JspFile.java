package com.dianping.egret.console.page.about;

public enum JspFile {
	VIEW("/jsp/console/about.jsp"),

	;

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
