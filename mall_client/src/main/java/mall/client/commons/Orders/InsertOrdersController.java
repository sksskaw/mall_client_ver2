package mall.client.commons.Orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.*;
import mall.client.vo.*;

@WebServlet("/InsertOrdersController")
public class InsertOrdersController extends HttpServlet {

	private OrdersDao ordersDao;
	private CartDao cartDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}

		this.ordersDao = new OrdersDao();
		this.cartDao = new CartDao();
		
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		Client client = (Client)session.getAttribute("loginClient");
		
		Orders orders = new Orders();
		orders.setEbookNo(ebookNo);
		orders.setClientNo(client.getClientNo());
		
		Cart cart = new Cart();
		cart.setCartNo(cartNo);
		
		// 주문 완료 후 장바구니 목록에서 제거하기
		ordersDao.insertOrders(orders);
		cartDao.deleteCart(cart);
		
		response.sendRedirect(request.getContextPath() + "/OrdersListController");
	}

}
