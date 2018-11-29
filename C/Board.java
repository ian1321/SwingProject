package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JFrame {
	// 변수설정
	JTextField textField; // 제목입력 텍스트필드
	JTextPane textPane; // 내용입력 텍스트필드

	
	

	public void setTextField(Object o) {
		this.textField.setText((String) o);;
	}

	public void setTextPane(Object o) {
		this.textPane.setText((String) o);
	}

	public Board() {
		// 프레임설정
		
		setTitle("È¸¿ø°¡ÀÔ");
		setSize(505, 647);
		getContentPane().setLayout(null);

		// 요기요 상단 레이블
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\001.png"));
		label.setBounds(0, 0, 489, 74);
		getContentPane().add(label);

		// 요기요 로고 레이블
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MyPage();
				dispose();
			}
		});
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 13, 106, 45);
		getContentPane().add(label_1);

		// 제목 레이블
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setBounds(12, 94, 57, 15);
		getContentPane().add(lblNewLabel);

		// 글내용 레이블
		JLabel lblNewLabel_2 = new JLabel("\uAE00 \uB0B4\uC6A9");
		lblNewLabel_2.setBounds(12, 167, 57, 15);
		getContentPane().add(lblNewLabel_2);

		// 제목 입력할 텍스트필드
		textField = new JTextField();
		textField.setBounds(12, 119, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		// 내용 입력되는 텍스트페인
		textPane = new JTextPane();
		textPane.setBounds(12, 195, 448, 292);
		getContentPane().add(textPane);

		//글 저장 버튼
		JButton btnNewButton = new JButton("글 저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (new BoardDAO().countMyRow(textField.getText()) >0) {
						BoardDTO dto = new BoardDTO();
						new BoardDTO();
						dto.setNumber(Integer.parseInt(BoardDTO.Upnumber));
						dto.setTitle(textField.getText());
						dto.setId(MemberDTO.SessionId);
						dto.setContent(textPane.getText());
						new BoardDAO().update(dto);
						JOptionPane.showMessageDialog(null, "글수정을 완료했습니다. 수정합니다", "알림", 0);
						new MainBoard();
						dispose();
					}
					//DAO.create 실행후 제목, 실행중ID, 텍스트내용 - 텍스트페인 dto개체로 전송
					else {
						BoardDTO dto = new BoardDTO();
						dto.setTitle(textField.getText());
						dto.setId( MemberDTO.SessionId);
						dto.setContent(textPane.getText());
						new BoardDAO().create(dto);
						dispose(); //창닫기
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(174, 552, 97, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
	}

	
}
