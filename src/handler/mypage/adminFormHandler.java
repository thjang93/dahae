package handler.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDBBean;
import handler.CommandHandler;

public class adminFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		// TODO Auto-generated method stub
		CartDBBean cartDao = CartDBBean.getInstance();
		cartDao.deleteCartAuto();
		return "/mypage/adminForm.jsp";
	}

}
