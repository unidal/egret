package com.dianping.egret.console;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.unidal.webres.resource.runtime.ResourceConfigurator;
import org.unidal.webres.resource.runtime.ResourceInitializer;
import org.unidal.webres.resource.runtime.ResourceRuntime;
import org.unidal.webres.resource.runtime.ResourceRuntimeContext;
import org.unidal.webres.resource.spi.IResourceRegistry;
import org.unidal.webres.tag.resource.ResourceTagConfigurator;
import org.unidal.webres.taglib.basic.ResourceTagLibConfigurator;

import com.site.web.mvc.Action;
import com.site.web.mvc.ActionContext;
import com.site.web.mvc.ActionPayload;
import com.site.web.mvc.Page;

public class ConsoleContext<T extends ActionPayload<? extends Page, ? extends Action>> extends ActionContext<T> {

   @Override
   public void initialize(HttpServletRequest request, HttpServletResponse response) {
      super.initialize(request, response);

      String contextPath = request.getContextPath();

      synchronized (ResourceRuntime.INSTANCE) {
         if (!ResourceRuntime.INSTANCE.hasConfig(contextPath)) {
            ServletContext servletContext = request.getSession().getServletContext();
            File warRoot = new File(servletContext.getRealPath("/"));
   
            System.out.println("[INFO] Working directory is "+ System.getProperty("user.dir"));
            System.out.println("[INFO] War root is " + warRoot);
   
            ResourceRuntime.INSTANCE.removeConfig(contextPath);
            ResourceInitializer.initialize(contextPath, warRoot);
   
            IResourceRegistry registry = ResourceRuntime.INSTANCE.getConfig(contextPath).getRegistry();
   
            new ResourceConfigurator().configure(registry);
            new ResourceTagConfigurator().configure(registry);
            new ResourceTagLibConfigurator().configure(registry);
   
            registry.lock();
         }
   
         ResourceRuntimeContext.setup(contextPath);
      }
   }

}
