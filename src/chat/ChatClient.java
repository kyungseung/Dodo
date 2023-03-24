package chat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class ChatClient {
   
    public static void main(String[] args, String userID, String userName, String subject, int userGrade ) { 
    	String groupName = "";
    	String Name = userName;
    	InputStream is;
    	BufferedInputStream bis;
    	DataInputStream dis;
    	
    	OutputStream os;
    	BufferedOutputStream bos;
    	DataOutputStream dos;
    	
        try{
            String ServerIP = "localhost";
            Socket socket = new Socket(ServerIP, 20000); //소켓 객체 생성        
            System.out.println("#서버와 연결이 되었습니다.");
            //사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.
            is =socket.getInputStream();
            bis=new BufferedInputStream(is);
            dis = new DataInputStream(bis);
            
            os =socket.getOutputStream();
            bos = new BufferedOutputStream(os);
            dos = new DataOutputStream(bos);
           

            dos.writeUTF(userID);//아이디 넘김
            dos.writeUTF(Name); //서버에 name 넘김 
            
            String groupStr = dis.readUTF();  
            
            StringTokenizer st = new StringTokenizer(groupStr,"@");
            
            groupName = userGrade+"학년 "+subject;
            System.out.println("# '/exit'입력 시 채팅방에서 퇴장됩니다. ");
            dos.writeUTF(groupName);
           
            dos.flush();
           
           
            Thread sender = new Sender(socket, Name);            
            //서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
            Thread receiver = new Receiver(socket);        
            System.out.println("#"+groupName+"질문방에 입장하였습니다.");
            

            //당일 채팅 모두 가져오기
             //현재 날짜 가져오기
    			SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
    			Date now = new Date();
    			
    			//금일날짜
    			String todayDate = today.format(now);
             
             //당일 대화 list 가져오기
    			ArrayList<ChatDTO> List = new ArrayList<ChatDTO>();
    			
    			List = new ChatDAO().chatList(todayDate, groupName);
    			
    			if (List != null) {

    				for (int i = 0; i < List.size(); i++) {
    					ChatDTO dto = List.get(i);
    					System.out.print(dto.getUserName()+"> ");//name>
    					System.out.print(dto.getChatContent()+"\t\t\t\t"); //msg
    					System.out.println(dto.getChatDate());
    				}
    				if(List.size()==0) {System.out.println("오늘 채팅 내용이 없습니다.");}
    			} 
    			
            sender.start(); //스레드 시동
            receiver.start(); //스레드 시동
           
        }catch(Exception e){
            System.out.println("예외[MultiClient class]:"+e);
        }
       
    }//main()
}
 
 
 
//서버로부터 메시지를 읽는 클래스
class Receiver extends Thread{
   
    Socket socket;
	InputStream is;
	BufferedInputStream bis;
	DataInputStream dis;
	
	
    //Socket을 매개변수로 받는 생성자.
    public Receiver(Socket socket){
        this.socket = socket;
       
        try{
            dis = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
            
        }catch(Exception e){
            System.out.println("예외:"+e);
        }
    }
   
    @Override
    public void run(){ //run()메소드 재정의
			
        while(dis!=null){
            try{              	
            	String name=dis.readUTF(); 
                String msg=dis.readUTF(); 
                String chatDate=dis.readUTF(); 
              
                //메세지 출력 부분
                System.out.print(name+"> "); 
                System.out.print(msg);
                System.out.print("\t\t\t\t");
                System.out.println(chatDate);
               
            }catch(SocketException e){             
                 System.out.println("예외:"+e);
                 System.out.println("##접속중인 서버와 연결이 끊어졌습니다.");
                return;
                 
            } catch(Exception e){              
                System.out.println("예외:"+e);
             
            }
        }//while
    }//run()
}//class Receiver
 
 
//서버로 메시지를 전송하는 클래스
class Sender extends Thread {
    Socket socket;
    String name;
    
	InputStream is;
	BufferedInputStream bis;
	DataInputStream dis;
	
	OutputStream os;
	BufferedOutputStream bos;
	DataOutputStream dos;
	
   
    //생성자 ( 매개변수로 소켓과 사용자 이름 받음. )
    public Sender(Socket socket, String name){ //소켓과 사용자 이름을 받는다.
        this.socket = socket;      
        try{
        	os = this.socket.getOutputStream();
            bos = new BufferedOutputStream(os);
            dos = new DataOutputStream(bos);         
            this.name = name; //받아온 사용자이름을 전역변수에 저장, 다른 메서드인 run()에서 사용.
        }catch(Exception e){
            System.out.println("예외:"+e);
        }
    }
   
    @Override
    public void run(){ //run()메소드 재정의
       
        Scanner s = new Scanner(System.in);
 
        while(dos!=null){ 
            try {     
            	
            	 String msg = s.nextLine();
            	 
            	 //timestamp //0605추가 //변수명 수정 fTimestamp -> chatDate
            	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            	 SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            	 String chatDate = sdf.format(timestamp); // format을 사용해 출력
            	 
            	 
            	if(msg.equalsIgnoreCase("/exit")){ //equalsIgnoreCase 대소문자 구별하지 않음.
                    System.out.println("##클라이언트를 종료합니다.\n");
                    //  System.exit(0);
                    main.DoDoMain.main(null); //교사, 학생 구분이 힘들어 채팅 종료 시 메뉴로 돌아가게 하는게 어려움
                  break;
              }
            	//메세지, 시간 서버로 보냄
                dos.writeUTF(msg); 
                dos.writeUTF(chatDate);
                dos.flush();

                
            }catch(SocketException e){             
                 System.out.println("예외:"+e);
                 System.out.println("##접속중인 서버와 연결이 끊어졌습니다.");
                return;              
           } catch (IOException e) {
                System.out.println("예외:"+e);
           }
        }//while     
    }//run()
}//class Sender
 