<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 한글 오류 방지 
	request.setCharacterEncoding("utf-8");
    
	// JSP 방식
	/* String user_id = request.getParameter("id").trim();
	String user_name = request.getParameter("name").trim();
	int user_age = Integer.parseInt(request.getParameter("age").trim()); */

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<div align="center">
 		<h3>표현언어(EL)를 이용하여 파라미터 값을 출력하는 방법</h3>
 		
 		<table border="1" width="300">
 			<tr>
					<th>아이디</th>
					<td>${param.id }</td>
			</tr>
			<tr>
					<th>이 름</th>
					<td>${param.name }</td>
			</tr>
			<tr>
					<th>나 이</th>
					<td>${param.age }</td>
			</tr>
 		
 		
 		
 		</table>
 	
 	
 	</div>
	

</body>
</html>
