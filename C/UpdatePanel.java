package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdatePanel extends JPanel {
	static JTextField idTextField;
	static JTextField pwTextField;
	static JTextField rpwTextField;
	static JTextField nameTextField;
	static JTextField telTextField;

	public UpdatePanel() {
		setBounds(0, 0, 487, 592);
		setLayout(null);

		rpwTextField = new JTextField();
		rpwTextField.setColumns(10);
		rpwTextField.setBounds(103, 138, 358, 29);
		add(rpwTextField);

		JLabel updateLabel = new JLabel("");
		updateLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (idTextField.getText().equals("") || pwTextField.getText().equals("")
							|| nameTextField.getText().equals("") || telTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
					} else if (pwTextField.getText().equals(rpwTextField.getText())) {
						MemberDTO dto = new MemberDTO();
						dto.setId(MemberDTO.SessionId);
						dto.setPw(Integer.parseInt(pwTextField.getText()));
						dto.setName(nameTextField.getText());
						dto.setTel(telTextField.getText());
						new MemberDAO().update(dto);
						JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
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
		updateLabel.setBounds(12, 529, 466, 63);
		add(updateLabel);

		telTextField = new JTextField();
		telTextField.setColumns(10);
		telTextField.setBounds(103, 221, 358, 29);
		add(telTextField);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(103, 188, 358, 29);
		add(nameTextField);

		pwTextField = new JTextField();
		pwTextField.setColumns(10);
		pwTextField.setBounds(103, 104, 358, 29);
		add(pwTextField);

		idTextField = new JTextField();
		idTextField.setEnabled(false);
		idTextField.setColumns(10);
		idTextField.setBounds(103, 49, 358, 29);
		add(idTextField);

		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(
				new ImageIcon(UpdatePanel.class.getResource("/image/\uD68C\uC6D0\uC218\uC815\uD328\uB110.png")));
		backgroundLabel.setBounds(0, 0, 487, 592);
		add(backgroundLabel);

	}
}
