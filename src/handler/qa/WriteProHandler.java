package handler.qa;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import qa.QaDBBean;
import qa.QaDataBean;

public class WriteProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
	 request.setCharacterEncoding("utf-8");
	 	QaDataBean qaDto = new QaDataBean();
	 	qaDto.setNum(Integer.parseInt(request.getParameter("num")));
	 	qaDto.setSubject(request.getParameter("subject"));
	 	qaDto.setContent(request.getParameter("content"));
	 	qaDto.setReg_date( new Timestamp( System.currentTimeMillis() ) );
	 	
	 	qaDto.setRef(Integer.parseInt(request.getParameter("ref")));
	 	qaDto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
	 	qaDto.setRe_level(Integer.parseInt(request.getParameter("re_level")));

	 	qaDto.setId((String) request.getSession().getAttribute( "memId" ));
	 	
	 		
		QaDBBean qaDao = QaDBBean.getInstance();
		int result = qaDao.insertArticle( qaDto );
		
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);

		return "/qa/writePro.jsp";
	}

}
