package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;

public class LogoutHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		request.getSession().removeAttribute("memId");		
		return "/main/index.jsp";
	}
}
