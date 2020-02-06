<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${folder}script.js"></script>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div align="center">
				<b style="font-size:30px;">${str_notice}</b>
			</div>
			<div id ="list">
				<div align="center">
					<b>( 전체글 : ${count} )</b>
				</div>
			</div>
			<div id ="container">
				<div align="center">
					<c:if test="${id != null}">
					</c:if>      
					<c:if test = "${id == null}">
					</c:if>
				</div>
				<div>
					<c:if test = "${sessionScope.memId == 'admin'}">
						<table style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
							<tr>
								<td style="text-align:right;">
									<input class = "btn btn-default btn-md" type = "button" value = "${str_write}"
										onclick="return nmembercheckRe('${sessionScope.memId}')" 
										style ="font-size:16px;">
								</td>
							</tr>
						</table>
					</c:if>
					<c:if test="${sessionScope.memId != 'admin'}">
						<label style="font-size: 20px;"> &nbsp; </label>
					</c:if>
					<table class="table table-stroped table-bordered table-hover" 
						style = "margin-right: auto; margin-left: auto; width: 1000px; font-size: 15px;">
						<thead>
							<tr>
								<th width="6%" style ="text-align:center;">${str_number}</th>
								<th width="50%" style ="text-align:center;">${str_subject}</th>
								<th width="10%" style ="text-align:center;">${str_id}</th>
								<th width="20%" style ="text-align:center;">${str_reg_date}</th>
								<th width="6%" style ="text-align:center;">${str_readcount}</th>          
							</tr>
						</thead>
						<tbody>
							<c:if test="${count != 0 }">
								<c:forEach var="impDto" items="${notice}">
									<!-- 제대로 글을 가져오는지 확인해야함  -->
									<tr class="danger">
										<td align ="center">${str_impnumber}</td>				
										<td style="cursor:pointer;" 
											onclick="location='noticeContent.do?pageNum=${pageNum}&num=${impDto.n_number}&n_number=${n_number+1}'">
												${impDto.n_subject}		   		
											<c:if test="${impDto.n_readcount gt 20}">
												<img src="${folder}/images/hot.gif" border="0" width="20" height="15">
											</c:if>					
										</td>
										<td align ="center"> <b> ${impDto.id} </b> </td>
										<td align ="center">
											<fmt:formatDate value="${impDto.n_reg_date}" type="both" pattern="yyyy-MM-dd HH:mm"/>
										</td>		
										<td align ="center">${impDto.n_readcount}</td>			
									</tr>
								</c:forEach>
								<c:forEach var="boardDto" items="${articles}">
									<!-- 제대로 글을 가져오는지 확인해야함  -->
									<tr>
										<td align ="center">
											${number} <!-- 출력할 때 번호를 새로 계산해서 출력할 수 있게 함. 깔끔 -->
											<c:set var="number" value="${(number-1)}"/>
										</td>
										<td onclick="location='noticeContent.do?pageNum=${pageNum}&num=${boardDto.n_number}&n_number=${n_number+1}'" style="cursor:pointer;">
												${boardDto.n_subject}														   		
													<c:if test="${boardDto.n_readcount gt 20}">
														<img src="${project}notice/images/hot.gif" border="0" width="20" height="15">
													</c:if>			
										</td>												
										<td align ="center"> <b> ${boardDto.id} </b> </td>				
										<td align ="center">
											<fmt:formatDate value="${boardDto.n_reg_date}" type="both" pattern="yyyy-MM-dd HH:mm"/>
										</td>	
										<td align ="center">${boardDto.n_readcount}</td>			
									</tr>
								</c:forEach>      
							</c:if>
						</tbody>
					</table>
					<div id="paging"  style ="text-align:center;">
						<c:if test="${count gt 0}">
							<c:if test="${startPage gt pageBlock}">
								<a href="noticeList.do">[◀◀]</a>
								<a href="noticeList.do?pageNum=${startPage-pageBlock}">[◀]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i == currentPage}">
									<span>[${i}]</span>
								</c:if>	
								<c:if test="${i != currentPage}">	
									<a href="noticeList.do?pageNum=${i}">[${i}]</a>
								</c:if>
							</c:forEach>
							<c:if test="${pageCount gt endPage}">
								<a href="noticeList.do?pageNum=${startPage+pageBlock}">[▶]</a>
								<a href="noticeList.do?pageNum=${pageCount}">[▶▶]</a>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">
			<c:import url = "/main/footer.jsp"/>
		</div>
	</div>
</body>
</html>