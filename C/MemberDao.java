package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class MemberDao {
	java.sql.Connection con;
	
	public Connection connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			String url = "jdbc:mysql://localhost:3306/member";
			String user = "root";
			String password = "1234";
			
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("연결되었습니다");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
		return  con;
		
		
}
}
