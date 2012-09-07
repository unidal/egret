/**
 * Project: egret-home
 * 
 * File Created at 2012-9-7
 * 
 * Copyright 2012 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.egret.console.service;

import java.util.List;

public class Project {
	private String m_name;
	private List<String> m_hosts;
	private String m_owner;
	private List<String> m_dependencyJars;

	public List<String> getDependencyJars() {
		return m_dependencyJars;
	}

	public List<String> getHosts() {
		return m_hosts;
	}

	public String getName() {
		return m_name;
	}

	public String getOwner() {
		return m_owner;
	}

	public void setDependencyJars(List<String> dependencyJars) {
		m_dependencyJars = dependencyJars;
	}

	public void setHosts(List<String> hosts) {
		m_hosts = hosts;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setOwner(String owner) {
		m_owner = owner;
	}
}