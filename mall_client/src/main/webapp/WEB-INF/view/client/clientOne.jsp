<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="mall.client.vo.Client"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	Client client = (Client)request.getAttribute("client");
%>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- �޴�1 -->

	<h1>������</h1>
	<table border="1">
		<tr>
			<td>clientNo</td>
			<td><%=client.getClientNo() %></td>
		</tr>

		<tr>
			<td>clientMail</td>
			<td><%=client.getClientMail() %></td>
		</tr>

		<tr>
			<td>clientDate</td>
			<td><%=client.getClientDate() %></td>
		</tr>
	</table>
	<!-- UpdateClientPwController.doGet() - updateClientPw.jsp -->
	<!-- UpdateClientPwController.doPost() - clientDao.updateClientPw() - session.invalidate() - redirect:/IndexController -->
	<a href="<%=request.getContextPath()%>/UpdateClientPwController?clientMail=<%=client.getClientMail() %>"><button type="button">��й�ȣ����</button></a>
	<!-- DeleteClientController - clientDao.deleteClient() -- session.invalidate() - redirect:/IndexController-->
	<a href=""><button type="button">ȸ��Ż��</button></a>
</body>
</html>