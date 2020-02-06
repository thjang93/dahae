<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>

<meta name = "viewport" content = "width = device-width", initial-scale = "1">
<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
<script src="${folder}script.js"></script>
    
<c:if test="${result == 0}">
	<script type="text/javascript">
		<!--
			alert(updateerror);
		//-->
	</script>
	<meta http-equiv="refresh" content="0; url=orderList.do?orderNum=${orderNum}">
</c:if>

<c:if test ="${result == 1 }">
	<c:redirect url = "orderList.do?orderNum=${orderNum}"/>
</c:if>