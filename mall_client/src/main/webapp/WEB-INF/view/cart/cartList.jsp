<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>cartList</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h1>장바구니</h1>

	<!-- 장바구니 정보 List 출력 -->
	<table border="1">
		<c:forEach var="m" items="${cartList}">
			<tr>
				<td>
					<div>${m.cartNo}</div>
					<div>제목 : ${m.ebookTitle}</div>
					<div>주문날자 : ${m.cartDate.substring(0,10)}</div>
				</td>
				<td>가격 : ${m.ebookPrice}</td>
				<td>
					<a href="${pageContext.request.contextPath}/DeleteCartController?cartNo=${m.cartNo}"><button type="button">삭제</button></a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/InsertOrdersController?ebookNo=${m.ebookNo}&cartNo=${m.cartNo}"><button type="button">주문</button></a>
				</td>
			</tr>
			<c:set var="totalPrice" value="${totalPrice + m.ebookPrice}"/>
		</c:forEach>
		
		<tr>
			<td></td>
			<td>총 금액 : ${totalPrice}</td>
		</tr>
	</table>
</body>
</html>