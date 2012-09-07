package com.dianping.egret.console.page.home;

import java.util.List;

import com.dianping.egret.console.ConsolePage;
import com.dianping.egret.home.model.entity.Project;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<ConsolePage, Action, Context> {
	private List<Project> m_projects;

	private Project m_project;

	private List<String> m_deployPlans;

	private List<String> m_hosts;

	private String m_deployPlan;

	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.HOME;
	}

	public String getDeployPlan() {
		return this.m_deployPlan;
	}

	public List<String> getDeployPlans() {
		return m_deployPlans;
	}

	public List<String> getHosts() {
		return m_hosts;
	}

	public Project getProject() {
		return m_project;
	}

	public List<Project> getProjects() {
		return m_projects;
	}

	public void setDeployPlan(String plan) {
		this.m_deployPlan = plan;
	}

	public void setDeployPlans(List<String> deployPlans) {
		this.m_deployPlans = deployPlans;
	}

	public void setHosts(List<String> hosts) {
		this.m_hosts = hosts;
	}

	public void setProject(Project project) {
		m_project = project;
	}

	public void setProjects(List<Project> projects) {
		m_projects = projects;
	}
}
