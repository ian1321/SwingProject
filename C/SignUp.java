package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Checkbox;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {
	// 변수
	private JTextField textField; // 아이디입력
	private JTextField textField_1; // 비밀번호입력
	private JTextField textField_2; // 비밀번호 재입력
	private JTextField textField_3; // 닉네임입력
	private JTextField textField_4; // 전화번호

	// SignUp
	public SignUp() {
		// 프레임설정
		setTitle("회원가입");
		setSize(505, 647);
		getContentPane().setLayout(null);

		// 중복확인버튼
		JButton btnNewButton = new JButton("중복확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// DAO.compare에 ID텍스트필드대입
					if (textField.getText().equals(null) || textField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "알림", 0);
					}
					// 있을경우
					else if (new MemberDAO().compare(textField.getText())) {
						JOptionPane.showMessageDialog(null, "이미 있는 아이디입니다.", "알림", 0);
					}
					// 없을경우
					else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다..", "알림", 0);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(373, 181, 104, 43);
		getContentPane().add(btnNewButton);

		// 회원정보입력 레이블
		JLabel lblNewLabel = new JLabel("회원정보입력");
		lblNewLabel.setBounds(12, 120, 90, 30);
		getContentPane().add(lblNewLabel);

		// 아이디입력 텍스트필드
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(null); // 클릭하면 공백
			}
		});
		textField.setForeground(new Color(192, 192, 192));
		textField.setText("  아이디 입력");
		textField.setBounds(12, 182, 465, 42);
		getContentPane().add(textField);
		textField.setColumns(10);

		// 비밀번호입력 텍스트필드
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setText(null);
			}
		});
		textField_1.setText("  비밀번호 입력");
		textField_1.setForeground(new Color(192, 192, 192));
		textField_1.setColumns(10);
		textField_1.setBounds(12, 223, 465, 42);
		getContentPane().add(textField_1);

		// 비밀번호재입력 텍스트필드
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_2.setText(null);
			}
		});
		textField_2.setForeground(new Color(192, 192, 192));
		textField_2.setText("  비밀번호 재확인");
		textField_2.setColumns(10);
		textField_2.setBounds(12, 265, 465, 42);
		getContentPane().add(textField_2);

		// 닉네임입력 텍스트필드
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_3.setText(null);
			}
		});
		textField_3.setForeground(new Color(192, 192, 192));
		textField_3.setText("  이름 입력");
		textField_3.setColumns(10);
		textField_3.setBounds(12, 306, 465, 42);
		getContentPane().add(textField_3);

		// 전화번호입력 텍스트필드
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_4.setText(null);
			}
		});
		textField_4.setText("  전화번호 입력 (-도 입력해주세요)");
		textField_4.setForeground(Color.LIGHT_GRAY);
		textField_4.setColumns(10);
		textField_4.setBounds(12, 348, 465, 42);
		getContentPane().add(textField_4);

		// 전체동의패널
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 388, 465, 143);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		// 이용약간체크박스
		JCheckBox checkBox = new JCheckBox("이용약관동의");
		checkBox.setBounds(18, 45, 115, 23);
		panel_1.add(checkBox);
		// 개인정보체크박스
		JCheckBox checkBox_1 = new JCheckBox("개인정보 수집동의");
		checkBox_1.setBounds(18, 81, 194, 23);
		panel_1.add(checkBox_1);
		// 전체동의체크박스
		JCheckBox chckbxNewCheckBox = new JCheckBox("전체동의");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBox.setSelected(false);
				checkBox_1.setSelected(false);
				if (chckbxNewCheckBox.isSelected()) {
					checkBox.setSelected(true);
					checkBox_1.setSelected(true);
				}
			}
		});
		chckbxNewCheckBox.setBounds(8, 6, 115, 23);
		panel_1.add(chckbxNewCheckBox);

		// 회원가입 액션 레이블
		JLabel lblNewLabel_1 = new JLabel("회원가입");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) { // 클릭하면
				try {
					// 모든 항목을 입력하지 않았을때
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")
							|| textField_4.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 항목을 입력하세요", "알림", 0);
					} else if (!checkBox.isSelected() || !checkBox_1.isEnabled()) {
						JOptionPane.showMessageDialog(null, "약관에 동의해주세요", "알림", 0);
					}
					// 회원가입완료
					else if (textField_1.getText().equals(textField_2.getText())) {
						// 회원가입완료
						new MemberDAO().create(new MemberDTO(textField.getText(),
								Integer.parseInt(textField_2.getText()), textField_3.getText(), textField_4.getText()));
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "알림", 0);
						new MainPage(); // 메인페이지로 이동
					} else {
						// 비밀번호가 재입력값과 다를때
						JOptionPane.showMessageDialog(null, "비밀번호가 재입력과 다릅니다.", "알림", 0);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\091.png"));
		lblNewLabel_1.setBounds(12, 541, 465, 42);
		getContentPane().add(lblNewLabel_1);

		// 요기요 상단
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\001.png"));
		label.setBounds(0, 0, 489, 74);
		getContentPane().add(label);

		// 요기요 로고
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MainPage(); // 클릭하면 메인페이지로 이동
				dispose();
			}
		});
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 13, 106, 45);
		getContentPane().add(label_1);

		setVisible(true);

	}
}
