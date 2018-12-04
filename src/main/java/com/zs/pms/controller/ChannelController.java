package com.zs.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TChannel;
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryChannel;

@Controller
public class ChannelController {
@Autowired
ChannelService cs;
/**
 * 1.查询
 * @param map
 * @param query
 * @param page
 * @return
 */
@RequestMapping("/channel/list.do")
public String channellist(ModelMap map,QueryChannel query,String page){
	 if (page==null||"".equals(page)) {
			page="1";
		}
	//带回分页数据
	 map.addAttribute("LIST",cs.queryByPage(query, Integer.parseInt(page)));
	//带回总页数
	 map.addAttribute("PAGECOUNT",cs.queryCount(query));
	//带回当前页
	 map.addAttribute("PAGE", page);
	//带回查询条件
	 map.addAttribute("QUERY", query);
	 return "channel/list";
	 
}
/**
 *2. 删除	
 */
@RequestMapping("channel/delete.do")
public String delete(int id,ModelMap map,QueryChannel query,String page){
	try {
		cs.deleteById(id);
		return "redirect:list.do";
		
	} catch (AppException e) {
		// TODO Auto-generated catch block
	map.addAttribute("MSG", e.getErrMsg());
	return channellist(map, query, page);
	}
}
/**
 * 3.新增 先获取栏目列表
 */
@RequestMapping("channel/toadd.do")
public String toadd(ModelMap map){
	List<TChannel> channels=cs.queryByPid(0);
	map.addAttribute("LIST", channels);
	return "channel/add";
}
/**
 * 4.新增
 */
@RequestMapping("/channel/add.do")
public String  add(TChannel channel){
	try {
		
		cs.insert(channel);
		return "redirect:list.do";
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "redirect:list.do";
	}
}	

/**
 * 5.修改获取栏目
 */
//	@RequestMapping("channel/get.do")
//	public String get(ModelMap map, int id){
//		TChannel channel=cs.queryById(id);
//		map.addAttribute("CHANNEL", channel);
//		List<TChannel> channel2=cs.queryByPid(0);
//		map.addAttribute("LIST", channel2);		
//		return "channel/update";
//		
//	}
	
}

