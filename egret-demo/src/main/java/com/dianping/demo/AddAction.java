package com.dianping.demo;

import com.opensymphony.xwork2.ActionSupport;

public class AddAction extends ActionSupport {

	private static final long serialVersionUID = -4294243456159777514L;

	private int m_a;

	private int m_b;

	private int m_result;

	@Override
	public String execute() throws Exception {
		m_result = m_a + m_b;
		return SUCCESS;
	}

	public int getA() {
		return m_a;
	}

	public int getB() {
		return m_b;
	}

	public int getResult() {
		return m_result;
	}

	public void setA(int a) {
		m_a = a;
	}

	public void setB(int b) {
		m_b = b;
	}

}
