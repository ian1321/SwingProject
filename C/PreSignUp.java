package swing;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PreSignUp extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public PreSignUp() {
		
		setTitle("회원가입");
		setSize(505,647);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(71, 255, 323, 45);
		textField.setText("\uC774\uBA54\uC77C \uC8FC\uC18C \uC785\uB825 \uD544\uC218()");
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(71, 312, 323, 45);
		textField_1.setText("\uBE44\uBC00\uBC88\uD638 \uC785\uB825 \uD544\uC218()");
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(181, 162, 125, 60);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\087.png"));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(204, 0, 0));
		lblNewLabel_1.setBounds(71, 389, 323, 45);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\088.png"));
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUp signUp = new SignUp();
			}
		});
		lblNewLabel_2.setBounds(273, 479, 121, 32);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\089.png"));
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC694\uAE30\uC694\uAC00 \uCC98\uC74C\uC774\uC2E0\uAC00\uC694?");
		lblNewLabel_3.setBounds(71, 489, 166, 17);
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("굴림체", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 489, 81);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\090.png"));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 10, 117, 61);
		panel.add(label_1);
		
		setVisible(true);
		
		
		
	}
	
	
}
