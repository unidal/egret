package com.dianping.egret.console.page.monitor;

import com.dianping.egret.console.ConsolePage;
import com.site.web.mvc.view.BaseJspViewer;

public class JspViewer extends BaseJspViewer<ConsolePage, Action, Context, Model> {
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
