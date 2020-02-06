<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<script src="${folder}script.js"></script>

<c:if test = "${result == 0}">
	<script type="text/javascript">
		<!--
			erroralert(ordererror);
		//-->
	</script>
</c:if>

<c:if test = "${result != 0}">
	<c:redirect url = "orderSuccess.do?pay=${pay_method}"/>
</c:if>