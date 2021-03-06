package swing;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionListener;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

public class BoardWritePanel extends JPanel {
	JTextField titleTextField;
	JLabel starLabel;
	JComboBox restComboBox;
	JComboBox menuComboBox;
	JTextPane textPane;
	JLabel imageLabel;
	JLabel imageLabel2;
	int grade = 0;
	String path = "";
	int indexCount = 0;
	public BoardWritePanel() {
		setBounds(0, 0, 500, 600);
		setLayout(null);

		JLabel star1 = new JLabel("");
		star1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				starLabel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/S1.png")));
				grade = 1;
			}
		});

		imageLabel2 = new JLabel("");
		imageLabel2.setBounds(290, 130, 157, 91);
		add(imageLabel2);

		JLabel star5 = new JLabel("");
		star5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				starLabel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/S5.png")));
				grade = 5;
			}
		});
		star5.setBounds(209, 230, 48, 45);
		add(star5);

		JLabel star4 = new JLabel("");
		star4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				starLabel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/S4.png")));
				grade = 4;
			}
		});
		star4.setBounds(160, 230, 48, 45);
		add(star4);

		JLabel star3 = new JLabel("");
		star3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				starLabel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/S3.png")));
				grade = 3;
			}
		});
		star3.setBounds(110, 230, 48, 45);
		add(star3);

		JLabel star2 = new JLabel("");
		star2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				starLabel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/S2.png")));
				grade = 2;
			}
		});
		star2.setBounds(61, 230, 48, 45);
		add(star2);
		star1.setBounds(13, 230, 48, 45);
		add(star1);

		starLabel = new JLabel("");
		starLabel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/S0.png")));
		starLabel.setBounds(12, 230, 245, 45);
		add(starLabel);

		JButton fileAdd = new JButton("파일추가");
		fileAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(path.equals("")) {
				 path =new FileSource().FileSource();
				 imageLabel.setIcon(new ImageIcon(path));
				} else {
					path =new FileSource().FileSource();
					 imageLabel2.setIcon(new ImageIcon(path));
				}
			}
		});
		fileAdd.setBounds(15, 176, 94, 45);
		add(fileAdd);

		JLabel cancenlLabel = new JLabel("");
		cancenlLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				MainPage.boardPanel.setVisible(true);
			}
		});
		cancenlLabel.setBounds(376, 240, 112, 28);
		add(cancenlLabel);

		JLabel commitLabel = new JLabel("");
		commitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 글 번호를 검색해서 글이 있다면 - 수정
				try {
					if (new BoardDAO().countMyRow(BoardDTO.Upnumber) > 0) {
						BoardDTO dto = new BoardDTO();
						new BoardDTO();
						dto.setNumber(Integer.parseUnsignedInt(BoardDTO.Upnumber));
						dto.setTitle(titleTextField.getText());
						dto.setId(MemberDTO.SessionId);
						dto.setContent(textPane.getText());
						dto.setFmenu((String) menuComboBox.getSelectedItem());
						new BoardDAO().update(dto);
						
						ArrayList<RecommenderDTO> arr=new RecommenderDAO().selectColumn("fMenu", menuComboBox.getSelectedItem());
						RecommenderDTO rDto = new RecommenderDTO();
						rDto=arr.get(0);
						
						new RecommenderDAO().Update((rDto.getCount()*rDto.getScore()+(double)grade)/(rDto.getCount()+1),rDto.getCount(),dto.getFmenu());
						JOptionPane.showMessageDialog(null, "글수정을 완료했습니다. 수정합니다");
						BoardDTO.Upnumber = 0 + "";
						setVisible(false);
						MainPage.boardPanel.setVisible(true);
					}
					// DAO.create 실행후 제목, 실행중ID, 텍스트내용 - 텍스트페인 dto개체로 전송
					// 글번호를 검색해서 글이 없으면 작성
					else if (new BoardDAO().countMyRow(BoardDTO.Upnumber) == 0) {
						BoardDTO dto = new BoardDTO();
						dto.setTitle(titleTextField.getText());
						dto.setId(MemberDTO.SessionId);
						dto.setContent(textPane.getText());
						dto.setFmenu((String) menuComboBox.getSelectedItem());
						new BoardDAO().create(dto);
						
						ArrayList<RecommenderDTO> arr=new RecommenderDAO().selectColumn("fMenu", menuComboBox.getSelectedItem());
						RecommenderDTO rDto = new RecommenderDTO();
						rDto=arr.get(0);
						
						new RecommenderDAO().Update((rDto.getCount()*rDto.getScore()+(double)grade)/(rDto.getCount()+1),rDto.getCount(),dto.getFmenu());
						JOptionPane.showMessageDialog(null, "글을 작성했습니다.");
						setVisible(false);
						MainPage.boardPanel.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		commitLabel.setBounds(258, 240, 112, 28);
		add(commitLabel);

		textPane = new JTextPane();
		textPane.setBounds(14, 281, 472, 307);
		add(textPane);

		imageLabel = new JLabel("");
		imageLabel.setBounds(122, 130, 157, 91);
		add(imageLabel);

		titleTextField = new JTextField();
		titleTextField.setColumns(10);
		titleTextField.setBounds(91, 80, 399, 34);
		add(titleTextField);

		String[] sortC = { "치킨", "피자", "중식" };
		JComboBox sortComboBox = new JComboBox(sortC);
		sortComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
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
		sortComboBox.setBounds(204, 47, 92, 24);
		add(sortComboBox);

		restComboBox = new JComboBox();
		restComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb1 = (JComboBox) e.getSource();
				int index = cb1.getSelectedIndex();
				ArrayList arr1;
				String[] menuC;
				
				FoodListDTO dto1 = new FoodListDTO();
				try {
					if (indexCount == 0) {
						indexCount++;
					} else {
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
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		restComboBox.setBounds(308, 47, 86, 24);
		add(restComboBox);

		menuComboBox = new JComboBox();
		menuComboBox.setBounds(406, 47, 80, 24);
		add(menuComboBox);

		String[] loc = { "서울", "경기", "강원" };
		JComboBox locComboBox = new JComboBox(loc);
		locComboBox.setBounds(102, 47, 92, 24);
		add(locComboBox);

		JLabel backGroundPanel = new JLabel();
		backGroundPanel.setSize(500, 600);
		backGroundPanel.setIcon(new ImageIcon(BoardWritePanel.class.getResource("/image/글쓰기패널.png")));
		add(backGroundPanel);
	}
}
