package com.dianping.egret.agent.page.deploy;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;

import com.dianping.egret.agent.page.deploy.shell.DefaultShell;
import com.site.web.mvc.PageHandler;
import com.site.web.mvc.annotation.InboundActionMeta;
import com.site.web.mvc.annotation.OutboundActionMeta;
import com.site.web.mvc.annotation.PayloadMeta;

public class Handler implements PageHandler<Context> {
	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "deploy")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "deploy")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Payload payload = ctx.getPayload();

		OutputStream resOut = ctx.getHttpServletResponse().getOutputStream();
		switch (payload.getAction()) {
		case PREPARE:
			DefaultShell.getInstance().prepare(payload.getVersion(), resOut);
			break;
		case ACTIVATE:
			DefaultShell.getInstance().activate(resOut);
			break;
		case COMMIT:
			DefaultShell.getInstance().commit(resOut);
			break;
		case ROLLBACK:
			DefaultShell.getInstance().rollback(payload.getVersion(), resOut);
			break;
		}
	}
}
