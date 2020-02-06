/* 후기게시판 */
var goods_nameerror ="상품명을 입력하세요";
var subjecterror = "글제목을 입력하세요";
var contenterror = "글내용을 입력하세요";
var passwderror = "비밀번호를 입력하세요";
var notmembererror = "로그인을 해주세요";

var writeerror = "글작성에 실패했습니다. 잠시 후 다시 시도하세요.";
var iderror = "본인이 작성한 글이 아니면 삭제할 수 없습니다.";
var modifyerror = "글수정에 실패했습니다. 잠시 후 다시 시도하세요.";
var deleteerror = "글삭제에 실패했습니다. 잠시 후 다시 시도하세요.";
var replyerror = "답글이 있는 글은 삭제할 수 없습니다.";

function erroralert( msg ){
	alert( msg );
	history.back();
}

/* 로그인 상태 확인 */
function rmembercheckRe(memId, pageNum, goods_name, num, ref, re_step, re_level) {
	   if( ! memId ) {
	      alert( notmembererror );
	      return false;      
	 }
	 location.replace("reviewWriteForm.do?pageNum="+pageNum+"&goods_name="+goods_name+"&num="+num+"&ref="+ref+"&re_step="+re_step+"&re_level="+re_level);
}

function rmembercheck(memId) {
	if( ! memId ) {
		alert( notmembererror );
		return false;
	}
	location.replace("reviewWriteForm.do");
}

//리스트검색
function rsearchcheck(){
	if( ! searchform.keyword.value ){
		alert( keyworderror );
		searchform.keyword.focus();
		return false;
	}
}

//글수정
function rmodifyfocus(){
	modifyform.subject.focus();
}
function rmodifycheck(){
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
function rwritefocus(){
	writeform.goods_name.focus();
}
function rwritecheck(){
	if( writeform.goods_name.value == 0 ){
		alert( goods_nameerror);
		writeform.goods_name.focus();
		return false;
	} else if( ! writeform.subject.value ){
		alert( subjecterror );
		writeform.subject.focus();
		return false;
	} else if( ! writeform.content.value ){
		alert( contenterror );
		writeform.content.focus();
		return false;
	} 	
}

function rcheckadmin(memid) {
	if(memid == "admin") {
		return true;
	}
}