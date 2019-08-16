package com.ssm.utils;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.ssm.dao.HttpTestResultMapper;
import com.ssm.entity.HttpTestResult;

public class TestMybatis {
	
	@Resource
	HttpTestResultMapper HttpTestResultdao;
    
	@Test
    public void testGetAllBooks() {
		
	//	HttpTestResultdao.add(httpTestResult);
		
     //   List<HttpTestResult> HttpTestResults=HttpTestResultdao.getHttpTestResults();
        
    }

}
