package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RecommenderDAO {
	// 변수설정
	Connection con; // 커넥션변수
	private ResultSet rs; // 결과값 넣을 변수
	private boolean result; /* 연결결과값 변수 */
	
	

	public boolean connect() {
		result = false; // 연결 결과 변수
		try {
			// 연결이 된다면 result = true
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=utf8", "root", "1234");
			result = true;
		} catch (Exception e) {
			// 연결실패
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<RecommenderDTO> selectColumn(String column, Object object) throws Exception {
		ArrayList<RecommenderDTO> arr = new ArrayList<RecommenderDTO>();
		String object1 = (String) object;
		RecommenderDTO dto;
		if (connect()) {
			
			PreparedStatement ps = con.prepareStatement("select * from recommender where " + column + " = ?");
			ps.setString(1, object1);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new RecommenderDTO();
				dto.setFMenu(rs.getString("fMenu"));
				dto.setCook(rs.getString("cook"));
				dto.setSauce(rs.getBoolean("sauce"));
				dto.setTaste(rs.getString("taste"));
				dto.setScore(rs.getDouble("score"));
				dto.setCount(rs.getInt("count"));
				arr.add(dto);
			}
		}

		return arr;

	}

	public void Update(double score, int count, String menu) throws Exception {
		RecommenderDTO dto;
		if (connect()) {
			PreparedStatement ps = con.prepareStatement("update recommender set score = ?, count = ? where fMenu = ?");
			ps.setDouble(1, score);
			ps.setInt(2, count + 1);
			ps.setString(3, menu);
			ps.executeUpdate();
			ps.close();
		}

	}
	/*public ArrayList<RecommenderDTO> join(String column, Object object) throws Exception {
		ArrayList<RecommenderDTO> arr = new ArrayList<RecommenderDTO>();
		String object1 = (String) object;
		count = 0;
		if (connect()) {
			java.sql.PreparedStatement ps = con.prepareStatement("select " + column
					+ ".foodList from foodList, recommender where menu.foodList = ?");
			
			ps.setString(1, object1);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new RecommenderDTO();
				dto.setFmenu(rs.getString("fMenu"));
				dto.setCook(rs.getString("cook"));
				dto.setSauce(rs.getBoolean("sauce"));
				dto.setTaste(rs.getString("tase"));
				dto.setScore(rs.getDouble("score"));
				dto.setCount(rs.getInt("count"));
				arr.add(dto);
				count++;
			}
		}

		return arr;

	}
*/
}
