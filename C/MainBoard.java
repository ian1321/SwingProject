package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MainBoard extends JFrame {
	// 멤버변수
	JTable jTable;

	public MainBoard() throws Exception {
		// 프레임설정
		setTitle("메인보드");
		setSize(505, 647);
		getContentPane().setLayout(null);

		// 요기요상단 레이블
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 489, 74);
		label.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\001.png"));
		getContentPane().add(label);
		
		// 요기요 로고
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MyPage(); // 로고 클릭하면 마이페이지로 이동
				dispose();
			}
		});
		label_1.setBounds(12, 13, 106, 45);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label_1);

		// 게시판 메인 레이블
		JLabel lblNewLabel = new JLabel("게시판메인");
		lblNewLabel.setBounds(10, 84, 77, 15);
		getContentPane().add(lblNewLabel);

		// 게시판 테이블 값 넣을 배열생성
		String columnNames[] = { "No", "제목", "ID", "글 내용" };
		// 행 값에 넣을 데이터 DAO.select값으로 arr배열 반환
		ArrayList arr = new BoardDAO().select();
		// 행수 지정해주기 위해 값이 얼마나 있는지 count int로 값
		int row = new BoardDAO().countRow();
		Object rowData[][] = new Object[row][4]; // count값으로 Object2차배열 값 설정

		// for반복문으로 2차배열에 arr인덱스값 대입
		for (int i = 0; i < rowData.length; i++) {
			for (int j = 0; j < rowData[i].length; j++) {
				rowData[i][j] = arr.get(i * 4 + j);
			}
		}

		// 글쓰기버튼
		JButton btnNewButton = new JButton("글 쓰기");
		btnNewButton.setBounds(37, 137, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Board();
				dispose();
			}
		});
		getContentPane().add(btnNewButton);

		// 글수정버튼
		JButton btnNewButton_1 = new JButton("글 수정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 선택한 값의 id가져오기
				int sRow = jTable.getSelectedRow();
				String rowId = (String) jTable.getValueAt(sRow, 2);
				// 선택한 값과 로그인한 아이디가 같지 않으면
				if (MemberDTO.SessionId.equals(null) || !MemberDTO.SessionId.equals(rowId)) {
					JOptionPane.showMessageDialog(null, "내 글이 아닙니다", "알림", 0);
					// 아이디가 작성자 아이디와 같을때 수정
				} else if (MemberDTO.SessionId.equals(rowId)) {
					//선택한 줄의 넘버 가져오기
					try {
						ArrayList arr =new BoardDAO().recall(jTable.getValueAt(sRow, 0));
						
						Board board = new Board();
						board.textField.setText((String) arr.get(0));
						board.textPane.setText((String) arr.get(1));
						new BoardDTO().Upnumber = (String) arr.get(2);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}
			}
		});
		btnNewButton_1.setBounds(146, 137, 97, 23);
		getContentPane().add(btnNewButton_1);

		// 내글보기 버튼
		JButton btnNewButton_2 = new JButton("내 글보기");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList arr1 = null; // 내가 작성할 글 가져올 ArrayList
				Object[][] rowData1 = null; // 테이블 row에 넣을 Object2차 배열
				try {
					arr1 = new BoardDAO().selectMine(MemberDTO.SessionId); // arr1에 사용중인 아이디값 넣어서 DAO.update
					// rowData배열생성시에 행값에 updateCount값(데이터가 존재하는만큼 카운트) 설정
					rowData1 = new Object[new BoardDAO().sMCount(MemberDTO.SessionId)][4];

					// rowData1에 DAO.update로 받아온 ArrayList값 대입
					for (int i = 0; i < rowData1.length; i++) {
						for (int j = 0; j < rowData1[i].length; j++) {
							rowData1[i][j] = arr1.get(i * 4 + j);
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				// 값 가져와서 행에 출력. 열이름은 그대로출력
				DefaultTableModel model = new DefaultTableModel(rowData1, columnNames);
				// 출력시에 모양 유지
				jTable.setModel(model);
				jTable.getColumnModel().getColumn(0).setPreferredWidth(41);
				jTable.getColumnModel().getColumn(1).setPreferredWidth(123);
				jTable.getColumnModel().getColumn(2).setPreferredWidth(63);
				jTable.getColumnModel().getColumn(3).setPreferredWidth(209);
			}
		});
		btnNewButton_2.setBounds(365, 137, 97, 23);
		getContentPane().add(btnNewButton_2);

		// 글 삭제
		JButton btnNewButton_3 = new JButton("글 삭제");
		btnNewButton_3.addActionListener(new ActionListener() {
			// 삭제하기기능
			public void actionPerformed(ActionEvent e) {
				// 선택한 행 가져오기
				int sRow = jTable.getSelectedRow();
				// 선택한 행의 2번째값 = ID 를 가져오기
				String rowId = (String) jTable.getValueAt(sRow, 2);
				// 아이디가 널값이거나 고른행의 아이디가 내 아이디랑 같지 않을때
				if (MemberDTO.SessionId.equals(null) || !MemberDTO.SessionId.equals(rowId)) {
					JOptionPane.showMessageDialog(null, "내 글이 아닙니다", "알림", 0);
					// 아이디가 작성자 아이디와 같을때 삭제
				} else if (MemberDTO.SessionId.equals(rowId)) {
					// 삭제확인
					int option = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
					if (option == 0) { // 예를 눌렀을때
						try {
							// 선택한 행의 글 넘버 가져와서 DAO.delete
							new BoardDAO().delete(jTable.getValueAt(sRow, 0));
							JOptionPane.showMessageDialog(null, "글이 삭제되었습니다.", "알림", 0);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						// 그 외에는 삭제 하지 않기
						JOptionPane.showMessageDialog(null, "삭제 안하기를 선택하셨습니다.", "알림", 0);
					}
				}
			}
		});
		btnNewButton_3.setBounds(255, 137, 97, 23);
		getContentPane().add(btnNewButton_3);

		// 게시판 목록 테이블
		jTable = new JTable(rowData, columnNames);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(41);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(123);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(63);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(209);
		JScrollPane scroll = new JScrollPane(jTable);
		scroll.setBounds(12, 208, 465, 366);
		getContentPane().add(scroll);

		setVisible(true);
	}
}