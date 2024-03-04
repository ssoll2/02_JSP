package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardInsertOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String board_writer = request.getParameter("board_writer");
		String board_title = request.getParameter("board_title");
		String board_cont = request.getParameter("board_cont");
		String board_pwd = request.getParameter("board_pwd");
		
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시물 추가 성공!!')");
			out.println("location.href='insert.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시물 추가 실패!!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
	 // return null: 페이지 이동시킬 필요 없음.
		
		
	}

}
