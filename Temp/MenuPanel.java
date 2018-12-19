package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import swing2.FoodListDAO;
import swing2.FoodListDTO;

public class MenuPanel extends JPanel {

	public MenuPanel(String menu) throws Exception {
		/* 변수 */
		RecommenderDAO rDao = new RecommenderDAO();
		RecommenderDTO rDto = new RecommenderDTO();
		FoodListDAO fDao = new FoodListDAO();
		FoodListDTO fDto = new FoodListDTO();

		/* 프레임설정 */
		setSize(220, 70);
		setLayout(null);

		/* 파라미터의 recommender 레코드 */
		ArrayList<RecommenderDTO> arr = rDao.selectColumn("fMenu", menu);
		rDto = arr.get(0);
		/* 파라미터의 foodlist 레코드 */
		ArrayList<FoodListDTO> fArr = fDao.selectColumn("menu", menu);
		fDto = fArr.get(0);

		/* 메뉴 */
		JLabel menuLabel = new JLabel(rDto.getFMenu());
		menuLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		menuLabel.setBounds(6, 44, 71, 20);
		add(menuLabel);
		/* 리뷰수 */
		JLabel countLabel_2 = new JLabel("리뷰수 : " + rDto.getCount());
		countLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		countLabel_2.setBounds(80, 26, 96, 18);
		add(countLabel_2);
		/* 별 */
		JLabel scoreLabel = new JLabel("★" + rDto.getScore());
		scoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		scoreLabel.setForeground(Color.ORANGE);
		scoreLabel.setBounds(6, 26, 62, 18);
		add(scoreLabel);
		/* 레스토랑 */
		JLabel restLabel = new JLabel(fDto.getRest());
		restLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		restLabel.setBounds(6, 5, 105, 18);
		add(restLabel);
		/* 백그라운드이미지 */
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(MenuPanel.class.getResource("/image/메뉴패널.jpg")));
		backgroundLabel.setBounds(0, 0, 220, 70);
		add(backgroundLabel);

	}
}
