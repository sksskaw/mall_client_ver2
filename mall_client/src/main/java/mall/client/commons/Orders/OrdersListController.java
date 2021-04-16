package mall.client.commons.Orders;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.OrdersDao;
import mall.client.vo.Client;

@WebServlet("/OrdersListController")
public class OrdersListController extends HttpServlet {

	private OrdersDao ordersDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}		
		
		Client client = (Client)session.getAttribute("loginClient");
		
		// 주문 정보 가져오기
		this.ordersDao = new OrdersDao();
		List<Map<String, Object>> ordersList = this.ordersDao.selectOrderListByClient(client.getClientNo());
		request.setAttribute("ordersList", ordersList); // 주문 리스트 request객체에 셋팅
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersList.jsp").forward(request, response);
		
	}

}
