package summary;

public class SummaryDTO {
	private int summaryNum;
	private String summaryDate;
	private String summaryTitle;
	private String summaryContent;
	private String userS_ID;
	private int subjectCode;
;
	
	
	public int getSummaryNum() {
		return summaryNum;
	}
	
	public void setSummaryNum(int summaryNum) {
		this.summaryNum = summaryNum;
	}
	
	public String getSummaryDate() {
		return summaryDate;
	}
	
	public void setSummaryDate(String summaryDate) {
		this.summaryDate = summaryDate;
	}
	
	public String getSummaryTitle() {
		return summaryTitle;
	}
	
	public void setSummaryTitle(String summaryTitle) {
		this.summaryTitle = summaryTitle;
	}
	
	public String getSummaryContent() {
		return summaryContent;
	}
	
	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}
	
	public String getUserS_ID() {
		return userS_ID;
	}

	public void setUserS_ID(String userS_ID) {
		this.userS_ID = userS_ID;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public SummaryDTO() {
		
	}

	public SummaryDTO(int summaryNum, String summaryTitle, String summaryContent, String summaryDate, String userS_ID, int subjectCode) {
		super();
		this.summaryNum = summaryNum;
		this.summaryDate = summaryDate;
		this.summaryTitle = summaryTitle;
		this.summaryContent = summaryContent;
		this.userS_ID = userS_ID;
		this.subjectCode = subjectCode;

	}
	
	
}
