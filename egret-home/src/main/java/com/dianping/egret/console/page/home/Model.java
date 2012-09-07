package com.dianping.egret.console.page.home;

import java.util.List;

import com.dianping.egret.console.ConsolePage;
import com.site.web.mvc.ViewModel;

public class Model extends ViewModel<ConsolePage, Action, Context> {
	private List<Project> m_projects;

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

	static class Project {
		private String m_name;
		private List<String> m_hosts;
		private String m_owner;
		private List<String> m_dependencyJars;

		public List<String> getDependencyJars() {
			return m_dependencyJars;
		}

		public List<String> getHosts() {
			return m_hosts;
		}

		public String getName() {
			return m_name;
		}

		public String getOwner() {
			return m_owner;
		}

		public void setDependencyJars(List<String> dependencyJars) {
			m_dependencyJars = dependencyJars;
		}

		public void setHosts(List<String> hosts) {
			m_hosts = hosts;
		}

		public void setName(String name) {
			m_name = name;
		}

		public void setOwner(String owner) {
			m_owner = owner;
		}
	}
}
