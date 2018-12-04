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
<title>Artical-list</title>
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
	   return true;
        }else{
	   return false;
    }
   }
   
   //创建就绪时间
   $(function(){
	  
	   //删除点击时间
	   $("#delete").click(function(){
		 var ids=new Array();
		  var msg="您真的确定要删除吗？？？真的吗！！！！";
		  if (confirm(msg)==true) {
			  //获取多选框属性值
			$("input[type=checkbox]:checked").each(function(){
				//给数组赋值
				ids.push($(this).val());			
			})
			//如果数组里有数据 长度不为0
			if (ids.length!=0) {
				location.href="articalsdelete.do?idss="+ids;
			} else {
				 alert("请您至少要选择一条数据呀");
					//重新加载当前页面
				 location.reload();
			}
			  
			  
		} else {
             return false;
		}
		   
		   
	   });
	   
	   
	   
	   
	   
   })
   
   
   
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置:文章管理 - 列表</div>
	<form class="ropt">
	
	<!-- 只有走部门信息才能把栏目信息传进来 -->
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toadd.do'"/>
	</form>
	<div class="clear"></div>
</div>
<form action="list.do" method="post" style="padding-top:5px;">
作者:<input type="text"  value="${QUERY.author }" name="author"/>
	<select  name="isremod">
	  <c:if test="${QUERY.isremod==0 }">	  
	        <option value="0" selected="selected">请选择</option>		
			<option value="1"> 推荐</option>		
			<option value="-1" >不推荐</option>
	  </c:if>
	  <c:if test="${QUERY.isremod==1 }">	  
	        <option value="0" >请选择</option>		
			<option value="1" selected="selected"> 推荐</option>		
			<option value="-1" >不推荐</option>
	  </c:if>
	  <c:if test="${QUERY.isremod==-1 }">	  
	        <option value="0" >请选择</option>		
			<option value="1"> 推荐</option>		
			<option value="-1" selected="selected">不推荐</option>
	  </c:if>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<div class="body-box">
<input type="hidden" name="pageNo" value=""/>
<form method="post" id="tableForm" action="deletes.do">
<input type="hidden" value="" name="pageNo"/>
<input type="hidden" value="" name="queryName"/>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"></th>
			<th>文章编号</th>
			<th>文章名</th>
			<th width="30%">文章内容</th>
			<th>作者</th>
			<th>著作日期</th>			
			<th>所属栏目</th>
			<th>是否推荐</th>
			<th>是否热点</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${LIST }" var="artical">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="${artical.id }"/></td>
				<td align="center">${artical.id }</td>
				<td align="center">${artical.title }</td>
				<td align="center">${artical.content }</td>
				<td align="center">${artical.author }</td>
				<td align="center">${artical.ctimeTxt }</td>
				<td align="center">${artical.channel.cname}</td>
				<td align="center">${artical.isremodTxt }</td>
				<td align="center">${artical.ishotTxt }</td>
				
				<td align="center">
					<a href="articalget.do?id=${artical.id }" class="pn-opt" >修改</a>
					<a href="delete.do?id=${artical.id }" class="pn-opt" onclick="javascript:return del()">删除</a>
				</td>
		   </tr>
		</c:forEach>
	</tbody>
</table>
<div class="page pb15" style="float:left;font-size: 20px;margin-top: 10px">
	<input class="del-button" type="submit" value="删除"/> 
	
</div>
</form>

</div>
<!-- 页码 -->
<div class="page pb15" style="float:right;font-size: 20px;margin-top: -40px">
	<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		<!-- 作用域 -->
		
		<a href="list.do?page=1&author=${QUERY.author}&isremod=${QUERY.isremod}">首页</a>		
		<a href="list.do?page=${(PAGE-1)<=0?1:(PAGE-1) }&author=${QUERY.author }&isremod=${QUERY.isremod}">上一页</a>
		<a href="list.do?page=${PAGE+1>PAGECOUNT?PAGECOUNT:PAGE+1 }&author=${QUERY.author }&isremod=${QUERY.isremod}">下一页</a>
		<a href="list.do?page=${PAGECOUNT }&author=${QUERY.author }&isremod=${QUERY.isremod}">尾页</a>
	
		<div>当前第${PAGE }/共${PAGECOUNT }页</div>
	</span>
</div>
</body>
</html>