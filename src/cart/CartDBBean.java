package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CartDBBean {
	private static CartDBBean instance = new CartDBBean();
	public static CartDBBean getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws NamingException, SQLException{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/kh");
		return ds.getConnection();	
	}
	
	//濡쒓렇�씤�븳 �쑀��媛� �옣諛붽뎄�땲�뿉 �듅�젙 �긽�뭹�쓣 媛�吏�怨� �엳�뒗吏� �솗�씤�븯湲� �쐞�븿 
	public int getGoodsCount( CartDataBean cartDto ){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_cart where goods_number = ? and id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartDto.getGoods_number() );
			pstmt.setString(2, cartDto.getId() );
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)"); //�뼱李⑦뵾 �븯�굹留� �굹�삤�땲源� 洹몃깷 �닽�옄 1�쓣 �엯�젰�븿.
				count = rs.getInt( 1 ); //DB�뒗 1踰덈��꽣 �떆�옉�븳�떎.
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
	
	public int getGoodsCount( String id, int goods_number ){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select goods_count from dh_cart where goods_number = ? and id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_number );
			pstmt.setString(2, id );
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)"); //�뼱李⑦뵾 �븯�굹留� �굹�삤�땲源� 洹몃깷 �닽�옄 1�쓣 �엯�젰�븿.
				count = rs.getInt(1); //DB�뒗 1踰덈��꽣 �떆�옉�븳�떎.
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
	
	
	//濡쒓렇�씤�븳 �궗�엺�쓽 �옣諛붽뎄�땲�뿉 �긽�뭹�쓣 異붽��븳�떎.
	public int putInCart( CartDataBean cartDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "insert into dh_cart(cart_number, goods_number, id, goods_count, get_date) "
					+ "values(cart_seq.NEXTVAL, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartDto.getGoods_number());
			pstmt.setString(2, cartDto.getId());
			pstmt.setInt(3, cartDto.getGoods_count());
			pstmt.setTimestamp(4, cartDto.getGet_date());
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
	
	//濡쒓렇�씤�븳 �궗�엺�쓽 �옣諛붽뎄�땲�뿉 �긽�뭹�쓣 異붽��븳�떎(�꽔怨좎옄 �븯�뒗 �긽�뭹�씠 �씠誘� �옣諛붽뎄�땲�뿉 �엳�뒗 寃쎌슦)
	public int putInCart2( CartDataBean cartDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "update dh_cart set goods_count = goods_count+?, get_date = ? "
					+ "where goods_number = ? and id =?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartDto.getGoods_count());
			pstmt.setTimestamp(2, cartDto.getGet_date());
			pstmt.setInt(3, cartDto.getGoods_number());
			pstmt.setString(4, cartDto.getId());
			
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
	
	//�빐�떦 �븘�씠�뵒�쓽 �쑀��媛� �옣諛붽뎄�땲�뿉 �뼹留덈굹 �꽔�뿀�뒗吏� �솗�씤
	public int getCartCount(String id){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_cart where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id );
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)"); //�뼱李⑦뵾 �븯�굹留� �굹�삤�땲源� 洹몃깷 �닽�옄 1�쓣 �엯�젰�븿.
				count = rs.getInt( 1 ); //DB�뒗 1踰덈��꽣 �떆�옉�븳�떎.
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
	
	//濡쒓렇�씤�븳 �궗�엺�쓽 �옣諛붽뎄�땲�뿉 �꽔�뼱�꽔�� �긽�뭹�뱾�쓣 媛��졇�샂.
	public ArrayList<CartDataBean> getCart(int start, int end, String id){
		ArrayList<CartDataBean> someGoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select goods_number, goods_images, goods_name, goods_info, price, goods_count, r "
					+ "from (select goods_number, goods_images, goods_name, goods_info, price, goods_count, rownum r "
					+ "from (select gd.goods_number, goods_images, goods_name, goods_info, price, goods_count " 
					+ "from dh_goods gd join dh_cart ca " 
					+ "on gd.goods_number = ca.goods_number where id =? order by goods_number desc )) " 
					+ "where r>= ? and r<= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, id);
			pstmt.setInt( 2, start );
			pstmt.setInt( 3, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				someGoods = new ArrayList <CartDataBean>();
				do{
					CartDataBean goods = new CartDataBean();
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setGoods_images(rs.getString("goods_images"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setGoods_info(rs.getString("goods_info"));
					goods.setPrice(rs.getInt("price"));
					goods.setGoods_count(rs.getInt("goods_count"));
					
					someGoods.add( goods ); 
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
		return someGoods;
	}
	
	public ArrayList<CartDataBean> getCart(String id){
		ArrayList<CartDataBean> someGoods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from dh_cart where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, id);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				someGoods = new ArrayList <CartDataBean>();
				do{
					CartDataBean goods = new CartDataBean();
					goods.setCart_number(rs.getInt("cart_number"));
					goods.setGoods_number(rs.getInt("goods_number"));
					goods.setId(rs.getString("id"));
					goods.setGoods_count(rs.getInt("goods_count"));
					goods.setGet_date(rs.getTimestamp("get_date"));
					someGoods.add( goods ); 
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
		return someGoods;
	}
	
	public int deleteCart( int goods_number, String id ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = null;
			if(goods_number == 0){
				//goods_number濡� 諛쏆븘�삤�뒗 媛믪씠 �뾾�쑝硫�, �쟾泥댁궘�젣
				sql = "delete from dh_cart where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
			}else{
				//goods_number濡� 諛쏆븘�삤�뒗 媛믪씠 �엳�쑝硫�, �빐�떦 �긽�뭹留� �옣諛붽뎄�땲�뿉�꽌 �궘�젣
				sql = "delete from dh_cart where id = ? and goods_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, goods_number);
			}
			result = pstmt.executeUpdate();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void deleteCartAuto() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from dh_cart where getdate() < sysdate-1/24/60";
			pstmt = con.prepareStatement(sql);			
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
	}
}
