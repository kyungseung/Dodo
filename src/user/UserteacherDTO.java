package user;

public class UserteacherDTO {

	private String userT_ID;
	private String userPW;
	private String userName;
	private int userGrade;
	private int subjectCode;
	
	public String getUserT_ID() {
		return userT_ID;
	}
	public void setUserS_ID(String userT_ID) {
		this.userT_ID = userT_ID;
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
	
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	//초기화
	public UserteacherDTO() {
		
	}
	
	public UserteacherDTO(String userT_ID, String userPW, String userName, int userGrade, int subjectCode) {
		super();
		this.userT_ID = userT_ID;
		this.userPW = userPW;
		this.userName = userName;
		this.userGrade = userGrade;
		this.subjectCode = subjectCode;
	}


	
}
