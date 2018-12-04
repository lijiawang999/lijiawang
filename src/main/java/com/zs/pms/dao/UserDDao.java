package com.zs.pms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;
/*
 * 用注解的方式 不用xml
 */
@Repository
public interface UserDDao {
	 //通过条件查询
	@Select("select * from tuser where loginname=#{loginname} and password=#{password}")
	public List<TUser> queryByCon(QueryUser query);
}
