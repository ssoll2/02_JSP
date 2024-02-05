<%@page import="com.dept.model.DeptDTO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
 	DeptDTO cont = (DeptDTO)request.getAttribute("Content");
 
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <div align="center">
        <hr width="50%" color="purple">
        <h3><%=cont.getDeptno()%> DEPT 테이블 수정 폼 페이지</h3>
        <hr width="50%" color="purple">
        <br><br>

        <form method="post" action="<%=request.getContextPath()%>/dept_update_ok">
            <table border="1">
                <tr>
                    <th>부서No.</th>
                    <td>
                        <input type="text" name="deptno" value="<%=cont.getDeptno()%>" readonly>
                    </td>
                </tr>
                <tr>
                    <th>부서명</th>
                    <td>
                        <input type="text" name="dname" value="<%=cont.getDname()%>">
                    </td>
                </tr>
                <tr>
                    <th>부서위치</th>
                    <td>
                        <input type="text" name="loc" value="<%=cont.getLoc()%>">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="부서수정">&nbsp;&nbsp;
                        <input type="reset" value="다시작성">
                    </td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>
