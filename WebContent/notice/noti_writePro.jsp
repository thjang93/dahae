<%@page import="notice.Dh_noticeDBBean"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<script src="${folder}script.js"></script> 

<c:if test="${imp_count >= 3}">
	<script type="text/javascript">
      <!--
         alert(imperror);
      //-->
   </script>
   <meta http-equiv="refresh" content="0; url=noticeList.do">
</c:if>

<c:if test="${imp_count < 3}">
	<c:if test="${result == 0}">
		<script type="text/javascript">
			<!--
				erroralert(writeerror);
			//-->
		</script>	 
	</c:if> 
	<c:if test = "${result == 1}">
		<c:redirect url ="noticeList.do"/>
	</c:if>
</c:if>