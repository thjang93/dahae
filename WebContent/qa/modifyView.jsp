<%@ page import="qa.QaDataBean"%>
<%@ page import="qa.QaDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<script src="${folder}script.js"></script>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
	</head>
	<body onload="qa_modifyfocus()">
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<c:if test="${result == 0}">
					<script type="text/javascript">
						<!--
							alert( iderror2 );
						//-->
					</script>
					<meta http-equiv="refresh" content="0; url=qaList.do?pageNum=${pageNum}">
				</c:if>
					<c:if test="${result == 1}">
					<div align="center">
						<b style="font-size: 30px;">${page_list}</b> <br> 
						<b style="font-size: 15px;">${msg_modyfy}</b>
					</div>
					<label style="font-size: 20px;"> &nbsp; </label>
					<div class="container">
						<form method="post" action="qaModifyPro.do"
							name="modifyform" onsubmit="return qa_modifycheck()">	
							<input type="hidden" name="num" value="${num}">
							<input type="hidden" name="pageNum" value="${pageNum}">					
							<table class="table table-striped table-bordered table-hover"
								style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
									<tr>
										<th width="30" style="text-align:center;">${str_id}</th>
										<td width="70" style="text-align:center;">${qaDto.id}</td>
									</tr>
									<tr>
										<th style="text-align:center;"> ${str_subject} </th>
										<td>
											<input class="form-control" type="text" name="subject" maxlength="50"
												value="${qaDto.subject}">
										</td>
									</tr>	
									<tr>
										<th style="text-align:center;"> ${str_content} </th>
										<td>
											<textarea class="form-control" rows="20" cols="100" name="content">${qaDto.content}</textarea>
										</td>
									</tr>
									<tr>
										<th colspan="2"  style ="text-align:center;">
											<input class="btn btn-info btn-sm" type="submit" value="${btn_mod}">
											<input class="btn btn-info btn-sm" type="reset" value="${btn_cancel}">
											<input class="btn btn-default btn-sm" type="button" value="${btn_mod_cancel}"
												onclick="location='qaList.do?pageNum=${pageNum}'">
										</th>
									</tr>
							</table>
						</form>
					</div>				
				</c:if>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>