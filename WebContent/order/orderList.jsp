<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
		<script src="${folder}script.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<div align="center">
					<b style="font-size:30px;">주문 리스트</b>
				</div>
				<div id ="list">
					<div align="center">
						<b>( 전체주문 : ${count} )</b>
					</div>
				</div>			
				<a style = "font-size:20px;"> &nbsp; </a>
				<div class="container">
				<table class = "table table-stroped table-bordered table-hover"
					style = "margin-right: auto; margin-left: auto; width: 800px; font-size:15px;">
					<thead>
						<tr>
							<th style ="text-align:center;"> 주문날짜 </th>
							<th style ="text-align:center;"> 주문번호 </th>
							<c:if test="${sessionScope.memId == 'admin'}">
								<th style ="text-align:center;"> 아이디 </th>
							</c:if>
							<th style ="text-align:center;"> 이름 </th>
							<th style ="text-align:center;"> 상품명 </th>
							<th style ="text-align:center;"> 주문상황 </th>
							<th style ="text-align:center;"> 상세보기 </th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${count != 0}">		
							<c:forEach var="orderList" items="${orderList}">
								<tr>
									<td align ="center">
										<fmt:formatDate value="${orderList.order_date}" type="both"
										pattern="yyyy-MM-dd HH:mm"/>				
									</td>
									<td align ="center">
									   ${orderList.order_number}
									</td>
									<c:if test="${sessionScope.memId == 'admin'}">
										<td align ="center">
											${orderList.id}
										</td>
									</c:if>
									<td align ="center">
										${orderList.delivery_name}
									</td>
									<c:if test = "${sessionScope.memId != 'admin'}">
										<td align ="center">
											<img src = "${upload}${orderList.goods_images}" 
												style="width:120px; height:auto;">  <br>
											${orderList.goods_name}
										</td>
									</c:if>
									<c:if test = "${sessionScope.memId == 'admin'}">
										<td align ="center">
											${orderList.goods_name}
										</td>
									</c:if>
									<td align ="center">
										${orderList.order_state}
									</td>								
							 <td colspan="2" style="text-align:center;">
							       <input class="btn btn-info btn-sm" type="button" value="상세"
							        onclick="location='orderView.do?orderNum=${orderList.order_number}'">
						     </td>
						   	</tr>	
							</c:forEach>			
						</c:if>
					</tbody>
				  </table>
					<div id="paging"  style ="text-align:center;">
						<c:if test="${count gt 0}">
							<c:if test="${startPage gt pageBlock}">
								<a href="orderList.do">[◀◀]</a>
								<a href="orderList.do?pageNum=${startPage-pageBlock}">[◀]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i == currentPage}">
									<span>[${i}]</span>
								</c:if>	
								<c:if test="${i != currentPage}">	
									<a href="orderList.do?pageNum=${i}">[${i}]</a>
								</c:if>
							</c:forEach>
							<c:if test="${pageCount gt endPage}">
								<a href="orderList.do?pageNum=${startPage+pageBlock}">[▶]</a>
								<a href="orderList.do?pageNum=${pageCount}">[▶▶]</a>
							</c:if>
						</c:if>
					</div>  
				 </div>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>