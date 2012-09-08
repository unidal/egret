package com.dianping.egret.console.service;

public class HostPlan {
	private int m_index;

	private String m_host;

	private int[] m_stepWeights;

	private int m_step;

	private String m_status;

	public HostPlan(int index, String host) {
		m_index = index;
		m_host = host;
	}

	public String getHost() {
		return m_host;
	}

	public int getIndex() {
		return m_index;
	}

	public String getStatus() {
		return m_status;
	}

	public int getStep() {
		return m_step;
	}

	public int[] getStepWeights() {
		return m_stepWeights;
	}

	public HostPlan setStatus(String status) {
		m_status = status;
		return this;
	}

	public void setStep(int step) {
		m_step = step;
	}

	public HostPlan setStepWeights(int... stepWeights) {
		m_stepWeights = stepWeights;
		return this;
	}

	public void nextStep() {
		m_step++;
	}
}