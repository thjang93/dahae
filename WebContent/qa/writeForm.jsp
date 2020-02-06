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
<body onload="qa_writefocus()">
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align ="center">
				<b style="font-size:30px;">${page_list}</b>
			</div>	
			<label style="font-size: 20px;"> &nbsp; </label>
			<form method="post" action="qaWritePro.do"
				name="writeform" onsubmit="return qa_writecheck()">				
				<input type="hidden" name="num" value="${num}">
				<input type="hidden" name="pageNum" value="${pageNum}">
				<input type="hidden" name="ref" value="${ref}">
				<input type="hidden" name="re_step" value="${re_step}">
				<input type="hidden" name="re_level" value="${re_level}">				
				<table class="table table-striped table-bordered table-hover"
					style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
					<tr>
						<th width="30" style="text-align:center;">${str_id}</th>
						<td width="70" style="text-align:center;">${sessionScope.memId}</td>
					</tr>
					<tr>
						<th style="text-align:center;">${str_subject}</th>
						<td>
							<input class="form-control" type="text" name="subject" 
								placeholder="제목을 입력하세요." maxlength="50">
						</td>
					</tr>
					<tr>
						<th style="text-align:center;">${str_content}</th>
						<td>
							<textarea class="form-control" rows="20" cols="100" name="content" 
								placeholder="내용을 입력하세요."></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2" style ="text-align:center;">
							<input class="btn btn-info btn-sm" type="submit" value="${btn_write}">
							<input class="btn btn-default btn-sm" type="button" value="${btn_list}"
								onclick="location='qaList.do?pageNum=${pageNum}'">
						</th>
					</tr>		
				</table>	
			</form>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>