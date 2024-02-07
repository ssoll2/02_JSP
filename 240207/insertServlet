package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.DeptDTO;
import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertServlet() {
        super();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// 사원 등록 폼 페이지로 넘어가기 전에 담당업무 리스트와
	// 관리자 리스트, 부서번호 리스트를 DB에서 조회하여
	// 사원 등록 폼 페이지로 해당 데이터를 넘겨주는 비지니스 로직
		
		// 현재 하고 있는 프로젝트는 싱글톤 방식이다. 따라서 new EmpDAO()를 안쓴다.
		EmpDAO dao = EmpDAO.getInstance(); // 이미 EmpDAO안에 메서드를 만들어놨다.
		
		// 확인차
		System.out.println("insert do >>>"+ dao);
	
		// EMP 테이블에서 담당 업무 리스트를 조회해 보자.
		List<String> jobList = dao.getJobList(); // 글자들만 만들어서 STRING
	
		// EMP 테이블에서 관리자 사원 리스트를 조회해 보자.
		List<EmpDTO> mgrList = dao.getMgrList(); 
		
		// DEPT 테이블에서 부서 전체 리스트를 조회해 보자.
		List<DeptDTO> deptList = dao.getDeptList();
		
		request.setAttribute("Job", jobList);
		request.setAttribute("Mgr", mgrList);
		request.setAttribute("Dept", deptList);
	
		RequestDispatcher rd = request.getRequestDispatcher("view/emp_insert.jsp");
	
		rd.forward(request, response);
	
	
	
	
	
	}

}
