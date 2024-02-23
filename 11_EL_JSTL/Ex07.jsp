<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현 언어로(EL)로 scope 내용 출력</h2>
	
	<h3>
	
		pageContext >>> ${pageScope.Res} == ${Res }<br>
		
		request >>> ${requestScope.R } == ${R } <br>
		
		session >>> ${sessionScope.S } == ${S } <br>
		
		application >>> ${applicaionScope.A } == ${A } <br>
	</h3>	

</body>
</html>
