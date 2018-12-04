package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

import com.zs.pms.utils.DateUtil;

public class TArtical implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6589871743450296813L;
   private int id;
   private String title;//
   private String content;//
   private String author;//
   private Date ctime;//
   private String updator;//
   private Date uptime;//
   private TChannel channel;//一对一关联
  // private int channel;//上级栏目
   private int isremod;//是否推荐
   private String isremodTxt;
   private int ishot;//是否热点
   private String ishotTxt;
   private String ctimeTxt;
   
   
 
public String getCtimeTxt() {	
		return DateUtil.getDateToStr(ctime, "yyyy-mm-dd");	
}



public String getIsremodTxt() {
	if (isremod==1) {
		return "推荐";
	}else {
		return "不推荐";
	}
	
}

public String getIshotTxt() {
	if (ishot==1) {
		return "热点";
	} else {
		return "非热点";
	}
	
}

public TChannel getChannel() {
	return channel;
}
public void setChannel(TChannel channel) {
	this.channel = channel;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public Date getCtime() {
	return ctime;
}
public void setCtime(Date ctime) {
	this.ctime = ctime;
}
public String getUpdator() {
	return updator;
}
public void setUpdator(String updator) {
	this.updator = updator;
}
public Date getUptime() {
	return uptime;
}
public void setUptime(Date uptime) {
	this.uptime = uptime;
}

public int getIsremod() {
	return isremod;
}
public void setIsremod(int isremod) {
	this.isremod = isremod;
}
public int getIshot() {
	return ishot;
}
public void setIshot(int ishot) {
	this.ishot = ishot;
}
	

}
