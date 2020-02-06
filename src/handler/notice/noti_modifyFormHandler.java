package handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;

public class noti_modifyFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
	
	    String pageNum = request.getParameter("pageNum");
		int n_number = Integer.parseInt(request.getParameter("n_number"));
	  
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("n_number", n_number);
		
		return "/notice/noti_modifyForm.jsp";
	}

}
