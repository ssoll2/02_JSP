package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberInsertAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// 회원 등록 폼 페이지로 이동시키는 비지니스 로직
		return "member/member_insert.jsp";
		
	}

}
