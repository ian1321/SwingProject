package swing;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PreSignUp extends JFrame {

	// 변수설정
	private JTextField textField; // ID입력 텍스트필드
	private JTextField textField_1; // PW입력 텍스트필드

	// preSignUp
	public PreSignUp() {

		// 프레임설정
		setTitle("로그인");
		setSize(505, 647);
		getContentPane().setLayout(null);

		// ID입력 텍스트필드
		textField = new JTextField();
		textField.setForeground(Color.LIGHT_GRAY);
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(null); // 클릭하면 공백
			}
		});
		textField.setBounds(71, 255, 323, 45);
		textField.setText("아이디");
		getContentPane().add(textField);
		textField.setColumns(10);

		// PW입력 텍스트필드
		textField_1 = new JTextField();
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setText(null); // 클릭하면 공백
			}
		});
		textField_1.setBounds(71, 312, 323, 45);
		textField_1.setText("비밀번호");
		textField_1.setColumns(10);
		getContentPane().add(textField_1);

		// 로고레이블
		JLabel lblNewLabel = new JLabel("로고");
		lblNewLabel.setBounds(181, 162, 125, 60);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);

		// 로그인 레이블
		JLabel lblNewLabel_1 = new JLabel("로그인");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					// ID,Pw가 공백일경우에
					if (textField.getText().equals("") || textField_1.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "다 입력해주세요", "경고", 0);
						// 공백은 아니지만 ID와 PW값이 틀릴때
					} else if (new MemberDAO().select(textField.getText(), textField_1.getText()) == false) {
						JOptionPane.showMessageDialog(null, "로그인 오류입니다. 아이디,패스워드를 확인하세요", "경고", 0);
						// 정상적으로 로그인될때
					} else if (new MemberDAO().select(textField.getText(), textField_1.getText()) == true) {
						MemberDTO.SessionId = textField.getText(); // SessionId를 넘겨줌
						MyPage my = new MyPage(); // myPage 호출
						dispose(); // 해당 창 닫기
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_1.setBackground(new Color(204, 0, 0));
		lblNewLabel_1.setBounds(71, 389, 323, 45);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\088.png"));
		getContentPane().add(lblNewLabel_1);

		// 회원가입 레이블 -- 회원가입폼 넘어가는 action
		JLabel lblNewLabel_2 = new JLabel("회원가입");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SignUp signUp = new SignUp(); // 회원가입창 호출
				dispose(); // 해당창 닫기
			}
		});
		lblNewLabel_2.setBounds(273, 479, 121, 32);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\089.png"));
		getContentPane().add(lblNewLabel_2);

		// 요기요가 처음이신가요 레이블
		JLabel lblNewLabel_3 = new JLabel("\uC694\uAE30\uC694\uAC00 \uCC98\uC74C\uC774\uC2E0\uAC00\uC694?");
		lblNewLabel_3.setBounds(71, 489, 166, 17);
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_3);

		// 맨 위 디자인창 (요기요배너)
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setBounds(0, 0, 489, 74);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\001.png")); // 요기요배너
		
		// 요기요 메인 돌아가기 레이블
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				MainPage main = new MainPage(); //처음페이지
				dispose(); //닫
			}
		});
		label_1.setBounds(12, 13, 106, 45);
		getContentPane().add(label_1);
		label_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\090.png"));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);

		setVisible(true);
	}
}