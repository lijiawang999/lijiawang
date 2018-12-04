<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="res/css/style.css" />
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<title>user-update</title>

<!-- 引入jqyery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<!-- 引入时间控件 -->
<script type="text/javascript" language="javascript" src="../js/DatePicker/WdatePicker.js"></script>




<!-- 正则表达式 -->
<script type="text/javascript">
//用户名必须包含数字和字母  长度不低于六位到十六位
var CHKLOGINNAME="^(?![0-9]+$)[a-zA-Z0-9]{6,16}$";
//出生日期     yyyy-MM-dd  月份1-12     日期1-31
var CHKDATE="^[0-9]{4}-0?[1-9]|1[0-2]-0?[1-9]|[1-2][0-9]|3[0-1]$";
//邮箱xxxxxx@xxx.com,可以包含_      企业邮箱qwe@huewei.com.cn
var CHKEMAIL="^[a-zA-Z0-9_]+@[a-z0-9]{2,5}(\\.[a-z]{2,3}){1,2}$";
//真实姓名
var CHKREALNAME="^[\u4e00-\u9fa5]{2,5}$";
//改用jquery方法验证
//验证用户名
function chkloginname(){
	//获取用户名
	var loginname=$("#loginname").val();
	//定义用户匹配的正则表达式
	var reg=new RegExp(CHKLOGINNAME);
	if(reg.test(loginname)){
		if(chkExistLoginname(loginname)){
			return true;
		}else{
			return false;
		}
	}else{
		$("#resultName").html("用户名必须包含数字与字母且不能低于六位");
		$("#resultName").css("color","red");
		$("#loginname").val("");
		$("#loginname").focus();
		return false;
	}
}
//检查用户名是否重复
function chkExistLoginname(loginname){
	var flag=false;
	$.ajax({
		//请求路径
		url:'chkuser.do',
		//请求方式
		type:'post',
		//请求参数
		data:'type=1&loginname='+loginname,
		//是否异步
		async:false,
		//预期服务器返回的数据类型
		dataType:'text',
		//响应成功调用回调函数
		success:function(flag){
			if(flag=='true'){//没有重复
				$("#resultName").html("✔");
				$("#resultName").css("color","green");
				flag=true;
			}else{
				$("#resultName").html("此用户名已存在");
				$("#resultName").css("color","red");
				$("#loginname").val("");
				$("#loginname").focus();
				flag=false;
			}
		},
		error:function(){
			alert('请求数据失败。。。');
		}
	});
	return flag;
}


