package handler.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;

public class WriteFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//goodsWriteForm.do
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
		
		return "/goods/writeForm.jsp";
	}
}
