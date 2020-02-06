<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardDataBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDBBean"%>
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
				<div align="center">
					<b style="font-size: 30px;">${page_listM}</b>
				</div>
				<div id="list">
					<div align="center">
						<b>( ${str_count} : ${count} )</b>
					</div>
				</div>
				</div>
				
				<table style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
					<tr>
						<td style="text-align:right;">
							<c:if test="${pageNum == null}">
		                        <input class="btn btn-default btn-md" type="button" value="${str_write}"
		                           onclick="location='goodsWriteForm.do?pageNum=${1}'"
		                           style ="font-size:16px;">
                     		</c:if>
		                     <c:if test="${pageNum != null}">
		                        <input class="btn btn-default btn-md" type="button" value="${str_write}"
		                           onclick="location='goodsWriteForm.do?pageNum=${pageNum}'"
		                           style ="font-size:16px;">
		                     </c:if>
						</td>
					</tr>
				</table>
				<table class="table table-stroped table-bordered table-hover" 
					style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
					<thead>
					<tr>
						<th style="width:5%; text-align:center;"> ${str_num}</th>               <%-- 글번호 --%> 
						<th style="width:20%; text-align:center;"> ${str_goods_name}</th>        <%-- 상품명 --%> 
						<th style="width:8%; text-align:center;"> ${str_category_name}</th>     <%-- 카테고리명 --%>
						<th style="width:15%; text-align:center;"> ${str_image}</th>             <%-- 상품이미지 --%>
						<th style="width:10%; text-align:center;"> ${str_price}</th>             <%-- 판매액 --%>
						<th style="width:15%; text-align:center;"> ${str_reg_date}</th>          <%-- 등록일 --%>
						<th style="width:10%; text-align:center;"> ${str_sell_count}</th>        <%-- 판매량 --%>
					</tr>
					</thead>
					<tbody>
					<c:if test="${count == 0}">
						<tr>
							<td colspan="7" align="center">
								${msg_list_x}
							</td>
						</tr>
					</c:if>					
					<c:if test="${count != 0}">
						<%-- 글이 있는 경우 : 해당 페이지에 있는 글10개만 가져오기위해 start와 end를 매개변수로 준다.  --%>
						<%-- ArrayList <BoardDataBean> articles 
							= (ArrayList<BoardDataBean>) request.getAttribute("articles");
						for( int i = 0; i<articles.size(); i++ ){
							BoardDataBean boardDto = articles.get(i);
							--%>
						<%--Handler에서 던져준 articles를 el태그로 받는다 --%>
						<c:forEach var="goodsDto" items="${somegoods}" varStatus="status">	
							<tr onclick="location='goodsContent.do?pageNum=${pageNum}&num=${goodsDto.goods_number}'" 
								style="cursor:pointer;">
								<td align="center">
									${number}
									<c:set var="number" value="${number-1}"/>
								</td>
								<td>
									<b> ${goodsDto.goods_name} </b>
								</td>
								<td align = "center">
									${goodsDto.category_name}
								</td>
								<td align = "center">
									${goodsDto.goods_image}
								</td>
								<td>
									<fmt:formatNumber value="${goodsDto.price}"/>
								</td>
								<td align = "center">
									<fmt:formatDate value="${goodsDto.goods_reg_date}" type="both"
										pattern="yyyy-MM-dd HH:mm"/>	
								</td>
								<td align = "center">
									<fmt:formatNumber value="${goodsDto.sell_count}"/>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
				<div id="paging"  style ="text-align:center;">
					<c:if test="${count gt 0}">
						<c:if test="${startPage gt pageBlock}">
							<a href="goodsListM.do?pageNum=${1}">[◀◀]</a>
							<a href="goodsListM.do?pageNum=${startPage-pageBlock}">[◀]</a>
						</c:if>
						
							<%--
						}		
						for( int i = startPage; i<=endPage; i++){
							if( i == currentPage ) {
							--%>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${i == currentPage}">
								<span>[${i }]</span>
								<%--
							}else{
								--%>
							</c:if>
							<c:if test="${i != currentPage}">
								<a href="goodsListM.do?pageNum=${i}">[${i }]</a>
							</c:if>
						</c:forEach>
								<%--	
							}
						}
						if( pageCount > endPage ){ //뒤에 페이지가 있다는 뜻
							--%>
						<c:if test="${pageCount gt endPage}">
							<a href="goodsListM.do?pageNum=${startPage+pageBlock }">[▶]</a>
							<a href="goodsListM.do?pageNum=${pageCount}">[▶▶]</a>
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