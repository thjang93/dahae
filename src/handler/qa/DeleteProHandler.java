package handler.qa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import qa.QaDBBean;

public class DeleteProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String id = (String) request.getSession().getAttribute("memId");
		QaDBBean qaDao = QaDBBean.getInstance();
		
		if(id.equals("admin")) {
			int result = qaDao.deleteArticle( num );
			request.setAttribute("resultCheck", 1);
			request.setAttribute("result", result);		
		}else {
			int resultCheck = qaDao.check( num, id );	
			request.setAttribute("resultCheck", resultCheck);
			
			if(resultCheck != 0){
				int result = qaDao.deleteArticle( num );
				request.setAttribute("result", result);
			}
		}
		
		request.setAttribute("pageNum", pageNum);
		
		return "/qa/deletePro.jsp";
	}
}
