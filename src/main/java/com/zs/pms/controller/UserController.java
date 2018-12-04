package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.CsrfPreventionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DeptService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
public class UserController {
@Autowired
UserService us;
@Autowired
DeptService ds;
/**
 * 1.查询列表
 * @param query 获取页面信息
 * @param map 往页面带数据
 * @param page 接收页码数
 * @return
 */
@RequestMapping("/user/list.do")
public String userlist(QueryUser query,ModelMap map,String page){
	if (page==null||"".equals(page)) {
		page="1";
	}
	    //带回分页数据
		map.addAttribute("LIST", us.queryByPage(query, Integer.parseInt(page)));
		//带回总页数
		map.addAttribute("PAGECOUNT",us.queryCount(query));
		//带回当前页
		map.addAttribute("PAGE", page);
		//带回查询条件	
		map.addAttribute("QUERY", query);
		//返回user/list.jsp
		return "user/list";
	
	
}
/**
 *2. 初始化部门信息
 * @param map
 * @return
 */
@RequestMapping("user/toadd.do")
public String adduser(ModelMap map){
	//获得一级部门列表
	List<TDep> list1=ds.queryByPid(0);
	map.addAttribute("DLIST",list1);
	//获得上述集合中第一条信息二级部门
	List<TDep> list2=ds.queryByPid(list1.get(0).getId());
	map.addAttribute("DLIST2", list2);
	return "user/add";
}
/**
 * 3.获取部门
 * @ResponseBody以ajax方式相应
 * 方法返回string 直接返回文本
 * 方法返回对象 返回json格式 自动调用jsonarray
 */
@RequestMapping("/user/getdep.do")
@ResponseBody
public List<TDep> getDet(int pid){
	List<TDep> list=ds.queryByPid(pid);
	return list;
}

/**
 * 4.新增提交
 */
@RequestMapping("/user/add.do")
public String add(TUser user,ModelMap map,HttpSession session){
	try {
		//获取session中的信息
		TUser cuser=(TUser) session.getAttribute("USER");
		user.setCreator(cuser.getId());
		user.setIsenabled(1);
		
		us.insert(user);
		//跳到指定的url上 不需要传参
		return "redirect:list.do";
	} catch (AppException e) {
		map.addAttribute("MSG", e.getErrMsg());
		//执行方法 传参
		return this.adduser(map);
	}
}
/**
 * 删一条
 */
@RequestMapping("/user/userdelete.do")
public String delete(int id){	
		try {
			us.deleteById(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	return "redirect:list.do";
}
/**
 * 删多条
 */
@RequestMapping("/user/deletes.do")
public String deleteUsers(int [] ids,QueryUser query,ModelMap map,String page){		
	  try {
		us.deleteByIds(ids);
		return "redirect:list.do";
	} catch (AppException e) {
		// TODO Auto-generated catch block
		map.addAttribute("MSG", e.getErrMsg());
		return userlist(query, map, page);
	}				
	
}
/**
 * 修改1 获得
 */
@RequestMapping("/user/userget.do")
public String get(int id,ModelMap map){
	TUser user=us.queryById(id);
	//返回数据
	map.addAttribute("USER", user);
	//获得一级部门
	List<TDep> list1=ds.queryByPid(0);
	map.addAttribute("DLIST", list1);
	//获得二级部门
	List<TDep> list2=ds.queryByPid(user.getDept().getPid());
	map.addAttribute("DLIST2", list2);
	return "user/update";
}
/**
 * 修改2
 * @param user
 * @param session
 * @param map
 * @return
 */
@RequestMapping("/user/update.do")
private String update(TUser user,HttpSession session,ModelMap map){
	//获得session中的信息
	TUser cuser=(TUser) session.getAttribute("USER");
	user.setUodator(cuser.getId());
	try {
		us.update(user);
		return "redirect:list.do";
	} catch (AppException e) {
		// TODO Auto-generated catch block
		map.addAttribute("MSG",e.getErrMsg());
		//调用上面的信息
		return get(user.getId(), map);
	}
	
}
}
