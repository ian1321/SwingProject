package money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BankDAO {
	
	/*DB값 넣어주기*/
	public void create(BankDTO dto) throws Exception {
		/*연결*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
		String sql = "insert into bank values(?,?,?,?)";
		/*sql문실행*/
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getName());
		ps.setInt(3, dto.getAge());
		ps.setString(4, dto.getTel());
		ps.executeUpdate();
		ps.close();
	}
	
	/*DB값 변경하기*/
	public void update(String id, String tel) throws Exception {
		/*연결*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
		String sql = "update bank set tel = ? where id = ?";
		/*sql문실행*/
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		ps.setString(2, id);
		ps.executeUpdate();
		ps.close();
	}
	
	/*DB값 삭제하기*/
	public void delete(String id) throws Exception {
		/*연결*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
		String sql = "delete from bank where id = ?";
		/*sql문실행*/
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		ps.close();
	}
	
	/*DB값 선택된것만 가져오기*/
	public BankDTO select(String id) throws Exception {
		/*연결*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
		String sql = "select * from bank where id = ?";
		/*sql문실행*/
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		BankDTO dto = new BankDTO();
		
		if( rs.next() && rs!= null) {
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setAge(Integer.parseInt(rs.getString("age")));
			dto.setTel(rs.getString("tel"));
		}
		
		return dto;
	}
	
	/*DB값 전체 가져오기*/
	public ArrayList<BankDTO> selectAll() throws Exception {
		/*연결*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234");
		String sql = "select * from bank";
		/*sql문실행*/
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		
		ArrayList<BankDTO> arr = new ArrayList<BankDTO>();
		BankDTO dto;
		
		while (rs.next() && rs!=null) {
			dto = new BankDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setAge(Integer.parseInt(rs.getString("age")));
			dto.setTel(rs.getString("tel"));
			arr.add(dto);
		}
		
		return arr;
	}
}
