package com.zs.pms.exception;

public class AppException extends Exception{
	//异常码
	   private int errCode;
	   //异常信息
	   private String errMsg;
	   //创建构造函数
	public AppException(int errCode, String errMsg){
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	//加上Setter和Getter
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	   
}
