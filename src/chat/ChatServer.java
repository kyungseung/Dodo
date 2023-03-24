package chat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import chat2.ChatDAO;
import chat2.ChatDTO;
 

public class ChatServer {
    HashMap<String,HashMap<String,MultiServerRec>> globalMap; //지역별 해쉬맵을 관리하는 해시맵
    ServerSocket serverSocket = null;
    Socket socket = null;
    static int connUserCount = 0; //서버에 접속된 유저 카운트
   
    //생성자
    public ChatServer(){
       globalMap = new HashMap<String,HashMap<String, MultiServerRec>>();
       
        //clientMap = new HashMap<String,DataOutputStream>(); //클라이언트의 출력스트림을 저장할 해쉬맵 생성.
        Collections.synchronizedMap(globalMap); //해쉬맵 동기화 설정.
       
        HashMap<String,MultiServerRec> group01 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group02 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group03 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group04 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group05 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group06 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group07 = new HashMap<String,MultiServerRec>();
        HashMap<String,MultiServerRec> group08 = new HashMap<String,MultiServerRec>();       
        HashMap<String,MultiServerRec> group09 = new HashMap<String,MultiServerRec>();
       
       
        globalMap.put("1학년 국어",group01);
        globalMap.put("1학년 수학",group02);
        globalMap.put("1학년 영어",group03);
        globalMap.put("2학년 국어",group04);
        globalMap.put("2학년 수학",group05);
        globalMap.put("2학년 영어",group06);
        globalMap.put("3학년 국어",group07);
        globalMap.put("3학년 수학",group08);
        globalMap.put("3학년 영어",group09);
       
       
    }//생성자----
   
    public void init(){
        try{
            serverSocket = new ServerSocket(20000); //20000포트로 서버소켓 객체생성.
            System.out.println("##서버가 시작되었습니다.");
           
            while(true){ //서버가 실행되는 동안 클라이언트들의 접속을 기다림.
                socket = serverSocket.accept(); //클라이언트의 접속을 기다리다가 접속이 되면 Socket객체를 생성.
                System.out.println(socket.getInetAddress()+":"+socket.getPort()); //클라이언트 정보 (ip, 포트) 출력
               
                Thread msr = new MultiServerRec(socket); //쓰레드 생성.
                msr.start(); //쓰레드 시동.
            }      
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   

    //해당 클라이언트가 속해있는 그룹에대해서만 메시지 전달.
    public void sendGroupMsg(String groupName, String userID, String name, String msg, String chatDate){  
    	
        //출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다. //해당 그룹(key)에 들어간 msg(value)
        HashMap<String, MultiServerRec> gMap = globalMap.get(groupName);     
        Iterator<String> group_it = globalMap.get(groupName).keySet().iterator();        
        while(group_it.hasNext()){
            try{
                //채팅그룹명
                MultiServerRec st = gMap.get(group_it.next());
                
                //이름, 메세지,날짜 전달 
                st.dos.writeUTF(name); 
                st.dos.writeUTF(msg);
                st.dos.writeUTF(chatDate); 
                st.dos.flush();           
                
            }catch(Exception e){
                System.out.println("예외:"+e);
            }
        }  
    }//sendGroupMsg()
   
   
   
    //각방의 접속자수와 서버에 접속되 유저를 반환하는 메소드
    public String getEachMapSize(){
       
         //출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
        Iterator global_it = globalMap.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        //int sum = 0;
        while(global_it.hasNext()){ ////hasNext() : 다음 요소가 있으면 가져옴
            try{
                String key = (String) global_it.next();
                HashMap<String, MultiServerRec> it_hash = globalMap.get(key);
                int size = it_hash.size();
                sb.append(key+":"+size+"@");
               
            }catch(Exception e){
                System.out.println("예외:"+e);
            }
        }
        sb.append("현재 서버에 접속된 유저수 :"+ ChatServer.connUserCount);
        return sb.toString();
    }//sendGroupMsg()

    //main메서드
    public static void main(String[] args) {
        ChatServer ms = new ChatServer(); //서버객체 생성.
        ms.init();//실행.
    }
   
   
    // 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드
    class MultiServerRec extends Thread { //MultiServerRec 제네릭 값
       
        Socket socket;
    	InputStream is;
    	BufferedInputStream bis;
    	DataInputStream dis;
    	
    	OutputStream os;
    	BufferedOutputStream bos;
    	DataOutputStream dos;
    	
        String userID="";//id 저장 
        String name=""; //이름 저장
        String groupName="";  //그룹 저장
        String msg = "";
        String chatDate = "";
        int roomNum;
       
        
        public MultiServerRec(Socket socket){
            this.socket = socket;
            try{
	        	is = socket.getInputStream();
	            bis = new BufferedInputStream(is);
	            dis = new DataInputStream(bis);
                  
	        	os =socket.getOutputStream();
	            bos = new BufferedOutputStream(os);
	            dos = new DataOutputStream(bos);
	            
            }catch(Exception e){
                System.out.println("예외:"+e);
            }
        }

       
        @Override
        public void run(){ //쓰레드를 사용하기 위해서 run()메서드 재정의
            HashMap<String, MultiServerRec> clientMap=null;
            try{
                ChatServer.connUserCount++; //접속자수 증가.
                
                os = socket.getOutputStream();
                bos = new BufferedOutputStream(os);
                dos = new DataOutputStream(bos);
                
                //접속된 클라이언트에게 그룹목록 제공
                dos.writeUTF(getEachMapSize());
                dos.flush();
                
                //클라이언트에서 정보 받아옴
                userID = dis.readUTF(); //사용자 아이디
                name = dis.readUTF(); //사용자 이름 
                groupName = dis.readUTF(); //채팅방 이름
               
                sendGroupMsg(groupName,"server","server", "#"+name + "님이 입장하셨습니다.",chatDate); 
    			
                clientMap= globalMap.get(groupName); //현재그룹의 해쉬맵을 따로 저장.
                clientMap.put(name, this); //현재 MultiServerRec인스턴스를 클라이언트맵에 저장.
                System.out.println(getEachMapSize());
               
               
                while(dis!=null){ //입력스트림이 null이 아니면 반복.
                    msg = dis.readUTF(); //입력스트림을 통해 읽어온 msg.
                    chatDate = dis.readUTF(); //읽어온 datetime
                    
                    sendGroupMsg(groupName, userID, name, msg, chatDate);
                    
                    //groupName으로 roomNum 지정
                    roomNum = new ChatDAO().roomNum(groupName);
                    if(roomNum == -1)
                    	System.out.println("DB insert error(roomNum)");
                    
                    int result =  new ChatDAO().chatinsert(new ChatDTO(0,userID, name, msg, chatDate, roomNum)); 
                    if(result == -1)
                    	System.out.println("DB insert error");
                    
                }//while()
            }catch(Exception e){
                System.out.println(e + "----> ");
            }finally{
                //예외가 발생할때 퇴장. 해쉬맵에서 해당 데이터 제거.
                //보통 종료하거나 나가면 java.net.SocketException: 예외발생
                if(clientMap!=null){
                    clientMap.remove(name);
                }

                sendGroupMsg(groupName,"server","server","## "+ name + "님이 퇴장하셨습니다.", chatDate);            
                System.out.println("##현재 서버에 접속된 유저는 "+(--ChatServer.connUserCount)+"명 입니다.");
            }
        }//run()

        
    }//class MultiServerRec
}