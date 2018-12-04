package com.zs.pms.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ArticalDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TArtical;
import com.zs.pms.service.ArticalService;
import com.zs.pms.utils.Constants;
import com.zs.pms.vo.QueryArtical;
@Service
public class ArticalServiceImpl implements ArticalService{
@Autowired
private ArticalDao ad;
/**
 * 1.通过条件查询
 */
@Override
public List<TArtical> queryByCon(QueryArtical query) {
	// TODO Auto-generated method stub	
	return ad.queryByCon(query);
}
/**
 * 2根据id查id
 * @param id
 */
@Override
public TArtical queryById(int id)  {
	
	return ad.queryById(id);
}
/**
 * 3.新增
 */
@Override
//有异常就回滚否则提交
	@Transactional(rollbackFor=Exception.class)
public int insert(TArtical artical) {
	// TODO Auto-generated method stub
	return ad.insert(artical);
}
/**
 * 4.修改
 */
@Override
//有异常就回滚否则提交
	@Transactional(rollbackFor=Exception.class)
public void update(TArtical artical) throws AppException {
	// TODO Auto-generated method stub
	ad.update(artical);
}
/**
 * 5.通过id删除一条
 */
@Override
//有异常就回滚否则提交
	@Transactional(rollbackFor=Exception.class)
public void deleteById(int id) throws AppException {
	// TODO Auto-generated method stub
	ad.deleteById(id);
}
/**
 * 6.分页查询
 */
@Override
public List<TArtical> queryByPage(QueryArtical query,int page)  {
	// TODO Auto-generated method stub
	query.setPage(page);
	return ad.queryByPage(query);
}
/**
 * 7.批量删除
 */
@Override
//有异常就回滚否则提交
	@Transactional(rollbackFor=Exception.class)
public void deleteByIds(int[] ids) throws AppException {
	// TODO Auto-generated method stub
	ad.deleteByIds(ids);
	
}
/**
 * 8.查询总页数
 */
@Override
public int queryCount(QueryArtical query)  {
	// TODO Auto-generated method stub
	//先查到总数
	int count=ad.queryCount(query);
	//如果整除 页数为商 不整除 商+1
	if (count%Constants.PAGECOUNT==0) {
		return count/Constants.PAGECOUNT;
	}else {
		return count/Constants.PAGECOUNT+1;
	}
	
}
@Override
public List<TArtical> queryByChannel(int channel) {
	// TODO Auto-generated method stub
	return ad.queryByChannel(channel);
}


	

}
