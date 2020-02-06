package handler.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.CalendarDBBean;
import calendar.CalendarDataBean;
import handler.CommandHandler;

public class CdFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		//현재 날짜 정보 
		Calendar  cr = Calendar.getInstance();
		int year = cr.get(Calendar.YEAR);
		int month = cr.get(Calendar.MONTH);
		int date = cr.get(Calendar.DATE);
		 
		//오늘 날짜
		String today = year + "/" +(month+1)+ "/"+date; 
		 
		//선택한 연도 / 월
		String input_year = request.getParameter("year");
		String input_month = request.getParameter("month");
		 
		if(input_month != null){
		 month = Integer.parseInt(input_month)-1;
		}
		if(input_year != null){
		 year = Integer.parseInt(input_year);
		}
		// 1일부터 시작하는 달력을 만들기 위해 오늘의 연도,월을 셋팅하고 일부분은 1을 셋팅한다.
		cr.set(year, month, 1);
		 
		// 셋팅한 날짜로 부터 아래 내용을 구함
		 
		// 해당 월의 첫날를 구함
		int startDate = cr.getMinimum(Calendar.DATE);
		 
		// 해당 월의 마지막 날을 구함
		int endDate = cr.getActualMaximum(Calendar.DATE);
		 
		// 1일의 요일을 구함
		int startDay = cr.get(Calendar.DAY_OF_WEEK);
		 
		int count = 0;
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("today", today);
		request.setAttribute("input_year", input_year);
		request.setAttribute("input_month", input_month);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("startDay", startDay);
		request.setAttribute("count", count);	
		
		int countsch = 0; //일정이 몇개인지
		
		if(request.getSession().getAttribute("memId") != null){
			String id = (String)request.getSession().getAttribute("memId");
			CalendarDBBean cdDao = CalendarDBBean.getInstance();
			countsch = cdDao.getCount(year, month+1, id); //해당 월에 일정이 몇개인지 알아보기
			
			if(countsch != 0){
				//가져온 일정을 달력에 담기
				HashMap<Integer, ArrayList<String>> calendar = new HashMap <Integer, ArrayList<String>>();
				for(int i = startDate; i <= endDate; i++){
						calendar.put(i, null);
				}//달력날짜 담기
				
				//달력에 db에 있는 일정 추가하기
				HashMap<Integer, ArrayList<String>> calendar2 = cdDao.getSchedules(year, month+1, id, calendar);
				request.setAttribute("calendar2", calendar2);
			}
				
		}
		
		return "/calendar/cdForm.jsp";
	}
	
}
