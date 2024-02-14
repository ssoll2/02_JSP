package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	// EmpDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : EmpDAO 객체를 정적(static) 멤버로 선언을 해 주어야 한다.
	private static EmpDAO instance = null;
	
	// 2단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//		  기본 생성자의 접근제어자를 public이 아닌
	//		  private으로 바꾸어 주어야 한다.
	//		  즉, 외부에서 직접적으로 기본생성자를 접근하여
	//		  호출하지 못하도록 하는 방법이다.
	private EmpDAO() {	}
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는
	//        getInstance() 라는 메서드를 만들어서 해당
	//        getInstance() 메서드를 외부에서 접근할 수 
	//        있도록 해 주면 됨.
	public static EmpDAO getInstance() {
		
		if(instance == null) {
			instance = new EmpDAO();
		}
		
		return instance;
	}  // getInstance() 메서드 end
	
	// DB 연동하는 작업을 진행하는 메서드.
	public void openConn() {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3333/hms"; 
		String user = "root";
		String password = "1234";
		
		try {
			// 1. mySQL 드라이버를 메모리로 로딩 작업
			Class.forName(driver);
				
			// 2. mySQL 데이터베이스와 연결 작업 진행
			con = DriverManager.getConnection(url, user, password);
				
				
		} catch (Exception e) {
			e.printStackTrace();
				
		}
		
	} // openConn() end
	
	// DB에 연결된 자원을 종료하는 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		try {
			// open한 역순으로 close한다.
			if(rs != null) {
				rs.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // closeConn() end
	
	// DB에 연결된 자원을 종료하는 메서드
		public void closeConn(PreparedStatement pstmt, Connection con) {
			
			try {
				// open한 역순으로 close한다.
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // closeConn() end
		

		
	// Emp 테이블에서 전체 사원 리스트를 조회하는 메서드.
	public List<EmpDTO> allList(){
		
		// List(컬렉션)안에 EmpDTO객체를 하나씩 넣을 준비한다.
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		
		
		try {
			//1~2단계 진행 : 드라이버 로딩 및 데이터베이스와 연동 작업 진행.
			openConn(); // EmpDAO에 이미 관련작업 메서드를 만들었기 때문에 호출만 하면 처리 끝.
			
			//3단계 : 데이터베이스에 전송할 SQL문 작성.
			sql = "select * from emp order by empno desc";
			
			//4단계 : SQL문을 데이터베이스 전송객체에 인자로 전달.
			pstmt = con.prepareStatement(sql);
			
			//5단계 : SQL문을 DB에 전송 및 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// 가져온 데이터를 DTO에 담는다.
				EmpDTO dto = new EmpDTO();
				
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				
				// dto에 담은 데이터를 리스트에 추가한다.
				list.add(dto);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			// 6단계 : 데이터베이스와 연결되어 있던 자원 종료. 
			closeConn(rs, pstmt, con);
		}
		
		return list;
		
	} // allList() end
	
	// EMP 테이블에서 담당업무를 조회하는 메서드
	public List<String> getJobList() {
		
		// 리스트에 담당업무 글자를 넣기 위해 객체를 생성한다.
		List<String> jobList = new ArrayList<String>();//공식화
		
		try {
			//드라이버 로딩 및 데이터베이스와 연동 작업 진행
			openConn();
			
			// EMP 테이블에서 job을 중복없이 가져오면서 job을 기준으로 오름차순 정렬
			sql = "select distinct(job)from emp order by job";
			
			// 	SQL문을 데이터베이스 전송객체에 인자로 전달
			pstmt = con.prepareStatement(sql);
			
			// SQL문을 DB에 전송 및 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String job = rs.getString("job");
				
				//rs가 가져온 job을 jobList에 추가한다.
				jobList.add(job);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return jobList;
	}//getJobList() end
	
	// EMP 테이블에서 관리자를 조회하는 메서드
	public List<EmpDTO> getMgrList() {
		List<EmpDTO> mgrList = new ArrayList<EmpDTO>();
		
		
		
		try {
			openConn();
			
			// EMP 테이블에서 관리자를 중복없이 검색 후
			// 관리자 번호에 해당하는 사람의 모든 정보를 가져와라~~
			sql = "select * from emp where empno in(select distinct(mgr) from emp)";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// EmpDTO에 담아서 리턴할 예정이다.
				EmpDTO dto = new EmpDTO();
				
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				
				mgrList.add(dto);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return mgrList;
		
	}// getMgrList() end
	
	// DEPT 테이블의 전체 부서 리스트를 조회하는 메서드
	public List<DeptDTO> getDeptList() {
		
		List<DeptDTO> deptList = new ArrayList<DeptDTO>();
		
		try {
			openConn();
			
			sql = "select *from dept order by deptno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//dept 정보를 dto(객체)로 만들어서 리턴할 예정
				DeptDTO dto = new DeptDTO();
				
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				deptList.add(dto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return deptList;
	}// getDeptList() end

	// emp테이블에 사원을 등록하는 메서드
	public  int insertEmp(EmpDTO dto) {
		int result = 0;
		
		
		try {
			// 1 ~ 2단계 진행 : 드라이버 로딩 및 데이터베이스와 연동 작업 진행.
			openConn();
			
			// 3단계 : 데이터베이스에 전송할 SQL문 작성.
			sql = "insert into emp "
					+ " values(?, ?, ?, ?, curdate(), ?, ?, ?)";
			
			// 4단계 : SQL문을 데이터베이스 전송객체에 인자로 전달.
			pstmt = con.prepareStatement(sql);
			
			// 4-1단계 : ?(플레이스 홀더)에 데이터 배정.
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			pstmt.setInt(5, dto.getSal());
			pstmt.setInt(6, dto.getComm());
			pstmt.setInt(7, dto.getDeptno());
			
			// 5단계 : SQL문을 DB에 전송 및 실행.
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6단계 : 데이터베이스와 연결되어 있던 자원 종료.
			closeConn(pstmt, con);
		}
		
		return result;
		
	}// insertemp() end
	
	// 사원번호에 해당하는 사원의 상세정보를 조회하는 메서드.
		public EmpDTO contentEmp(int no) {
			
			EmpDTO dto = null;
			
			try {
				// 1 ~ 2단계 진행 : 드라이버 로딩 및 데이터베이스와 연동 작업 진행.
				openConn();
				
				// 3단계 : 데이터베이스에 전송할 SQL문 작성.
				sql = "select * from emp where empno = ?";
				
				// 4단계 : SQL문을 데이터베이스 전송객체에 인자로 전달.
				pstmt = con.prepareStatement(sql);
				
				// 4-1단계 : ?(플레이스 홀더)에 값을 배정.
				pstmt.setInt(1, no);
				
				// 5단계 : SQL문을 데이터베이스에 전송 및 실행.
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					dto = new EmpDTO();
					
					dto.setEmpno(rs.getInt("empno"));
					dto.setEname(rs.getString("ename"));
					dto.setJob(rs.getString("job"));
					dto.setMgr(rs.getInt("mgr"));
					dto.setHiredate(rs.getString("hiredate"));
					dto.setSal(rs.getInt("sal"));
					dto.setComm(rs.getInt("comm"));
					dto.setDeptno(rs.getInt("deptno"));
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 6단계 : 데이터베이스와 연결되어 있던 자원 종료.
				closeConn(rs, pstmt, con);
			}
			
			return dto;
		}  // contentEmp() 메서드 end
		
		// 사원번호에 해당하는 사원의 정보를 수정하는 메서드.
		public int updateEmp(EmpDTO dto) {
			
			int result = 0;
			
			
			try {
				openConn();
				
				sql = "update emp set "
						+ " job = ?, mgr = ?, "
						+ " sal = ?, comm = ?, "
						+ " deptno = ? where empno = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getJob());
				pstmt.setInt(2, dto.getMgr());
				pstmt.setInt(3, dto.getSal());
				pstmt.setInt(4, dto.getComm());
				pstmt.setInt(5, dto.getDeptno());
				pstmt.setInt(6, dto.getEmpno());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con);
			}
			
			return result;
		}  // updateEmp() 메서드 end

		public int deleteEmp(int no) {
			
			int result = 0;
			
			
			try {
				openConn();
				
				sql = "delete from emp where empno = ?";
				
				pstmt = con.prepareStatement(sql);
				
			
				pstmt.setInt(1, no);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con);
			}
			
			return result;
		}  // deleteEmp() 메서드 end
		
		
		
	
		
		
		
		
		
		
} // class end()





















