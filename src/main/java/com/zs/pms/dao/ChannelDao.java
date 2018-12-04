package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TChannel;
import com.zs.pms.vo.QueryChannel;
/**
 * 栏目表
 * @author Administrator
 *
 */
public interface ChannelDao {
	//查上级栏目
      public List<TChannel> queryByPid(int pid);
      //1.新增
      public int insert(TChannel channel) ;
      //2.删除一条
      public void deleteById(int id) throws AppException;
      //3.删除多条
      public void deleteByIds(int [] ids) throws AppException;
      //4.修改
      public void update(TChannel channel) throws AppException;
      //5查询一条
      public TChannel queryById(int id);
      //6.条件查询
      public List<TChannel> queryByCon(QueryChannel query);
      //7.分页查询
     public List<TChannel> queryByPage(QueryChannel query);
      //8.查询总数
      public int queryCount(QueryChannel query);
    
      
	

}
