package Login;

import java.util.Scanner;

class UserMain
{
	private UserInfo[] users;
	private int idx;

	public UserMain(int num){
		users = new UserInfo[num];
		int idx = 0;
	}
	
	//회원가입
	public void userInsert(){ 
		String id, pw, name;
		int role;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("아이디 : ");
		id = sc.nextLine();
		
		// 아이디가 중복일 때
		if (!sameID(id)) {
			System.out.println("이미 사용중인 아이디 입니다.");
			return;
		}
		
		System.out.print("비밀번호 : ");
		pw = sc.nextLine();
		
		System.out.print("이 름 : ");
		name = sc.nextLine();
		
		System.out.print("역할을 선택해주세요. \n");
		System.out.print("1. 선생님 \n");
		System.out.print("2. 학생\n");
		role = sc.nextInt();
		
		// 역할 선택 시 보기 번호를 벗어난 경우
		if(role != 1 && role != 2) {
			System.out.print("역할을 다시 선택해 주세요. \n");
			return;
		}

		users[idx] = new UserInfo(id, pw, name, role);
		idx++;
		System.out.println("회원가입이 완료되었습니다.");
	}
	
	//아이디 중복 체크
	private boolean sameID(String id) { 
		if (idx == 0)
			return true;
		
		for (int i = 0 ; i < idx ; i ++) {
			if (users[i].getId().equals(id)) {
				return false;
			}
		}
		return true;
	}
	
	// 로그인
	public void userLogin(){ 
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		//회원가입 정보가 없을 때
		if (idx == 0)
			System.out.println("회원가입 먼저 진행해주세요."); 
		
		for (int i = 0 ; i < idx ; i++) {
			String role_nm = null;
			
			//로그인 성공 시
			if (users[i].getId().equals(id) && users[i].getPw().equals(pw)) {
				
				//역할 출력을 위한 이름 지정
				if(users[i].getRole() == 1) {
					role_nm = "선생님";
				} else {
					role_nm = "학생";
				}
				
				System.out.println(users[i].getName() + " 님 환영합니다. (" + role_nm +")");
				return;
			}
			
			//아이디 오류 시
			else if (!users[i].getId().equals(id)) {
				System.out.println("존재하지 않는 아이디입니다.");
				return;
			}
			
			//암호 오류 시
			else if (users[i].getId().equals(id) && !users[i].getPw().equals(pw)) {
				System.out.println("암호가 잘못되었습니다.");
				return;
			}
		}
	}
}

public class Login {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserMain login = new UserMain(100);
		boolean run = true;
		int num = 0;
		
		while (run) {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 프로그램 종료");
			System.out.println("---------------"); 
			num = sc.nextInt();
			
			if(num==1) { 
				login.userInsert();
				continue;
			} else if(num==2) {
				login.userLogin();
				continue;
			} else if(num==3) { 
				run=false;
				System.out.println("프로그램이 종료되었습니다.");
			}

		}
	}
}
