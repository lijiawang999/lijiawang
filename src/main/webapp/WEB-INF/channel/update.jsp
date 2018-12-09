<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 引时间控件 -->
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>栏目新增</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>
<script type="text/javascript">
 //验证栏目名
 var CHKCNAME="^[\u4e00-\u9fa5]{2,6}$";
 function chkCame(){
	 var cname=$("#cname").val();
	//定义匹配用户名的正则表达式
		var reg=new RegExp(CHKCNAME);
	if(reg.test(cname)){
		$("#resultCname").html("✔");
		$("#resultCname").css("color","green");
		return true;
	}else{
		$("#resultCname").html("栏目名必须是2到6个汉字组成");
		$("#resultCname").css("color","red");
		$("#cname").val("");
		$("#cname").focus();
		return false;				
	}	 	 
 }
 //验证栏目名
 var CHKSORT="^[0-9]{1,6}$"
 function chkSort(){
	 var sort=$("#sort").val();
	//定义匹配用户名的正则表达式
		var reg=new RegExp(CHKSORT);
	if(reg.test(sort)){
		$("#resultSort").html("✔");
		$("#resultSort").css("color","green");
		return true;
	}else{
		$("#resultSort").html("顺序必须是1-6位数字");
		$("#resultSort").css("color","red");
		$("#sort").val("");
		$("#sort").focus();
		return false;				
	}	 	 
 }




</script>
</head>
<body>
	<div>
		<img src="../images/logo4.png"/>	
	</div>
<div class="box-positon">
	<div class="rpos">当前位置: 栏目管理 - 修改</div>
	<form class="ropt">
	    
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="update.do?id=${CHANNEL.id }" method="post" onsubmit="return chkAll()">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
			     <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>				
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						栏目名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="cname" id="cname" maxlength="100" size="50" value=${CHANNEL.cname } />
						<span id="resultCname"></span>
					</td>
				</tr>
				<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属栏目:</td>
						<td width="80%" class="pn-fcontent">
						     <select name="pid">
							 <c:forEach items="${LIST}" var="name">
							       <c:if test="${CHANNEL.pid==name.id  }">
					                  <option value="${name.id}" selected="selected">${name.cname}</option>		       
							       </c:if>
									
							       <c:if test="${CHANNEL.pid!=name.id  }">
					                  <option value="${name.id}">${name.cname}</option>		       
							       </c:if>
									
								
							  </c:forEach>
					         </select>
						</td>
					</tr>				
			</tbody>
			
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交" onclick="return confirm('是否确定提交')"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>