package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class MyPage extends JFrame {

	// Mypage
	public MyPage() {
		// 프레임설정
		setTitle("마이페이지");
		setSize(832, 539);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// 게시판 메인화면 실행
					new MainBoard();
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateMember upM = new UpdateMember();
				MemberDTO dto = new MemberDTO();
				try {
					dto = new MemberDAO().selectMine(MemberDTO.SessionId);
					dto.getId();
					dto.getPw();
					dto.getName();
					dto.getTel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// int s에 설정값 ( Yes - 0 , no - 1, 취소 - 2)
				int s = JOptionPane.showConfirmDialog(null, MemberDTO.SessionId + "님 정말 탈퇴하시겠습니까?");
				// Yes누르면 탈퇴
				if (s == 0) {
					try {
						new MemberDAO().delete(MemberDTO.SessionId);
						JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님 지금까지 이용해주셔서 감사합니다");
						new MainPage();
						dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 다른것을 선택하면 취소
				else {
					JOptionPane.showMessageDialog(null, "계속 이용해주셔서 감사합니다, 잘 부탁드리겠습니다", null, 0);
				}

			}
		});
		lblNewLabel.setBounds(196, 265, 173, 45);
		getContentPane().add(lblNewLabel);
		lblNewLabel_4.setBounds(12, 265, 173, 47);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_2.setBounds(12, 322, 173, 45);
		getContentPane().add(lblNewLabel_2);

		JLabel label_3 = new JLabel("\uB0A0\uC9DC");
		label_3.setBounds(138, 172, 150, 15);
		getContentPane().add(label_3);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_3.setForeground(Color.WHITE);

		JLabel label_2 = new JLabel("\uB204\uC801 \uC8FC\uBB38 \uD69F\uC218 : ");
		label_2.setBounds(138, 147, 150, 15);
		getContentPane().add(label_2);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_2.setForeground(Color.WHITE);

		// 로그인한 아이디 보여주는 레이블
		// sessionID는 로그인하면 설정하고 공유.
		JLabel lblNewLabel_1 = new JLabel("[  " + MemberDTO.SessionId + "  ]  님 환영합니다");
		lblNewLabel_1.setBounds(138, 103, 197, 22);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));

		// 요기요 상단 레이블
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\066.png"));
		label.setBounds(0, 0, 816, 74);
		getContentPane().add(label);

		// 요기요로고 레이블
		JLabel label_1 = new JLabel("요기요로고");
		label_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(6, 13, 106, 45);
		getContentPane().add(label_1);

		// 주문하러가기버튼 --이어지는곳
		JButton button_1 = new JButton("주문하러가기");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 휘령씨 part -->
			}
		});
		button_1.setBounds(152, 521, 142, 45);
		getContentPane().add(button_1);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\\uB9C8\uC774\uD398\uC774\uC9C0.png"));
		lblNewLabel_3.setBounds(0, 74, 816, 426);
		getContentPane().add(lblNewLabel_3);

		setVisible(true);
	}

	
	
	public class CardLayoutDemo extends JFrame { 

	    private CardLayout cLayout; 
	    private JPanel mainPane; 

	    public CardLayoutDemo(){ 

	     setTitle("Card Layout Demo"); 
	     setSize(400,250); 
	     setDefaultCloseOperation(EXIT_ON_CLOSE); 

	     mainPane = new JPanel(); 
	     cLayout = new CardLayout(); 
	     mainPane.setLayout(cLayout); 

	     JPanel yellowPane = new JPanel(); 
	     yellowPane.setBackground(Color.YELLOW); 
	     JPanel redPane = new JPanel(); 
	     redPane.setBackground(Color.RED); 

	}
	    
	}
	
}
