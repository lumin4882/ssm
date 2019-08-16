package com.ssm.enmu;

public enum ResponseEnmu {

	TEST_PLAN_RUNING(1, "测试计划在运行"),
	TEST_PLAN_STOP(2,"测试计划已结束"),
	TEST_PLAN_PAUSE(3, "测试计划已暂停");
	
	
	private ResponseEnmu(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	
	private int code;
	private  String  desc;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
