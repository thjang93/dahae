<%@page import="java.text.SimpleDateFormat"%>
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
<body>
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size:30px;"> ${page_list}</b>
			</div>
			<label style="font-size: 20px;"> &nbsp; </label>
			<div class="container" > 
				<div align="center" >  	
					<table class= "table table-striped table-bordered table-hover"
						style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
						<tr>
							<th width="15%" style ="text-align:center;"> ${str_num} </th>
							<td align="center"> ${number} </td>
							<th width="15%" style ="text-align:center;"> ${str_readcount} </th>
							<td align="center"> ${qaDto.readcount} </td>
						</tr>
						<tr>
							<th style ="text-align:center;"> ${str_id} </th>
							<td align="center"> ${qaDto.id} </td>
							<th style ="text-align:center;"> ${str_reg_date} </th>
							<td align="center">
								<fmt:formatDate value="${qaDto.reg_date}" type="both"
								pattern="yyyy-MM-dd HH:mm"/>			
							</td>
						</tr>
						<tr>
							<th style ="text-align:center;"> ${str_subject} </th>
							<td colspan="3" style="text-align:left;"> ${qaDto.subject} </td>
						</tr>
						<tr>
							<td colspan="5" height=auto; valign="top" style="text-align:left;">
								<pre>${qaDto.content}</pre>
							</td>
						</tr>
						<tr>
							<th colspan="4" style ="text-align:center;">
								<input class="btn btn-info btn-sm" type="button" value="${btn_modify}"
										onclick="location='qaModifyForm.do?num=${qaDto.num}&pageNum=${pageNum}'">
								<input class="btn btn-info btn-sm" type="button" value="${btn_delete}"
										onclick="location='qaDeleteForm.do?num=${qaDto.num}&pageNum=${pageNum}'">
								<c:if test = "${sessionScope.memId == 'admin'}">
									<input class="btn btn-info btn-sm" type="button" value="${btn_reply}"
										onclick="return qa_membercheckRe('${sessionScope.memId}','${pageNum}','${qaDto.num}','${qaDto.ref}','${qaDto.re_step}','${qaDto.re_level}')">
								</c:if>  
								<input class="btn btn-default btn-sm" type="button" value="${btn_prelist}"
									onclick="location='qaList.do?pageNum=${pageNum}'">
							</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>