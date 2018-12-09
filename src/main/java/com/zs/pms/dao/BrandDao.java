package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TBrand;
/**
 * 品牌表DAO
 * @author Administrator
 *
 */
public interface BrandDao {
	//查询所有
   public List<TBrand> queryAll();
}
