package mall.client.controller.Cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.CartDao;
import mall.client.vo.Cart;


@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	
	CartDao cartDao;
	
	
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		
		this.cartDao = new CartDao();
		Cart cart = new Cart();
		cart.setCartNo(cartNo);
		cartDao.deleteCart(cart);
		
		response.sendRedirect(request.getContextPath() + "/CartListController");
	}

}
