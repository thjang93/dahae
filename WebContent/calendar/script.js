/*에러메세지*/
var writeerror = "일정을 입력해주세요.";

var inputerror = "일정 등록에 실패했습니다\n잠시 후 다시 시도하세요"; 
var modifyerror = "일정 수정에 실패했습니다\n잠시 후 다시 시도하세요"; 
var deleteerror = "일정 삭제에 실패했습니다\n잠시 후 다시 시도하세요"; 

function erroralert( msg ) {
	alert( msg );
	history.back();
}

//cdInputForm 일정추가하기
function inputcheck(){
	if( !inputform.breakfast.value 
		&& !inputform.lunch.value
		&& !inputform.dinner.value
		&& !inputform.ex1.value 
		&& !inputform.ex2.value
		&& !inputform.ex3.value){
		alert(writeerror);
		return false;
	}
}
//취소버튼 눌렀을 때, 창닫기 input,modify
function inputcancel(){
	self.close();
}
//새창 열었을 때 포커스주기input,modify
function inputfocus(){
	inputform.breakfast.focus();
}

//cdForm에서 cdInputForm 열기
function openInputDay(day){
	var year = changeForm.year.value;
	var month = changeForm.month.value;
	var url = "cdInputForm.do?year="+year+"&month="+month+"&day="+day;
	open ( url, "cdwindow", "statusbar=no, scrollbar=no, menubar=no, width=400, height=750" );
}

//cdForm 달력폼 관련
function selectCheck(){
	changeForm.submit();
}
function monthDown(){
	 if(changeForm.month.value>1){
		 changeForm.month.value--;
	 }else {
		 changeForm.month.value=12;
		 changeForm.year.value--;
	 }
	 changeForm.submit();
}
function monthUp(){
	 if(changeForm.month.value<12){
		 changeForm.month.value++;
	 }else {
		 changeForm.month.value=1;
		 changeForm.year.value++;
	 }
	 changeForm.submit();
}