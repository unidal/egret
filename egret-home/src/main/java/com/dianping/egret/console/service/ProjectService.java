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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author <a href="mailto:yiming.liu@dianping.com">Yiming Liu</a>
 */
public class ProjectService {

	private static List<Project> m_projects = null;

	public Project findByName(String projectName) {
		for (Project project : m_projects) {
			if (project.getName().equalsIgnoreCase(projectName)) {
				return project;
			}
		}
		return null;
	}

	public List<Project> search(String keyword) {
		if (m_projects == null) {
			int length = 10;
			List<Project> sampleProjects = new ArrayList<Project>(length);
			for (int i = 0; i < length; i++) {
				Project project = new Project();
				project.setName("Project" + i);
				project.setOwner("Owner" + i);
				List<String> hosts = new ArrayList<String>();
				for (int j = 0; j < length / 2; j++) {
					hosts.add("127.0.0." + (j + 1));
				}
				project.setHosts(hosts);
				List<String> jars = new ArrayList<String>();
				Random random = new Random();
				for (int j = 0; j < length * 2; j++) {
					jars.add("samplejar-" + random.nextInt(10) + "." + random.nextInt(10) + "." + random.nextInt(10));
				}
				project.setDependencyJars(jars);
				sampleProjects.add(project);
			}

			m_projects = sampleProjects;
		}

		if (keyword == null || keyword.trim().length() == 0) {
			return m_projects;
		}

		List<Project> result = new ArrayList<Project>();
		for (Project project : m_projects) {
			for (String jar : project.getDependencyJars()) {
				if (jar.indexOf(keyword) != -1) {
					result.add(project);
					break;
				}
			}
		}
		return result;
	}
}
