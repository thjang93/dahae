<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${folder}script.js"></script>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}style.css" type="text/css">
</head>
<body onload = "loginfocus()">
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size:30px;">로그인</b> <br>
			</div>
			<label style = "font-size:20px;"> &nbsp; </label>
			<form method = "post" action = "loginPro.do"
				name = "loginform" onsubmit = "return logincheck2()">
				<table style = "margin-left: auto; margin-right: auto;">
					<tr>
						<th style = "text-align: center; font-size:15px;"> 아 이 디&nbsp; </th> 
						<td>
							<input class = "form-control" type = "text" name = "id" 
									placeholder = "아이디를 입력해주세요.">
						</td>
					</tr>
					<tr>
						<td> <p style="font-size: 1.5px;"></p> </td>
					</tr>
					<tr>
						<th style = "text-align: center; font-size:15px;"> 비밀번호&nbsp; </th>
						<td>
							<input class = "form-control" type = "password" name = "passwd"
								placeholder = "비밀번호를 입력해주세요.">
						</td>
					</tr>
					<tr>
						<th colspan = 2 style = "text-align: center;"> <br>
							<input class = "btn btn-info" type = "submit" value = "${btn_login}">
							<input class = "btn btn-danger" type = "button" value = "${btn_reg}"
								onclick = "location='inputForm.do'">
						</th>
					</tr>
				</table>
			</form>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>