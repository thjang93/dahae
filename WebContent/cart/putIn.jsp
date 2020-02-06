<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
<script src = "${folder}script.js"></script>

<c:if test="${result == 0}">
		<script type="text/javascript">
			<!--
				erroralert2 ( putinerror );
			//-->
		</script>
</c:if>
<c:if test="${result != 0}">
	<table style = "margin-right: auto; margin-left: auto;">
		<tr>
			<th style = "text-align: center;"><br>${msg_putIn}</th>
		</tr>
		<tr>
			<th style = "text-align: center;">
				<br>
				<img src="${folder}images/cart.jpg" height = 150px width = auto>
			</th>
		</tr>
		<tr>
			<th style = "text-align: center;">
				<br>
				<input class = "btn btn-info btn-sm" type="button" value="${btn_go_cart}" onclick="go_cart()">
				<input class = "btn btn-default btn-sm" type="button" value="${btn_con}" onclick="con1()">
			</th>
		</tr>
	</table>
</c:if>
