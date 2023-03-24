package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	
	public static Connection getConnection(){
		try{ 
			String dbURL = "jdbc:mysql://localhost:3306/dodo?characterEncoding=UTF-8&serverTimezone=UTC"; //포트 3306, DB이름 dodo
			String dbID = "root";
			String dbPassword = "111111";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
