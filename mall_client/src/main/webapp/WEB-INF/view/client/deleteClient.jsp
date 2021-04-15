<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteClient</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->
	
	<h1>회원탈퇴</h1>
	
	<form action="<%=request.getContextPath()%>/DeleteClientController" method="post">
		<div> 현재 비밀번호를 입력하세요 : 
			<input type="password" name="currentPw" required="required">
		</div>
		
		<button type="submit">입력</button>
		<a href="<%=request.getContextPath()%>/SelectOneClientController"><button type="button">취소</button></a>
	</form>
</body>
</html>