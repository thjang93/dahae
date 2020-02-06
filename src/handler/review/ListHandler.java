package handler.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import review.ReviewDBBean;
import review.ReviewDataBean;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pageSize = 10;		//한 페이지에 출력할 글의 수
		int pageBlock = 3;		//한번에 보여줄 페이지 수**
		int count = 0;			//글의 갯수**
		String pageNum = null; 	//현재페이지**
		int currentPage = 0;   	//현재페이지 :위의 pageNum과 같고, 연산용으로 쓸 것이다.
		int start = 0;			//현재페이지의 시작 rownum**
		int end = 0;			//현재페이지의 끝 rownum
		int number = 0;			//글번호 계산**
		
		int pageCount = 0 ;		//전체 페이지수**
		int startPage = 0;		//보여줄 첫 페이지**
		int endPage = 0;		//보여줄 끝 페이지**
			
	//글이 몇개 있는지 Dao로 알아보기
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		count = reviewDao.getCount();
		//페이지에 대한 설정 : 10개의 글을 하나의 페이지로 만들기

		if(count > 0 ){
			//글이 있는 경우
			pageNum = request.getParameter( "pageNum" );
			if( pageNum == null || pageNum.equals("")){
				pageNum = "1";
			}
			currentPage = Integer.parseInt( pageNum );
			start = ( currentPage - 1 ) * pageSize + 1;
					// 예 : ( 5 - 1 ) * 10 + 1 = 41
						//	( 1 - 1 ) * 10 + 1 = 1 
			end = start + pageSize - 1;		
					// 예 : 41 + 10 - 1  = 50
						//	1 + 10 - 1 = 10
			if( end > count ) end = count; 
			
			number = count - ( currentPage - 1 )* pageSize;
			
			
			pageCount = count / pageSize 
					+ ( count % pageSize > 0 ? 1 : 0 ); //count를 pageSize로 나눴는데 나머지가 남는다면 1
			
			startPage = ( currentPage / pageBlock ) * pageBlock + 1;
					//예 : ( 15/ 10 ) * 10 + 1 = 11
						// ( 1 / 3 ) * 3 + 1 = 1
						// ( 2 / 3 ) * 3 + 1 = 1
						// ( 3 / 3 ) * 3 + 1 = 4
						// ( 4 / 3 ) * 3 + 1 = 4
			if( currentPage % pageBlock == 0 ) startPage -= pageBlock;
				//예 : ( 3 / 3 == 0 ) 일때 startPage를 4-=3으로 한다. 
					
			endPage = startPage + pageBlock - 1;
					//예 : 11 + 10 - 1 = 20
			if(endPage > pageCount ) endPage = pageCount;
		} 
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("number", number);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		if( count != 0 ){
			ArrayList<ReviewDataBean> articles = reviewDao.getArticles( start, end );
			request.setAttribute( "articles", articles );
		}
		
		return "/review/list.jsp";
	}	
}
