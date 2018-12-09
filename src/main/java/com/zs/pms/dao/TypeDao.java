package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPtype;
/**
 * 商品列表
 * @author Administrator
 *
 */
public interface TypeDao {
	//通过上级目录查询
     public List<TPtype> queryByPid(int pid);
}
