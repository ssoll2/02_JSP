<%@page import="com.dept.model.DeptDTO" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("List");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>DEPT 테이블 부서 전체 리스트</h3>
		<hr width="50%" color="blue">
		<br><br>
		<table border="1" width="800">
    <tr>
        <th>부서No.</th> <th>부서명</th>
        <th>부서위치</th><th>부서수정</th><th>부서삭제</th>
    </tr>
    <% 
    if(dept.size() != 0) {  // 데이터가 있다면
        // 데이터 수만큼 반복해서 화면에 출력
        for(int i=0; i<dept.size(); i++) {
            DeptDTO dto = dept.get(i);
    %>
        <tr>
            <td> <%=dto.getDeptno()%> </td>
            <td> <%=dto.getDname()%> </td>
            <td> <%=dto.getLoc()%> </td>
            <td> 
                <input type="button" value="부서수정"
                    onclick="location.href='update?no=<%=dto.getDeptno() %>'">
            </td>
            <td>
                <input type="button" value="부서삭제"
                    onclick="location.href='delete?no=<%=dto.getDeptno()%>'">
            </td>
        </tr>
    <%   
        }  
    } else {  // 데이터가 없다면 
    %>
        <tr>
            <td colspan="5" align="center">
                <h3>학생 데이터가 없습니다.....</h3>
            </td>
        </tr>   
    <%  
    }  
    %>
    
</table>
<br>
	<tr>
	    <td colspan="5" align="center">
	         <input type="button" value="부서추가"
	            onclick="location.href='view/dept_insert.jsp'">
	    </td>
	</tr>
	
	
	
	</div>

</body>
</html>
