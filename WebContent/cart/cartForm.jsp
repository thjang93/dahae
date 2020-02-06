<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>

<html>
	<head>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/bootstrap.min.css" type="text/css">
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
	            	<b style="text-align:center; font-size:30px;">${page_cart}</b>
	       		</div>		
	       		<label style = "font-size:20px;"> &nbsp; </label>
				<form name="cartForm">	
					<table class="table table-border table-striped table-hover" 
						style = "width: 80%; margin : 0 auto;">
						<thead>
						<tr class="active">
							<th style="width : 20%; text-align:left">
								<input class="btn btn-danger btn-sm" type="button" value="${btn_delete_all}" 
								onclick="location='cartDelete.do?goods_number=0&pageNum=${currentPage}'">
							</th>
							<th style="width : 45%; text-align:center">${str_goods}</th>
							<th style ="text-align:center">${str_count}</th>
							<th style ="text-align:center">${str_goods_price}</th>
							<th style ="text-align:center">${str_total_price}</th>
							<th style ="text-align:center">${str_delete}</th>				
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
							<c:forEach var="cartDto" items="${someGoods}">
								<tr>
									<th style ="text-align:center">
										<img src="${upload}${cartDto.goods_images}" width = 100px height = auto>
									</th>
									<th style ="text-align:center">
										<a href="goodsView.do?goodsNum=${cartDto.goods_number}">
											<b>${cartDto.goods_name}</b><br>
											<pre>${cartDto.goods_info}</pre><br>
										</a>
									</th>
									<th style ="text-align:center">
										<table style = "margin-left: auto; margin-right: auto;">
											<tr>
												<th rowspan="2" style ="text-align:center">
													<fmt:formatNumber value="${cartDto.goods_count}"/>
												</th>
											</tr>
										</table>
									</th>
									<th style ="text-align:center">
										<fmt:formatNumber value="${cartDto.price}"/>
									</th>
									<th style ="text-align:center">
										<fmt:formatNumber value="${cartDto.price * cartDto.goods_count}"/>
									</th>
									<th style ="text-align:center">
										<a href="cartDelete.do?goods_number=${cartDto.goods_number}&pageNum=${currentPage}">
											<img src="${folder}images/xbox.jpg" width = 20px height = auto>
										</a>
									</th>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
					<br><br>
					<div style ="text-align:center; font-size:18px;">
					<c:if test="${count gt 0}">
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${i == currentPage}">
								<span>[${i}]</span>
							</c:if>
							<c:if test="${i != currentPage}">
								<a href="cartForm.do?pageNum=${i}">[${i}]</a>
							</c:if>
						</c:forEach>
					</c:if>	
					</div>
					<br><br>
					<table class="table table-bordered table-hover" style = "width: 300px; margin : 0 auto;">
						<tr>
							<th class="danger" style="text-align:center">${str_total}</th>
						 	<%-- 장바구니에 담긴 총금액 --%> 
							<c:set var="total" value="${0}"/>
							<c:forEach var="cartDto" items="${someGoods}">
								<c:set var="goods_total" value="${cartDto.price * cartDto.goods_count}"/>
								<c:set var="total" value="${total + goods_total}"/>
							</c:forEach>
							<th style="text-align:center"><fmt:formatNumber value="${total}"/></th>
						</tr>
					</table>
					<br><br>
					<div style ="text-align:center">
					<input class = "btn btn-danger btn-sm" type="button" value="${btn_buy}" onclick="location='orderForm.do?id=${sessionScope.memId}'">
					<input class = "btn btn-info btn-sm" type="button" value="${btn_con}" onclick="con2()">
					</div>
				</form>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>