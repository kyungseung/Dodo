package notice;

import java.util.ArrayList;
import java.util.Scanner;

public class Notice_S {

	//등록된 공지사항 내용 보기
	public void NoticeList(String user_ID, int subjectCode, int userGrade){

		 ArrayList<NoticeDTO_join> List = new ArrayList<NoticeDTO_join>(); // ArrayList<NoticeDTO> List = new ArrayList<NoticeDTO>();
	       List =new NoticeDAO().NoticeList2(subjectCode, userGrade);//, userGrade
	     
	       if(List != null) { 
	    	   for(int i = 0; i < List.size(); i++) {
	    		   NoticeDTO_join dto = List.get(i); // NoticeDTO dto = List.get(i);
	    		   //교사 name이 null이면 id 출력
	    		   String Tname =  dto.getUserName();
	    		   if(dto.getUserName() == null)
	    			  Tname = dto.getUserT_ID();
	    		   
	    		   	System.out.println(Tname+" 선생님");
					System.out.println("내용: "+dto.getNoticeContent());
					System.out.println("날짜: "+dto.getNoticeDate());
					System.out.println();
	    	   }
	    	   if(List.size()==0) {System.out.println("작성된 공지사항이 없습니다.");}
	       }
	}


	public static void main(String[] args, String user_ID, int subjectCode, int userGrade, String subject) {
		Scanner sc = new Scanner(System.in);
		Notice_S notice = new Notice_S();
		boolean run = true;
		int num = 0;

		while (run) {
			System.out.println("-----공지확인-----");
			System.out.println("\n["+subject+"]");
			System.out.println("1. 공지사항 보기");
			System.out.println("2. 프로그램 종료하기");
			System.out.println("---------------"); 

			num = sc.nextInt();

			if(num == 1) { 
				notice.NoticeList(user_ID, subjectCode, userGrade);
				continue;
			} else if(num == 2) {
				run=false;
				System.out.println("프로그램이 종료되었습니다.");
				main.DoDoMenu menu = new main.DoDoMenu();
				menu.StudentMenu(user_ID, userGrade); //+추가함
				continue;
			}
		}
	}
}
