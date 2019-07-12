package com.ssm.entity;

import java.util.ArrayList;

public class TestCaseSuite {
	//测试用例索引
	public int TestCaseSuiteID;
	
	public int TestCaseCount;
	//包含测试步骤对象的集合
	public ArrayList<TestCase> TestCaseList;

	public int getTestCaseSuiteID() {
		return TestCaseSuiteID;
	}
	public void setTestCaseSuiteID(int testCaseSuiteID) {
		TestCaseSuiteID = testCaseSuiteID;
	}
	public int getTestCaseCount() {
		return TestCaseList.size();
	}
	public void setTestCaseCount(int testCaseCount) {
		TestCaseCount = testCaseCount;
	}
	public ArrayList<TestCase> getTestCaseList() {
		return TestCaseList;
	}
	public void setTestCaseList(ArrayList<TestCase> testCaseList) {
		TestCaseList = testCaseList;
	}

}
