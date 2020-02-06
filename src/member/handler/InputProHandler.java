package member.handler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import member.LogonDBBean;
import member.LogonDataBean;

public class InputProHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding( "utf-8" );
		
		LogonDataBean memberDto = new LogonDataBean();
		memberDto.setId( request.getParameter( "id" ) );
		memberDto.setPasswd( request.getParameter( "passwd" ) );
		memberDto.setName( request.getParameter( "name" ) );
		memberDto.setbirth_date( Integer.parseInt(request.getParameter( "birth_date" )));
		memberDto.setZipcode(request.getParameter("sample6_postcode"));
		System.out.println("member inputpro" + request.getParameter("sample6_postcode"));
		
		
		String address = null;
		String address1 = request.getParameter("sample6_address");
		String address2 = request.getParameter("sample6_address2");
		if( (address1 != null && address2 != null) || (! address1.equals( "" )	&& ! address2.equals( "" )) )  {
				address = address1 + "/n" + address2;
			}
		memberDto.setAddress(address);
		System.out.println("주소" + address1 + " ");
		System.out.println("주소2" + address);
		
		
		// ?��?��번호
		String tel = null;
		String tel1 = request.getParameter( "tel1" );
		String tel2 = request.getParameter( "tel2" );
		String tel3 = request.getParameter( "tel3" );
		if( ! tel1.equals( "" ) 
			&& ! tel2.equals( "" ) 
			&& ! tel3.equals( "" ) )  {
			tel = tel1 + "-" + tel2 + "-" + tel3;
		}
		memberDto.setTel( tel );

		// ?��메일
		String email = null;
		String email1 = request.getParameter( "email1" );
		String email2 = request.getParameter( "email2" );
		if( ! email1.equals( "" ) ) {
			if( email2.equals( "0" ) ) {
				// 직접?��?��
				email = email1;
			} else {
				// ?��?��?��?��	
				email = email1 + "@" + email2;
			}		
		}
		memberDto.setEmail( email );
	
		// �??��?��?��
		memberDto.setReg_date( new Timestamp( System.currentTimeMillis() ) );
		
		
			
		LogonDBBean memberDao = LogonDBBean.getInstance();
		int result = memberDao.insertMember( memberDto );
			
		request.setAttribute( "result", result );
		
		return "/member/inputPro.jsp";
	}
}








