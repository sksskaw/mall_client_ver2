package mall.client.controller;

import java.io.IOException;
import java.util.List;

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
		}
		
		// EbookList
		this.ebookDao = new EbookDao();
		List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage,categoryName,null);
		
		// CategoryList
		this.categoryDao = new CategoryDao();
		List<Category> categoryList = this.categoryDao.selectCategory();
		
		// EbookList totalCount
		int totalCount = this.ebookDao.totalCount(categoryName);
		
		// View forward
		//System.out.println("IndexController currentPage " + currentPage + " rowPerPage" + rowPerPage + " categoryName" + categoryName + " totalCount" + totalCount);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("rowPerPage",rowPerPage);
		request.setAttribute("categoryName",categoryName);
		request.setAttribute("totalCount",totalCount);
		request.setAttribute("ebookList", ebookList); // request객체에 ebookList 정보 넣어주기
		request.setAttribute("categoryList", categoryList); // request객체에 categoryList 정보 넣어주기
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			if(categoryName.equals("1")) { // 1이면 전체 검색
				categoryName = null;
			}
		}
		
		// 검색
		String searchTitle = null;
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		// EbookList
		this.ebookDao = new EbookDao();
		List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage,categoryName,searchTitle);
		
		// CategoryList
		this.categoryDao = new CategoryDao();
		List<Category> categoryList = this.categoryDao.selectCategory();
		
		// EbookList totalCount
		int totalCount = this.ebookDao.totalCount(categoryName);

		request.setAttribute("currentPage",currentPage);
		request.setAttribute("rowPerPage",rowPerPage);
		request.setAttribute("totalCount",totalCount);
		request.setAttribute("ebookList", ebookList); // request객체에 ebookList 정보 넣어주기
		request.setAttribute("categoryList", categoryList); // request객체에 categoryList 정보 넣어주기
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
		
	}

}