package com.board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
		// DB와 연결하는 객체
		Connection con = null;
		
		// DB에 SQL문을 전송하는 객체
		PreparedStatement pstmt = null;
		
		//SQL문을 실행한 후에 결과값을 가지고 있는 객체
		ResultSet rs = null;
		
		// SQL문을 저장할 변수
		String sql = null;
		
		//MemberDAO객체를 싱글턴 방식으로 만들어보자.
		// 1단계 : MemberDAO 객체를 정적(static) 멤버로 선언을 해줘야 한다.
		// static은 객체 생성없이 사용할 수 있는 멤버로 프로그램 시작시 바로 메모리에 올라간다.
		private static BoardDAO instance = null;
		
		// 2단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
		//		기본생성자의 접근 제어자를 public이 아닌 private으로 바꿔줘야 한다.
		//		즉, 외부에서 직접적으로 기본생성자를 접근하여 호출하지 못하도록 하는 방법이다.
		private BoardDAO() {} // 기본 생성자
		
		// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해주는 getInstance()라는 메서드를 만들어서
		//   	해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해 주면됨.
		public static BoardDAO getInstance() {
			
			if(instance == null) {
				instance = new BoardDAO();
			}
			return instance;
			
		}// getInstance() end
		
		// JDBC 방식이 아닌 DBCP 방식으로 DB와 연동 작업 진행.
		public void openConn() {
			
			try {
				// 1단계 : JNDI 서버 객체 생성
				// 자바의 네이밍 서비스(JNDI)에서 실제 객체를 
				// 연결해 주는 개념이 Context 객체이며, 
				// InitialContext 객체는 네이밍 서비스를 이용하기 위한 시작점이 됨.
				Context initCtx = new InitialContext();
				
				// 2단계 : Context 객체를 얻어와야 함.
				// "java:comp/env" 라는 이름의 인수로 Context 객체를 얻어옴.
				// "java:comp/env" 는 현재 웹 애플리케이션에서
				// 네이밍 서비스를 이용시 루트 디렉토리라고 생각하면 됨.
				// 즉, 현재 웹 애플리케이션이 사용할 수 있는 모든 자원은
				// "java:comp/env" 아래에 위치하게 됨.
				Context ctx = (Context)initCtx.lookup("java:comp/env");
				
				// 3단계 : lookup()메서드를 이용하여 매칭되는 커넥션을 찾아옴.
				// "java:comp/env" 아래에 위치한 "jdbc/mysql" 자원을 얻어옴.
				// 이 자원이 바로 데이터 소스(커넥션 풀) 임.
				// 여기서 "jdbc/mysql"은 context.xml 파일에 추가했던
				// <Resource> 태그 안에 있던 name 속성의 값임.
				DataSource ds = (DataSource) ctx.lookup("jdbc/mysql");
				
				// 4단계 : DataSource 객체를 이용하여 커넥션을 하나 가져오면 됨.
				con = ds.getConnection();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}// openConn() end
		
		// DB에 연결된 자원을 종료하는 메서드.(select)
		public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con)  {
				try {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}// closeConn() end
		
		// DB에 연결된 자원을 종료하는 메서드.(insert, delete,update)
		public void closeConn(PreparedStatement pstmt, Connection con)  {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}// closeConn() end

		
		// 전체 게시물의 수를 확인 하는 메서드.
		public int getBoardCount() {
			
			int count = 0;
			
			try {
				
				openConn();
				
				sql = "select count(*) from board";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					// 검색된 결과값은 한개이므로 1로 지정한다.
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
			return count;
			
		} // getBoardCount() end
		
		 // board 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메서드.
		public List<BoardDTO> getBoardList(int page, int rowsize) {
			  
			  List<BoardDTO> list = new ArrayList<BoardDTO>();
			  
			 // 해당 페이지에서 시작 번호 
			int startNo = (page * rowsize) - (rowsize - 1);
				
			// 해당 페이지에서 끝 번호
			int endNo = (page * rowsize);
			
			try {
				openConn();
				
				// 오라클에서는 row_number() 함수가 있으나
				// mysql에서는 지원되지 않아 코딩으로 페이지를 검색한다.
				sql = "SELECT * FROM (SELECT @row_number:=@row_number+1 AS rnum, b.* " +
					      "FROM board b, (SELECT @row_number:=0) AS dummy " +
					      "ORDER BY board_no DESC) AS subquery " +
					      "WHERE rnum >= ? AND rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					BoardDTO dto = new BoardDTO();
					
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					
					list.add(dto);
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
			return list;
		}

	
			
		
		
		
		
		
		

} // class end
