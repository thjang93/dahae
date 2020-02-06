package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;

public class LoginProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		String id = request.getParameter( "id" );
		String passwd = request.getParameter( "passwd" );
		
		LogonDBBean memberDao = LogonDBBean.getInstance();
		//아이디 비밀번호 확인
		int result = memberDao.check(id, passwd);
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		return "/member/loginPro.jsp";
	}

}
