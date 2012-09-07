package com.dianping.egret.console.page.deploy;

import java.io.IOException;

import javax.servlet.ServletException;

import com.dianping.egret.console.ConsolePage;
import com.dianping.egret.console.service.DeployService;
import com.site.lookup.annotation.Inject;
import com.site.web.mvc.PageHandler;
import com.site.web.mvc.annotation.InboundActionMeta;
import com.site.web.mvc.annotation.OutboundActionMeta;
import com.site.web.mvc.annotation.PayloadMeta;

public class Handler implements PageHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;

	@Inject
	private DeployService m_service;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "deploy")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "deploy")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);
		Payload payload = ctx.getPayload();

		model.setAction(payload.getAction());
		model.setPage(ConsolePage.DEPLOY);

		switch (payload.getAction()) {
		case LOG:
			String plan = payload.getPlan();
			String log = m_service.getLog(plan, payload.getOffset());
			int progress = m_service.getProgress(plan);
			
			model.setLog(log);
			model.setProgress(progress);
		}

		m_jspViewer.view(ctx, model);
	}
}
