package handler.cart;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDBBean;
import cart.CartDataBean;
import handler.CommandHandler;

public class PutInHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		CartDataBean cartDto = new CartDataBean();
		cartDto.setGoods_number(Integer.parseInt(request.getParameter("goods_number")));
		cartDto.setId((String)request.getSession().getAttribute("memId"));
		cartDto.setGoods_count(Integer.parseInt(request.getParameter("goods_count")));
		cartDto.setGet_date(new Timestamp(System.currentTimeMillis()));
		
		CartDBBean cartDao = CartDBBean.getInstance();
		
		//�옣諛붽뎄�땲�뿉 �빐�떦 �긽�뭹�씠 �엳�뒗吏� �솗�씤
		int count = cartDao.getGoodsCount( cartDto ); 
		
		int result = 0;
		if(count == 0){
			//�빐�떦�긽�뭹�씠 �옣諛붽뎄�땲�뿉 �뾾�쑝硫� insert
			result = cartDao.putInCart( cartDto );
		}else{
			//�빐�떦�긽�뭹�씠 �옣諛붽뎄�땲�뿉 �엳�쑝硫� goods_count�� get_date瑜� update
			result = cartDao.putInCart2( cartDto );
		}
		
		request.setAttribute("result", result);
		
		return "/cart/putIn.jsp";
	}
	
}
