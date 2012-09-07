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

/**
 * @author <a href="mailto:yiming.liu@dianping.com">Yiming Liu</a>
 */
public class DeployService {

	public List<String> getDeployPlans() {
		List<String> deployPlans = new ArrayList<String>();
		deployPlans.add("ABC");
		deployPlans.add("BCD");
		return deployPlans;
	}
}
