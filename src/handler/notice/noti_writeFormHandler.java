package handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;

public class noti_writeFormHandler implements CommandHandler{



	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		int num = 0;		
	    int imp_level = 0;

		if( request.getParameter( "num" ) != null ) {
			num = Integer.parseInt(request.getParameter("num"));
			imp_level = Integer.parseInt(request.getParameter("imp_level"));
		
		
		}
		
		request.setAttribute("num", num);
		request.setAttribute("imp_level", imp_level);
	
	
			
		return "/notice/noti_writeForm.jsp";
	}

}
