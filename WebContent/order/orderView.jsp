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
					<b style="font-size:30px;">주문 상세 정보</b>
				</div>			
				<a style = "font-size:20px;"> &nbsp; </a>
				<table class = "form-inline" 
					style = "margin-right: auto; margin-left: auto; width: 800px; font-size:15px;">
				 <c:forEach var="orderDto" items="${result}">
					<tr>
						<th colspan = "5" style="line-height:40px;"> 주문 번호  : ${orderDto.order_number}</th>
					</tr>
					<tr>
						<th colspan = "5" style="line-height:40px;"> 주문 상황 : <label style="color:red;"> ${orderDto.order_state} </label> </th>
					</tr>
					<tr>
						<th style = "text-align: center;"> 상품명 </th>
						<th style = "text-align: center;"> 상품이미지 </th>
						<th style = "text-align: center;"> 상품정보 </th>
						<th style = "text-align: center;"> 수량 </th>
						<th style = "text-align: center;"> 가격 </th>
					</tr>
					<tr>
						<td> <p style="font-size: 1px;"> </p></td>
					</tr>
					<tr>
						<td align="center" style="font-size:15px;">${orderDto.goods_name}</td>
						<td align="center" style="font-size:15px;"><img src="${upload}${orderDto.goods_images}" width = "180px" height = "auto"></td>
						<td align="center" style="font-size:15px;">${orderDto.goods_info}</td>
						<td align="center" style="font-size:15px;">${orderDto.goods_count}</td>
						<td align="center" style="font-size:15px;"><fmt:formatNumber value="${orderDto.price}"/></td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 받는분 </th>
					</tr>
					<tr>
						<td>
							<p style="font-size: 0.5px;"></p>
						</td>
					</tr>
					<tr>
						<th style = "text-align: center; line-height:40px;"> 이름  </th>
						<td colspan = "4"> ${orderDto.delivery_name} </td>
					</tr>
					<tr>
						<th style = "text-align: center; line-height:40px;"> 연락처 </th>
						<td colspan = "4"> ${orderDto.delivery_tel} </td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px; "> <br> 배송지 </th>
					</tr>
					<tr>
						<td>
							<p style="font-size: 0.5px;"></p>
						</td>
					</tr>
					<tr>
						<th style = "text-align: center;line-height:40px;"> 우편번호 </th>
						<td colspan = "4">${orderDto.delivery_zipcode}</td>
					</tr>
					<tr>
						<th rowspan = "2" style = "text-align: center;line-height:40px;"> 주소 </th>
						<c:set var="a" value="${fn:split(orderDto.delivery_address,'/n')}"/>
						<td colspan = "4"> ${a[0]} </td>					
					</tr>
					<tr>
						<td colspan = "4"> ${a[1]} </td>
					</tr>
					<tr>
					
						<th style = "text-align: center;line-height:40px;"> 배송시 요청사항 </th>
						<td colspan = "4"> ${orderDto.delivery_msg} </td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 배송 조회 </th>
					</tr>
					<tr>
						<td>
							<p style="font-size: 0.5px;"></p>
						</td>
					</tr>
					<tr>
						<td> <b> 택배회사 : </b>
							<c:if test = "${orderDto.track_num == 0}">
								정보없음
							</c:if>
							<c:if test = "${orderDto.track_num != 0}">
								${orderDto.track_com}
							</c:if>
						</td>
						
						<td> <b> 운송장 번호 : </b>
							<c:if test = "${orderDto.track_num == 0}">
								정보없음
							</c:if>
							<c:if test = "${orderDto.track_num != 0}">
								${orderDto.track_num}
								<input class = "btn btn-default" type = "button" value = "배송조회">
							</c:if>
						</td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 결제 금액  <label style="font-size:8px;"> (배송비 미포함) </label> </th>		
					</tr>
					<tr>
						<td>
							<p style="font-size: 0.5px;"></p>
						</td>
					</tr>
					<tr>
						<td><fmt:formatNumber value="${orderDto.price*orderDto.goods_count}"/> </td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 결제수단 </th>
					</tr>
					<tr>
						<td>
							<p style="font-size: 0.5px;"></p>
						</td>
					</tr>
					<tr>
						<td>
						  <c:if test ="${orderDto.pay_method == 0}" >
						  		카드
					      </c:if>
					      <c:if test ="${orderDto.pay_method == 1}" >
						    	무통장 입금 
					      </c:if>
						</td>
					</tr>
					<tr>
						<th colspan = "5" style = "text-align: center;">
							<c:if test="${sessionScope.memId == 'admin'}">
							  <input class ="btn btn-info btn-sm" type ="button" value ="수정"
						      id = "adminId" onclick ="location='orderUpdate.do?orderNum=${orderDto.order_number}'"> 
							</c:if>
							<input class = "btn btn-default btn-sm" type = "button" value = "목록" 
							 onclick = "o_goback()">
						</th>
						
					</tr>
					</c:forEach>	
				</table>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>