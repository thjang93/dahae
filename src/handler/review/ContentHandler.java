package handler.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import review.ReviewDBBean;
import review.ReviewDataBean;

public class ContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter( "num" ));
		int number = Integer.parseInt(request.getParameter("number"));
	
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		//들어가면 늘어나있는 것을 볼 수 있음
		ReviewDataBean reviewDto = reviewDao.getArticle( num );
		
		//나가면 늘어난 것을 볼 수 있음.
		if( ((String)request.getSession().getAttribute("memId")) != reviewDto.getId()){
			//글쓴사람(DB Dto에서)과 content를 보는 사람(request로 받아온 id)이 같지 않으면 조회수가 늘어나게 한다.  
			reviewDao.addCount( num );
		}
		
		request.setAttribute("reviewDto", reviewDto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("number", number);

		return "/review/content.jsp";
	}

}
