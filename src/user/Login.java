package user;

import java.util.ArrayList;
import java.util.Scanner;

import summary.SummaryDAO;
import summary.SummaryDTO;
import notice.NoticeDTO;

//class UserMain
public class Login
{

	//회원가입
	public void userInsert(){ 
		
		String user_ID, userPW, userName=null;
		int role,userGrade,SubjectCode=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("아이디 : ");
		user_ID = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		userPW = sc.nextLine();
		
		System.out.print("이 름 : ");
		userName = sc.nextLine();
		
		System.out.print("역할을 선택해주세요. \n");
		System.out.print("1. 선생님 \n");
		System.out.print("2. 학생\n");
		role = sc.nextInt();
		
		// 역할 선택 시 보기 번호를 벗어난 경우
		if(role != 1 && role != 2) {
			System.out.print("역할을 다시 선택해 주세요. \n");
			return;
		}
		
		System.out.print("소속(담당)학년 : ");
		userGrade = sc.nextInt();
		// 1~3학년을 벗어난 경우
		if(userGrade != 1 && userGrade != 2 && userGrade != 3) {
			System.out.print("학년을 다시 입력해주세요. \n");
			return;
		}
				
		//DB에 회원가입
		UserDAO userDAO = new UserDAO();
		
		int result = 0; //한명의 회원가입 결과 담음
		if( role == 2){ //학생을 택하면 user_student 테이블
			result = userDAO.studentjoin(new UserstudentDTO(user_ID, userPW, userName, userGrade));
		}else if (role == 1) {
			//담당 과목 선택
			System.out.print("담당 과목을 선택해주세요. \n");
			System.out.print("1. 국어\n");
			System.out.print("2. 수학\n");
			System.out.print("3. 영어\n");
			SubjectCode = sc.nextInt();

			//데이터 입력
			result = new UserDAO().teacherjoin(new UserteacherDTO(user_ID, userPW, userName, userGrade, SubjectCode));
			
		}else{
			System.out.print("역할을 다시 선택해 주세요. \n");
			userInsert(); //+추가함
			//return;
		}

		if(result == 1){
			System.out.print("회원가입이 완료되었습니다.\n\n");

			 main.DoDoMain.main(null);//+추가함
			//return;
			//메인으로 이동
		}
		else if(result == -1) {
			System.out.print("이미 존재하는 아이디입니다.\n 다시 회원가입 해주세요.\n\n");
			userInsert();
		}
		//
		 
	}//userInsert()
	
	
	
	// 로그인
	public void userLogin(){ // static
		Scanner sc = new Scanner(System.in);

		
		System.out.print("아이디 : ");
		String user_ID = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPW = sc.nextLine();
		

		System.out.print("역할을 선택해주세요. \n");
		System.out.print("1. 선생님 \n");
		System.out.print("2. 학생\n");
		int role = sc.nextInt();
		
		
		int subjectCode=0;//교사 과목 코드
		int userGrade=0; //회원 학년
		String userName ="";
		
		UserDAO userDAO = new UserDAO();
		int result =0;
		
		if( role == 1){ //user_teacher 테이블로 로그인
			result = userDAO.teacherLogin(user_ID, userPW);
			
			//교사 학년, 과목 코드 가져옴.
			 ArrayList<UserteacherDTO> List = new ArrayList<UserteacherDTO>();
		       List = new UserDAO().teacherCode(user_ID);

		       if(List != null) {
		    	   for(int i = 0; i < List.size(); i++) {
		    		   UserteacherDTO dto = List.get(i);
		    		   subjectCode = dto.getSubjectCode();
						userGrade = dto.getUserGrade();
					//	userName = dto.getUserName(); //userName추가 0603
		    	   }
		       }
			//
			
		}else if (role == 2) { //user_student 테이블로 로그인
			result =  userDAO.studentLogin(user_ID, userPW);
			userGrade = userDAO.StudentCode(user_ID); //학년 가져옴
			
		}else{
			System.out.print("역할을 다시 선택해 주세요. \n");
		}
		
		
		
		if(result == 1) {
			System.out.println("로그인 되었습니다.\n\n"); 
			 
			main.DoDoMenu menu = new main.DoDoMenu();
			
			switch(role) {
			case 1:
				//main.DoDoMenu.TeacherMenu(user_ID);//교사 메뉴 실행
				menu.TeacherMenu(user_ID, subjectCode, userGrade);
		        break;
		    case 2:
		    	//main.DoDoMenu.StudentMenu(user_ID);//학생 메뉴 실행
		    	menu.StudentMenu(user_ID, userGrade);
		        break;
		    default:
		    	System.out.println("숫자를 확인해 주세요.\n");
		    	main.DoDoMain.main(null); //+추가함
			}
		}else if(result == 2) {
			System.out.println("비밀번호를 확인해주세요.\n\n"); 
			main.DoDoMain.main(null); //+추가함
		}else if(result == -1) {
			System.out.println("아이디를 확인해주세요.\n\n"); 
			main.DoDoMain.main(null); //+추가함
		}
		
	
	}
}


