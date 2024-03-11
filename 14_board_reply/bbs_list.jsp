<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<style type="text/css">
	.pagination{
		justify-content: center;
		
	}
	.table {
		width : 700px;
	}
</style>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>JSP_BBS 답변형 게시판 테이블 전체 게시물 리스트 페이지</h3>
		<hr width="50%" color="">
		<br><br>
		
		<table border="1" width="650"> 
			<tr>
				<th>글No.</th><th>글제목</th><th>조회수</th>
				<th>작성일자</th><th>Group</th><th>Step</th><th>Indent</th>
			</tr>
			
			<c:set var="list" value="${List }"/>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td>
							<!-- 댓글인 경우  -->
							<c:if test="${dto.getBoard_indent() != 0 }">
								<c:forEach begin="1" end="${dto.getBoard_indent }">
									&nbsp;&nbsp;
								</c:forEach>
							</c:if>
							<a href="<%=request.getContextPath()%>/bbs_content.go?no=${dto.getBoard_no()}&page=${page}">${dto.getBoard_title() }</a>
						</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date()}</td>
						<td>${dto.getBoard_group() }</td>
						<td>${dto.getBoard_step() }</td>
						<td>${dto.getBoard_indent() }</td>
						
						
					</tr>
				
				
				</c:forEach>
			</c:if>
		
		
		
		</table>
		<br><br>
		
		<!-- Modal 창 만드는 버튼  -->
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
		  게시글 작성
		</button>

		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">JSP_BBS 답변형 게시판 등록 폼 페이지</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form method="post"
		            action="<%=request.getContextPath() %>/bbs_write_ok.go">
		            
		            <table border="1" width="300">
		               <tr>
		                  <th>작성자</th>
		                  <td>
		                     <input type="text" name="writer">
		                  </td>
		               </tr>
		               
		               <tr>
		                  <th>글제목</th>
		                  <td>
		                     <input type="text" name="title">
		                  </td>
		               </tr>
		               
		               <tr>
		                  <th>글내용</th>
		                  <td>
		                     <textarea rows="7" cols="25" name="cont"></textarea>
		                  </td>
		               </tr>
		               
		               <tr>
		                  <th>글 비밀번호</th>
		                  <td>
		                     <input type="password" name="pwd">
		                  </td>
		               </tr>
		            </table>
		            <br>
		            
		            <div class="submit1">
		               <input class="submit_btn btn-primary" type="submit" value="글쓰기">
		               <input class="submit_btn btn-primary" type="reset" value="다시작성">
		            </div>
		         </form> 
		      </div>
		      
		    </div>
		  </div>
		</div>
	
	</div>

</body>
</html>
