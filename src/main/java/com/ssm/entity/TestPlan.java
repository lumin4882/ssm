package com.ssm.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class TestPlan {
	public String TPName;
	public String TPFileName;
	public String CreaterName;
	public HashMap globalmap;
	public int TestCaseSuiteCount;
	
	//当前执行的的测试用例集合的序号
	public int CurentRunTestCaseSuiteNum;
	//当前执行的的测试用例集合的测试步骤序号
	public int CurentRunTestCaseNum;
	
	//包含测试用例集对象的集合
	public ArrayList<TestCaseSuite> TestCaseSuiteList;
	
	public ArrayList<TestCaseSuite> getTestCaseSuiteList() {
		return TestCaseSuiteList;
	}
	public void setTestCaseSuiteList(ArrayList<TestCaseSuite> testCaseSuiteList) {
		TestCaseSuiteList = testCaseSuiteList;
	}
	public HashMap getGlobalmap() {
		return globalmap;
	}
	public void setGlobalmap(HashMap globalmap) {
		this.globalmap = globalmap;
	}
	public String getTPName() {
		return TPName;
	}
	public void setTPName(String tPName) {
		TPName = tPName;
	}
	public String getTPFileName() {
		return TPFileName;
	}
	public void setTPFileName(String tPFileName) {
		TPFileName = tPFileName;
	}
	public String getCreaterName() {
		return CreaterName;
	}
	public void setCreaterName(String createrName) {
		CreaterName = createrName;
	}
	public int getTestCaseSuiteCount() {
		return TestCaseSuiteList.size();
	}
	public void setTestCaseSuiteCount(int testCaseSuiteCount) {
		TestCaseSuiteCount = testCaseSuiteCount;
	}
	

}
