<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px rgb(78,78,78);
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body style="background: rgb(254,238,189);">
<h1>订单列表</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	
	<!--  循环订单的信息 -->
	<c:forEach var="order" items="${ oList }">
		<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
			<td colspan="6">
				订单：${ order.oid }　成交时间：${ order.ordertime }　金额：<font color="red"><b>${ order.total }元</b></font>	
				<!-- 未付款 -->
				<c:if test="${ order.state eq 1 }">
					未付款
				</c:if>
				<c:if test="${ order.state eq 2 }">
					<a href="${ pageContext.request.contextPath }/order?method=updateByStateAdmin&oid=${order.oid}">发货</a>
				</c:if>
				<c:if test="${ order.state eq 3 }">
					未确认收货
				</c:if>
				<c:if test="${ order.state eq 4 }">
					已收货（完成）
				</c:if>
			</td>
		</tr>
		
		<c:forEach var="orderItem" items="${ order.orderItems }">
			<tr bordercolor="rgb(78,78,78)" align="center">
				<td width="15%">
					<div><img src="${ pageContext.request.contextPath }/${ orderItem.book.image }" height="75"/></div>
				</td>
				<td>书名：${ orderItem.book.bname }</td>
				<td>单价：${ orderItem.book.price }元</td>
				<td>作者：${ orderItem.book.author }</td>
				<td>数量：${ orderItem.count }</td>
				<td>小计：${ orderItem.subtotal }元</td>
			</tr>
		</c:forEach>
  	
  	</c:forEach>
 

</table>
  </body>
</html>
