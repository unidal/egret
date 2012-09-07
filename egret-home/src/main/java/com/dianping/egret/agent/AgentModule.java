package com.dianping.egret.agent;

import com.site.web.mvc.AbstractModule;
import com.site.web.mvc.annotation.ModuleMeta;
import com.site.web.mvc.annotation.ModulePagesMeta;

@ModuleMeta(name = "agent", defaultInboundAction = "home", defaultTransition = "default", defaultErrorAction = "default")
@ModulePagesMeta({

com.dianping.egret.agent.page.home.Handler.class,

com.dianping.egret.agent.page.deploy.Handler.class
})
public class AgentModule extends AbstractModule {

}
