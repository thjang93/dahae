<%@page import="member.LogonDBBean"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<script src="${project}member/script.js"></script>

<c:if test="${result == 0}">
	<script type="text/javascript">
		<!--
		erroralert( inputerror );
		//-->
	</script>
</c:if>
<c:if test="${sessionScope.memId == 'admin'}">
	<c:if test="${result == 1}">
		<c:redirect url="memberList.do"/>
	</c:if>
</c:if>
<c:if test="${sessionScope.memId != 'admin'}">
	<c:if test="${result == 1}">
		<c:redirect url="loginForm.do"/>
	</c:if>
</c:if>

	













