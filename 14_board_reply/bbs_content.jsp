<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	request.setAttribute("newLine", "\n");
	request.setAttribute("br", "<br>");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${Cont }"/>
		<hr width="30%" color="tomato">
			<h3>${dto.getBoard_writer() }님 게시물 상세 내역 페이지</h3>
		<hr width="30%" color="tomato">
		<br><br>
		
		<table border="1" width="400">
			<tr>
				<th>글No.</th>
				<td>${dto.getBoard_no() }</td>
			</tr>
			<tr>
				<th>글 작성자</th>
				<td>${dto.getBoard_writer() }</td>
			</tr>
			<tr>
				<th>글 제목</th>
				<td>${dto.getBoard_title() }</td>
			</tr>
			<tr>
				<th>글 내용</th>
				<td>
					${fn:replace(dto.getBoard_cont(), newLine, br) } 
				</td>
			</tr>
			
			<tr>
				<th>글 비밀번호</th>
				<td>
					<c:if test="${dto.getBoard_pwd().length() != 0 }">
						<c:forEach begin="1" end="${dto.getBoard_pwd().length() }">
									 	*
						</c:forEach>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<th>글 조회수</th>
				<td>${dto.getBoard_hit() }</td>
			</tr>
			<tr>
				<c:if test="${empty dto.getBoard_update() }"> <!-- 수정된게 없다면 -->
					<th>작성일자</th>
					<td>${dto.getBoard_date() }</td>
				</c:if>
				<c:if test="${!empty dto.getBoard_update() }"> <!-- 수정된게 있다면 -->
					<th>수정일자</th>
					<td>${dto.getBoard_update() }</td>
				</c:if>
			</tr>
			<c:if test="${empty dto }"> <!-- 상세내역을 클릭하기 직전에 작성자가 게시물을 삭제했다면.. 자료는 없을 것이다. -->
				<tr>
					<td colspan="2" align="center">
						<h3>게시물 번호에 해당하는 게시물이 없습니다.</h3>
					</td>
				</tr>
			
			</c:if>
		
		</table>
		<br>
		
		<input type="button" value="글 수정" 
			onclick="location.href='bbs_modify.do?no=${dto.getBoard_no()}&page=${Page }'">&nbsp;
		<input type="button" value="글 삭제"
			onclick="if(confirm('게시글을 정말 삭제하시겠습니까?')){
				location.href='bbs_delete.go?no=${dto.getBoard_no()}&page=${Page }&pwd=${dto.getBoard_pwd() }'
			} else { return;}">&nbsp;
		<input type="button" value="전체목록"
			onclick="location.href='bbs_list.go?page=${Page}'"> 
		
	
	
	</div>	
	


</body>
</html>
