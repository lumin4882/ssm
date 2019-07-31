package com.ssm.dao;

import java.util.List;

import com.ssm.entity.HttpTestResult;

public interface HttpTestResultDAO {
	
	//对实体的增改删查
    public List<HttpTestResult> getHttpTestResults();
 
    public HttpTestResult getHttpTestResultById(int id);
 
    public int add(HttpTestResult entity);

    public int delete(int id);

    public int update(HttpTestResult entity);
    

}
