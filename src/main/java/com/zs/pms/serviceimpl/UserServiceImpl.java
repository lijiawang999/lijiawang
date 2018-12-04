package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryPage;
import com.zs.pms.vo.QueryUser;


@Service //业务对象
@Transactional //该业务支持事物
public class UserServiceImpl implements UserService{
	@Autowired
   private UserDao ud;	
	@Override
	/**
	 * 1.检测登录的方法
	 * 
	 */
	public TUser chkLogin(QueryUser query) throws AppException {
           MD5 md5=new MD5();
          String p1=md5.getMD5ofStr(query.getPassword());
           query.setPassword(p1);
		List<TUser> list=ud.queryByCon(query);
		//没有用户		
		if(list==null||list.size()!=1){
			throw new AppException(100, "用户名或密码有误，请重新输入");
		}
		//获得第一条
		TUser user=list.get(0);
		//返回第一条
		return ud.queryById(user.getId());
	}
	@Override
	/**
	 * 2.通过条件查询
	 */
	
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return ud.queryByCon(query);
	}
	@Override
	/**
	 *3. 批量删除
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteByIds(int[] ids) throws AppException {
		if (ids==null) {
			throw new AppException(107, "请至少选择一条数据");
		}
		
		ud.deleteByIds(ids);
		
	}
	@Override
	/**
	 * 4.修改
	 */
	@Transactional(rollbackFor=Exception.class)
	public void update(TUser user) throws AppException {
		if (user.getIsenabled()==-1) {
			throw new AppException(1003, "不能修改不可用的用户");
		}
		TUser ouser=ud.queryById(user.getId());
		if (user.getPassword()!=null&&(!"".equals(user.getPassword()))&&!user.getPassword().equals(ouser.getPassword())) {
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		ud.update(user);
		
	}
	/**
	 *5. 新增
	 */
	@Override
	//有异常就回滚否则提交
	@Transactional(rollbackFor=Exception.class)
	public int insert(TUser user) throws AppException {
		if ("admin".equals(user.getLoginname())||user.getLoginname()==null) {
			throw new AppException(1001, "用户名无效，重新输入");
		}
		if (user.getPassword()!=null&&(!"".equals(user.getPassword()))) {
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		
		if (user.getPassword()=="") {
			throw new AppException(1003, "密码不能为空，重新输入");
		}
		if (user.getRealname()==null) {
			throw new AppException(1004, "真实姓名不能为空，重新输入");
		}
		if (user.getBirthday()==null) {
			throw new AppException(1005, "生日不能为空，重新输入");
		}

		return ud.insert(user);
	}
	@Override
	/**
	 * 6.查询分页
	 */
	public List<TUser> queryByPage(QueryUser query, int page) {
		query.setPage(page);
		return ud.queryByPage(query);
	}
	@Override
	/**
	 *7. 查询一条
	 */
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return ud.queryById(id);
	}
	
	
	@Override
	/**
	 *8. 通过id删除一条
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteById(int id) throws AppException{
		
		ud.deleteById(id);
		
	}
	@Override
	/**
	 * 9.查询总页数
	 * @param query
	 * @return
	 */
	public int queryCount(QueryUser query) {
		int count=ud.queryCount(query);
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		}else {
			return count/Constants.PAGECOUNT+1;
		}
		
	}
	

}
