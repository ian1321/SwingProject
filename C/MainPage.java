package swing;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import com.mysql.jdbc.PreparedStatement;

import java.awt.Component;
import java.awt.TextField;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage extends JFrame implements ActionListener {
	// 변수설정
	static JTextField textField; // ID입력 텍스트필드
	static JTextField textField_1; // PW입력 텍스트필드
	static JButton button = new JButton("회원가입"); // 회원가입버튼
	static JButton button2 = new JButton("로그인"); // 로그인버튼

	// 메인페이지
	public MainPage() {
		// 기본프레임설정
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\yogiyo (1).jpg"));
		setTitle("메인페이지");
		setSize(1148, 652);
		getContentPane().setLayout(null);

		// ID 입력 텍스트필드
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(null); // 클릭하면 공백처리
			}
		});
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setText("  아이디");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(586, 412, 348, 38);
		textField.setColumns(10);
		getContentPane().add(textField);

		// PW 입력 텍스트필드
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setText(null); // 클릭하면 필드 공백 처리
			}
		});
		textField_1.setText("  패스워드");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setColumns(10);
		textField_1.setBounds(586, 469, 348, 38);
		getContentPane().add(textField_1);

		// 회원가입버튼
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PreSignUp(); // 재로그인 폼
				dispose(); // 해당창 닫기
			}
		});
		button.setBounds(795, 541, 97, 23);
		button.setBackground(Color.YELLOW);
		getContentPane().add(button);
		button.addActionListener(this);

		// 로그인버튼
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// ID,Pw가 공백일경우에
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField.getText().equals("  아이디") || textField_1.equals("  패스워드")) {
						JOptionPane.showMessageDialog(null, "항목을 모두 입력해주세요");
						// 공백은 아니지만 ID와 PW값이 틀릴때
					} else if (new MemberDAO().select(textField.getText(), textField_1.getText()) == false) {
						JOptionPane.showMessageDialog(null, "아이디,패스워드를 확인하세요", "로그인 오류", 0);
						// 정상적으로 로그인될때
					} else if (new MemberDAO().select(textField.getText(), textField_1.getText()) == true) {
						MemberDTO.SessionId = textField.getText(); // SessionId를 넘겨줌
						new MyPage(); // myPage 호출
						dispose(); // 해당 창 닫기
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button2.setBackground(Color.RED);
		button2.setBounds(613, 541, 97, 23);
		getContentPane().add(button2);

		// 배경화면 레이블
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\yogiyo (1).jpg"));
		lblNewLabel.setBounds(0, 0, 1132, 613);
		getContentPane().add(lblNewLabel);

		// setvisible
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}