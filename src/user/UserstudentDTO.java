package user;

public class UserstudentDTO {

	private String userS_ID;
	private String userPW;
	private String userName;
	private int userGrade;
	
	public String getUserS_ID() {
		return userS_ID;
	}
	public void setUserS_ID(String userS_ID) {
		this.userS_ID = userS_ID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}
	
	//초기화
	public UserstudentDTO() {
		
	}
	
	public UserstudentDTO(String userS_ID, String userPW, String userName, int userGrade) {
		super();
		this.userS_ID = userS_ID;
		this.userPW = userPW;
		this.userName = userName;
		this.userGrade = userGrade;
	}
	

	
}
