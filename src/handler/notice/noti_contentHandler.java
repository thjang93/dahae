package handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.Dh_noticeDBBean;
import notice.Dh_noticeDataBean;
import handler.CommandHandler;

public class noti_contentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		

		    String pageNum = request.getParameter("pageNum");
		    int num = Integer.parseInt(request.getParameter("num"));
		    int n_number = Integer.parseInt(request.getParameter("n_number"));
	
		    Dh_noticeDBBean boardDao = Dh_noticeDBBean.getInstance();
		    Dh_noticeDataBean boardDto = boardDao.getAticle(num);
		    	   boardDao.addCount(num);
		
		    
		     request.setAttribute("num", num);
		     request.setAttribute("boardDto", boardDto);
		     request.setAttribute("pageNum", pageNum);
		     
		
		
		return "/notice/noti_content.jsp";
	}

}
