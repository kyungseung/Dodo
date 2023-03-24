package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

//DB접근
public class UserDAO {
	
	//학생_로그인
	public int studentLogin(String userS_ID, String userPW) {
		String SQL = "SELECT userPW FROM user_student WHERE userS_ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //특정한 sql문 실행이후 결과값 처리
		try {
			conn = DatabaseUtil.getConnection(); //연결된 객체 반환
			pstmt = conn.prepareStatement(SQL); //sql문 실행 가능한 형태로 준비. 
			pstmt.setString(1, userS_ID); //위의 '?'값.
			rs = pstmt.executeQuery(); //실행 결과 담음. //executeQuery() 데이터 검색
			if(rs.next()) {
				//sql결과가 존재하는 경우
				if(rs.getString(1).equals(userPW)){
					return 1; //로그인 성공
				}//사용자 입력 비밀번호와 DB 비밀번호가 같은 경우 1 반환
				else {
					return 2;
					//return 0; //비밀번호 틀림
				}
			}
			return -1; //id존재x
			
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			//사용후 닫아주어야함.
			try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
			try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
		}
		return -2; //데이터베이스 오류
	}
	
	//교사_로그인
		public int teacherLogin(String userS_ID, String userPW) {
			String SQL = "SELECT userPW, SubjectCode FROM user_teacher WHERE userT_ID = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null; //특정한 sql문 실행이후 결과값 처리
			try {
				conn = DatabaseUtil.getConnection(); //연결된 객체 반환
				pstmt = conn.prepareStatement(SQL); //sql문 실행 가능한 형태로 준비. 
				pstmt.setString(1, userS_ID); //위의 '?'값.
				rs = pstmt.executeQuery(); //실행 결과 담음. //executeQuery() 데이터 검색

				 if(rs.next()) {
		            //sql결과가 존재하는 경우
		            if(rs.getString(1).equals(userPW)){
						return 1; //로그인 성공
					}//사용자 입력 비밀번호와 DB 비밀번호가 같은 경우 1 반환
					else {
						return 2;
						//return 0; //비밀번호 틀림
					}
				}
				return -1; //id존재x
				
			} catch(Exception e) {
				//e.printStackTrace();
			} finally {
				//사용후 닫아주어야함.
				try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
				try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
				try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
			}
			return -2; //데이터베이스 오류
		}
		
	
	
	
	//학생_회원가입
	public int studentjoin(UserstudentDTO user) {
		//+수정함
		String SQL = "INSERT INTO user_student VALUES (?,?,?,?)"; //userS_ID, userPW, userName, userGrade
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //특정한 sql문 실행이후 결과값 처리
		try {
			conn = DatabaseUtil.getConnection(); //연결된 객체 반환
			pstmt = conn.prepareStatement(SQL); //sql문 실행 가능한 형태로 준비. 
			
			//sql문 ?에 들어갈 값들
			pstmt.setString(1, user.getUserS_ID()); 
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserName());
			pstmt.setInt(4, user.getUserGrade());
			
			return pstmt.executeUpdate();//데이터 업데이트. 데이터의 개수를 반환하기 때문에 데이터 업데이트가 성공하면 1이 반환. 
			
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			//사용후 닫아주어야함.
			try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
			try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
		}
		return -1; //회원가입 실패
	}
	
	
	//교사_회원가입
		public int teacherjoin(UserteacherDTO user) {
			//+수정함
			String SQL = "INSERT INTO user_teacher VALUES (?,?,?,?,?)"; //userT_ID, userPW, userName, userGrade, subjectCode
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null; //특정한 sql문 실행이후 결과값 처리
			try {
				conn = DatabaseUtil.getConnection(); //연결된 객체 반환
				pstmt = conn.prepareStatement(SQL); //sql문 실행 가능한 형태로 준비. 
				
				//sql문 ?에 들어갈 값들
				pstmt.setString(1, user.getUserT_ID()); 
				pstmt.setString(2, user.getUserPW());
				pstmt.setString(3, user.getUserName());
				pstmt.setInt(4, user.getUserGrade());
				pstmt.setInt(5, user.getSubjectCode());
				
				return pstmt.executeUpdate();//데이터 업데이트. 데이터의 개수를 반환하기 때문에 데이터 업데이트가 성공하면 1이 반환. 
				
			} catch(Exception e) {
				//e.printStackTrace();
			} finally {
				//사용후 닫아주어야함.
				try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
				try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
				try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
			}
			return -1; //회원가입 실패
		}
	
