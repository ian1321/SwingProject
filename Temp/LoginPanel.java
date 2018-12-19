package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

public class LoginPanel extends JPanel {
	public LoginPanel() throws Exception {
		/* 프레임설정 */
		setBounds(0, 0, 248, 139);
		setLayout(null);

		/* 멤버등급 이미지 */
		JLabel memberImage = new JLabel("");
		memberImage.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/\uB808\uBCA8.png")));
		memberImage.setBounds(12, 11, 77, 85);
		add(memberImage);
		/* 멤버쉽버튼 */
		JButton membershipButton = new JButton("\uB808\uBCA8/\uD3EC\uC778\uD2B8\uC548\uB0B4");
		membershipButton.setBackground(Color.GRAY);
		membershipButton.setForeground(Color.BLACK);
		membershipButton.setBounds(54, 106, 171, 23);
		add(membershipButton);
		/* 날짜 */
		Calendar cal = Calendar.getInstance();
		JLabel dateLabel = new JLabel(
				/* 현재날짜 연도 + 월 + 일 */
				cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH));
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setBounds(95, 56, 115, 15);
		add(dateLabel);
		/* 아이디 */
		JLabel idLabel = new JLabel(
				"[  " + MemberDTO.SessionId /* 세션아이디 */ + "  ]\uB2D8 \uD658\uC601\uD569\uB2C8\uB2E4");
		idLabel.setForeground(Color.WHITE);
		idLabel.setBounds(95, 20, 153, 28);
		add(idLabel);
		/* 기록 */
		JLabel OrderHistoryLabel = new JLabel(
				"\uB204\uC801 \uC8FC\uBB38 \uD69F\uC218 : " + new FileReadOrder().date(MemberDTO.SessionId).size());
		OrderHistoryLabel.setForeground(Color.WHITE);
		OrderHistoryLabel.setBounds(95, 81, 115, 15);
		add(OrderHistoryLabel);
		/* 백그라운드 레이블 */
		JLabel BackgroundLabel = new JLabel("");
		BackgroundLabel
				.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/\uB85C\uADF8\uC778\uD328\uB110.png")));
		BackgroundLabel.setBounds(0, 0, 248, 139);
		add(BackgroundLabel);

	}
}
