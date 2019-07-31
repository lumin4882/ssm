package com.ssm.utils;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.ssm.dao.HttpTestResultDAO;
import com.ssm.entity.HttpTestResult;

public class TESTMYBATIS {
	
	@Resource
	HttpTestResultDAO HttpTestResultdao;
    
	@Test
    public void testGetAllBooks() {
        List<HttpTestResult> HttpTestResults=HttpTestResultdao.getHttpTestResults();
        
    }

}
