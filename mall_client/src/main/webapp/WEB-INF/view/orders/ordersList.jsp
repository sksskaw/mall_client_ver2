<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ordersList</title>
</head>
<body>
<%
	List<Map<String, Object>> ordersList = (List<Map<String, Object>>)request.getAttribute("ordersList");
%>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h1>주문 목록</h1>
	<table border="1">
		<tr>
			<th>ordersNo</th>
			<th>ebookNo</th>
			<th>ordersDate</th>
			<th>ordersState</th>
			<th>ebookTitle</th>
			<th>ebookPrice</th>
		</tr>
		<%
			int totalPrice = 0;
			for(Map m : ordersList){
				totalPrice = totalPrice + (Integer)(m.get("ebookPrice"));
		%>
			<tr>
				<td><%=(Integer)(m.get("ordersNo"))%></td>
				<td><%=(Integer)(m.get("ebookNo"))%></td>
				<td><%=((String)(m.get("ordersDate"))).substring(0,10)%></td>
				<td><%=(String)(m.get("ordersState"))%></td>
				<td><%=(String)(m.get("ebookTitle"))%></td>
				<td><%=(Integer)(m.get("ebookPrice"))%></td>
			</tr>
		<%
			}
		%>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>총 금액: <%=totalPrice%></td>
			</tr>
	</table>
</body>
</html>