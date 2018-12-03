package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class FoodListDAO {
	Connection con;
	private ResultSet rs; // 결과값 넣을 변수
	private boolean result; /* 연결결과값 변수 */

	public boolean connect() {
		result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList selectColumn(String column, String data) throws Exception {
		ArrayList arr = new ArrayList();
		if (connect()) {
			java.sql.PreparedStatement ps = con.prepareStatement("select * from foodlist where " +column + " = ?");
			ps.setString(1, data);
			rs = ps.executeQuery();
			while (rs.next()) {
				arr.add(rs.getString("sort"));
				arr.add(rs.getString("rest"));
				arr.add(rs.getString("menu"));
				arr.add(rs.getString("price"));
			}
		}
		return arr;

	}

}
