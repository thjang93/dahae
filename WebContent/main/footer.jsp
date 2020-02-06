<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<html>
<head>
	<script src="${folder}script.js"></script>
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
</head>
<body>
	<div class = "container" style = "background-color: #000000; color: #ffffff; bottom:0; width:100%;">
		<div class = "row">
			<div class = "col-sm-2" style = "text-align: center;">
				<h5>Copyright &copy; 2017 몸뚱이</h5>
			</div>
			<div class = "col-sm-3" style = "text-align: center; margin-top: 8px;">
				<a href = "noticeList.do" style = "color: white"> ${btn_notice} </a> &nbsp;&nbsp;&nbsp;
				<a href = "reviewList.do" style = "color: white"> ${btn_review} </a> &nbsp;&nbsp;&nbsp;
				<a href = "qaList.do" style = "color: white"> ${btn_qa} </a>
			</div>
		</div>
	</div>
</body>
</html>