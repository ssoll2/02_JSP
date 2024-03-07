package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberInsertOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원 추가 폼 페이지에서 넘어온 데이터들을 가지고
		// member10 테이블의 회원으로 추가시키는 비지니스 로직
		
		// 1단계 : 회원추가 폼 페이지에서넘어온 데이터들을 받아 주어야 한다.
		// 1단계 : 회원추가 폼 페이지에서 넘어온 데이터들을 받아 주어야 한다.
		
		String member_id = request.getParameter("mem_id");
		String member_name = request.getParameter("mem_name");
		String member_pwd = request.getParameter("mem_pwd");
		int member_age = Integer.parseInt(request.getParameter("mem_age"));
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage"));
		String member_job = request.getParameter("mem_job");
		String member_addr = request.getParameter("mem_addr");
		
		
		// 2단계 : 데이터들을 DTO 객체의 setter() 메서드의
		// 		  인자로 넘겨주어 멤버변수에 초기값으로 할당을 한다.
		MemberDTO dto = new MemberDTO();
		
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);;
		
		// 3단계 : DTO 객체를 DAO 메서드 호출 시
		//		  인자로 넘겨주어 DB에 저장을 하자.
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원 추가 성공!!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 추가 실패!!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return null; // 페이지 이동 시킬 필요없음.
	}

}
