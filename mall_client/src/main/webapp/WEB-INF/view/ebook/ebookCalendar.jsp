<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EbookCalendar</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<h1>EbookCalendar</h1>
	
	<!-- n행 7열 -->
	<div>
		<a href="${pageContext.request.contextPath}/EbookCalendarController?currentYear=${preYear}&currentMonth=${preMonth}">이전달</a>
		<span>${currentYear}</span>
		<span>${currentMonth}</span>
		<a href="${pageContext.request.contextPath}/EbookCalendarController?currentYear=${nextYear}&currentMonth=${nextMonth}">다음달</a>
	</div>
	<table border="">
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<c:forEach var="i" begin="1" end="${lastDayOfMonth+(startDayOfTheWeek-1)}" step="1">
				<c:if test="${i-(startDayOfTheWeek-1) <= 0}">
					<td>&nbsp;</td>
				</c:if>
				<c:if test="${i-(startDayOfTheWeek-1) > 0}">
					<td>
						<div>${i-(startDayOfTheWeek-1)}</div>
						<div>
							<c:forEach var="m" items="${EbookListByMonth}">
								<c:if test="${i-(startDayOfTheWeek-1) == m.day}">
									<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">
										<c:if test="${m.ebookTitle.length() > 10}">
											${m.ebookTitle.substring(0,10)}...
										</c:if>
										<c:if test="${m.ebookTitle.length() <= 10}">
											${m.ebookTitle}
										</c:if>
									</a>
								</c:if>
							</c:forEach>
						</div>
					</td>
				</c:if>
				
				<c:if test="${i%7 == 0}">
					<tr></tr>
				</c:if>
				
			</c:forEach>
			
			<c:if test="${(lastDayOfMonth+(startDayOfTheWeek-1)) % 7 != 0}">
				<c:forEach begin="1" end="${7 - (lastDayOfMonth+(startDayOfTheWeek-1))%7}" step="1">
					<td>&nbsp;</td>
				</c:forEach>
			</c:if>
		</tr>
	</table>
	
</body>
</html>