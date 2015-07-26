<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.table{
			border:1px solid black;
			border-collapse:collapse;
		}
		.table td{
			padding:5px;
			border:1px solid black;
		}
	</style>
  </head>
  <body>
   	<p>以下是${user.name}的所有联系人:</p>
   	<a href="xxx">增加</a>
   	<table class="table">
   		<tr>
   			<td>
   				姓名
   			</td>
   			<td>
   				性别
   			</td>
   			<td>
   				电话
   			</td>
   			<td>
   				地址
   			</td>
   			<td>
   				操作
   			</td>
   		</tr>
   		<c:forEach items="${cs}" var="c">
   			<tr>
   				<td>
   					${c.name}
   				</td>
   				<td>
   					${c.sex==0?'女':'男'}
   				</td>
   				<td>
   					${c.tel}
   				</td>
   				<td>
   					${c.addr}
   				</td>
   				<td>
   					<a href="<c:url value='/contacts?cmd=del&id=${c.id}'/>">删除</a>
   					&nbsp;
   					<a href="<c:url value='/contacts?cmd=update&id=${c.id}'/>">修改</a>
   				</td>
   			</tr>
   		</c:forEach>
   	</table>
  </body>
</html>
