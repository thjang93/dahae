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
<body onload="nwritefocus()">
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size: 30px;">${str_notice}</b> <br>
			</div>
			<label style="font-size: 20px;"> &nbsp; </label>
			<div class="container">
				<form method="post" action="noticeWritePro.do"
					name= "no_writeform" onsubmit="return nwritecheck()">
					<input type="hidden" name="num" value="${num}">
					<input type="hidden" name="imp_level" value="${imp_level}">
					<table class="table table-bordered"
						style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">	
						<tr>
							<th style="text-align:center;">${str_subject}</th>
							<td><input type="text" placeholder="제목을 입력하세요." name="subject" class="form-control"></td>
						</tr>
						<tr>
							<th style="text-align:center;">${str_imp_level}</th>
							<td>
								<select class="form-control" name="notice" style="width:auto;">
									<option value="0">${str_imp_choice}</option>
									<option value="1">${str_imp}</option>
								</select>       
							</td>   	     
						</tr> 		      
						<tr>
							<th style="text-align:center;">${str_content}</th>
							<td>
								<textarea cols="100" rows="20" placeholder="글 내용을 입력하세요." name="content" class="form-control"></textarea>
							</td> 
						</tr>
						<tr>
							<td colspan="2"  style ="text-align:center;">
								<input class="btn btn-info btn-sm" type="submit" value="${btn_write}">
								<input class="btn btn-info btn-sm" type="reset" value="${btn_cancel}">
								<input class="btn btn-default btn-sm" type="button" value="${btn_list}"
									onclick="location='noticeList.do'">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>