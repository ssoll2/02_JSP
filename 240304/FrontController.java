package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.Action;
import com.board.action.BoardListAction;

public class FrontController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				// 한글 처리 작업 진행
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				
				// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 반환해 주는 메서드.
				String uri = request.getRequestURI();
				System.out.println("URI >>> " + uri);
				
				// getContextPath() : 현재 프로젝트명을 문자열로 반환해주는 메서드.
				String path = request.getContextPath();
				System.out.println("PATH >>> " + path);
				
				String command = uri.substring(path.length()+1);
				System.out.println("Command >>> " + command);
				
				Action action = null;
				
				String viewPage = null;
				
				
				if(command.equals("board_list.do")) {
					action = new BoardListAction();
					action.execute(request, response);
					viewPage = "board/board_list.jsp";
					
				}
				
				RequestDispatcher rd = request.getRequestDispatcher(viewPage);
				
				rd.forward(request, response);
	}
}
