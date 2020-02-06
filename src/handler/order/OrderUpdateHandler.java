package handler.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import order.OrderDBBean;
import order.OrderDataBean;

public class OrderUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		 request.setCharacterEncoding("UTF-8");
		 int order_number = Integer.parseInt(request.getParameter("orderNum"));
		
		 OrderDBBean orderDao =  OrderDBBean.getInstance();
		 ArrayList<OrderDataBean> orderDto =  orderDao.getArticles(order_number);	
		 
		 request.setAttribute("orderDto", orderDto);
         request.setAttribute("order_number", order_number);

         OrderDataBean orderDtodto= orderDto.get(0);
         System.out.println(orderDtodto.getTrack_com());
		return "/order/orderUpdate.jsp";
	}

	
	
}
