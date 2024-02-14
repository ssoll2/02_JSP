package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateOkServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원 수정 폼 페이지에서 넘어온 데이터들을
		// DB에 수정하여 저장시키는 비지니스 로직
		
		// 요청과 응답 시 한글 깨짐 방지 설정 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		
		// 1단계 : emp_modify.jsp 페이지에서 넘어온 정보들을 받아주어야 한다.
		int emp_no = Integer.parseInt(request.getParameter("num").trim());
		String emp_name = request.getParameter("name").trim();
		String emp_job = request.getParameter("job").trim();
		int emp_mgr = Integer.parseInt(request.getParameter("mgr").trim());
		int emp_sal = Integer.parseInt(request.getParameter("sal").trim());
		int emp_comm = Integer.parseInt(request.getParameter("comm").trim());
		int emp_dept = Integer.parseInt(request.getParameter("dept").trim());
		
		// 2단계 : 넘어온 데이터들을 DB에 전송해 주어야 한다.
		//        전송 시 DTO 객체를 이용한다.
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(emp_no);
		dto.setEname(emp_name);
		dto.setJob(emp_job);
		dto.setMgr(emp_mgr);
		dto.setSal(emp_sal);
		dto.setComm(emp_comm);
		dto.setDeptno(emp_dept);
		
		EmpDAO dao = EmpDAO.getInstance();
		
		int res = dao.updateEmp(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('사원 수정 성공!!!')");
			out.println("location.href='content.do?no=" + dto.getEmpno() + "'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('사원 수정 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
