

package com.zs.pms.po;

import java.io.Serializable;

public class TChannel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2017797701798209722L;
	  private int id;
	  private String cname;
	  private TChannel channel;//自己关联自己	   
	   private int pid;
	   private int lev;
	   private int isleaf;
	   private int sort;
	   private String isleaftTxt;
	   
	   
	   
	   
	 public String  getIsleaftTxt() {
		if (isleaf==1) {
			return "是";
		}else {
			return "不是";
		}
	}
	
	public int getPid() {	
			return pid;
	
		}
		public void setPid(int pid) {
			this.pid = pid;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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


}
