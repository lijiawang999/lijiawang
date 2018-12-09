package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.BrandDao;
import com.zs.pms.dao.TCodeDao;
import com.zs.pms.dao.TypeDao;
import com.zs.pms.po.TBrand;
import com.zs.pms.po.TCode;
import com.zs.pms.po.TPtype;
import com.zs.pms.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    TCodeDao cdao;
    
    @Autowired
    BrandDao bdao;
    
    @Autowired
    TypeDao tdao;
    //注入缓存
    @Autowired
    RedisTemplate rs;
    /**
     * 将码表写入redis中
     */
	@Override
	public void setTCodes(String type) {
		// TODO Auto-generated method stub
		List<TCode> list=cdao.queryByType(type);
		rs.opsForValue().set(type, list);
		
	}
    /**
     * 从redis中读取码表
     */
	@Override
	public List<TCode> getTCodes(String type) {
		// TODO Auto-generated method stub
		return (List<TCode>) rs.opsForValue().get(type);
	}
	/**
     * 将品牌表写入redis中
     */
	@Override
	public void setBrands() {
		// TODO Auto-generated method stub
		rs.opsForValue().set("BRANDS", bdao.queryAll());
	}
	 /**
     * 从redis中读取品牌表
     */

	@Override
	public List<TBrand> getBrands() {
		// TODO Auto-generated method stub
		return (List<TBrand>) rs.opsForValue().get("BRANDS");
	}
	/**
     * 将商品列表表写入redis中
     */
	@Override
	public void setTypes(int pid) {
		// TODO Auto-generated method stub
		rs.opsForValue().set("TYPES"+pid, tdao.queryByPid(pid));

		
	}
	 /**
     * 从redis中读取商品列表
     */
	@Override
	public List<TPtype> getTypes(int pid) {
		// TODO Auto-generated method stub
		return (List<TPtype>) rs.opsForValue().get("TYPES"+pid);
	}
	@Override
	//从redis中获取中文名字 通过类型 
	public String getCodeName(String type, int cid) {
		// TODO Auto-generated method stub
		//从redis中
		List<TCode> list = (List<TCode>) rs.opsForValue().get(type);
		for (TCode code : list) {
			//从通码表中获得的pid
			//System.out.println(code.getCid());
			//传进来的id
			//System.out.println(cid);
			if (code.getCid() == cid) {
				
				//System.out.println(code.getCname());
				return code.getCname();
			}
		}
		return "";
	}

	

}
