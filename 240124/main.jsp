<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 메인 페이지</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>STUDENT 테이블 메인 페이지</h3>
		<hr width="50%" color="blue">
		<br><br>
		
		<!-- request.getContextPath()==> 현재 프로젝트 명을 문자열로 반환해주는 메서드  -->
		<a href="<%=request.getContextPath()%>/select">[전체 학생 리스트]</a>
		<!-- 매핑 작업 때문에 자바 코드가 들어간다. 클릭해서 강제로 연결시킴 -->
		<!-- 매핑을 하기 위해서 /select를 부름 -->
		
		
	
	
	
	
	</div>

</body>
</html>
