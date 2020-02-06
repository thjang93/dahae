package handler.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import goods.GoodsDataBean;
import handler.CommandHandler;

public class ModifyViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt( request.getParameter("num") );
		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
	//	int result = goodsDao.check( num, id );
	//	request.setAttribute("result", result);		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		
	//	if(result != 0){
			GoodsDataBean goodsDto = goodsDao.getArticle( num );
			request.setAttribute("goodsDto", goodsDto);
	//	}
		
		return "/goods/modifyView.jsp";
	}

}
