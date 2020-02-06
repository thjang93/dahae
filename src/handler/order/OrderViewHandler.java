package handler.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import handler.CommandHandler;
import member.LogonDBBean;
import member.LogonDataBean;
import notice.Dh_noticeDataBean;
import order.OrderDBBean;
import order.OrderDataBean;

public class OrderViewHandler implements CommandHandler{
  @Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
	  request.setCharacterEncoding("UTF-8");
	  int order_number = Integer.parseInt(request.getParameter("orderNum"));
      
      OrderDBBean orderDao = OrderDBBean.getInstance();
      ArrayList<OrderDataBean>  result = orderDao.getArticles(order_number);
     
      //LogonDBBean memberDao = LogonDBBean.getInstance();
      //LogonDataBean memberDto = new LogonDataBean();
      //memberDto = memberDao.getMember(result.get(1).getId());
      
      
      request.setAttribute("result", result);
      //request.setAttribute("memberDto", memberDto);
		

       
        return "/order/orderView.jsp";
	}
	
	
	
	
}
