<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<EmpDTO> emp = (List<EmpDTO>)request.getAttribute("List");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="30%" color="purple">
	      <h3>EMP 테이블 전체 사원 리스트 페이지</h3>
	   <hr width="30%" color="purple">
	   <br> <br>
	   
	   <table border="1" width="400">
	      <tr>
	         <th>사원No.</th> <th>사원명</th>
	         <th>부서No.</th> <th>입사일자</th>
	      </tr>
	      
	      <%
	         if(emp.size() != 0) {
	        	 
	        	 for(int i=0; i<emp.size(); i++) {
	        		 EmpDTO dto = emp.get(i);
	      %>
	      			<tr>
	      			   <td> <%=dto.getEmpno() %> </td>
	      			   <td> 
	      			      <a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getEmpno() %>">
	      			   					<%=dto.getEname() %> </a> </td>
	      			   <td> <%=dto.getDeptno() %> </td>
	      			   <td> <%=dto.getHiredate().substring(0,10) %> </td>
	      			</tr>
	      <%   	 }  // for문 end
	         }else {
	        	 // 사원 목록이 없는 경우
	      %>
	      		<tr>
	      		   <td colspan="4" align="center">
	      		      <h3>사원 전체 목록이 없습니다.....</h3>
	      		   </td>
	      		</tr>
	     <% } %>
	     
	     <tr>
	        <td colspan="4" align="center">
	           <input type="button" value="사원등록"
	           		onclick="location.href='insert.do'">
	        </td>
	     </tr>
	     
	   </table>
	
	</div>






</body>
</html>
