package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BoardDAO {
	/* 변수 */
	Connection con;
	private ResultSet rs;
	boolean result;

	/* Connect - 연결 */
	private boolean connect() {
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

	/* Create - insert */
	public void create(BoardDTO dto) throws SQLException {
		if (connect()) {
			PreparedStatement ps = con.prepareStatement("insert into board value(null,?,?,?,?)");

			ps.setString(1, dto.getTitle());
			ps.setString(2, MemberDTO.SessionId);
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getFmenu());
			ps.executeUpdate();
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
	}

	/* Read - select */
	public ArrayList<BoardDTO> select() throws Exception {
		/* 반환할 ArrayList생성 */
		ArrayList<BoardDTO> arr = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		if (connect()) {
			String sql = "select * from board";
			Statement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				dto = new BoardDTO();
				dto.setNumber(rs.getInt("number"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setFmenu(rs.getString("fMenu"));
				arr.add(dto);
			}
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return arr;
	}

	/* Read - select - number */
	public int countMyRow(Object number) throws Exception {
		int count = 0;
		if (connect()) {
			String sql = "select * from board where number =" + number;
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				count++;
			}
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return count;
	}

	/* Read - select - number */
	public BoardDTO recall(Object number) throws SQLException {
		BoardDTO dto = new BoardDTO();
		// 연결이 된다면
		if (connect()) {
			// 연결 sql설정
			String sql = "select * from board where number=" + number;// 해당 number값으로 select검색
			PreparedStatement ps = con.prepareStatement(sql);
			// Resultset에 ps결과값 설정
			rs = ps.executeQuery(sql);

			if (rs.next()) {
				// ArrayList에 title, content, number순서대로 입력
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setNumber(Integer.parseInt(rs.getString("number")));
				dto.setFmenu(rs.getString("fMenu"));
			}
			ps.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}

		return dto;
	}

	/* Update - update - id */
	public ArrayList<BoardDTO> selectMine(String column, Object data) throws Exception {
		/* 반환 ArrayList */
		ArrayList<BoardDTO> arr = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		if (connect()) {
			String sql = "select * from board where " + column + " = '" + data + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				dto = new BoardDTO();
				dto.setNumber(rs.getInt("number"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setFmenu(rs.getString("fMenu"));
				arr.add(dto);
			}
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return arr;

	}

	/* Update - update */
	public void update(BoardDTO dto) throws Exception {
		if (connect()) {
			String sql = "update board set title=?, content=? where number=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getNumber());
			ps.executeUpdate();
			ps.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
	}

	/* Delete - delete */
	public void delete(Object number) throws Exception {
		if (connect()) {
			String sql = "delete from board where number=" + number;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

}
