package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;

public class MoSendEmailHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String email =request.getParameter("email");
		String authnum = "";
		String content = "";	
		
		//������ȣ ����
		for(int i =0; i <= 6; i++){
			authnum += Integer.toString((int)(Math.random()*10));
		}
		content = "인증번호 [ " + authnum + " ]";
	
		LogonDBBean memberDao = LogonDBBean.getInstance();
		int result = memberDao.sendEmail(email, content);
		
		request.setAttribute("result",result);
		request.setAttribute("authnum", authnum );
		return "/member/mosendEmail.jsp";
	}

}
