package handler.review;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import handler.CommandHandler;
import review.ReviewDBBean;
import review.ReviewDataBean;

public class WriteProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
	 request.setCharacterEncoding("utf-8");
	/*
		writeForm에서 num, ref, re_step, re_level,
					subject, content, passwd,
					goods_name파라미터로 가져오지만, 받아오게 되는 value는 goods_num임.
					readcount X->content에서 함
					reg_date 등록일
					id ->session에서 가져옴
	*/
	 	ReviewDataBean reviewDto = new ReviewDataBean();
	 	reviewDto.setNum(Integer.parseInt(request.getParameter("num")));
	 	int num = Integer.parseInt(request.getParameter("num"));
	 	reviewDto.setSubject(request.getParameter("subject"));
	 	reviewDto.setContent(request.getParameter("content"));
	 	if(num == 0) {
	 	reviewDto.setGoods_num(Integer.parseInt(request.getParameter("goods_name")));
	 	}else {
	 		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
	 		String goods_name = request.getParameter("goods_name");
	 		reviewDto.setGoods_num(goodsDao.getGoodsNumber(goods_name)); 		
	 	}
	 	//readcount X
	 	
	 	// reg_date
	 	reviewDto.setReg_date( new Timestamp( System.currentTimeMillis() ) );
	 	
	 	reviewDto.setRef(Integer.parseInt(request.getParameter("ref")));
	 	reviewDto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
	 	reviewDto.setRe_level(Integer.parseInt(request.getParameter("re_level")));

		reviewDto.setId((String) request.getSession().getAttribute( "memId" ));
	 	
	 		
		ReviewDBBean reviewDao = ReviewDBBean.getInstance();
		int result = reviewDao.insertArticle( reviewDto );
		
        String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		
		return "/review/writePro.jsp";
	}

}
