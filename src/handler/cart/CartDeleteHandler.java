package handler.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDBBean;
import handler.CommandHandler;

public class CartDeleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		int result = 0;
		String id = (String) request.getSession().getAttribute("memId");
		int goods_number = Integer.parseInt(request.getParameter("goods_number"));
		int pageNum =  Integer.parseInt(request.getParameter("pageNum"));
		
		CartDBBean cartDao = CartDBBean.getInstance();
		
		result = cartDao.deleteCart(goods_number, id);
		
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		
		return "/cart/cartDelete.jsp";
	}

}
