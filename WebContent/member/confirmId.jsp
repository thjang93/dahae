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
<body onload="confirmfocus()">
	<c:if test="${result == 0}">
		<table style = "margin-left: auto; margin-right: auto;">
			<tr>
				<th> 
					<b style="font-size:23px;"> <br><br> " ${id} "</b>
					<a style="fontisize:18px;"> ${msg_id_o} <br> <br> </a>
				</th>
			</tr>
			<tr>
				<td align="center">
					<input class="btn btn-info" type="button" value="${btn_ok}" onclick="setid('${id}')">
				</td>
			</tr>		
		</table>		
	</c:if>
	<c:if test="${result == 1}">
		<form method="post" action="confirmId.do"
			name="confirmform" onsubmit="return confirmcheck()">
			<table class="form-inline" style = "margin-left: auto; margin-right: auto;">
				<tr>
					<th colspan="2" style="text-align:center;"> 
						<b style="font-size:23px;"> <br> " ${id} " </b>
						<b style="fontisize:18px;"> ${msg_id_x} </b>
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align:center;">
						<p style="fontisize:12px;"> ${msg_id2} </p>
						<input class="form-control" type="text" name="id" maxlength="15""> <br>
					</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align:center;">
					 	<input class="btn btn-info" type="submit" value="${btn_ok}">
						<input class="btn" type="button" value="${btn_ok_cancel}"
						 	onclick="self.close()">						 		
					</th>
				</tr>
			</table>				
		</form>	
	</c:if>
</body>
</html>	