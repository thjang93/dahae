package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;
import member.LogonDataBean;

public class ModifyViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = (String) request.getSession().getAttribute( "memId" );
		String passwd = request.getParameter( "passwd" );
		
			LogonDBBean memberDao = LogonDBBean.getInstance();
			int result = memberDao.check( id, passwd );		
			request.setAttribute("result", result);		
			if(result == 1){
				LogonDataBean memberDto = memberDao.getMember( id );
				request.setAttribute("memberDto", memberDto);
		}
		

			/*
			request.setAttribute("passwd", passwd);
			request.setAttribute("name", memberDto.getName());
			request.setAttribute("jumin1", memberDto.getJumin1());
			request.setAttribute("jumin2", memberDto.getJumin2());
			request.setAttribute("tel", memberDto.getTel());
			request.setAttribute("email", memberDto.getEmail());
			request.setAttribute("reg_date", memberDto.getReg_date());
			*/
			
		
		
		return "/member/modifyView.jsp";
	}

}
