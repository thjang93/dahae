package handler.cart;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDBBean;
import cart.CartDataBean;
import handler.CommandHandler;

public class CartFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		//ListHandler 참고하기
		
		int pageSize = 10;  	//한 페이지에 출력할 상품의 수
//		int pageBlock = 3;		//한번에 보여줄 페이지 수**
		int count = 0;			//글의 갯수**
		String pageNum = null; 	//현재페이지**
		int currentPage = 0;   	//현재페이지 :위의 pageNum과 같고, 연산용으로 쓸 것이다.
		int start = 0;			//현재페이지의 시작 rownum**
		int end = 0;			//현재페이지의 끝 rownum
		int number = 0;			//글번호 계산**
		
		int pageCount = 0 ;		//전체 페이지수**
		int startPage = 0;		//보여줄 첫 페이지**
		int endPage = 0;		//보여줄 끝 페이지**
		
		String id = (String) request.getSession().getAttribute("memId");
		
		CartDBBean cartDao = CartDBBean.getInstance();
		//해당 아이디유저가 장바구니에 넣은 게 있는지 count로 확인.
		count = cartDao.getCartCount(id);
				
		if(count > 0 ){
			//글이 있는 경우
			pageNum = request.getParameter( "pageNum" );
			if( pageNum == null ){
				pageNum = "1" ;
			}
			currentPage = Integer.parseInt( pageNum );
			start = ( currentPage - 1 ) * pageSize + 1;
						//	( 1 - 1 ) * 10 + 1 = 1 
			end = start + pageSize - 1;	
						//	1 + 10 - 1 = 10
			if( end > count ) end = count; 
			
			number = count - ( currentPage - 1 )* pageSize;
			
			pageCount = count / pageSize 
					+ ( count % pageSize > 0 ? 1 : 0 ); //count를 pageSize로 나눴는데 나머지가 남는다면 1
			
			startPage = 1;
			endPage = pageCount;
			//페이지 번호에 ◀◀◀ ▶▶▶ 안 넣을 거임.
		} 
		
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("number", number);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		if( count != 0){
			//로그인한 사람이 장바구니에 넣었던 것을 가져옴.
			ArrayList<CartDataBean> someGoods = cartDao.getCart(start, end, id);
			request.setAttribute("someGoods", someGoods);
		}
		
		return "/cart/cartForm.jsp";
	}

}
