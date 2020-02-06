package handler.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import goods.GoodsDataBean;
import handler.CommandHandler;
import member.LogonDBBean;
import member.LogonDataBean;

public class OrderFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		//占쎄맒占쎈�배린�뜇�깈 獄쏆룇釉섓옙苑� 占쎈쨲占쎌몵嚥∽옙 占쎄퐜疫꿸퀗由�...
		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
		//order 0 = 二쇰Ц �븳媛�  1 = �옣諛붽뎄�땲 二쇰Ц
		int order;
		
		try{
			GoodsDataBean goodsDto = goodsDao.getGoods(Integer.parseInt(request.getParameter("goodsNum")));	
			request.setAttribute("goodsDto", goodsDto);
			request.setAttribute("goodsCount", Integer.parseInt(request.getParameter("goodsCount")));
			order = 0;
		}catch (Exception e) {
			String id = request.getParameter("id");
			ArrayList<GoodsDataBean> goodsDto = goodsDao.getCartGoods(id);
			request.setAttribute("goodsDto", goodsDto);
			order = 1;
		}
		
		LogonDBBean memberDao = LogonDBBean.getInstance();
		LogonDataBean memberDto = memberDao.getMember((String) request.getSession().getAttribute("memId"));
		request.setAttribute("memberDto", memberDto);
		request.setAttribute("order", order);
		
		return "/order/orderForm.jsp";
	}

}