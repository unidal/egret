package com.dianping.egret.console.page.home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.dianping.egret.console.ConsolePage;
import com.dianping.egret.console.service.DeployService;
import com.dianping.egret.console.service.ProjectService;
import com.dianping.egret.home.model.entity.Project;
import com.site.lookup.annotation.Inject;
import com.site.web.mvc.PageHandler;
import com.site.web.mvc.annotation.InboundActionMeta;
import com.site.web.mvc.annotation.OutboundActionMeta;
import com.site.web.mvc.annotation.PayloadMeta;

public class Handler implements PageHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;

	@Inject
	private ProjectService m_projectService;

	@Inject
	private DeployService m_deployService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "home")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		Action action = ctx.getPayload().getAction();

		switch (action) {
		case DEPLOY:
			List<String> hosts = ctx.getPayload().getHosts();
			String deployPlan = ctx.getPayload().getDeployPlan();
			// TODO process deploy
			break;
		default:
			break;
		}
	}

	@Override
	@OutboundActionMeta(name = "home")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);
		Payload payload = ctx.getPayload();
		Action action = payload.getAction();

		switch (action) {
		case HOME:
			List<Project> projects = m_projectService.search(payload.getKeyword());
			model.setProjects(projects);
			break;
		case PROJECT:
			Project project = m_projectService.findByName(payload.getProjectName());
			model.setProject(project);
			List<String> deployPlans = m_deployService.getDeployPlans();
			model.setDeployPlans(deployPlans);
			break;
		case DEPLOY:
			model.setHosts(payload.getHosts());
			model.setDeployPlan(payload.getDeployPlan());
			break;
		case ABOUT:
			break;
		}

		model.setAction(payload.getAction());
		model.setPage(ConsolePage.HOME);

		m_jspViewer.view(ctx, model);
	}
}
