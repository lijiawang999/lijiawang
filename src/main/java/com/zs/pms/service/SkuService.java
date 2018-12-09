package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.TSku;

public interface SkuService {
	//查询所有根据pid
	public List<TSku> queryByPid(int pid);
	//修改
	public void update(TSku sku);

	
    
}
