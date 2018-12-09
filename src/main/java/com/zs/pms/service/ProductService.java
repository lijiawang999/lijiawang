package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TProduct;

public interface ProductService {
	//查所有
	 public List<TProduct> queryAll();
	 //新增
	 public int addProduct(TProduct product) throws AppException;
	 //查询一条
	    public TProduct queryById(int id);
}
