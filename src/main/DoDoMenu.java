package main;

import java.util.Scanner;


public class DoDoMenu {

	
	public void TeacherMenu(String user_ID, int subjectCode, int userGrade) {
        Scanner sc = new Scanner (System.in); 
        
        String subject = subjectName(subjectCode); //과목이름 설정
 		
		System.out.println("---선생님 메뉴 입니다---\n");
		System.out.println("다음 중 하나를 선택해주세요\n");
		System.out.println("1.공지전달  2."+subject+"질문방");
		String choice = sc.next();
		
       switch (choice) {
        case "1":
            //공지전달 
        	notice.Notice_T.main(null, user_ID, subjectCode, userGrade);
            break;
        case "2":
           //1:1질문 //과목 질문방
        //	chat.Client.main(null, user_ID, subjectCode, userGrade, subjectCode, userGrade);
        	user.UserDAO userDAO = new user.UserDAO();
        	String Name = userDAO.TeacherName(user_ID)+" 선생님";
        	// userName+" 선생님";
        	chat.ChatClient.main(null, user_ID, Name, subject, userGrade); //userName
        //	chattt.MultiClient.main(null, Name, subject, userGrade);
            break;
        default:
           System.out.println("선생님메뉴를 다시 확인해 주세요.\n");
           TeacherMenu(user_ID, subjectCode, userGrade); //메뉴에 없는 번호 선택 시 다시 선생님메뉴 실행
       }
    }
  public void StudentMenu(String user_ID, int userGrade) {
	  
       Scanner sc = new Scanner (System.in); 
       
       System.out.println("---학생 메뉴 입니다---\n"); 
        System.out.println("다음 중 하나를 선택해주세요.");
        System.out.println("1.국어  2.수학  3.영어");
        int subjectCode = sc.nextInt(); //nextInt(): 입력받은 값을 int 타입으로 반환
        
        String subject = subjectName(subjectCode); //과목이름 설정
        
        System.out.println("다음 중 하나를 선택해주세요\n");
        System.out.println("1.공지확인  2."+subject+"질문방  3.수업요약정리");
        String choice = sc.next();
        
        
      switch (choice) {
       case "1":
           //공지확인 
    	   notice.Notice_S.main(null, user_ID, subjectCode, userGrade, subject);
           break;
       case "2":
    	   user.UserDAO userDAO = new user.UserDAO();
       		String Name = userDAO.StudentName(user_ID)+" 학생";
          //1:1질문 //과목 질문방
       	//	MultiCopy2.MultiClient.main(null, Name, subject, userGrade);
       		chat.ChatClient.main(null, user_ID, Name, subject, userGrade); //ㅇㅈ: userID 추가
           break;
       case "3":
          //수업요약정리
    	   summary.Summary.main(null, user_ID, subjectCode, subject, userGrade); //+userGrade추가
           break;
       default:
          System.out.println("학생메뉴를 다시 확인해 주세요.\n");
          StudentMenu(user_ID, userGrade); //메뉴에 없는 번호 선택 시 과목선택부터 다시 실행
      }
   }
  
	
	public String subjectName(int subjectCode) {
		String subject = "";
		switch (subjectCode) {
		  case 1:
			  subject = "국어";
			  break;
		  case 2:
			  subject = "수학";
			  break;
		  case 3:
			  subject = "영어";
			  break;
		  default:
			  subject = "과목코드오류";
			}
		return subject;
	}
	
}