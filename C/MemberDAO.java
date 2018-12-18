package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MemberDAO implements Crud {
	/* 변수설정 */
	Connection con;
	private ResultSet rs;
	private boolean result;

	/* 연결 */
	private boolean connect() {
		/* 결과값 */
		result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=utf8", "root", "1234");
			/* 연결되면 true값 반환 */
			result = true;
		} catch (Exception e) {
			/* 실패시 오류메세지 */
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	/* Create - insert */
	public void create(MemberDTO dto) throws Exception {
		/* 연결되면 */
		if (connect()) {
			PreparedStatement ps = con.prepareStatement("insert into member value(?,?,?,?)");
			ps.setString(1, dto.getId());
			ps.setInt(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getTel());
			ps.executeUpdate();
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결 실패");
		}
	}

	/* Read - select */
	public Boolean select(String id, String pw) throws Exception {
		/* 반환값 */
		Boolean login = false;
		if (connect()) {
			String sql = "select * from member where id='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql); // 결과값을 rs에 대입

			while (rs.next()) {
				/* rs column2와 입력값비교 */
				if (rs.getString(2).equals(pw)) {
					login = true;
				} else {
					login = false;
				}
			}
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결 실패");
		}
		return login;
	}

	/* Update - update */
	public void update(MemberDTO dto) throws Exception {
		if (connect()) {
			PreparedStatement ps = con
					.prepareStatement("update member set pw = ?, name = ?, tel = ? where id='" + dto.getId() + "'");
			ps.setInt(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getTel());
			ps.executeUpdate();
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결 실패");
		}
	}

	/* Remove - delete */
	public void delete(String id) throws Exception {
		if (connect()) {
			PreparedStatement ps = con.prepareStatement("delete from member where id = '" + id + "'");
			ps.executeUpdate();
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결 실패");
		}
	}

	// compare DAO -- ID 중복확인
	public boolean compare(String id) throws Exception {
		/*반환값*/
		boolean comp = false;
		// 연결되면
		if (connect()) {

			// select sql실행문
			String sql = "select * from member where id='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql); // 결과값을 rs에 대입
			while (rs.next()) {
				comp = true; // rs가 한번이라도 돌아가면 comp = true
			}
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결 실패");
		}
		return comp;
	}

	public MemberDTO selectMine(String id) throws Exception {
		MemberDTO dto = new MemberDTO();
		if (connect()) {
			String sql = "select * from member where id ='" + id + "'";

			PreparedStatement ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet
			if (rs.next() || rs != null) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getInt("pw"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
			}
			ps.close();
			con.close();

		} else {
			JOptionPane.showMessageDialog(null, "연결 실패");
		}
		return dto;
	}
}
