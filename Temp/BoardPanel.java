package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import swing2.FoodListDAO;
import swing2.FoodListDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;

public class BoardPanel extends JPanel {
	/* 변수 */
	private JTextField showTitleTextField;
	JComboBox<String> sortComboBox; // 종류
	JComboBox<Object> restComboBox; // 레스토랑
	JComboBox<Object> menuComboBox; // 메뉴
	int indexCount = 0; // 콤보박스 한번에 안뜨게하기위한 변수

	public BoardPanel() throws Exception {
		/* 프레임 */
		setBounds(0, 0, 487, 592);
		setLayout(null);

		/* 테이블값 */
		String columnNames[] = { "", "", "", "" };
		/* board DB 값가져오기 */
		ArrayList<BoardDTO> arr = new BoardDAO().select();
		BoardDTO dto = new BoardDTO();
		Object rowData[][] = new Object[arr.size()][4]; // count값으로 Object2차배열 값 설정
		/* 배열값 입력 */
		for (int i = 0; i < arr.size(); i++) {
			dto = arr.get(i);
			rowData[i][0] = dto.getNumber();
			rowData[i][1] = dto.getTitle();
			rowData[i][2] = dto.getId();
			rowData[i][3] = dto.getFmenu();
		}
		JTable jTable = new JTable();
		/* 클릭하면 해당글제목 띄우기 */
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sRow = jTable.getSelectedRow();
				String rowTitle = (String) jTable.getValueAt(sRow, 1);
				showTitleTextField.setText(rowTitle);
			}
		});
		/* 테이블길이 */
		jTable.setModel(new DefaultTableModel(rowData, columnNames));
		jTable.getColumnModel().getColumn(0).setResizable(false);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(34);
		jTable.getColumnModel().getColumn(1).setResizable(false);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(334);
		jTable.getColumnModel().getColumn(2).setResizable(false);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		jTable.getColumnModel().getColumn(3).setResizable(false);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(63);

		/* 전체글보기 */
		JLabel AllListLabel = new JLabel("");
		AllListLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuComboBox.removeAllItems();
				restComboBox.removeAllItems();
				try {
					/* 배열생성 */
					String columnNames[] = { "", "", "", "" };
					ArrayList<BoardDTO> arr;
					arr = new BoardDAO().select();
					BoardDTO dto = new BoardDTO();
					Object rowData[][] = new Object[arr.size()][4]; // count값으로 Object2차배열 값 설정

					for (int i = 0; i < arr.size(); i++) {
						dto = arr.get(i);
						rowData[i][0] = dto.getNumber();
						rowData[i][1] = dto.getTitle();
						rowData[i][2] = dto.getId();
						rowData[i][3] = dto.getFmenu();
					}
					JTable jTable = new JTable();
					jTable.setModel(new DefaultTableModel(rowData, columnNames));
					jTable.getColumnModel().getColumn(0).setResizable(false);
					jTable.getColumnModel().getColumn(0).setPreferredWidth(34);
					jTable.getColumnModel().getColumn(1).setResizable(false);
					jTable.getColumnModel().getColumn(1).setPreferredWidth(334);
					jTable.getColumnModel().getColumn(2).setResizable(false);
					jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
					jTable.getColumnModel().getColumn(3).setResizable(false);
					jTable.getColumnModel().getColumn(3).setPreferredWidth(63);
					JScrollPane scrollPane = new JScrollPane(jTable);
					scrollPane.setBounds(8, 167, 467, 325);
					add(scrollPane);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/* 글삭제 */
		JLabel deleteNewLabel = new JLabel("");
		deleteNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent del) {
				/* 선택하기 */
				int sRow = jTable.getSelectedRow();
				/* 선택한행의 아이디 */
				String rowId = (String) jTable.getValueAt(sRow, 2);
				/* 아이디널값, 혹은 같지 않을떄 */
				if (MemberDTO.SessionId.equals(null) || !MemberDTO.SessionId.equals(rowId)) {
					JOptionPane.showMessageDialog(null, "내 글이 아닙니다", "알림", 0);
					/* 아이디가 같을때 */
				} else if (MemberDTO.SessionId.equals(rowId)) {
					/* 삭제확인 */
					int option = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
					/* 예를누르면 */
					if (option == 0) {
						try {
							/* 선택한 행 삭제 */
							new BoardDAO().delete(jTable.getValueAt(sRow, 0));
							JOptionPane.showMessageDialog(null, "글이 삭제되었습니다.", "알림", 0);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				} else {
					/* 삭제안하기 */
					JOptionPane.showMessageDialog(null, "삭제 안하기를 선택하셨습니다.", "알림", 0);
				}
			}
		}

		);

		/*수정*/
		JLabel correctingLabel = new JLabel("");
		correctingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent upt) {
				// 선택한 값의 id가져오기
				int sRow = jTable.getSelectedRow();
				String rowId = (String) jTable.getValueAt(sRow, 2);
				// 선택한 값과 로그인한 아이디가 같지 않으면
				if (MemberDTO.SessionId == null || !MemberDTO.SessionId.equals(rowId)) {
					JOptionPane.showMessageDialog(null, "내 글이 아닙니다", "알림", 0);
					// 아이디가 작성자 아이디와 같을때 수정
				} else if (MemberDTO.SessionId.equals(rowId)) {
					BoardDTO bDto = new BoardDTO();
					try {
						bDto = new BoardDAO().recall(jTable.getValueAt(sRow, 0));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					/*수정할값 BoardWritePanel에 불러오기*/
					BoardWritePanel boardWritePanel = new BoardWritePanel();
					boardWritePanel.menuComboBox.addItem(bDto.getFmenu());
					boardWritePanel.titleTextField.setText(bDto.getTitle());
					boardWritePanel.textPane.setText(bDto.getContent());
					/*setvisible*/
					MainPage.mainPanel.add(boardWritePanel);
					boardWritePanel.setVisible(true);
					setVisible(false);
				}
			}
		});
		/*글쓰기*/
		JLabel writeLabel = new JLabel("");
		writeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent write) {
				/*글쓰기panel*/
				BoardWritePanel boardWritePanel = new BoardWritePanel();
				MainPage.mainPanel.add(boardWritePanel);
				boardWritePanel.setVisible(true);
				setVisible(false);
			}
		});
		/*종료*/
		JButton btnNewButton = new JButton("X");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				MainPage.mainPagePanel.setVisible(true);
			}
		});
		
		/*위치설정*/
		btnNewButton.setBounds(421, 0, 54, 27);
		add(btnNewButton);
		writeLabel.setBounds(8, 499, 92, 52);
		add(writeLabel);
		correctingLabel.setBounds(137, 499, 92, 52);
		add(correctingLabel);
		deleteNewLabel.setBounds(264, 499, 92, 52);
		add(deleteNewLabel);
		AllListLabel.setBounds(380, 499, 95, 52);
		add(AllListLabel);
		/*스크롤 - jTable*/
		JScrollPane scrollPane = new JScrollPane(jTable);
		scrollPane.setBounds(8, 167, 467, 325);
		add(scrollPane);

		/*콤보박스*/
		String[] loc = { "서울", "경기", "강원" };
		JComboBox<Object> locComboBox = new JComboBox<Object>(loc);
		locComboBox.setBounds(91, 56, 92, 24);
		add(locComboBox);
		
		menuComboBox = new JComboBox<Object>();
		menuComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent menu) {
				try {
					ArrayList<BoardDTO> arr1 = new BoardDAO().selectMine("fmenu", menuComboBox.getSelectedItem());
					Object[][] rowData1 = new Object[arr1.size()][4]; // 테이블 row에 넣을 Object2차 배열
					BoardDTO dto = new BoardDTO();
					for (int i = 0; i < arr1.size(); i++) {
						dto = arr1.get(i);
						rowData1[i][0] = dto.getNumber();
						rowData1[i][1] = dto.getTitle();
						rowData1[i][2] = dto.getId();
						rowData1[i][3] = dto.getFmenu();
					}
					jTable.setModel(new DefaultTableModel(rowData1, columnNames));
					jTable.getColumnModel().getColumn(0).setResizable(false);
					jTable.getColumnModel().getColumn(0).setPreferredWidth(34);
					jTable.getColumnModel().getColumn(1).setResizable(false);
					jTable.getColumnModel().getColumn(1).setPreferredWidth(334);
					jTable.getColumnModel().getColumn(2).setResizable(false);
					jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
					jTable.getColumnModel().getColumn(3).setResizable(false);
					jTable.getColumnModel().getColumn(3).setPreferredWidth(63);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuComboBox.setBounds(395, 56, 80, 24);
		add(menuComboBox);

		restComboBox = new JComboBox();
		restComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent rest) {
				JComboBox cb1 = (JComboBox) rest.getSource();
				int index = cb1.getSelectedIndex();
				ArrayList arr1;
				String[] menuC;

				FoodListDTO dto1 = new FoodListDTO();
				if (indexCount == 0) {
					indexCount++;
				} else {
					try {
						switch (index) {
						case 0:

							arr1 = new FoodListDAO().selectColumn("rest", restComboBox.getSelectedItem());
							for (int i = 0; i < arr1.size(); i++) {
								dto1 = (FoodListDTO) arr1.get(i);
								menuComboBox.addItem(dto1.getMenu());
							}
							break;
						case 1:
							arr1 = new FoodListDAO().selectColumn("rest", restComboBox.getSelectedItem());
							for (int i = 0; i < arr1.size(); i++) {
								dto1 = (FoodListDTO) arr1.get(i);
								menuComboBox.addItem(dto1.getMenu());
							}
							break;
						case 2:
							arr1 = new FoodListDAO().selectColumn("rest", restComboBox.getSelectedItem());
							for (int i = 0; i < arr1.size(); i++) {
								dto1 = (FoodListDTO) arr1.get(i);
								menuComboBox.addItem(dto1.getMenu());
							}
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		restComboBox.setBounds(297, 56, 86, 24);
		add(restComboBox);

		String[] sortC = { "치킨", "피자", "중식" };
		sortComboBox = new JComboBox(sortC);
		sortComboBox.setBounds(193, 56, 92, 24);
		add(sortComboBox);

		sortComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent sort) {
				JComboBox cb = (JComboBox) sort.getSource();
				int index = cb.getSelectedIndex();
				ArrayList arr;
				String[] restC;

				HashSet hs = new HashSet<>();
				FoodListDTO dto;

				try {
					switch (index) {
					case 0:
						arr = new FoodListDAO().selectColumn("sort", "chicken");

						for (int i = 0; i < arr.size(); i++) {
							dto = new FoodListDTO();
							dto = (FoodListDTO) arr.get(i);

							hs.add(dto.getRest());
						}
						arr.clear();
						arr.addAll(hs);
						for (int j = 0; j < arr.size(); j++) {
							restComboBox.addItem(arr.get(j));
						}
						break;
					case 1:
						arr = new FoodListDAO().selectColumn("sort", "pizza");
						for (int i = 0; i < arr.size(); i++) {
							dto = new FoodListDTO();
							dto = (FoodListDTO) arr.get(i);

							hs.add(dto.getRest());
						}
						arr.clear();
						arr.addAll(hs);
						for (int j = 0; j < arr.size(); j++) {
							restComboBox.addItem(arr.get(j));
						}
						break;
					case 2:
						arr = new FoodListDAO().selectColumn("sort", "chinese");
						for (int i = 0; i < arr.size(); i++) {
							dto = new FoodListDTO();
							dto = (FoodListDTO) arr.get(i);

							hs.add(dto.getRest());
						}
						arr.clear();
						arr.addAll(hs);
						for (int j = 0; j < arr.size(); j++) {
							restComboBox.addItem(arr.get(j));
						}
						break;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		showTitleTextField = new JTextField();
		showTitleTextField.setEditable(false);
		showTitleTextField.setBounds(76, 90, 399, 34);
		add(showTitleTextField);
		showTitleTextField.setColumns(10);

		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(BoardPanel.class.getResource("/image/\uB9AC\uBDF0\uD328\uB110.png")));
		backgroundLabel.setBounds(0, 0, 487, 592);
		add(backgroundLabel);

		JLabel editLabel = new JLabel("");
	
		editLabel.setBounds(135, 499, 92, 52);
		add(editLabel);

	}

	// comboAdd는 foodListDAO에 들어가지 않는다. dao는 연결만
	public String[] comboAdd(ArrayList arr, int column) {
		Object dto = new FoodListDTO();
		String[] restC = { "", "", "" };
		int count = 0;
		HashSet distinctData = new HashSet();
		for (int i = 0; i < arr.size(); i++) {
			dto = arr.get(i);
		}

		/*
		 * for (int i = 0; i < arr.size() / 4; i++) { if
		 * (!restC[count].equals((arr.get(i)))) { distinctData.add(arr.get(i * 4 +
		 * column)); if (count < 2) count++; } }
		 */
		arr = new ArrayList(distinctData);
		for (int j = 0; j < arr.size(); j++) {
			restC[j] = (String) arr.get(j);
		}
		return restC;
	}
}
