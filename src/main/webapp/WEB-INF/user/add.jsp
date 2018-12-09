<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<title>user-add</title>
<!-- 引入jqyery函数库 -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<!-- 引入时间控件 -->
<script type="text/javascript" language="javascript" src="../js/DatePicker/WdatePicker.js"></script>



<!-- 正则表达式 -->
<script type="text/javascript">

//用户名不能纯数字  长度不低于六位到十六位
var CHKLOGINNAME="^(?![0-9]+$)[a-zA-Z0-9]{3,16}$";
//密码:数字+字母，结束之前不能全部都是数字和字母，6-16
var CHKPASSWORD="^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{5,16}$";
//出生日期     yyyy-MM-dd  月份1-12     日期1-31
var CHKDATE="^[0-9]{4}-0?[1-9]|1[0-2]-0?[1-9]|[1-2][0-9]|3[0-1]$";
//邮箱xxxxxx@xxx.com,可以包含_      企业邮箱qwe@huewei.com.cn
var CHKEMAIL="^[a-zA-Z0-9_]+@[a-z0-9]{2,5}(\\.[a-z]{2,3}){1,2}$";
//真实姓名
var CHKREALNAME="^[\u4e00-\u9fa5]{2,4}$";
//验证用户名

//改用jquery方法验证
//验证用户名
function chkloginname(){
		//获取用户名
		var loginname=$("#loginname").val();
		//定义匹配用户名的正则表达式
		var reg=new RegExp(CHKLOGINNAME);
		if(reg.test(loginname)){//正确
			if(chkExistLoginname(loginname)){
				return true;
			}else{
				return false;
			}
		}else{
			$("#resultName").html("用户名必须包含字母不能使纯数字，并且不能低于六位");
			$("#resultName").css("color","red");
			$("#loginname").val("");
			$("#loginname").focus();
			return false;
		}
	}
	
//检查用户名是否重复

	
//验证密码
function chkpassword(){
	//获取密码
	var password=$("#password").val();
	//定义用户匹配的正则表达式
	var reg=new RegExp(CHKPASSWORD);
	if(reg.test(password)){
		$("#resultPwd").html("✔");
		$("#resultPwd").css("color","green");
		return true;
	}else{
		$("#resultPwd").html("密码必须是包含数字和字母，并且不能低于六位");
		$("#resultPwd").css("color","red");
		$("#password").val("");
		$("#password").focus();
		return false;
	}
}
//验证两次密码是否一致
function chkRePwd(){
	//获取二次的密码
	var repwd=$("#repwd").val();
	//获取首次的密码
	var password=$("#password").val();
	
	if(repwd==password){
		$("#resultRepwd").html("✔");
		$("#resultRepwd").css("color","green");
		return true;
	}else{
		$("#resultRepwd").html("两次密码不一致呀 ，请您重新输入一次");
		$("#resultRepwd").css("color","red");
		//清空
		$("#repwd").val("");
		//聚焦
		$("#repwd").focus();
		return false;
	}
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
		$("#resultRealName").html("您输入的信息不符合人名格式的");
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
	//获取邮箱
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

//验证所有



//文档就绪事件
$(function(){
//下拉框change事件
	$("#dept1").change(		
	 function(){
		 //清空第二个下拉框
			$("#dept2").empty();
		//ajax异步提交
		$.post(
	    //地址
		"getdep.do",
		//待发送的参数类型 json类型数据传值
		{pid:this.value},
		function(date){
			
		  if(date!=null){
			$(date).each(function(){
				//添加数据到第二个下拉框56				
		      $("#dept2").append("<option value="+this.id+">"+this.dname+"</option>");
			});  			  
		  }		
		},
		"json"
		);
	});
	
	
	
	
});

			
function upload(){
	var args={
			//url绝对路径
	 url:"../upload/service.do",
	 //返回类型
	 dataType:"text",
	 //提交方式
	 type:"post",
	 success:function(result){
		 //设置图片的属性
		 $("#img").attr("src",result);
		 //将路径设置到隐藏域中
		 $("#pic").val(result);
	 }
	}
	$("#jvForm").ajaxSubmit(args);
}

</script>
</head>
<body>
<!-- 获得应用的绝对路径 -->
<input type="hidden" id="path" value="${pageContext.request.contextPath }"/>
 <div>
		<img src="../images/logo4.png"/>	
	</div>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="add.do" method="post" enctype="multipart/form-data">
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
						<input type="text" class="required" name="loginname" id="loginname" maxlength="100" onblur="chkloginname()"/>
						<span id="resultName"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						密码:</td><td width="80%" class="pn-fcontent">
						<input type="password" class="required" name="password" id="password" maxlength="100" onblur="chkpassword()"/>
						<span id="resultPwd"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						确认密码:</td><td width="80%" class="pn-fcontent">
						<input type="password" class="required" name="repwd" id="repwd" maxlength="100" onblur="chkRePwd()" />						
						<span id="resultRepwd"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						真实姓名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="realname" id="realname" maxlength="100" onblur="chkrealname()" />
						<span id="resultRealName"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="sex" value="男" checked="checked"/>男
						<input type="radio" name="sex" value="女"/>女
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						
						出生日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" name="birthday" id="birthday" maxlength="100" onclick="WdatePicker()" readonly="readonly"/>
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select name="dept1" id="dept1">
							<c:forEach items="${DLIST}" var="dep1">
								<option value="${dep1.id}" name="id">${dep1.dname}</option>
							</c:forEach>
					   </select>
						<select name="dept.id" id="dept2">
							<c:forEach items="${DLIST2}" var="dept2">
								<option value="${dept2.id}" name="id">${dept2.dname}</option>
				 			</c:forEach>
					   </select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						邮箱:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="email" id="email" maxlength="80" onblur="chkemail()" />
						<span id="resultEmail"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						头像:</td><td width="80%" class="pn-fcontent">
						<input type="file"  name="file" onchange="upload()" />
						<img id="img" width="150px" height="150px"/>
						<input type="hidden" id="pic" name="pic" />
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