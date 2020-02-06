/* 장바구니 */

//cartForm.jsp에서
//수량 늘이고, 줄이고

//putIn.jsp장바구니 확인창에서
//장바구니로 갈래요
function go_cart(){
	self.close();
	opener.location.href="cartForm.do";
}

//쇼핑 계속할래요 창닫고 이동
function con1(){
	self.close();
}

//쇼핑 계속할래요 메인으로 이동
function con2(){
	location.replace("index.do");
}
