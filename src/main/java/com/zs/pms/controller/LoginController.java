package com.zs.pms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhlabs.composite.AddComposite;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.RedisService;
import com.zs.pms.service.UserService;
import com.zs.pms.serviceimpl.UserServiceImpl;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.vo.QueryUser;





@Controller //控制层
public class LoginController {
	@Autowired //装配业务层通过
	UserService us;
	@Autowired
	RedisService rs;
	@RequestMapping("/tologin.do")
	public String toLogin(){
		return "login";
	}
    /**
     * 
     * @param query 用户名和密码
     * @param code 验证码
     * @param map 数据带回页面
     * @param session 
     * @return
     */
    @RequestMapping("/login.do") 
	public String login(QueryUser query,String code,ModelMap map,HttpSession session){
    	//获取图片中的验证码
    	String coded=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
    	//判断
		if(!code.equals(coded)){
			session.setAttribute("err", "验证码输入有误，请重新输入");			
		     return "login";			
		}
		try {
			//用TUser类型的变量接收查询到的信息
			TUser user=us.chkLogin(query);
			//用TPermission类型变量接收该用户的一级权限
			List<TPermission> permissions=user.getMeau();
			//通过session将一级权限传递传递到页面上
			session.setAttribute("permissions", permissions);
			//将用户信息传递到页面上
			session.setAttribute("USER", user);
		    session.setAttribute("TIME",DateUtil.getDateToStr(new Date(), "yyyy-MM-dd"));
			//跳转到主页
		    rs.setTCodes("F");//材质
			rs.setTCodes("C");//颜色
			rs.setTCodes("S");//尺码
			rs.setBrands();//品牌
			rs.setTypes(1);//上衣类型的子类别

			return "main";
		} catch (AppException e) {
			//账号密码不正确提示错误信息
			map.addAttribute("err", e.getErrMsg());
			return "login";
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
   
    @RequestMapping("/toleft.do") 
    public String toleft(){
    	return "left";
    }
    @RequestMapping("/toright.do") 
    public String toright(){
    	return "right";
    }
    @RequestMapping("/totop.do") 
    public String totop(){
    	return "top";
    }
    	
    }
	


