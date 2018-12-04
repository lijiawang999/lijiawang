package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TArtical;
import com.zs.pms.vo.QueryArtical;

/**
 * 文章表的方法
 * @author Administrator
 *
 */
public interface ArticalDao {
	    //通过条件查询
		public List<TArtical> queryByCon(QueryArtical query);
		//根据id查id
		public TArtical queryById(int id);
		//新增
		public int insert(TArtical user);
		//修改
		public void update(TArtical user);
		//删除
		public void deleteById(int id);
		//查询分页
		public List<TArtical> queryByPage(QueryArtical query);
		//批量删除
		public void deleteByIds(int []ids);
		//查询总数
		public int queryCount(QueryArtical query);
		//通过所属栏目查文章
		public List<TArtical> queryByChannel(int channel);

}
