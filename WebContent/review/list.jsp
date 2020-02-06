<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
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
				<b style="font-size:30px;"> ${page_list} </b>
			</div>
			<div id ="list">
				<div align="center">
					<b> ( ${str_count} : ${count} ) </b>
				</div>
			</div>
			<table style = "margin-right: auto; margin-left: auto; width: 1200px; font-size: 15px;">
				<tr>
					<td style="text-align: right;">
					<input class="btn btn-default" type="button" value="${str_write}"
						onclick="return rmembercheck('${sessionScope.memId}')"
						style="font-size: 16px;"></td>
				</tr>
			</table>
			<table class="table table-stroped table-bordered table-hover"
				style = "margin-right: auto; margin-left: auto; width: 1200px; font-size: 15px;">
				<thead>
					<tr>
						<th style="width : 4%; text-align: center;"> ${str_num}</th>
						<th style="width : 8%; text-align: center;"> ${str_goods_name}</th>
						<th style="width : 30%; text-align: center;"> ${str_subject}</th>
						<th style="width : 5%; text-align: center;"> ${str_id}</th>
						<th style="width : 8%; text-align: center;"> ${str_reg_date}</th>
						<th style="width : 4%; text-align: center;"> ${str_readcount}</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${count == 0}">
						<tr>
							<td colspan="6" align="center">
								${msg_list_x}
							</td>
						</tr>
					</c:if>			
					<c:if test="${count != 0}">
						<c:forEach var="reviewDto" items="${articles}">
							<tr>
								<td align="center">	${number} </td>
								<c:set var="number" value="${number-1}"/>
								<c:set var="level" value="${reviewDto.re_level}"/>
								<c:set var="wid" value="${(level-1)*10}"/>
								<td align = "center"> ${reviewDto.goods_name} </td>
								<td style="cursor:pointer; text-align: left;"
									onclick="location='reviewContent.do?pageNum=${pageNum}&num=${reviewDto.num}&number=${number+1}'">
									<c:if test="${level gt 1}">
										<img src="${folder}images/level.gif" border="0" width="${wid}" height="15">
									</c:if>	
									<c:if test="${level gt 0}">
										<img src="${folder}images/re.gif" border="0" width="20" height="15">
									</c:if>
										${reviewDto.subject}				
									<c:if test="${reviewDto.readcount gt 20}">
										<img src="${folder}images/hot.gif" border="0" width="20" height="15">
									</c:if>
								</td>
								<td align = "center"> <b> ${reviewDto.id} </b> </td>
								<td align = "center">
									<fmt:formatDate value="${reviewDto.reg_date}" type="both"
									pattern="yyyy-MM-dd HH:mm"/>	
								</td>
								<td align = "center">
									${reviewDto.readcount}
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div id="paging"  style ="text-align:center;">
				<c:if test="${count gt 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="reviewList.do?pageNum=${1}">[◀◀]</a>
						<a href="reviewList.do?pageNum=${startPage-pageBlock}">[◀]</a>
					</c:if>				
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
							<span>[${i}]</span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="reviewList.do?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href="reviewList.do?pageNum=${startPage+pageBlock }">[▶]</a>
						<a href="reviewList.do?pageNum=${pageCount}">[▶▶]</a>
					</c:if>
				</c:if>
				<form method="post" action="reviewSearchList.do" name="searchform" 
					class="form-inline" style = "margin-top: 10px;">
					<input type="hidden" name="pageNum" value="${currentPage}">
					<select class = "form-control" name="search_code">
						<option value="goods_name">상품명</option>
						<option value="id">작성자</option>
					</select>
					<input class="form-control" type="text" name="keyword" placeholder="검색명을 입력하세요.">
					<input class="btn btn-info" type="submit" value="${btn_search}">
				</form>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>