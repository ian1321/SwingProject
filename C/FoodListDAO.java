package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import com.mysql.jdbc.PreparedStatement;

public class FoodListDAO {
	Connection con;
	private ResultSet rs; // 결과값 넣을 변수
	private boolean result; /* 연결결과값 변수 */
	FoodListDTO dto;
	public boolean connect() {
		result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=utf8", "root", "1234");
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<FoodListDTO> selectColumn(String column, Object object) throws Exception {
		ArrayList<FoodListDTO> arr = new ArrayList<FoodListDTO>();
		String object1 = (String) object;
		if (connect()) {
			java.sql.PreparedStatement ps = con.prepareStatement("select * from foodlist where " + column + " = ?");
			ps.setString(1, object1);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new FoodListDTO();
				dto.setSort(rs.getString("sort"));
				dto.setRest(rs.getString("rest"));
				dto.setMenu(rs.getString("menu"));
				dto.setPrice(rs.getInt("price"));
				arr.add(dto);
			}
		}
		return arr;

	}

	

}
