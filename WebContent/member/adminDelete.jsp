<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="${folder}script.js"></script>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
	</head>
	<body>
		<div align="center">
			<b style="font-size: 20px;"> <br> ${msg_id} <br> </b>
			<label style = "font-size:5px;"> &nbsp; </label>
		</div>
		<div>
			<form method="post" action="adminDeletePro.do"
				name="admindelform" onsubmit="return selete()">	
				<table style = "margin-left: auto; margin-right: auto;">
					<tr>
						<td>
							<input class="form-control" type="text" name="id">
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<input class="btn btn-info" type="submit" value="${btn_passwd_delete}">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
