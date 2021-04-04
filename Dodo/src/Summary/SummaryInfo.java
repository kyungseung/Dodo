package Summary;

//하나의 요약내용을 저장하기위한 클래스
public class SummaryInfo {
	private String summaryTitle, summaryContent, summaryDate;
	
	public SummaryInfo(String summaryTitle, String summaryContent, String summaryDate){
		this.summaryTitle = summaryTitle;
		this.summaryContent = summaryContent;
		this.summaryDate = summaryDate;
	}
	
	public String getSummaryTitle(){ return summaryTitle; }
	public String getSummaryContent(){ return summaryContent; }
	public String getSummaryDate(){ return summaryDate; }
}
