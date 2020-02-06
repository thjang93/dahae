<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
		<script src = "${folder}script.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<div align="center">
					<b style="font-size: 30px;">${page_modify}</b>
				</div>
				<div class="container">
				<div class = "jumbotron" style = "background-color: transparent;">
				<label style="font-size: 20px;"> &nbsp; </label>
				<form method="post" action="goodsModifyView.do">					
					<input type="hidden" name="num" value="${num}">
					<input type="hidden" name="pageNum" value="${pageNum}">			
					<table style = "margin-left: auto; margin-right: auto;">
						<tr>
							<td align="center" style="height:100px; width:300px; font-size:16px;">
								${msg_modify_check}
							</td>
						</tr>
						<tr>
							<th colspan="2" style="text-align:center;">
								<input class="btn btn-info btn-sm" type="submit" value="${btn_mod}">
								<input class="btn btn-default btn-sm" type="button" value="${btn_mod_cancel}"
									onclick="location='goodsListM.do?pageNum=${pageNum}'">
							</th>
						</tr>
					</table>
				</form>
				</div>
				</div>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>