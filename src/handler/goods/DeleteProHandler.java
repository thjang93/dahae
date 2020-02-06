package handler.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import handler.CommandHandler;

public class DeleteProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		//String passwd = request.getParameter("passwd");
	
		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
		//int resultCheck = boardDao.check( num, passwd );	
		//request.setAttribute("resultCheck", resultCheck);
		
		//if(resultCheck != 0){
			int result = goodsDao.deleteArticle( num );
			request.setAttribute("result", result);
			
		request.setAttribute("pageNum", pageNum);
		
		return "/goods/deletePro.jsp";
	}
}
