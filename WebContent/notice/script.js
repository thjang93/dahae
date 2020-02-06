/**
 * 게시판
 */
var writererror = "작성자를 입력하세요";
var subjecterror = "글제목을 입력하세요";
var contenterror = "글내용을 입력하세요";
var passwderror = "비밀번호를 입력하세요";
var notadmin = "관리자만 등록할 수 있습니다."
var writeerror = "글작성에 실패했습니다. 잠시 후 다시 시도하세요.";
var imperror = "중요공지는 3개까지 공지할 수 있습니다.\n중요공지글을 하나 삭제한 후 다시 시도하세요.";
var repasswderror = "입력하신 비밀번호가 다릅니다.\n다시 확인하세요.";
var modifyerror = "글 수정에 실패했습니다. \n잠시후 다시 시도하세요.";
var deleteerror = "글삭제에 실패했습니다. \n잠시 후 다시 시도하세요.";
var replyerror = "댓글이 있는 글은 삭제할 수 없습니다.";

function erroralert(msg){
	alert(msg);
	history.back();
}

/* 관리자 확인 */
function nadmincheckRe(memId) {
	if(!memId ) {
		alert( notmembererror );
		return false;		
	}
	if(memId != "admin") {
		alert( notadmin );
		return false;
	}
}

/* 로그인 상태 확인 */
function nmembercheckRe(memId) {
	if(! memId ) {
		alert( notmembererror );
		return false;		
	}
	if(memId != "admin") {
		alert( notadmin );
		return false;
	}
	location.replace("noticeWriteForm.do");
}

function nmodifyfocus(){
	modifyform.subject.focus()
}
function nmodicheck(){
	if(! modiform.subject.value){
		alert(subjecterror);
		modiform.subject.focus();
		return false;
	}else if(! modiform.content.value){
		alert(contenterror);
		modiform.content.focus();
		return false;
	}
}

//글작성
function nwritefocus() {
	no_writeform.subject.focus();
}
function nwritecheck() {
	if( ! no_writeform.subject.value ) {
		alert( subjecterror );
		no_writeform.subject.focus();
		return false;
	} else if( ! no_writeform.content.value ) {
		alert( contenterror );
		no_writeform.content.focus();
		return false;
	} else if( ! no_writeform.passwd.value ) {
		alert( passwderror );
		no_writeform.passwd.focus();
		return false;
	}
}

