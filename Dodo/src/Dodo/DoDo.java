package Dodo;
import java.util.Scanner;

public class DoDo {
   public static void main(String[] args) {
      Scanner sc = new Scanner (System.in); 
      UserMain login = new UserMain(100);
      SummaryMain summary = new SummaryMain(100);
      
      String choice, subject = null;
      int role = 0;
      
      System.out.println("자기주도학습 도우미, 도도입니다.\n"
                  + "1.회원가입  2.로그인");
      System.out.println("----------------------");
      choice = sc.next();
      
	  if (  
         choice.equals("1.회원가입")	| 
         choice.equals("1")			|
         choice.equals("회원가입")
      ) {  
         System.out.println("1.회원가입을 선택하셨습니다.");
         
         //회원가입
         login.userInsert();
         
      } else if (   
         choice.equals("2.로그인")	| 
         choice.equals("2")			|
         choice.equals("로그인")
         ) { 
         System.out.println("2.로그인을 선택하셨습니다.");
         
         //로그인
         login.userLogin();
         

         //선생님, 학생 구분해서 다음 프로그램 실행
         // role = 1 : 선생님이 로그인 했을 시
         if (role == 1) {
            System.out.println("선생님이 로그인하셨습니다.\n");
            System.out.println("다음 중 하나를 선택해주세요\n");
            System.out.println("1.공지전달  2.1:1질문");
            choice = sc.next();
            
            if (   
               choice.equals("1.공지전달")		| 
               choice.equals("1")			|
               choice.equals("공지전달")   
            ) {

            	//공지전달
            	
            } 
            else if (
                choice.equals("2.1:1질문")	| 
                choice.equals("2")			|
                choice.equals("1:1질문")
            
            ) {
             
            	//1:1질문
            	
            }
            else {
            	System.out.println("잘못 입력 하셨습니다. 다시 입력해주세요.");
            }
         }
         //roll == 2 : 학생이 로그인 했을 시,
         else {  
             System.out.println("학생이 로그인하셨습니다.\n");
             System.out.println("다음 중 하나를 선택해주세요.");
             System.out.println("1.국어  2.수학  3.영어");
             subject = sc.next();
             
             System.out.println("다음 중 하나를 선택해주세요\n");
             System.out.println("1.공지확인  2.1:1질문  3.수업요약정리");
             choice = sc.next();
             
             if (   
                choice.equals("1.공지확인")	| 
                choice.equals("1")			|
                choice.equals("공지확인")   
             ) {

             	//공지전달
             	
             } 
             else if (
                 choice.equals("2.1:1질문")	| 
                 choice.equals("2")			|
                 choice.equals("1:1질문")
             
             ) {
              
             	//1:1질문
             	
             }
             else if (
                 choice.equals("3.수업요약정리")	| 
                 choice.equals("3")				|
                 choice.equals("수업요약정리")
             
             ) {
             	//수업요약정리
            	 summary.SummaryWrite();
             	
             }
             else {
             	System.out.println("잘못 입력 하셨습니다. 다시 입력해주세요.");
             }
         }  
      }
      }
   }