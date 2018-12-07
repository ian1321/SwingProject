package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
	private JTextField showTitleTextField;

	public BoardPanel() throws Exception {
		setBounds(0, 0, 487, 592);
		setLayout(null);

		// 게시판 테이블 값 넣을 배열생성
		String columnNames[] = { "", "", "", "" };
		// 행 값에 넣을 데이터 DAO.select값으로 arr배열 반환
		ArrayList arr = new BoardDAO().select();
		// 행수 지정해주기 위해 값이 얼마나 있는지 count int로 값
		int row = new BoardDAO().countRow();
		BoardDTO[] dto = new BoardDTO[row];
		Object rowData[][] = new Object[row][4]; // count값으로 Object2차배열 값 설정
		
		for (int i = 0; i < row; i++) {
			dto[i] = (BoardDTO) arr.get(i);
			rowData[i][0] = dto[i].getNumber();
			rowData[i][1] = dto[i].getTitle();
			rowData[i][2] = dto[i].getId();
			rowData[i][3] = dto[i].getFmenu();
		}
		JTable jTable= new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sRow = jTable.getSelectedRow();
				String rowTitle = (String) jTable.getValueAt(sRow, 1);
				showTitleTextField.setText(rowTitle);
			}
		});
		jTable.setModel(new DefaultTableModel(
			rowData,columnNames
		));
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
		
		String[] loc= {"서울","경기","강원"};
		JComboBox locComboBox = new JComboBox(loc);
		locComboBox.setBounds(95, 56, 86, 24);
		add(locComboBox);

		JComboBox menuComboBox = new JComboBox();
		menuComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				ArrayList arr1 = null; // 내가 작성할 글 가져올 ArrayList
				Object[][] rowData1 = null; // 테이블 row에 넣을 Object2차 배열
				BoardDTO[] dto1; 
				
				try {
					arr1 = new BoardDAO().selectMine("sort",menuComboBox.getSelectedItem()); // arr1에 사용중인 아이디값 넣어서 DAO.update
					// rowData배열생성시에 행값에 updateCount값(데이터가 존재하는만큼 카운트) 설정
					int myRow = new BoardDAO().countMyRow(MemberDTO.SessionId);
					dto1 = new BoardDTO[myRow];
					rowData1 = new Object[myRow][4];
					
					for (int i = 0; i < myRow; i++) {
						dto[i] = (BoardDTO) arr1.get(i);
						rowData[i][0] = dto[i].getNumber();
						rowData[i][1] = dto[i].getTitle();
						rowData[i][2] = dto[i].getId();
						rowData[i][3] = dto[i].getFmenu();
					}
					// rowData1에 DAO.update로 받아온 ArrayList값 대입
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				// 값 가져와서 행에 출력. 열이름은 그대로출력
				DefaultTableModel model = new DefaultTableModel(rowData1, columnNames);
				// 출력시에 모양 유지
				jTable.setModel(model);
				
			}
			
		});
		menuComboBox.setBounds(395, 56, 80, 24);
		add(menuComboBox);

		JComboBox restComboBox = new JComboBox();
		restComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb1 = (JComboBox) e.getSource();
				int index = cb1.getSelectedIndex();
				ArrayList arr1;
				String[] menuC;
				int indexCount = 0;
				try {
					switch (index) {
					case 0:
						if(indexCount == 0) {
							indexCount++;
						}else {
							arr1 = new FoodListDAO().selectColumn("rest",restComboBox.getSelectedItem());
							menuC = new FoodListDAO().comboAdd(arr1,2);
							for (int i = 0; i < menuC.length; i++) {
								menuComboBox.addItem(menuC[i]);
							}
						}
						break;
					case 1:
						arr1 = new FoodListDAO().selectColumn("rest",restComboBox.getSelectedItem());
						menuC = new FoodListDAO().comboAdd(arr1,2);
						for (int i = 0; i < menuC.length; i++) {
							menuComboBox.addItem(menuC[i]);
						}
						break;
					case 2:
						arr1 = new FoodListDAO().selectColumn("rest",restComboBox.getSelectedItem());
						menuC = new FoodListDAO().comboAdd(arr1,2);
						for (int i = 0; i < menuC.length; i++) {
							menuComboBox.addItem(menuC[i]);
						}
						break;
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});
		restComboBox.setBounds(297, 56, 86, 24);
		add(restComboBox);

		String[] sortC = { "치킨", "피자", "중식" };
		JComboBox sortComboBox = new JComboBox(sortC);
		sortComboBox.setBounds(193, 56, 92, 24);
		add(sortComboBox);

		sortComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				int index = cb.getSelectedIndex();
				ArrayList arr;
				String[] restC;
				try {
					switch (index) {
					case 0:
						arr = new FoodListDAO().selectColumn("sort", "chicken");
						restC = new FoodListDAO().comboAdd(arr,1);
						for (int i = 0; i < restC.length; i++) {
							restComboBox.addItem(restC[i]);
						}
						break;
					case 1:
						arr = new FoodListDAO().selectColumn("sort", "pizza");
						restC = new FoodListDAO().comboAdd(arr,1);
						for (int i = 0; i < restC.length; i++) {
							restComboBox.addItem(restC[i]);
						}
						break;

					case 2:
						arr = new FoodListDAO().selectColumn("sort", "chinese");
						restC = new FoodListDAO().comboAdd(arr,1);
						for (int i = 0; i < restC.length; i++) {
							restComboBox.addItem(restC[i]);
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

	}
}
