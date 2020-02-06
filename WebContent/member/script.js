/**
 * 	회원관리
 */	

var iderror = "아이디를 입력하세요";
var passwderror = "비밀번호를 입력하세요";
var repasswderror = "비밀번호를 확인해주세요";
var nameerror = "이름을 입력하세요";
var juminerror = "생년월일을 확인하세요";
var telerror = "전화번호를 모두 입력하세요";
var emailerror = "이메일 형식에 맞지 않습니다";
var interror = "생년월일을 숫자로 입력해주세요";
var telinterror = "전화번호를 숫자로 입력해주세요";
var emailnumerror ="인증번호를 입력하세요.";
var inputerror = "회원 가입에 실패했습니다\n잠시 후 다시 시도하세요"; 
var noiderror = "입력하신 아이디가 없습니다\n다시 확인하세요";
var diffpasswderror = "입력하신 비밀번호가 다릅니다\n다시 확인하세요";
var confirmerror = "아이디 중복확인을 해주세요";
var updateerror = "회원 정보 변경에 실패했습니다\n잠시 후 다시 시도하세요";
var remailerror = "인증번호를 확인해주세요";
var verifyerror = "인증확인 버튼을 눌러주세요";
var idselecterror = "입력하신 아이디가 존재하지 않습니다.";
var deleteerror = "회원 탈퇴에 실패했습니다.";

function erroralert( msg ) {
	alert( msg );
	history.back();
}

function m_goback() {
	history.back();
}

function loginfocus() {
	loginform.id.focus();
}

/* 가입 폼 */

//아이디 중복확인
function confirmid() {
	if( ! insertform.id.value ) {
		alert( iderror );
		insertform.id.focus();
		return;
	}

	var url = "confirmId.do?id=" + insertform.id.value;
	open( url, "cofirmwindow", "statusbar=no, scrollbar=no, menubar=no, width=300, height=200" );
}

function confirmfocus() {
	confirmform.id.focus();
}

function confirmcheck() {
	if( ! confirmform.id.value ) {
		alert( iderror );
		confirmform.id.focus();
		return false;
	}
}

function setid( id ) {
	opener.document.insertform.id.value = id;
	self.close();
	opener.document.insertform.confirm.value = id;
	opener.document.insertform.passwd.focus();
}

function inputfocus() {
	insertform.id.focus();
}

function inputcheck() {
	if(insertform.confirm.value != insertform.id.value){
		alert( confirmerror );
		insertform.id.focus();
		return false;
	}

	if( insertform.confirm.value == 0 ) {
		alert( confirmerror );
		insertform.id.focus();
		return false;
	}

	if( ! insertform.id.value ) {
		alert( iderror );
		insertform.id.focus();
		return false;
	} else if( ! insertform.passwd.value ) {
		alert( passwderror );
		insertform.passwd.focus();
		return false;
	} else if( insertform.passwd.value 
			!= insertform.repasswd.value ) {
		alert( diffpasswderror );
		insertform.repasswd.focus();
		return false;
	} else if( ! insertform.name.value ) {
		alert( nameerror );
		insertform.name.focus();
		return false;
	} else if( insertform.birth_date.value.length < 6 ) {
		alert( juminerror );
		insertform.birth_date.focus();
		return false;	
	}else if( insertform.tel1.value 
			|| insertform.tel2.value 
			|| insertform.tel3.value ) {
		if( insertform.tel1.value.length < 3 
				|| insertform.tel2.value.length < 3 
				|| insertform.tel3.value.length < 4 ) {
			alert( telerror );
			insertform.tel1.focus();
			return false;
		}

	} else if(insertform.birth_date.value !=0){
		var string = insertform.birth_date.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(interror);
				insertform.birth_date.focus();
				return false;
				break;
			}
		}

	} else if(!insertform.repasswd.value) {
		alert(repasswderror);
		insertform.repasswd.focus();
		return false;
	}

	if(insertform.tel1.value !=0){
		var string = insertform.tel1.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(telinterror);
				insertform.tel1.focus();
				return false;
				break;
			}
		}
	}

	if(insertform.tel2.value !=0){
		var string = insertform.tel2.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(telinterror);
				insertform.tel2.focus();
				return false;
				break;
			}
		}
	}

	if(insertform.tel3.value !=0){
		var string = insertform.tel3.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(telinterror);
				insertform.tel3.focus();
				return false;
				break;
			}
		}
	}

	if(insertform.email1.value != 0 && insertform.emailnum.value == 0){
		alert(emailnumerror);
		insertform.emailnum.focus();
		return false;
	}else if(insertform.authnum.value != insertform.emailnum.value){
		alert(remailerror);
		insertform.emailnum.focus();
		return false;
	}

	if(!insertform.email1.value && insertform.email2.value == '0') {
		insertform.verty.value = "0";
	}

	if(insertform.verty.value != "0") {
		alert(verifyerror);
		insertform.email1.focus();
		return false;
	}

	// 이메일 
	// 1. null일 경우			이동
	// 2. 직접입력일 경우		입력창에 @가 없으면 경고
	// 3. 선택이력일 경우		입력창에 @가 있으면 경고	

	// 전화번호가 있을때나 없을때 모두 동일하게 동작해야 한다.
}

