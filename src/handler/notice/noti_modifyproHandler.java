package handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.Dh_noticeDBBean;
import notice.Dh_noticeDataBean;
import handler.CommandHandler;

public class noti_modifyproHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
	
	      request.setCharacterEncoding("utf-8");
   
	    Dh_noticeDataBean boardDto = new Dh_noticeDataBean();
	     boardDto.setN_number(Integer.parseInt(request.getParameter("n_number")));
	     boardDto.setN_subject(request.getParameter("subject"));
	     boardDto.setN_content(request.getParameter("content"));
	    String pageNum = request.getParameter("pageNum");

	       
	    Dh_noticeDBBean boardDao =  Dh_noticeDBBean.getInstance();
	    int result =  boardDao.updateArticle(boardDto);
	   
	     request.setAttribute("result", result);
	     request.setAttribute("pageNum", pageNum);
	     
		
		return "/notice/noti_modifyPro.jsp";
	}

}
