package handler.goods;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import goods.GoodsDBBean;
import goods.GoodsDataBean;
import handler.CommandHandler;

public class WriteProHandler implements CommandHandler {
 
	@Override
	 public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	  
		  request.setCharacterEncoding("utf-8");
		  /*
		   String relativePath = "/bccc/file";
		   String appPath = System.getProperty("catalina.home") + relativePath;
		   String relativePath = "/bccc/file";
		   	위 방법은 톰캣 홈 디렉토리 아래에 폴더를 생성하고 파일을 업로드 하게끔 구성한것인데
		   */
		
		  String path = request.getSession().getServletContext().getRealPath("/upload");
		  
		  File file = new File(path);
		  if(! file.exists()){
			  file.mkdir();
		  }
		  
		  MultipartRequest multi 
		  = new MultipartRequest( request, path, 1024*1024*100, "utf-8", new DefaultFileRenamePolicy() );
		  
		  String filename = multi.getOriginalFileName("image");
		  String systemname = multi.getFilesystemName("image");
		  String filename1 = multi.getOriginalFileName("specimg1");
		  String systemname1 = multi.getFilesystemName("specimg1");
		  String filename2 = multi.getOriginalFileName("specimg2");
		  String systemname2 = multi.getFilesystemName("specimg2");
		  String filename3 = multi.getOriginalFileName("specimg3");
		  String systemname3 = multi.getFilesystemName("specimg3");
		  
		   GoodsDataBean goodsDto = new GoodsDataBean();
		   goodsDto.setGoods_name(multi.getParameter("goods_name"));
		   goodsDto.setCategory_name(multi.getParameter("category_name"));
		   goodsDto.setGoods_info(multi.getParameter("goods_info"));
		   goodsDto.setGoods_image(filename);
		   goodsDto.setGoods_images(systemname);
		   goodsDto.setPrice(Integer.parseInt(multi.getParameter("price")));
		   goodsDto.setGoods_specimg1(filename1);
		   goodsDto.setGoods_specimg1s(systemname1);
		   goodsDto.setGoods_specimg2(filename2);
		   goodsDto.setGoods_specimg2s(systemname2);
		   goodsDto.setGoods_specimg3(filename3);
		   goodsDto.setGoods_specimg3s(systemname3);
		   
		  // reg_date
		   goodsDto.setGoods_reg_date( new Timestamp( System.currentTimeMillis() ) );
		 
		  GoodsDBBean goodsDao = GoodsDBBean.getInstance();
		  int result = goodsDao.insertGoods( goodsDto );
		  int goods_number = goodsDao.getGoodsNumber(goodsDto.getGoods_name());
		  
		  String pageNum = multi.getParameter( "pageNum" );
		  request.setAttribute("pageNum", pageNum);
		  request.setAttribute("result", result);
		  request.setAttribute("goods_number", goods_number);
		  
		  return "/goods/writePro.jsp";
	 }

}


