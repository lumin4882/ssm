package com.ssm.dao;

import java.util.List;

import com.ssm.entity.HttpTestResult;
/*
Mapper动态代理开发所要遵循的四个原则
①.接口方法名与映射文件中Mapper.xml中id名一致。
②.返回值类型与Mapper.xml文件中返回值类型一致
③.方法的入参类型与Mapper.xml中的入参的类型一致
④.命名空间 绑定此接口
*/
public interface HttpTestResultMapper {
	
	//对实体的增改删查
	
//    public List<HttpTestResult> getHttpTestResults();
// 
//    public HttpTestResult getHttpTestResultById(int id);
 
     int add(HttpTestResult httpTestResult);

	 List<HttpTestResult> getHttpTestResults();

//    public int delete(int id);
//
//    public int update(HttpTestResult entity);
    

}
