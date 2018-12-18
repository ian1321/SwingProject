package swing2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.fabric.xmlrpc.base.Array;
import javax.swing.JLabel;

public class ListPage extends JFrame implements ActionListener { // moreOrder

//	String[] three = { "kfc", "bbq", "bhc", "pizzahut", "domino", "ogurice", "hongkong", "kyodong", "backs" };
	String[] img = { "060.png" };
	// restList 버튼 //
	JButton rest1 = new JButton();
	JButton rest2 = new JButton();
	JButton rest3 = new JButton();

	String[] food = { "치킨", "피자", "중식" };
	JComboBox foodCombo2 = new JComboBox(food);
	JLabel lbl1 = new JLabel("");

	public ListPage() throws Exception {

		setTitle("listpage");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(780, 580);
		getContentPane().setLayout(null);
		ImageIcon p1 = new ImageIcon(ListPage.class.getResource("/image2/053.png"));
		lbl1.setIcon(p1);

		foodCombo2.setBounds(155, 119, 173, 35);
		foodCombo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox) e.getSource(); // cb
				int index = cb.getSelectedIndex();
				// 콤보박스의 인덱스가 0번일때 버튼값 //
				if (index == 0) {
					rest1.setText("kfc");
					rest1.setIcon(new ImageIcon("060.png"));
					rest2.setText("bbq");
					rest2.setIcon(new ImageIcon("061.png"));
					rest3.setText("bhc");
					rest3.setIcon(new ImageIcon("062.png"));
				} else if (index == 1) {
					rest1.setText("pizzahut");
					rest1.setIcon(new ImageIcon("063.png"));
					rest2.setText("domino");
					rest2.setIcon(new ImageIcon("064.png"));
					rest3.setText("ogurice");
					rest3.setIcon(new ImageIcon("065.png"));
				} else if (index == 2) {
					rest1.setText("hongkong");
					rest1.setIcon(new ImageIcon("066.png"));
					rest2.setText("kyodong");
					rest2.setIcon(new ImageIcon("067.png"));
					rest3.setText("backs");
					rest3.setIcon(new ImageIcon("068.png"));
				}
			}
		});

		rest1.setBounds(155, 175, 390, 100);
		getContentPane().add(rest1);
		rest2.setBounds(155, 285, 390, 100);
		getContentPane().add(rest2);
		rest3.setBounds(155, 396, 390, 100);
		getContentPane().add(rest3);
		lbl1.setBounds(0, 0, 764, 66);

		getContentPane().add(lbl1);
		getContentPane().add(foodCombo2);
		setVisible(true);
		rest1.addActionListener(this);
		rest2.addActionListener(this);
		rest3.addActionListener(this);
	}

	public static void main(String[] args) throws Exception {
		ListPage list = new ListPage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			dispose();
			ImageIcon[] img1 = new ImageIcon[9];
			ImageIcon[] img2 = new ImageIcon[9];
			ImageIcon[] img3 = new ImageIcon[9];
			ImageIcon[] franC = new ImageIcon[9];

			for (int i = 0; i < img2.length; i++) {
				/* 90번대 치킨 , 100번대 피자, 110번대 중식사진 삽입*/
				img1[i] = new ImageIcon(ListPage.class.getResource("/image2/09" + i + ".png"));
				img2[i] = new ImageIcon(ListPage.class.getResource("/image2/10" + i + ".png"));
				img3[i] = new ImageIcon(ListPage.class.getResource("/image2/11" + i + ".png"));
				franC[i] = new ImageIcon("06"+i+ ".png");
				System.out.println("11");
			}

			 /*식당 1번을 눌렀을때 , 식당1번이 kfc일때.*/
			if (e.getSource() == rest1 && rest1.getText().equals("kfc")) {
				Menu menu = new Menu(rest1.getText());
				menu.franchise.setIcon(franC[0]);
				menu.b1.setIcon(img1[0]);
				menu.b2.setIcon(img1[1]);
				menu.b3.setIcon(img1[2]);
			} else if (e.getSource() == rest2 && rest2.getText().equals("bbq")) {
				Menu menu;
				menu = new Menu(rest2.getText());
				menu.franchise.setIcon(franC[1]);
				menu.b1.setIcon(img1[3]);
				menu.b2.setIcon(img1[4]);
				menu.b3.setIcon(img1[5]);
			} else if (e.getSource() == rest3 && rest3.getText().equals("bhc")) {
				Menu menu;
				menu = new Menu(rest3.getText());
				menu.franchise.setIcon(franC[2]);
				menu.b1.setIcon(img1[6]);
				menu.b2.setIcon(img1[7]);
				menu.b3.setIcon(img1[8]);
			}
			if (e.getSource() == rest1 && rest1.getText().equals("pizzahut")) {
				Menu menu;
				menu = new Menu(rest1.getText());
				menu.franchise.setIcon(franC[3]);
				menu.b1.setIcon(img2[0]);
				menu.b2.setIcon(img2[1]);
				menu.b3.setIcon(img2[2]);
			} else if (e.getSource() == rest2 && rest2.getText().equals("domino")) {
				Menu menu = new Menu(rest2.getText());
				menu.franchise.setIcon(franC[4]);
				menu.b1.setIcon(img2[3]);
				menu.b2.setIcon(img2[4]);
				menu.b3.setIcon(img2[5]);
			} else if (e.getSource() == rest3 && rest3.getText().equals("ogurice")) {
				Menu menu = new Menu(rest3.getText());
				menu.franchise.setIcon(franC[5]);
				menu.b1.setIcon(img2[6]);
				menu.b2.setIcon(img2[7]);
				menu.b3.setIcon(img2[8]);
			}
			if (e.getSource() == rest1 && rest1.getText().equals("hongkong")) {
				Menu menu = new Menu(rest1.getText());
				menu.franchise.setIcon(franC[6]);
				menu.b1.setIcon(img3[0]);
				menu.b2.setIcon(img3[1]);
				menu.b3.setIcon(img3[2]);
			} else if (e.getSource() == rest2 && rest2.getText().equals("kyodong")) {
				Menu menu = new Menu(rest2.getText());
				menu.franchise.setIcon(franC[7]);
				menu.b1.setIcon(img3[3]);
				menu.b2.setIcon(img3[4]);
				menu.b3.setIcon(img3[5]);
			} else if (e.getSource() == rest3 && rest3.getText().equals("backs")) {
				Menu menu = new Menu(rest3.getText());
				menu.franchise.setIcon(franC[8]);
				menu.b1.setIcon(img3[6]);
				menu.b2.setIcon(img3[7]);
				menu.b3.setIcon(img3[8]);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
