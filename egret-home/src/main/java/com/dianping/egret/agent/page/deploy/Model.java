package com.dianping.egret.agent.page.deploy;

import com.dianping.egret.agent.AgentPage;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<AgentPage, Action, Context> {
	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.VIEW;
	}
}
