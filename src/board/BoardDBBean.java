package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	public static BoardDBBean getInstance(){
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
			String sql ="select count(*) from board";
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
	
	public int insertArticle( BoardDataBean boardDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			/*						ref		re_step		re_level 
			 * 11	제목글			12		0			0
			 * 10	 ㄴ답글			12		1			1
			 * 9	   ㄴ재답글		12		2			2
			 * 8	 ㄴ답글			12		1			1 ->얘를 10번답글 위로 올릴것이다...
			 * 	
			 * re_step과 re_level은 원글에서 가져왔다가 1씩 증가시킨다.
			 * 답글이 두개이상이 될 경우 꼬이게 되므로, 
			 * update를 먼저하고(다른 답변글들의 re_step번호 변경을 위해) 
			 * insert를 해줘야 한다.(글쓴거 DB에 담기) 
			 */
			
			/*						ref		re_step		re_level 
			 * 11	제목글			12		0			0
			 *  8	 ㄴ답글			12		1			1
			 * 10	 ㄴ답글			12		2			1 -> re_step을 1증가시킨다.
			 * 	9	   ㄴ재답글		12		3			2 -> re_step을 1증가시킨다	
			 */
			
			/*						ref		re_step		re_level 
			 * 12	제목글			14		0			0
			 * 11	제목글			12		0			0
			 *  8	 ㄴ답글			12		1			1
			 * 10	 ㄴ답글			12		2			1 -> re_step을 1증가시킨다.
			 * 	9	   ㄴ재답글		12		3			2 -> re_step을 1증가시킨다	
			 */
			
			int num = boardDto.getNum();
			int ref = boardDto.getRef();
			int re_step = boardDto.getRe_step();
			int re_level = boardDto.getRe_level();
			
			String sql = null;
			
			//num은 writeForm.jsp에서 0으로 받아왔다면 제목글
			//content.form의 reply에서 1을 받아온다면 답변글
			
			if( num == 0 ){
				//제목글인 경우 : ref값만 가장 큰 값이면 된다. DB에 있는 num값...
				sql = "select max(num) from board";
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
				sql = "update board set re_step = re_step+1 "
						+ "where ref=? and re_step>?";
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
			sql = "insert into board( num, writer, email, subject, "
					+ "passwd, reg_date, ref, re_step, re_level, content, ip ) "
			 		+ "values( board_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			pstmt.close();
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, boardDto.getWriter() );
			pstmt.setString( 2, boardDto.getEmail() );
			pstmt.setString( 3, boardDto.getSubject() );
			pstmt.setString( 4, boardDto.getPasswd() );
			pstmt.setTimestamp( 5, boardDto.getReg_date() );
			pstmt.setInt( 6, ref); //ref와 re_step, re_level에는 가공한 값을 넣어야 한다.
			pstmt.setInt( 7, re_step);
			pstmt.setInt( 8, re_level);
			pstmt.setString( 9, boardDto.getContent() );
			pstmt.setString( 10, boardDto.getIp() );
			
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
	
	public ArrayList<BoardDataBean> getArticles(int start, int end ){
		ArrayList<BoardDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select num,writer,email,subject,passwd,";
			sql+= "reg_date,ref,re_step,re_level,content,ip,readcount,r ";
			sql+= "from (select num,writer,email,subject,passwd,reg_date,ref,re_step";
			sql+= ",re_level,content,ip,readcount,rownum r from ";
			sql+= "(select num,writer,email,subject,passwd,reg_date,ref,re_step,re_level ";
			sql+= ",content,ip,readcount from board order by ref desc, re_step asc) ";
			sql+= "order by ref desc, re_step asc ) where r >= ? and r <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, start );
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList <BoardDataBean>();
				do{
					BoardDataBean article = new BoardDataBean();
					article.setNum(rs.getInt( "num" ));
					article.setWriter(rs.getString( "writer" ));
					article.setEmail(rs.getString( "email" ));
					article.setSubject(rs.getString( "subject" ));
					article.setPasswd(rs.getString( "passwd" ));
					article.setReg_date(rs.getTimestamp( "reg_date" ));
					article.setReadcount(rs.getInt( "readcount" ));
					article.setRef(rs.getInt( "ref" ));
					article.setRe_step(rs.getInt( "re_step" ));
					article.setRe_level(rs.getInt( "re_level" ));
					article.setContent(rs.getString( "content" ));
					article.setIp(rs.getString( "ip" ));
					
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
	
	public BoardDataBean getArticle( int num ){
		BoardDataBean article = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if( rs.next() ){
				article = new BoardDataBean();
				article.setNum(rs.getInt( "num" ));
				article.setWriter(rs.getString( "writer" ));
				article.setEmail(rs.getString( "email" ));
				article.setSubject(rs.getString( "subject" ));
				article.setPasswd(rs.getString( "passwd" ));
				article.setReg_date(rs.getTimestamp( "reg_date" ));
				article.setReadcount(rs.getInt( "readcount" ));
				article.setRef(rs.getInt( "ref" ));
				article.setRe_step(rs.getInt( "re_step" ));
				article.setRe_level(rs.getInt( "re_level" ));
				article.setContent(rs.getString( "content" ));
				article.setIp(rs.getString( "ip" ));
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
			String sql ="update board set readcount=readcount+1 where num=?";
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
	
	public int check( int num, String passwd){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				if( passwd.equals(rs.getString("passwd"))){
					//비밀번호가 같다
					result = 1;
				}else{
					//비밀번호가 다르다
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
	
	public int updateArticle( BoardDataBean boardDto ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update board set email=?, subject=?, content=?, passwd=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDto.getEmail());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setString(4, boardDto.getPasswd());
			pstmt.setInt(5, boardDto.getNum());
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
	
	public int deleteArticle( int num ){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			BoardDataBean boardDto = getArticle( num );
			int ref = boardDto.getRef();
			int re_step = boardDto.getRe_step();
			int re_level = boardDto.getRe_level(); 
			//re_step은 1이 커야 하고
			//ref는 같아야 하고
			//re_level은 커야 한다.
			
			String sql = "select * from board "
						+ "where ref=? and re_step=?+1 and re_level>?";
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
				sql = "update board set re_step=re_step-1 where ref=? and re_step>?";
				pstmt.close();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				sql = "delete from board where num=?";
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
}
