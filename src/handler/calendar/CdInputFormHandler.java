package handler.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.CalendarDBBean;
import calendar.CalendarDataBean;
import handler.CommandHandler;

public class CdInputFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		String id = (String)request.getSession().getAttribute("memId");
	
		
		int countsch = 0; //일정이 몇개인지
		
		CalendarDBBean cdDao = CalendarDBBean.getInstance();
		countsch = cdDao.getCount(year, month, day, id);	//해당 날짜에 일정이 이미 있는지 확인하기
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);

		if(countsch == 0){
			return "/calendar/cdInputForm.jsp";
		}else{
			CalendarDataBean cdDto = cdDao.getSchedule(year, month, day, id);
			request.setAttribute("cdDto", cdDto);
			
			return "/calendar/cdContent.jsp";
		}
		
	}

}
