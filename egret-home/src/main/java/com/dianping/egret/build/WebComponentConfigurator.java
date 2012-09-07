package com.dianping.egret.build;

import java.util.ArrayList;
import java.util.List;

import com.dianping.egret.agent.AgentModule;
import com.dianping.egret.console.ConsoleModule;

import com.site.lookup.configuration.Component;
import com.site.web.configuration.AbstractWebComponentsConfigurator;

class WebComponentConfigurator extends AbstractWebComponentsConfigurator {
	@SuppressWarnings("unchecked")
	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();

		defineModuleRegistry(all, ConsoleModule.class, AgentModule.class, ConsoleModule.class);

		return all;
	}
}
