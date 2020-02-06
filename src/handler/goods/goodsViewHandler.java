package handler.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import goods.GoodsDataBean;
import handler.CommandHandler;

public class goodsViewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		GoodsDataBean goodsDto = new GoodsDataBean();
		
		int goods_number = Integer.parseInt(request.getParameter("goodsNum"));
		
		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
		goodsDto = goodsDao.getGoods(goods_number);
		
		request.setAttribute("goodsDto", goodsDto);
		
		return "/goods/goodsView.jsp";
	}

}