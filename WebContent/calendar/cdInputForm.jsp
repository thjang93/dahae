<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<link href="${project}calendar/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
<script src = "${project}calendar/script.js"></script>

<body onload="inputfocus()">
<form method="post" action="cdInputPro.do"
	name="inputform" onsubmit="return inputcheck()">
	
	<input type="hidden" name="year" value="${year}">
	<input type="hidden" name="month" value="${month}">
	<input type="hidden" name="day" value="${day}">
	
	<h4 style = "font-family: 'Jeju Gothic', serif"> 
		♥${year}년 ${month}월 ${day}일의 일정 추가하기♥
	</h4>
	<h3 style = "font-family: 'Jeju Gothic', serif"> 
		${str_meal}
	</h3>
	<h4 style = "font-family: 'Jeju Gothic', serif"> 
		${msg_meal}
	</h4>
	<table class="table table-stripped" style = "font-family: 'Nanum Gothic', serif">
		<tr>
			<th>
				${str_br}<br>
				<input type="text" name="breakfast" class="form-control"
					placeholder="예)고구마, 사과, 요플레로 간단한 한끼식사">				
			</th>
		</tr>
		<tr>
			<th>
				${str_lu}<br>
				<input type="text" name="lunch" class="form-control"
					placeholder="예)나물로 만든 건강한 비빔밥">
			</th>
		</tr>
		<tr>
			<th>
				${str_di}<br>
				<input type="text" name="dinner" class="form-control"
					placeholder="예)닭가슴살스테이크로 건강하고 맛있게">				
			</th>
		</tr>		
	</table>
	
	<h3 style = "font-family: 'Jeju Gothic', serif"> 
		${str_ex}
	</h3>
	<h4 style = "font-family: 'Jeju Gothic', serif"> 
		${msg_ex}<br>
		${msg_ex2}
	</h4>
	<table class="table table-stripped" style = "font-family: 'Nanum Gothic', serif">
		<tr>
			<th>
				${str_ex1}
				<input type="text" name="ex1" class="form-control"
					placeholder="예)아침,저녁으로  플랭크 3세트,스쿼트 3세트">
			</th>
		</tr>
		<tr>
			<th>
				${str_ex2}
				<input type="text" name="ex2" class="form-control"
					placeholder="예)점심시간에 짬내서 런닝머신 20분">
			</th>
		</tr>
		<tr>
			<th>
				${str_ex3}
				<input type="text" name="ex3" class="form-control"
					placeholder="예)7:00 수영수업">	
			</th>
		</tr>
	</table>
	<table style="margin : 0 auto;">
		<tr>
			<th>
				<input class="btn btn-warning" type="submit" value="${btn_input}">
				<input class="btn btn-warning" type="button" value="${btn_input_cancel}"
					onclick="inputcancel()">
			</th>
		</tr>	
	</table>
</form>
</body>