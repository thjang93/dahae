package handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import notice.Dh_noticeDBBean;
import notice.Dh_noticeDataBean;
import handler.CommandHandler;

public class noti_modifyviewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
	   
	    String pageNum = request.getParameter("pageNum");
	    int n_number = Integer.parseInt(request.getParameter("n_number"));
	    String memId = (String)request.getSession().getAttribute("memId");	   
	     Dh_noticeDBBean boardDao = Dh_noticeDBBean.getInstance();
	     int result = boardDao.check(n_number, memId);   
	     
	    request.setAttribute("result", result);
	    request.setAttribute("n_number", n_number);
	    request.setAttribute("pageNum",pageNum);
		
	    if(result == 1){
	    	Dh_noticeDataBean boardDto = boardDao.getAticle(n_number);
	    	request.setAttribute("boardDto", boardDto);    
	    }
		
		
		return "/notice/noti_modifyview.jsp";
	}

}
