package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TCode;
/**
 * 通码表DAO
 * @author Administrator
 *
 */
public interface TCodeDao {
	//通过类型查询
     public List<TCode> queryByType(String type);
    	 
     
}
