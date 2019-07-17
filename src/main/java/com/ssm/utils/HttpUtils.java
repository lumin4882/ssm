package com.ssm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;



public class HttpUtils  {
	 
	public String   URL;

	public HashMap<String, String> myheadvaluemap;
	public String   requestmethod;
	public String   requestbody;
	public int    PassOrFail;//0= PASS  1=FAIL

	//校验的响应头的状态码
	public String responseAssertionStatusCode;
		
	//校验的响应头的状态消息内容
	public String responseAssertionMsg;
		
	public String getResponseAssertionStatusCode() {
		return responseAssertionStatusCode;
	}


	public void setResponseAssertionStatusCode(String StatusCode) {
		responseAssertionStatusCode = StatusCode;
	}


	public String getResponseAssertionMsg() {
		return responseAssertionMsg;
	}


	public void setResponseAssertionMsg(String AssertionMsg) {
		responseAssertionMsg = AssertionMsg;
	}


	public String getRequestbody() {
		return requestbody;
	}


	public void setRequestbody(String requestbody) {
		this.requestbody = requestbody;
	}


	public String getURL() {
		return URL;
	}


	public String getRequestmethod() {
		return requestmethod;
	}


	public void setRequestmethod(String requestmethod) {
		this.requestmethod = requestmethod;
	}


	public void setURL(String strURL) {
		URL = strURL;
	}

	public HashMap<String, String> getHeadValueMap() {
		return myheadvaluemap;
	}


	public void setHeadValueMap(HashMap<String, String> headValueMap) {			
		
		System.out.println("setHeadValueMap");
		myheadvaluemap = headValueMap;
	}
	//初始化报文头
	public void InitHttpGetRequestHeader(HttpRequestBase http) {
		
		for(Map.Entry<String, String> entry : myheadvaluemap.entrySet()){	 
			   System.out.println("key= "+entry.getKey()+" and value= "+entry.getValue());
			  http.setHeader(entry.getKey(), entry.getValue());
		}


	}
	


	public void sendHttpGetRequest() throws ClientProtocolException, IOException {
		
		System.out.println("bigin send request");
		//发送请求
		if(requestmethod.toUpperCase().equals("GET")&&(!URL.equals(""))) {

			HttpClient client = new DefaultHttpClient();
			System.out.println("URL=="+URL);
			HttpGet myhttpGet=new HttpGet(URL.trim());
			InitHttpGetRequestHeader(myhttpGet);
			
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
	                .setConnectionRequestTimeout(35000)// 请求超时时间
	                .setSocketTimeout(60000)// 数据读取超时时间
	                .build();
			myhttpGet.setConfig(requestConfig);
			
			System.out.println("http request  is send");
			HttpResponse response = client.execute(myhttpGet);

			//默认是成功的，防止用户未设置校验
			PassOrFail=0;
			//当用户设置的校验 状态码不为空进行校验
			if((responseAssertionStatusCode!=null)&&(!responseAssertionStatusCode.isEmpty())) {
				if(response.getStatusLine().getStatusCode() == Integer.parseInt(responseAssertionStatusCode)) {
					System.out.println("http response  is arrive");
					HttpEntity entity = response.getEntity();
					String string = EntityUtils.toString(entity);//获取消息体
					System.out.println("response body"+string);//
					//判断结果包含 指定字符串
					//当用户设置的消息体校验内容 不为空进行校验
					
					if(responseAssertionMsg!=null&&!responseAssertionMsg.isEmpty()) {
						if(string.contains(responseAssertionMsg)) {
							System.out.println("response body  contains "+responseAssertionMsg);
						}
						else {
							PassOrFail=1;
							System.out.println("response body not contains "+responseAssertionMsg);
						}
					}
				}
				else {
					PassOrFail=1;
				}
			}
			else {
				System.out.println("http response  StatusCode not need check "+response.getStatusLine().getStatusCode());
				
			}

		
			
		}
		else if(requestmethod.toUpperCase().equals("POST")) {
			
			HttpClient client = new DefaultHttpClient();
			HttpPost myhttppost=new HttpPost(URL);
			InitHttpGetRequestHeader(myhttppost);
			
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
	                .setConnectionRequestTimeout(35000)// 请求超时时间
	                .setSocketTimeout(60000)// 数据读取超时时间
	                .build();
			myhttppost.setConfig(requestConfig);
			myhttppost.setEntity(new StringEntity(requestbody,HTTP.UTF_8));
			
			HttpResponse response = client.execute(myhttppost);
			
			if(response.getStatusLine().getStatusCode() == 200) {
				System.out.println("http response  is arrive");
				HttpEntity entity = response.getEntity();
				String string = EntityUtils.toString(entity);//获取消息体
				System.out.println(string);//
				String result = entity.toString();
				System.out.println(result);
			}
			
		}
		
	}
	
