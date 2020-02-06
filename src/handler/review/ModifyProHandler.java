package handler.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import review.ReviewDBBean;
import review.ReviewDataBean;

public class ModifyProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ReviewDataBean reviewDto = new ReviewDataBean();
		reviewDto.setSubject(request.getParameter("subject"));
		reviewDto.setContent(request.getParameter("content"));
		reviewDto.setNum(Integer.parseInt(request.getParameter("num")));
	
		String pageNum = request.getParameter( "pageNum" );
		request.setAttribute("pageNum", pageNum);	
		
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		int result = reviewDao.updateArticle( reviewDto );
		request.setAttribute("result", result);
	
		return "/review/modifyPro.jsp";
	}

}
