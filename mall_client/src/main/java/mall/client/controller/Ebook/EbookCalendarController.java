package mall.client.controller.Ebook;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;

@WebServlet("/EbookCalendarController")
public class EbookCalendarController extends HttpServlet {

	private EbookDao ebookDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 년/월에 매개값으로 전달되지 않으면
		Calendar dday = Calendar.getInstance();
		int currentYear = dday.get(Calendar.YEAR);
		int currentMonth = dday.get(Calendar.MONTH) + 1;
		
		if(request.getParameter("currentYear") != null) {
			currentYear = Integer.parseInt(request.getParameter("currentYear"));
			dday.set(Calendar.YEAR,currentYear);
		}
		
		if(request.getParameter("currentMonth") != null) {
			currentMonth = Integer.parseInt(request.getParameter("currentMonth"));
			dday.set(Calendar.MONTH,currentMonth-1); // 0이 1월
		}
		
		int lastDayOfMonth = dday.getActualMaximum(Calendar.DAY_OF_MONTH); // 말일 구하기
		
		dday.set(Calendar.DATE,1);
		System.out.println("dday " + dday);
		int startDayOfTheWeek = dday.get(Calendar.DAY_OF_WEEK);
		
		ebookDao = new EbookDao();
		List<Map<String, Object>> EbookListByMonth = this.ebookDao.selectEbookListByMonth(currentYear, currentMonth);

		System.out.println("currentYear " + currentYear);
		System.out.println("currentMonth " + currentMonth);
		System.out.println("lastDayOfMonth " + lastDayOfMonth);
		System.out.println("startDayOfTheWeek " + startDayOfTheWeek);
		
		request.setAttribute("currentYear",currentYear);
		request.setAttribute("currentMonth",currentMonth);
		request.setAttribute("lastDayOfMonth",lastDayOfMonth);
		request.setAttribute("startDayOfTheWeek",startDayOfTheWeek);
		request.setAttribute("EbookListByMonth",EbookListByMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/ebook/ebookCalendar.jsp").forward(request, response);
	}

}
