<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel="stylesheet" href="${project}css/bootstrap.css" type="text/css">
		<link rel="stylesheet" href="${folder}style.css" type="text/css">
		<script src="${folder}script.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<c:if test = "${param.pay == 0}">
					<h1 align = "center"> 
						${msg_order_complete0} 
					</h1>
				</c:if>
				
				<c:if test = "${param.pay == 1}">
					<h1 align = "center"> 
						${msg_order_complete1}
					</h1>
				</c:if>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>