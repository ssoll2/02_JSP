<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	EmpDTO cont = (EmpDTO)request.getAttribute("Content");
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
	      <h3>EMP 테이블 사원 상세 정보 페이지</h3>
	   <hr width="30%" color="marmoon">
	   <br> <br>
	   
	   <table border="1" width="400">
	      <%
	         if(cont != null) {  // 데이터가 있다면
	      %>
		      	<tr>
		      	   <th>사원No.</th>
		      	   <td> <%=cont.getEmpno() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>사원명</th>
		      	   <td> <%=cont.getEname() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>담당업무</th>
		      	   <td> <%=cont.getJob() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>관리자No.</th>
		      	   <td> <%=cont.getMgr() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>사원 급여</th>
		      	   <td> <%=cont.getSal() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>사원 보너스</th>
		      	   <td> <%=cont.getComm() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>사원 부서번호</th>
		      	   <td> <%=cont.getDeptno() %> </td>
		      	</tr>
		      	
		      	<tr>
		      	   <th>사원 입사일</th>
		      	   <td> <%=cont.getHiredate() %> </td>
		      	</tr>
	        	 
	      <% }else {  // 데이터가 없다면 %>
	        	<tr>
	        	   <td colspan="2" align="center">
	        	      <h3>해당 사원의 정보가 없습니다.....</h3>
	        	   </td>
	        	</tr>
	      <% } %>
	   </table>
	   <br>
	   
	   <input type="button" value="사원수정"
	        onclick="location.href='update.do?num=<%=cont.getEmpno() %>'">&nbsp;&nbsp;
	   
	   <input type="button" value="사원삭제"
	   		onclick="if(confirm('정말로 삭제하시겠습니까')) {
	   					location.href='delete.do?num=<%=cont.getEmpno() %>'
	   				 }else { return; }">&nbsp;&nbsp;   
	   
	   <input type="button" value="사원목록"
	   		onclick="location.href='select.do'">
	   		
	</div>
</body>
</html>








