package com.zs.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
/**
 * 表述性状态转移
 * @author Administrator
 *
 */
@Controller
public class RestController {
	@Autowired
	UserService us;
    @RequestMapping(value="/userinfo/{id}.do")
    @ResponseBody
	public TUser queryById(@PathVariable("id") int id){
		return us.queryById(id);
	}

}
