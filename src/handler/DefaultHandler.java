package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultHandler implements CommandHandler {
	public String process(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("result", "처리할 수 없는 요청입니다.");
		return "/member/default.jsp";
	}
}


