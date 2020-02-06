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
	<body onload="modiformfocus()">
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<div align="center">
					<b style="font-size: 30px;">${page_modify}</b> <br>
					<b style="font-size:15px;">${msg_passwd}</b>
				</div>
				<label style = "font-size:20px;"> &nbsp; </label>
				<div class="container">
						<form method="post" action="modifyView.do" name="mofyform">
							<table style= "margin-left: auto; margin-right:auto;">
								<tr>
									<td colspan = "2" style = "font-size: 15px;"> 
										<input class="form-control" type="password" name="passwd" maxlength="15"
											placeholder = "비밀번호를 입력해주세요">
									</td>
								</tr>
								<tr>
									<th colspan="2" style = "text-align:center; line-height:50px;">
										<input class="btn btn-info btn-sm" type="submit" value="${btn_mod}">
										<input class="btn btn-default btn-sm" type="button" value="${btn_mod_cancel}"	
											onclick="location='myPage.do'">
									</th>
								</tr>
							</table>		
						</form>
				</div>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>













