<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<String> jList = (List<String>)request.getAttribute("jList");
	List<EmpDTO> mList = (List<EmpDTO>)request.getAttribute("mList");
	List<DeptDTO> dList = (List<DeptDTO>)request.getAttribute("dList");
	EmpDTO cont = (EmpDTO)request.getAttribute("Modify");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="50%" color="gray">
	      <h3>EMP 테이블 사원 정보 수정 폼 페이지</h3>
	   <hr width="50%" color="gray">
	   <br> <br>
	   
	   <form method="post"
	      action="<%=request.getContextPath() %>/update_ok.do">
	   
	      <table border="1" width="300">
	         <%
	         	if(cont != null) {
	         %>
	         	   <tr>
			          <th>사원No.</th>
			          <td> <input type="text" name="num" readonly
			                      value="<%=cont.getEmpno() %>"> </td>
			       </tr>
			       
			       <tr>
			          <th>사원명</th>
			          <td> <input type="text" name="name" readonly
			                      value="<%=cont.getEname() %>"> </td>
			       </tr>
			       
			       <tr>
			          <th>담당업무</th>
			          <td> 
					     <select name="job">
			                 <%
			                    if(jList.size() == 0) {
			                 %>
			                     <option value="">:::담당업무 없음:::</option>
			                 <% }else {  // 카테고리 코드가 있는 경우
			                	   for(int i=0; i<jList.size(); i++) {
			                			String strJob = jList.get(i);
			                			
			                			if(strJob.equals(cont.getJob())) {
			                 %>
						                 	 <option value="<%=strJob %>" selected>
						                 	 		  <%=strJob %> 
						                 	 </option>				
			                 <% 		}else {
			                 %>
						                	 <option value="<%=strJob %>">
					              	 		          <%=strJob %> 
					              	 		 </option>				
			                 <%}
			                	   }
			                 }  %>
               			 </select>
					  </td>
			       </tr>
			       
			       <tr>
			          <th>담당 관리자</th>
			          <td> 
					     <select name="mgr">
			                 <%
			                    if(mList.size() == 0) {
			                 %>
			                     <option value="">:::담당 관리자 없음:::</option>
			                 <% }else {  // 카테고리 코드가 있는 경우
			                	   for(int i=0; i<mList.size(); i++) {
			                			EmpDTO mgrCont = mList.get(i);
			                			
			                			if(mgrCont.getEmpno() == cont.getMgr()) {
			                 %>
						                 	 <option value="<%=mgrCont.getEmpno() %>" selected>
						                 	 		  <%=mgrCont.getEmpno() %>
						                 	 		  [<%=mgrCont.getEname() %>] 
						                 	 </option>				
			                 <% 		}else {
			                 %>
						                	 <option value="<%=mgrCont.getEmpno() %>">
						                 	 		  <%=mgrCont.getEmpno() %>
						                 	 		  [<%=mgrCont.getEname() %>] 
						                 	 </option>					
			                 <%}
			                	   }
			                 }  %>
               			 </select>
					  </td>
			       </tr>
			       
			       <tr>
			          <th>사원 급여</th>
			          <td> <input type="text" name="sal"
			                      value="<%=cont.getSal() %>"> </td>
			       </tr>
			       
			       <tr>
			          <th>사원 보너스</th>
			          <td> <input type="text" name="comm"
			                      value="<%=cont.getComm() %>"> </td>
			       </tr>
			       
			       <tr>
			          <th>부서번호</th>
			          <td> 
					     <select name="dept">
			                 <%
			                    if(dList.size() == 0) {
			                 %>
			                     <option value="">:::부서번호 없음:::</option>
			                 <% }else {  // 부서번호가 있는 경우
			                	   for(int i=0; i<dList.size(); i++) {
			                			DeptDTO dCont = dList.get(i);
			                			
			                			if(dCont.getDeptno() == cont.getDeptno()) {
			                 %>
						                 	 <option value="<%=dCont.getDeptno() %>" selected>
						                 	 		  <%=dCont.getDeptno() %>
						                 	 		  [<%=dCont.getDname() %>] 
						                 	 </option>				
			                 <% 		}else {
			                 %>
						                     <option value="<%=dCont.getDeptno() %>">
						                 	 		  <%=dCont.getDeptno() %>
						                 	 		  [<%=dCont.getDname() %>] 
						                 	 </option>					
			                 <%}
			                	   }
			                 }  %>
               			 </select>
					  </td>
			       </tr>	
	         <%	}  %>
	         
	         <tr>
	            <td colspan="2" align="center">
	               <input type="submit" value="사원수정">
	                  &nbsp;&nbsp;
	         	   <input type="reset" value="다시작성">
	            </td>
	         </tr>
	      </table>
	   </form>
	</div>
	
</body>
</html>
