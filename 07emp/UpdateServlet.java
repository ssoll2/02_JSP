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



@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 수정 폼 페이지로 넘어가는 컨트롤러
		
				int emp_no = Integer.parseInt(request.getParameter("num").trim());
				
				EmpDAO dao = EmpDAO.getInstance();
				
				// EMP 테이블에서 담당업무 리스트를 조회하자.
				List<String> jobList = dao.getJobList();
				
				// EMP 테이블에서 관리자 사원리스트를 조회하자.
				List<EmpDTO> mgrList = dao.getMgrList();
				
				// 부서 테이블 전체 리스트를 조회하자.
				List<DeptDTO> deptList = dao.getDeptList();
				
				// 사원번호에 해당하는 사원의 상세정보를 조회하자.
			    EmpDTO cont = dao.contentEmp(emp_no);
			    
			    // 모든 정보를 view page로 이동시키자.
			    request.setAttribute("jList", jobList);
			    request.setAttribute("mList", mgrList);
			    request.setAttribute("dList", deptList);
			    request.setAttribute("Modify", cont);
			    
			    RequestDispatcher rd = request.getRequestDispatcher("view/emp_modify.jsp");
			    rd.forward(request, response);
				
			
	
	}

}
