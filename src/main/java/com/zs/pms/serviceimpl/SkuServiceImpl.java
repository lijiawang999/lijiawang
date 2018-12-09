package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.SkuDao;
import com.zs.pms.po.TSku;
import com.zs.pms.service.SkuService;
@Service
public class SkuServiceImpl implements SkuService{
   @Autowired
   SkuDao sd;
	@Override
	public List<TSku> queryByPid(int pid) {
		// TODO Auto-generated method stub
		return sd.queryByPid(pid);
	}

	@Override
	public void update(TSku sku) {
		// TODO Auto-generated method stub
		sd.update(sku);
	}

	

}
