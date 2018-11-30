package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPage extends JFrame{
	
	//Mypage
	public MyPage() {
		//프레임설정
		setTitle("마이페이지");
		setSize(505,647);
		getContentPane().setLayout(null);
		
		//요기요 상단 레이블
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\001.png"));
		label.setBounds(0, 0, 489, 74);
		getContentPane().add(label);
		
		//요기요로고 레이블
		JLabel label_1 = new JLabel("요기요로고");
		label_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MainPage(); //로고 클릭하면 메인페이지로
				dispose();
			}
		});
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 13, 106, 45);
		getContentPane().add(label_1);
		
		//단계 레이블 --병아리
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\032.png"));
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 166, 115, 107);
		getContentPane().add(lblNewLabel);
		
		//로그인한 아이디 보여주는 레이블
		//sessionID는 로그인하면 설정하고 공유.
		JLabel lblNewLabel_1 = new JLabel("[  " + MemberDTO.SessionId + "  ]  님 환영합니다");
		lblNewLabel_1.setBounds(152, 200, 197, 22);
		getContentPane().add(lblNewLabel_1);
		
		//회원정보수정 버튼
		JButton btnNewButton = new JButton("회원정보수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateMember();
				dispose();
			}
		});
		btnNewButton.setBounds(65, 295, 142, 45);
		getContentPane().add(btnNewButton);
		
		//회원탈퇴 버튼
		JButton btnNewButton_1 = new JButton("회원 탈퇴");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int s에 설정값 ( Yes - 0 , no - 1, 취소 - 2)
				int s = JOptionPane.showConfirmDialog(null, MemberDTO.SessionId + "님 정말 탈퇴하시겠습니까?");
				//Yes누르면 탈퇴
				if ( s == 0) {
					try {
						new MemberDAO().delete(MemberDTO.SessionId);
						JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님 지금까지 이용해주셔서 감사합니다");
						new MainPage();
						dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//다른것을 선택하면 취소
				else {
					JOptionPane.showMessageDialog(null, "계속 이용해주셔서 감사합니다, 잘 부탁드리겠습니다", null, 0);
				}
			}
		});
		btnNewButton_1.setBounds(65, 350, 142, 45);
		getContentPane().add(btnNewButton_1);
		
		//게시판버튼
		JButton button = new JButton("게시판");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//게시판 메인화면 실행
					new MainBoard();
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(65, 405, 142, 45);
		getContentPane().add(button);
		
		//주문하러가기버튼 --이어지는곳
		JButton button_1 = new JButton("주문하러가기");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//휘령씨 part -->
			}
		});
		button_1.setBounds(152, 521, 142, 45);
		getContentPane().add(button_1);
		
		setVisible(true);
	}
}
