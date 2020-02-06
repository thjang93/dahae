package handler.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import handler.CommandHandler;
import review.ReviewDBBean;
import review.ReviewDataBean;

public class SearchListHandler implements CommandHandler{

	@Override
	public String process
		(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int pageSize = 5;		//한 페이지에 출력할 글의 수
		int pageBlock = 3;		//한번에 보여줄 페이지 수**
		int count = 0;			//글의 갯수*
		String pageNum = "1"; 	//현재페이지**
		int currentPage = 1;   	//현재페이지 :위의 pageNum과 같고, 연산용으로 쓸 것이다.
		int start = 1;			//현재페이지의 시작 rownum**
		int end = 1;			//현재페이지의 끝 rownum
		int number = 0;			//글번호 계산**
		
		int pageCount = 1 ;		//전체 페이지수**
		int startPage = 1;		//보여줄 첫 페이지**
		int endPage = 1;		//보여줄 끝 페이지**
		
		String search_code = request.getParameter("search_code");
		String keyword = request.getParameter("keyword");
		
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		count = reviewDao.getSearchCount( search_code, keyword );
		
		if(count > 0 ){
			//글이 있는 경우
			pageNum = request.getParameter( "pageNum" );
			if( pageNum == null ){
				pageNum = "1" ;
			}
			currentPage = Integer.parseInt( pageNum );
			start = ( currentPage - 1 ) * pageSize + 1;
			end = start + pageSize - 1;		
			if( end > count ) end = count; 
			
			number = count - ( currentPage - 1 )* pageSize;
			
			
			pageCount = count / pageSize 
					+ ( count % pageSize > 0 ? 1 : 0 ); //count를 pageSize로 나눴는데 나머지가 남는다면 1
			
			startPage = ( currentPage / pageBlock ) * pageBlock + 1;
			if( currentPage % pageBlock == 0 ) startPage -= pageBlock;
					
			endPage = startPage + pageBlock - 1;
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
			ArrayList<ReviewDataBean> articles = reviewDao.getSearchArticles( start, end, search_code, keyword );
			request.setAttribute( "articles", articles );
		}
		
		request.setAttribute("search_code", search_code);		
		request.setAttribute("keyword", keyword);
		
		return "/review/searchList.jsp";
	}
	
}
