package mall.client.controller.Client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/DeleteClientController")
public class DeleteClientController extends HttpServlet {

	ClientDao clientDao;
	
	// 회원 탈퇴 전 비밀번호 입력 받기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 로그인 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/client/deleteClient.jsp").forward(request, response);
	}
	
	// 삭제 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 우선 입력한 비밀번호가 맞는지 체크

		// 세션에 있는 로그인 정보 가져오기
		HttpSession session = request.getSession();
		Client client = (Client)session.getAttribute("loginClient");
		
		
		String currentPw = request.getParameter("currentPw");
		client.setClientPw(currentPw);
		
		// 현재 비밀번호가 맞는지 체크
		clientDao = new ClientDao();
		if(clientDao.checkClientCurrenPw(client) != null) { // 현재 비밀번호가 맞으면 회원 계정 삭제
			
			// 회원 탈퇴 이전에 장바구니 목록 삭제
			CartDao cartDao = new CartDao();
			cartDao.deleteCartAll(client.getClientMail()); // 장바구니 목록 삭제
			clientDao.deleteClient(client); // 회원 계정 삭제 메소드 호출
			session.invalidate(); // 회원계정 삭제 후 세션 초기화(로그아웃)
		} else {
			System.out.println("현재 비밀번호가 틀렸습니다!");
			response.sendRedirect(request.getContextPath() + "/SelectOneClientController");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/IndexController");
	}

}
