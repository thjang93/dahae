<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="${folder}script.js"></script>
	<meta name="viewport" content="width = device-width" , initial-scale="1">
	<link rel="stylesheet" href="${project}css/bootstrap.css" type="text/css">
	<link rel="stylesheet" href="${project}css/style.css" type="text/css">
</head>
<body onload="inputfocus()">
	<div id="wrapper">
		<div id="header">
			<c:import url="/main/header.jsp" />
		</div>
		<div id="main">
			<div class="container">
				<div align="center">
					<b style="font-size:30px;">회원가입</b> <br>
					<b style="font-size:15px;">회원정보를 입력하세요.</b>
				</div>
				<label style = "font-size:20px;"> &nbsp; </label>
				<div style="margin-left: auto; margin-right: auto; width: 430px;">
					<form name="insertform" method="post" action="inputPro.do"
						onsubmit="return inputcheck()">
						<input type="hidden" name="authnum" value=""> <input
							type="hidden" name="verty" value="0"> <input
							type="hidden" name="confirm" value="0">
						<div class="form-group">
							<div class="form-group">
								<label for="Inputid"> * ${str_id} </label>
								<div class="form-inline">
									<input type="text" class="form-control" id="InputId" name="id"
										placeholder="아이디"> <input class="btn btn-info btn-sm"
										type="button" value="${btn_confirm}" onclick="confirmid()">
								</div>
							</div>
							<div class="form-group">
								<label for="InputPassword1">* ${str_passwd} </label> <input
									type="password" name="passwd" class="form-control"
									id="InputPassword1" placeholder="비밀번호">
							</div>
							<div class="form-group">
								<label for="InputPassword2"> * ${str_passwds}</label> <input
									type="password" class="form-control" id="InputPassword2"
									placeholder="비밀번호 확인" name=repasswd>
								<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해주세요</p>
							</div>
							<div class="form-group">
								<label for="username"> * ${str_name} </label> <input type="text"
									name="name" class="form-control" id="username"
									placeholder="이름을 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="userjumin"> * ${str_jumin}</label> 
								<input type="text" name="birth_date" class="form-control"
									id="userjumin" onkeyup="nexttel1" placeholder="${msg_int}"
									maxlength="6">
								<p class="help-block">ex)19940101은 940101로 입력해주세요</p>
							</div>
							<div class="form-group">
								<div class="form-inline">
									<label for="tel">${str_tel}</label> <input
										class="form-control" type="text" name="tel1" maxlength="3"
										style="width: 60px" onkeyup="nexttel2()"> - <input
										class="form-control" type="text" name="tel2" maxlength="4"
										style="width: 60px" onkeyup="nexttel3()"> - <input
										class="form-control" type="text" name="tel3" maxlength="4"
										style="width: 60px" onkeyup="nextemail1()">
								</div>
							</div>
							<label for="sample6_postcode">${str_zipcode}</label>
							<div class="form-group form-inline">
								<input type="text" class="form-control"
									id="sample6_postcode" name="sample6_postcode" readonly>
								<input class="btn btn-info btn-sm" type="button"
									onclick="addresscheck()" value="${btn_code_search}">
							</div>
							<div class="form-group">
								<label for="sample6_address">${str_address}</label>
								<input type="text" class="form-control" name="sample6_address"
									id="sample6_address" readonly>
								<p style="font-size: 3px;"></p>
								<input type="text" class="form-control" id="sample6_address2"
									name="sample6_address2" placeholder="추가할 주소를 입력해주세요">
							</div>
							<div class="form-group">
								<div class="form-inline">
									<label for="email">${str_email}&nbsp; </label> 
									<input class="form-control" type="text" name="email1" maxlength="100"
										style="width: 160px" onchange="modifyemail()"> @ 
									<select class="form-control" name="email2" onchange="modifyemail()">
										<option value="0">직접입력</option>
										<option value="naver.com">네이버</option>
										<option value="daum.net">다음</option>
										<option value="nate.com">네이트</option>
										<option value="gmail.com">구글</option>
									</select> 
									<input class="btn btn-warning btn-sm" type="button"
										value="${btn_num_check}" onclick="sendemail()">
								</div>
							</div>
							<div class="form-group">
								<div class="form-inline">
									<label for="emailnum">${str_emailnum}</label> 
									<input class="form-control" type="text" name="emailnum" maxlength="7"
										placeholder="인증번호를 입력해 주세요">
								</div>
							</div>
							<div class="form-group text-center" style="magin-top: 10px;">
								<input class="btn btn-info btn-sm" type="submit" value="${btn_input}">
								<c:if test = "${sessionScope.memId == 'admin'}">
									<input class="btn btn-default btn-sm" type="button" value="${btn_input_cancel}"
										onclick="location='memberList.do'">
								</c:if>
								<c:if test = "${sessionScope.memId != 'admin'}">
									<input class="btn btn-default btn-sm" type="button" value="${btn_input_cancel}"
										onclick="location='loginForm.do'">
								</c:if>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="footer">
			<c:import url="/main/footer.jsp" />
		</div>
	</div>
</body>
</html>
