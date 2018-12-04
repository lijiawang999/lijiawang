package com.zs.pms.po;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.zs.pms.utils.DateUtil;
/*
 * 用户表
 */
public class TUser implements Serializable {

	/**
	 * 网络唯一序列号
	 */
	private static final long serialVersionUID = 5293214558214995122L;
	private int id;//用户id
	private String loginname;//用户名
	private String password;//密码
	private String sex;//性别
	private Date birthday;//生日
	private String email;//游戏
	private TDep dept;//部门表关联对象 一对一
	//private int dept;
	private String realname;//真实姓名
	private int creator;//创建人
	private Date creatime;//创建时间
	private int  uodator;//修改人
	private Date updatime;//修改时间
	private   String pic;//头像
	private int isenabled;//是否可用
	private String isenabledTxt;
	
	private String birthdayTxt;
	private String updatimeTxt;
	private String creatimeTxt;
	
	
	
	
	
	
	public String getCreatimeTxt() {
		return DateUtil.getDateToStr(creatime, "yyyy-mm-dd");
	}

	public String getUpdatimeTxt() {
		if (updatime!=null) {
			return DateUtil.getDateToStr(updatime, "yyyy-mm-dd");
		}else {
			return "不曾修改过";
		}
		
	}

	public String getBirthdayTxt() {
		
		return DateUtil.getDateToStr(birthday, "yyyy-mm-dd");
	}
	
	public String getIsenabledTxt() {
		if (isenabled==1) {
			return "可用";
		}else {
			
			return "不可用";
		}
	}
	

	//整理后的菜单
	private List<TPermission> meau=new ArrayList<>();
	//整理菜单
	public List<TPermission> getMeau() {
		//遍历原始菜单
		for(TPermission per1:permission){
			//一级菜单 上级pid等于0
			if (per1.getPid()==0) {
				//装载二级菜单
				for (TPermission per2:permission) {
					//二级菜单 二级菜单的上级id等于一级菜单的id
					if (per1.getId()==per2.getPid()) {
						//把二级菜单存在Children里
						per1.getChildren().add(per2);
					}
				}
			//把一级菜单存进meau
			meau.add(per1);
			}
						
		}
		return meau;
		
		
	}
	
	//一对多 关联权限列表
	private List<TPermission> permission;
	public List<TPermission> getPermission() {
		return permission;
	}
	public void setPermission(List<TPermission> permission) {
		this.permission = permission;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public TDep getDept() {
		return dept;
	}
	public void setDept(TDep dept) {
		this.dept = dept;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public Date getCreatime() {
		return creatime;
	}
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	public int getUodator() {
		return uodator;
	}
	public void setUodator(int uodator) {
		this.uodator = uodator;
	}
	public Date getUpdatime() {
		return updatime;
	}
	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
}
