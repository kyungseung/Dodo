package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

//DB접근
public class NoticeDAO {

	
	//공지 전달내용 저장
	public int noticeinsert(NoticeDTO notice) {
		String SQL = "INSERT INTO notice VALUES(NULL,?,?,?,?,?)"; //NoticeNum, NoticeDate ,NoticeContent, userT_ID ,subjectCode
																// NoticeNum은 auto increament 설정 되있기 때문에 NULL값을 넣어주면 차례대로 증가
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //특정한 sql문 실행이후 결과값 처리
		try {
			conn = DatabaseUtil.getConnection(); //연결된 객체 반환
			pstmt = conn.prepareStatement(SQL); //sql문 실행 가능한 형태로 준비. 
			
			//sql문 ?에 들어갈 값들
			pstmt.setString(1, notice.getNoticeDate()); 
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getUserT_ID());
			pstmt.setInt(4, notice.getSubjectCode());
			pstmt.setInt(5, notice.getUserGrade());
			
			return pstmt.executeUpdate();//데이터 업데이트. 데이터의 개수를 반환하기 때문에 데이터 업데이트가 성공하면 1이 반환. 
			
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			//사용후 닫아주어야함.
			try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
			try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
		}
		return -1; //공지 저장 실패
	}
	
	
	//해당 아이디가 작성한 공지 보기.(교사 공지 확인)
	public ArrayList<NoticeDTO> NoticeList(String user_ID) {
		String SQL = "SELECT * FROM notice WHERE userT_ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeDTO> List = null;
		
		try {
			conn = DatabaseUtil.getConnection(); 
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_ID); 
			rs = pstmt.executeQuery();
			
			
			List = new ArrayList<NoticeDTO>();
			
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				//dto.setNoticeNum(rs.getInt(1));
				dto.setNoticeContent(rs.getString(3));
				dto.setNoticeDate(rs.getString(2));

				List.add(dto);
			}
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
			try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
		}
		return List;
	}
	
	//해당 과목코드 공지list 보기
	public ArrayList<NoticeDTO_join> NoticeList2(int subjectCode, int userGrade) {
		//String SQL = "SELECT * FROM notice WHERE subjectCode = ? AND userGrade = ? ORDER BY NoticeNum LIMIT 3";// 
		String SQL = "SELECT N.NoticeContent, N.NoticeDate, N.userT_ID, T.userName "
				+ "FROM notice N LEFT JOIN user_teacher T "
				+ "ON N.userT_ID = T.userT_ID "
				+ "WHERE N.subjectCode = ? AND N.userGrade = ? "
				+ "ORDER BY N.noticeNum desc LIMIT 3"; //최근 등록 3개글까지.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		ArrayList<NoticeDTO_join> List = null; //ArrayList<NoticeDTO> List = null;
		try {
			conn = DatabaseUtil.getConnection(); 
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, subjectCode);
			pstmt.setInt(2, userGrade);
			rs = pstmt.executeQuery();
			List = new ArrayList<NoticeDTO_join>(); //new ArrayList<NoticeDTO>();
			while(rs.next()) {
				NoticeDTO_join dto = new NoticeDTO_join(); //NoticeDTO dto = new NoticeDTO();
				dto.setNoticeContent(rs.getString(1));
				dto.setNoticeDate(rs.getString(2));
				dto.setUserT_ID(rs.getString(3));
				dto.setUserName(rs.getString(4));
				List.add(dto);
			}
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
			try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
		}
		return List;
	}
	
	
	
}
