package goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class GoodsDBBean {
	private static GoodsDBBean instance = new GoodsDBBean();
	public static GoodsDBBean getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws NamingException, SQLException{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/kh");
		return ds.getConnection();
	}
	
	public int getCount(){
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			if( rs.next() ) {
				//rs.getInt("count(*)");
				count = rs.getInt( 1 ); //DB�뜝�럥裕� 1�뵓怨뺣쐞�뜝�룞�삕�땻占� �뜝�럥六삣뜝�럩�굚�뜝�럥由썲뜝�럥堉�.
			}		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}		
		return count;
	}
	
	public int getSearchCount(String keyword) {
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_goods where goods_name like '%' || ? || '%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				//rs.getInt("count(*)");
				count = rs.getInt( 1 ); //DB�뜝�럥裕� 1�뵓怨뺣쐞�뜝�룞�삕�땻占� �뜝�럥六삣뜝�럩�굚�뜝�럥由썲뜝�럥堉�.
			}			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}
		return count;
	}
	
	public int getCatCount(String category_name) {
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_goods where category_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category_name);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				//rs.getInt("count(*)");
				count = rs.getInt( 1 ); //DB�뜝�럥裕� 1�뵓怨뺣쐞�뜝�룞�삕�땻占� �뜝�럥六삣뜝�럩�굚�뜝�럥由썲뜝�럥堉�.
			}			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}
		return count;
	}

	public ArrayList<GoodsDataBean> getGoodsBest(int start, int end) {
		ArrayList<GoodsDataBean> catgoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number,goods_name,category_name,goods_info, goods_image, goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count,r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count, rownum r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count from dh_goods order by sell_count desc) ) "
					+ "where r >= ? and r <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				catgoods = new ArrayList <GoodsDataBean>();
				do{
					GoodsDataBean goods = new GoodsDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setCategory_name(rs.getString("category_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_specimg1(rs.getString("goods_specimg1"));
					goods.setGoods_specimg2(rs.getString("goods_specimg2"));
					goods.setGoods_specimg3(rs.getString("goods_specimg3"));
					goods.setGoods_specimg1s(rs.getString("goods_specimg1s"));
					goods.setGoods_specimg2s(rs.getString("goods_specimg2s"));
					goods.setGoods_specimg3s(rs.getString("goods_specimg3s"));
					goods.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
					goods.setSell_count(rs.getInt("sell_count"));
					catgoods.add( goods ); 
				}while(rs.next() );
			}	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return catgoods;
	}
	
	public ArrayList<GoodsDataBean> getGoodsNew(int start, int end) {
		ArrayList<GoodsDataBean> catgoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number,goods_name,category_name,goods_info, goods_image, goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count,r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count, rownum r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count from dh_goods order by goods_reg_date desc) ) "
					+ "where r >= ? and r <= ?";
				
			pstmt = con.prepareStatement(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				catgoods = new ArrayList <GoodsDataBean>();
				do{
					GoodsDataBean goods = new GoodsDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setCategory_name(rs.getString("category_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_specimg1(rs.getString("goods_specimg1"));
					goods.setGoods_specimg2(rs.getString("goods_specimg2"));
					goods.setGoods_specimg3(rs.getString("goods_specimg3"));
					goods.setGoods_specimg1s(rs.getString("goods_specimg1s"));
					goods.setGoods_specimg2s(rs.getString("goods_specimg2s"));
					goods.setGoods_specimg3s(rs.getString("goods_specimg3s"));
					goods.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
					goods.setSell_count(rs.getInt("sell_count"));
					catgoods.add( goods ); 
				}while(rs.next() );
			}	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return catgoods;
	}
	
	public ArrayList<GoodsDataBean> getGoodsCategory(int start, int end, String category_name ){
		ArrayList<GoodsDataBean> catgoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number,goods_name,category_name,goods_info, goods_image, goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count,r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count, rownum r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count from dh_goods where category_name = ? ) ) "
					+ "where r >= ? and r <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, category_name);
			pstmt.setInt( 2, start );
			pstmt.setInt( 3, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				catgoods = new ArrayList <GoodsDataBean>();
				do{
					GoodsDataBean goods = new GoodsDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setCategory_name(rs.getString("category_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_specimg1(rs.getString("goods_specimg1"));
					goods.setGoods_specimg2(rs.getString("goods_specimg2"));
					goods.setGoods_specimg3(rs.getString("goods_specimg3"));
					goods.setGoods_specimg1s(rs.getString("goods_specimg1s"));
					goods.setGoods_specimg2s(rs.getString("goods_specimg2s"));
					goods.setGoods_specimg3s(rs.getString("goods_specimg3s"));
					goods.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
					goods.setSell_count(rs.getInt("sell_count"));
					catgoods.add( goods ); 
				}while(rs.next() );
			}	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return catgoods;
	}
	
	public ArrayList<GoodsDataBean> getSearchGoods(int start, int end, String keyword ){
		ArrayList<GoodsDataBean> searchgoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number,goods_name,category_name,goods_info, goods_image, goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count,r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count, rownum r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count from dh_goods where goods_name like '%' || ? || '%') ) "
					+ "where r >= ? and r <= ?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, keyword);
			pstmt.setInt( 2, start );
			pstmt.setInt( 3, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				searchgoods = new ArrayList <GoodsDataBean>();
				do{
					GoodsDataBean goods = new GoodsDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setCategory_name(rs.getString("category_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_specimg1(rs.getString("goods_specimg1"));
					goods.setGoods_specimg2(rs.getString("goods_specimg2"));
					goods.setGoods_specimg3(rs.getString("goods_specimg3"));
					goods.setGoods_specimg1s(rs.getString("goods_specimg1s"));
					goods.setGoods_specimg2s(rs.getString("goods_specimg2s"));
					goods.setGoods_specimg3s(rs.getString("goods_specimg3s"));
					goods.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
					goods.setSell_count(rs.getInt("sell_count"));
					searchgoods.add( goods ); 
				}while(rs.next() );
			}	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return searchgoods;
	}
	
	public ArrayList<GoodsDataBean> getCartGoods(String id) {
		ArrayList<GoodsDataBean> cartgoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from dh_goods where goods_number in"
					+ "(select goods_number from dh_cart where id = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				cartgoods = new ArrayList <GoodsDataBean>();
				do{
					GoodsDataBean goods = new GoodsDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setCategory_name(rs.getString("category_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_specimg1(rs.getString("goods_specimg1"));
					goods.setGoods_specimg2(rs.getString("goods_specimg2"));
					goods.setGoods_specimg3(rs.getString("goods_specimg3"));
					goods.setGoods_specimg1s(rs.getString("goods_specimg1s"));
					goods.setGoods_specimg2s(rs.getString("goods_specimg2s"));
					goods.setGoods_specimg3s(rs.getString("goods_specimg3s"));
					goods.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
					goods.setSell_count(rs.getInt("sell_count"));
					cartgoods.add( goods ); 
				}while(rs.next() );
			}	
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return cartgoods;
	}
	
	public ArrayList<GoodsDataBean> getSomegoods(int start, int end ){
		ArrayList<GoodsDataBean> somegoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number,goods_name,category_name,goods_info, goods_image, goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count,r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count, rownum r "
					+ "from (select goods_number, goods_name, category_name,goods_info,goods_image,goods_images, "
					+ "price,goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count from dh_goods order by goods_number desc) ) "
					+ "where r >= ? and r <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, start );
			pstmt.setInt( 2, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				somegoods = new ArrayList <GoodsDataBean>();
				do{
					GoodsDataBean goods = new GoodsDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setCategory_name(rs.getString("category_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setGoods_image(rs.getString("goods_image"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_specimg1(rs.getString("goods_specimg1"));
					goods.setGoods_specimg2(rs.getString("goods_specimg2"));
					goods.setGoods_specimg3(rs.getString("goods_specimg3"));
					goods.setGoods_specimg1s(rs.getString("goods_specimg1s"));
					goods.setGoods_specimg2s(rs.getString("goods_specimg2s"));
					goods.setGoods_specimg3s(rs.getString("goods_specimg3s"));
					goods.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
					goods.setSell_count(rs.getInt("sell_count"));
					
					somegoods.add( goods ); 
				}while(rs.next() );
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return somegoods;
	}
	
	public int insertGoods(GoodsDataBean goodsDto){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into dh_goods( goods_number, goods_name, "
					+ "category_name, goods_info, goods_image, goods_images, "
					+ "price, goods_specimg1, goods_specimg2, goods_specimg3, "
					+ "goods_specimg1s, goods_specimg2s, goods_specimg3s, "
					+ "goods_reg_date, sell_count) "
					+ "values( goods_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, goodsDto.getGoods_name() );
			pstmt.setString( 2, goodsDto.getCategory_name() );
			pstmt.setString( 3, goodsDto.getGoods_info() );
			pstmt.setString( 4, goodsDto.getGoods_image() );
			pstmt.setString( 5, goodsDto.getGoods_images() );
			pstmt.setInt( 6, goodsDto.getPrice() );
			pstmt.setString( 7,  goodsDto.getGoods_specimg1() );
			pstmt.setString( 8,  goodsDto.getGoods_specimg2() );
			pstmt.setString( 9,  goodsDto.getGoods_specimg3() );
			pstmt.setString( 10,  goodsDto.getGoods_specimg1s() );
			pstmt.setString( 11,  goodsDto.getGoods_specimg2s() );
			pstmt.setString( 12,  goodsDto.getGoods_specimg3s() );
			pstmt.setTimestamp( 13, goodsDto.getGoods_reg_date() );			
			result = pstmt.executeUpdate();			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				   e.printStackTrace();
			}		   
		}			
		return result;	
	}
	
	public int getGoodsNumber(String goods_name) {
		int goods_number = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number from dh_goods where goods_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goods_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				goods_number = rs.getInt("goods_number");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				   e.printStackTrace();
			}		   
		}			
		return goods_number; 
	}
		
	public GoodsDataBean getGoods(int goods_number) {
		GoodsDataBean goodsDto = new GoodsDataBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from dh_goods where goods_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				goodsDto.setGoods_number(rs.getInt("goods_number"));
				goodsDto.setCategory_name(rs.getString("category_name"));
				goodsDto.setGoods_name(rs.getString("goods_name"));
				goodsDto.setGoods_info(rs.getString("goods_info"));
				goodsDto.setGoods_image(rs.getString("goods_image"));
				goodsDto.setGoods_images(rs.getString("goods_images"));
				goodsDto.setPrice(rs.getInt("price"));
				goodsDto.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
				goodsDto.setSell_count(rs.getInt("sell_count"));
				goodsDto.setGoods_specimg1(rs.getString("goods_specimg1"));
				goodsDto.setGoods_specimg1s(rs.getString("goods_specimg1s"));
				goodsDto.setGoods_specimg2(rs.getString("goods_specimg2"));
				goodsDto.setGoods_specimg2s(rs.getString("goods_specimg2s"));
				goodsDto.setGoods_specimg3(rs.getString("goods_specimg3"));
				goodsDto.setGoods_specimg3s(rs.getString("goods_specimg3s"));			
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				   e.printStackTrace();
			}		   
		}			
		return goodsDto;
	}
	
	public int updateArticle(GoodsDataBean goodsDao){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "update dh_goods set goods_name=?,category_name=?, goods_info=?, ";
			
			if(goodsDao.getGoods_image() != null){
				sql+= "goods_image=?, goods_images=?, ";
			}
			sql+="price=?, ";
			if(goodsDao.getGoods_specimg1() != null){
				sql+= "goods_specimg1=?, goods_specimg1s=?, ";
			}
			if(goodsDao.getGoods_specimg2() != null){
				sql+= "goods_specimg2=?, goods_specimg2s=?, ";
			}
			if(goodsDao.getGoods_specimg3() != null){
				sql+= "goods_specimg3=?, goods_specimg3s=?, ";
			}
			sql+= "goods_reg_date=? where goods_number=?";
			
			pstmt = con.prepareStatement(sql);
			
			int count = 4; 
			pstmt.setString( 1, goodsDao.getGoods_name() );
			pstmt.setString( 2, goodsDao.getCategory_name() );
			pstmt.setString( 3, goodsDao.getGoods_info() );
			
			if(goodsDao.getGoods_image() != null){
				pstmt.setString( count++, goodsDao.getGoods_image() );
				pstmt.setString( count++, goodsDao.getGoods_images() );
			}
			pstmt.setInt( count++, goodsDao.getPrice() );
			
			if(goodsDao.getGoods_specimg1() != null){
				pstmt.setString( count++,  goodsDao.getGoods_specimg1() );
				pstmt.setString( count++,  goodsDao.getGoods_specimg1s() );
			}
			if(goodsDao.getGoods_specimg2() != null){
				pstmt.setString( count++,  goodsDao.getGoods_specimg2() );
				pstmt.setString( count++,  goodsDao.getGoods_specimg2s() );
			}
			if(goodsDao.getGoods_specimg3() != null){
				pstmt.setString( count++,  goodsDao.getGoods_specimg3() );
				pstmt.setString( count++,  goodsDao.getGoods_specimg3s() );
			}
			pstmt.setTimestamp( count++, goodsDao.getGoods_reg_date() );
			pstmt.setInt(count, goodsDao.getGoods_number());
			
			result = pstmt.executeUpdate();	
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e){
				   e.printStackTrace();
			}		   
		}			
		return result;	
	}
	
	public GoodsDataBean getArticle( int num ){
		GoodsDataBean article = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select goods_number,category_name,goods_name,goods_info, ";
			   sql += "goods_image,goods_images,price,goods_reg_date,sell_count, ";
			   sql += "goods_specimg1,goods_specimg2,goods_specimg3,goods_specimg1s,goods_specimg2s,goods_specimg3s ";
			   sql += "from dh_goods ";
			   sql += "where goods_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				article = new GoodsDataBean();
				article.setGoods_number(rs.getInt("goods_number"));
				article.setCategory_name(rs.getString("category_name"));
				article.setGoods_name(rs.getString("goods_name"));
				article.setGoods_info(rs.getString("goods_info"));
				article.setGoods_images(rs.getString("goods_images"));
				article.setGoods_image(rs.getString("goods_image"));
				article.setPrice(rs.getInt("price"));
				article.setGoods_reg_date(rs.getTimestamp("goods_reg_date"));
				article.setSell_count(rs.getInt("sell_count"));
				article.setGoods_specimg1(rs.getString("goods_specimg1"));				
				article.setGoods_specimg2(rs.getString("goods_specimg2"));
				article.setGoods_specimg3(rs.getString("goods_specimg3"));
				article.setGoods_specimg1s(rs.getString("goods_specimg1s"));
				article.setGoods_specimg2s(rs.getString("goods_specimg2s"));
				article.setGoods_specimg3s(rs.getString("goods_specimg3s"));
			}			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(rs != null)rs.close();
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}				
		return article;
	}
	
	//�궘�젣
	public int deleteArticle( int num ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			GoodsDataBean goodsDto = getArticle( num );
			int goods_number = goodsDto.getGoods_number();		
			String sql = "select * from dh_goods where goods_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_number);
			rs = pstmt.executeQuery();
//			if(rs.next()){
				//�떟湲��씠 �엳�뒗 寃쎌슦
//				result = 1;
//			}else{
				//�떟湲��씠 �뾾�뒗 寃쎌슦
				sql = "delete from dh_goods where goods_number = ?";
				pstmt.close();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
//			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(rs != null)rs.close();
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}	
		return result;
	}
	
	//modifyView에서 이미지삭제하기
	public int deleteImage(int goods_number, String image_what){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();		
			String sql = null;
			if(image_what.equals("goods_specimg1")){
				sql = "update dh_goods set goods_specimg1=null,goods_specimg1s=null where goods_number=?";
			}else if(image_what.equals("goods_specimg2")){
				sql = "update dh_goods set goods_specimg2=null,goods_specimg2s=null where goods_number=?";
			}else if(image_what.equals("goods_specimg3")){
				sql = "update dh_goods set goods_specimg3=null,goods_specimg3s=null where goods_number=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_number);
			result = pstmt.executeUpdate();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(rs != null)rs.close();
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}	
		return result;
	}
}
