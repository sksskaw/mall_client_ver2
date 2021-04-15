package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.ClientDao;
import mall.client.vo.Cart;
import mall.client.vo.Client;

@WebServlet("/InsertCartController")
public class InsertCartController extends HttpServlet {

	private CartDao cartDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath() + "IndexController");
			return;
		}

		request.setCharacterEncoding("utf-8");
		
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();
		
		this.cartDao = new CartDao();
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(clientMail);

		if(this.cartDao.selectCartNo(cart) == 0) {
			this.cartDao.insertCart(cart);
		} else {
			System.out.println("카트에 중복된 데이터 존재");
		}
		
		response.sendRedirect(request.getContextPath()+"/CartListController");
	}

}
