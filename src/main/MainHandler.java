package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;

public class MainHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		// TODO Auto-generated method stub
		return "/main/index.jsp";
	}

}