//验证真实姓名
function chkrealname(){
	//获取真实姓名	
	var realname=$("#realname").val();
	//定义匹配用户名的正则表达式
	var reg=new RegExp(CHKREALNAME);
	
	if(reg.test(realname)){//表示输入正确
		$("#resultRealName").html("✔");
		$("#resultRealName").css("color","green");		
		return true;
	}else{//输入失败
		$("#resultRealName").html("您输入的信息不符合人名格式，2-5个汉字");
		$("#resultRealName").css("color","red");
		//清空文本框
		$("#realname").val("");
		//重新聚焦
	$("#realname").focus();
		return false;
	}
}
//验证邮箱
function chkemail(){
	//获取用户名
	var email=$("#email").val();	
	//定义匹配用户名的正则表达式
	var reg=new RegExp(CHKEMAIL);
	//获取用户名是否输入成功的元素对象
	var spanEle=document.getElementById("#resultEmail");
	if(reg.test(email)){//表示输入正确
		if (chkExistEmail(email)) {
			return true;
		} else {
            return false;
		}
	}else{//输入失败
		$("#resultEmail").html("邮箱格式不正确，请重新输入");
		$("#resultEmail").css("color","red");
	
		//清空文本框
		$("#email").val("");
		//重新聚焦
		$("#email").focus();
		return false;
	 }
	
}
//检查邮箱是否唯一
function chkExistEmail(email){
	var flag=false;
	$.ajax({
		//请求路径
		url:'chkuser.do',
		//请求方式
		type:'post',
		//请求参数
		data:'type=2&email='+email,
		//是否异步
		async:false,
		//预期服务器返回的数据类型
		dataType:'text',
		//响应成功调用回调函数
		success:function(flag){
			if(flag=='true'){//没有重复
				$("#resultEmail").html("✔");
				$("#resultEmail").css("color","green");
				flag=true;
			}else{
				$("#resultEmail").html("此邮箱已存在");
				$("#resultEmail").css("color","red");
				$("#email").val("");
				$("#email").focus();
				flag=false;
			}
		},
		error:function(){
			alert('请求数据失败。。。');
		}
	});
	return flag;
}
//验证所有
function chkAll(){
	if ((chkloginname()&&chkrealname()&&chkemail())==true) {
		return true;
	} else {
		alert("请把信息填写完整或者部分信息不符合标准");
		return false;
	}
	
}
//文档就绪事件
$(function(){

	$("#dept1").change(
		
	 function(){
			$("#dept2").empty();
		$.post(
		"getdep.do",
		{pid:this.value},
		function(date){
			
		  if(date!=null){
			$(date).each(function(){
		      $("#dept2").append("<option value="+this.id+">"+this.dname+"</option>");
			});  			  
		  }		
		},
		"json"
		);
	});
	
	
});
</script>
</head>
<body>
<div>
		<img src="../images/logo4.png"/>	
	</div>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="update.do" method="post" >
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${MSG }</span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						用户名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="loginname" id="loginname" maxlength="100" value=${USER.loginname } onblur="chkloginname()"/>
						<span id="resultName"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						密码:</td><td width="80%" class="pn-fcontent">
						<input type="password" class="required" name="password" id="password" maxlength="100" value=${USER.password } onblur="chkpassword()"/>
						<span id="resultPwd"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						确认密码:</td><td width="80%" class="pn-fcontent">
						<input type="password" class="required" name="repwd" id="repwd" value=${USER.password } maxlength="100" onblur="chkRePwd()" />						
						<span id="resultRepwd"></span>
					</td>
				</tr>
				
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						真实姓名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="realname" id="realname" maxlength="100" value=${USER.realname } onblur="chkrealname()"/>
						<span id="resultRealName"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td>
						<td width="80%" class="pn-fcontent">
						<!-- test后面接的是条件 获取当前要修改的用户信息 -->
						<c:if test="${USER.sex=='男' }">
							<input type="radio" name="sex" value="男" checked="checked"/>男
							<input type="radio" name="sex" value="女"/>女
						<!-- 默认为男或女 -->
						</c:if>
						<c:if test="${USER.sex=='女' }">
							<input type="radio" name="sex" value="男" />男
							<input type="radio" name="sex" value="女" checked="checked"/>女
						
						</c:if>
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						
						出生日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" name="birthday" id="birthday" maxlength="100" onclick="WdatePicker()" readonly="readonly" value=${USER.birthdayTxt } />
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td>
						<td width="80%" class="pn-fcontent">
						<select name="dept1" id="dept1">
							<c:forEach items="${DLIST}" var="dep1">
								<!-- 显示所属部门 -->
								<c:if test="${USER.dept.pid==dep1.id }">
									<option value="${dep1.id}" selected="selected">${dep1.dname}</option>
								</c:if>
								<!-- 显示其他部门 -->
								<c:if test="${USER.dept.pid!=dep1.id }">
									<option value="${dep1.id}">${dep1.dname}</option>
								</c:if>	
							</c:forEach>
						
					</select>
						<select name="dept.id" id="dept2">
							<c:forEach items="${DLIST2}" var="dep2">
								<!-- 显示所属部门 -->
								<c:if test="${USER.dept.id==dep2.id }">
									<option value="${dep2.id}" selected="selected">${dep2.dname}</option>
								</c:if>
								<!-- 显示其他部门 -->
								<c:if test="${USER.dept.id!=dep2.id }">
									<option value="${dep2.id}">${dep2.dname}</option>
								</c:if>	
							</c:forEach>
						
					</select>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						邮箱:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="email" id="email" maxlength="80" value=${USER.email }  onblur="chkemail()"/>
						<span id="resultEmail"></span>
					</td>
				</tr>
				<input type="hidden" name="isenabled" value="${USER.isenabled }"/>
				<input type="hidden" name="id" value="${USER.id }"/>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交" onclick="return confirm('确认提交吗')"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>

