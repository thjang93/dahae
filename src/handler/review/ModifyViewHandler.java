package handler.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import review.ReviewDBBean;
import review.ReviewDataBean;

public class ModifyViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt( request.getParameter("num") );
		String id = (String) request.getSession().getAttribute("memId");
	
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		int result = reviewDao.check( num, id );
		request.setAttribute("result", result);
		request.setAttribute("num", num );		
		request.setAttribute("pageNum", pageNum);
		
		if(result != 0){
			ReviewDataBean reviewDto = reviewDao.getArticle( num );
			request.setAttribute("reviewDto", reviewDto);
		}
		
		return "/review/modifyView.jsp";
	}

}
