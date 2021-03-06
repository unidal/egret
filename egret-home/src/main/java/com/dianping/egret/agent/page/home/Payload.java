package com.dianping.egret.agent.page.home;

import com.dianping.egret.agent.AgentPage;
import com.site.web.mvc.ActionContext;
import com.site.web.mvc.ActionPayload;
import com.site.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AgentPage, Action> {
	private AgentPage m_page;

	@FieldMeta("op")
	private Action m_action;

	public void setAction(Action action) {
		m_action = action;
	}

	@Override
	public Action getAction() {
		return m_action;
	}

	@Override
	public AgentPage getPage() {
		return m_page;
	}

	@Override
	public void setPage(String page) {
		m_page = AgentPage.getByName(page, AgentPage.HOME);
	}

	@Override
	public void validate(ActionContext<?> ctx) {
	}
}
