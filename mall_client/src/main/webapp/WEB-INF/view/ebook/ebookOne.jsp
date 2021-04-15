<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="mall.client.vo.Ebook"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ebookOne</title>
</head>
<body>
<%
	Ebook ebook = (Ebook)request.getAttribute("ebook");
%>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h1>ebookOne</h1>
	<table border="1">
		<tr>
			<td>카테고리 </td>
			<td><%=ebook.getCategoryName()%></td>
		</tr>
		
		<tr>
			<td>ISBN 코드 </td>
			<td><%=ebook.getEbookISBN()%></td>
		</tr>
		
		<tr>
			<td>제목 </td>
			<td><%=ebook.getEbookTitle()%></td>
		</tr>
	</table>
	
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
		<%
			if(session.getAttribute("loginClient") == null 
				|| ebook.getEbookState().equals("품절")
				|| ebook.getEbookState().equals("절판")
				|| ebook.getEbookState().equals("구편절판")) {
		%>
				<button type="button" disabled="disabled">장바구니추가</button>
		<%		
			} else {
		%>
				<button type="button">장바구니추가</button>
		<%		
			}
		%>		
	</a>
</body>
</html>