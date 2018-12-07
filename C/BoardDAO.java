package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class BoardDAO {
	// 변수설정
	Connection con; // connection변수
	private ResultSet rs; // preparestatement값 받을 resultset변수
	boolean result; // 연결 결과 변수
	
	// 연결 메소드
	private boolean connect() {
		result = false;
		try {
			// 연결이 된다면 result = true
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=utf8", "root", "1234");
			//con =DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery?useUnicode=true&characterEncoding=UTF-8", "root", "1234");
			result = true;
		} catch (Exception e) {
			// 연결실패
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	// create DAO -- dto값 받아서 board table에 값 대입
	public void create(BoardDTO dto) throws SQLException {
		// 연결이 된다면
		if (connect()) {

			// sql실행문 -- 게시판의 제목, 사용중 id, 내용 보내기
			PreparedStatement ps = con.prepareStatement("insert into board value(null,?,?,?,?)");

			ps.setString(1, dto.getTitle()); // 제목설정
			ps.setString(2, MemberDTO.SessionId); // 사용중인 아이디 설정
			ps.setString(3, dto.getContent()); // 내용설정
			ps.setString(4, dto.getFmenu());
			ps.executeUpdate();
			ps.close();
			con.close();
		} 
		else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
	}

	public ArrayList select() throws Exception {
		ArrayList arr = new ArrayList();
		BoardDTO dto = new BoardDTO();
		if (connect()) {
			
			String sql = "select * from board";
			Statement ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet

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

		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return arr;

	}

	// 글목록 가져오기
	public int countRow() throws Exception {
		int count = 0;
		if (connect()) {

			String sql = "select * from board";
			PreparedStatement ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet

			while (rs.next()) {
				count++;

			}
			ps.close();
			con.close();

		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return count;
	}

	// 글 수정하기 위해 글 제목검색
	public int countMyRow(Object number) throws Exception {
		int count = 0;
		if (connect()) {

			String sql = "select * from board where number =" + number;
			
			PreparedStatement ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet

			while (rs.next()) {
				count++;
			}
			ps.close();
			con.close();

		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return count;
	}

	// update DAO -- id값 받아서 내가 작성한 글들을 반환
	public ArrayList<BoardDTO> selectMine(String column, Object data) throws Exception {
		// ArrayList값으로 반환하기위해 arr설정
		ArrayList<BoardDTO> arr = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		// 연결이 된다면
		if (connect()) {
			// sql문설정 select - 입력받은 id값
			String sql = "select * from board where '" + column + "' = '" + data + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			
			// rs가 있는만큼 반복
			while (rs.next()) {
				// arr배열에 순서대로 number, title, id, content추가
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
		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return arr;

	}

	// updateCount DAO -- 내가 작성한 글 수 확인
	public int countMyRow(String id) throws Exception {
		// 계산할 변수생성
		int countUp = 0;
		// 연결되면
		if (connect()) {
			String sql = "select * from board where id ='" + id + "'"; // 해당 ID값으로 select검색
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			// resultset값이 있으면 count++
			while (rs.next()) {
				countUp++;
			}
			// preparestatement와 connection close
			ps.close();
			con.close();
		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
		return countUp;
	}

	//클릭한 글 번호로 글 삭제하기
	public void delete(Object number) throws Exception {
		// 연결이 되면
		if (connect()) {
			String sql = "delete from board where number=" + number; // 해당 number값으로 select검색
			PreparedStatement ps = con.prepareStatement(sql);

			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

	//글 수정하기 위해 글 번호로 검색해서 제목이랑 내용 가져오기
	public ArrayList recall(Object number) throws SQLException {
		// 반환할 ArrayList생성
		ArrayList arr = new ArrayList();
		// 연결이 된다면
		if (connect()) {
			// 연결 sql설정
			String sql = "select * from board where number=" + number;// 해당 number값으로 select검색
			PreparedStatement ps = con.prepareStatement(sql);
			// Resultset에 ps결과값 설정
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				//ArrayList에 title, content, number순서대로 입력
				arr.add(rs.getString("title"));
				arr.add(rs.getString("content"));
				arr.add(rs.getString("number"));
				arr.add(rs.getString("fMenu"));
			}
			ps.close();
		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}

		return arr;
	}
	
	//글 수정
	public void update(BoardDTO dto) throws Exception {
		//연결이되면
		if (connect()) {
			String sql = "update board set title=?, content=? where number=?";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps에 해당값 setString
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			
			ps.setInt(3, dto.getNumber());
			//실행
			ps.executeUpdate();
			ps.close();
			con.close();
		}else {
			JOptionPane.showMessageDialog(null, "연결실패");
		}
	}

	
}
