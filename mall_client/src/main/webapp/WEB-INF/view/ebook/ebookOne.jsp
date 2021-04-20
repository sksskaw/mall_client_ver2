<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ebookOne</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h1>ebookOne</h1>
	<table border="1">
		<tr>
			<td>카테고리 </td>
			<td>${ebook.categoryName}</td>
		</tr>
		
		<tr>
			<td>ISBN 코드 </td>
			<td>${ebook.ebookISBN}</td>
		</tr>
		
		<tr>
			<td>제목 </td>
			<td>${ebook.ebookTitle}</td>
		</tr>
	</table>
	
	<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebook.ebookNo}">
		<c:choose>
			<c:when test = "${loginClient == null}">
	            <button type="button" disabled="disabled">장바구니추가</button>
	        </c:when>
	        <c:when test = "${ebook.ebookState == '품절'}">
	        	<button type="button" disabled="disabled">장바구니추가</button>
	        </c:when>
	        <c:when test = "${ebook.ebookState == '절판'}">
	            <button type="button" disabled="disabled">장바구니추가</button>
	        </c:when>
	        <c:when test = "${ebook.ebookState == '구편절판'}">
	        	<button type="button" disabled="disabled">장바구니추가</button>
	        </c:when>
		
	        <c:otherwise>
	        	<button type="button">장바구니추가</button>
	        </c:otherwise>
		</c:choose>
	</a>
</body>
</html>