<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EbookCalendar</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<h1>EbookCalendar</h1>
	<%
		int currentYear = (Integer)request.getAttribute("currentYear");
		int currentMonth = (Integer)request.getAttribute("currentMonth");
		int lastDayOfMonth = (Integer)request.getAttribute("lastDayOfMonth");
		int startDayOfTheWeek = (Integer)request.getAttribute("startDayOfTheWeek");
		List<Map<String, Object>> EbookListByMonth = (List<Map<String, Object>>)request.getAttribute("EbookListByMonth");
		// ������ ������ ó��
		int preYear = currentYear;
		int preMonth = currentMonth - 1;
		if(preMonth == 0){
			preMonth = 12;
			preYear -= 1;
		}
		
		int nextYear = currentYear;
		int nextMonth = currentMonth + 1;
		if(nextMonth == 13){
			nextMonth = 1;
			nextYear += 1;
		}
	%>
	
	<!-- n�� 7�� -->
	<div>
		<a href="<%=request.getContextPath()%>/EbookCalendarController?currentYear=<%=preYear%>&currentMonth=<%=preMonth%>">������</a>
		<span><%=currentYear%></span>
		<span><%=currentMonth%></span>
		<a href="<%=request.getContextPath()%>/EbookCalendarController?currentYear=<%=nextYear%>&currentMonth=<%=nextMonth%>">������</a>
	</div>
	<table border="">
		<tr>
			<th>��</th>
			<th>��</th>
			<th>ȭ</th>
			<th>��</th>
			<th>��</th>
			<th>��</th>
			<th>��</th>
		</tr>
		<tr>
		<%
			// ���� ���� ���� ó��
			for(int i=1; i<startDayOfTheWeek; i++){
		%>
				<td>&nbsp;</td>
			<%
				}
				int a = startDayOfTheWeek;
				// �޷� ��¥ ü���
				for(int i=1; i<=lastDayOfMonth; i++){
			%>
					<td><%=i%>
						<%
							for(Map m : EbookListByMonth){
								if((Integer)m.get("day") == i)
								{
							%>
									<div>
										<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=m.get("ebookNo")%>">
											<%	
												String ebookTitle = (String)m.get("ebookTitle");
												if(ebookTitle.length() > 10){
											%>
													<%=ebookTitle.substring(0,10)+"..."%>
											<%
												} else{
											%>
													<%=ebookTitle%>
											<%
												}
											%>
										</a>
									</div>
							<%
								}
							}
						%>
					</td>
			<%
						if(a%7 == 0){
					%>
						<tr></tr>
					<%
						}
						a = a + 1;
				}
				//������ ĭ ó��
				for(int i = (a-1)%7; i<7; i++){
					if(i==0)
						break;
				%>
					<td>&nbsp;</td>
				<%
				}
			%>
		</tr>
	</table>
	
</body>
</html>