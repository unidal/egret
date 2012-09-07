package com.dianping.egret.console;

import com.site.web.mvc.AbstractModule;
import com.site.web.mvc.annotation.ModuleMeta;
import com.site.web.mvc.annotation.ModulePagesMeta;

@ModuleMeta(name = "console", defaultInboundAction = "home", defaultTransition = "default", defaultErrorAction = "default")
@ModulePagesMeta({

com.dianping.egret.console.page.home.Handler.class,

com.dianping.egret.console.page.projects.Handler.class
})
public class ConsoleModule extends AbstractModule {

}
