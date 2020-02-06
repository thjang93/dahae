<%@page import="java.text.SimpleDateFormat"%>
<%@page import="member.LogonDataBean"%>
<%@page import="member.LogonDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="${folder}script.js"></script>
	<meta name = "viewport" content = "width = device-width", initial-scale = "1">
	<link rel = "stylesheet" href = "${project}css/bootstrap.css" type="text/css">
	<link rel = "stylesheet" href = "${project}css/style.css" type="text/css">
</head>
<body onload="modifyfocus()">
	<div id="wrapper">
		<div id="header">
			<c:import url = "/main/header.jsp"/>
		</div>
		<div id="main">
			<div class="container">
				<div align="center">
					<b style="font-size:30px;">${page_modify}</b> <br>
					<b style="font-size:15px;">${msg_modify}</b>
				</div>
				<label style = "font-size:20px;"> &nbsp; </label>
				<c:if test="${result == 1}">
					<div style="margin-left: auto; margin-right: auto; width: 430px;">
						<form method="post" action="modifyPro.do"
							name="modifyform" onsubmit="return modifycheck()">
							<input type="hidden" name="verty" value="0">
							<input type="hidden" name="authnum" value="">
							<div class="form-group">
								<div class="form-group">
									<label for="id">${str_id}</label>
									<label>${memberDto.id}</label>
								</div>
								<div class="form-group">
									<label for="InputPassword1">${str_passwd}</label> 
									<input type="password" name="passwd" class="form-control" id="InputPassword1" 
										placeholder="비밀번호" value="${memberDto.passwd}">
								</div>
								<div class="form-group">
									<label for="InputPassword2">${str_passwds}</label> 
									<input class="form-control" type="password" name ="repasswd" id="InputPassword2"
										placeholder="비밀번호 확인" value="${memberDto.getPasswd()}">
									<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해주세요</p>
								</div>
								<div class="form-group">
									<label for="username">${str_name} </label> 
									<label> &nbsp;${memberDto.getName()} </label>
								</div>
								<div class="form-group">
									<label for="userjumin">${str_jumin}</label> 
									<label>${memberDto.getbirth_date()}</label>
								</div>
								<div class="form-group" style="line-height: 400%">
									<div class="form-inline">
										<label for="tel">${str_tel}</label>
										<c:if test="${memberDto.tel == null}">
											<input class="form-control" type="text" name="tel1"
												maxlength="3" style="width: 60px" onkeyup="nextttel2()"> -
											<input class="form-control" type="text" name="tel2"
												maxlength="4" style="width: 60px" onkeyup="nextttel3()"> -
											<input class="form-control" type="text" name="tel3"
												maxlength="4" style="width: 60px" onkeyup="nextttel4()"> 
										</c:if>
										<c:if test="${memberDto.tel != null}">
											<c:set var="t" value="${fn:split(memberDto.tel,'-')}" />
											<input class="form-control" type="text" style="width: 60px"
												name="tel1" maxlength="3" value="${t[0]}" onkeyup="nextttel2()"> -
											<input class="form-control" type="text" style="width: 60px"
												name="tel2" maxlength="4" value="${t[1]}" onkeyup="nextttel3()"> -
											<input class="form-control" type="text" style="width: 60px"
												name="tel3" maxlength="4" value="${t[2]}" onkeyup="nextttel4()"> 
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label for="sample6_postcode"> ${str_zipcode}</label>
									<div class="form-inline">
										<c:if test="${memberDto.zipcode == null }">
											<input class="form-control" type="text" id="sample6_postcode"
												name="sample6_postcode" readonly>
										</c:if>
										<c:if test="${memberDto.zipcode != null }">
											<input class="form-control" type="text" id="sample6_postcode"
												name="sample6_postcode" value="${memberDto.zipcode}"
												readonly>
										</c:if>
										<input class="btn btn-info btn-sm" type="button"
											onclick="addresscheck()" value="${btn_code_search}">
									</div>
								</div>
								<div class="form-group">
									<label for="sample6_address">${str_address}</label>
									<c:if test="${memberDto.address == null }">
										<input type="text" class="form-control" name="sample6_address"
											id="sample6_address" readonly>
										<p style="font-size: 3px;"></p>
										<input type="text" class="form-control" id="sample6_address2"
											name="sample6_address2" placeholder="추가할 주소를 입력해주세요">
									</c:if>
									<c:if test="${memberDto.address != null}">
										<c:set var="a" value="${fn:split(memberDto.address,'/n')}" />
										<input class="form-control" type="text" name="sample6_address"
											id="sample6_address" value="${a[0]}" readonly>
										<p style="font-size: 3px;"></p>
										<input class="form-control" type="text" name="sample6_address2"
											id="sample6_address2" value="${a[1]}">
									</c:if>
								</div>
								<div class="form-group">
									<div class="form-inline">
										<label for="email">${str_email}</label>
										<c:if test="${memberDto.email == null}">
											<input class="form-control" type="text" name="email1"
												maxlength="15" style="width: 130px" onchange="moemail()">
											@ 
											<input class="form-control" type="text" name="email2"
												maxlength="15" style="width: 130px" onchange="moemail()">
										</c:if>
										<c:if test="${memberDto.email != null}">
											<c:set var="e" value="${fn:split(memberDto.email,'@')}"/>
											<input class="form-control" type="text" name="email1"
												maxlength="15" style="width: 130px" value="${e[0]}"
												onchange="moemail()">
											@ 
											<input class="form-control" type="text" name="email2"
												maxlength="15" style="width: 130px" value="${e[1]}"
												onchange="moemail()">
										</c:if>
										<input class="btn btn-warning btn-sm" type="button"
											value="${btn_num_check}" onclick="mosendemail()">
									</div>
								</div>
								<div class="form-group">
									<div class="form-inline">
										<label for="emailnum">${str_emailnum}</label>
										<input class="form-control" type="text" name="emailnum" maxlength="7"
											placeholder="인증번호를 입력해 주세요">
									</div>
								</div>
								<div class="form-group">
										<label for="reg_date">${str_reg_date}</label>
										<fmt:formatDate value="${ memberDto.reg_date}" type="both"
											pattern="yyyy-MM-dd HH:mm" />
								</div>
								<div class="form-group text-center" style="magin-top: 10px;">
									<input class="btn btn-info" type="submit"
										value="${btn_mod}">
									<input class="btn btn-default" type="button"
										value="${btn_mod_cancel}" onclick="location='myPage.do'">
								</div>
							</div>
						</form>
					</div>
				</c:if>
			</div>
				<c:if test="${result != 1}">
					<script type="text/javascript">
						<!--
						erroralert( diffpasswderror );
						//-->
					</script>
				</c:if>
			</div>
			<div id="footer">
				<c:import url = "/main/footer.jsp"/>
			</div>
		</div>
	</body>
</html>