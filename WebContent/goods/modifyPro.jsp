<%@page import="board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<script src="${project}goods/script.js"></script>

<c:if test="${result == 0}">
		<script type="text/javascript">
		<!--
			alert ( modifyerror );
		//-->
		</script>
		<meta http-equiv="refresh" content="0; url=goodsListM.do?pageNum=${pageNum}">
</c:if>
<c:if test="${result != 0}">
	<c:redirect url="goodsListM.do?pageNum=${pageNum}"/>
</c:if>