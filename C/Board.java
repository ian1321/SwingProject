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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JComboBox;

public class Board extends JFrame {
	// 변수설정
	JTextField textField; // 제목입력 텍스트필드
	JTextPane textPane; // 내용입력 텍스트필드

	public void setTextField(Object o) {
		this.textField.setText((String) o);
		;
	}

	public void setTextPane(Object o) {
		this.textPane.setText((String) o);
	}

	public Board() {
		// 프레임설정

		setTitle("리뷰");
		setSize(1148, 652);
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

		// 글 저장 버튼
		JButton btnNewButton = new JButton("글 저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// 글 번호를 검색해서 글이 있다면 - 수정
					if (new BoardDAO().countMyRow(BoardDTO.Upnumber) > 0) {
						BoardDTO dto = new BoardDTO();
						new BoardDTO();
						dto.setNumber(Integer.parseUnsignedInt(BoardDTO.Upnumber));
						dto.setTitle(textField.getText());
						dto.setId(MemberDTO.SessionId);
						dto.setContent(textPane.getText());
						new BoardDAO().update(dto);
						JOptionPane.showMessageDialog(null, "글수정을 완료했습니다. 수정합니다");
						BoardDTO.Upnumber = 0 + "";
						new MainBoard();
						dispose();
					}
					// DAO.create 실행후 제목, 실행중ID, 텍스트내용 - 텍스트페인 dto개체로 전송
					// 글번호를 검색해서 글이 없으면 작성
					else if (new BoardDAO().countMyRow(BoardDTO.Upnumber) == 0) {
						BoardDTO dto = new BoardDTO();
						dto.setTitle(textField.getText());
						dto.setId(MemberDTO.SessionId);
						dto.setContent(textPane.getText());
						new BoardDAO().create(dto);
						JOptionPane.showMessageDialog(null, "글을 작성했습니다.");
						new MainBoard();
						dispose(); // 창닫기
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(174, 552, 97, 23);
		getContentPane().add(btnNewButton);

		/* 종류레이블 */
		JLabel lblNewLabel_1 = new JLabel("종류");
		lblNewLabel_1.setBounds(174, 86, 40, 18);
		getContentPane().add(lblNewLabel_1);

		/* 음식점콤보박스 */
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(280, 118, 86, 24);
		getContentPane().add(comboBox_1);

		/* 종류콤보 */
		String[] sortC = { "치킨", "피자", "중식" };
		String[] restC = { "", "", "" };
		String[] menuC = new String[3];
		JComboBox comboBox = new JComboBox(sortC);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				int index = cb.getSelectedIndex();
				ArrayList arr;
				ArrayList arr2 = null;
				int count = 0;
			
				try {
					switch (index) {
					case 0:
						arr = new FoodListDAO().selectColumn("sort", "chicken");
						for (int i = 0; i < arr.size(); i++) {
							System.out.println(arr.get(i));
						}
						for (int i = 0; i < arr.size()/4; i++) {
							if (!restC[count].equals((arr.get(i*4 + 1)))) {
								arr2 = (ArrayList) arr.get(i*4 + 1);
								if (count < 2)
									count++;
							}
						}
						 HashSet<String> distinctData = new HashSet<String>(arr2);
				            arr = new ArrayList<String>(distinctData);
						restC[0] = (String) arr.get(0);
						restC[1] = (String) arr.get(1);
						restC[2] = (String) arr.get(2);
						for (int j = 0; j < restC.length; j++) {
							System.out.println("rest" + j + "번째 : " + restC[j]);
						}

						System.out.printf(restC[0], restC[1], restC[2]);

						break;
					case 1:
						// arr = new FoodListDAO().selectSort("sort","pizza");
						break;
					case 2:
						// arr = new FoodListDAO().selectSort("sort","chinese");
						break;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		comboBox.setBounds(174, 118, 92, 24);
		getContentPane().add(comboBox);

		/* 음식점 */
		JLabel label_2 = new JLabel("음식점");
		label_2.setBounds(280, 86, 62, 18);
		getContentPane().add(label_2);

		/* 메뉴콤보박스 */
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(380, 118, 80, 24);
		getContentPane().add(comboBox_2);

		/* 메뉴레이블 */
		JLabel label_3 = new JLabel("메뉴");
		label_3.setBounds(380, 86, 80, 18);
		getContentPane().add(label_3);
		setVisible(true);
	}
}
