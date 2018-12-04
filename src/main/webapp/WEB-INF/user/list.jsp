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

<!-- 引入jqyery函数库 -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>User-list</title>

<script type="text/javascript">
   function del(){
	   var msg="您真的确定要删除吗？？？真的吗！！！！";
	   if(confirm(msg)==true){
	   return true;
        }else{
	   return false;
    }
   }
   function update(){
	   var msg="您真的确定要修改吗？？？真的吗！！！！";
	   if(confirm(msg)==true){
	   return true;1
        }else{
	   return false;
    }
   }
   
 //创建文档就绪事件
 
 $(function(){
	//获取被选中的下拉菜单选项的value值
		//$("#delect").click(function(){
			//alert($("select option:selected").val());
			
			//:selected只针对下拉框
			//alert($(":selected").val());
		//});
		//批量删除
		$("#delete").click(function(){
			//获取被选中的多选框的value值
			//checked:表示选中的单选框和多选框
			//创建数组进行存储
			var ids=new Array();
			var msg="您真的确定要删除吗？？？真的吗！！！！";
			   if(confirm(msg)==true){
				   $("input[type=checkbox]:checked").each(function(){
					   //给数组赋值
						ids.push($(this).val());
					   alert($(this).val());
					});
					//链接
					if(ids.length==0){
						alert("请您至少要选择一条数据呀");
						
						//重新加载当前页面
						location.reload();
						continue;
					}else{
						//按照指定的url信息载入地址  携带数组参数     参数名   参数值
						location.href="user/deletes.do?ids="+ids;	
					}
								   				   
		        }else{
			   return false;
		    }		
			
			
		});
	 
 });
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 列表</div>
	<form class="ropt"> 
	<!-- 只有走部门信息才能把部门信息传进来 -->
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toadd.do'"/>
	</form>
	<div class="clear"></div>
</div>

<form action="list.do" method="post" style="padding-top:5px;">
登录名: <input type="text"  value="${QUERY.loginname }" name="loginname"/>
	<select  name="isenabled">
	<c:if test="${QUERY.isenabled==0 }">
	        <option value="0" selected="selected">请选择</option>		
			<option value="1"> 有效</option>		
			<option value="-1" >无效</option>
	</c:if>
	<c:if test="${QUERY.isenabled==1 }">
	      <option value="0" >请选择</option>		
			<option value="1" selected="selected"> 有效</option>		
			<option value="-1" >无效</option>
	</c:if>
	<c:if test="${QUERY.isenabled==-1 }">
	      <option value="0" >请选择</option>		
			<option value="1"> 有效</option>		
			<option value="-1" selected="selected">无效</option>
	</c:if>
	</select>	
	<input type="submit" class="query" value="查询"/>
</form>

<div class="body-box">


<form method="post"  action="deletes.do">
      <tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired"></span>
				<span class="pn-frequired">${MSG }</span>
			</td>
		</tr>
<input type="hidden" value="" name="queryName"/>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"></th>
			<th>用户编号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>性别</th>
			<th>出生年月</th>			
			<th>邮箱</th>
			<th>部门名称</th>
			<th>头像</th>
			<th>创建人</th>			
			<th>创建时间</th>
			<th>修改人</th>			
			<th>修改时间</th>
			
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	
		<c:forEach items="${LIST }" var="user">		
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${user.id }"/></td>
			<td align="center">${user.id }</td>
			<td align="center">${user.loginname }</td>
			<td align="center">${user.realname }</td>
			<td align="center">${user.sex }</td>
			<td align="center">${user.birthdayTxt }</td>			
			<td align="center">${user.email }</td>
			<td align="center">${user.dept.dname }</td>
			<td align="center"><img src="../upload/${user.pic }" width="30px" height="45px"/></td>
			<td align="center">${user.creator}</td>
			<td align="center">${user.creatimeTxt}</td>
			<td align="center">${user.uodator}</td>
			<td align="center">${user.updatimeTxt}</td>
			
			<td align="center">${user.isenabledTxt }</td>
			<td align="center">
			<a href="userget.do?id=${user.id }" class="pn-opt" onclick="javascript:return update()">修改</a>
			<a href="userdelete.do?id=${user.id }" class="pn-opt" onclick="javascript:return del()">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
   </table>

	<div style="float:left;font-size: 20px;margin-top: 10px">
	<!-- 批量删除按钮 通过ID触发上面的点击事件 -->
	
	<input class="del-button"  type="submit" value="删除"/> 
   </div>
  </form>

</div>
<!-- 页码 -->
<div class="page pb15" style="float:right;font-size: 20px;margin-top: 10px">
	<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		<!-- 作用域 -->
		
		<a href="list.do?page=1&loginname=${QUERY.loginname }&isenabled=${QUERY.isenabled}">首页</a>
		
		<a href="list.do?page=${(PAGE-1)<=0?1:(PAGE-1) }&loginname=${QUERY.loginname }&isenabled=${QUERY.isenabled}">上一页</a>

		<a href="list.do?page=${PAGE+1>PAGECOUNT?PAGECOUNT:PAGE+1 }&loginname=${QUERY.loginname }&isenabled=${QUERY.isenabled}">下一页</a>
		<a href="list.do?page=${PAGECOUNT }&${QUERY.loginname }&${QUERY.isenabled}">尾页</a>
	
		<div>当前第${PAGE }/共${PAGECOUNT }页</div>
	</span>
</div>
  




</div>
</body>
</html>