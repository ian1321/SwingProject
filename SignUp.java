package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class SignUp extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public SignUp() {
		setTitle("회원가입");
		setSize(505,647);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 489, 81);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uC815\uBCF4 \uC785\uB825");
		lblNewLabel.setBounds(12, 130, 90, 30);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(192, 192, 192));
		textField.setText("  \uC774\uBA54\uC77C \uC8FC\uC18C \uC785\uB825");
		textField.setBounds(12, 182, 465, 42);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("  \uBE44\uBC00\uBC88\uD638 \uC785\uB825");
		textField_1.setForeground(new Color(192, 192, 192));
		textField_1.setColumns(10);
		textField_1.setBounds(12, 222, 465, 42);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(192, 192, 192));
		textField_2.setText("  \uBE44\uBC00\uBC88\uD638 \uC7AC\uD655\uC778");
		textField_2.setColumns(10);
		textField_2.setBounds(12, 264, 465, 42);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(192, 192, 192));
		textField_3.setText("  \uB2C9\uB124\uC784 \uC785\uB825");
		textField_3.setColumns(10);
		textField_3.setBounds(12, 306, 465, 42);
		getContentPane().add(textField_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 382, 465, 143);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\uC804\uCCB4\uB3D9\uC758");
		chckbxNewCheckBox.setBounds(8, 6, 115, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JCheckBox checkBox = new JCheckBox("\uC774\uC6A9\uC57D\uAD00\uB3D9\uC758");
		checkBox.setBounds(18, 45, 115, 23);
		panel_1.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("\uAC1C\uC778\uC815\uBCF4 \uC218\uC9D1\uB3D9\uC758");
		checkBox_1.setBounds(18, 81, 194, 23);
		panel_1.add(checkBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\091.png"));
		lblNewLabel_1.setBounds(12, 557, 465, 42);
		getContentPane().add(lblNewLabel_1);
		
		
		
		
		setVisible(true);
	}
}
