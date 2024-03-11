package com.reply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;


public class BbsContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  {
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		
		BbsDAO dao = BbsDAO.getInstance();
		
		dao.bbshit(board_no);
		
		BbsDTO dto = dao.getBbsContent(board_no);
		
		request.setAttribute("Cont", dto);
		request.setAttribute("Page", nowPage);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("bbs/bbs_content.jsp");
		return forward;
		
		
		
		
		 
		
		

}
	
}
