package com.ssm.entity;

import java.util.HashMap;

public class TestCase {
	//测试步骤序号 只是当前测试用例集和序号
	public int TestCaseID;
	
	//测试名称
	public String TestCaseName;
	
	//测试步骤类型  0=报文头设置  1=http报文发送 
	public int TestType;
	//当测试步骤为设置报文头的时候 该值为报文头的键值对集合
	public HashMap<String, String> HeadValueMap;
	
	public String getTestCaseName() {
		return TestCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		TestCaseName = testCaseName;
	}
	//请求地址
	public String HttpRequestUrlPath;
	//请求方法 目前只支持 GET OR POST 
	public String HttpRequestMethod;
	//请求消息体
	public String HttpRequestBody;
	
	//校验的响应头的状态码
	public String ResponseAssertionStatusCode;
	
	//校验的响应头的状态消息内容
	public String ResponseAssertionMsg;
	
	public String getResponseAssertionStatusCode() {
		return ResponseAssertionStatusCode;
	}
	public void setResponseAssertionStatusCode(String responseAssertionStatusCode) {
		ResponseAssertionStatusCode = responseAssertionStatusCode;
	}
	public String getResponseAssertionMsg() {
		return ResponseAssertionMsg;
	}
	public void setResponseAssertionMsg(String responseAssertionMsg) {
		ResponseAssertionMsg = responseAssertionMsg;
	}
	public int getTestCaseID() {
		return TestCaseID;
	}
	public void setTestCaseID(int testCaseID) {
		TestCaseID = testCaseID;
	}
	public int getTestType() {
		return TestType;
	}
	public void setTestType(int testType) {
		TestType = testType;
	}
	public HashMap getHeadValueMap() {
		return HeadValueMap;
	}
	public void setHeadValueMap(HashMap headValueMap) {
		HeadValueMap = headValueMap;
	}
	public String getHttpRequestUrlPath() {
		return HttpRequestUrlPath;
	}
	public void setHttpRequestUrlPath(String httpRequestUrlPath) {
		HttpRequestUrlPath = httpRequestUrlPath;
	}
	public String getHttpRequestMethod() {
		return HttpRequestMethod;
	}
	public void setHttpRequestMethod(String httpRequestMethod) {
		HttpRequestMethod = httpRequestMethod;
	}
	public String getHttpRequestBody() {
		return HttpRequestBody;
	}
	public void setHttpRequestBody(String httpRequestBody) {
		HttpRequestBody = httpRequestBody;
	}
	
	
	

}
