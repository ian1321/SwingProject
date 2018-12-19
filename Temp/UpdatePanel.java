package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdatePanel extends JPanel {
	/* 변수 */
	static JTextField idTextField;
	JTextField pwTextField;
	JTextField rpwTextField;
	JTextField nameTextField;
	JTextField telTextField;

	public UpdatePanel() {
		/* 프레임설정 */
		setBounds(0, 0, 500, 600);
		setLayout(null);
		/* 종료버튼 */
		JButton xButton = new JButton("X");
		xButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				MainPage.mainPagePanel.setVisible(true);
			}
		});

		/*-----------------------텍스트필드-----------------------*/
		/* 아이디입력 */
		idTextField = new JTextField();
		idTextField.setEnabled(false);
		idTextField.setColumns(10);
		idTextField.setBounds(103, 49, 358, 29);
		add(idTextField);
		/* 비밀번호입력 */
		pwTextField = new JTextField();
		pwTextField.setColumns(10);
		pwTextField.setBounds(103, 104, 358, 29);
		add(pwTextField);
		/* 비밀번호 재입력 */
		rpwTextField = new JTextField();
		rpwTextField.setColumns(10);
		rpwTextField.setBounds(103, 138, 358, 29);
		add(rpwTextField);
		/* 이름 입력 */
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(103, 188, 358, 29);
		add(nameTextField);
		/* 전화번호 입력 */
		telTextField = new JTextField();
		telTextField.setColumns(10);
		telTextField.setBounds(103, 221, 358, 29);
		add(telTextField);

		/* 종료버튼 */
		xButton.setForeground(Color.WHITE);
		xButton.setBackground(Color.RED);
		xButton.setBounds(430, 12, 56, 25);
		add(xButton);
		/* 회원수정 완료 */
		JLabel updateLabel = new JLabel("");
		updateLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					/* 아이디와 비밀번호 공백 */
					if (idTextField.getText().equals("") || pwTextField.getText().equals("")
							|| nameTextField.getText().equals("") || telTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
						/* 비밀번호 재입력 같다면 */
					} else if (pwTextField.getText().equals(rpwTextField.getText())) {
						MemberDTO dto = new MemberDTO();
						dto.setId(MemberDTO.SessionId);
						dto.setPw(Integer.parseInt(pwTextField.getText()));
						dto.setName(nameTextField.getText());
						dto.setTel(telTextField.getText());
						new MemberDAO().update(dto);
						JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
						/*패널설정*/
						setVisible(false);
						MainPage.mainPagePanel.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 재입력값과 일치하지 않습니다.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		updateLabel.setBounds(12, 541, 474, 51);
		add(updateLabel);
		/* 백그라운드 */
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(
				new ImageIcon(UpdatePanel.class.getResource("/image/\uD68C\uC6D0\uC218\uC815\uD328\uB110.png")));
		backgroundLabel.setBounds(0, 0, 500, 600);
		add(backgroundLabel);

	}
}
