<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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

<link rel="stylesheet" href="../res/css/style.css" />


<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章修改</title>
<script type="text/javascript">
//文章标题 必须是二到八个汉字或者3-30个字母组成
var CHKTITLE="^[\u4e00-\u9fa5]{2,8}$";
function chktitle(){
	//获取标题
	var title=$("#title").val();
	//匹配定义用户名的正则表达式
	var reg=new RegExp(CHKTITLE);
	//获取是否成功的元素对象
	
	if (reg.test(title)) {
		$("#resultTitle").html("✔");
		$("#resultTitle").css("color","green");		
		return true;
	}else{
		$("#resultTitle").html("文章标题必须是2-8个汉字组成,注意哦");
		$("#resultTitle").css("color","red");		
		//清空文本框
		$("#title").val("");
		//重新聚焦
		$("#title").focus();
		return false;
	}
}
//文章内容  汉字 加符号
var CHKCONTENT="^[a-zA-Z0-9\u4e00-\u9fa5\u3002|\uff1f|\uff01|\uff0c|\u3001|\uff1b|\uff1a|\u201c|\u201d|\u2018|\u2019|\uff08|\uff09|\u300a|\u300b|\u3008|\u3009|\u3010|\u3011|\u300e|\u300f|\u300c|\u300d|\ufe43|\ufe44|\u3014|\u3015|\u2026|\u2014|\uff5e|\ufe4f|\uffe5]{15,}$";

function chkcontent(){	
	var content=$("#content").val();
	var reg=new RegExp(CHKCONTENT);
	if (reg.test(content)) {
		$("#resultContent").html("✔");
		$("#resultContent").css("color","green");		
		return true;
	} else {
		$("#resultContent").html("广告内容必须多余15个汉字或字母，标点须是中文下标点o");		
		$("#resultContent").css("color","red");	
		//清空文本框
		$("#content").val("");
		//重新聚焦
		$("#content").focus();
		return false;
	}
}
//作者
var CHKAUTHOR="^[\u4e00-\u9fa5]{2,4}$";
function chkauthor(){
	
	var author=$("#author").val();
	var reg=new RegExp(CHKAUTHOR);
	
	if (reg.test(author)) {
		$("#resultAuthor").html("✔");
		$("#resultAuthor").css("color","green");
		
		return true;
	} else {
		$("#resultAuthor").html("公告人姓名须由2到4个汉字组成");
		$("#resultAuthor").css("color","red");		
		//清空文本框
		$("#author").val("");
		//重新聚焦
		$("#author").focus();
		return false;
	}
}

function chkAll(){
	if ((chktitle()&&chkcontent()&&chkauthor())==true) {
		return true;
	} else {
          alert("填入信息有误或请把信息填写完整");
          return false;
	}
	
	
}
//文档就绪事件
$(function(){
//下拉框change时间
	$("#cha1").change(		
	 function(){
		 //清空第二个下拉框
			$("#cha2").empty();
		//ajax异步提交
		$.post(
	    //地址
		"getcha.do",
		//待发送的参数类型 json类型数据传值
		{pid:this.value},
		function(date){
			
		  if(date!=null){
			$(date).each(function(){
				//添加数据到第二个下拉框56				
		      $("#cha2").append("<option value="+this.id+">"+this.cname+"</option>");
			});  			  
		  }		
		},
		"json"
		);
	});
	
	
});
	

</script>



<style type="">
.h2_ch a:hover,.h2_ch a.here {
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
	background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0%
		0% transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>

</head>
<body>
<div>
		<img src="images/logo4.png"/>	
	</div>
	<div class="box-positon">
		<div class="rpos">当前位置: 文章管理 - 修改</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='list.do';"value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>

	<div class="body-box" style="float: right">
		<form id="jvForm" action="update.do?id=${ARTICAL.id }" method="post" onsubmit="return chkAll()">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${msga }</span>
					</td>
				</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 标题:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="title" id="title" maxlength="100" size="50" value="${ARTICAL.title }" />
							<span id="resultTitle"></span>
							</td>
							
					</tr>
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 内容:</td>
						<td width="80%" class="pn-fcontent"><textarea rows="15"
								cols="70"  name="content" id="content" >${ARTICAL.content }</textarea>
								<span id="resultContent"></span>		
								</td>
								
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">作者:</td>
						<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="author" id="author" maxlength="100" size="50" value="${ARTICAL.author }" />
							<span id="resultAuthor"></span>
							</td>
					</tr>
					
					<tr>
					<!-- 栏目 -->
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属栏目:</td>
						<td width="80%" class="pn-fcontent">
						<select name="channel1" id="cha1">
							<c:forEach items="${LIST1}" var="c1">
								
								<c:if test="${ARTICAL.channel.pid==c1.id }">
									<option value="${c1.id}" selected="selected">${c1.cname}</option>
								</c:if>
								
								<c:if test="${ARTICAL.channel.pid!=c1.id}">
									<option value="${c1.id}">${c1.cname}</option>
								</c:if>	
							</c:forEach>
						
					</select>
						<select name="channel.id" id="cha2">
							<c:forEach items="${LIST2}" var="c2">
								
								<c:if test="${ARTICAL.channel.id==c2.id }">
									<option value="${c2.id}" selected="selected">${c2.cname}</option>
								</c:if>
								
								<c:if test="${ARTICAL.channel.id!=c2.id }">
									<option value="${c2.id}">${c2.cname}</option>
								</c:if>	
							</c:forEach>
						
					</select>
						</td>
					</tr>

					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
						<td width="80%" class="pn-fcontent">
						<c:if test="${ARTICAL.isremod==1 }">
						    <input type="radio"	name="isremod" value="1" checked="checked"/>推荐 
							<input type="radio" name="isremod" value="-1" />不推荐						
						</c:if>
						 <c:if test="${ARTICAL.isremod==-1 }">
						    <input type="radio"	name="isremod" value="1" />推荐 
							<input type="radio" name="isremod" value="-1" checked="checked"/>不推荐
						
						</c:if>   
					   </td>
						
						
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否热点:</td>						
						<td width="80%" class="pn-fcontent">
						<c:if test="${ARTICAL.ishot==1 }">
						   <input type="radio"name="ishot" value="1" checked="checked"/>热点 
							<input type="radio" name="ishot"value="-1" />非热卖
						</c:if>
						<c:if test="${ARTICAL.ishot==-1 }">
						   <input type="radio"name="ishot" value="1" />热点 
							<input type="radio" name="ishot"value="-1" checked="checked"/>非热卖
						</c:if>
							
						</td>
					</tr>

				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td><textarea rows="10" cols="10" id="productdesc"
								name="description"></textarea></td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td><textarea rows="15" cols="136" id="productList"
								name="packageList"></textarea></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" onclick="confirm('确认提交吗')"/> &nbsp; <input type="reset"
							class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>