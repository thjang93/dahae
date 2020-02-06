package handler.notice;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.Dh_noticeDBBean;
import notice.Dh_noticeDataBean;
import handler.CommandHandler;


public class noti_writeProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	    
		
		request.setCharacterEncoding("utf-8");	  
	    Dh_noticeDataBean boardDto = new Dh_noticeDataBean();
	    boardDto.setN_subject(request.getParameter("subject"));
	    boardDto.setId((String) request.getSession().getAttribute("memId"));
	    boardDto.setN_content(request.getParameter("content"));
	    boardDto.setN_number(Integer.parseInt(request.getParameter("num")));
	    boardDto.setN_imp_level(Integer.parseInt(request.getParameter("notice")));
	    boardDto.setN_reg_date(new Timestamp(System.currentTimeMillis()));
	    
	    Dh_noticeDBBean boardDao =  Dh_noticeDBBean.getInstance();
	    
	    int imp_count = 0;
	    
	    if(Integer.parseInt(request.getParameter("notice")) == 1){
	    	//중요공지로 글을 올릴 때
	    	imp_count = boardDao.getImp_count();
	    	
		    if(imp_count < 3){
		    	//중요공지글이 3개미만일때 
		    	int result =  boardDao.insertArticle(boardDto);
				request.setAttribute("result", result);
		    }else{
		    	//중요공지글이 3개이상일때
		    	//4번째 글부터는 자바스크립트로 글지우라고 alert을 띄운다.
				//insertArticle이 실행이 되지 않는다.
		    }
	    	
	    }else{
	    	//일반공지로 글을 올릴 때
	    	int result =  boardDao.insertArticle(boardDto);
			request.setAttribute("result", result);
	    }
	    
	    request.setAttribute("imp_count", imp_count);
				
		return "/notice/noti_writePro.jsp";
	}

}
