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
					<b style="font-size:30px;">주문 상세 정보 변경</b>
				</div>
				<form method = "post" action = "orderUpPro.do" 
					name = "orderUpdate" onsubmit = "return ordercheck()"> 
				<table class = "form-inline"
					style = "margin-right: auto; margin-left: auto; width: 800px; font-size: 15px;">
				 <c:forEach var="orderDto" items="${orderDto}">
				 	<input type="hidden" name="order_number" value="${order_number}">
					<tr>
						<th style="line-height:40px;"> 주문 번호  : ${order_number}</th>
					</tr>
					<tr>
						 <td colspan="4" style="text-align:left;">
						 	<label style="line-height:40px;"> 주문 상황 : </label>
						     <select class="form-control" name="order_state">
					           <c:if test = "${orderDto.order_state == '결제대기'}">
					           	<option value="결제대기" selected>결제대기</option>
					           	<option value="결제완료">결제완료</option>
					           	<option value="배송전">배송전</option>
					           	<option value="배송중">배송중</option>
					           	<option value="배송완료">배송완료</option>
					           </c:if>
					           <c:if test = "${orderDto.order_state == '결제완료'}">
					           	<option value="결제대기">결제대기</option>
					           	<option value="결제완료" selected>결제완료</option>
					           	<option value="배송전">배송전</option>
					           	<option value="배송중">배송중</option>
					           	<option value="배송완료">배송완료</option>
					           </c:if>
					           <c:if test = "${orderDto.order_state == '배송전'}">
					           	<option value="결제대기">결제대기</option>
					           	<option value="결제완료">결제완료</option>
					           	<option value="배송전" selected>배송전</option>
					           	<option value="배송중">배송중</option>
					           	<option value="배송완료">배송완료</option>
					           </c:if>
					           <c:if test = "${orderDto.order_state == '배송중'}">
					           	<option value="결제대기">결제대기</option>
					           	<option value="결제완료">결제완료</option>
					           	<option value="배송전">배송전</option>
					           	<option value="배송중" selected>배송중</option>
					           	<option value="배송완료">배송완료</option>
					           </c:if>
					           <c:if test = "${orderDto.order_state == '배송완료'}">
					           	<option value="결제대기">결제대기</option>
					           	<option value="결제완료">결제완료</option>
					           	<option value="배송전">배송전</option>
					           	<option value="배송중">배송중</option>
					           	<option value="배송완료" selected>배송완료</option>
					           </c:if>		          
							</select>  
						 </td>  
					</tr>
					<tr>
						<th style = "text-align: center;"> 상품명 </th>
						<th style = "text-align: center;"> 상품이미지 </th>
						<th style = "text-align: center;"> 상품정보 </th>
						<th style = "text-align: center;"> 수량 </th>
						<th style = "text-align: center;"> 가격 </th>
					</tr>
					<tr>
						<td style = "font-size:15px; text-align: center;">${orderDto.goods_name}</td>
						<td style = "font-size:15px; text-align: center;"><img src="${upload}${orderDto.goods_images}" width = "180px" height = "auto"></td>
						<td style = "font-size:15px; text-align: center;">${orderDto.goods_info}</td>
						<td style = "font-size:15px; text-align: center;">${orderDto.goods_count}</td>
						<td style = "font-size:15px; text-align: center;"><fmt:formatNumber value="${orderDto.price}"/></td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 받는분 </th>
					</tr>
					<tr>
						<th style="line-height:40px;text-align:center;"> 이름 </th>
						<td colspan = "4"> ${orderDto.name} </td>
					</tr>
					<tr>
						<th style="line-height:40px;text-align:center;"> 연락처 </th>
						<td colspan = "4"> ${orderDto.delivery_tel} </td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 배송지 </th>
						
					</tr>
					<tr>
						<th style="line-height:40px;text-align:center;"> 우편번호 </th>
						<td colspan = "4">${orderDto.delivery_zipcode}</td>
					</tr>
					<tr>
						<th rowspan="2" style="line-height:40px;text-align:center;"> 주소 </th>
						<c:set var="a" value="${fn:split(orderDto.delivery_address,'/n')}"/>
						<td colspan = "4"> ${a[0]} </td>
					</tr>
					<tr>
						<td colspan = "4"> ${a[1]} </td>
					</tr>
					<tr>
						<th style="line-height:40px;text-align:center;"> 배송시 요청사항 </th>
						<td colspan = "4"> ${orderDto.delivery_msg} </td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 배송 조회 </th>
					</tr>
					<tr>
						<th style="line-height:40px;"> 택배회사
						    <select class="form-control" name="track_com">
						           <option value="0">회사선택</option>
						           <option value="롯데택배">롯데택배</option>
						           <option value="대한통운">대한통운</option>
						           <option value="우체국택배">우체국택배</option>
							</select>
						</th>     
						<th style="line-height:40px;"> 운송장 번호 
							<c:if test="${orderDto.track_num != 0}">
								<input class = "form-control" type="text" name = "track_num" maxlength="8"
									value="${orderDto.track_num}"> 
							</c:if>
							<c:if test="${orderDto.track_num == 0}">
								<input class = "form-control" type="text" name = "track_num" maxlength="8"> 
							</c:if>
						</th>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 총 결제 금액 </th>		
					</tr>
					<tr>
						<td colspan = "4" style="line-height:40px;">
							<fmt:formatNumber value="${orderDto.price*orderDto.goods_count}"/>
						</td>
					</tr>
					<tr>
						<th colspan = "5" class = "page-header" style="font-size: 20px;"> <br> 결제수단 </th>
					</tr>
					<tr>
						  <c:if test ="${orderDto.pay_method == 0}" >
						  	<td colspan = "4" style="line-height:40px;">카드</td> 
					      </c:if>
					      <c:if test ="${orderDto.pay_method == 1}" >
						    <td colspan = "4" style="line-height:40px;">무통장 입금</td>  
					      </c:if>
					</tr>
					<tr>
						<th colspan = "5" style = "text-align: center;">
							<c:if test="${sessionScope.memId == 'admin'}">
							  <input class ="btn btn-info" type ="submit" value ="수정">
							</c:if>
							<input class = "btn btn-default" type = "button" value = "목록" 
							 onclick = "location='orderList.do'">
						</th>
						
					</tr>
					</c:forEach>	
				</table>
				</form>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>