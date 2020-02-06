package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class LogonDBBean {
	
	private static LogonDBBean instance = new LogonDBBean();
	public static LogonDBBean getInstance() {
		return instance;
	} 
	
	public Connection getConnnection() throws NamingException, SQLException {
		Context initCtx = new InitialContext();	
		Context envCtx = (Context) initCtx.lookup( "java:comp/env" );
		DataSource ds = (DataSource) envCtx.lookup( "jdbc/kh" );
		return ds.getConnection();
	}
	
	public int insertMember( LogonDataBean memberDto ) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = getConnnection();
			String sql = "insert into dh_member values( ?, member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, memberDto.getId() );
			pstmt.setString( 2, memberDto.getPasswd() );
			pstmt.setString( 3, memberDto.getName() );
			pstmt.setInt( 4, memberDto.getbirth_date() );
			pstmt.setString( 5, memberDto.getTel() );
			pstmt.setString( 6, memberDto.getZipcode());
			pstmt.setString( 7, memberDto.getAddress());
			pstmt.setString( 8, memberDto.getEmail() );
			pstmt.setTimestamp( 9, memberDto.getReg_date() );
			
			
			
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
	
	public int check( String id ) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnnection();
			String sql = "select * from dh_member where id=?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				result = 1;
			} else {
				result = 0;
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
	
	public int check( String id, String passwd ) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnnection();
			String sql = "select * from dh_member where id=?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1 , id );
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				// ?�뇡占�?占쎈턄?�얜�ｌ쾸? ?占쎈엿?占쎈펲
				if( passwd.equals( rs.getString( "passwd" ) ) ) {
					// 占쎈쑏熬곻옙?�뵓怨뺣쐡占쎄퉰�뤆占�? �뤆�룇�듋占쎈펲
					result = 1;
				} else {
					// 占쎈쑏熬곻옙?�뵓怨뺣쐡占쎄퉰�뤆占�? ?占쎈펲占쎈ご占쏙옙占쎈펲
					result = -1;
				}
				
			} else {
				// ?�뇡占�?占쎈턄?�얜�ｌ쾸? ?驪볩옙?占쎈펲
				result = 0;
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
	
	public int deleteMember( String id ) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnnection();
			String sql = "delete from dh_cart where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			result = pstmt.executeUpdate();
			pstmt.close();
			sql = "delete from dh_notice  where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			result = pstmt.executeUpdate();
			pstmt.close();
			sql = "delete from dh_order where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			result = pstmt.executeUpdate();		
			pstmt.close();
			sql = "delete from dh_review where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			result = pstmt.executeUpdate();
			pstmt.close();
			sql = "delete from dh_qa where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			result = pstmt.executeUpdate();
			pstmt.close();
			sql = "delete from dh_calendar where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			result = pstmt.executeUpdate();
			pstmt.close();
			sql = "delete from dh_member where id =?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
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
	
	public LogonDataBean getMember( String id ) {
		LogonDataBean memberDto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnnection();
			String sql = "select * from dh_member where id=?";
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, id );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				memberDto = new LogonDataBean();
				memberDto.setId( rs.getString( "id" ) );
				memberDto.setPasswd( rs.getString( "passwd" ) );
				memberDto.setName( rs.getString( "name" ) );
				memberDto.setbirth_date( rs.getInt( "birth_date" ) );
				memberDto.setZipcode(rs.getString("zipcode"));
				memberDto.setAddress(rs.getString("address"));
				memberDto.setTel( rs.getString( "tel" ) );
				memberDto.setEmail( rs.getString( "email" ) );
				memberDto.setReg_date( rs.getTimestamp( "reg_date" ) );
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
		return memberDto;
	}
	public ArrayList<LogonDataBean> getArticles(int start, int end ){
		ArrayList<LogonDataBean> articles = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnnection();
			String sql = "select member_number, id, name, birth_date, tel, reg_date, r "
					+ "from(select  member_number, id, name, birth_date, tel, reg_date, rownum r "
					+ "from(select  member_number, id, name, birth_date, tel, reg_date "
					+ "from dh_member order by member_number desc ) ) "
					+ "where r >=? and r <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start );
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				articles = new ArrayList<LogonDataBean>();
				do{
					LogonDataBean article = new LogonDataBean();
					article.setNum(rs.getString("member_number"));
					article.setId(rs.getString("id"));
					article.setName(rs.getString("name"));
					article.setbirth_date(rs.getInt("birth_date"));
					article.setTel(rs.getString("tel"));
					article.setReg_date(rs.getTimestamp( "reg_date" ));
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
			con = getConnnection();
			String sql ="select count(*) from dh_member";
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

	
	public int updateMember( LogonDataBean memberDto ) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnnection();
			String sql = "update dh_member set "
					+ "passwd=?, tel=?, email=?, zipcode=?, address=? where id=?";	
			pstmt = con.prepareStatement( sql );
			pstmt.setString( 1, memberDto.getPasswd() );
			pstmt.setString( 2, memberDto.getTel() );
			pstmt.setString( 3, memberDto.getEmail() );
			pstmt.setString( 4, memberDto.getZipcode());
			pstmt.setString( 5, memberDto.getAddress());
			pstmt.setString( 6, memberDto.getId() );
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
	
	public int sendEmail(String to, String content){
		int result = 0;
		Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
        
        Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("akinostudy@gmail.com","khamsmtp");//"Gmail占썩몿�뫒占쎌젧", "�뜝�럥�넮�뜝�럥裕욃뜝�럩�쐳�뜝�럥援�"
            }
        };
        
        try {
        	
	        Session sess = Session.getDefaultInstance(props,auth);
	        MimeMessage message = new MimeMessage(sess);
	        
	        message.setSubject("Please check the Certification number.");
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	       /* Multipart mp = new MimeMultipart();
	        MimeBodyPart mbp1 = new MimeBodyPart();
	        mbp1.setText( content );
	        mp.addBodyPart(mbp1);        
	        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
	        message.setContent(mp);*/
	        message.setContent(content, "text/html; charset=utf-8");
	        Transport.send(message);
	        System.out.println("Mail Send Sucess!!");
	        result = 1;
        }catch(Exception e){
        	e.printStackTrace();
        }
        return result;
	}
	
} // class












