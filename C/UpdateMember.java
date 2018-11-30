package swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateMember extends JFrame{
	//변수
	private JTextField textField_1; //비밀번호변경
	private JTextField textField_2; //비밀번호재입력
	private JTextField textField_3; //이름입력
	private JTextField textField_4; //전화번호입력
	
	//updateMember
	public UpdateMember() {
		//프레임설정
		setTitle("회원정보수정");
		setSize(505,647);
		getContentPane().setLayout(null);
		
		//회원정보 수정 레이블
		JLabel lblNewLabel = new JLabel("회원정보 수정");
		lblNewLabel.setBounds(12, 120, 90, 30);
		getContentPane().add(lblNewLabel);
		
		//비밀번호변경 텍스트필드
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setText(null); //클릭하면 공백
			}
		});
		textField_1.setText("  비밀번호 입력");
		textField_1.setForeground(new Color(192, 192, 192));
		textField_1.setColumns(10);
		textField_1.setBounds(12, 223, 465, 42);
		getContentPane().add(textField_1);
		
		//비밀번호재확인 텍스트필드
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_2.setText(null); //클릭하면 공백
			}
		});
		textField_2.setForeground(new Color(192, 192, 192));
		textField_2.setText("  비밀번호 재확인");
		textField_2.setColumns(10);
		textField_2.setBounds(12, 265, 465, 42);
		getContentPane().add(textField_2);
		
		//이름입력 텍스트필드
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_3.setText(null); //클릭하면 공백
			}
		});
		textField_3.setForeground(new Color(192, 192, 192));
		textField_3.setText("  이름 입력");
		textField_3.setColumns(10);
		textField_3.setBounds(12, 306, 465, 42);
		getContentPane().add(textField_3);
		
		//전화번호입력 텍스트필드
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_4.setText(null); //클릭하면 공백
			}
		});
		textField_4.setText("  전화번호 입력");
		textField_4.setForeground(Color.LIGHT_GRAY);
		textField_4.setColumns(10);
		textField_4.setBounds(12, 348, 465, 42);
		getContentPane().add(textField_4);
		
		//수정완료버튼
		JLabel lblNewLabel_1 = new JLabel("수정완료");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
					}
					else if (textField_1.getText().equals(textField_2.getText())) {
						MemberDTO dto = new MemberDTO();
						dto.setId(MemberDTO.SessionId);
						dto.setPw(Integer.parseInt(textField_2.getText()));
						dto.setName(textField_3.getText());
						dto.setTel(textField_4.getText());
						new MemberDAO().update(dto);
						JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
						new MyPage();
						dispose();
					}
					else  {
						JOptionPane.showMessageDialog(null, "비밀번호가 재입력값과 일치하지 않습니다.");
					}
				}  catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\\uC591\uCC2C\uC6B0\uD53D\uD53D\\091.png"));
		lblNewLabel_1.setBounds(12, 435, 465, 42);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\001.png"));
		label.setBounds(0, 0, 489, 74);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 13, 106, 45);
		getContentPane().add(label_1);
		
		
		
		setVisible(true);
	}
}