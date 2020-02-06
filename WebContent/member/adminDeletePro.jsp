<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<script src="${folder}/script.js"></script>

<c:if test="${resultCheck == 1}">
	<c:if test="${result == 0}">
		<script type="text/javascript">
			<!--
				alert( deleteerror );
				self.close();
			//-->
		</script>
	</c:if>
	<c:if test="${result == 1}">
		<script type="text/javascript">
			self.close();
			opener.document.listform.submit();
		</script>
	</c:if>
</c:if>
<c:if test="${resultCheck == 0}">
	<script type="text/javascript">
		<!--
			erroralert( idselecterror );
		//-->
	</script>
</c:if>
	