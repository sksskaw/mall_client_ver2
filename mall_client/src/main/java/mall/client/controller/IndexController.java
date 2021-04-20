package mall.client.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.*;
import mall.client.vo.*;


@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	private OrdersDao ordersDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request 분석
		
		// 페이징 처리
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 15;
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 카테고리 선택
		String categoryName = null;
		if(request.getParameter("categoryName") != null ) {
			categoryName = request.getParameter("categoryName");
			
			if(request.getParameter("categoryName").equals("null") || request.getParameter("categoryName").equals("")) { // 문자열 null이 넘어올 경우 진짜 null로 변경
				categoryName = null;
			}
			
		}
		
		// 검색
		String searchTitle = null;
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
			
			if(request.getParameter("searchTitle").equals("null")) { // 문자열 null이 넘어올 경우 진짜 null로 변경
				searchTitle = null;
			}
		}
		
		// EbookList
		this.ebookDao = new EbookDao();
		List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage,categoryName, searchTitle);
		
		// CategoryList
		this.categoryDao = new CategoryDao();
		List<Category> categoryList = this.categoryDao.selectCategory();
		
		// BestEbookList
		this.ordersDao = new OrdersDao();
		List<Map<String, Object>> bestOrdersList = this.ordersDao.selectBestOrdersList();
		
		// EbookList totalCount
		int totalCount = this.ebookDao.totalCount(categoryName, searchTitle);
		
		// View forward
		//System.out.println("IndexController currentPage " + currentPage + " rowPerPage" + rowPerPage + " categoryName" + categoryName + " totalCount" + totalCount);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("rowPerPage",rowPerPage);
		request.setAttribute("categoryName",categoryName);
		request.setAttribute("searchTitle",searchTitle);
		request.setAttribute("totalCount",totalCount);
		request.setAttribute("ebookList", ebookList); // request객체에 ebookList 정보 넣어주기
		request.setAttribute("categoryList", categoryList); // request객체에 categoryList 정보 넣어주기
		request.setAttribute("bestOrdersList",bestOrdersList); // 베스트 상품 list 정보 넣어주기
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
	
}