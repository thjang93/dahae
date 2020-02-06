/**
 * 문의 게시판
 */

var writererror = "작성자를 입력하세요";
var subjecterror = "글제목을 입력하세요";
var contenterror = "글내용을 입력하세요";
var passwderror = "비밀번호를 입력하세요";
var notmembererror = "로그인을 해주세요";

var writeerror = "글작성에 실패했습니다. 잠시 후 다시 시도하세요.";
var iderror = "본인이 작성한 글이 아니면 삭제할 수 없습니다.";
var iderror2 = "본인이 작성한 글이 아니면 수정할 수 없습니다.";
var modifyerror = "글수정에 실패했습니다. 잠시 후 다시 시도하세요.";
var deleteerror = "글삭제에 실패했습니다. 잠시 후 다시 시도하세요.";
var replyerror = "댓글이 있는 글은 삭제할 수 없습니다.";

function erroralert( msg ) {
	alert( msg );
	history.back();
}

/* 로그인 상태 확인 */
function qa_membercheckRe(memId, pageNum, num, ref, re_step, re_level) {
	if( ! memId) {
		alert( notmembererror );
		return false;		
	}
	location.replace("qaWriteForm.do?pageNum="+pageNum+"&num="+num+"&ref="+ref+"&re_step="+re_step+"&re_level="+re_level);
}

function qa_membercheck(memId, pageNum) {
	if( ! memId ) {
		alert( notmembererror );
		return false;
	}
	location.replace("qaWriteForm.do?pageNum="+pageNum);
}

//리스트검색
function qa_searchcheck(){
	if( ! searchform.keyword.value ){
		alert( keyworderror );
		searchform.keyword.focus();
		return false;
	}
}

//글수정
function qa_modifyfocus(){
	modifyform.subject.focus();
}
function qa_modifycheck(){
	if( ! modifyform.subject.value ){
		alert( subjecterror );
		modifyform.subject.focus()
		return false;
	}else if( !modifyform.content.value ){
		alert( contenterror );
		modifyform.content.focus();
		return false;
	}
}

//글쓰기
function qa_writefocus() {
	writeform.subject.focus();
}

function qa_writecheck() {
	if( ! writeform.subject.value ) {
		alert( subjecterror );
		writeform.subject.focus();
		return false;
	} else if( ! writeform.content.value ) {
		alert( contenterror );
		writeform.content.focus();
		return false;
    }
}