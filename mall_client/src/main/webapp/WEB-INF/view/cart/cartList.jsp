<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>cartList</title>
</head>          
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h1>��ٱ���</h1>
	
	<!-- request��ü�� ��Ƶ� List �������� -->
	<%
		List<Map<String, Object>> cartList = (List<Map<String, Object>>)request.getAttribute("cartList");
		int totalPrice = 0;
	%>
	
	<!-- ��ٱ��� ���� List ��� -->
	<table border="1">
		<%
			for(Map<String, Object> list : cartList){
				totalPrice = totalPrice + Integer.parseInt((String)list.get("ebookPrice"));
		%>
			<tr>
				<td>
					<div><%=list.get("ebookNo")%></div>
					<div>���� : <%=list.get("ebookTitle")%></div>
					<div>�ֹ����� : <%=((String)list.get("cartDate")).substring(0,10)%></div>
				</td>
				<td>���� : <%=list.get("ebookPrice")%></td>
				<td>
					<a href="<%=request.getContextPath()%>/DeleteCartController?cartNo=<%=list.get("cartNo")%>"><button type="button">����</button></a>
				</td>
				<td>
					<a href=""><button type="button">�ֹ�</button></a>
				</td>
			</tr>
		<%
			}
		%>
		
		<tr>
			<td></td>
			<td>�� �ݾ� : <%=totalPrice%></td>
		</tr>
	</table>
</body>
</html>