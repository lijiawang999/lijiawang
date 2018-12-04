package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//检测登录方法
	public TUser chkLogin(QueryUser query) throws AppException;
	//通过条件查询
	public List<TUser> queryByCon(QueryUser query);
	//查询分页
	public List<TUser> queryByPage(QueryUser query,int page);
	//根据id查id
	public TUser queryById(int id);
	//批量删除
	public void deleteByIds(int []ids) throws AppException;
	//修改
	public void update(TUser user) throws AppException;
	//新增
	public int insert(TUser user)throws AppException;
	//查询总数
	public int queryCount(QueryUser query);
	//删除一条数据
	public void deleteById(int id)throws AppException;
	
	
	
}
