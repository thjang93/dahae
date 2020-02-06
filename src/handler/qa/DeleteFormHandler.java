package handler.qa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;

public class DeleteFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num")); 
	
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		
		return "/qa/deleteForm.jsp";
	}

}
