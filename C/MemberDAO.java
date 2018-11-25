package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class MemberDAO implements crud {
	java.sql.Connection con;
	private ResultSet rs;

	private boolean connect() {
		boolean result = false;
		// 1. throws exception 과 try catch로 묶어서 System.out.println("클래스 로드 실패 : " +
		// e.getMessage());를 catch뒤에 출력하는것중에 어떤 방법이 더 좋은지
		// 2. if (connect())로 묶어서 연결확인후 실행 or connect문을 매 메소드마다 넣어주는것
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			result = true;
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	public void create(MemberDTO dto) throws Exception {
		if (connect()) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			PreparedStatement ps = con.prepareStatement("insert into member(id,pw,name,tel) value(?,?,?,?)");

			ps.setString(1, dto.getId());
			ps.setInt(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getTel());
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

	public Boolean select(String id, String pw) throws Exception {
		Boolean login = false;
		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			java.sql.Statement ps = con.createStatement(); //방식 :  preparestatement에 넣는것과, 이방법

			String sql = "select * from member where id='" + id + "'";
			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet
			
			while (rs.next() == true) { // 다음값의
				if (rs.getString(2).equals(pw)) { // pw와 같은지 비교
					login = true; // 같으면 로그인 성공
				} else { // 아이디는같고 pw가 다른경우
					login = false;
				}
			}
		}
		return login;
	}

	public void update(MemberDTO dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "1234");

//		PreparedStatement ps = con.prepareStatement("update member(id,pw,name,tel) value(?,?,?,?)");
	}

	public void delete(MemberDTO dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "1234");

//		PreparedStatement ps = con.prepareStatement("delete from member where ");
	}

}