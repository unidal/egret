package com.dianping.egret.console.page.home;

import java.util.List;

import com.dianping.egret.console.ConsolePage;
import com.dianping.egret.home.model.entity.Project;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<ConsolePage, Action, Context> {
	private List<Project> m_projects;

	private Project m_project;

	private List<String> m_deployPlans;

	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.HOME;
	}

	public List<String> getDeployPlans() {
		return m_deployPlans;
	}

	public Project getProject() {
		return m_project;
	}

	public List<Project> getProjects() {
		return m_projects;
	}

	public void setDeployPlans(List<String> deployPlans) {
		m_deployPlans = deployPlans;
	}

	public void setProject(Project project) {
		m_project = project;
	}

	public void setProjects(List<Project> projects) {
		m_projects = projects;
	}
}
