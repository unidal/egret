package com.dianping.egret.agent.page.deploy;

public enum Action implements com.site.web.mvc.Action {
	PREPARE("prepare"),
	ACTIVATE("activate"),
	COMMIT("commit"),
	ROLLBACK("rollback"),
	VIEW("view");
	
	

	private String m_name;

	private Action(String name) {
		m_name = name;
	}

	public static Action getByName(String name, Action defaultAction) {
		for (Action action : Action.values()) {
			if (action.getName().equals(name)) {
				return action;
			}
		}

		return defaultAction;
	}

	@Override
	public String getName() {
		return m_name;
	}
}
