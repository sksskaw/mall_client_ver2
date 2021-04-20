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
			<td>ī�װ� </td>
			<td>${ebook.categoryName}</td>
		</tr>
		
		<tr>
			<td>ISBN �ڵ� </td>
			<td>${ebook.ebookISBN}</td>
		</tr>
		
		<tr>
			<td>���� </td>
			<td>${ebook.ebookTitle}</td>
		</tr>
	</table>
	
	<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebook.ebookNo}">
		<c:choose>
			<c:when test = "${loginClient == null}">
	            <button type="button" disabled="disabled">��ٱ����߰�</button>
	        </c:when>
	        <c:when test = "${ebook.ebookState == 'ǰ��'}">
	        	<button type="button" disabled="disabled">��ٱ����߰�</button>
	        </c:when>
	        <c:when test = "${ebook.ebookState == '����'}">
	            <button type="button" disabled="disabled">��ٱ����߰�</button>
	        </c:when>
	        <c:when test = "${ebook.ebookState == '��������'}">
	        	<button type="button" disabled="disabled">��ٱ����߰�</button>
	        </c:when>
		
	        <c:otherwise>
	        	<button type="button">��ٱ����߰�</button>
	        </c:otherwise>
		</c:choose>
	</a>
</body>
</html>