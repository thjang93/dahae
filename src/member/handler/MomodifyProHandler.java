package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;
import member.LogonDataBean;

public class MomodifyProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		/*
		<jsp:useBean id="memberDto" class="member.LogonDataBean"/>
		<jsp:setProperty name="memberDto" property="*"/>
		<!-- passwd -->
		*/
		LogonDataBean memberDto = new LogonDataBean();
		memberDto.setPasswd( request.getParameter( "passwd" ) );
		memberDto.setZipcode(request.getParameter("sample6_postcode"));
		
		memberDto.setId( request.getParameter("id"));
	
		String tel = null;
		String tel1 = request.getParameter( "tel1" );
		String tel2 = request.getParameter( "tel2" );
		String tel3 = request.getParameter( "tel3" );
		if( ! tel1.equals( "" ) 
			|| ! tel2.equals( "" ) 
			|| ! tel3.equals( "" ) ) {
			tel = tel1 + "-" + tel2 + "-" + tel3;		
		}	
		memberDto.setTel( tel );
	
		String address = null;
		String address1 = request.getParameter("sample6_address");
		String address2 = request.getParameter("sample6_address2");
		if( ! address1.equals( "" )	&& ! address2.equals( "" ))  {
				address = address1 + "/n" + address2;
		}
		memberDto.setAddress(address);
		
		System.out.println(address);
		
		String email = null;
		String email1 = request.getParameter( "email1" );
		String email2 = request.getParameter( "email2" );
	
		if( ! email1.equals( "" ) 
			|| ! email2.equals( "" ) ) {
			email = email1 + "@" + email2;
		}
		memberDto.setEmail( email );
	
		LogonDBBean memberDao = LogonDBBean.getInstance();
		int result = memberDao.updateMember( memberDto );
	
		request.setAttribute( "result", result);
		System.out.println(request.getParameter("id"));
		System.out.println(result);
		return "/member/momodifyPro.jsp";
	}

}
