<%@ page import="qa.QaDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
<%@ include file="setting.jsp" %>
<script src="${folder}script.js"></script>

<c:if test="${resultCheck == 0}">
	<script type="text/javascript">
		<!--
			alert( iderror );
		//-->
	</script>
	<meta http-equiv="refresh" content="0; url=qaList.do?pageNum=${pageNum}">
</c:if>
<c:if test="${resultCheck == 1}">
	<c:if test="${result == 0}">	
		<script type="text/javascript">
			<!--
				alert( deleteerror );
			//-->				
		</script>
		<meta http-equiv="refresh" content="0; url=qaList.do?pageNum=${pageNum}">
	</c:if>		
	<c:if test="${result == -1}">
		<script type="text/javascript">
			<!--
				alert( replyerror );
			//-->				
		</script>
		<meta http-equiv="refresh" content="0; url=qaList.do?pageNum=${pageNum}">
	</c:if>
	<c:if test="${result == 1}">
		<c:redirect url="qaList.do?pageNum=${pageNum}"/>
	</c:if>
</c:if>	
