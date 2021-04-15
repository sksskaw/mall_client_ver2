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
			<td>ī�װ� </td>
			<td><%=ebook.getCategoryName()%></td>
		</tr>
		
		<tr>
			<td>ISBN �ڵ� </td>
			<td><%=ebook.getEbookISBN()%></td>
		</tr>
		
		<tr>
			<td>���� </td>
			<td><%=ebook.getEbookTitle()%></td>
		</tr>
	</table>
	
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
		<%
			if(session.getAttribute("loginClient") == null 
				|| ebook.getEbookState().equals("ǰ��")
				|| ebook.getEbookState().equals("����")
				|| ebook.getEbookState().equals("��������")) {
		%>
				<button type="button" disabled="disabled">��ٱ����߰�</button>
		<%		
			} else {
		%>
				<button type="button">��ٱ����߰�</button>
		<%		
			}
		%>		
	</a>
</body>
</html>