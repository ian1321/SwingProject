package swing2;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodListDAO {

	ResultSet rs;

	public FoodListDTO foodie(String foodName) throws Exception { //
		// 1. connect Driver //menu , rest
		FoodListDTO dto = new FoodListDTO();
		Class.forName("com.mysql.jdbc.Driver"); //
		// 2. DB연결
		String url = "jdbc:mysql://localhost:3306/delivery";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);
		// 3.
		String sql = "select * from foodlist where menu = ?"; // ~~가 ~~일때
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, foodName);
		rs = ps.executeQuery(); // select 할때 사용 결과값을 받아올때 쿼리.

		while (rs.next()) { // rs.next = true 일때
			System.out.print("지역:");
			dto.setLocation(rs.getString(1));
			dto.setSort(rs.getString(2));
			dto.setRest(rs.getString(3));
			dto.setMenu(rs.getString(4));
			dto.setPrice(rs.getInt(5));
		}
		return dto;
	}

	public ArrayList list1() throws Exception { //
		// 1. connect Driver //menu , rest
		Class.forName("com.mysql.jdbc.Driver"); //
		// 2. DB연결
		String url = "jdbc:mysql://localhost:3306/delivery";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);
		String sql = "select * from foodlist where sort = 'chicken'";
		PreparedStatement ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		FoodListDTO dto1 = null;
		ArrayList chick = null; 
		chick = new ArrayList();
		while (rs.next()) {
			dto1 = new FoodListDTO();
			
//			dto1.setSort(rs.getString(2));
			dto1.setRest(rs.getString(3));
			
			
			chick.add(dto1);
//			dto1.setMenu(rs.getString(4));
//			dto1.setPrice(rs.getInt(5));
		}
//		return dto1;
		return chick;
	}
}
