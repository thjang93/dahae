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
				<form method = "post" action = "goodsSpecPro.jsp" name = "goodViewForm">
					<div align="center">
						<b style="font-size:30px;">상품 상세 설명</b>
					</div>
					<label style = "font-size:20px;"> &nbsp; </label>
					<table style = "margin-right: auto; margin-left: auto; width: 800px; font-size:15px;">
						<tr>
							<th rowspan = "6" style = "text-align: center;">
								<img src = '${upload}${goodsDto.goods_images}' width = 380px height = auto>
							</th>
							<th rowspan = "6"> <label style = "font-size:50px;"> &nbsp; </label> </th>
						</tr>
						<tr>
							<th width = "380px"> 상품명 : ${goodsDto.goods_name} </th>
						</tr>
						<tr>
							<th> <pre>${goodsDto.goods_info}</pre> </th>
						</tr>
						<tr>
							<th> 가격 : <fmt:formatNumber value="${goodsDto.price}"/> </th>
						</tr>
						<tr>
							<th> 
								<table style = "margin-left: auto; margin-right: auto;">
									<tr>
										<th rowspan="2" style="text-align:center; width:50px;">
											<input class="form-control" type="text" 
												name="goods_count" value="${1}">
										</th>
										<th style="text-align:center">
											<input class = "btn btn-default" type="button" 
												value="▲" onclick="g_add()">
										</th>
									</tr>
									<tr>
										<th style="text-align:center">
											<input class = "btn btn-default" type="button" 
												value="▼" onclick="g_cha()">
										</th>
									</tr>
								</table>
							</th>
						</tr>
						<tr>
							<td style = "text-align: center;">
								<input class = "btn btn-info btn-sm" type = "button" value = "${str_cart}" 
									onclick = "return g_membercheckCart('${sessionScope.memId}','${goodsDto.goods_number}')">
								<input class = "btn btn-danger btn-sm" type = "button" value = "${str_order}" 
									onclick = "return g_membercheck('${sessionScope.memId}', '${param.goodsNum}')">
							</td>
						</tr>
						<tr>			
							<th colspan = "3" style = "text-align: center;">
								<p class = "page-header" align = "center" style = "font-size: 25px;">상품 상세 이미지</p><br>
								<c:if test = "${goodsDto.goods_specimg1s != null}">
									<img src = '${upload}${goodsDto.goods_specimg1s}' style="width: 800px; height: auto;"> <br>
								</c:if>
								<c:if test = "${goodsDto.goods_specimg2s != null}">
									<img src = '${upload}${goodsDto.goods_specimg2s}' style="width: 800px; height: auto;"> <br>
								</c:if>
								<c:if test = "${goodsDto.goods_specimg3s != null}">
									<img src = '${upload}${goodsDto.goods_specimg3s}' style="width: 800px; height: auto;"> <br>
								</c:if>
								<br>
							</th>
						</tr>
						<tr>
							<th colspan = "3">
								<p class = "page-header" align = "center" style = "font-size: 25px;">구매 정보</p>
								<p>
									주문 배송 정보 <br>
									- 배송지역 : 전국 (도서 산간 지역 및 제주도 추가 배송료 발생) <br>
									- 출고시간 : 평일 오후 4시 이전 결제시 당일 발송 (주말 및 공휴일 배송 불가) <br>
									- 배송기간 : 평균 1~2일 <br><br>
									
									결제 정보 <br>	
									- 교환/환불/취소 불가 <p>
								</p>	
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