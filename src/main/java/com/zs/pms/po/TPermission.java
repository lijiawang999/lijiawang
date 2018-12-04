package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
 * 权限表
 */
public class TPermission implements Serializable{

	/**
	 *  网络唯一序列号
	 */
	private static final long serialVersionUID = -7512049132416900243L;
	private int  id;//权限id
	private String pname;//权限名称
	private int  pid;//上级权限
	private int lev;//层级
	private int  isleaf;//是否叶子
	private int sort;//顺序
	private String url;//地址
	private String icon;//图标
	
	//子权限
	private List<TPermission> children=new ArrayList<>();
	
	
	public List<TPermission> getChildren() {
		return children;
	}
	public void setChildren(List<TPermission> children) {
		this.children = children;
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
