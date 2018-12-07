package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyPagePanel extends JPanel{

	public MyPagePanel() {
		setBounds(0, 0, 466, 312);
		setLayout(null);
		
		JLabel updateLabel = new JLabel("");
		updateLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				UpdatePanel updatePanel = new UpdatePanel();
				MemberDTO dto = new MemberDTO();
				try {
					dto = new MemberDAO().selectMine(MemberDTO.SessionId);
					updatePanel.idTextField.setText(dto.getId());
					updatePanel.pwTextField.setText(String.valueOf(dto.getPw()));
					updatePanel.rpwTextField.setText(String.valueOf(dto.getPw()));
					updatePanel.nameTextField.setText(dto.getName());
					updatePanel.telTextField.setText(dto.getTel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainPage.mainPanel.add(updatePanel);
				updatePanel.setVisible(true);
				MainPage.mainPagePanel.setVisible(false);
			}
		});
		
		JLabel quitLabel = new JLabel("");
		quitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// int s에 설정값 ( Yes - 0 , no - 1, 취소 - 2)
				int s = JOptionPane.showConfirmDialog(null, MemberDTO.SessionId + "님 정말 탈퇴하시겠습니까?");
				// Yes누르면 탈퇴
				if (s == 0) {
					try {
						new MemberDAO().delete(MemberDTO.SessionId);
						JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님 지금까지 이용해주셔서 감사합니다");
						MemberDTO.SessionId = null;
						MainPage.mainPagePanel.setVisible(true);
						MainPage.mainSubPanel.setVisible(true);
						MainPage.loginPanel.setVisible(false);
						MainPage.idInputPanel.setVisible(true);
						setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 다른것을 선택하면 취소
				else {
					JOptionPane.showMessageDialog(null, "계속 이용해주셔서 감사합니다, 잘 부탁드리겠습니다", null, 0);
				}
			}
		});
		quitLabel.setBounds(240, 60, 214, 49);
		add(quitLabel);
		updateLabel.setBounds(12, 60, 209, 49);
		add(updateLabel);
		
		JLabel myPageLabel = new JLabel("");
		myPageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPageLabel.setIcon(new ImageIcon(MyPagePanel.class.getResource("/image/\uB9C8\uC774\uD398\uC774\uC9C0\uD328\uB110.png")));
		myPageLabel.setBounds(0, 0, 466, 312);
		add(myPageLabel);
		
		JLabel boardLabel = new JLabel("New label");
		boardLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPage.boardPanel.setVisible(true);
			}
		});
		boardLabel.setBounds(12, 126, 209, 49);
		add(boardLabel);
		
	}
}
