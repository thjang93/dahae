/* 메인화면  */

var notmembererror = "로그인 해주세요";
var keyworderror = "검색어를 입력해주세요";

/* 로그인 상태 확인 */
function logincheck(memId) {
	if(!memId) {
		alert( notmembererror );
		return false;
	}
}

function hsearchcheck() {
	if(!mainsearchform.keyword.value) {
		alert( keyworderror );
		return false;
	}
}