function nextjumin2() {
	if( insertform.jumin1.value.length == 6 ) {
		insertform.jumin2.focus();
	}
}

function nexttel1() {
	if( insertform.jumin2.value.length == 7 ) {
		insertform.tel1.focus();
	}
}

function nexttel2() {
	if( insertform.tel1.value.length == 3 ) {
		insertform.tel2.focus();
	}
}

function nexttel3() {
	if( insertform.tel2.value.length == 4 ) {
		insertform.tel3.focus();
	}
}

function nextemail1() {
	if( insertform.tel3.value.length == 4 ) {
		insertform.email1.focus();
	}
}

//이메일 인증 소스
function sendemail(){
	var url = "";
	if( !insertform.email1.value && (insertform.email2.value == "0") ){
		alert( emailerror );
		insertform.email1.focus();
		return;
	}else if( insertform.email1.value.length != 0 ) {
		if( insertform.email2.value == "0" ) {
			// 직접입력
			if( insertform.email1.value.indexOf( "@" ) == -1 ) {
				alert( emailerror );
				insertform.email1.focus();
				return;
			}
			url = "sendEmail.do?email=" + insertform.email1.value;
		} else {
			// 선택입력
			if( insertform.email1.value.indexOf( "@" ) != -1 ) {
				alert( emailerror );
				insertform.email1.focus();
				return;
			}
			url = "sendEmail.do?email=" + insertform.email1.value 
			+ "@" + insertform.email2.value;
		}      
	}	
	open(url,"emailwindow",
	"statusbar=no, scrollbar=no, menubar=no, width=400px, height=200px");
}

//인증번호 확인
function modifyemail() {
	insertform.verty.value = "1";
}

function setAuthNum(){
	opener.document.insertform.authnum.value = sendemailform.authnum.value;
	opener.document.insertform.verty.value = "0";
	self.close();
	opener.document.insertform.emailnum.focus();
}

/* 회원 수정 */
function modiformfocus() {
	mofyform.passwd.focus();
}

function modifyfocus() {
	modifyform.passwd.focus();
}

