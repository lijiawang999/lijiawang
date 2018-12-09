package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TProduct;

public interface ProductDao {
	//查询所有
    public List<TProduct> queryAll();
    //新增
    public int insert(TProduct product);
    //查询一条
    public TProduct queryById(int id);
}
