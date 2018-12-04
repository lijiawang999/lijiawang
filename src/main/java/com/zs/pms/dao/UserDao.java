package com.zs.pms.dao;


import java.util.List;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;
/**
 * 用户表数据接口
 */
public interface UserDao {
  //通过条件查询
	public List<TUser> queryByCon(QueryUser query);
	//根据id查id
	public TUser queryById(int id);	
	//新增
	public int insert(TUser user);
	//修改
	public void update(TUser user);
	//删除
	public void deleteById(int id);
	//查询分页
	public List<TUser> queryByPage(QueryUser query);
	//批量删除
	public void deleteByIds(int []ids);
	//查询总数
	public int queryCount(QueryUser query);
	
}
