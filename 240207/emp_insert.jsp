<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="green">
			<h3>EMP 테이블 사원 등록 폼 페이지</h3>
		<hr width="50%" color="green">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/insert.do">
		
		<table border="1">
			<tr>
				<th>사원No.</th>
				<td>
					<input type="text" name="empno">
				</td>
				
			</tr>
			<tr>
				<th>사원명</th>
				<td>
					<input type="text" name="ename">
				</td>
				
			</tr>
			<tr>
				<th>부서No.</th>
				<td>
					<input type="text" name="deptno">
				</td>
				
			</tr>
			<tr>
				<th>입사날짜</th>
				<td>
					<input type="text" name="hiredate">
				</td>
				
			</tr>
			<tr>
					<td colspan="2" align="center">
						<input type="submit" value="사원추가">&nbsp;&nbsp;
						<input type="reset" value="다시작성">
				
				
				</tr>
		
		
		
		
		</table>
	
	
	</div>

</body>
</html>
