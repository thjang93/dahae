package handler.order;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDBBean;
import cart.CartDataBean;
import goods.GoodsDataBean;
import handler.CommandHandler;
import order.OrderDBBean;
import order.OrderDataBean;

public class OrderProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		//占쌍뱄옙占쏙옙호 占쏙옙占쏙옙 : 占쌍뱄옙占쏙옙짜  占쌍뱄옙 占쏙옙 占쏙옙.. 占쏙옙 占쏙옙占쏙옙 占싹몌옙 占쏙옙占쌘곤옙 占십뱄옙 커占쏙옙 占쏙옙載� 占쏙옙 占쏙옙占쏙옙...
		/*
		DateFormat df = new SimpleDateFormat("yyMMddhhmmss");
		Date date = new Date();
		String orderDate = df.format(date);
		int order_number = Integer.parseInt(orderDate);
		*/
		OrderDataBean orderDto = new OrderDataBean();
		OrderDBBean orderDao = OrderDBBean.getInstance();
		int order = Integer.parseInt(request.getParameter("order"));
		Timestamp order_date = new Timestamp( System.currentTimeMillis());
		
		//占쏙옙화占쏙옙호
		if(order == 0) {
			String delivery_name = request.getParameter("delivery_name");
			orderDto.setDelivery_name(delivery_name);
			
			String delivery_tel1 = request.getParameter("delivery_tel1");
			String delivery_tel2 = request.getParameter("delivery_tel2");
			String delivery_tel3 = request.getParameter("delivery_tel3");
			String delivery_tel = delivery_tel1 + "-" + delivery_tel2 + "-" + delivery_tel3;	
			orderDto.setDelivery_tel(delivery_tel);
			
			//우편번호
			String delivery_zipcode = request.getParameter("sample6_postcode");
			orderDto.setDelivery_zipcode(delivery_zipcode);
			
			//占쌍쇽옙
			String delivery_address1 = request.getParameter("sample6_address1");
			String delivery_address2 = request.getParameter("sample6_address2");
			String delivery_address = delivery_address1 + "/n" + delivery_address2;		
			orderDto.setDelivery_address(delivery_address);
			
			//占쏙옙占� 占쏙옙청 占쏙옙占쏙옙
			orderDto.setDelivery_msg(request.getParameter("delivery_msg"));
			
			//占쏙옙占쏙옙 占쏙옙占쏙옙
			orderDto.setPay_method(request.getParameter("pay_method"));
			
			//占쌍뱄옙占쏙옙황 : 카占쏙옙 占싹띰옙 占쏙옙占쏙옙 占싹뤄옙 占쏙옙占쏙옙占싹띰옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�
			String order_state = null;
			int pay_method = Integer.parseInt(request.getParameter("pay_method"));
			if(pay_method == 0) {
				order_state = "결제완료";
			}else {
				order_state = "결제대기";
			}
			orderDto.setOrder_state(order_state);
			
			//占싸깍옙占쏙옙 占쏙옙 占쏙옙占싱듸옙 
			orderDto.setId((String) request.getSession().getAttribute("memId"));
			
			//占쌍뱄옙占쏙옙 占쏙옙품 占쏙옙호
			int goods_number = Integer.parseInt(request.getParameter("goods_number"));
			orderDto.setGoods_number(goods_number);
			
			int goods_count = Integer.parseInt(request.getParameter("goods_count"));
			orderDto.setGoods_count(goods_count);
			
			//占쌍뱄옙占시곤옙
			orderDto.setOrder_date(order_date);
				
			int result = orderDao.insertOrder(orderDto);
			
			request.setAttribute("result", result);	
			request.setAttribute("pay_method", pay_method);
			request.setAttribute("goods_number", goods_number);
		} else {
			CartDBBean cartDao = CartDBBean.getInstance();
			ArrayList<CartDataBean> cartDto = cartDao.getCart((String) request.getSession().getAttribute("memId"));

			for(int i = 0; i < cartDto.size(); i++) {
				String delivery_name = request.getParameter("delivery_name");
				orderDto.setDelivery_name(delivery_name);
				
				String delivery_tel1 = request.getParameter("delivery_tel1");
				String delivery_tel2 = request.getParameter("delivery_tel2");
				String delivery_tel3 = request.getParameter("delivery_tel3");
				String delivery_tel = delivery_tel1 + "-" + delivery_tel2 + "-" + delivery_tel3;	
				orderDto.setDelivery_tel(delivery_tel);
				
				//우편번호
				String delivery_zipcode = request.getParameter("sample6_postcode");
				orderDto.setDelivery_zipcode(delivery_zipcode);
				
				//占쌍쇽옙
				String delivery_address1 = request.getParameter("sample6_address1");
				String delivery_address2 = request.getParameter("sample6_address2");
				String delivery_address = delivery_address1 + "/n" + delivery_address2;		
				orderDto.setDelivery_address(delivery_address);
				
				//占쏙옙占� 占쏙옙청 占쏙옙占쏙옙
				orderDto.setDelivery_msg(request.getParameter("delivery_msg"));
				
				//占쏙옙占쏙옙 占쏙옙占쏙옙
				orderDto.setPay_method(request.getParameter("pay_method"));
				
				//占쌍뱄옙占쏙옙황 : 카占쏙옙 占싹띰옙 占쏙옙占쏙옙 占싹뤄옙 占쏙옙占쏙옙占싹띰옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�
				String order_state = null;
				int pay_method = Integer.parseInt(request.getParameter("pay_method"));
				if(pay_method == 0) {
					order_state = "결제완료";
				}else {
					order_state = "결제대기";
				}
				orderDto.setOrder_state(order_state);
				
				//占싸깍옙占쏙옙 占쏙옙 占쏙옙占싱듸옙 
				orderDto.setId((String) request.getSession().getAttribute("memId"));
					
				int goods_number = (cartDto.get(i).getGoods_number());
				orderDto.setGoods_number(goods_number);
				
				int goods_count = cartDto.get(i).getGoods_count();
				orderDto.setGoods_count(goods_count);
				
				//占쌍뱄옙占시곤옙
				orderDto.setOrder_date(order_date);
						
				int result = orderDao.insertOrder(orderDto);
				
				request.setAttribute("result", result);	
				request.setAttribute("pay_method", pay_method);
				request.setAttribute("goods_number", goods_number);
				
				cartDao.deleteCart(0, (String) request.getSession().getAttribute("memId"));
				
			}

		}
		
		//占쏙옙占쏙옙占쏙옙占싹띰옙... 
		//占쏙옙袂占쏙옙占� 占쏙옙호占쏙옙...
		return "/order/orderPro.jsp";
	}

}
