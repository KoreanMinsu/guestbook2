<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "com.javaex.vo.GuestbookVo" %>
<%@page import = "java.util.List" %>

<%
	List<GuestbookVo> guestbookList = (List<GuestbookVo>)request.getAttribute("gList");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="/guestbook2/gbc" method ="post">
			<table border ="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value=""></td>
					<td>비밀번호</td>
					<td><input type="text" name="password" value=""></td>
				</tr>
				<tr>
					<td colspan="4"><textarea cols="50" rows="10" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="4"><button type="submit">확인</button></td>
				</tr>		
			</table>
			<input type="hidden" name="action" value="add">
		</form>
		
		<%
			for(int i=0;i<guestbookList.size();i++){
		%>
	
				<table border="1">
					<tr>
			<!-- 번호 -->	<td><%=guestbookList.get(i).getNo()%></td>
			<!-- 이름 --><td><%=guestbookList.get(i).getName()%></td>
			<!-- 날짜 --><td><%=guestbookList.get(i).getRegDate()%></td>
			<!-- 삭제 --><td><a href="/guestbook2/gbc?action=dform&no=<%=guestbookList.get(i).getNo()%>">삭제</a></td>
					</tr>
					<tr>
			<!-- 내용 --><td colspan="4"><%=guestbookList.get(i).getContent()%></td>
					<tr>
				</table>
				<br>
			
		<%
			}
		%>
		

</body>
</html>