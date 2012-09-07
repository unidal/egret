package com.dianping.egret.console.page.monitor;

import com.dianping.egret.console.ConsolePage;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<ConsolePage, Action, Context> {
	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.VIEW;
	}
}
