<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateClientPw</title>
</head>
<body>

	<h1>비밀번호 수정</h1>
	
	<form action="<%=request.getContextPath()%>/UpdateClientPwController" method="post">
		<div> 현재 비밀번호 : 
			<input type="password" name="currentPw" required="required">
		</div>
		
		<div> 수정 비밀번호 : 
			<input type="password" name="editPw" required="required">
		</div>
		<button type="submit">입력</button>
		<a href="<%=request.getContextPath()%>/SelectOneClientController"><button type="button">취소</button></a>
	</form>
</body>
</html>