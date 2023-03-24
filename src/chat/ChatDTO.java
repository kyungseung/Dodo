package chat;

public class ChatDTO {
	//qa_chat
	private int chatNum;
	private String user_ID;  
	private String userName;
	private String chatContent;
	private String chatDate;
	private int roomNum;
	
	public int getChatNum() {
		return chatNum;
	}
	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatDate() {
		return chatDate;
	}
	public void setChatDate(String chatDate) {
		this.chatDate = chatDate;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	public ChatDTO() {
		
	}
	
	public ChatDTO(int chatNum, String user_ID, String userName, String chatContent, String chatDate, int roomNum) {
		super();
		this.chatNum = chatNum;
		this.user_ID = user_ID;
		this.userName = userName;
		this.chatContent = chatContent;
		this.chatDate = chatDate;
		this.roomNum = roomNum;

	}
	
	
}
