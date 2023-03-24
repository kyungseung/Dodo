package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import summary.SummaryDTO;
import util.DatabaseUtil;

public class ChatDAO{
	
	public int chatinsert(ChatDTO chat) {
		String SQL = "INSERT INTO qa_chat VALUES(NULL,?,?,?,?,?)";  //roomNum는 임시
																
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			conn = DatabaseUtil.getConnection(); 
			pstmt = conn.prepareStatement(SQL); 

			pstmt.setString(1, chat.getUser_ID());
			pstmt.setString(2, chat.getUserName());
			pstmt.setString(3, chat.getChatContent());
			pstmt.setString(4, chat.getChatDate());
			pstmt.setInt(5, chat.getRoomNum());
			
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

	
	//채팅 데이터 읽기 //채팅방 입장 시//당일 채팅 내용 모두 가져오기 **DB qa_chat에 roomName 추가**
		public ArrayList<ChatDTO> chatList(String ymdChatDate, String roomName){
			String SQL = "SELECT * FROM qa_chat Q left JOIN chat_room C "
					+ "ON Q.roomNum = C.roomNum "
					+ "WHERE Q.chatDate LIKE ? AND C.roomName = ?  "
					+ "ORDER BY Q.chatNum ";
		            
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<ChatDTO> List = null;
			
			try{
				conn = DatabaseUtil.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, ymdChatDate+'%');
				pstmt.setString(2, roomName);
				
				rs = pstmt.executeQuery();
				
				List = new ArrayList<ChatDTO>();
				
				while(rs.next()) {
					ChatDTO dto = new ChatDTO();
					
					dto.setUser_ID(rs.getString(2));
					dto.setUserName(rs.getString(3));
					dto.setChatContent(rs.getString(4));
					dto.setChatDate(rs.getString(5));
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
		
		
		//채팅방 코드 가져오기
		public int roomNum(String groupName) {
			String SQL = "SELECT roomNum FROM chat_room WHERE roomName = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int roomNum = 0;
			
			try {
				conn = DatabaseUtil.getConnection(); 
				pstmt = conn.prepareStatement(SQL); 
				pstmt.setString(1, groupName); 
				rs = pstmt.executeQuery(); //executeQuery() 데이터 검색
				
				if(rs.next()) {
					roomNum = rs.getInt(1);
				}
				return roomNum;
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
		
		
}
