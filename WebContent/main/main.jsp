<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset = "UTF-8">
	<script src = "${folder}script.js"></script>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<meta name = "viewport" content = "width=device-width, initial-scale=1">
	<link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
</head>
<body>
	<div class="container"">
		<div id="myCarousel" class="carousel slide" data-ride="carousel" style = "margin-top: 25px;">	
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarouse1" data-slide-to="1"></li>
				<li data-target="#myCarouse1" data-slide-to="2"></li>
			</ol>

			<div class="carousel-inner">
				<div class="item active">
					<img src="${project}images/main1.jpg" alt="main1" style="width:100%;">
				</div>
				<div class="item">
					<img src="${project}images/main2.jpg" alt="main2" style="width:100%;">
				</div>
				<div class="item">
					<img src="${project}images/main3.jpg" alt="main3" style="width:100%;">
				</div>
			</div>		
			<!-- 수동 -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<div class = "container">
		<div class = "jumbotron" style = "background-color: transparent;">
			<h1 class = "text-center" style = "font-size: 86px; font-family: 'Nanum Pen Script', cursive;"> 
				" 다이어트를 해석하다 "  <br>
			</h1>
			<p class = "text-center" style = "font-family: 'Jeju Gothic', serif"> 
				다해에 오신것을 환영합니다.
			</p>
		</div>
	</div>
</body>
</html>