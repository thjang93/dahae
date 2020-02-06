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
				<b style="font-size: 30px;">${str_notice}</b>
			</div>
			<label style="font-size: 20px;"> &nbsp; </label>
			<div class="container" > 
					<div align="center" >
						<table class= "table table-striped table-bordered table-hover"
							style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
							<tr>
								<th width="15%" style ="text-align:center;">${str_number}</th>
								<td align = "center" >${boardDto.n_number}</td>     
								<th width="15%" style ="text-align:center;"> ${str_readcount}</th>
								<td align ="center">${boardDto.n_readcount}</td>
							</tr>
							<tr>
								<th style ="text-align:center;">${str_id}</th>
								<td align ="center">${boardDto.id}</td>     
								<th style ="text-align:center;">${str_reg_date}</th>
								<td align = "center">
									<fmt:formatDate value="${boardDto.n_reg_date}" type="both" 
										pattern="yyyy-MM-dd HH:mm"/>
								</td>
							</tr>    
							<tr>
								<th style ="text-align:center;">${str_subject}</th>
								<td colspan = "3" style="text-align:left;">${boardDto.n_subject}</td>
							</tr>    
							<tr>
								<td colspan="5" height=auto; valign="top" style="text-align:left;">
									<pre>${boardDto.n_content}</pre>
								</td>
							</tr>
							<tr>      
								<th colspan = "4"  style ="text-align:center;">
								 	<c:if test="${sessionScope.memId == 'admin'}">
										<input class="btn btn-info btn-sm" type="button" value ="${btn_modify}"
										 	onclick="location='noticeModifyForm.do?n_number=${boardDto.n_number}&pageNum=${pageNum}'">
										<input class = "btn btn-info btn-sm" type="button" value = "${btn_delete}"
										 	onclick="location='noticeDeleteForm.do?n_number=${boardDto.n_number}&pageNum=${pageNum}'"> 
								  	</c:if>      
									<input class = "btn btn-default btn-sm" type="button" value = "${btn_prelist}"
										onclick = "location = 'noticeList.do?pageNum=${pageNum}'">
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
