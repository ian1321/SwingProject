package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class RecommenderPanel extends JPanel{
	public RecommenderPanel(MenuPanel[] mP, MenuPanel[] cP) {
		setSize(470,240);
		setLayout(null);
		
		for (int i = 0; i < mP.length; i++) {
			add(mP[i]);
			mP[i].setBounds(240,(i+1)*80, 220, 70);
		}
		
		for (int i = 0; i < cP.length; i++) {
			add(cP[i]); 
			cP[i].setBounds(10,(i+1)*80,220,70);
		}
		
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(RecommenderPanel.class.getResource("/image/추천패널.png")));
		backgroundLabel.setBounds(0, 0, 470, 240);
		add(backgroundLabel);
	}
}
