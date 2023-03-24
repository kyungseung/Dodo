package summary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Summary {
	//요약 내용 입력
		public void SummaryWrite(String user_ID, int subjectCode){
			Scanner sc = new Scanner(System.in);
			String summaryTitle, summaryContent;		
			
			String userS_ID = user_ID;
			
			//현재 날짜 가져오기
			SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date();
			
			//금일날짜
			String summaryDate = today.format(now);

			System.out.print("제목을 입력하세요 : ");
			summaryTitle = sc.nextLine();

			System.out.print("내용을 입력하세요 (최대 150자까지 입력 가능): \n");
			summaryContent = sc.nextLine();

			//입력한 글자 수가 150자 이상일때
			if (summaryContent.length() > 150) {
				System.out.print("문자 입력 수를 초과하였습니다. \n");
				System.out.print("내용을 다시 입력해주세요. \n");
				
				return;
			}
			
			// 0, summaryTitle, summaryContent, summaryDate, userS_ID, subjectCode 순서
			int result = new SummaryDAO().summaryinsert(new SummaryDTO(0, summaryTitle, summaryContent, summaryDate, userS_ID, subjectCode));
			
			//현재 날짜 출력
			System.out.print("작성 날짜 : " + summaryDate +"\n");

			if(result == 1) {
			System.out.println("입력이 완료되었습니다.");
			}else {
				System.out.println("입력 실패했습니다.");
			}
			
		}
		
		public void SummaryList(String user_ID, int subjectCode){
			
			ArrayList<SummaryDTO> List = new ArrayList<SummaryDTO>();
			List = new SummaryDAO().summaryList(user_ID, subjectCode);
			
			if (List != null) {
				for (int i = 0; i < List.size(); i++) {
					SummaryDTO dto = List.get(i);
					System.out.println("제목: " + dto.getSummaryTitle());
					System.out.println("내용: " + dto.getSummaryContent());
					System.out.println("날짜: " + dto.getSummaryDate());
					System.out.println();
				}
				if(List.size()==0) {System.out.println("작성하신 요약내용이 없습니다.");}
			} //else { System.out.println("작성하신 요약내용이 없습니다."); }
		}
		
		public static void main(String[] args, String user_ID, int subjectCode, String subject, int userGrade) {
			Scanner sc = new Scanner(System.in);
			
			Summary summary = new Summary();
			boolean run = true;
			int num = 0;
			main.DoDoMenu menu = new main.DoDoMenu();
			while (run) {
				System.out.println("---수업요약정리----");
				System.out.println("\n["+subject+"]");
				System.out.println("1. 요약 내용 입력");
				System.out.println("2. 요약 내용 보기");
				System.out.println("3. 프로그램 종료하기");
				System.out.println("---------------"); 
				num = sc.nextInt();
				
				
				if(num == 1) { 
					summary.SummaryWrite(user_ID, subjectCode);
					continue;
				} else if(num == 2) {
					summary.SummaryList(user_ID, subjectCode);
					continue;
				} else if (num == 3) {
					run=false;
					System.out.println("프로그램이 종료되었습니다.");
					menu.StudentMenu(user_ID, userGrade);
				}
			}
		}
		

}
