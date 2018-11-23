package order091;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class OrderPageList extends JFrame {// 음식고르는 페이지
	private JTextField textArea;
	
	public OrderPageList() {
		setTitle("https://www.yogiyo.co.kr/mobile/#/");
		setSize(776, 579);
		getContentPane().setLayout(null);
		
		JButton searchButton = new JButton("");		//text
		searchButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120008.png"));
		searchButton.setBounds(508, 184, 52, 41);
		getContentPane().add(searchButton);
		
		JButton logoButton = new JButton("");
		logoButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120009.png"));
		logoButton.setBounds(45, 10, 92, 41);
		getContentPane().add(logoButton);
		
		textArea = new JTextField();
		textArea.setBounds(263, 184, 243, 41);
		getContentPane().add(textArea);
		textArea.setColumns(10);
		
		JLabel stringLabel = new JLabel("");
		stringLabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120003.png"));
		stringLabel.setBounds(12, 73, 737, 235);
		getContentPane().add(stringLabel);
		
		JButton franchise = new JButton("");
		franchise.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120004.png"));
		franchise.setBounds(22, 318, 233, 221);
		getContentPane().add(franchise);
		
		JButton chicken = new JButton("");	//치킨메뉴버튼 
		chicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPageList002 chik = new OrderPageList002();
			}
		});
		chicken.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120005.png"));
		chicken.setBounds(271, 318, 233, 221);
		getContentPane().add(chicken);
		
		JButton pizza = new JButton("");
		pizza.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120006.png"));
		pizza.setBounds(516, 318, 233, 221);
		getContentPane().add(pizza);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\\uCC0C\uB974\uB808\uAE30\\20181120007.png"));
		lblNewLabel.setBounds(0, 0, 760, 73);
		getContentPane().add(lblNewLabel);
		
		setVisible(true);
	}
}
