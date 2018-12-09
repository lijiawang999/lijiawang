package com.zs.pms.po;

import java.io.Serializable;
/**
 * 通用码表
 * @author Administrator
 *
 */
public class TCode implements Serializable {
	
	
   /**
	 * 
	 */
	private static final long serialVersionUID = -8671858129746305409L;
   private int id;
   private int cid;//通用码编号
   private String cname;//通用码名称
   private String type;//通用码类型
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
	

}
