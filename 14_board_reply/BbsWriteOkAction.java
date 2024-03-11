package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("cont").trim();
		String board_pwd = request.getParameter("pwd").trim();
	
		BbsDTO dto = new BbsDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		int check = dao.insertBbs(dto);
       		
		PrintWriter out = response.getWriter();
		
		request.setAttribute("Cont", dto);
		
		ActionForward forward = new ActionForward();
		
	
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시글 추가 성공!!!')");
			out.println("location.href='bbs_list.go'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 추가 실패!!!')");
			out.println("history.back()");
			out.println("</script>");
			
		}
		
		
		
		forward.setRedirect(false);
		
		return forward;
		
		
	}

}
