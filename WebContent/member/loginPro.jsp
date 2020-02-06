<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
		<script src="${folder}/script.js"></script>	
	</head>
	<body>
		<c:if test = "${result == 0}">
			<script type="text/javascript">
				<!--
					erroralert( noiderror );
				//-->
			</script>
		</c:if>
		<c:if test = "${result == -1}">
			<!-- 비밀번호가 다르다 -->
			<script type="text/javascript">
				<!--
				erroralert( diffpasswderror );
				//-->
			</script>
		</c:if>
		<c:if test = "${result == 1}">
			<!-- 비밀번호가 같다 -->
			${sessionScope.memId = id}
			<c:redirect url = "index.do"/>
		</c:if>
	</body>
</html>