package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TDep;

public interface DeptDao {
	//通过上级id查部门名称
       public List<TDep> queryByPid(int pid);
}
