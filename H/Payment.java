package swing2;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import swing.Complete;
import java.awt.Color;
import javax.swing.JTextArea;

public class Payment extends JFrame{
	private JTextField addrT;
	private JTextField telT;
	JTextField totalPrice;
	public Payment() {
		setSize(780,580);
		getContentPane().setLayout(null);
		
		ImageIcon p1 = new ImageIcon(Payment.class.getResource("/image2/053.png"));
		JLabel lbl1 = new JLabel();	//빨간줄
		lbl1.setBounds(0, 0, 780, 65);
		getContentPane().add(lbl1);
		lbl1.setIcon(p1);
		
		JLabel lb1 = new JLabel("결제하기");
		lb1.setBounds(143, 72, 338, 33);
		getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("총금액");
		lb2.setBounds(143, 332, 57, 15);
		getContentPane().add(lb2);
		
		JCheckBox card = new JCheckBox("card");
		card.setBounds(233, 392, 57, 23);
		getContentPane().add(card);
		
		JCheckBox cash = new JCheckBox("cash");
		cash.setBounds(312, 392, 57, 23);
		getContentPane().add(cash);
		
		JLabel how = new JLabel("결제방법");
		how.setBounds(143, 396, 57, 15);
		getContentPane().add(how);
		
		JLabel info = new JLabel("배달정보");
		info.setBounds(143, 108, 57, 15);
		getContentPane().add(info);
		
		JLabel lblNewLabel = new JLabel("주소");
		lblNewLabel.setBounds(143, 133, 57, 15);
		getContentPane().add(lblNewLabel);
		
		addrT = new JTextField();
		addrT.setBounds(233, 130, 258, 21);
		getContentPane().add(addrT);
		addrT.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("휴대폰");
		lblNewLabel_1.setBounds(143, 161, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		telT = new JTextField();
		telT.setColumns(10);
		telT.setBounds(233, 158, 258, 21);
		getContentPane().add(telT);
		
		JLabel hope = new JLabel("요청사항");
		hope.setBounds(143, 196, 57, 15);
		getContentPane().add(hope);
		
		totalPrice = new JTextField();
		totalPrice.setBounds(233, 329, 116, 21);
		getContentPane().add(totalPrice);
		totalPrice.setColumns(10);
		
		JTextArea hopeArea = new JTextArea();
		hopeArea.setBounds(233, 189, 258, 77);
		getContentPane().add(hopeArea);
		
		JButton b1 = new JButton("주문완료");
		b1.setBounds(252, 457, 115, 33);
		getContentPane().add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Complete();
			}
		});
		
		setVisible(true);
	}
}
