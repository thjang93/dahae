package handler.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import notice.Dh_noticeDBBean;
import notice.Dh_noticeDataBean;
import handler.CommandHandler;



public class dhnotice_listHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
				   
		int pageSize = 10;           
		int pageBlock = 3;            //[1][2][3]
		int count = 0;
		String pageNum = null;       
		int currentPage = 0;         
		int start = 0;               
		int end = 0; 
		int number =0;               
		int pageCount = 0;           
		int startPage = 0;           
		int endPage = 0;             
        int imp_count =0;

			Dh_noticeDBBean boardDao = Dh_noticeDBBean.getInstance();
			count = boardDao.getCount();
            imp_count = boardDao.getImp_count();
 
		if(count >0){
			pageNum = request.getParameter("pageNum");
			if(pageNum == null){
				pageNum = "1";  
			
			}
			currentPage = Integer.parseInt(pageNum);
			start = (currentPage - 1) * pageSize +1; 
			 // (5 -1)* 10 +1     41   => 5
		    end = start + pageSize - 1;
		    //1+10-1 =10
		
			 // 41 +10 -1 
			
			if(end > count) end = count; 
			
				
		   number = count - (currentPage -1)*pageSize - imp_count;
				
				
		
			pageCount = count /pageSize + (count % pageSize > 0 ? 1 : 0);
			startPage = (currentPage /pageBlock)* pageBlock + 1;
			   
			            		 if(currentPage % pageBlock == 0) startPage  -= pageBlock;
			 endPage = startPage + pageBlock -1;
			             //11+10-1
			if(endPage >pageCount) endPage = pageCount;
			            		 
			
		  
		}

		
		if(imp_count != 0) {
			ArrayList <Dh_noticeDataBean> notice
			 = boardDao.getNotice(1,3);
		request.setAttribute("notice", notice);
		}

		if(count != 0  ){
			ArrayList <Dh_noticeDataBean> articles
			 = boardDao.getArticles(start,end);
		request.setAttribute("articles", articles);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("number", number);
		
		
		return "/notice/notice_list.jsp";
	}

}
