package com.ssm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.dao.HttpTestResultMapper;
import com.ssm.dao.UserMapper;
import com.ssm.enmu.ResponseEnmu;
import com.ssm.entity.HttpTestResult;
import com.ssm.entity.TestCase;
import com.ssm.entity.TestCaseSuite;
import com.ssm.entity.TestPlan;
import com.ssm.utils.HttpUtils;
import com.ssm.utils.XmlUtils;


@Controller
public class RunHttpTestPlan extends Thread{
	
	public TestPlan  testplan;


	@Test
	public void run() {
		testplan=new TestPlan();
		testplan.setTPFileName("testplan1.xml");
		// 开始根据XML文件组织报文
		
		XmlUtils  xmlutils = new XmlUtils();
		try {
			//加载测试计划文件
			xmlutils.load(testplan.getTPFileName());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
		//获取测试计划名称
		testplan.setTPName(xmlutils.readTPname());
		System.out.println(testplan.getTPName());
		//获取测试计划作者
		testplan.setCreaterName(xmlutils.readCreater());
		System.out.println(testplan.getCreaterName());
		//获取全局参数

		testplan.setGlobalmap(xmlutils.readGlobalPrameter());
		System.out.println(testplan.getGlobalmap().get("global1"));
		System.out.println(testplan.getGlobalmap().get("city"));
		System.out.println(testplan.getGlobalmap().get("city11"));


		//HTTP报文有2中方式实现 一种JAVA自带的HttpUrlConnection，另一种是 apach 开发的 HttpClient
		//开始发送报文
		//Xml文档获取报文
		testplan.setTestCaseSuiteList(xmlutils.readTestCaseSuite());

		int currentestcasesuitenum=0;//当前执行的测试用例集
		int testcasesuitecount=testplan.getTestCaseSuiteCount();
		System.out.println("testcasesuitecount ="+testcasesuitecount);

		int currenttestcasenum=0;//当前执行的测试用例集的 测试步骤
		//当执行的测试用例集标记大于总数 代表 结束
		HttpUtils httprequest=new HttpUtils();
		while(currentestcasesuitenum<testcasesuitecount) {
			TestCaseSuite curenttestcasesuit=testplan.TestCaseSuiteList.get(currentestcasesuitenum);
			int testcasecount=curenttestcasesuit.getTestCaseCount();
			System.out.println("testcasecount ="+testcasecount);
			while(currenttestcasenum<testcasecount) {
				TestCase curenttestcase=curenttestcasesuit.TestCaseList.get(currenttestcasenum);
				System.out.println("testcase name="+curenttestcase.getTestCaseName());
				
				
				if(curenttestcase.getTestType()==0) {
					
					//打印报文头的键值对信息
					HashMap<String, String> HeadValueMap=curenttestcase.getHeadValueMap();				
					for(Map.Entry<String, String> entry : HeadValueMap.entrySet()){	 
						  System.out.println("key= "+entry.getKey()+" and value= "+entry.getValue());
					}
					//设置 报文的头
					httprequest.setHeadValueMap(curenttestcase.getHeadValueMap());
				}
				if(curenttestcase.getTestType()==1) {
					
					String urlpath=curenttestcase.getHttpRequestUrlPath();					
					String myregex="\\$\\{([^\\}]+)\\}";
					Pattern pattern = Pattern.compile(myregex);  
					Matcher matcher = pattern.matcher(urlpath); 
					String repacestr;      
					while (matcher.find()) {     
						repacestr = matcher.group(1);
						System.out.println("repacestr=="+repacestr);
						if(testplan.getGlobalmap().get(repacestr)!=null) {
							urlpath = matcher.replaceFirst(testplan.getGlobalmap().get(repacestr) + "");     
							matcher = pattern.matcher(urlpath); 
						}
						    
				    }     
					
					System.out.println("URL="+urlpath+" and method= "+curenttestcase.getHttpRequestMethod()+" and body= "+curenttestcase.getHttpRequestBody());
					httprequest.setURL(urlpath);
					httprequest.setRequestmethod(curenttestcase.getHttpRequestMethod());
					httprequest.setRequestbody(curenttestcase.getHttpRequestBody());
					httprequest.setResponseAssertionStatusCode(curenttestcase.getResponseAssertionStatusCode());
					httprequest.setResponseAssertionMsg(curenttestcase.getResponseAssertionMsg());
					System.out.println("ResponseAssertionStatusCode="+curenttestcase.getResponseAssertionStatusCode());
					System.out.println("ResponseAssertionMsg="+curenttestcase.getResponseAssertionMsg());
					
					try {
						httprequest.sendHttpGetRequest();
						int passorfail=httprequest.getresult();
						System.out.println("testcase test result ="+passorfail);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				currenttestcasenum++;
			}
			
			currentestcasesuitenum++;
		}
		
		//获取HTTP报文返回结果
	}
	/*
	public static void main(String args[]) {
		 RunHttpTestPlan  tt=		new RunHttpTestPlan();
		  tt.start();

	}*/
	
	@Autowired
	private HttpTestResultMapper httpTestResultMapper ;
	
	// http://localhost:8084/gorun 
	
	@RequestMapping("/gorun")  
    public void GoRun(HttpServletRequest request,HttpServletResponse response) throws IOException{  
		RunHttpTestPlan  tt=		new RunHttpTestPlan();
		 // tt.start(); 
		  
		  HttpTestResult httpTestResult=new HttpTestResult();
		  httpTestResult.setResultContent("Test STEP content");
		  httpTestResult.setResultTitle("Test STEP title");
		  httpTestResult.setResultPassOrFail("PASS");
		 int insertId= httpTestResultMapper.add(httpTestResult);
		 System.out.println("插入"+insertId+"条数据" +"序号是"+httpTestResult.getId());
		  response.getWriter().write(ResponseEnmu.TEST_PLAN_RUNING.getDesc());
		  
        return  ;  
    }  
}
