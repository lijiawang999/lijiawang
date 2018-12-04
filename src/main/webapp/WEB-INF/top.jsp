<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>


	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎页面</title>
<script type="text/javascript">
function getTime(){
	//日期函数
	var date=new Date();
	//年
	var year=date.getFullYear();
	//月 
	var mon=date.getMonth()+1;
	if(mon<10){
		mon="0"+mon;
	}
	//日
	var d=date.getDate();
	if(d<10){
		d="0"+d;
	}
	//时
	var hour=date.getHours();
	if(hour<10){
		hour="0"+hour;
	}
	//分
	var min=date.getMinutes();
	if(min<10){
		min="0"+min;
	}
	//秒
	var sec=date.getSeconds();
	if(sec<10){
		sec="0"+sec;
	}
	document.getElementById("currentTime").innerHTML=year+"年"+mon+"月"+d+"日     "+hour+":"+min+":"+sec;
	//document.write(year+"年"+mon+"月"+d+"日     "+hour+":"+min+":"+sec);
}
//经过指定的毫秒数执行代码（只执行一次）
//window.setTimeout(getTime, 5000);
//经过指定的毫秒数重复执行代码
window.setInterval(getTime, 1000);
</script>
<link href="images/skin.css" rel="stylesheet" type="text/css" />

	
</head>
<body leftmargin="0" topmargin="0">
<%--
Date date=new Date();
pageContext.setAttribute("date", date);
--%>

	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="61%" height="64" valign="top"><img src="images/logo4.png"
				width="176" height="54" ></td>
			<td width="39%" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td width="54%" height="38" class="admin_txt"><b></b>
							&nbsp;&nbsp;&nbsp;&nbsp;${USER.realname }&nbsp;&nbsp;&nbsp;你好,今天是${TIME } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢登录使用！</td>
						
						<td width="42%">
						 <a href="login.do" class="pn-opt">
						   <img src="images/out.gif" alt="退出" width="46" height="20"border="0">
						 </a>
						</td>
						<td width="4%">&nbsp;</td>
					</tr>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>