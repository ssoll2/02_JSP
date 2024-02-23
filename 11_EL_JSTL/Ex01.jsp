<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	 int su = 237;
	 
	 pageContext.setAttribute("SU", su); // 자체적으로 set 함.
	 
	 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현 언어로 여러 가지 데이터 출력하기</h2>
	
	<h3>
		JSP 표현식 >>> <%=su %><br>
		
		EL(표현언어) >>> ${SU} <br>
	</h3>
	
	
	<hr>
	<h3>
		\${123 + 50 } >>> ${123 + 50 } <br>
		
		\${"안녕하세요" } >>> ${"안녕하세요" } <br>
		
		<%-- 숫자형 문자열과 숫자를 더하면 문자열을
			 자동으로 숫자로 변환햐여 더해줌 --%>
		\${"20" + 55 } >>> ${"20" + 55 } <br>
		
		<%-- 피연산자가 null이면 0으로 처리가됨.
		     따라서 덧셈 연산이 진행이 됨.  --%>
		\${null + 45 } >>> ${null + 45 } <br>
		
	
	</h3>
	
	
	
	
	
	
	
	
</body>
</html>
