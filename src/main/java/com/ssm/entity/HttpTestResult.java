package com.ssm.entity;

public class HttpTestResult {
	public int     id;
	public String  ResultTitle;
	public String  ResultContent;
	public String  ResultPassOrFail;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResultTitle() {
		return ResultTitle;
	}
	public void setResultTitle(String resultTitle) {
		ResultTitle = resultTitle;
	}
	public String getResultContent() {
		return ResultContent;
	}
	public void setResultContent(String resultContent) {
		ResultContent = resultContent;
	}
	public String getResultPassOrFail() {
		return ResultPassOrFail;
	}
	public void setResultPassOrFail(String resultPassOrFail) {
		ResultPassOrFail = resultPassOrFail;
	}
	

}
