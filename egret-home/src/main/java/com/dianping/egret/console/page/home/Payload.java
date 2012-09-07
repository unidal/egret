package com.dianping.egret.console.page.home;

import com.dianping.egret.console.ConsolePage;
import com.site.web.mvc.ActionContext;
import com.site.web.mvc.ActionPayload;
import com.site.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<ConsolePage, Action> {
	private ConsolePage m_page;

	@FieldMeta("op")
	private Action m_action;
	
	@FieldMeta("name")
	private String m_projectName;

	@Override
	public Action getAction() {
		return m_action;
	}
	
	@Override
	public ConsolePage getPage() {
		return m_page;
	}
	
	public String getProjectName(){
		return m_projectName;
	}

	public void setAction(String action) {
		m_action = Action.getByName(action, Action.HOME);
	}

	@Override
	public void setPage(String page) {
		m_page = ConsolePage.getByName(page, ConsolePage.HOME);
	}

	public void setProjectName(String projectName){
		this.m_projectName = projectName;
	}

	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.HOME;
		}
	}
}
