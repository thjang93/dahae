<%@page import="board.BoardDataBean"%>
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
	<body onload="g_modifyfocus()">
		<div id="wrapper">
			<div id="header">
				<c:import url = "/main/header.jsp"/>
			</div>
			<div id="main">
				<c:if test="${result == 0}">
					<script type="text/javascript">
						<!--
							alert( dbderror );
						//-->
					</script>
						<meta http-equiv="refresh" content="0; url=goodsListM.do?pageNum=${pageNum}">
				</c:if>
				<div align="center">
					<b style="font-size: 30px;">${page_modify}</b>
				</div>
				<label style = "font-size:20px;"> &nbsp; </label>
				<c:if test="${result != 0}">
					<div class="container">			
						<form method="post" action="goodsModifyPro.do" enctype="multipart/form-data"
							name="modifyform" onsubmit="return g_modifycheck()">		
						    <input type="hidden" name="num" value="${goodsDto.goods_number}">
							<input type="hidden" name="pageNum" value="${pageNum}">
							<table class="table table-stroped table-bordered table-hover" 
								style = "margin-right:auto; margin-left:auto; width: 800px; font-size: 15px;">
								<tr>
									<th style="text-align:center;">${str_goods_name}</th>
									<td colspan="2" style="text-align:left;">
										<input class="form-control" type="text" name="goods_name" value="${goodsDto.goods_name}">
									</td>
								</tr>
								<tr>
									<th style="text-align:center;">${str_category_name} </th>
									<td colspan="2">
										<select class="form-control" name="category_name" style="width:auto;">
											<c:set var="selected" value="selected"/>
											<c:if test="${goodsDto.category_name == '간식'}">
												<option value="0">카테고리 변경을 원하시면 선택해주세요</option>
												<option value="간식" selected>간식</option>
												<option value="식단">식단</option>
												<option value="건강식품">건강식품</option>
												<option value="헬스케어/운동기구">헬스케어/운동기구</option>
											</c:if>
											<c:if test="${goodsDto.category_name == '식단'}">
												<option value="0">카테고리 변경을 원하시면 선택해주세요</option>
												<option value="간식">간식</option>
												<option value="식단" selected>식단</option>
												<option value="건강식품">건강식품</option>
												<option value="헬스케어/운동기구">헬스케어/운동기구</option>
											</c:if>
											<c:if test="${goodsDto.category_name == '건강식품'}">
												<option value="0">카테고리 변경을 원하시면 선택해주세요</option>
												<option value="간식">간식</option>
												<option value="식단">식단</option>
												<option value="건강식품" selected>건강식품</option>
												<option value="헬스케어/운동기구">헬스케어/운동기구</option>
											</c:if>
											<c:if test="${goodsDto.category_name == '헬스케어/운동기구'}">
												<option value="0">카테고리 변경을 원하시면 선택해주세요</option>
												<option value="간식">간식</option>
												<option value="식단">식단</option>
												<option value="건강식품">건강식품</option>
												<option value="헬스케어/운동기구" selected>헬스케어/운동기구</option>
											</c:if>	
										</select>	
									</td>
								</tr>
								<tr>
									<th style="text-align:center;"> ${str_goods_info}</th>
									<td colspan="2">
										<textarea class="form-control" rows="5" cols="64" name="goods_info">${goodsDto.goods_info}</textarea>
									</td>
								</tr>
								<tr>
									<th style="text-align:center;"> ${str_image}</th>
									<td style="width:380px;">
										<input type="file" name="image" >
									</td>
									<td style="text-align:left;">
										현재이미지 : ${goodsDto.goods_image}
									</td>
								</tr>
								<tr>
									<th style="text-align:center;">${str_price}</th>
									<td colspan="2">
										<input class="form-control" type="text" name="price" value="${goodsDto.price}"
											style="width : 100px">
									</td>
								</tr>
								<tr>
									<th style="text-align:center;"> ${str_specimg1}</th>
									<td>
										<input type="file" name="specimg1">
										<c:if test="${goodsDto.goods_specimg1 == null || goodsDto.goods_specimg1 == 'x'}">
											<td style="text-align:left;"> 현재이미지 : 없음  </td>
										</c:if>
										<c:if test="${goodsDto.goods_specimg1 != null && goodsDto.goods_specimg1 != 'x'}">
											<td style="text-align:left;"> 현재이미지 : ${goodsDto.goods_specimg1}
											<input type="button" value="${btn_del}"
												onclick="location='deleteImage.do?image_what=goods_specimg1&goods_number=${goodsDto.goods_number}&pageNum=${pageNum}'">
											</td>
										</c:if>
									</td>
								</tr>
								<tr>
									<th style="text-align:center;"> ${str_specimg2}</th>
									<td>
										<input type="file" name="specimg2">
										<c:if test="${goodsDto.goods_specimg2 == null || goodsDto.goods_specimg2 == 'x'}">
											<td style="text-align:left;"> 현재이미지 : 없음 </td>
										</c:if>
										<c:if test="${goodsDto.goods_specimg2 != null && goodsDto.goods_specimg2 != 'x'}">
											<td style="text-align:left;"> 현재이미지 : ${goodsDto.goods_specimg2}
												<input type="button" value="${btn_del}" onclick="location='deleteImage.do?image_what=goods_specimg2&goods_number=${goodsDto.goods_number}&pageNum=${pageNum}'">
											</td>
										</c:if>
									</td>
									
								</tr>
								<tr>
									<th style="text-align:center;"> ${str_specimg3}</th>
									<td>
										<input type="file" name="specimg3">
										<c:if test="${goodsDto.goods_specimg3 == null || goodsDto.goods_specimg3 == 'x'}">
											<td style="text-align:left;"> 현재이미지 : 없음 </td>
										</c:if>
										<c:if test="${goodsDto.goods_specimg3 != null && goodsDto.goods_specimg3 != 'x'}">
											<td style="text-align:left;"> 현재이미지 : ${goodsDto.goods_specimg3}
												<input type="button" value="${btn_del}" onclick="location='deleteImage.do?image_what=goods_specimg3&goods_number=${goodsDto.goods_number}&pageNum=${pageNum}'">
											</td>
										</c:if>
									</td>
								</tr>
								<tr>
									<th colspan="3" style="text-align:center;">
										<input class="btn btn-info btn-sm" type="submit" value="${btn_mod}">
										<input class="btn btn-default btn-sm" type="button" value="${btn_mod_cancel}"
											onclick="location='goodsListM.do?pageNum=${pageNum}'">
									</th>
								</tr>
							</table>
						</form>
					</div>
				</c:if>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>