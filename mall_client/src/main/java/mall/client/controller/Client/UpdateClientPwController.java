package mall.client.controller.Client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/UpdateClientPwController")
public class UpdateClientPwController extends HttpServlet {
	
	ClientDao clientDao;
	
	// 비밀번호 수정 버튼을 눌렀을 때 입력 폼으로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/client/updateClientPw.jsp").forward(request, response);
	}

	// 비밀번호 입력받아서 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션에 있는 로그인 정보 가져오기
		HttpSession session = request.getSession();
		Client client = (Client)session.getAttribute("loginClient");
		
		
		String currentPw = request.getParameter("currentPw");
		String editPw = request.getParameter("editPw");
		client.setClientPw(currentPw);
		
		// 현재 비밀번호가 맞는지 체크
		clientDao = new ClientDao();
		if(clientDao.checkClientCurrenPw(client) != null) { // 현재 비밀번호가 맞으면 수정 메소드 호출
			client.setClientPw(editPw);
			clientDao.updateClientPw(client);
			session.invalidate(); // 비밀번호 수정 후 세션 초기화(로그아웃)
		} else {
			System.out.println("현재 비밀번호가 틀렸습니다!");
			response.sendRedirect(request.getContextPath() + "/SelectOneClientController");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/IndexController");
	}

}
