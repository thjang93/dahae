package handler.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import goods.GoodsDataBean;
import handler.CommandHandler;

public class DeleteImageHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		String image_what = request.getParameter("image_what");
		int goods_number = Integer.parseInt(request.getParameter("goods_number"));
		String pageNum = request.getParameter("pageNum");
		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
		
		//상세이미지 db에서 삭제하기
		int result = goodsDao.deleteImage(goods_number, image_what);
		
		
		
		GoodsDataBean goodsDto = goodsDao.getArticle( goods_number );
		
		//상세이미지 서버에서 삭제하기
		String path = null;
		if(image_what.equals("goods_specimg1")){
			path = request.getSession().getServletContext().getRealPath("/upload"+goodsDto.getGoods_specimg1s());
		}else if(image_what.equals("goods_specimg2")){
			path = request.getSession().getServletContext().getRealPath("/upload"+goodsDto.getGoods_specimg2s());
		}else if(image_what.equals("goods_specimg3")){
			path = request.getSession().getServletContext().getRealPath("/upload"+goodsDto.getGoods_specimg3s());
		}
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		
		
		//modifyView띄우기위해...set...
		request.setAttribute("result", result);
		request.setAttribute("goodsDto", goodsDto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", goods_number);
		
		return "goods/modifyView.jsp";
	}

}
