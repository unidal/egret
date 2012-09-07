package com.dianping.egret.console.page.home;

import java.util.List;

import com.dianping.egret.console.ConsolePage;
import com.dianping.egret.home.model.entity.Project;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<ConsolePage, Action, Context> {
	private List<Project> m_projects;
	
	private Project m_project;
	
	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.HOME;
	}

	public List<Project> getProjects() {
		return m_projects;
	}

	public void setProjects(List<Project> projects) {
		m_projects = projects;
	}

   public void setProject(Project project) {
   	m_project = project;
   }
   
   public Project getProject(){
   	return m_project;
   }
}
