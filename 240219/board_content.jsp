<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDTO board = (BoardDTO)request.getAttribute("Content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="tomato">
			<h3>BOARD 게시글 상세 내역 페이지</h3>
		<hr width="30%" color="tomato">
		<br><br>
		
		<table border="1" width="500">
			
			<%
				if(board != null) {
			%>	
					<tr>
						<td colspan="4" align="center">
							<h3><%=board.getBoard_writer() %>님 게시글 상세 내역</h3>
						</td>
					</tr>
					<tr>
						<th>글번호</th>
						<td><%=board.getBoard_no() %></td>
						
						<th>작성자</th>
						<td><%=board.getBoard_writer() %></td>
				
					</tr>
					<tr>
						
						<% if(board.getBoard_update()== null) { %>
						
							<th>작성일자</th>
							<td><%=board.getBoard_date() %></td>
						
							
						<% } else {%>
							<th>수정일자</th>
							<td><%=board.getBoard_update() %></td>
						<% } %>
							<th>조회수</th>
							<td><%=board.getBoard_hit() %></td>
						
					</tr>
					<tr>
						<th>글제목</th>
						<td colspan="3" align="center">
							<%=board.getBoard_title() %>
						</td>
					</tr>
					<tr>
						<th>글내용</th>
						<td colspan="3" height="100px">
							<%-- <textarea rows="7" cols="25" readonly><%=board.getBoard_cont() %></textarea> --%>
							<%=board.getBoard_cont().replace("\r\n", "<br>") %>
						</td>
					</tr>
					<tr>
						<th>글 비밀번호</th>
						<td colspan="3" align="center">
						<%
						 	if(board.getBoard_pwd().length()!= 0) {
						 		for(int i =1; i<=board.getBoard_pwd().length();i++) {
						 %>
						 					 *
						 <% 		}
						 	}
						
						%>
						
						</td>
					</tr>
					<% } else { %>
						<tr>
							<td colspan="4" align="center">
								<h3>조회된 게시글 상세 내역이 없습니다...</h3>
							</td>
						</tr>
			
			
			<% 	}%>
		
		
		
		
		</table>
		<br>
		
		<input type="button" value="글 수정"
			onclick="location.href='modify.go?no=<%=board.getBoard_no()%>'">&nbsp;&nbsp;
		
		<input type="button" value="글 삭제"
			onclick="if(confirm('정말로 게시글을 삭제하시겠습니까?')){
				location.href='board/board_delete.jsp?no=<%=board.getBoard_no() %>'
			} else {return} ">&nbsp;&nbsp;
		<input type="button" value="게시글 목록" onclick="location.href='list.go'">
	
	
	
	</div>

</body>
</html>
