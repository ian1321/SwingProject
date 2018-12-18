package swing2;
import java.sql.*;
import java.util.ArrayList;

public class FoodListDAO1 {
	
	public ArrayList<FoodListDTO> connect(String restName) throws Exception {		// 버튼을 받아야하는가 ? //메소드 1번으로 연결메소드.
//		
		//1	커넥터
		Class.forName("com.mysql.jdbc.Driver");
		//2	db연결
		String url = "jdbc:mysql://localhost:3306/delivery";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);
		//3
		String sql = "select * from foodlist where rest = ?";	
		PreparedStatement ps = con.prepareStatement(sql);	//문장
		ps.setString(1, restName);
		ResultSet rs = ps.executeQuery();
		ArrayList<FoodListDTO> arr = new ArrayList<>();
		FoodListDTO dto = null;
		while(rs.next()) {
		dto = new FoodListDTO();
		dto.setSort(rs.getString(2));
		dto.setRest(rs.getString(3));
		dto.setMenu(rs.getString(4));
		dto.setPrice(rs.getInt(5));
		arr.add(dto);
		}
		return arr;
	}
	
	public ArrayList<FoodListDTO> foodName(String foodName) throws Exception {
		
		//1	커넥터
		Class.forName("com.mysql.jdbc.Driver");
		//2	db연결
		String url = "jdbc:mysql://localhost:3306/delivery";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);
		//3
		String sql = "select * from foodlist where menu = ?";	
		PreparedStatement ps = con.prepareStatement(sql);	//문장
		ps.setString(1, foodName);
		ResultSet rs = ps.executeQuery();
		ArrayList<FoodListDTO> arr = new ArrayList<>();
		FoodListDTO dto = null;
		while(rs.next()) {
			dto = new FoodListDTO();
			dto.setSort(rs.getString(2));
			dto.setRest(rs.getString(3));
			dto.setMenu(rs.getString(4));
			dto.setPrice(rs.getInt(5));
			arr.add(dto);
		}
		return arr;
	}
	
	

}
