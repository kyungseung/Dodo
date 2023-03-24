package main;
import java.util.Scanner;

import user.Login;

public class DoDoMain {

  

public static void main(String[] args) {
	  Login login = new Login();	  
	  Scanner sc = new Scanner (System.in); 


   System.out.println("자기주도하습 도우미, 도도입니다.\n"
               + "1.회원가입  2.로그인");
   String choice = sc.next();
   
   
	switch (choice) {
    case "1":
    	 System.out.println("1.회원가입을 선택하셨습니다.");
         login.userInsert();
        break;
    case "2":
    	System.out.println("2.로그인을 선택하셨습니다.");
        login.userLogin();
        break;
    default:
    	System.out.println("숫자를 확인해 주세요.");
    	main.DoDoMain.main(null);
	}
	
	}//main()
}