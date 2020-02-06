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
			<c:import url = "header.jsp"/>
		</div>
		<div id="main">
			<c:import url = "main.jsp"/>
		</div>
		<div id="footer">
			<c:import url = "footer.jsp"/>
		</div>
	</div>
</body>
</html>