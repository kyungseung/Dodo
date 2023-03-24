package notice;

public class NoticeDTO_join {
	//N.NoticeContent, N.NoticeDate, N.userT_ID, T.userName

	private String NoticeContent ;
	private String NoticeDate;
	private String userT_ID ;
	private String userName ;
	
	
	
	public String getNoticeDate() {
		return NoticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		NoticeDate = noticeDate;
	}
	public String getNoticeContent() {
		return NoticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		NoticeContent = noticeContent;
	}
	public String getUserT_ID() {
		return userT_ID;
	}
	public void setUserT_ID(String userT_ID) {
		this.userT_ID = userT_ID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	//초기화
		public NoticeDTO_join() {
			
		}

		
		public NoticeDTO_join(String noticeContent, String noticeDate, String userT_ID, String userName) {
			super();
			this.NoticeContent = noticeContent;
			this.NoticeDate = noticeDate;
			this.userT_ID = userT_ID;
			this.userName = userName;
		}
	
}
