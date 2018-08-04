package cn.selenium.bean;

import java.util.Date;

public class SeleniumRunResultBean {
	
	private int id;
	private String xmltest_name;
	private String test_class;
	private String testcase_name;
	private String testcase_param;
	private int testcase_status;
	private String testcase_result;
	private long testcase_time;
	private Date create_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getXmltest_name() {
		return xmltest_name;
	}
	public void setXmltest_name(String xmltest_name) {
		this.xmltest_name = xmltest_name;
	}
	public String getTest_class() {
		return test_class;
	}
	public void setTest_class(String test_class) {
		this.test_class = test_class;
	}
	public String getTestcase_name() {
		return testcase_name;
	}
	public void setTestcase_name(String testcase_name) {
		this.testcase_name = testcase_name;
	}
	public String getTestcase_param() {
		return testcase_param;
	}
	public void setTestcase_param(String testcase_param) {
		this.testcase_param = testcase_param;
	}
	public int getTestcase_status() {
		return testcase_status;
	}
	public void setTestcase_status(int testcase_status) {
		this.testcase_status = testcase_status;
	}
	public String getTestcase_result() {
		return testcase_result;
	}
	public void setTestcase_result(String testcase_result) {
		this.testcase_result = testcase_result;
	}
	public long getTestcase_time() {
		return testcase_time;
	}
	public void setTestcase_time(long testcase_time) {
		this.testcase_time = testcase_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
