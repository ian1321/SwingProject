package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class RecommenderPanel extends JPanel {
	/* MenuPanel 배열 */
	public RecommenderPanel(MenuPanel[] mP, MenuPanel[] cP) {
		/* 사이즈설정 */
		setSize(470, 240);
		setLayout(null);

		/* 띄워줄 위치설정 */
		for (int i = 0; i < mP.length; i++) {
			add(mP[i]);
			mP[i].setBounds(240, (i + 1) * 80, 220, 70);
		}

		for (int i = 0; i < cP.length; i++) {
			add(cP[i]);
			cP[i].setBounds(10, (i + 1) * 80, 220, 70);
		}

		/* 백그라운드 레이블 */
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(RecommenderPanel.class.getResource("/image/추천패널.png")));
		backgroundLabel.setBounds(0, 0, 470, 240);
		add(backgroundLabel);
	}
}
