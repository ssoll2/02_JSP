package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		// 요청 : 게시물 전체 목륵을 보여달라는 요청.
				// 응답 : DB의 board 테이블의 전체 게시물 목록을
				//		조회하여 view page로 이동시키는 비지니스 로직.
				//		비지니스 로직 진행 시 페이징 처리 작업도 같이 진행.
				
				// 페이징 처리 작업이 아닌 경우
				// BoardDAO dao = BoardDAO.getInstance();
				// List<BoardDTO> list = dao.getBoardList();
				// request.setAttribute("List", list);
				
				// 페이징 처리 작업 진행
				// 한 페이지당 보여질 게시물의 수
				int rowsize = 3;
				
				// 아래에 보여질 페이지 최대 블럭 수
				int block = 3;
				// 예) [1][2][3] / [4][5][6] / [7][8][9]
				
				// DB상의 게시물의 전체 수
				int totalRecord = 0;
				
				// 전체 페이지 수
				int allPage = 0;
				
				// 현재 페이지 변수
				int page = 0;
				
				if(request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page").trim());
				} else {
					// 처음으로 "게시물 전체 목록" a 태그를 클릭한 경우
					page = 1;
				}
				
				
				// 해당 페이지에서 시작 번호
				int startNo = (page * rowsize) - (rowsize - 1);
				
				// 해당 페이지에서 끝 번호
				int endNo = (page * rowsize);
				
				// 해당 페이지에서 시작 블럭
				int startBlock = (((page - 1) / block) * block) + 1;
				
				// 해당 페이지에서 끝 블럭
				int endBlock = (((page - 1) / block) * block) + block;
				
				
				BoardDAO dao = BoardDAO.getInstance();
				
				// 전체 게시물의 수를 확인하는 메서드.
				totalRecord = dao.getBoardCount();
				
				
				// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로
				// 나누어 주면 전체 페이지가 나옴.
				// 이 때 전체 페이지 수가 나올 때 나머지가 있으면
				// 무조건 전체 페이지 수를 하나 올려 주어야 함.
				allPage = (int)Math.ceil(totalRecord / (double)rowsize);
				
				
				if(endBlock > allPage) {
					endBlock = allPage;
				}
				
				// 현재 페이지에 해당하는 게시물을 가져오는 메서드 호출
				List<BoardDTO> list = dao.getBoardList(page, rowsize);
				
				
				// 페이징 작업 후에는 지금까지 페이징 처리 시 작업했던 모든 데이터들을
				// view page로 바인딩하여 이동을 시켜준다.
				request.setAttribute("page", page);
				request.setAttribute("rowsize", rowsize);
				request.setAttribute("block", block);
				request.setAttribute("totalRecord", totalRecord);
				request.setAttribute("allPage", allPage);
				request.setAttribute("startNo", startNo);
				request.setAttribute("endNo", endNo);
				request.setAttribute("startBlock", startBlock);
				request.setAttribute("endBlock", endBlock);
				request.setAttribute("List", list);
	
	
	} // execute() end
}
