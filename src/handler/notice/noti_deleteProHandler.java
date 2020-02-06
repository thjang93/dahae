package handler.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.Dh_noticeDBBean;
import handler.CommandHandler;

public class noti_deleteProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		
	
		    String pageNum = request.getParameter("pageNum");
		    int n_number = Integer.parseInt(request.getParameter("n_number"));
		    String memId = (String)request.getSession().getAttribute("memId");	   
			  
		    Dh_noticeDBBean boardDao =  Dh_noticeDBBean.getInstance();
		    int resultCheck =  boardDao.check(n_number,  memId);
	
		    
	      	request.setAttribute("pageNum", pageNum);
		    request.setAttribute("resultCheck", resultCheck);
		    
		
		  if(resultCheck == 1){
			  int result = boardDao.deleteAtricle(n_number);
			 
			  request.setAttribute("result", result);
		  }
		    
		    
		
		
		return "/notice/noti_deletePro.jsp";
	}

}
