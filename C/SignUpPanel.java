package swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class SignUpPanel extends JPanel {

	JPanel panel;
	JCheckBox useCheckBox;
	JCheckBox infoCheckBox;
	JCheckBox allCheckBox;
	JLabel checkBoxLabel;
	JTextField securityTextField;
	JTextField idTextField;
	JTextField pwTextField;
	JTextField rpwTextField;
	JTextField nameTextField;
	JTextField telTextField;
	JLabel pwAlarmLabel;
	JLabel signUpPLabel;
	JCheckBox dupleCheckBox;
	private JLabel useTermLabel;
	private JLabel infoTermLabel;

	public SignUpPanel() {
		// 회원가입패널

		setBounds(0, 0, 493, 592);
		setLayout(null);

		signUpPLabel = new JLabel("");
		signUpPLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// 모든 항목을 입력하지 않았을때
					if (idTextField.getText().equals("") || pwTextField.getText().equals("")
							|| rpwTextField.getText().equals("") || nameTextField.getText().equals("")
							|| telTextField.getText().equals("") || securityTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 항목을 입력하세요", "알림", 0);
						// 항목 체크를 안했을때
					} else if (!rpwTextField.getText().equals(pwTextField.getText())) {

						// 비밀번호가 재입력값과 다를때
						JOptionPane.showMessageDialog(null, " 비밀번호가 재입력과 다릅니다.");
						pwAlarmLabel.setText("비밀번호가 재입력과 다릅니다");
						pwAlarmLabel.setForeground(Color.red);
					} else if (!dupleCheckBox.isSelected()) {
						JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요");
					} else if (!useCheckBox.isSelected() || !infoCheckBox.isSelected()) {
						JOptionPane.showMessageDialog(null, "약관에 동의해주세요");
					}  else if (!securityTextField.getText().equals("1518")) {
						JOptionPane.showMessageDialog(null, "시큐리티넘버가 다릅니다");
					}
					// 회원가입완료
					 else if (pwTextField.getText().equals(rpwTextField.getText())
							|| securityTextField.equals("1518")) {
						// dto객체에 값 대입
						MemberDTO dto = new MemberDTO();
						dto.setId(idTextField.getText());
						dto.setPw(Integer.parseInt(pwTextField.getText()));
						dto.setName(nameTextField.getText());
						dto.setTel(telTextField.getText());
						new MemberDAO().create(dto);
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");

						MainPage.mainPagePanel.setVisible(true);
						MainPage.signUpPanel.setVisible(false);

						MainPage.mainSubPanel.setVisible(true);
						MainPage.useTermPanel.setVisible(false);
						MainPage.infoTermPanel.setVisible(false);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		signUpPLabel.setBounds(12, 532, 466, 63);
		add(signUpPLabel);

		pwAlarmLabel = new JLabel("");
		pwAlarmLabel.setBounds(103, 171, 358, 19);
		add(pwAlarmLabel);

		JLabel duplLabel = new JLabel("");
		duplLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (idTextField.getText().equals(null) || idTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					} else if (new MemberDAO().compare(idTextField.getText())) {
						JOptionPane.showMessageDialog(null, "이미 있는 아이디입니다.");
					}
					// 없을경우
					else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
						dupleCheckBox.setSelected(true);
						;
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		duplLabel.setIcon(
				new ImageIcon(SignUpPanel.class.getResource("/image/\uC911\uBCF5\uD655\uC778\uBC84\uD2BC.png")));
		duplLabel.setBounds(103, 81, 79, 25);
		add(duplLabel);

		dupleCheckBox = new JCheckBox("ID\uC0AC\uC6A9\uAC00\uB2A5");
		dupleCheckBox.setEnabled(false);
		dupleCheckBox.setBounds(185, 81, 85, 25);
		add(dupleCheckBox);

		panel = new JPanel();
		panel.setBounds(9, 417, 470, 110);
		add(panel);
		panel.setLayout(null);

		allCheckBox = new JCheckBox("\uC804\uCCB4\uB3D9\uC758");
		allCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				useCheckBox.setSelected(false);
				infoCheckBox.setSelected(false);
				// 약관과 수집동의 동의
				if (allCheckBox.isSelected()) {
					useCheckBox.setSelected(true);
					infoCheckBox.setSelected(true);
				}
			}
		});
		allCheckBox.setBackground(Color.WHITE);
		allCheckBox.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		allCheckBox.setBounds(8, 6, 115, 23);
		panel.add(allCheckBox);

		infoCheckBox = new JCheckBox("\uAC1C\uC778\uC815\uBCF4 \uC218\uC9D1\uB3D9\uC758");
		infoCheckBox.setBackground(Color.WHITE);
		infoCheckBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		infoCheckBox.setBounds(22, 56, 132, 23);
		panel.add(infoCheckBox);

		useCheckBox = new JCheckBox("\uC774\uC6A9\uC57D\uAD00\uB3D9\uC758");
		useCheckBox.setBackground(Color.WHITE);
		useCheckBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		useCheckBox.setBounds(22, 31, 132, 23);
		panel.add(useCheckBox);

		checkBoxLabel = new JLabel("");
		checkBoxLabel.setIcon(new ImageIcon(SignUpPanel.class.getResource("/image/\uB3D9\uC758\uD328\uB110.png")));
		checkBoxLabel.setBounds(0, 0, 470, 110);
		panel.add(checkBoxLabel);

		useTermLabel = new JLabel("");
		useTermLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPage.mainSubPanel.setVisible(false);
				MainPage.useTermPanel.setVisible(true);
				MainPage.infoTermPanel.setVisible(false);
			}
		});
		useTermLabel.setBounds(163, 36, 57, 15);
		panel.add(useTermLabel);

		infoTermLabel = new JLabel("");
		infoTermLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPage.infoTermPanel.setVisible(true);
				MainPage.useTermPanel.setVisible(false);
				MainPage.mainSubPanel.setVisible(false);
			}
		});
		infoTermLabel.setBounds(162, 61, 57, 15);
		panel.add(infoTermLabel);

		securityTextField = new JTextField();
		securityTextField.setBounds(255, 323, 169, 43);
		add(securityTextField);
		securityTextField.setColumns(10);

		telTextField = new JTextField();
		telTextField.setColumns(10);
		telTextField.setBounds(103, 224, 358, 29);
		add(telTextField);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(103, 191, 358, 29);
		add(nameTextField);

		rpwTextField = new JTextField();
		rpwTextField.setColumns(10);
		rpwTextField.setBounds(103, 141, 358, 29);
		add(rpwTextField);

		pwTextField = new JTextField();
		pwTextField.setColumns(10);
		pwTextField.setBounds(103, 107, 358, 29);
		add(pwTextField);

		idTextField = new JTextField();
		idTextField.setBounds(103, 52, 358, 29);
		add(idTextField);
		idTextField.setColumns(10);

		JLabel signUpLabel = new JLabel();
		signUpLabel.setHorizontalAlignment(SwingConstants.LEFT);
		signUpLabel.setBounds(0, 0, 500, 600);
		add(signUpLabel);
		signUpLabel.setIcon(
				new ImageIcon(SignUpPanel.class.getResource("/image/\uD68C\uC6D0\uAC00\uC785\uD328\uB110.png")));

	}
}
