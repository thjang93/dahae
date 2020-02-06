package calendar;

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

public class CalendarDBBean {
	private static CalendarDBBean instance = new CalendarDBBean();
	public static CalendarDBBean getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws NamingException, SQLException{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/kh");
		return ds.getConnection();
	}
	
	public int insertSch(CalendarDataBean cdDto){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "insert into dh_calendar"
						+ "(cd_number,id,cd_year,cd_month,cd_day,"
						+ "breakfast,lunch,dinner,ex1,ex2,ex3) " 
						+ "values( calendar_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cdDto.getId());
			pstmt.setInt(2, cdDto.getCd_year());
			pstmt.setInt(3, cdDto.getCd_month());
			pstmt.setInt(4, cdDto.getCd_day());
			pstmt.setString(5, cdDto.getBreakfast());
			pstmt.setString(6, cdDto.getLunch());
			pstmt.setString(7, cdDto.getDinner());
			pstmt.setString(8, cdDto.getEx1());
			pstmt.setString(9, cdDto.getEx2());
			pstmt.setString(10, cdDto.getEx3());
			
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
	
	//해당 월의 일정이 몇개인지
	public int getCount(int year, int month, String id){
		int countsch = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_calendar where cd_year = ? and cd_month = ? and id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)");
				countsch = rs.getInt( 1 );
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
		return countsch;
	}
	public HashMap<Integer,ArrayList<String>> getSchedules(int year, int month, 
			String id, HashMap<Integer, ArrayList<String>> calendar){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select cd_day,breakfast,lunch,dinner,ex1,ex2,ex3 "
					+"from dh_calendar where cd_year = ? and cd_month = ? and id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, year );
			pstmt.setInt( 2, month);
			pstmt.setString( 3, id );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				do{
					ArrayList<String> cdCtoArray = new ArrayList<String>();
					cdCtoArray.add(rs.getString("breakfast"));
					cdCtoArray.add(rs.getString("lunch"));
					cdCtoArray.add(rs.getString("dinner"));
					cdCtoArray.add(rs.getString("ex1"));
					cdCtoArray.add(rs.getString("ex2"));
					cdCtoArray.add(rs.getString("ex3"));
					
					calendar.put( rs.getInt("cd_day"), cdCtoArray ); 
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
		return calendar;
	}
	//해당 날짜에 일정이 있는지
	public int getCount(int year, int month, int day, String id){
		int countsch = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql ="select count(*) from dh_calendar where cd_year = ? and cd_month = ? and cd_day =? and id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setInt(3, day);
			pstmt.setString(4, id);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				//rs.getInt("count(*)");
				countsch = rs.getInt( 1 );
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
		return countsch;
	}
	public CalendarDataBean getSchedule(int year, int month, int day, String id){
		CalendarDataBean schedule = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select cd_day,breakfast,lunch,dinner,ex1,ex2,ex3 "
					+"from dh_calendar where cd_year = ? and cd_month = ? and cd_day =? and id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt( 1, year );
			pstmt.setInt( 2, month);
			pstmt.setInt( 3, day);
			pstmt.setString( 4, id );
			
			rs = pstmt.executeQuery();
			if( rs.next() ){
				schedule = new CalendarDataBean();
				schedule.setBreakfast(rs.getString("breakfast"));
				schedule.setLunch(rs.getString("lunch"));
				schedule.setDinner(rs.getString("dinner"));
				schedule.setEx1(rs.getString("ex1"));
				schedule.setEx2(rs.getString("ex2"));
				schedule.setEx3(rs.getString("ex3"));
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
		return schedule;
	}
	//일정 수정하기
	public int updateSch(CalendarDataBean cdDto){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "update dh_calendar set breakfast=?,lunch=?,dinner=?,ex1=?,ex2=?,ex3=? "
					+ "where id=? and cd_year=? and cd_month=? and cd_day=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cdDto.getBreakfast());
			pstmt.setString(2, cdDto.getLunch());
			pstmt.setString(3, cdDto.getDinner());
			pstmt.setString(4, cdDto.getEx1());
			pstmt.setString(5, cdDto.getEx2());
			pstmt.setString(6, cdDto.getEx3());
			pstmt.setString(7, cdDto.getId());
			pstmt.setInt(8, cdDto.getCd_year());
			pstmt.setInt(9, cdDto.getCd_month());
			pstmt.setInt(10, cdDto.getCd_day());
			
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
	//일정삭제하기
	public int deleteSch(CalendarDataBean cdDto){
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "delete from dh_calendar "
					+ "where id=? and cd_year=? and cd_month=? and cd_day=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cdDto.getId());
			pstmt.setInt(2, cdDto.getCd_year());
			pstmt.setInt(3, cdDto.getCd_month());
			pstmt.setInt(4, cdDto.getCd_day());
			
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
