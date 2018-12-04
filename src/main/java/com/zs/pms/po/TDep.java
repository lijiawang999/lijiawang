package com.zs.pms.po;

import java.io.Serializable;
/*
 * 部门表
 */
public class TDep implements Serializable{

	/**
	 * 网络唯一序列号
	 */
	private static final long serialVersionUID = 6693929339309480899L;
   private int id;//部门编号
   private String dname;//部门名称
   private int pid;//上级部门编号
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDname() {
	return dname;
}
public void setDname(String dname) {
	this.dname = dname;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
   
}
