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
		// 1. throws exception �� try catch�� ��� System.out.println("Ŭ���� �ε� ���� : " +
		// e.getMessage());�� catch�ڿ� ����ϴ°��߿� � ����� �� ������
		// 2. if (connect())�� ��� ����Ȯ���� ���� or connect���� �� �޼ҵ帶�� �־��ִ°�
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			result = true;
		} catch (Exception e) {
			System.out.println("���� ���� : " + e.getMessage());
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

			java.sql.Statement ps = con.createStatement(); //��� :  preparestatement�� �ִ°Ͱ�, �̹��

			String sql = "select * from member where id='" + id + "'";
			rs = ps.executeQuery(sql); // �о���°Ŷ� �ٸ��� ���� //����Ÿ���� ResultSet
			
			while (rs.next() == true) { // ��������
				if (rs.getString(2).equals(pw)) { // pw�� ������ ��
					login = true; // ������ �α��� ����
				} else { // ���̵�°��� pw�� �ٸ����
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