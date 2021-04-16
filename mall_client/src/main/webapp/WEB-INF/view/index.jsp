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
	<form action="<%=request.getContextPath()%>/IndexController" method="post">
		<div>
			<select name="categoryName">
				<option value="1" selected>전체</option> <!-- 여기서 value를 ""로 보내면 문자열 null이 날라감, 문자열 "1"로 보내서 categoryName값이 1이면 카테고리 없는 쿼리 실행할 수 있게-->
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
	
	<table border="1">
		<tr>
		<%
			int i = 0; //5줄마다 칸을 띄우기 위해 내부 반복문 연산자
			for(Ebook ebook : ebookList){
				i++;
		%>
				<td>
					<div><img src="<%=request.getContextPath()%>/img/default.jpg"></div>
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
			if(categoryName == null){
	
				if(currentPage != 1){
			%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>"><button type="button">Previous</button></a>
			<%
				}
				if(currentPage < (totalCount/rowPerPage) + 1){ //나머지 부분도 출력하기 위한 +1
			%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>"><button type="button">Next</button></a>
			<%
				}
				
			} else{
				
				if(currentPage != 1){
			%>
						<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>"><button type="button">Previous</button></a>
			<%
				}
				if(currentPage < (totalCount/rowPerPage) + 1){ //나머지 부분도 출력하기 위한 +1
			%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>"><button type="button">Next</button></a>
			<%
				}
				
			}
		%>
</body>
</html>






