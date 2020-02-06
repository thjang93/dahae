<%@page import="member.LogonDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<script src="${folder}script.js"></script>

<c:if test="${result == 0}">
	<script type="text/javascript">
		<!--
			alert( updateerror );
		//-->
	</script>
	<meta http-equiv="refresh" content="0; url=memberList.do">
</c:if>
<c:if test="${result != 0}">
	<c:redirect url="memberList.do"/>
</c:if>