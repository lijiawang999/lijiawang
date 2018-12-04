package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TChannel;
import com.zs.pms.vo.QueryChannel;


public interface ChannelService {
	//查上级栏目
    public List<TChannel> queryByPid(int pid);
    //1.分页查询
    public List<TChannel> queryByPage(QueryChannel query,int page);
  //2.条件查询
    public List<TChannel> queryByCon(QueryChannel query);
    //3.查询总数
    public int queryCount(QueryChannel query);
    //4.删除一条
    public void deleteById(int id) throws AppException;
    //5.新增
    public int insert(TChannel channel) throws AppException;
    //4.修改
    public void update(TChannel channel) throws AppException;
    //5查询一条
    public TChannel queryById(int id);
}
