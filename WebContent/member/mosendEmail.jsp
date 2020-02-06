<%@page import="member.LogonDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<script src="${folder}script.js"></script>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
</head>
<body>
	<c:if test="${result ==0}">
		<h2 align="center"> <br> 이메일 전송실패</h2>
		<div align="center">
			<input class="btn btn-info" type="button" value="${btn_close}"
				onclick="self.close()">
		</div>
	</c:if>
	<c:if test="${result !=0}">
		<form name="mosendemailform">
			<input type="hidden" name="authnum" value="${authnum}">
			<table style = "margin-left: auto; margin-right: auto;">
				<tr>
					<th style="text-align:center;"> <br> 이메일 전송완료 </th>
				</tr>
				<tr>
					<td style="text-align:center;">
						<input class="btn btn-info" type="button" 
							value="${btn_ok}" onclick="setmoAuthNum()">
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>

	


