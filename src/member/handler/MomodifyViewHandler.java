package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;
import member.LogonDataBean;

public class MomodifyViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("id");
		
			LogonDBBean memberDao = LogonDBBean.getInstance();
			int result = memberDao.check( id);		
			request.setAttribute("result", result);		
			if(result == 1){
				LogonDataBean memberDto = memberDao.getMember( id );
				request.setAttribute("memberDto", memberDto);
		}
		
		return "/member/momodifyView.jsp";
	}

}
