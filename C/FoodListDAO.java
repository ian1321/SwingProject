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

	public boolean connect() {
		result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=utf8", "root", "1234");
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList selectColumn(String column, Object object) throws Exception {
		ArrayList arr = new ArrayList();
		String object1 = (String)object;
		if (connect()) {
			java.sql.PreparedStatement ps = con.prepareStatement("select * from foodlist where " +column + " = ?");
			ps.setString(1, object1);
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
	
	//comboAdd는 foodListDAO에 들어가지 않는다. dao는 연결만
	public String[] comboAdd(ArrayList arr,int column) {
		String[] restC = { "", "", "" };
		  int count = 0;
		  HashSet distinctData = new HashSet();
			for (int i = 0; i < arr.size() / 4; i++) {
			if (!restC[count].equals((arr.get(i * 4 + column)))) {
				distinctData.add(arr.get(i * 4 + column));
				if (count < 2)
					count++;
			}
		}
		arr = new ArrayList(distinctData);
		for (int j = 0; j < arr.size(); j++) {
			restC[j] = (String) arr.get(j);
		}
		return restC;
		}

}
