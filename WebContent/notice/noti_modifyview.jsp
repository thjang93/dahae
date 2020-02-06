<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${folder}script.js"></script>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
</head>
<body onload ="nmodifyfocus()">
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size: 30px;">${str_notice}</b> <br>
				<b style="font-size:15px;">${msg_modyfy}</b>
			</div>
			<label style="font-size: 20px;"> &nbsp; </label>
			<c:if test="${result == 0}">

			</c:if>		    
			<c:if test="${result == 1}">			
			<div class="container">
				<form method="post" action ="noticeModifyPro.do"
					name="modiform" onsubmit="return nmodicheck()">
					<input type="hidden" name="n_number" value="${n_number}">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<table class="table table-striped table-bordered table-hover"
						style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
    
							<tr>
								<th style="text-align:center;">${str_subject}</th>
								<td>
									<input class="form-control" type="text" name ="subject" maxlength="50" value="${boardDto.n_subject}">
								</td>
							</tr>


							<tr>
								<th style="text-align:center;">${str_content}</th>
								<td colspan ="2">
									<textarea rows="20" cols="100" name ="content" class="form-control">${boardDto.n_content}</textarea>
								</td>
							</tr>
							<tr>
								
							</tr>
 
						<tr>
							<td colspan="2" style = "text-align:center; background:white; border: hidden;">
								<input class="btn btn-info btn-sm" type="submit" value="${btn_mod}">
								<input class="btn btn-default btn-sm" type="button" value="${btn_mod_cancel}"
									onclick ="location='noticeList.do?pageNum=${pageNum}'">
							</td>
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