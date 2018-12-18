package swing2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.xml.ws.soap.AddressingFeature;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;

public class Bag extends JFrame implements ActionListener {
	int cnt = 80;
	JTextField text;
	FoodListDAO1 dao = new FoodListDAO1();
	JLabel logo = new JLabel("yogiyo"); // 메인으로가기
	JButton moreButton = new JButton("더주문하기");
	JButton completeButton = new JButton("주문완료");
	JButton del = new JButton("삭제");
	JTextField totalPayT;
	int totalPrice;
	int number;

	public Bag(String[] food) throws Exception {

		ImageIcon p1 = new ImageIcon(Bag.class.getResource("/image2/053.png"));
		JLabel lbl1 = new JLabel(); // 빨간줄
		lbl1.setBounds(0, 0, 780, 65);
		getContentPane().add(lbl1);
		lbl1.setIcon(p1);

		for (int i = 0; i < food.length; i++) {
			if (food[i] != null) { // food[i]번방에 비어있지않다면,

				/* 버튼에있는 메뉴값 으로 dao 값 arr에저장 */
				ArrayList<FoodListDTO> arr = dao.foodName(food[i]); 
				FoodListDTO dto1 = arr.get(0);
				/* 버튼클릭마다 필드,체크박스생성 */
				text = new JTextField(20);  
				text.setText(dto1.getRest() + "\t\t" + dto1.getMenu() + "\t\t" + dto1.getPrice() + "원"); 
				number++;
				JLabel num = new JLabel();
				num.setBounds(120, cnt, 20,20);
				num.setText(number+"");
//				checkBox.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						if (e.getSource()==del) {
//							removeAll();
//						}
//					}
//				});
				add(num);
				text.setBounds(143, cnt, 415, 20);
				del.setBounds(570, 80, 97, 23);
				cnt = cnt + 30;
				logo.setBounds(12, 10, 81, 56);
				totalPrice = totalPrice + dto1.getPrice();
				getContentPane().add(text); 
//				getContentPane().add(checkBox);
				/* 더주문하기버튼 메인초기화면 이동 */
				moreButton.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
						try {
							dispose();
							new OrderMain(); //
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
		setSize(780, 580);
		getContentPane().setLayout(null);
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog(null, "지우실번호입력");
				int sel = Integer.parseInt(s);
				remove(text);
				text.setVisible(false);
				food[sel-1] = null;
				repaint();
				revalidate();
			}
		});
		getContentPane().add(del);
		
		/*요기요로고화면 이동*/
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				OrderMain orderMain = new OrderMain();
			}
		});
		getContentPane().add(logo);
		getContentPane().add(del);
		getContentPane().add(moreButton);
		getContentPane().add(completeButton);
		moreButton.setBounds(260, 401, 112, 34);
		completeButton.setBounds(378, 401, 112, 34);

		/*합계금액이 들어갈 텍스트필드*/
		totalPayT = new JTextField(); 
		totalPayT.setHorizontalAlignment(SwingConstants.CENTER);
		repaint();
		totalPayT.setText(totalPrice + "원");
		totalPayT.setColumns(10);

		JLabel totalPay = new JLabel("합계금액:"); // 총합금액(레이블(title))
		totalPay.setFont(new Font("Gulim", Font.PLAIN, 12));
		totalPayT.setBounds(374, 370, 116, 21);
		totalPay.setBounds(300, 373, 57, 15);
		getContentPane().add(totalPayT);
		getContentPane().add(totalPay);

		setVisible(true);
		completeButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == completeButton) { // 주문완료 결제 페이지
			Payment last = new Payment();
			last.totalPrice.setText(totalPayT.getText());
		}
	}
}
