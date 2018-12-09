package com.zs.pms.po;

import java.io.Serializable;

import com.zs.pms.utils.DateUtil;

public class TProduct implements Serializable{

	/**
	 * 商品表
	 */
	private static final long serialVersionUID = 579407442283499756L;
	private int id;
	private String pname;//商品名
	private int brand;//品牌
	private double weight;//中路
	private int isnew;//是否新品
	private int ishot;//是否热销
	private int isrecommend;//是否推荐
	private int ptype;//商品类别
	private int creator;//
	private DateUtil creatime;//
	private int chktor;//审核人
	private DateUtil chktime;//审核时间
	private int updator;
	private DateUtil updatime;
	private String status;//状态
	private String fromarea;//产地
	private String discribe;//描述 图片内容
	private String packlist;//包装清单
	private String features;//属性集
	private String colors;//颜色集
	private String sizess;//尺寸集
	private String picturl;//图片
	
	
	public String getPicturl() {
		return picturl;
	}
	public void setPicturl(String picturl) {
		this.picturl = picturl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getBrand() {
		return brand;
	}
	public void setBrand(int brand) {
		this.brand = brand;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getIsnew() {
		return isnew;
	}
	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	public int getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}
	public int getPtype() {
		return ptype;
	}
	public void setPtype(int ptype) {
		this.ptype = ptype;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public DateUtil getCreatime() {
		return creatime;
	}
	public void setCreatime(DateUtil creatime) {
		this.creatime = creatime;
	}
	public int getChktor() {
		return chktor;
	}
	public void setChktor(int chktor) {
		this.chktor = chktor;
	}
	public DateUtil getChktime() {
		return chktime;
	}
	public void setChktime(DateUtil chktime) {
		this.chktime = chktime;
	}
	public int getUpdator() {
		return updator;
	}
	public void setUpdator(int updator) {
		this.updator = updator;
	}
	public DateUtil getUpdatime() {
		return updatime;
	}
	public void setUpdatime(DateUtil updatime) {
		this.updatime = updatime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFromarea() {
		return fromarea;
	}
	public void setFromarea(String fromarea) {
		this.fromarea = fromarea;
	}
	public String getDiscribe() {
		return discribe;
	}
	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}
	public String getPacklist() {
		return packlist;
	}
	public void setPacklist(String packlist) {
		this.packlist = packlist;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public String getSizess() {
		return sizess;
	}
	public void setSizess(String sizess) {
		this.sizess = sizess;
	}
	
	

}
