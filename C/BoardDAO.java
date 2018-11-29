package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Spring;

public class BoardDAO {
	// 변수설정
	Connection con; // connection변수
	private ResultSet rs; // preparestatement값 받을 resultset변수

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

	//create DAO -- dto값 받아서 board table에 값 대입
	public void create(BoardDTO dto) throws SQLException {
		//연결이 된다면
		if (connect()) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			
			//sql실행문 -- 게시판의 제목, 사용중 id, 내용 보내기
			PreparedStatement ps = con.prepareStatement("insert into board(title,id,content) value(?,?,?)");

			ps.setString(1, dto.getTitle()); //제목설정
			ps.setString(2, MemberDTO.SessionId); //사용중인 아이디 설정
			ps.setString(3, dto.getContent()); //내용설정
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

	public ArrayList select() throws Exception {
		ArrayList arr = new ArrayList();

		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			String sql = "select * from board";

			java.sql.Statement ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet

			while (rs.next()) {
				arr.add(rs.getInt("number"));
				arr.add(rs.getString("title"));
				arr.add(rs.getString("id"));
				arr.add(rs.getString("content"));

			}
			ps.close();
			con.close();

		}
		return arr;

	}

	public int countRow() throws Exception {
		int count = 0;
		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");

			String sql = "select * from board";

			java.sql.Statement ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql); // 읽어오는거라 다르다 비교해 //리턴타입이 ResultSet

			while (rs.next()) {
				count++;

			}
			ps.close();
			con.close();

		}
		return count;
	}

	//update DAO -- id값 받아서 내가 작성한 글들을 반환
	public ArrayList update(String id) throws Exception {
		//ArrayList값으로 반환하기위해 arr설정
		ArrayList arr = new ArrayList();
		//연결이 된다면
		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			//sql문설정 select - 입력받은 id값
			String sql = "select * from board where id ='" + id + "'";
			java.sql.Statement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql); 
			
			//rs가 있는만큼 반복
			while (rs.next()) {
				//arr배열에 순서대로 number, title, id, content추가
				arr.add(rs.getInt("number")); 
				arr.add(rs.getString("title"));
				arr.add(rs.getString("id"));
				arr.add(rs.getString("content"));
			}
			ps.close();
			con.close();
		}
		return arr;

	}

	//updateCount DAO -- 내가 작성한 글 수 확인
	public int updateCount(String id) throws Exception {
		//계산할 변수생성
		int countUp = 0;
		//연결되면
		if (connect()) {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery", "root", "1234");
			String sql = "select * from board where id ='" + id + "'"; //해당 ID값으로 select검색
			java.sql.Statement ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			
			//resultset값이 있으면 count++
			while (rs.next()) {
				countUp++;
			}
			//preparestatement와 connection close
			ps.close();
			con.close();
		}
		return countUp;
	}
}
