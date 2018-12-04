package com.zs.pms.vo;
/**
 * 条件查询
 * @author Administrator
 *
 */
public class QueryArtical extends QueryPage{
   /**
    * 标题
    * 作者
    */
   private String title;
   private String author;
   private int ishot;//是否热点
   private int isremod;
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
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}



}
