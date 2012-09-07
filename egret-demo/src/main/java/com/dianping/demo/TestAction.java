package com.dianping.demo;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.spi.MessageTree;
import com.dianping.hawk.common.alarm.service.CommonAlarmService;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	private static final long serialVersionUID = -4294243456159777514L;

	private String m_version;

	private CommonAlarmService m_commonAlarmSerivce;

	@Override
	public String execute() throws Exception {

		MessageTree messagetree = Cat.getManager().getThreadLocalMessageTree();
		System.out.println(messagetree);
		Transaction t = Cat.getProducer().newTransaction("Demo", "Test");

		try {
			List<String> emails = new ArrayList<String>();
			emails.add("yong.you@dianping.com");

			boolean result = m_commonAlarmSerivce.sendEmail("Test", "Test", emails);
			m_version = "0.1.0  " + result;
			t.setStatus(Message.SUCCESS);
		} catch (Exception e) {
			t.setStatus(e);
			Cat.logError(e);
			throw e;
		} finally {
			t.complete();
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getVersion() {
		return m_version;
	}

	public void setCommonAlarmSerivce(CommonAlarmService commonAlarmSerivce) {
		m_commonAlarmSerivce = commonAlarmSerivce;
	}

}
