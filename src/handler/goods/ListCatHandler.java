package handler.goods;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.GoodsDBBean;
import goods.GoodsDataBean;
import handler.CommandHandler;

public class ListCatHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int pageSize = 6;  	//�븳 �럹�씠吏��뿉 異쒕젰�븷 �긽�뭹�쓽 �닔
		int pageBlock = 3;		//�븳踰덉뿉 蹂댁뿬以� �럹�씠吏� �닔**
		int count = 0;			//湲��쓽 媛��닔**
		String pageNum = null; 	//�쁽�옱�럹�씠吏�**
		int currentPage = 0;   	//�쁽�옱�럹�씠吏� :�쐞�쓽 pageNum怨� 媛숆퀬, �뿰�궛�슜�쑝濡� �벝 寃껋씠�떎.
		int start = 0;			//�쁽�옱�럹�씠吏��쓽 �떆�옉 rownum**
		int end = 0;			//�쁽�옱�럹�씠吏��쓽 �걹 rownum
		int number = 0;			//湲�踰덊샇 怨꾩궛**
		
		int pageCount = 0 ;		//�쟾泥� �럹�씠吏��닔**
		int startPage = 0;		//蹂댁뿬以� 泥� �럹�씠吏�**
		int endPage = 0;		//蹂댁뿬以� �걹 �럹�씠吏�**
			
	//湲��씠 紐뉕컻 �엳�뒗吏� Dao濡� �븣�븘蹂닿린
		String category_name = request.getParameter("category");
		GoodsDBBean goodsDao = GoodsDBBean.getInstance();
		if(category_name.equals("new") || category_name.equals("best")) {
			count = goodsDao.getCount();
		}else {
			count = goodsDao.getCatCount(category_name);
		}
		
		//�럹�씠吏��뿉 ���븳 �꽕�젙 : 10媛쒖쓽 湲��쓣 �븯�굹�쓽 �럹�씠吏�濡� 留뚮뱾湲�

		if(count > 0 ){
			//湲��씠 �엳�뒗 寃쎌슦
			pageNum = request.getParameter( "pageNum" );
			if( pageNum == null ){
				pageNum = "1" ;
			}
			currentPage = Integer.parseInt( pageNum );
			start = ( currentPage - 1 ) * pageSize + 1;
					// �삁 : ( 5 - 1 ) * 10 + 1 = 41
						//	( 1 - 1 ) * 10 + 1 = 1 
			end = start + pageSize - 1;		
					// �삁 : 41 + 10 - 1  = 50
						//	1 + 10 - 1 = 10
			if( end > count ) end = count; 
			
			number = count - ( currentPage - 1 )* pageSize; //�뾾�뼱�룄 �릺�굹??
			
			pageCount = count / pageSize 
					+ (count % pageSize > 0 ? 1 : 0); //count瑜� pageSize濡� �굹�댋�뒗�뜲 �굹癒몄�媛� �궓�뒗�떎硫� 1
			
			startPage = (currentPage / pageBlock ) * pageBlock + 1;
					//�삁 : ( 15/ 10 ) * 10 + 1 = 11
						// ( 1 / 3 ) * 3 + 1 = 1
						// ( 2 / 3 ) * 3 + 1 = 1
						// ( 3 / 3 ) * 3 + 1 = 4
						// ( 4 / 3 ) * 3 + 1 = 4
			if( currentPage % pageBlock == 0 ) startPage -= pageBlock;
				//�삁 : ( 3 / 3 == 0 ) �씪�븣 startPage瑜� 4-=3�쑝濡� �븳�떎. 
					
			endPage = startPage + pageBlock - 1;
					//�삁 : 11 + 10 - 1 = 20
			if(endPage > pageCount ) endPage = pageCount;
		} 
		
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("number", number);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		if( count != 0 ){
			if(category_name.equals("new")) {
				ArrayList<GoodsDataBean> catgoods = goodsDao.getGoodsNew(start, end);
				request.setAttribute( "catgoods", catgoods );
			}else if(category_name.equals("best")) {
				ArrayList<GoodsDataBean> catgoods = goodsDao.getGoodsBest(start, end);
				request.setAttribute( "catgoods", catgoods );
			}else {
				ArrayList<GoodsDataBean> catgoods = goodsDao.getGoodsCategory(start, end, category_name);
				request.setAttribute( "catgoods", catgoods );
			}
			request.setAttribute("category", category_name);
		}
		
		return "/goods/listCat.jsp";
	}
	
}
