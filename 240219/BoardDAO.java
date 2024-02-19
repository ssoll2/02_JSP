package com.board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;







public class BoardDAO {
	
	// DB와 연결하는 객체
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	//SQL문을 실행한 후에 결과값을 가지고 있는 객체
	ResultSet rs = null;
	
	// SQL문을 저장할 변수
	String sql = null;
	
	//BoardDAO객체를 싱글턴 방식으로 만들어보자.
	// 1단계 : BoardDAO 객체를 정적(static) 멤버로 선언을 해줘야 한다.
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
	
	// DB 연동하는 작업을 진행하는 메서드
	public void openConn() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3333/hms";
		String user = "root";
		String pwd = "1234";
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, user, pwd);
		
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
		
		// board 테이블의 전체 게시물의 수를 조회하는 메서드.
		public int getBoardCount() {
			
			int count = 0;
			
			try {
				openConn();
				
				sql = "select count(*) from board";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return count;
			
			
		}// getBoardCount() end

		// board 테이블의 전체 게시물을 조회하는 메서드
		public List<BoardDTO> getBoardList() {
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			openConn();
	
			try {
				openConn();
				
				sql = "select * from board order by board_no desc";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.executeQuery();
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					// board 테이블에서 하나의 레코드를 가져와서
					// 각각 컬럼의 데이터를 DTO 객체의 setter()
					// 메서드의 인자로 전달
					
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
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		
		
		}// getBoardList()end

		// board 테이블의 게시글 번호에 해당하는 게시글의 
		// 조회수를 증가시켜 주는 메서드
		public void boardHit(int no) {
			
			
			try {
				openConn();
				
				sql = "update board set board_hit = board_hit + 1 where board_no = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con);
			}
		}// boardHit(board_no) end

		// 글 번호에 해당하는 게시글을 조회하는 메서드
		public BoardDTO contentBoard(int no) {
			
			BoardDTO dto = null;
			
			try {
				openConn();
				
				sql = "select * from board where board_no = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // 있으면 dto를 만듬
					dto = new BoardDTO();
					
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
			return dto;
		
		} // contentBoard(board_no) end

		// board 테이블에 게시글을 추가하는 메서드.
		public int insertBoard(BoardDTO dto) {
			
			int result = 0, count = 0;
			
			try {
				openConn();
				
				sql = "select max(board_no) from board";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				// 글 번호, 작성자, 글 제목, 글 내용, 비밀번호       클릭수,   작성일,  수정일
				sql = "insert into board value(?,?,?,?,?, default, now(), null)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, count+1);
				pstmt.setString(2, dto.getBoard_writer());
				pstmt.setString(3, dto.getBoard_title());
				pstmt.setString(4, dto.getBoard_cont());
				pstmt.setString(5, dto.getBoard_pwd());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		}// insertBoard(BoardDTO dto) end
		
		
	
	
	
	
	
	
	
	
	
	
	
	

}// CLASS END
