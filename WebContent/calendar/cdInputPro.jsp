<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<c:if test="${result == 0}">
	<script type="text/javascript">
		<!--
		erroralert( inputerror );
		//-->
	</script>
</c:if>
<c:if test="${result == 1}">
	<script type="text/javascript">
		<!--
		self.close();
		opener.document.changeForm.submit();
		//-->
	</script>
</c:if>