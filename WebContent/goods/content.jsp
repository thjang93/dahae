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
		<script src = "${folder}script.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<div align="center">
	            	<b style="text-align:center; font-size:30px;">${page_content}</b>
	       		</div>
	       		<label style = "font-size:20px;"> &nbsp; </label>
				<table class="table table-bordered"
					style = "margin-right: auto; margin-left: auto; width:800px; font-size:15px;">
					<tr>
						<th style="text-align:center; width:200px;;">${str_goods_name}</th>
						<td> ${goodsDto.goods_name} </td>
					</tr>
					<tr>
						<th style="text-align:center;">${str_category_name}</th>
						<td> ${goodsDto.category_name} </td>
					</tr>
					<tr>
						<th style="text-align:center;"> ${str_goods_info}</th>
						<td> ${goodsDto.goods_info} </td>
					</tr>
					<tr>
						<th style="text-align:center;"> ${str_image} </th>
						<td> ${goodsDto.goods_image} </td>
					</tr>
					<tr>
						<th style="text-align:center;"> ${str_price} </th>
						<td> <fmt:formatNumber value="${goodsDto.price}"/> </td>
					</tr>
					<tr>
						<th style="text-align:center;"> ${str_specimg1}</th>
						<td> ${goodsDto.goods_specimg1} </td>
					</tr>
					<tr>
						<th style="text-align:center;"> ${str_specimg2}</th>
						<td> ${goodsDto.goods_specimg2} </td>
						
					</tr>
					<tr>
						<th style="text-align:center;"> ${str_specimg3}</th>
						<td> ${goodsDto.goods_specimg3} </td>
					</tr>
					<tr>
						<th colspan="2" style="text-align:center;">
							<input class="btn btn-info btn-sm" type="button" value="${btn_modify}"
								onclick="location='goodsModifyForm.do?num=${goodsDto.goods_number}&pageNum=${pageNum}'">
							<input class="btn btn-info btn-sm" type="button" value="${btn_delete}"
								onclick="location='goodsDeleteForm.do?num=${goodsDto.goods_number}&pageNum=${pageNum}'">
							<input class="btn btn-default btn-sm" type="button" value="${btn_prelist}"
								onclick="location='goodsListM.do?pageNum=${pageNum}'">
						</th>
		        	</tr>
				</table>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>