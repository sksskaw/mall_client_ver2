<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="mall.client.vo.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<h1>index</h1>
	<%
		List<Ebook> ebookList = (List<Ebook>)request.getAttribute("ebookList");
		List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
		List<Map<String, Object>> bestOrdersList = (List<Map<String, Object>>)request.getAttribute("bestOrdersList");
		
		int currentPage = (Integer)request.getAttribute("currentPage");
		int rowPerPage = (Integer)request.getAttribute("rowPerPage");
		String categoryName = (String)request.getAttribute("categoryName");
		String searchTitle = (String)request.getAttribute("searchTitle");

		int totalCount = (Integer)request.getAttribute("totalCount");
		System.out.println("index.jsp currentPage " + currentPage + " rowPerPage" + rowPerPage + " categoryName" + categoryName + " totalCount" + totalCount);
	%>
	
	<div>
			<a href="<%=request.getContextPath()%>/IndexController">[전체]</a>
	<%
		for(Category category : categoryList){
	%>
			<a href="<%=request.getContextPath()%>/IndexController?categoryName=<%=category.getCategoryName()%>">[<%=category.getCategoryName()%>]</a>
	<%	
		}
	%>
	</div>
	
	<!-- 검색 입력 폼 -->
	<form action="<%=request.getContextPath()%>/IndexController" method="get">
		<div>
			<select name="categoryName">
				<option value="null" selected>전체</option>
			    <%
					for(Category category : categoryList){
				%>
						<option value="<%=category.getCategoryName()%>"><%=category.getCategoryName()%></option>
				<%	
					}
				%>
			</select>
			<input type="text" name="searchTitle">
			<button type="submit">검색</button>
		</div>
	</form>
	
	<!-- best ebook 출력 -->
	<h1>best ebook</h1>
	<table border="1">
		<tr>
		<%
			for(Map m : bestOrdersList){
		%>
				<td>
					<div><img src="<%=request.getContextPath()%>/img/default.jpg"></div>
					<div>카테고리 : <%=m.get("categoryName")%></div>
					<div>제목 : 
						<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=m.get("ebookNo")%>">
							<%=m.get("ebookTitle")%>
						</a>
					</div>
					<div>가격 : <%=m.get("ebookPrice")%>￦</div>
				</td>
		<%
			}
		%>
		</tr>
	</table>
	<br>
	<h1>ebook List</h1>
	<!-- ebook 상품 출력 -->
	<table border="1">
		<tr>
		<%
			int i = 0; //5줄마다 칸을 띄우기 위해 내부 반복문 연산자
			for(Ebook ebook : ebookList){
				i++;
		%>
				<td>
					<div><img src="<%=request.getContextPath()%>/img/default.jpg"></div>
					<div>카테고리 : <%=ebook.getCategoryName()%></div>
					<div>제목 : 
						<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=ebook.getEbookNo()%>">
							<%=ebook.getEbookTitle()%>
						</a>
					</div>
					<div>가격 : <%=ebook.getEbookPrice()%>￦</div>
				</td>
			<!-- 5칸 이후 테이블 줄바꿈 -->
			<%
				if(i%5==0){
			%>
		</tr>
		<tr>
			<%		
				}
			}
		%>
		</tr>
	</table>
		<%
				if(currentPage != 1){
			%>
						<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&searchTitle=<%=searchTitle%>"><button type="button">Previous</button></a>
			<%
				}
				if(currentPage < (totalCount/rowPerPage) + 1){ //나머지 부분도 출력하기 위한 +1
			%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&searchTitle=<%=searchTitle%>"><button type="button">Next</button></a>
			<%
				}
		%>
</body>
</html>






