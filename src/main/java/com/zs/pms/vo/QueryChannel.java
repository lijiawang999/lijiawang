package com.zs.pms.vo;

import com.zs.pms.po.TChannel;

public class QueryChannel extends QueryPage{
    private String cname;
    private int isleaf;
    private TChannel channel;
    private int pid;
    
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	

}
