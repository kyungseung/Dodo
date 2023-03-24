package notice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Notice_T {

	//공지할 내용 입력
	public void NoticeWrite(String user_ID, int subjectCode, int userGrade){
		Scanner sc = new Scanner(System.in);
		
		//현재 날짜 가져오기
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		
		//금일날짜
		String NoticeDate = today.format(now);
		
		System.out.print("공지할 내용을 입력하세요 : \n");
		String NoticeContent = sc.nextLine();
		
		String userT_ID = user_ID;
		
		// 0, NoticeDate, NoticeContent, userT_ID 순서
		int result = new NoticeDAO().noticeinsert(new NoticeDTO(0, NoticeDate, NoticeContent, userT_ID, subjectCode, userGrade));
		
		//작성자 내용,작성한 날짜 출력
		System.out.print("공지 내용 : " + NoticeContent + "\n");
		System.out.print("작성 날짜 : " + NoticeDate + "\n");
		//System.out.printf("%s %s %s %d %d",NoticeDate, NoticeContent, userT_ID, subjectCode, userGrade);
		
		if(result == 1) {
		System.out.println("공지사항 등록이 완료되었습니다.");
		} else {
			System.out.println("공지사항 등록이 실패되었습니다.");
		}
	}
	
	//등록된 공지사항 내용 보기
	public void NoticeList(String user_ID){
		 ArrayList<NoticeDTO> List = new ArrayList<NoticeDTO>();
	       List =new NoticeDAO().NoticeList(user_ID);

	       if(List != null) { 
	    	   for(int i = 0; i < List.size(); i++) {
	    		   NoticeDTO dto = List.get(i);
	    		  	//System.out.println("공지번호: "+dto.getNoticeNum());
					System.out.println("내용: "+dto.getNoticeContent());
					System.out.println("날짜: "+dto.getNoticeDate());
					
	    	   }
	    	   if(List.size()==0) {System.out.println("작성하신 공지사항이 없습니다.\n");}
	       }//else { System.out.println("작성하신 공지가 없습니다."); }

	}

	public static void main(String[] args, String user_ID, int subjectCode, int userGrade) {
		Scanner sc = new Scanner(System.in);
		Notice_T notice = new Notice_T();
		boolean run = true;
		int num = 0;

		while (run) {
			System.out.println("-----공지전달-----");
			System.out.println("1. 공지사항 내용 입력");
			System.out.println("2. 작성한 공지사항 보기");
			System.out.println("3. 프로그램 종료하기");
			System.out.println("---------------"); 
			num = sc.nextInt();

			if(num == 1) { 
				notice.NoticeWrite(user_ID, subjectCode, userGrade);
				continue;
			} else if(num == 2) {
				notice.NoticeList(user_ID);
				continue;
			} else if (num == 3) {
				run=false;
				System.out.println("프로그램이 종료되었습니다.");
				main.DoDoMenu menu = new main.DoDoMenu();
				menu.TeacherMenu(user_ID, subjectCode, userGrade);//+추가함
			}
		}
	}
}
