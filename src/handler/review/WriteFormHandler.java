package handler.review;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import review.ReviewDBBean;

public class WriteFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//제목글
		int num = 0; 		//제목글/답변글 //임시로 0이면 제목글, 1이면 답변글로
		int ref = 1;		//그룹화 아이디
		int re_step = 0;	//글순서
		int re_level = 0;	//글레벨
		String pageNum = null;
		
		if( request.getParameter( "num" ) != null ){
			num = Integer.parseInt( request.getParameter( "num" ) );
			pageNum = request.getParameter("pageNum");
			ref = Integer.parseInt( request.getParameter( "ref" ) );
			re_step = Integer.parseInt( request.getParameter( "re_step" ) );
			re_level = Integer.parseInt( request.getParameter( "re_level" ) );
		}else{
			
		}
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
		if(request.getParameter("goods_name") != null) {
			request.setAttribute("goods_name", request.getParameter("goods_name"));
		}
		
		//상품명 가져오기
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		HashMap<Integer,String> goods = reviewDao.getAllGoods();
		request.setAttribute("goods", goods);
		
		return "/review/writeForm.jsp";
	}

}
