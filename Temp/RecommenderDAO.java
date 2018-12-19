package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RecommenderDAO {
	/* 변수 */
	Connection con;
	private ResultSet rs;
	private boolean result;

	/* Connect - 연결 */
	public boolean connect() {
		/* 반환값 */
		result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=utf8", "root", "1234");
			result = true;
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	/* Read - Select */
	public ArrayList<RecommenderDTO> selectColumn(String column, Object object) throws Exception {
		ArrayList<RecommenderDTO> arr = new ArrayList<RecommenderDTO>();
		RecommenderDTO dto;
		if (connect()) {
			PreparedStatement ps = con.prepareStatement("select * from recommender where " + column + " = ?");
			ps.setString(1, String.valueOf(object));
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

	/* Update - update */
	public void Update(double score, int count, String menu) throws Exception {
		if (connect()) {
			PreparedStatement ps = con.prepareStatement("update recommender set score = ?, count = ? where fMenu = ?");
			ps.setDouble(1, score);
			ps.setInt(2, count + 1);
			ps.setString(3, menu);
			ps.executeUpdate();
			ps.close();
		}

	}

}
