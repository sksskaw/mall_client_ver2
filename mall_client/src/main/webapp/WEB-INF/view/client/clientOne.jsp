<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- �޴�1 -->

	<h1>������</h1>
	<table border="1">
		<tr>
			<td>clientNo</td>
			<td>${client.clientNo}</td>
		</tr>

		<tr>
			<td>clientMail</td>
			<td>${client.clientMail}</td>
		</tr>

		<tr>
			<td>clientDate</td>
			<td>${client.clientDate.substring(0,10)}</td>
		</tr>
	</table>
	<!-- UpdateClientPwController.doGet() - updateClientPw.jsp -->
	<!-- UpdateClientPwController.doPost() - clientDao.updateClientPw() - session.invalidate() - redirect:/IndexController -->
	<a href="${pageContext.request.contextPath}/UpdateClientPwController?clientMail=${client.clientMail}"><button type="button">��й�ȣ����</button></a>
	
	<!-- DeleteClientController - clientDao.deleteClient() -- session.invalidate() - redirect:/IndexController-->
	<a href="${pageContext.request.contextPath}/DeleteClientController"><button type="button">ȸ��Ż��</button></a>
</body>
</html>