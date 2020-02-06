package handler.qa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import qa.QaDBBean;
import qa.QaDataBean;

public class ModifyProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		QaDataBean qaDto = new QaDataBean();
		qaDto.setSubject(request.getParameter("subject"));
		qaDto.setContent(request.getParameter("content"));
		qaDto.setNum(Integer.parseInt(request.getParameter("num")));
		
		String pageNum = request.getParameter( "pageNum" );
		request.setAttribute("pageNum", pageNum);	
		
		QaDBBean qaDao = QaDBBean.getInstance();
		int result = qaDao.updateArticle( qaDto );
		request.setAttribute("result", result);
	
		return "/qa/modifyPro.jsp";
	}

}
