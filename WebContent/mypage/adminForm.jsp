<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel="stylesheet" href="${project}css/bootstrap.css" type="text/css">
	<link rel="stylesheet" href="${folder}style.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div class = "container">
				<div class = "jumbotron" style = "background-color: transparent;">
					<h1 class = "text-center" style = "font-size: 86px; font-family: 'Nanum Pen Script', cursive;"> 
						" 다이어트를 해석하다 "  <br>
					</h1>
					<p class = "text-center" style =  "color:#B2CCFF;  font-size: 40px;  font-family: 'Nanum Pen Script', cursive;"> 
						DAHAE
					</p>
				</div>
			</div>
			<div class = "container"> 
				<div  class = "text-center" style ="text-align:center; font-family: 'Hanna', serif; font-size:50px;"> 
					<h2>
						<a href="memberList.do">${admeun1}</a>&nbsp;&nbsp;&nbsp;   
						<a href="goodsListM.do">${admeun2}</a>&nbsp;&nbsp;&nbsp;
						<a href="orderList.do">${admeun3}</a>&nbsp;&nbsp;&nbsp;
						<a href="noticeList.do">${admeun4}</a>&nbsp;&nbsp;&nbsp;  
					</h2>
				</div>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>