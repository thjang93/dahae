<%@page import="notice.Dh_noticeDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<script src="${folder}script.js"></script>
   
<c:if test = "${resultCheck == 1 }">
	<c:if test = "${result == 0 }">
		<script type="text/javascript">
			<!--
				alert(deleterror);
			//-->
		</script>
		<meta http-equiv="refresh" content="0; url=noticeList.do?pageNum=${pageNum}">
    </c:if>      		
	<c:if test = "${result == 1}"> 		  
		<c:redirect url="noticeList.do?pageNum=${pageNum}"/>
	</c:if>
</c:if>