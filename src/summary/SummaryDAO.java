package summary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

public class SummaryDAO{
	
	public int summaryinsert(SummaryDTO summary) {
		String SQL = "INSERT INTO summary VALUES(NULL,?,?,?,?,?)"; 
																
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			conn = DatabaseUtil.getConnection(); 
			pstmt = conn.prepareStatement(SQL); 

			pstmt.setString(1, summary.getSummaryTitle()); 
			pstmt.setString(2, summary.getSummaryContent());
			pstmt.setString(3, summary.getSummaryDate());
			pstmt.setString(4, summary.getUserS_ID());
			pstmt.setInt(5, summary.getSubjectCode());
			
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try{if(conn != null) conn.close();}catch(Exception e) {	e.printStackTrace();}
			try{if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try{if(rs != null) rs.close();}catch(Exception e) {	e.printStackTrace();}
		}
		return -1; 
		
		
	
	}
	//요약리스트 확인
	public ArrayList<SummaryDTO> summaryList(String user_ID, int subjectCode){
		String SQL = "SELECT * FROM summary WHERE userS_ID = ? and subjectCode = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SummaryDTO> List = null;
		try{
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_ID);
			pstmt.setInt(2, subjectCode);
			rs = pstmt.executeQuery();
			
			List = new ArrayList<SummaryDTO>();
			
			while(rs.next()) {
				SummaryDTO dto = new SummaryDTO();
				dto.setSummaryTitle(rs.getString(2));
				dto.setSummaryContent(rs.getString(3));
				dto.setSummaryDate(rs.getString(4));
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