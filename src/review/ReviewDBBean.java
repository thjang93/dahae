package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.BoardDataBean;

public class ReviewDBBean {
	private static ReviewDBBean instance = new ReviewDBBean();
	public static ReviewDBBean getInstance(){
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
			String sql ="select count(*) from dh_review";
			pstmt = con.prepareStatement(sql);
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
	
	//�긽�뭹紐� 媛��졇�삤湲�
	public HashMap<Integer,String> getAllGoods(){
		HashMap<Integer,String> goods = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select goods_number, goods_name from dh_goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				goods = new HashMap<Integer,String>();
				do{
					goods.put( rs.getInt("goods_number"), rs.getString("goods_name") ); 
				}while(rs.next() );
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
		return goods;
	}
	
	public int insertArticle( ReviewDataBean reviewDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			
			int num = reviewDto.getNum();
			int ref = reviewDto.getRef();
			int re_step = reviewDto.getRe_step();
			int re_level = reviewDto.getRe_level();
			
			String sql = null;
			
			if( num == 0 ){
				//�젣紐⑷��씤 寃쎌슦 : ref媛믩쭔 媛��옣 �겙 媛믪씠硫� �맂�떎. DB�뿉 �엳�뒗 num媛�...
				sql = "select max(r_number) from dh_review";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if( rs.next() ){
					//湲��씠 �엳�뒗 寃쎌슦
					ref = rs.getInt( 1 ) + 1; 
					//getInt�븞�뿉 而щ읆�쓽 �씠由꾩쓣 �벐吏�留�, 
					//�븯�굹留� �굹�삤�뒗 嫄곕씪硫� 泥ル쾲吏� 媛믪씠�씪�뒗 �쑜�쑝濡� �닽�옄1�쓣 �뜥�룄 �맂�떎. 
				} else {
					//湲��씠 �뾾�뒗 寃쎌슦 DB�뿉 �엳�뒗 num�� �뾾�떎.
					ref = 1;
				}
				re_step = 0;
				re_level = 0;
			} else {
				//�떟蹂�湲��씤 寃쎌슦 : 
				//(媛숈� �젣紐⑷��뿉 �떖由� �떟蹂�湲��뱾�쓽 �닽�옄瑜� 利앷��떆�궎湲� �쐞�빐)
				//�옄�떊怨� 媛숈� 洹몃９(ref)�씠硫댁꽌 湲��닚�꽌(re_step)媛� �벐�젮�뒗�떟蹂�湲�蹂대떎 �겙 湲��뱾�쓽
				// re_step怨� re_level媛믪쓣 1�뵫 利앷��떆�궓�떎. 
				sql = "update dh_review set r_re_step = r_re_step+1 "
						+ "where r_ref=? and r_re_step>?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step ++;
				re_level ++;
			}
			
			//readcount瑜� 類� �굹癒몄� 媛믩뱾�쓣 insert�븳�떎.(�옉�꽦�븳 湲��뱾�쓣 �떎�젣濡� DB�뿉 �꽔�쓬)
			//readcount�뒗 readcount number default 0濡� �꽕�젙�빐�넧湲� �븣臾몄뿉 �옄�룞�쑝濡� 0媛믪씠 �뱾�뼱媛꾨떎. 
			//�쐞�쓽 蹂��닔num怨� DB�뿉 �꽔�뒗 num媛믪� �떎瑜대떎. 
			//DB�뿉 �꽔�뒗 num媛숈� sequence濡� �꽔�뼱以섏빞 �븳�떎. 
			sql = "insert into dh_review( r_number, r_subject, r_content, "
					+ "goods_number, r_reg_date, r_ref, r_re_step, r_re_level, "
					+ "id, r_readcount ) "
			 		+ "values( review_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, 0 )";
			pstmt.close();
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, reviewDto.getSubject() );
			pstmt.setString( 2, reviewDto.getContent() );
			pstmt.setInt( 3, reviewDto.getGoods_num() );
			pstmt.setTimestamp( 4, reviewDto.getReg_date() );
			pstmt.setInt( 5, ref); //ref�� re_step, re_level�뿉�뒗 媛�怨듯븳 媛믪쓣 �꽔�뼱�빞 �븳�떎.
			pstmt.setInt( 6, re_step);
			pstmt.setInt( 7, re_level);
			pstmt.setString( 8, reviewDto.getId() );
			
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
	public ArrayList<ReviewDataBean> getArticles(int start, int end ){
		ArrayList<ReviewDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select r_number,r_subject,r_content,r_reg_date,r_readcount,r_ref,r_re_step,r_re_level,id,goods_name,r "
					+ "from (select r_number,r_subject,r_content,r_reg_date,r_readcount,r_ref,r_re_step,r_re_level,id,goods_name,rownum r "
					+ "from (select r_number,r_subject,r_content,r_reg_date,r_readcount,r_ref,r_re_step,r_re_level,id,goods_name "
					+ "from dh_review re,dh_goods gd where re.goods_number=gd.goods_number "
					+ "order by r_ref desc,r_re_step asc) "
					+ "order by r_ref desc,r_re_step asc) "
					+ "where r >= ? and r <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, start );
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList <ReviewDataBean>();
				do{
					ReviewDataBean article = new ReviewDataBean();
					article.setNum(rs.getInt( "r_number" ));
					article.setSubject(rs.getString("r_subject"));
					article.setContent(rs.getString("r_content"));
					article.setReg_date(rs.getTimestamp("r_reg_date"));
					article.setReadcount(rs.getInt("r_readcount"));
					article.setRef(rs.getInt("r_ref"));
					article.setRe_step(rs.getInt("r_re_step"));
					article.setRe_level(rs.getInt("r_re_level"));
					article.setId(rs.getString("id"));
					article.setGoods_name(rs.getString("goods_name"));
					
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
	public ReviewDataBean getArticle( int num ){
		ReviewDataBean article = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select r_number,r_subject,r_content,r_readcount, ";
				   sql += "r_reg_date,r_ref,r_re_step,r_re_level,id,goods_name ";
				   sql += "from dh_review re join dh_goods gd ";
				   sql += "on re.goods_number = gd.goods_number where r_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if( rs.next() ){
				article = new ReviewDataBean();
				article.setNum(rs.getInt("r_number"));
				article.setSubject(rs.getString("r_subject"));
				article.setContent(rs.getString("r_content"));
				article.setReadcount(rs.getInt("r_readcount"));
				article.setReg_date(rs.getTimestamp("r_reg_date"));
				article.setRef(rs.getInt("r_ref"));
				article.setRe_step(rs.getInt("r_re_step"));
				article.setRe_level(rs.getInt("r_re_level"));
				article.setId(rs.getString("id"));
				article.setGoods_name(rs.getString("goods_name"));
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
	
	public void addCount( int num ){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql ="update dh_review set r_readcount=r_readcount+1 where r_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
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
	}
	
	public int check( int num, String id){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select * from dh_review where r_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				if( id.equals(rs.getString("id"))){
					//�븘�씠�뵒媛� 媛숇떎
					result = 1;
				}else{
					//�븘�씠�뵒媛� �떎瑜대떎
					result = 0;
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   try{
			   if(rs != null ) rs.close();
			   if(pstmt != null) pstmt.close();
			   if(con != null) con.close();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }		   
		}	
		return result;
	}
	
	public int deleteArticle( int num ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ReviewDataBean reviewDto = getArticle( num );
			int ref = reviewDto.getRef();
			int re_step = reviewDto.getRe_step();
			int re_level = reviewDto.getRe_level(); 
			//re_step�� 1�씠 而ㅼ빞 �븯怨�
			//ref�뒗 媛숈븘�빞 �븯怨�
			//re_level�� 而ㅼ빞 �븳�떎.
			
			String sql = "select * from dh_review "
						+ "where r_ref=? and r_re_step=?+1 and r_re_level>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_step);
			pstmt.setInt(3, re_level);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				//�떟湲��씠 �엳�뒗 寃쎌슦
				result = -1;
			}else{
				//�떟湲��씠 �뾾�뒗 寃쎌슦
				sql = "update dh_review set r_re_step=r_re_step-1 where r_ref=? and r_re_step>?";
				pstmt.close();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				sql = "delete from dh_review where r_number=?";
				pstmt.close();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
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
		return result;
	}
	
	public int updateArticle( ReviewDataBean reviewDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update dh_review set r_subject=?,r_content=? where r_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewDto.getSubject());
			pstmt.setString(2, reviewDto.getContent());
			pstmt.setInt(3, reviewDto.getNum());
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
	
	//寃��깋�븳 由ъ뒪�듃
	public int getSearchCount(String search_code, String keyword){
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql =null;
			if(search_code.equals("goods_name")){
				sql = "select count(*) from dh_review re join dh_goods gd ";
				sql += "on re.goods_number = gd.goods_number where goods_name like '%'||?||'%'";
			}else{
				sql = "select count(*) from dh_review re join dh_goods gd ";
				sql += "on re.goods_number = gd.goods_number where id like '%'||?||'%'";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,keyword);
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
	public ArrayList<ReviewDataBean> getSearchArticles(int start, int end, String search_code, String keyword){
		
		ArrayList<ReviewDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql =null;
			if(search_code.equals("goods_name")){
				sql = "select r_number,r_subject,r_content,r_reg_date,r_readcount,"
						+ "r_ref,r_re_step,r_re_level,id,goods_name,r from "
						+ "(select r_number,r_subject,r_content,r_reg_date,r_readcount,"
						+ "r_ref,r_re_step,r_re_level,id,goods_name,rownum r from "
						+ "(select r_number,r_subject,r_content,r_reg_date,r_readcount,"
						+ "r_ref,r_re_step,r_re_level,id,goods_name "
						+ "from dh_review re "
						+ "join dh_goods gd "
						+ "on re.goods_number=gd.goods_number "
						+ "where goods_name like '%'||?||'%' "
						+ "order by r_ref desc,r_re_step asc) "
						+ "order by r_ref desc,r_re_step asc) "
						+ "where r >= ? and r <= ?";
			}else{
				sql = "select r_number,r_subject,r_content,r_reg_date,r_readcount,"
						+ "r_ref,r_re_step,r_re_level,id,goods_name,r from "
						+ "(select r_number,r_subject,r_content,r_reg_date,r_readcount,"
						+ "r_ref,r_re_step,r_re_level,id,goods_name,rownum r from "
						+ "(select r_number,r_subject,r_content,r_reg_date,r_readcount,"
						+ "r_ref,r_re_step,r_re_level,id,goods_name "
						+ "from dh_review re "
						+ "join dh_goods gd "
						+ "on re.goods_number=gd.goods_number "
						+ "where id like '%'||?||'%' "
						+ "order by r_ref desc,r_re_step asc) "
						+ "order by r_ref desc,r_re_step asc) "
						+ "where r >= ? and r <= ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword );
			pstmt.setInt(2, start );
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList <ReviewDataBean>();
				do{
					ReviewDataBean article = new ReviewDataBean();
					article.setNum(rs.getInt( "r_number" ));
					article.setSubject(rs.getString("r_subject"));
					article.setContent(rs.getString("r_content"));
					article.setReg_date(rs.getTimestamp("r_reg_date"));
					article.setReadcount(rs.getInt("r_readcount"));
					article.setRef(rs.getInt("r_ref"));
					article.setRe_step(rs.getInt("r_re_step"));
					article.setRe_level(rs.getInt("r_re_level"));
					article.setId(rs.getString("id"));
					article.setGoods_name(rs.getString("goods_name"));
					
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
}
