package handler.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.CalendarDBBean;
import calendar.CalendarDataBean;
import handler.CommandHandler;

public class CdModifyFormHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		String id = (String)request.getSession().getAttribute("memId");
		
		CalendarDBBean cdDao = CalendarDBBean.getInstance();
		CalendarDataBean cdDto = cdDao.getSchedule(year, month, day, id);
		
		request.setAttribute("cdDto", cdDto);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		
		return "/calendar/cdModifyForm.jsp";
	}

}
