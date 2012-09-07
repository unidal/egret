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
	}

	@Override
	@OutboundActionMeta(name = "deploy")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);
		Payload payload = ctx.getPayload();

		model.setAction(payload.getAction());
		model.setPage(ConsolePage.DEPLOY);

		String planName = payload.getPlan();
		model.setHosts(m_service.getHostsByPlan(planName));
		switch (payload.getAction()) {
		case LOG:
			StringBuilder sb = new StringBuilder(1024);
			String plan = payload.getPlan();
			int offset = payload.getOffset();
			int logs = m_service.getLog(plan, offset, sb);
			int progress = m_service.getProgress(plan);
			model.setLog("\""+sb.toString()+"\"");
			model.setProgress(progress);
			model.setOffset(offset + logs);
		default:
			break;
		}

		m_jspViewer.view(ctx, model);
	}
}
