package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.ArticalDao;
import com.zs.pms.dao.ChannelDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TArtical;
import com.zs.pms.po.TChannel;
import com.zs.pms.service.ChannelService;
import com.zs.pms.utils.Constants;
import com.zs.pms.vo.QueryChannel;



@Service
public class ChannelServiceImpl implements ChannelService {
     @Autowired
     private ChannelDao cd;
     @Autowired
     private ArticalDao ad;
	@Override
	public List<TChannel> queryByPid(int pid) {
		// TODO Auto-generated method stub
		return cd.queryByPid(pid);
	}
	@Override
	public List<TChannel> queryByPage(QueryChannel query, int page) {
		query.setPage(page);
		return cd.queryByPage(query);
	}
	@Override
	public List<TChannel> queryByCon(QueryChannel query) {
		// TODO Auto-generated method stub
		return cd.queryByCon(query);
	}
	@Override
	public int queryCount(QueryChannel query) {
		// TODO Auto-generated method stub
		//先查到总数
		int count=cd.queryCount(query);
		//如果整除 页数为商 不整除 商+1
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		}else {
			return count/Constants.PAGECOUNT+1;
		}
	}
	/**
	 * 删除一条
	 */
	@Override
	public void deleteById(int id) throws AppException {
		/**
		 * 通过文章表的所属栏目查询文章，
		 * 文章表的所属栏目编号的等于栏目表所属的id
		 * 通过栏目表的上级栏目查询栏目信息，上级栏目编号等于
		 *所属栏目的id
		 *如果这两个集合有一个不是0就不能删除栏目了
		 */
		List<TArtical> artical=ad.queryByChannel(id);
		List<TChannel> channel=cd.queryByPid(id);
		if (artical.size()!=0||channel.size()!=0) {
			throw new AppException(200, "不能删除该栏目");
		}
		cd.deleteById(id);
		
	}
	/**
	 * 新增
	 */
	@Override
	public int insert(TChannel channel) throws AppException {
		// TODO Auto-generated method stub
		return cd.insert(channel);
	}
	/**
	 * 修改
	 */
	@Override
	public void update(TChannel channel) throws AppException {
		cd.update(channel);
		
	}
	@Override
	public TChannel queryById(int id) {
		// TODO Auto-generated method stub
		return cd.queryById(id);
	}
   

	

}
