package swing2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OrderMain extends JFrame implements ActionListener {
	 JButton b1 = new JButton("chicken");
	 JButton b2 = new JButton("pizza");
	 JButton b3 = new JButton("chinese");
	private JTextField search;

	public OrderMain() {
		setTitle("sortList");
		setSize(780, 580);
		getContentPane().setLayout(null);
		JLabel lbl1 = new JLabel(""); // 요기요 배너
		JLabel lbl2 = new JLabel("");
		ImageIcon[] mp = new ImageIcon[5];
		for (int i = 0; i < mp.length; i++) {
			mp[i] = new ImageIcon(OrderMain.class.getResource("/image2/05"+i+".png"));
		}
//		ImageIcon p1 = new ImageIcon(OrderMain.class.getResource("/image2/053.png")); // 치킨
//		ImageIcon p2 = new ImageIcon(OrderMain.class.getResource("/image2/054.png")); // 피자
//		ImageIcon p3 = new ImageIcon(OrderMain.class.getResource("/image2/055.png"));; // 중식
//		ImageIcon p4 = new ImageIcon(OrderMain.class.getResource("/image2/058.png")); // 요기요배너
//		ImageIcon p5 = new ImageIcon("059.png"); // 검색
		
		b1.setIcon(mp[0]);
		b1.setBounds(0, 331, 250, 200);
		b2.setIcon(mp[1]);
		b2.setBounds(258, 331, 250, 200);
		b3.setIcon(mp[2]);
		b3.setBounds(514, 331, 250, 200);
		lbl1.setIcon(mp[3]);
		lbl1.setBounds(0, 0, 780, 61);
		lbl2.setIcon(mp[4]);
		lbl2.setBounds(0, 78, 780, 243);

		getContentPane().add(lbl1);
		getContentPane().add(b1);
		getContentPane().add(b2);
		getContentPane().add(b3);
		//	주소검색창 		//
		search = new JTextField();
		search.setText("테스트");
		search.setBounds(264, 207, 245, 42);
		getContentPane().add(search);
		search.setColumns(10);
		getContentPane().add(lbl2);

		setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		OrderMain page1 = new OrderMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ImageIcon[] img = new ImageIcon[9];
		for (int i = 0; i < img.length; i++) {
			img[i] = new ImageIcon("06" + i + ".png");
		}
		//   치킨 눌렀을때 	//
		if (e.getSource() == b1) { 
			try {
				ListPage lp = new ListPage();
				lp.rest1.setText("kfc");		//다음페이지인 ListPage의 버튼의 입력값 , image , 콤보박스 index
				lp.rest1.setIcon(img[0]);
				lp.rest2.setText("bbq");
				lp.rest2.setIcon(img[1]);
				lp.rest3.setText("bhc");
				lp.rest3.setIcon(img[2]);
				lp.foodCombo2.setSelectedIndex(0); 
			} catch (Exception e1) {
				e1.printStackTrace();
			}	//  피자를 눌렀을때	//
		} else if (e.getSource() == b2) { 
			try {
				ListPage lp = new ListPage();
				lp.rest1.setText("pizzahut");
				lp.rest1.setIcon(img[3]);
				lp.rest2.setText("domino");
				lp.rest2.setIcon(img[4]);
				lp.rest3.setText("ogurice");
				lp.rest3.setIcon(img[5]);
				lp.foodCombo2.setSelectedIndex(1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}	//  중식을 눌렀을때	//
		} else if (e.getSource() == b3) { 
			try {
				ListPage lp = new ListPage();
				lp.rest1.setText("hongkong");
				lp.rest1.setIcon(img[6]);
				lp.rest2.setText("kyodong");
				lp.rest2.setIcon(img[7]);
				lp.rest3.setText("backs");
				lp.rest3.setIcon(img[8]);
				lp.foodCombo2.setSelectedIndex(2);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		dispose();
	}
}
