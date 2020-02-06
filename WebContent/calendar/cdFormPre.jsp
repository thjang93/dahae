<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
	<script src = "${project}calendar/script.js"></script>
</head>

<c:set var="memId" value="testMEMID" scope="session"/>
<br>
<form method="post" action="cdForm.do" name="changeForm">
	<table style="width: 800px; margin : 0 auto;">
		<tr>
		   	<td width="250" align="right">
		   		<input class="btn btn-warning" type="button" 
		   			value="◁" onClick="monthDown()">
		   	</td>
			<td width="100" align="center">
	      	<select class="form-control" name="year" 
	      		onchange="selectCheck()" style="width: 95px">
				      <c:forEach var="i" begin="${year-10}" end="${year+9}">
				      		<c:if test="${i == year}">
				      			<c:set var="selected" value="selected"/>
				      			<c:set var="color" value="#CCCCCC"/>
				      		</c:if>
				      		<c:if test="${i != year}">
				      			<c:set var="selected" value=""/>
				      			<c:set var="color" value="#FFFFFF"/> 
				      		</c:if>
							<option value="${i}" ${selected} 
								style="background:${color}">
								${i}
							</option> 
				      </c:forEach>
		      	</select>
		     </td>
		     <td width="100" align="center">
		      	<select class="form-control" name="month" 
		      		onchange="selectCheck()" style="width: 90px">
				      <c:forEach var="i" begin="1" end="12">
				      		<c:if test="${i == month+1}">
				      			<c:set var="selected" value="selected"/>
				      			<c:set var="color" value="#CCCCCC"/>
				      		</c:if>
				      		<c:if test="${i != month+1}">
				      			<c:set var="selected" value=""/>
				      			<c:set var="color" value="#FFFFFF"/> 
				      		</c:if>
				      		<option value="${i}" ${selected} 
				      			style="background:${color}">
								${i}
							</option>
				      </c:forEach>
		      	</select>
			</td>
			<td width="250">
				<input class="btn btn-warning" type="button" 
					value="▷" onClick="monthUp()">
			</td>
		</tr>
		<tr>
			<td align="right" colspan="4">
				<a href="cdForm.do">
					<font size="4" style="font-family: 'Jeju Gothic', serif">
						 오늘 : ${today}
					</font>
				</a>
			</td>
		</tr>
	</table> 
     
	<table class="table table-bordered" style="width: 800px; margin : 0 auto;">
		 <tr height="30" class="warning">
			  <th style="text-align : center"><b><font size="3">일</font></b></th>
			  <th style="text-align : center"><b><font size="3">월</font></b></th>
			  <th style="text-align : center"><b><font size="3">화</font></b></th>
			  <th style="text-align : center"><b><font size="3">수</font></b></th>
			  <th style="text-align : center"><b><font size="3">목</font></b></th>
			  <th style="text-align : center"><b><font size="3">금</font></b></th>
			  <th style="text-align : center"><b><font size="3">토</font></b></th>
		 </tr>
		 <tr height="80">
			<c:forEach var="i" begin="1" end="${startDay-1}">
				<td>&nbsp;</td>
				<c:set var="count" value="${count+1}"/>
			</c:forEach>
			
			<c:set var="count" value="${count}"/>
				
			<c:forEach var="i" begin="${startDate}" end="${endDate}" step="1">
				<c:set var="test" value="${year}/${month+1}/${i}"/>
				
				<c:if test="${today eq test}">
					<c:set var="bgcolor" value="#CCCCCC"/>
				</c:if>
				<c:if test="${today ne test}">
					<c:set var="bgcolor" value="#FFFFFF"/>
				</c:if>
				
				<c:if test="${count%7 == 0 || count%7 == 6}">
					<c:set var="color" value="red"/>
				</c:if>
				<c:if test="${count%7 != 0 && count%7 != 6}">
					<c:set var="color" value="black"/>
				</c:if>
				
				<c:set var="count" value="${count+1}"/>
				
			  	<td bgcolor="${bgcolor}" onclick="openInputDay(${i})" 
			  		style="cursor:pointer;">
			  		<font size="2" color="${color}">${i}</font>
			  		<table>
			  			<tr>
			  				<th>
			  					
			  				</th>
			  			</tr>
			  		</table>
			  	</td>
			  	
				<c:if test="${count % 7==0 && (i<endDate)}">
					 </tr>
					 <tr height="80">
				</c:if>
			</c:forEach>
		</tr>  
	</table>	
</form>  