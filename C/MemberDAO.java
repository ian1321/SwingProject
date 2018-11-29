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

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

public class MemberDAO implements crud {
	// 변수설정
	Connection con; // 커넥션변수
	private ResultSet rs; // 결과값 넣을 변수

	// 연결 메소드
	private boolean connect() {
		boolean result = false; // 연결 결과 변수
		try {
			// 연결이 된다면 result = true
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			result = true;
		} catch (Exception e) {
			// 연결실패
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	// create DAO -- 입력값 dto값 받아서 DB에 sql statement실행
	public void create(MemberDTO dto) throws Exception {
		// 연결이 된다면
		if (connect()) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			// insert sql실행문
			PreparedStatement ps = con.prepareStatement("insert into member(id,pw,name,tel) value(?,?,?,?)");
			// setString으로 sql문에 설정
			ps.setString(1, dto.getId());
			ps.setInt(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getTel());
			// 실행
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

	// select DAO -- id, pw값 받아서 DB와 비교
	public Boolean select(String id, String pw) throws Exception {

		// 로그인이 되는지 검사하는 변수 boolean login
		Boolean login = false;
		// 연결이 된다면
		if (connect()) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			java.sql.Statement ps = con.createStatement();

			// select sql실행문
			String sql = "select * from member where id='" + id + "'";
			rs = ps.executeQuery(sql); // 결과값을 rs에 대입

			// rs값이 있는만큼, 비밀번호와 같은지 비교
			while (rs.next() == true) {
				if (rs.getString(2).equals(pw)) {
					login = true;
				} else {
					login = false;
				}
			}
			ps.close();
			con.close();
		}
		// true or false값을 반환
		return login;

	}

	// update DAO -- dto값 받아서 해당 ID에 해당하는 업데이트
	public void update(MemberDTO dto) throws Exception {
		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			// update sql값 설정 preparestatement
			PreparedStatement ps = con
					.prepareStatement("update member set pw = ?, name = ?, tel = ? where id='" + dto.getId() + "'");

			// dto받은값 setString

			ps.setInt(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getTel());
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

	// delete DAO -- 로그인한 id값 받아서 탈퇴결정
	public void delete(String id) throws Exception {
		// 연결되면
		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			// delete sql값 설정 preparestatement
			PreparedStatement ps = con.prepareStatement("delete from member where id = '" + id + "'");
			ps.executeUpdate();

			ps.close();
			con.close();
		}
	}

	// compare DAO -- ID 중복확인
	public boolean compare(String id) throws Exception {
		// 검색되는 값 있으면 true로 돌려줄 comp변수
		boolean comp = false;

		// 연결되면
		if (connect()) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			java.sql.Statement ps = con.createStatement();

			// select sql실행문
			String sql = "select * from member where id='" + id + "'";
			rs = ps.executeQuery(sql); // 결과값을 rs에 대입
			while (rs.next()) {
				comp = true; // rs가 한번이라도 돌아가면 comp = true
			}
			ps.close();
			con.close();
		}
		return comp;
	}
}
