package com.dianping.egret.console.page.home;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.dianping.egret.console.ConsolePage;
import com.dianping.egret.console.service.Project;
import com.dianping.egret.console.service.ProjectService;
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
	
	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "home")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		Action action = ctx.getPayload().getAction();
		switch (action) {
		case HOME:
			break;
		case PROJECT:
			String projectName = ctx.getPayload().getProjectName();
			break;
		case DEPLOY:
			break;
		case ABOUT:
			break;
		}
	}

	@Override
	@OutboundActionMeta(name = "home")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);
		Action action = ctx.getPayload().getAction();
		switch (action) {
		case HOME:
			if(model.getProjects()==null){
				List<Project> projects =m_projectService.search("");
				model.setProjects(projects);
			}
			break;
		case PROJECT:
			String projectName = ctx.getPayload().getProjectName();
			Project project = m_projectService.findByName(projectName);
			model.setProject(project);
			break;
		case DEPLOY:
			break;
		case ABOUT:
			break;
		}
		Payload payload = ctx.getPayload();

		model.setAction(payload.getAction());
		model.setPage(ConsolePage.HOME);

		m_jspViewer.view(ctx, model);
	}
}
