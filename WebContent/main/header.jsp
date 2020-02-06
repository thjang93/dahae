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
	<nav class = "navbar navbar-default">
		<ul class = "nav navbar-nav navbar-right">
			<!-- 로그인이 안된 상태일때 -->
			<c:if test = "${sessionScope.memId == null}">
				<li><a href="loginForm.do" style = "color: white;">로그인<span class = "sr-only"></span></a></li>
				<li><a href="inputForm.do" style = "color: white;">회원가입<span class = "sr-only"></span></a></li>
				<li><a href="#" style = "color: white;" onclick = "return logincheck(${sessionScope.memId})">마이페이지&nbsp;&nbsp;&nbsp;<span class = "sr-only"></span></a></li>
			</c:if>
			<c:if test = "${sessionScope.memId != null}">
				<!-- 관리자일 때 -->
				<c:if test = "${sessionScope.memId == 'admin'}">
					<li style = "color: white; margin-top: 5%">${str_admin}님 환영합니다 &nbsp;<span class = "sr-only"></span></li>
					<li><a href="logout.do" style = "color: white;">로그아웃<span class = "sr-only"></span></a></li>
					<li><a href="adminPage.do" style = "color: white;">마이페이지&nbsp;&nbsp;&nbsp;<span class = "sr-only"></span></a></li>
				</c:if>
				<!-- 일반회원일 때 -->
				<c:if test = "${sessionScope.memId != 'admin'}">
					<li style = "color: white; margin-top: 5%">${sessionScope.memId}님 환영합니다 &nbsp;<span class = "sr-only"></span></li>
					<li><a href="logout.do" style = "color: white;">로그아웃<span class = "sr-only"></span></a></li>
					<li><a href="myPage.do" style = "color: white;">마이페이지&nbsp;&nbsp;&nbsp;<span class = "sr-only"></span></a></li>
				</c:if>
			</c:if>
		</ul>
	</nav>	
	<nav class = "navbar navbar-default" style = "background-color: white;">
		<a href = "index.do">
			<img src = "${project}images/logo.jpg" width = "80" height = "50" style = "margin-left: 48%;">
		</a>
		<form method = "post" action = "goodsSearchList.do"
			name = "mainsearchform" class = "navbar-form navbar-right">
		<div class = "form-group">
			<input class = "form-control" type = "text" placeholder = "상품명을 입력하세요." name = "keyword">
		</div>
		<input class = "btn btn-info" type = "submit" value = "검색"
			onclick = "return hsearchcheck()"><span class = "sr-only"></span>&nbsp;&nbsp;&nbsp;
		</form>
	</nav>
	<nav class = "navbar navbar-default" style = "background-color: white; font-size: 18px">
		<ul class = "nav navbar-nav navbar-center">
			<li><a href="goodsViewCat.do?category=new">${menu1}</a></li>
			<li><a href="goodsViewCat.do?category=best">${menu2}</a></li>
			<li><a href="goodsViewCat.do?category=${menu3}">${menu3}</a></li>
			<li><a href="goodsViewCat.do?category=${menu4}">${menu4}</a></li>
			<li><a href="goodsViewCat.do?category=${menu5}">${menu5}</a></li>
			<li><a href="goodsViewCat.do?category=${menu6}">${menu6}</a></li>
		</ul>
	</nav>		
</body>
</html>