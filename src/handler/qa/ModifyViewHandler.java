package handler.qa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import qa.QaDBBean;
import qa.QaDataBean;

public class ModifyViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt( request.getParameter("num") );
		String id = (String) request.getSession().getAttribute("memId");
	
		QaDBBean qaDao = QaDBBean.getInstance();
		int result = qaDao.check( num, id );
		
		request.setAttribute("result", result);
		request.setAttribute("num", num );		
		request.setAttribute("pageNum", pageNum);
		
		if(result != 0){
			QaDataBean qaDto = qaDao.getArticle( num );
			request.setAttribute("qaDto", qaDto);
		}
		
		return "/qa/modifyView.jsp";
	}

}