function modifycheck() {
	if( ! modifyform.passwd.value ) {
		alert( passwderror );
		modifyform.passwd.focus();
		return false;
	} else if( modifyform.passwd.value 
			!= modifyform.repasswd.value ) {
		alert( diffpasswderror );
		return false;
	} else if( !modifyform.repasswd.value ) {
		alert( repasswderror );
		return false;
	}

	if(modifyform.tel1.value !=0){
		var string = modifyform.tel1.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(telinterror);
				modifyform.tel1.focus();
				return false;
				break;
			}
		}
	}

	if(modifyform.tel2.value !=0){
		var string = modifyform.tel2.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(telinterror);
				modifyform.tel2.focus();
				return false;
				break;
			}
		}
	}

	if(modifyform.tel3.value !=0){
		var string = modifyform.tel3.value;
		var len = string.length;
		var str = "0123456789";
		for(i=0;i<len;i++){
			if(str.indexOf(string.charAt(i)) == -1){
				alert(telinterror);
				modifyform.tel3.focus();
				return false;
				break;
			}
		}
	}

	if( modifyform.tel1.value 
			|| modifyform.tel2.value 
			|| modifyform.tel3.value ) {
		if( modifyform.tel1.value.length < 3 
				|| modifyform.tel2.value.length < 3 
				|| modifyform.tel3.value.length < 4 ) {
			alert( telerror );
			modifyform.tel1.focus();
			return false;
		}
	}

	if(modifyform.verty.value != "0") {
		alert(verifyerror);
		modifyform.email1.focus();
		return false;
	}

	if(modifyform.email1.value != 0 && modifyform.emailnum.value === 0){
		alert(emailnumerror);
		modifyform.emailnum.focus();
		return false;
	}else if(modifyform.authnum.value != modifyform.emailnum.value){
		alert(remailerror);
		modifyform.emailnum.focus();
		return false;
	}

	if(!modifyform.email1.value && !modifyform.email2.value) {
		modifyform.verty.value = "0";
	}

	if(modifyform.verty.value != "0") {
		alert(verifyerror);
		modifyform.email1.focus();
		return false;
	}
	
}

function nextttel2(){
	if( modifyform.tel1.value.length == 3 ) {
		modifyform.tel2.focus();
	}
}

function nextttel3(){
	if( modifyform.tel2.value.length == 4 ) {
		modifyform.tel3.focus();
	}
}

function nextttel4(){
	if( modifyform.tel3.value.length == 4 ) {
		modifyform.email1.focus();
	}
}

function mosendemail(){
	if( !modifyform.email1.value && modifyform.email2.value !="0" ){
		alert( emailerror );
		modifyform.email2.focus();
		return false;
	}else if(modifyform.email1.value !="0" && !modifyform.email2.value) {
		alert( emailerror );
		modifyform.email1.focus();
		return false;      
	}else if(modifyform.email1.value !="0" && modifyform.email2.value !="0"){
		var String = modifyform.email1.value + "@" + modifyform.email2.value;
		var url = "mosendEmail.do?email=" + String;
		open(url,"emailwindow",
		"statusbar=no, scrollbar=no, menubar=no, width=400px, height=200px");
	}
}

function setmoAuthNum(){
	opener.document.modifyform.authnum.value = mosendemailform.authnum.value;
	opener.document.modifyform.verty.value = "0";
	self.close();
	opener.document.modifyform.emailnum.focus();
}

function moemail(){
	modifyform.verty.value = "1";
	if(!modifyform.email1.value && !modifyform.email2.value) {
		modifyform.verty.value = "0";
	}
}

/* 관리자 수정 */

//관리자가 회원 아이디삭제
function admindelete(){
	var url = "adminDelete.do";
	open(url,"deletewindow",
	"statusbar=no, scrollbar=no, menubar=no, width=400px, height=130px");	
}

function selete(){
	if(admindelform.id.value == 0){
		alert(iderror);
		admindelform.id.focus();
		return false;
	}
}

//다음 우편 api 사용 스크립트
function addresscheck() {

	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullAddr = ''; // 최종 주소 변수
			var extraAddr = ''; // 조합형 주소 변수

			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				fullAddr = data.roadAddress;

			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				fullAddr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
			if(data.userSelectedType === 'R'){
				//법정동명이 있을 경우 추가한다.
				if(data.bname !== ''){
					extraAddr += data.bname;
				} 
				// 건물명이 있을 경우 추가한다.
				if(data.buildingName !== ''){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
			document.getElementById('sample6_address').value = fullAddr;

			// 커서를 상세주소 필드로 이동한다.
			document.getElementById('sample6_address2').focus();
		}
	}).open();
}
