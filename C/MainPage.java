package swing;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MainPage extends JFrame implements ActionListener, Mysql {
	static JTextField textField;
	static JTextField textField_1;
	static JButton button = new JButton("회원가입");
	static JButton button2 = new JButton("로그인");

	public MainPage() {
		setTitle("메인페이지");
		setSize(1148, 652);
		getContentPane().setLayout(null);

		textField_1 = new JTextField();
		textField_1.setText("  \uD328\uC2A4\uC6CC\uB4DC");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setColumns(10);
		textField_1.setBounds(586, 469, 348, 38);
		getContentPane().add(textField_1);
		button.setBackground(Color.YELLOW);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreSignUp sign = new PreSignUp();
			}
		});
		button.setBounds(795, 541, 97, 23);
		getContentPane().add(button);
		button.addActionListener(this);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						MemberDao cM = new MemberDao();
						cM.connection();
						
						int count = 0;
						
						ArrayList arr = new ArrayList();
						String idTf = textField.getText();
						String pwTf = textField_1.getText();

						String sql = "select id, pw from contents";
						
						
							java.sql.PreparedStatement ps;
							try {
								ps = cM.connection().prepareStatement(sql);
							
							System.out.println("연결");
							ResultSet rs = ps.executeQuery();

							while (rs.next()) {
								count++;
								arr.add(count);
								arr.add(rs.getString("id"));
								arr.add(rs.getString("pw"));
							}
						
						for (int i = 0; i < Array.getLength(arr) / 3; i++) {
							if (idTf.equals(arr.get(i))) {
								if (pwTf.equals(arr.get(i))) {
									JOptionPane.showMessageDialog(null, "로그인되었습니다", "title", 0);
									PreSignUp p = new PreSignUp();
								}
							} else {
								System.out.println("땡");
							}
						}
						
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					
			
		});
		button2.setBackground(Color.RED);

		button2.setBounds(613, 541, 97, 23);
		getContentPane().add(button2);

		textField = new JTextField();
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setText("  \uC544\uC774\uB514");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(586, 412, 348, 38);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\086.png"));
		lblNewLabel.setBounds(0, 0, 1132, 613);
		getContentPane().add(lblNewLabel);
		setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { lblNewLabel, getContentPane(), textField }));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == button2) {
			try {
			Class.forName("com.mysql.jdbc.driver");
			
			String url = "jdbc:mysql://localhost:3306/member";
			String user = "root";
			String password = "1234";
			
			Connection con;
			
				con = DriverManager.getConnection(url, user, password);
			 
			System.out.println("연결되었습니다");
			
			int count = 0;
			
			ArrayList arr = new ArrayList();
			String idTf = textField.getText();
			String pwTf = textField_1.getText();

			String sql = "select id, pw from contents";
			System.out.println("b2");
			
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				System.out.println("연결");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					count++;
					arr.add(count);
					arr.add(rs.getString("id"));
					arr.add(rs.getString("pw"));
				}
			
			for (int i = 0; i < Array.getLength(arr) / 3; i++) {
				if (idTf.equals(arr.get(i))) {
					if (pwTf.equals(arr.get(i))) {
						JOptionPane.showMessageDialog(null, "로그인되었습니다", "title", 0);
						PreSignUp p = new PreSignUp();
					}
				} else {
					System.out.println("땡");
				}
			}
			
			
			}
			catch (SQLException | ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			

		}

	}// actionPerformed끝
*/}
