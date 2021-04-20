<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<h1>index</h1>
	
	<!-- 카테고리 출력 -->
	<div>
			<a href="${pageContext.request.contextPath}/IndexController">[전체]</a>
		<c:forEach var="category" items="${categoryList}">
			<a href="${pageContext.request.contextPath}/IndexController?categoryName=${category.categoryName}">[${category.categoryName}]</a>
		</c:forEach>
	</div>
	
	<!-- 검색 입력 폼 -->
	<form action="${pageContext.request.contextPath}/IndexController" method="get">
		<div>
			<select name="categoryName">
				<option value="null" selected>전체</option>
				<c:forEach var="category" items="${categoryList}">
					<option value="${category.categoryName}">${category.categoryName}</option>
				</c:forEach>	
			</select>
			<input type="text" name="searchTitle">
			<button type="submit">검색</button>
		</div>
	</form>
	
	<!-- best ebook 출력 -->
	<h1>best ebook</h1>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${bestOrdersList}">
				<td>
					<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
					<div>카테고리 : ${m.categoryName}</div>
					<div>제목 : 
						<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">
							${m.ebookTitle}
						</a>
					</div>
					<div>가격 : ${m.ebookPrice}￦</div>
				</td>
			</c:forEach>
		</tr>
	</table>
	<br>
	
	<!-- ebook 상품 출력 -->
	<h1>ebook List</h1>
	<table border="1">
		<tr>
		<c:set var="i" value="0"/>
		<c:forEach var="ebook" items="${ebookList}">
			<c:set var="i" value="${i + 1}"/>
			<td>
				<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
				<div>카테고리 : ${ebook.categoryName}</div>
				<div>제목 : 
					<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${ebook.ebookNo}">
						${ebook.ebookTitle}
					</a>
				</div>
				<div>가격 : ${ebook.ebookPrice}￦</div>
				<c:if test="${i%5 == 0}">
					</tr>
					<tr>
				</c:if>
			</td>
		</c:forEach>
		</tr>
	</table>
	
	<!-- 페이징 처리 -->
	<c:if test="${currentPage != 1}">
		<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&rowPerPage=${rowPerPage}&categoryName=${categoryName}&searchTitle=${searchTitle}"><button type="button">Previous</button></a>
	</c:if>
	
	<c:if test="${currentPage < (totalCount/rowPerPage) + 1}">
		<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&rowPerPage=${rowPerPage}&categoryName=${categoryName}&searchTitle=${searchTitle}"><button type="button">Next</button></a>
	</c:if>
</body>
</html>






