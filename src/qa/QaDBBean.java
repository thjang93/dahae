package qa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import qa.QaDataBean;

public class QaDBBean {
	private static QaDBBean instance = new QaDBBean();
	public static QaDBBean getInstance(){
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
			String sql ="select count(*) from dh_qa";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)"); //어차피 하나만 나오니까 그냥 숫자 1을 입력함.
				count = rs.getInt( 1 ); //DB는 1번부터 시작한다.
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
	
	public int insertArticle( QaDataBean qaDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			
			int num = qaDto.getNum();
			int ref = qaDto.getRef();
			int re_step = qaDto.getRe_step();
			int re_level = qaDto.getRe_level();
			
			String sql = null;
			
			if( num == 0 ){
				//제목글인 경우 : ref값만 가장 큰 값이면 된다. DB에 있는 num값...
				sql = "select max(q_number) from dh_qa";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if( rs.next() ){
					//글이 있는 경우
					ref = rs.getInt( 1 ) + 1; 
					//getInt안에 컬럼의 이름을 쓰지만, 
					//하나만 나오는 거라면 첫번째 값이라는 뜻으로 숫자1을 써도 된다. 
				} else {
					//글이 없는 경우 DB에 있는 num은 없다.
					ref = 1;
				}
				re_step = 0;
				re_level = 0;
			} else {
				//답변글인 경우 : 
				//(같은 제목글에 달린 답변글들의 숫자를 증가시키기 위해)
				//자신과 같은 그룹(ref)이면서 글순서(re_step)가 쓰려는답변글보다 큰 글들의
				// re_step과 re_level값을 1씩 증가시킨다. 
				sql = "update dh_qa set q_re_step = q_re_step+1 "
						+ "where q_ref=? and q_re_step>?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step ++;
				re_level ++;
			}
			
			//readcount를 뺀 나머지 값들을 insert한다.(작성한 글들을 실제로 DB에 넣음)
			//readcount는 readcount number default 0로 설정해놨기 때문에 자동으로 0값이 들어간다. 
			//위의 변수num과 DB에 넣는 num값은 다르다. 
			//DB에 넣는 num같은 sequence로 넣어줘야 한다. 
			sql = "insert into dh_qa( q_number, q_subject, q_content, "
					+ "q_reg_date, q_ref, q_re_step, q_re_level, "
					+ "id, q_readcount ) "
			 		+ "values( qa_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 0 )";
			pstmt.close();
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, qaDto.getSubject() );
			pstmt.setString( 2, qaDto.getContent() );
			pstmt.setTimestamp( 3, qaDto.getReg_date() );
			pstmt.setInt( 4, ref); //ref와 re_step, re_level에는 가공한 값을 넣어야 한다.
			pstmt.setInt( 5, re_step);
			pstmt.setInt( 6, re_level);
			pstmt.setString( 7, qaDto.getId() );

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
	public ArrayList<QaDataBean> getArticles(int start, int end ){
		ArrayList<QaDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select q_number,q_subject,q_content,q_reg_date,q_readcount,q_ref,q_re_step,q_re_level,id,q from (select q_number,q_subject,q_content,q_reg_date,q_readcount,q_ref,q_re_step,q_re_level,id,rownum q from (select q_number,q_subject,q_content,q_reg_date,q_readcount,q_ref,q_re_step,q_re_level,id from dh_qa order by q_ref DESC, q_re_step asc) order by q_ref DESC,q_re_step asc) where q >= ? and q <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, start );
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList <QaDataBean>();
				do{
					QaDataBean article = new QaDataBean();
					article.setNum(rs.getInt( "q_number" ));
					article.setSubject(rs.getString("q_subject"));
					article.setContent(rs.getString("q_content"));
					article.setReg_date(rs.getTimestamp("q_reg_date"));
					article.setReadcount(rs.getInt("q_readcount"));
					article.setRef(rs.getInt("q_ref"));
					article.setRe_step(rs.getInt("q_re_step"));
					article.setRe_level(rs.getInt("q_re_level"));
					article.setId(rs.getString("id"));
					
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
	public QaDataBean getArticle( int num ){
		QaDataBean article = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select q_number,q_subject,q_content,q_readcount, ";
			   sql += "q_reg_date,q_ref,q_re_step,q_re_level,id ";
			   sql += "from dh_qa ";
			   sql += "where q_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if( rs.next() ){
				article = new QaDataBean();
				article.setNum(rs.getInt("q_number"));
				article.setSubject(rs.getString("q_subject"));
				article.setContent(rs.getString("q_content"));
				article.setReadcount(rs.getInt("q_readcount"));
				article.setReg_date(rs.getTimestamp("q_reg_date"));
				article.setRef(rs.getInt("q_ref"));
				article.setRe_step(rs.getInt("q_re_step"));
				article.setRe_level(rs.getInt("q_re_level"));
				article.setId(rs.getString("id"));				
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
			String sql ="update dh_qa set q_readcount=q_readcount+1 where q_number=?";
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
			String sql ="select * from dh_qa where q_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				if( id.equals(rs.getString("id"))){
					//아이디가 같다
					result = 1;
				}else{
					//아이디가 다르다
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
			QaDataBean qaDto = getArticle( num );
			int ref = qaDto.getRef();
			int re_step = qaDto.getRe_step();
			int re_level = qaDto.getRe_level(); 
			//re_step은 1이 커야 하고
			//ref는 같아야 하고
			//re_level은 커야 한다.
			
			String sql = "select * from dh_qa "
						+ "where q_ref=? and q_re_step=?+1 and q_re_level>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_step);
			pstmt.setInt(3, re_level);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				//답글이 있는 경우
				result = -1;
			}else{
				//답글이 없는 경우
				sql = "update dh_qa set q_re_step=q_re_step-1 where q_ref=? and q_re_step>?";
				pstmt.close();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				sql = "delete from dh_qa where q_number=?";
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
	
	public int updateArticle( QaDataBean qaDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update dh_qa set q_subject=?,q_content=? where q_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qaDto.getSubject());
			pstmt.setString(2, qaDto.getContent());
			pstmt.setInt(3, qaDto.getNum());
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
	
	//검색한 리스트
	public int getSearchCount(String search_code, String keyword){
		int count =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql =null;
			String a = "q_subject";
			if(search_code.equals(a) ){
				sql = "select count(*) from dh_qa where q_subject like ?";
			}else{
				sql = "select count(*) from dh_qa where id like ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)"); //어차피 하나만 나오니까 그냥 숫자 1을 입력함.
				count = rs.getInt( 1 ); //DB는 1번부터 시작한다.
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
	public ArrayList<QaDataBean> getSearchArticles(int start, int end, String search_code, String keyword){
		
		ArrayList<QaDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql =null;
			String title = "q_subject";
			if(search_code.equals(title)){
				sql = "select q_number,q_subject,q_content,q_reg_date,q_readcount,"
						+ "q_ref,q_re_step,q_re_level,id,q from "
						+ "(select q_number,q_subject,q_content,q_reg_date,q_readcount,"
						+ "q_ref,q_re_step,q_re_level,id,rownum q from "
						+ "(select q_number,q_subject,q_content,q_reg_date,q_readcount,"
						+ "q_ref,q_re_step,q_re_level,id "
						+ "from dh_qa "						
						+ "where q_subject like '%'||?||'%' "
						+ "order by q_ref desc,q_re_step asc) "
						+ "order by q_ref desc,q_re_step asc) "
						+ "where q >= ? and q <= ?";
			}else{
				sql = "select q_number,q_subject,q_content,q_reg_date,q_readcount,"
						+ "q_ref,q_re_step,q_re_level,id,q from "
						+ "(select q_number,q_subject,q_content,q_reg_date,q_readcount,"
						+ "q_ref,q_re_step,q_re_level,id,rownum q from "
						+ "(select q_number,q_subject,q_content,q_reg_date,q_readcount,"
						+ "q_ref,q_re_step,q_re_level,id "
						+ "from dh_qa "
						+ "where id like '%'||?||'%' "
						+ "order by q_ref desc,q_re_step asc) "
						+ "order by q_ref desc,q_re_step asc) "
						+ "where q >= ? and q <= ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start );
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList <QaDataBean>();
				do{
					QaDataBean article = new QaDataBean();
					article.setNum(rs.getInt( "q_number" ));
					article.setSubject(rs.getString("q_subject"));
					article.setContent(rs.getString("q_content"));
					article.setReg_date(rs.getTimestamp("q_reg_date"));
					article.setReadcount(rs.getInt("q_readcount"));
					article.setRef(rs.getInt("q_ref"));
					article.setRe_step(rs.getInt("q_re_step"));
					article.setRe_level(rs.getInt("q_re_level"));
					article.setId(rs.getString("id"));				
					
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
