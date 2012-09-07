package com.dianping.egret.agent.page.deploy.shell;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class DefaultShell implements Shell {
	@Override
	public void activate(OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(getScriptPath() + "egret.sh activate");
		getExecutor(outputCollector).execute(cmd);
	}

	@Override
	public void commit(OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(getScriptPath() + "egret.sh commit");
		getExecutor(outputCollector).execute(cmd);
	}

	private DefaultExecutor getExecutor(OutputStream outputCollector) {
		DefaultExecutor executor = new DefaultExecutor();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputCollector);

		executor.setExitValues(null);
		executor.setStreamHandler(streamHandler);
		return executor;
	}

	private String getScriptPath() {
		String scriptPath = System.getProperty("scriptPath", "/Users/marsqing/bin");

		return scriptPath;
	}

	@Override
	public void prepare(String libVersion, final OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(getScriptPath() + "egret.sh prepare " + libVersion);
		getExecutor(outputCollector).execute(cmd);
	}

	@Override
	public void rollback(String appVersion, OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(getScriptPath() + "egret.sh rollback " + appVersion);
		getExecutor(outputCollector).execute(cmd);
	}
}
