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
	<body onload="g_writefocus()">
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<div align="center">
					<b style="font-size: 30px;">${page_write} <br> </b>
				</div>
				<label style = "font-size:20px;"> &nbsp; </label>
				<div class="container">
					<form method="post" action="goodsWritePro.do" enctype="multipart/form-data" 
						name="writeform" onsubmit="return g_writecheck()">						
						<input type = "hidden" name = "pageNum" value = "${pageNum}">
						<input type = "hidden" name = "goods_number" value = "${goods_number}">
						<table class="table table-stroped table-bordered table-hover" 
							style = "margin-right:auto; margin-left:auto; width: 800px; font-size: 15px;">
							<tr>
								<th style="text-align:center;">${str_goods_name}</th>
								<td style="text-align:left;">
									<input class="form-control" type="text" name="goods_name">
								</td>
							</tr>
							<tr>
								<th style="text-align:center;">${str_category_name}</th>
								<td>
									<select class="form-control" name="category_name" style="width:auto;">
										<option value="0">카테고리를 선택해주세요</option>
										<option value="간식">간식</option>
										<option value="식단">식단</option>
										<option value="건강식품">건강식품</option>
										<option value="헬스케어/운동기구">헬스케어/운동기구</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="text-align:center;"> ${str_goods_info}</th>
								<td>
									<textarea class="form-control" rows="5" cols="64" name="goods_info"></textarea>
								</td>
							</tr>
							<tr>
								<th style="text-align:center;"> ${str_image}</th>
								<td>
									<input type="file" name="image" style="width : 380px">
								</td>
							</tr>
							<tr>
								<th style="text-align:center;">${str_price}</th>
								<td>
									<input class="form-control" type="text" name="price"
										style="width : 100px">
								</td>
							</tr>
							<tr>
								<th style="text-align:center;"> ${str_specimg1}</th>
								<td>
									<input type="file" name="specimg1" style="width : 400px">
								</td>
							</tr>
							<tr>
								<th style="text-align:center;"> ${str_specimg2}</th>
								<td>
									<input type="file" name="specimg2" style="width : 400px">
								</td>
								
							</tr>
							<tr>
								<th style="text-align:center;"> ${str_specimg3}</th>
								<td>
									<input type="file" name="specimg3" style="width : 400px">
								</td>
							</tr>
							<tr>
								<th colspan="2" style="text-align:center;">
									<input class="btn btn-info btn-sm" type="submit" value="${btn_write}">
									<input class="btn btn-default btn-sm" type="button" value="${btn_list}"
										onclick="location='goodsListM.do?pageNum=${pageNum}'">
								</th>
							</tr>
						</table>		
					</form>
				</div>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>