package swing2;

import javax.swing.JFrame;

import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;

public class Menu extends JFrame implements ActionListener {
	JButton b1 = new JButton();
	JButton b2 = new JButton();
	JButton b3 = new JButton();
	JLabel franchise = new JLabel("프랜차이즈 이름.");
	JTextField textField1 = new JTextField();
	JTextField textField2 = new JTextField();
	JTextField textField3 = new JTextField();
	static String[] food = new String[10];

	int cnt = 50;
			
	public Menu(String name) throws Exception {
		ImageIcon p1 = new ImageIcon(Menu.class.getResource("/image2/053.png"));
		JLabel lbl1 = new JLabel();
		lbl1.setBounds(0, 0, 780, 65);
		getContentPane().add(lbl1);
		lbl1.setIcon(p1);
			//메뉴값 받아와서 dao 돌리고 리턴한값 arr에저장 //
		FoodListDAO1 dao = new FoodListDAO1();
		// arr에저장된값 DTO타입으로 받아준후, //
		ArrayList<FoodListDTO> arr = dao.connect(name);

		FoodListDTO dto1 = arr.get(0); 
		FoodListDTO dto2 = arr.get(1);
		FoodListDTO dto3 = arr.get(2);

		//		franchise.setText(dto1.getRest());
		// 버튼마다, 메뉴이름 값 세팅 // 
		b1.setText(dto1.getMenu() + "");
		b2.setText(dto2.getMenu() + "");
		b3.setText(dto3.getMenu() + "");
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setText(dto1.getPrice() + "원");
		textField2.setText(dto2.getPrice() + "원");
		textField3.setText(dto3.getPrice() + "원");
		setTitle("menu");
		setSize(780, 580);
		getContentPane().setLayout(null);
		b1.setBounds(87, 191, 400, 100);
		b2.setBounds(87, 308, 400, 100);
		b3.setBounds(87, 431, 400, 100);
		getContentPane().add(b1);
		getContentPane().add(b2);
		getContentPane().add(b3);
		getContentPane().add(franchise);

		franchise.setBounds(87, 75, 400, 100);
		textField1.setBounds(499, 217, 175, 50);
		textField2.setBounds(499, 334, 175, 50);
		textField3.setBounds(499, 444, 175, 50);
		textField1.setColumns(10);
		textField2.setColumns(10);
		textField3.setColumns(10);

		getContentPane().add(textField1);
		getContentPane().add(textField2);
		getContentPane().add(textField3);
		
		JLabel back = new JLabel("");
		ImageIcon backW = new ImageIcon(Menu.class.getResource("/image2/2000.png"));
		back.setIcon(backW);
		back.setBounds(10, 0, 764, 541);
		getContentPane().add(back);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			/*버튼을눌렀을때 */
		if (e.getSource() == b1) {
				for (int i = 0; i < food.length; i++) {
					/* 배열방이 비어있다면 food[i] 배열방에 b1.text값을넣고 브레이크 */
					if (food[i] == null) {
						food[i] = b1.getText();
						break;
					}
				}
		} else if (e.getSource() == b2) {
				for (int i = 0; i < food.length; i++) {
					if (food[i] == null) {
						food[i] = b2.getText();
						break;
					}
				}
		} else if (e.getSource() == b3) {
				for (int i = 0; i < food.length; i++) {
					if (food[i] == null) {
						food[i] = b3.getText();
						break;
					}
				}
		}dispose();
			/* food배열로 생성 */
			new Bag(food);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
