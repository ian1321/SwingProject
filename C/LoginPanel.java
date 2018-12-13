package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JButton;

public class LoginPanel extends JPanel {
	public LoginPanel() {
		setBounds(0, 0, 248, 139);
		setLayout(null);
		Calendar cal = Calendar.getInstance();

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/\uB808\uBCA8.png")));
		lblNewLabel_4.setBounds(12, 11, 77, 85);
		add(lblNewLabel_4);

		JButton btnNewButton = new JButton("\uB808\uBCA8/\uD3EC\uC778\uD2B8\uC548\uB0B4");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(54, 106, 171, 23);
		add(btnNewButton);
		JLabel lblNewLabel_3 = new JLabel(
				cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(95, 56, 115, 15);
		add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("[  " + MemberDTO.SessionId + "  ]\uB2D8 \uD658\uC601\uD569\uB2C8\uB2E4");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(95, 20, 153, 28);
		add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("\uB204\uC801 \uC8FC\uBB38 \uD69F\uC218 : ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(95, 81, 115, 15);
		add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/\uB85C\uADF8\uC778\uD328\uB110.png")));
		lblNewLabel.setBounds(0, 0, 248, 139);
		add(lblNewLabel);

	}
}