//교사 담당학년, 과목코드 //userName추가 0603
		public ArrayList<UserteacherDTO> teacherCode(String userT_ID) { 
			String SQL = "SELECT subjectCode, userGrade, userName FROM user_teacher WHERE userT_ID = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null; //특정한 sql문 실행이후 결과값 처리
			ArrayList<UserteacherDTO> List = null;
			
			try {
				conn = DatabaseUtil.getConnection(); //연결된 객체 반환
				pstmt = conn.prepareStatement(SQL); //sql문 실행 가능한 형태로 준비. 
				pstmt.setString(1, userT_ID); //위의 '?'값.
				rs = pstmt.executeQuery(); //실행 결과 담음. //executeQuery() 데이터 검색
				
				
				List = new ArrayList<UserteacherDTO>();
				
				while(rs.next()) {
					UserteacherDTO dto = new UserteacherDTO();
					
					dto.setSubjectCode(rs.getInt(1));
					dto.setUserGrade(rs.getInt(2));
					dto.setUserName(rs.getString(3));
					
					List.add(dto);
				}
			} catch(Exception e) {
				//e.printStackTrace();
			} finally {
				//사용후 닫아주어야함.
				try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
				try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
				try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
			}
			return List;
		}
		
		//학생 학년
		public int StudentCode(String userS_ID) {
			String SQL = "SELECT userGrade FROM user_student WHERE userS_ID = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int userGrade = 0;
			try {
				conn = DatabaseUtil.getConnection(); 
				pstmt = conn.prepareStatement(SQL); 
				pstmt.setString(1, userS_ID); 
				rs = pstmt.executeQuery(); //executeQuery() 데이터 검색
				
				if(rs.next()) {
					userGrade = rs.getInt(1);
				}
				return userGrade;
			} catch(Exception e) {
				//e.printStackTrace();
			} finally {
				//사용후 닫아주어야함.
				try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
				try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
				try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
			}
			return -1;//오류
		}
		
		
		//교사 이름 가져오기
		public String TeacherName(String userT_ID) {
			String SQL = "SELECT userName FROM user_teacher WHERE userT_ID = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String userName = "";
			String error = "DB오류" ;
			
			try {
				conn = DatabaseUtil.getConnection(); 
				pstmt = conn.prepareStatement(SQL); 
				pstmt.setString(1, userT_ID); 
				rs = pstmt.executeQuery(); //executeQuery() 데이터 검색
				
				if(rs.next()) {
					userName = rs.getString(1);
				}
				return userName;
			} catch(Exception e) {
				//e.printStackTrace();
			} finally {
				//사용후 닫아주어야함.
				try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
				try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
				try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
			}
			
			return error;//오류
		}
		
		//학생 이름 가져오기
		public String StudentName(String userS_ID) {
			String SQL = "SELECT userName FROM user_student WHERE userS_ID = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String userName = "";
			String error = "DB오류" ;
			
			try {
				conn = DatabaseUtil.getConnection(); 
				pstmt = conn.prepareStatement(SQL); 
				pstmt.setString(1, userS_ID); 
				rs = pstmt.executeQuery(); //executeQuery() 데이터 검색
				
				if(rs.next()) {
					userName = rs.getString(1);
				}
				return userName;
			} catch(Exception e) {
				//e.printStackTrace();
			} finally {
				//사용후 닫아주어야함.
				try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
				try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
				try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
			}
			
			return error;//오류
		}
		
	
	
}
