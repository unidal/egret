package com.dianping.egret.agent.page.deploy.shell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class DefaultShell implements Shell {

	private final static String SCRIPT_PATH = "/Users/marsqing/bin/";

	private static Shell ins = new DefaultShell();

	public static Shell getInstance() {
		return ins;
	}

	@Override
	public void prepare(String libVersion, final OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(SCRIPT_PATH + "egret.sh prepare " + libVersion);
		getExecutor(outputCollector).execute(cmd);
	}

	@Override
	public void activate(OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(SCRIPT_PATH + "egret.sh activate");
		getExecutor(outputCollector).execute(cmd);
	}

	@Override
	public void commit(OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(SCRIPT_PATH + "egret.sh commit");
		getExecutor(outputCollector).execute(cmd);
	}

	@Override
	public void rollback(String appVersion, OutputStream outputCollector) throws IOException {
		CommandLine cmd = CommandLine.parse(SCRIPT_PATH + "egret.sh rollback " + appVersion);
		getExecutor(outputCollector).execute(cmd);
	}

	private DefaultExecutor getExecutor(OutputStream outputCollector) {
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValues(null);
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputCollector);
		executor.setStreamHandler(streamHandler);
		return executor;
	}

	public static void main(String[] args) throws IOException {
		DefaultShell.getInstance().prepare("1.2", System.out);
	}

}
