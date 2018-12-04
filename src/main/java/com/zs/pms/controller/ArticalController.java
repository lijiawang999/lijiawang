package com.zs.pms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TArtical;
import com.zs.pms.po.TChannel;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TUser;
import com.zs.pms.service.ArticalService;
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryArtical;

@Controller
public class ArticalController {
     @Autowired
     ArticalService as;
     @Autowired
     ChannelService cs;
     @RequestMapping("/artical/list.do")
     public String articalList(QueryArtical query,ModelMap map,String page){
    	 if (page==null||"".equals(page)) {
    			page="1";
    		}    	
        //带回分页数据
    	 map.addAttribute("LIST",as.queryByPage(query, Integer.parseInt(page)));
    	//带回总页数
    	 map.addAttribute("PAGECOUNT",as.queryCount(query));
    	//带回当前页
    	 map.addAttribute("PAGE", page);
    	//带回查询条件
    	 map.addAttribute("QUERY", query);
    	 return "artical/list";
     }
     /**
      * 1.初始化新增页面的栏目信息
      * @param map
      * @return
      */
    @RequestMapping("artical/toadd.do")
    public String addArtical(ModelMap map){
    	List<TChannel> list1=cs.queryByPid(0);
    	map.addAttribute("LIST1", list1);
    	List<TChannel> list2=cs.queryByPid(list1.get(0).getId());
    	map.addAttribute("LIST2",list2);
    	return "artical/add";
    }
    /**
     * 2.获取栏目
     * @ResponseBody以ajax方式相应
     * 方法返回string 直接返回文本
     * 方法返回对象 返回json格式 自动调用jsonarray
     * pid 传进来的id值
     */
   @RequestMapping("/artical/getcha.do")
   @ResponseBody
   public List<TChannel> getCha(int pid){
	   List<TChannel> list=cs.queryByPid(pid);
	   return list;
   }
   /**
    * 3.新增
    * @param artical
    * @return
    */
   @RequestMapping("/artical/add.do")
   public String add(TArtical artical){
	  as.insert(artical);
	  return "redirect:list.do";
	   
   }
   /**
    * 4.删除一条
    * @param id
    * @return
    */
   @RequestMapping("/artical/delete.do")
   public String delete(int id){
	   try {
		as.deleteById(id);
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return "redirect:list.do";
   }
   /**
    * 5.批量删除
    * @param ids
    * @return
    */
   @RequestMapping("/artical/deletes.do")
   public String deletes(int [] ids){
	   try {
		as.deleteByIds(ids);
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return  "redirect:list.do";
   }
   /**
    * 6.获得文章的信息
    */
   
    @RequestMapping("/artical/articalget.do")
    public String get(int id,ModelMap map){
    	TArtical artical=as.queryById(id);
    	//返回数据
    	map.addAttribute("ARTICAL", artical); 
    	//获得一级栏目 也就是上级栏目编号为0
    	List<TChannel> list1=cs.queryByPid(0);
    	map.addAttribute("LIST1", list1);
    	//获得二级栏目     查询上级栏目为1的栏目 有多条     
    	//该文章所属栏目5的上级栏目编号 也就是一级栏目编号比如1
    	List<TChannel> list2=cs.queryByPid(artical.getChannel().getPid());
    	//结果集就是指定一级栏目的二级栏目
    	map.addAttribute("LIST2",list2);
    	return "artical/update";
    }
    /**
     * 修改
     * @param session
     * @param artical
     * @param map
     * @return
     */
    @RequestMapping("/artical/update.do")
    public String update(HttpSession session,TArtical artical,ModelMap map){
    	//获得session中的信息
    	TUser cuser=(TUser) session.getAttribute("USER");
    	artical.setUpdator(cuser.getRealname());
    	artical.setUptime(new Date());
    	try {
			as.update(artical);
			return "redirect:list.do";
		} catch (AppException e) {
			map.addAttribute("MSG", e.getErrMsg());
			return get(artical.getId(), map);
		}
    	
    }
}
