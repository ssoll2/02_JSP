<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardDTO cont =	(BoardDTO)request.getAttribute("Modify");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="30%" color="marmoon">
	      <h3>BOARD 테이블 게시판 글쓰기 수정 폼 페이지</h3>
	   <hr width="30%" color="marmoon">
	   <br> <br>
	   
	   <form method="post"
	   	      action="<%=request.getContextPath() %>/modify_ok.go">
	      
	      <input type="hidden" name="num" value="<%=cont.getBoard_no() %>">
	      
	      <table border="1" width="400">
	         <tr>
	            <th>작성자</th>
	         	<td>
	         	   <input type="text" name="writer"
	         	   		value="<%=cont.getBoard_writer() %>" readonly>
	         	</td>
	         </tr>
	         
	         <tr>
	            <th>글제목</th>
	         	<td>
	         	   <input type="text" name="title"
	         	   		value="<%=cont.getBoard_title() %>">
	         	</td>
	         </tr>
	         
	         <tr>
	            <th>글내용</th>
	         	<td>
	         	   <textarea rows="7" cols="25" name="content"><%=cont.getBoard_cont() %></textarea>
	         	</td>
	         </tr>
	         
	         <tr>
	            <th>글 비밀번호</th>
	         	<td>
	         	   <input type="password" name="pwd">
	         	</td>
	         </tr>
	         
	         <tr>
	            <td colspan="2" align="center">
	               <input type="submit" value="글수정">&nbsp;&nbsp;
	               <input type="reset" value="다시작성">
	            </td>
	         </tr>
	      </table>
	   
	   </form>
	</div>
	
</body>
</html>
