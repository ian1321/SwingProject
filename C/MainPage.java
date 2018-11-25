package swing;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import com.mysql.jdbc.PreparedStatement;

import java.awt.Component;
import java.awt.TextField;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener{
	static JTextField textField;
	static JTextField textField_1;
	static JButton button = new JButton("회원가입");
	static JButton button2 = new JButton("로그인");
	
	ArrayList list = new ArrayList();
	public MainPage() {
		setTitle("메인페이지");
		setSize(1148, 652);
		getContentPane().setLayout(null);

		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setText(null);
			}
		});
		textField_1.setText("  \uD328\uC2A4\uC6CC\uB4DC");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setColumns(10);
		textField_1.setBounds(586, 469, 348, 38);
		getContentPane().add(textField_1);
		button.setBackground(Color.YELLOW);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PreSignUp();
			}
		});
		button.setBounds(795, 541, 97, 23);
		getContentPane().add(button);
		button.addActionListener(this);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (new MemberDAO().select(textField.getText(), textField_1.getText())) {
						PreSignUp pre = new PreSignUp();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button2.setBackground(Color.RED);

		button2.setBounds(613, 541, 97, 23);
		getContentPane().add(button2);

		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(null);
			}
		});
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setText("  \uC544\uC774\uB514");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(586, 412, 348, 38);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\086.png"));
		lblNewLabel.setBounds(0, 0, 1132, 613);
		getContentPane().add(lblNewLabel);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	}
	
