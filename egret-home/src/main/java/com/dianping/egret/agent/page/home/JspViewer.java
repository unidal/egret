package com.dianping.egret.agent.page.home;

import com.dianping.egret.agent.AgentPage;
import com.site.web.mvc.view.BaseJspViewer;

public class JspViewer extends BaseJspViewer<AgentPage, Action, Context, Model> {
	@Override
	protected String getJspFilePath(Context ctx, Model model) {
		Action action = model.getAction();

		switch (action) {
		case VIEW:
			return JspFile.VIEW.getPath();
		}

		throw new RuntimeException("Unknown action: " + action);
	}
}
