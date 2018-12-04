package com.zs.pms.vo;

import com.zs.pms.utils.Constants;

public class QueryPage{
       protected int start;//起始数
       protected int end;//终止数
       protected int page;//当前页
	public int getStart() {
		//计算起始页 （当前页-1）*每页条数+1		
		return (page-1)*Constants.PAGECOUNT+1;
		
	}
	public void setStart(int start) {
		this.start = start;
	}
	//计算截止数  页数乘以每页数  2*5=10
	public int getEnd() {
		return page*Constants.PAGECOUNT;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	

}
