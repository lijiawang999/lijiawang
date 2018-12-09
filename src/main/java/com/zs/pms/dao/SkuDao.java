package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TSku;

public interface SkuDao {
	 //新增
	public int insert(TSku sku);
	//查询
	public List<TSku> queryByPid(int pid);
	//修改
	public void update(TSku sku);
}
