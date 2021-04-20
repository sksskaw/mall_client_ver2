<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 상단 메인 메뉴 -->

<c:if test="${loginClient == null}">
	<!-- 로그아웃 -->
	<form action="${pageContext.request.contextPath}/LoginController" method="post">
		ID : <input type="text" name="clientMail">
		PW : <input type="password" name="clientPw">
		<button type="submit">로그인</button>
	</form>
	<ul>
		<li><a href="${pageContext.request.contextPath}/IndexController">홈</a></li>
		<li><a href="${pageContext.request.contextPath}/InsertClientController">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/EbookCalendarController">ebook 달력</a></li>
	</ul>
</c:if>

<c:if test="${loginClient != null}">

	<!-- 로그인 -->
	<div>
		<div>${loginClient.clientMail}님 반갑습니다.</div>
		
		<ul>
			<li><a href="${pageContext.request.contextPath}/IndexController">홈</a></li>
			<li><a href="${pageContext.request.contextPath}/LogoutController">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/SelectOneClientController">회원정보</a></li>
			<li><a href="${pageContext.request.contextPath}/CartListController">장바구니</a></li>
			<li><a href="${pageContext.request.contextPath}/OrdersListController">주문목록</a></li>
			<li><a href="${pageContext.request.contextPath}/EbookCalendarController">ebook 달력</a></li>
		</ul>
	</div>
</c:if>