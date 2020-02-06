package handler.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import order.OrderDBBean;
import order.OrderDataBean;

public class OrderUpProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		OrderDataBean orderDto = new OrderDataBean();
       	orderDto.setOrder_state(request.getParameter("order_state"));
       	orderDto.setTrack_com(request.getParameter("track_com"));
       	if( !request.getParameter("track_num").equals("") && request.getParameter("track_num") != null ){
       		orderDto.setTrack_num(Integer.parseInt(request.getParameter("track_num")));
       	}
       	
       	orderDto.setOrder_number(Integer.parseInt(request.getParameter("order_number")));
		
       OrderDBBean orderDao = OrderDBBean.getInstance();
       int result = orderDao.updateArticle(orderDto);
       
       request.setAttribute("result", result);
           
       	
       	
		return "/order/orderUpPro.jsp";
	}

	
}
