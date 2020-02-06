<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<link href="${project}calendar/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
<script src = "${project}calendar/script.js"></script>

<form method="post" action="cdModifyForm.do">
	
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
		<c:if test="${cdDto.breakfast == null && cdDto.lunch == null && cdDto.dinner == null}">
		<tr>
			<th>
				식단 일정을 등록해주세요 
			</th>
		</tr>
		</c:if>
		<c:if test="${cdDto.breakfast != null}">
		<tr>
			<th>
				${str_br} :	${cdDto.breakfast}
			</th>
		</tr>
		</c:if>	
		<c:if test="${cdDto.lunch != null}">
		<tr>
			<th>
				${str_lu} :	${cdDto.lunch}
			</th>
		</tr>
		</c:if>
		<c:if test="${cdDto.dinner != null}">
		<tr>
			<th>
				${str_di} :	${cdDto.dinner}		
			</th>
		</tr>	
		</c:if>		
	</table>
	
	<h3 style = "font-family: 'Jeju Gothic', serif"> 
		${str_ex}
	</h3>
	<h4 style = "font-family: 'Jeju Gothic', serif"> 
		${msg_ex}<br>
		${msg_ex2}
	</h4>
	<table class="table table-stripped" style = "font-family: 'Nanum Gothic', serif">
		<c:if test="${cdDto.ex1 == null && cdDto.ex2 == null && cdDto.ex3 == null}">
		<tr>
			<th>
				운동 일정을 등록해주세요 
			</th>
		</tr>
		</c:if>
		<c:if test="${cdDto.ex1 != null}">
		<tr>
			<th>
				${str_ex1} : ${cdDto.ex1}
			</th>
		</tr>
		</c:if>		
		<c:if test="${cdDto.ex2 != null}">
		<tr>
			<th>
				${str_ex2} : ${cdDto.ex2}
			</th>
		</tr>
		</c:if>	
		<c:if test="${cdDto.ex3 != null}">
		<tr>
			<th>
				${str_ex3} : ${cdDto.ex3}
			</th>
		</tr>
		</c:if>
	</table>
	<table style="margin : 0 auto;">
		<tr>
			<th>
				<input class="btn btn-warning" type="submit" value="${btn_modify}">
				<input class="btn btn-warning" type="button" value="${btn_delete}"
					onclick="location='cdDeletePro.do?year=${year}&month=${month}&day=${day}'"> 
			</th>
		</tr>	
	</table>
</form>