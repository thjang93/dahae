package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;

public class adminDeleteProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String id =request.getParameter("id");
		
		LogonDBBean memberDao = LogonDBBean.getInstance();
		int resultCheck = memberDao.check(id);
		
		request.setAttribute("resultCheck", resultCheck);
		
		if( resultCheck == 1 ){
			int result = memberDao.deleteMember( id );
			request.setAttribute( "result", result );
			System.out.println(result);
		}		
		
		return "/member/adminDeletePro.jsp";
	}

}
