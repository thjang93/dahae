package notice;

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

import notice.Dh_noticeDataBean;



public class Dh_noticeDBBean {
 private static Dh_noticeDBBean instance =new Dh_noticeDBBean();
 public static Dh_noticeDBBean  getInstance(){
	 return instance;
 }

	public Connection getConnection() throws NamingException, SQLException {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup( "java:comp/env" );
		DataSource ds = (DataSource) envCtx.lookup( "jdbc/kh" );
		return ds.getConnection();
	}
 
	public int getCount(){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
  try {
		con = getConnection();
		String sql = "select count(*) from dh_notice";
		pstmt = con.prepareStatement( sql );
		rs = pstmt.executeQuery();
		if( rs.next() ) {
			count = rs.getInt( 1 );
		}
		
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if( rs != null ) rs.close();
			if( pstmt != null ) pstmt.close();
			if( con != null ) con.close();				
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}		
	return count;
}
	
	
	public int getImp_count(){
		int imp_count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
  try {
		con = getConnection();
		String sql = "select count(*) from dh_notice where n_imp_level = 1";
		pstmt = con.prepareStatement( sql );
		rs = pstmt.executeQuery();
		if( rs.next() ) {
			imp_count = rs.getInt( 1 );
		}
		
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if( rs != null ) rs.close();
			if( pstmt != null ) pstmt.close();
			if( con != null ) con.close();				
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}		
  System.out.println(imp_count);
	return imp_count;
}


	public int insertArticle(Dh_noticeDataBean boardDto)
	{
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			
			String sql = "insert into dh_notice(n_number, n_subject, n_reg_date, n_content, n_imp_level, n_readcount, id)"
					+ "values(notice_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql); 
            
			
			pstmt.setString(1, boardDto.getN_subject());		
			pstmt.setTimestamp(2, boardDto.getN_reg_date());
			pstmt.setString(3, boardDto.getN_content());
			pstmt.setInt(4, boardDto.getN_imp_level());
			pstmt.setInt(5, 0);
			pstmt.setString(6, boardDto.getId());
			result = pstmt.executeUpdate();
			
			}catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}		
		
		
		return result;
	}
	
	//getNotice(start,end);
	
	public ArrayList<Dh_noticeDataBean> getNotice(int start, int end){
		ArrayList<Dh_noticeDataBean> notice = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();		
			
			String sql = "select n_number, n_subject, n_content, n_readcount";
			sql+= ",n_reg_date , n_imp_level, id, r ";
			sql+= "from (select n_number, n_subject, n_content, n_readcount, n_reg_date";
			sql+= ",n_imp_level, id, rownum r from ";
			sql+= "(select n_number, n_subject, n_content, n_readcount, n_reg_date";
			sql+= ", n_imp_level, id from dh_notice where n_imp_level = 1 order by n_number desc) )";
			sql+= " where r >= ? and r <= ?";	
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);			
			rs =pstmt.executeQuery();			
			if(rs.next()){
				notice = new ArrayList <Dh_noticeDataBean>();
				do{
					Dh_noticeDataBean noti = new Dh_noticeDataBean();
			        
					noti.setN_number(rs.getInt("n_number"));
					noti.setN_subject(rs.getString("n_subject"));
					noti.setN_content(rs.getString("n_content"));
					noti.setN_readcount(rs.getInt("n_readcount"));
					noti.setN_reg_date(rs.getTimestamp("n_reg_date"));
					noti.setN_imp_level(rs.getInt("n_imp_level"));
					noti.setId(rs.getString("id"));
					notice.add(noti); 
					
				}while(rs.next());
			}
	
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}		
		
		
		return notice;
	}

	public ArrayList<Dh_noticeDataBean> getArticles(int start, int end){
		ArrayList<Dh_noticeDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select n_number, n_subject, n_content, n_readcount";
			sql+= ",n_reg_date , n_imp_level, id, r ";
			sql+= "from (select n_number, n_subject, n_content, n_readcount, n_reg_date";
			sql+= ",n_imp_level, id, rownum r from ";
			sql+= "(select n_number, n_subject, n_content, n_readcount, n_reg_date";
			sql+= ", n_imp_level, id from dh_notice where n_imp_level = 0 order by n_number desc ) )";
			sql+= " where r >= ? and r <= ?";	
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs =pstmt.executeQuery();	
			if(rs.next()){
				articles = new ArrayList <Dh_noticeDataBean>();
				do{
					Dh_noticeDataBean article = new Dh_noticeDataBean();
			        
					article.setN_number(rs.getInt("n_number"));
					article.setN_subject(rs.getString("n_subject"));
					article.setN_content(rs.getString("n_content"));
					article.setN_readcount(rs.getInt("n_readcount"));
					article.setN_reg_date(rs.getTimestamp("n_reg_date"));
					article.setN_imp_level(rs.getInt("n_imp_level"));
				    article.setId(rs.getString("id"));
					articles.add(article); 
					
				}while(rs.next());
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}		
		
		
		return articles;
	}
	
	public Dh_noticeDataBean getAticle(int num){
		Dh_noticeDataBean article = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from dh_notice where n_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new Dh_noticeDataBean();
				article.setN_number(rs.getInt("n_number"));
				article.setN_subject(rs.getString("n_subject"));
				article.setN_content(rs.getString("n_content"));
				article.setN_readcount(rs.getInt("n_readcount"));
				article.setN_reg_date(rs.getTimestamp("n_reg_date"));
				article.setN_imp_level(rs.getInt("n_imp_level"));
			    article.setId(rs.getString("id"));
				
				
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}	
		
		return article;
	}
	
	public void addCount(int num){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql = "update dh_notice set n_readcount=n_readcount+1 where n_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
		
			pstmt.executeUpdate();
		
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}	
		
		
	}
 

public int check (int n_number, String id){
	  int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from dh_notice where n_number=? and id=?";
			pstmt = con.prepareStatement( sql );
			pstmt.setInt( 1, n_number);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			System.out.println(id);
			
              if( rs.next() ) {
				
				if( id.equals( rs.getString( "id" ) ) ) {				
					result = 1;
				} else {
					result = 0;
				}
				
				}
			
		
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}			
		return result;

   }

public int updateArticle(Dh_noticeDataBean boardDto){
	  
	  int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			String sql = "update dh_notice set n_subject=?, n_content=? where n_number=?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString(1, boardDto.getN_subject());
			pstmt.setString(2, boardDto.getN_content());
			pstmt.setInt(3, boardDto.getN_number());
			
			result = pstmt.executeUpdate();
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}		
		
		
		return result;
	}


public int deleteAtricle(int n_number){
	  
	  int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			Dh_noticeDataBean boardDto = getAticle(n_number);
			int imp_level = boardDto.getN_imp_level();
			
			String sql = "delete from dh_notice where n_number=?";

				pstmt = con.prepareStatement( sql );
				pstmt.setInt(1, n_number);
				result = pstmt.executeUpdate();
				
				
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs !=null) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close();				
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}		
		
		
		return result;
	}

}

