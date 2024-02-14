package com.emp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ContentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 사번에 해당하는
		// 사원의 정보를 view page로 넘겨주는 비지니스 로직.
				int emp_no = 
						Integer.parseInt(request.getParameter("no").trim());
				
				EmpDAO dao = EmpDAO.getInstance();
				
				EmpDTO cont = dao.contentEmp(emp_no);
				
				request.setAttribute("Content", cont);
				
				RequestDispatcher rd = request.getRequestDispatcher("view/emp_content.jsp");
				
				rd.forward(request, response);
	
	
	}

}
