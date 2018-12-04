package com.zs.pms.po;

import java.io.Serializable;

public class TPpic implements Serializable{

	/**
	 * 商品图片表
	 */
	private static final long serialVersionUID = -6239594081105008105L;
	private int id;
	private int	pid;//商品id
	private String	picurl;//图片url
	private int	isdefault;//是否默认
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public int getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(int isdefault) {
		this.isdefault = isdefault;
	}
	
	
	

	

}
