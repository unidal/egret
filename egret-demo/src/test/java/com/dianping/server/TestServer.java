package com.dianping.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class TestServer {

	private Server server;

	@After
	public void after() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void before() {
		try {
			server = new Server(8888);
			WebAppContext context = new WebAppContext();

			System.setProperty("devMode", "true");
			context.setContextPath("/demo");
			context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
			context.setResourceBase("src/main/webapp");
			server.setHandler(context);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws Exception {
		String timestamp = new SimpleDateFormat("MM-dd HH:mm:ss.SSS").format(new Date());

		System.out.println(String.format("[%s] [INFO] Press any key to stop server ... ", timestamp));
		System.in.read();
	}
}
