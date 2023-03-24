package notice;


public class NoticeDTO {

	private int NoticeNum ;
	private String NoticeDate ;
	private String NoticeContent ;
	private String userT_ID ;
	private int subjectCode ;
	private int userGrade ;
		

	public int getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public int getNoticeNum() {
		return NoticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.NoticeNum = noticeNum;
	}

	public String getNoticeDate() {
		return NoticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.NoticeDate = noticeDate;
	}

	public String getNoticeContent() {
		return NoticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.NoticeContent = noticeContent;
	}

	public String getUserT_ID() {
		return userT_ID;
	}

	public void setUserT_ID(String userT_ID) {
		this.userT_ID = userT_ID;
	}
	
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	//초기화
	public NoticeDTO() {
		
	}
	
	public NoticeDTO(int noticeNum, String noticeDate, String noticeContent, String userT_ID, int subjectCode, int userGrade) {
		super();
		this.NoticeNum = noticeNum;
		this.NoticeDate = noticeDate;
		this.NoticeContent = noticeContent;
		this.userT_ID = userT_ID;
		this.subjectCode = subjectCode;
		this.userGrade = userGrade;
	}

	
}
