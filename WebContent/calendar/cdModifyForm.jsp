<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<link href="${project}calendar/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
<script src = "${project}calendar/script.js"></script>

<body onload="inputfocus()">
<form method="post" action="cdModifyPro.do"
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
				<c:if test="${cdDto.breakfast == null}">
					<input type="text" name="breakfast" class="form-control">
				</c:if>	
				<c:if test="${cdDto.breakfast != null}">
					<input type="text" name="breakfast" class="form-control"
						value = "${cdDto.breakfast}">
				</c:if>	
			</th>
		</tr>
		<tr>
			<th>
				${str_lu}<br>
				<c:if test="${cdDto.lunch == null}">
					<input type="text" name="lunch" class="form-control">
				</c:if>
				<c:if test="${cdDto.lunch != null}">
					<input type="text" name="lunch" class="form-control"
						value = "${cdDto.lunch}">
				</c:if>
			</th>
		</tr>
		<tr>
			<th>
				${str_di}<br>
				<c:if test="${cdDto.dinner == null}">
					<input type="text" name="dinner" class="form-control">
				</c:if>
				<c:if test="${cdDto.dinner != null}">
					<input type="text" name="dinner" class="form-control"
						value = "${cdDto.dinner}">
				</c:if>				
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
				<c:if test="${cdDto.ex1 == null}">
					<input type="text" name="ex1" class="form-control">
				</c:if>
				<c:if test="${cdDto.ex1 != null}">
					<input type="text" name="ex1" class="form-control"
						value = "${cdDto.ex1}">
				</c:if>		
				
			</th>
		</tr>
		<tr>
			<th>
				${str_ex2}
				<c:if test="${cdDto.ex2 == null}">
					<input type="text" name="ex2" class="form-control">
				</c:if>
				<c:if test="${cdDto.ex2 != null}">
					<input type="text" name="ex2" class="form-control"
						value = "${cdDto.ex2}">
				</c:if>	
				
			</th>
		</tr>
		<tr>
			<th>
				${str_ex3}
				<c:if test="${cdDto.ex3 == null}">
					<input type="text" name="ex3" class="form-control">	
				</c:if>
				<c:if test="${cdDto.ex3 != null}">
					<input type="text" name="ex3" class="form-control"
						value = "${cdDto.ex3}">
				</c:if>
			</th>
		</tr>
	</table>
	<table style="margin : 0 auto;">
		<tr>
			<th>
				<input class="btn btn-warning" type="submit" value="${btn_modify}">
				<input class="btn btn-warning" type="button" value="${btn_modify_cancel}"
					onclick="inputcancel()">
			</th>
		</tr>	
	</table>
</form>
</body>