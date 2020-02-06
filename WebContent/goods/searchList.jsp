<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
	<script src = "${folder}script.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size: 30px;">상품 검색 결과 </b>
			</div>
			<label style = "font-size:20px;"> &nbsp; </label>
			<table style = "margin-left: auto; margin-right: auto; width: 800px;" >
				<c:if test="${count == 0}">
					<tr>
						<td align="center">
							${msg_searchlist_x}
						</td>
					</tr>
				</c:if>
				<c:if test="${count != 0}">
					<tr>
						<c:forEach var="goodsDto" items="${searchgoods}" varStatus="status">	
						<td align = "center" style="overflow: hidden">
							<a class="thumbnail" href = "goodsView.do?goodsNum=${goodsDto.goods_number}">
								<div class="thumbnail-wrapper">
									<img src='${upload}${goodsDto.goods_images}' border="0"
										style="width:auto; height:230px; overflow:hidden;"><br>
								</div>
								${goodsDto.goods_name}<br>
								<pre>${goodsDto.goods_info}</pre><br>
								<fmt:formatNumber value="${goodsDto.price}"/>	
							</a>
						</td>
							<c:if test="${status.count % 3 eq 0}">
					</tr>
					<tr>
							</c:if>
						</c:forEach>
					</tr>		
				</c:if>
			</table>
			<div id="paging"  style ="text-align:center;">
				<c:if test="${count gt 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="goodsList.do?pageNum=${1}">[◀◀]</a>
						<a href="goodsList.do?pageNum=${startPage-pageBlock}">[◀]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
							<span>[${i}]</span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="goodsList.do?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href="goodsList.do?pageNum=${startPage+pageBlock }">[▶]</a>
						<a href="goodsList.do?pageNum=${pageCount}">[▶▶]</a>
					</c:if>
				</c:if>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>