	public int getresult() {
		return PassOrFail;
	}
	
	
	@Test
	public void testHttpGet() throws ClientProtocolException, IOException {
		String path = "http://119.23.204.160:8007";
		HttpClient client = new DefaultHttpClient();
	
		HttpGet httpGet = new HttpGet(path);
		
		
	
		httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
		httpGet.setHeader("Connection", "keep-alive");	
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
		httpGet.setHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
		httpGet.setHeader("Referer", "http://119.23.204.160:8007/");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		httpGet.setHeader("Cookie", "ZZCuId=5c5f029d-ef87-4aa3-8472-26259e1d1425; ZZCtype=0; gh=false; desktopH=593; NTCtype=0; NTCuserId=ytjzgc; NTCuId=b9358389-8426-11e8-b350-1866dae8e72d; JSESSIONID=F5E828A240BCC29C4E91EE2872044C0B");

		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
		httpGet.setConfig(requestConfig);
		HttpResponse response = client.execute(httpGet);
		
		if(response.getStatusLine().getStatusCode() == 200) {
			System.out.println("http response  is arrive");
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);//获取消息体
			System.out.println(string);//
			String result = entity.toString();
			System.out.println(result);
		}
		
	}	
		@Test
		public void testHttpPost() throws ClientProtocolException, IOException {
			String path = "http://119.23.204.160:8007";
			HttpClient client = new DefaultHttpClient();
		
			HttpPost httppost = new HttpPost(path);
		
			httppost.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			httppost.setHeader("Connection", "keep-alive");	
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
			httppost.setHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
			httppost.setHeader("Referer", "http://119.23.204.160:8007/");
			httppost.setHeader("Accept-Encoding", "gzip, deflate");
			httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			httppost.setHeader("Cookie", "ZZCuId=5c5f029d-ef87-4aa3-8472-26259e1d1425; ZZCtype=0; gh=false; desktopH=593; NTCtype=0; NTCuserId=ytjzgc; NTCuId=b9358389-8426-11e8-b350-1866dae8e72d; JSESSIONID=F5E828A240BCC29C4E91EE2872044C0B");
		
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
	                .setConnectionRequestTimeout(35000)// 请求超时时间
	                .setSocketTimeout(60000)// 数据读取超时时间
	                .build();
			httppost.setConfig(requestConfig);
			
			
			/*键值对消息体
			// 获得实例集合
		    List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		    // 添加要查询数据的参数
		    parameters.add(new BasicNameValuePair("cityname", "北京"));
		    parameters.add(new BasicNameValuePair("key", "aaaaaaaaaaa"));
		    //UrlEncodedFormEntity 发送的消息体格式是 cityname=北京&key=aaaaaaaaaaa ，不建议使用
		    //这种格式KEY1=VALUE1&KEY2=VALUE2。。。。。。。。。。。。。。。。
		    //建议使用  StringEntity
		    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
	                parameters, "UTF-8");
		    String string1= EntityUtils.toString(urlEncodedFormEntity);
		    
		    System.out.println(string1);
		      // 设置到实体类
		    httppost.setEntity(urlEncodedFormEntity);
		    */
		    /* JSON 消息体*/
		    JSONObject jsonObject = new JSONObject();
		    jsonObject.put("KEY1", "VALUE1");
		    jsonObject.put("KEY2", "VALUE2");
		    httppost.setEntity(new StringEntity(jsonObject.toString(),HTTP.UTF_8));

            String string1= EntityUtils.toString(new StringEntity(jsonObject.toString()));
		    
		    System.out.println(string1);
		    System.out.println(jsonObject.toString());
		    
			HttpResponse response = client.execute(httppost);
			
			if(response.getStatusLine().getStatusCode() == 200) {
				System.out.println("http response  is arrive");
				HttpEntity entity = response.getEntity();
				String string = EntityUtils.toString(entity);//获取消息体
				System.out.println(string);//
				String result = entity.toString();
				System.out.println(result);
			}
	
			
	}
	
	

}
