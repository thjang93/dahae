<%@page import="cart.CartDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 주문 폼 -->
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script src="${folder}script.js"></script>
		<meta name = "viewport" content = "width = device-width", initial-scale = "1">
		<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
		<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
	</head>
	<body onload="orderfocus()">
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<div align="center">
					<b style="font-size: 30px;">${page_order}</b>
				</div>	
					<label style = "font-size:20px;"> &nbsp; </label>
					<form method = "post" action = "orderPro.do"
						name = "orderForm" onsubmit = "return ordercheck()"> 
						<table class = "form-inline" style = "margin-right: auto; margin-left: auto; width: 800px">		
						<!-- 일단 상품 상세 페이지에서 상품 하나만 주문할 때 -->
							<tr>
								<th width="10%" style="text-align:center;font-size:15px;""> ${str_goods_name} </th>
								<th width="8%" style="text-align:center;font-size:15px;""> ${str_goods_img} </th>
								<th width="13%" style="text-align:center;font-size:15px;""> ${str_goods_info} </th>
								<th width="3%" style="text-align:center;font-size:15px;""> ${str_goods_count} </th>
								<th width="5%" style="text-align:center;font-size:15px;""> ${str_goods_price} </th>
							</tr>
							<tr>
								<td> <p style="font-size: 1px;"> </p></td>
							</tr>
								<c:if test = "${order == 1}">
									<c:forEach var="goodsDto" items="${goodsDto}">
										<tr>
											<td style="text-align:center;font-size:15px;"> ${goodsDto.goods_name} </td>
											<td style="text-align:center;">
												<img src = "${upload}${goodsDto.goods_images}" width = "180px" height = "auto">
											</td>
											<td style="text-align:center;">
												<pre style="text-align:center; font-size:15px; background:transparent; border:0;">${goodsDto.goods_info}</pre>
											</td>
											<c:set var = "cartDto" value = "<%=CartDBBean.getInstance()%>"/>
											<td style="text-align:center;font-size:15px;"> ${cartDto.getGoodsCount(sessionScope.memId, goodsDto.goods_number)}</td>
											<td style="text-align:center;font-size:15px;"> <fmt:formatNumber value="${goodsDto.price}"/> </td>
										</tr>
										<input type = "hidden" name = "goodsDto" value = "${goodsDto}">
									</c:forEach>
								</c:if>
								<c:if test = "${order == 0}">
									<tr>
										<td style="text-align:center;font-size:15px;"> ${goodsDto.goods_name} </td>
										<td style = "text-align: center;">
											<img src = "${upload}${goodsDto.goods_images}" width = "180px" height = "auto">
										</td>
										<td> <pre style="text-align:center;font-size:15px;background:transparent;border:0;">${goodsDto.goods_info}</pre> </td>
										<td style="text-align:center;font-size:15px;"> ${goodsCount} </td>
										<td style="text-align:center;font-size:15px;"> <fmt:formatNumber value="${goodsDto.price}"/> </td>
									</tr>
								</c:if>
							<tr>		
								<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> ${str_sendto} </th>
							</tr>
							<tr>
								<td> <p style="font-size: 0.5px;"> </p> </td>
							</tr>
							<tr>
								<th style = "text-align: center;"> ${str_name} </th>
								<td colspan = "4">
									<input class = "form-control" type = "text" name = "delivery_name" value = "${memberDto.name}">
								</td>
							</tr>
							<tr>
								<td> <p style="font-size: 0.3px;"> </p> </td>
							</tr>
							<tr>
								<th style = "text-align: center;"> ${str_tel} </th>
								<!-- 전화번호가 회원정보에 없을 때 -->
								<c:if test = "${memberDto.tel == null}">
									<td colspan = "4">
										<input class = "form-control" type = "text" name = "delivery_tel1" 
											onkeyup="onexttel2()" maxlength="3" style="width:60px;"> -
										<input class = "form-control" type = "text" name = "delivery_tel2" 
											onkeyup="onexttel3()" maxlength="4" style="width:60px;"> -
										<input class = "form-control" type = "text" name = "delivery_tel3" maxlength="4" style="width:60px;">
									</td>
								</c:if>
								<!-- 전화번호가 회원정보에 있을 때 -->
								<c:if test = "${memberDto.tel != null}"> 
									<c:set var = "tel" value = "${fn:split(memberDto.tel, '-')}"/>
									<c:forEach var="telNum" items = "${tel}" varStatus="g">
										<c:if test = "${g.count == 1}">
											<c:set var = "tel1" value = "${telNum}"/>
										</c:if>
										<c:if test = "${g.count == 2}">
											<c:set var = "tel2" value = "${telNum}"/>
										</c:if>
										<c:if test = "${g.last}">
											<c:set var = "tel3" value = "${telNum}"/>
										</c:if>
									</c:forEach>
									<td colspan = "4">				
										<input class = "form-control" type = "text" name = "delivery_tel1" value = "${tel1}"
											onkeyup="onexttel2()" maxlength="3" style="width:60px;"> -
										<input class = "form-control" type = "text" name = "delivery_tel2" value = "${tel2}"
											onkeyup="onexttel3()" maxlength="4" style="width:60px;"> -
										<input class = "form-control" type = "text" name = "delivery_tel3" value = "${tel3}"
											maxlength="4" style="width:60px;">
									</td>
								</c:if>
							</tr>
		
							<tr>
								<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> ${str_delivery_addr} </th>
							</tr>
							<tr>
								<td> <p style="font-size: 0.5px;"> </p> </td>
							</tr>
							<tr>
								<!-- 회원정보에 주소가 없을때 -->
								<th style = "text-align: center;"> ${str_zipcode} </th>
								<c:if test = "${memberDto.address == null}">
									<td colspan = "4">
										<input class = "form-control" type = "text" name = "sample6_postcode" id = "sample6_postcode" 
											style="width:205px;" readonly>
					 					<input class = "btn btn-info btn-sm"" type = "button" value = "우편번호 검색" onclick = "addresscheck()"
					 						placeholder="추가할 주소를 입력해주세요">
				 					</td>
								</c:if>
								<!-- 회원정보에 주소가 있을때 -->
								<c:if test = "${memberDto.address != null}">
									<td colspan = "4">
										<input class = "form-control" type = "text" name = "sample6_postcode" id = "sample6_postcode"
											style="width:205px;" value = "${memberDto.zipcode}" readonly>
					 					<input class = "btn btn-info btn-sm"" type = "button" value = "우편번호 검색" onclick = "addresscheck()">
				 					</td>
								</c:if>
							</tr>
							<tr>
								<!-- 회원정보에 주소가 없을때 -->
								<th style = "text-align: center;"> ${str_addr} </th>
								<c:if test = "${memberDto.address == null}">
									<td colspan = "4">
										<p style="font-size: 0.1px;"> </p>
										<input class = "form-control" type = "text" name = "sample6_address1" id = "sample6_address1" 
											style="width:300px;" readonly> <br>
					 					<p style="font-size: 0.15px;"> </p>
					 					<input class = "form-control" type = "text" name = "sample6_address2" id = "sample6_address2"
					 						style="width:300px;">
				 					</td>
								</c:if>
								<!-- 주소가 있을때 -->
								<c:if test = "${memberDto.address != null}">
									<c:set var = "address" value = "${fn:split(memberDto.address, '/n')}"/>
									<c:forEach var="addr" items = "${address}" varStatus="g">
										<c:if test = "${g.count == 1}">
											<c:set var = "sample6_address1" value = "${addr}"/>
										</c:if>
										<c:if test = "${g.last}">
											<c:set var = "sample6_address2" value = "${addr}"/>
										</c:if>
									</c:forEach>
									<td colspan = "4">
										<input class = "form-control" type = "text" name = "sample6_address1" id = "sample6_address1" 
											style="width:300px;" value = "${sample6_address1}" readonly> <br>
					 					<input class = "form-control" type = "text" name = "sample6_address2" id = "sample6_address2" 
					 						style="width:300px;" value = "${sample6_address2}">
				 					</td>
								</c:if>
							</tr>
							<tr>
								<td> <p style="font-size: 0.15px;"> </p></td>
							</tr>
							<tr>
								<th style = "text-align: center;"> ${str_delivery_msg} </th>
								<td colspan = "4">
									<textarea rows="3" cols="37" class = "form-control" name = "delivery_msg"> </textarea>
								</td>
							</tr>
							<tr>
								<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> ${str_total_price} </th>
							</tr>
								<c:if test = "${order == 1}"> 
									<c:forEach var="goodsDto" items="${goodsDto}">
										<c:set var = "cartDto" value = "<%=CartDBBean.getInstance()%>"/>
										<c:set var = "count" value = "${cartDto.getGoodsCount(sessionScope.memId, goodsDto.goods_number)}"/>
										<c:set var = "price" value = "${goodsDto.price * count}"/>
										<c:set var = "total" value = "${total = total + price}"/>
									</c:forEach>
									<td colspan = "5"> <label> 합계 : </label> <fmt:formatNumber value="${total}"/> + 배송비 (+2,500) = <label style="font-size:20px; color:red;"> <fmt:formatNumber value="${total + 2500}"/> </label> </td>
								</c:if>
								<c:if test = "${order == 0}">
									<tr>
										<td colspan = "5"> <label> 합계 : </label> <fmt:formatNumber value="${goodsDto.price * goodsCount}"/> + 배송비 (+2,500) = <label style="font-size:20px; color:red;"> <fmt:formatNumber value="${goodsDto.price * goodsCount + 2500}"/> </label> </td>
									</tr>
								</c:if>
							<tr>
								<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> ${str_pay_method} </th>
							</tr>
							<tr>
								<td colspan = "5">
									<input type = "radio" name = "pay_method" value = "0"> 카드
									<input type = "radio" name = "pay_method" value = "1"> 무통장 입금
								</td>
							</tr>
							<tr>
								<th colspan = "5" style = "text-align: center;">
									<br>
									<input class = "btn btn-info" type = "submit" value = "${btn_order}">
									<input class = "btn btn-default" type = "button" value = "${btn_order_cancel}"
										onclick = "o_goback()">
									<input type = "hidden" name = "goods_number" value = "${param.goodsNum}">
									<input type = "hidden" name = "order" value = "${order}">
									<input type = "hidden" name = "goods_count" value = "${goodsCount}">
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