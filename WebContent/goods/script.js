/* 상품 */
var goods_nameerror ="상품명을 입력하세요";
var category_nameerror = "카테고리를 선택하세요";
var imageerror = "이미지파일을 선택해주세요";
var priceerror = "가격을 입력하세요";
var priceerror2 = "판매액은 숫자만 입력 가능합니다";

var writeerror = "글작성에 실패했습니다. 잠시 후 다시 시도하세요.";
var dberror = "점검중입니다. 잠시 후 다시 시도하세요."
var modifyerror = "글수정에 실패했습니다. 잠시 후 다시 시도하세요.";
var deleteerror = "글삭제에 실패했습니다. 잠시 후 다시 시도하세요.";
var replyerror = "답글이 있는 글은 삭제할 수 없습니다.";


function erroralert( msg ){
	alert( msg );
	history.back();
}

function g_modifyfocus(){
	modifyform.goods_name.focus();
}

function g_modifycheck(){
	if( ! modifyform.goods_name.value ){
		alert( goods_nameerror );
		modifyform.goods_name.focus()
		return false;
	}else if( modifyform.category_name.value == "0" || !modifyform.category_name.value ){
		alert( category_nameerror );
		modifyform.category_name.focus();
		return false;
	}else if( !modifyform.price.value ){
		alert( priceerror );
		modifyform.price.focus();
		return false;
	}else if(modifyform.price.value !=0){
	      var string = modifyform.price.value;
	      var len = string.length;
	      var str = "0123456789";
	         for(i=0;i<len;i++){
	            if(str.indexOf(string.charAt(i)) ==-1){
	               alert(priceerror2);
	               modifyform.price.focus();
	               return false;
	               break;
	            }
	         }
	   }
}

//글쓰기
function g_writefocus(){
	writeform.goods_name.focus();
}

function g_writecheck(){
	if( !writeform.goods_name.value ){
		alert( goods_nameerror );
		writeform.goods_name.focus();
		return false;
	} else if( writeform.category_name.value == "0" || !writeform.category_name.value ){
		alert( category_nameerror );
		writeform.category_name.focus();
		return false;
	} else if( ! writeform.image.value || writeform.image.value =="0" ){
		alert( imageerror );
		writeform.image.focus();
		return false;
	} else if( !writeform.price.value ){
		alert( priceerror );
		writeform.price.focus();
		return false;
		//숫자만입력가능
	}   if(writeform.price.value !=0){
	      var string = writeform.price.value;
	      var len = string.length;
	      var str = "0123456789";
	         for(i=0;i<len;i++){
	            if(str.indexOf(string.charAt(i)) ==-1){
	               alert(priceerror2);
	               writeform.price.focus();
	               return false;
	               break;
	            }
	         }
	   }
}

//goodsView에서 상품갯수 늘리고, 줄이기
function g_add(){
	goodViewForm.goods_count.value 
		= eval(goodViewForm.goods_count.value) + 1;
}
function g_cha(){
	if(eval(goodViewForm.goods_count.value) == 1){
		
	}else{
		goodViewForm.goods_count.value
			= eval(goodViewForm.goods_count.value) - 1;
	}
}

//장바구니에 담고 장바구니 창열기
function g_membercheckCart(memId, goods_number) {
	if(!memId) {
		alert( notmembererror );
		return false;
	}
	var popupX = (window.screen.width / 2) - (200 / 2);
	var popupY= (window.screen.height /2) - (300 / 2);
	var goods_count = goodViewForm.goods_count.value;
	var url = "putIn.do?goods_number=" + goods_number+"&goods_count="+goods_count;
	open( url, "cartwindow", "statusbar=no, scrollbar=no, menubar=no, width=350, height=300"
			+ "left="+ popupX + ", top="+ popupY + ", screenX="+ popupX + ", screenY= "+ popupY);
}

/* 로그인 상태 확인 */
function g_membercheck(memId, goodsNum) {
	if(!memId) {
		alert( notmembererror );
		return false;
	}
	var goods_count = goodViewForm.goods_count.value;
	location.replace("orderForm.do?goodsNum=" + goodsNum + "&goodsCount=" + goods_count);
}
