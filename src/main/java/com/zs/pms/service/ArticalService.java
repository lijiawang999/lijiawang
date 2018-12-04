package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TArtical;
import com.zs.pms.vo.QueryArtical;

public interface ArticalService {
			/**
			 * 1.通过条件查询
			 */
			public List<TArtical> queryByCon(QueryArtical query) ;
			/**
			 * 2.根据id查id
			 * @param id
			 * @return
			 */
			public TArtical queryById(int id) ;
			public List<TArtical> queryByChannel(int channel) ;
			
			/**
			 * 3.新增
			 * @param artical
			 * @return
			 */
			public int insert(TArtical artical);
			/**
			 * 4.修改
			 * @param artical
			 */
			public void update(TArtical artical)throws AppException;
			/**
			 * 5.删除
			 * @param id
			 */
			public void deleteById(int id)throws AppException;
			/**
			 * 6.查询分页
			 * @param query
			 * @return
			 */
			public List<TArtical> queryByPage(QueryArtical query,int page);
			/**
			 * 7.批量删除
			 * @param ids
			 */
			public void deleteByIds(int []ids)throws AppException;
			/**
			 * 8.查询总数
			 * @param query
			 * @return
			 */
			public int queryCount(QueryArtical query);

}
