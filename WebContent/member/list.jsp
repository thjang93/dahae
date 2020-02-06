<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${folder}script.js"></script>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}style.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size: 30px;">회원 리스트</b>
			</div>
			<div id ="list">
				<div align="center">
					<b>( 전체회원 : ${count} )</b>
				</div>
			</div>
			<div class="container">
				<form method="post" action="memberList.do" name="listform">
					<table style="margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
						<tr>
							<td style="text-align:right;">
								<input class="btn btn-default" type="button" value="${btn_insert}"
									onclick="location='inputForm.do'">
								<input class="btn btn-default" type="button" value="${btn_passwd_delete}"
									onclick="admindelete()">
							</td>
						</tr>
					</table>
					<table class="table table-stroped table-bordered table-hover"
						style="margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
						<thead>
							<tr>
								<th width="5%" style ="text-align:center;"> ${str_num} </th>
								<th width="15%" style ="text-align:center;"> ${str_id} </th>
								<th width="8%" style ="text-align:center;"> ${str_name} </th>
								<th width="8%" style ="text-align:center;"> ${str_jumin} </th>
								<th width="18%" style ="text-align:center;"> ${str_tel} </th>
								<th width="13%" style ="text-align:center;"> ${str_reg_date} </th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${count != 0}">	
								<c:forEach var="memberList" items="${memberList}">
									<tr>
										<td>
											${memberList.num}
										</td>
										<td style="cursor:pointer;"
											onclick="location='adminModifyView.do?id=${memberList.id}'">
											<b>${memberList.id}</b>
										</td>
										<td>
											${memberList.name}
										</td>
										<td>
											${memberList.birth_date}
										</td>
										<td>
											${memberList.tel}
										</td>
										<td>
											${memberList.reg_date}
										</td>
									</tr>				
								</c:forEach>			
							</c:if>
						</tbody>
					</table>
					<div id = "paging" style = "text-align: center;">
						<c:if test="${count gt 0}">
							<c:if test="${startPage gt pageBlock}">
								<a href="list.do?pageNum=${1}">[◀◀]</a>
								<a href="list.do?pageNum=${startPage-pageBlock}">[◀]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i == currentPage}">
									<span>[${i}]</span>
								</c:if>	
								<c:if test="${i != currentPage}">	
									<a href="list.do?pageNum=${i}">[${i}]</a>
								</c:if>
							</c:forEach>
							<c:if test="${pageCount gt endPage}">
								<a href="list.do?pageNum=${startPage+pageBlock}">[▶]</a>
								<a href="list.do?pageNum=${pageCount}">[▶▶]</a>
							</c:if>
						</c:if>
					</div>
				</form>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>