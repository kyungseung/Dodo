package Dodo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class SummaryMain {
	private SummaryInfo[] summarys;
	private int summaryNum;

	public SummaryMain(int num){
		summarys = new SummaryInfo[num];
		int summaryNum = 0;
	}

	//요약 내용 입력
	public void SummaryWrite(){
		Scanner sc = new Scanner(System.in);
		String summaryTitle, summaryContent;

		//현재 날짜 가져오기
		SimpleDateFormat today = new SimpleDateFormat("yyyy년 MM월 dd일");
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

		//현재 날짜 출력
		System.out.print("작성 날짜 : " + summaryDate +"\n");

		summarys[summaryNum] = new SummaryInfo(summaryTitle, summaryContent, summaryDate);
		summaryNum++;
		System.out.println("입력이 완료되었습니다.");
	}
	
	//입력한 요약내용 보기
	public void SummaryList(){
		Scanner sc = new Scanner(System.in);
		for (int i = 0 ; i < summaryNum ; i ++) {
			System.out.println("제목 : " + summarys[i].getSummaryTitle());
			System.out.println("내용 : " + summarys[i].getSummaryContent());
			System.out.println("작성날짜 : " + summarys[i].getSummaryDate());
			System.out.println("-----------------------------------------");
		}
	}
}

public class Summary {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SummaryMain summary = new SummaryMain(100);
		boolean run = true;
		int num = 0;

		while (run) {
			System.out.println("1. 요약 내용 입력");
			System.out.println("2. 요약 내용 보기");
			System.out.println("3. 프로그램 종료하기");
			System.out.println("---------------"); 
			num = sc.nextInt();

			if(num == 1) { 
				summary.SummaryWrite();
				continue;
			} else if(num == 2) {
				summary.SummaryList();
				continue;
			} else if (num == 3) {
				run=false;
				System.out.println("프로그램이 종료되었습니다.");
			}
		}
	}
}
