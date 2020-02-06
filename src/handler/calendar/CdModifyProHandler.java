package handler.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.CalendarDBBean;
import calendar.CalendarDataBean;
import handler.CommandHandler;

public class CdModifyProHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		CalendarDataBean cdDto = new CalendarDataBean();
		cdDto.setId((String)request.getSession().getAttribute("memId"));
		cdDto.setCd_year(Integer.parseInt(request.getParameter("year")));
		cdDto.setCd_month(Integer.parseInt(request.getParameter("month")));
		cdDto.setCd_day(Integer.parseInt(request.getParameter("day")));
		cdDto.setBreakfast(request.getParameter("breakfast"));
		cdDto.setLunch(request.getParameter("lunch"));
		cdDto.setDinner(request.getParameter("dinner"));
		cdDto.setEx1(request.getParameter("ex1"));
		cdDto.setEx2(request.getParameter("ex2"));
		cdDto.setEx3(request.getParameter("ex3"));

		CalendarDBBean cdDao = CalendarDBBean.getInstance();
		int result = cdDao.updateSch( cdDto );
		
		request.setAttribute("result", result);
		  
		return "/calendar/cdModifyPro.jsp";
	}

}
