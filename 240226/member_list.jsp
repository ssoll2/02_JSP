<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h3>MEMBER10 테이블 전체 회원 목록 페이지</h3>
		<hr width="30%" color="red">
		<br><br>
		
		<!-- 검색 관련 내용  -->
		<form method="post" action="<%=request.getContextPath()%>/search.do">
			<select name="field">
				<option value="id">아이디</option>
				<option value="name">이름</option>
				<option value="job">직업</option>
				<option value="addr">주소</option>
			</select>
			
				<input type="text" name="keyword">&nbsp;&nbsp;
				<input type="submit" value="검색">
		</form>
		
		<br>
		
		<table border="1" width="500">
			<tr>
				<th>회원No.</th><th>회원명</th>
				<th>회원직업</th><th>회원가입일</th><th>상세내역</th>
			</tr>
			
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
			
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNum() }</td>
						<td>${dto.getMemname() }</td>
						<td>${dto.getJob() }</td>
						<td>${dto.getRegdate().substring(0,10) }</td>
						<td>
							<input type="button" value="상세내역"
								onclick="location.href='content.do?num=${dto.getNum()}'">
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5" align="center">
						<h3>전체 회원 리스트가 없습니다.</h3>
					
					</td>
				</tr>
			</c:if>
		</table>
		
		<br>
		
		<input type="button" value="회원등록"
			onclick="location.href='insert.do'">
	</div>
	

</body>
</html>
