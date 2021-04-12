package Dodo;

//한 명의 회원정보를 저정하기 위한 클래스
public class UserInfo {
	private String id, pw, name;
	private int role;
	
	public UserInfo(String id, String pw, String name, int role){
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.role = role;
	}
	
	public String getId(){ return id; }
	public String getPw(){ return pw; }
	public String getName(){ return name; }
	public int getRole(){ return role; }
}
