package com.reply.action;

/*
 * ActionForward 클래스
 * 
 * 1. Action 인터페이스에서 리턴타입(반환형)으로 사용될 클래스.
 * 2. 클래스 구성요소.
 * 		1) isRedirect 멤버 - 반환형은 boolean 타입.
 * 			- true : *.go 페이지
 * 			- false : *.jsp 페이지
 * 		2) path 멤버 - 반환형은 String 타입
 * 					- 파일 경로 설정(지정)
 */
public class ActionForward {
	
	private boolean isRedirect; // true
	
	private String path;		// "bbs_list.go"

	
	
	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
	
	
	
	
	
}
