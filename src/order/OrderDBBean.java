package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDBBean {
	private static OrderDBBean instance = new OrderDBBean();
	public static OrderDBBean getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws NamingException, SQLException{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/kh");
		return ds.getConnection();
	}
	
	public int insertOrder(OrderDataBean orderDto) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			con = getConnection();
			String sql = "insert into dh_order values(order_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, orderDto.getOrder_date());
			pstmt.setString(2, orderDto.getDelivery_name());
			pstmt.setString(3, orderDto.getDelivery_tel());
			pstmt.setString(4, orderDto.getDelivery_zipcode());
			pstmt.setString(5, orderDto.getDelivery_address());
			pstmt.setString(6, orderDto.getDelivery_msg());
			pstmt.setInt(7, orderDto.getTrack_num());
			pstmt.setString(8, orderDto.getTrack_com());
			pstmt.setString(9, orderDto.getPay_method());
			pstmt.setString(10, orderDto.getOrder_state());
			pstmt.setString(11, orderDto.getId());
			pstmt.setInt(12, orderDto.getGoods_number());
			pstmt.setInt(13, orderDto.getGoods_count());
			result = pstmt.executeUpdate();
			if(result != 0) {
				pstmt.close();
				sql = "update dh_goods set sell_count = sell_count + ? where goods_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, orderDto.getGoods_count());
				pstmt.setInt(2, orderDto.getGoods_number());
				pstmt.executeUpdate();
			}
		}catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	public ArrayList<OrderDataBean> getArticles(int start, int end ){
		ArrayList<OrderDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select order_date, order_number, id, delivery_name, goods_name, goods_images, order_state, r "
					+ "from(select order_date, order_number, id, delivery_name, goods_name, goods_images, order_state, rownum r "
					+ "from(select o.order_date, o.order_number, d.id, o.delivery_name, g.goods_name, g.goods_images, o.order_state "
					+ "from dh_member d, dh_goods g, dh_order o "
					+ "where d.id = o.id "
					+ "and o.goods_number = g.goods_number "
					+ "order by o.order_date desc)) "
					+ "where r >= ? and r <= ?";			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start );
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList<OrderDataBean>();
				do{
					OrderDataBean article = new OrderDataBean();
					article.setOrder_date(rs.getTimestamp( "order_date" ));
					article.setId(rs.getString("id"));
					article.setOrder_number(rs.getInt("order_number"));
					article.setDelivery_name(rs.getString("delivery_name"));
					article.setGoods_name(rs.getString("goods_name"));
					article.setOrder_state(rs.getString("order_state"));
					article.setGoods_images(rs.getString("goods_images"));
					articles.add( article ); 
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
		return articles;
	}
	
	public ArrayList<OrderDataBean> getArticles(int start, int end, String id){
		ArrayList<OrderDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select order_date, order_number, id, delivery_name, goods_name, goods_images, order_state, r "
					+ "from(select order_date, order_number, id, delivery_name, goods_name, goods_images, order_state, rownum r "
					+ "from(select o.order_date, o.order_number, d.id, o.delivery_name, g.goods_name, g.goods_images, o.order_state "
					+ "from dh_member d, dh_goods g, dh_order o "
					+ "where d.id = o.id "
					+ "and o.goods_number = g.goods_number "
					+ "and d.id = ? "
					+ "order by o.order_date desc)) "
					+ "where r >= ? and r <= ?";			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start );
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList<OrderDataBean>();
				do{
					OrderDataBean article = new OrderDataBean();
					article.setOrder_date(rs.getTimestamp( "order_date" ));
					article.setId(rs.getString("id"));
					article.setOrder_number(rs.getInt("order_number"));
					article.setDelivery_name(rs.getString("delivery_name"));
					article.setGoods_name(rs.getString("goods_name"));
					article.setGoods_images(rs.getString("goods_images"));
					article.setOrder_state(rs.getString("order_state"));					
					articles.add( article ); 
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
		return articles;
	}
	
	public int getCount() {
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_order";
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
	
	public int getCount(String id) {
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_order where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	 public ArrayList<OrderDataBean> getArticles(int order_number){
		 ArrayList<OrderDataBean> articles = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				String sql ="select o.order_date, o.order_number, d.id, d.name, g.goods_name, "
						+"o.order_state, g.goods_images, g.price, g.goods_info, o.delivery_tel, "
						+"o.delivery_address, o.delivery_msg, o.delivery_zipcode, o.goods_count, "
						+"o.track_com, o.track_num, o.pay_method, o.delivery_name "
						+"from dh_member d, dh_goods g, dh_order o " 
						+"where d.id = o.id and o.goods_number = g.goods_number "
						+"and order_number = ?";		
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_number);
				rs = pstmt.executeQuery();
				
				if( rs.next() ){
					articles = new ArrayList<OrderDataBean>();
					do{
						OrderDataBean article = new OrderDataBean();
						article.setOrder_date(rs.getTimestamp( "order_date" ));
						article.setId(rs.getString("id"));
						article.setOrder_number(rs.getInt("order_number"));
						article.setName(rs.getString("name"));
						article.setGoods_name(rs.getString("goods_name"));
						article.setOrder_state(rs.getString("order_state"));
						article.setPrice(rs.getInt("price"));
						article.setGoods_images(rs.getString("goods_images"));
						article.setGoods_info(rs.getString("goods_info"));
						article.setDelivery_tel(rs.getString("delivery_tel"));
						article.setDelivery_address(rs.getString("delivery_address"));
						article.setDelivery_msg(rs.getString("delivery_msg"));
						article.setDelivery_zipcode(rs.getString("delivery_zipcode"));
						article.setGoods_count(rs.getInt("goods_count"));
						article.setTrack_com(rs.getString("track_com"));
						article.setTrack_num(rs.getInt("track_num"));
						article.setPay_method(rs.getString("pay_method"));
						article.setDelivery_name(rs.getString("delivery_name"));
						
						articles.add( article ); 
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
			return articles;
		}
	 
	 public ArrayList<OrderDataBean> getArticles(String id){
		 ArrayList<OrderDataBean> articles = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				String sql ="select o.order_date, o.order_number, d.id, d.name, g.goods_name, "
						+"o.order_state, g.goods_images, g.price, g.goods_info, o.delivery_tel, "
						+"o.delivery_address, o.delivery_msg, o.delivery_zipcode, o.goods_count, "
						+"o.track_com, o.track_num, o.pay_method, o.delivery_name "
						+"from dh_member d, dh_goods g, dh_order o " 
						+"where d.id = o.id and o.goods_number = g.goods_number "
						+"and id = ?";			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if( rs.next() ){
					articles = new ArrayList<OrderDataBean>();
					do{
						OrderDataBean article = new OrderDataBean();
						article.setOrder_date(rs.getTimestamp( "order_date" ));
						article.setId(rs.getString("id"));
						article.setOrder_number(rs.getInt("order_number"));
						article.setName(rs.getString("name"));
						article.setGoods_name(rs.getString("goods_name"));
						article.setOrder_state(rs.getString("order_state"));
						article.setPrice(rs.getInt("price"));
						article.setGoods_images(rs.getString("goods_images"));
						article.setGoods_info(rs.getString("goods_info"));
						article.setDelivery_tel(rs.getString("delivery_tel"));
						article.setDelivery_address(rs.getString("delivery_address"));
						article.setDelivery_msg(rs.getString("delivery_msg"));
						article.setDelivery_zipcode(rs.getString("delivery_zipcode"));
						article.setGoods_count(rs.getInt("goods_count"));
						article.setTrack_com(rs.getString("track_com"));
						article.setTrack_num(rs.getInt("track_num"));
						article.setPay_method(rs.getString("pay_method"));
						article.setDelivery_name(rs.getString("delivery_name"));
						articles.add( article ); 
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
			return articles;
		}


	
	 public ArrayList<OrderDataBean>updateArticle(int order_number){
			ArrayList<OrderDataBean> articles = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				String sql = "update dh_order set order_state=?, track_num=?, track_com=? where order_number=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_number);
				
				rs = pstmt.executeQuery();
				
				if( rs.next() ){
					articles = new ArrayList<OrderDataBean>();
					do{
						OrderDataBean article = new OrderDataBean();
						article.setOrder_date(rs.getTimestamp( "order_date" ));
						article.setId(rs.getString("id"));
						article.setOrder_number(rs.getInt("order_number"));
						article.setName(rs.getString("name"));
						article.setGoods_name(rs.getString("goods_name"));
						article.setOrder_state(rs.getString("order_state"));
						
						articles.add( article ); 
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
			return articles;
		}
		 
		 
	 public int updateArticle(OrderDataBean orderDto){
            int	result  = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				String sql = "update dh_order set order_state=?, track_num=?, track_com=? where order_number=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, orderDto.getOrder_state());
				pstmt.setInt(2, orderDto.getTrack_num());
				pstmt.setString(3, orderDto.getTrack_com());
				pstmt.setInt(4, orderDto.getOrder_number());
				result = pstmt.executeUpdate();
				
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
			return result;
		}
	 
}
