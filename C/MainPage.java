/*경로
 * MainPage => SignUpPanel
 * 개요
 * 정보를 입력받아 DB에 값 저장
 * 사용DB
 * Delivery - member Table
 * */

package swing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.xml.crypto.dsig.SignatureProperties;

import com.teamdev.jxbrowser.chromium.Browser;

import swing2.OrderMain;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.SystemColor;

import swing2.FoodListDAO;
import swing2.FoodListDTO;

public class MainPage extends JFrame implements ActionListener {
	/* 텍스트필드 */
	static JTextField textField; // ID입력
	private JPasswordField passwordField; // pw입력

	/* 레이블 */
	private JLabel idInputPanelLabel;
	private JLabel mainLabel;
	private JLabel loginButtonLabel;
	private JLabel SignUpButtonLabel;
	private JLabel mainSubPanelLabel;
	private JLabel boardLabel;
	private JLabel rightLabel;
	private JLabel leftLabel;

	/* 패널 */
	static JPanel idInputPanel;
	static JPanel mainPagePanel;
	static JPanel subPanel;
	static JPanel subPanel2;
	static JPanel mainSubPanel2;
	static JPanel useTermPanel;
	static JPanel mainSubPanel;
	static JPanel mainPanel;
	static JPanel idPanel;
	static JPanel infoTermPanel;

	/* 사용자패널 */
	static SignUpPanel signUpPanel;
	static UpdatePanel updatePanel;
	static public LoginPanel loginPanel;
	static BoardPanel boardPanel;
	JLabel show1Label;
	JLabel show2Label;
	JLabel show3Label;

	/* textPane */
	private JTextPane useTermTextPane;
	private JTextPane infoTermTextPane;
	private JScrollPane infoTermScrollPane;
	private JScrollPane useTermScrollPane;

	/* 변수 */
	int count = 1;

	/* 메인페이지 */
	public MainPage() throws Exception {

		/* 프레임설정 */
		setTitle("메인페이지");
		setSize(1318, 754);
		getContentPane().setLayout(null);

		/*-----------------------서브패널2 설정-----------------------*/
		/* 서브패널2 */
		subPanel2 = new JPanel();
		subPanel2.setBounds(800, 454, 470, 240);
		getContentPane().add(subPanel2);
		subPanel2.setLayout(null);
		/* sub2 - 첫번째패널 */
		mainSubPanel2 = new JPanel();
		mainSubPanel2.setBounds(0, 0, 470, 240);
		subPanel2.add(mainSubPanel2);
		mainSubPanel2.setLayout(null);
		/* sub2 - 첫번째패널레이블 */
		JLabel mainSubPanel2Label = new JLabel("");
		mainSubPanel2Label.setBounds(0, 0, 470, 240);
		mainSubPanel2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/subPanel2Image.png")));
		mainSubPanel2.add(mainSubPanel2Label);

		/*-----------------------아이디패널 설정-----------------------*/
		idPanel = new JPanel();
		idPanel.setBounds(0, 76, 248, 139);
		getContentPane().add(idPanel);
		idPanel.setLayout(null);
		/* id - 입력패널 레이블 */
		idInputPanel = new JPanel();
		idInputPanel.setBounds(0, 0, 248, 139);
		idPanel.add(idInputPanel);
		idInputPanel.setLayout(null);
		/* id - 로그인버튼레이블 */
		loginButtonLabel = new JLabel("");
		loginButtonLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/* 로그인 */
				try {
					/* id,pw공백일 경우 */
					if (textField.getText().equals("") || passwordField.getText().equals("")
							|| textField.getText().equals("  아이디") || passwordField.equals("  패스워드")) {
						JOptionPane.showMessageDialog(null, "항목을 모두 입력해주세요");
						/* pw값 틀릴때 */
					} else if (new MemberDAO().select(textField.getText(), passwordField.getText()) == false) {
						JOptionPane.showMessageDialog(null, "아이디,패스워드를 확인하세요", "로그인 오류", 0);
						/* 정상 로그인 */
					} else if (new MemberDAO().select(textField.getText(), passwordField.getText()) == true) {
						MemberDTO.SessionId = textField.getText(); // SessionId를 넘겨줌

						/*주문기록 가져올 아이디생성*/
						File file = new File(MemberDTO.SessionId+".txt");
						if(!file.exists()) {
						FileWriter fw = new FileWriter(file);
						fw.write(MemberDTO.SessionId + "님의 주문기록\r\n");
						fw.flush();
						fw.close();
						}
						/* Collaborative Filtering */
						int[] sort = new int[3];
						ArrayList<FoodListDTO> arr = new FileReadOrder().history(MemberDTO.SessionId);
						FoodListDTO fDto = new FoodListDTO();
						for (int i = 0; i < arr.size(); i++) {
							fDto = arr.get(i);
							switch (fDto.getSort()) {
							case "chicken":
								sort[0]++;
								break;
							case "pizza":
								sort[1]++;
								break;
							case "chinese":
								sort[2]++;
								break;
							}
						}
						/* 대표메뉴 */
						String orderhistory = "onion";
						if (sort[0] >= sort[1] && sort[0] >= sort[2]) {
							orderhistory = "deepfried";
						}
						if (sort[1] >= sort[0] && sort[1] >= sort[2]) {
							orderhistory = "crab";
						}
						if (sort[2] >= sort[0] && sort[2] >= sort[1]) {
							orderhistory = "jajang";
						}
						/* 넘길 배열값 설정 */
						String recommend[] = new RecommenderSystem().recommendScore(orderhistory);
						MenuPanel[] mP = new MenuPanel[2];
						mP[0] = new MenuPanel(recommend[0]);
						mP[1] = new MenuPanel(recommend[1]);

						/* content based filtering */
						ArrayList<RecommenderDTO> arr2 = new RecommenderSystem().recommendType(orderhistory);
						RecommenderDTO rDto = new RecommenderDTO();
						Random random = new Random();

						MenuPanel[] cP = new MenuPanel[2];
						cP[0] = new MenuPanel(arr2.get(random.nextInt(arr2.size())).getFMenu());
						cP[1] = new MenuPanel(arr2.get(random.nextInt(arr2.size())).getFMenu());

						/* 추천패널 */
						RecommenderPanel rP = new RecommenderPanel(mP, cP);
						subPanel2.add(rP);
						rP.setBounds(0, 0, 470, 240);
						rP.setVisible(true);

						loginPanel = new LoginPanel();
						idPanel.add(loginPanel);
						/* 약관패널끄기 */
						infoTermPanel.setVisible(false);
						useTermPanel.setVisible(false);
						idInputPanel.setVisible(false);
						mainSubPanel.setVisible(false);
						mainSubPanel2.setVisible(false);

						subPanel.remove(infoTermPanel);
						subPanel.remove(useTermPanel);
						mainPanel.remove(signUpPanel);

						loginPanel.setVisible(true);
						mainPagePanel.setVisible(true);

						MyPagePanel myPagePanel = new MyPagePanel();
						subPanel.add(myPagePanel);
						myPagePanel.setVisible(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		lblPw.setBounds(24, 57, 45, 18);
		idInputPanel.add(lblPw);
		
		JLabel id = new JLabel("ID");
		id.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
		id.setForeground(Color.WHITE);
		id.setBounds(24, 17, 45, 18);
		idInputPanel.add(id);
		loginButtonLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/로그인버튼.png")));
		loginButtonLabel.setBounds(24, 97, 89, 32);
		idInputPanel.add(loginButtonLabel);

		/* id - 회원가입 버튼 레이블 */
		SignUpButtonLabel = new JLabel("");
		SignUpButtonLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* 회원가입 */
				signUpPanel.setVisible(true);
				mainPagePanel.setVisible(false);
			}
		});
		SignUpButtonLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/회원가입버튼.png")));
		SignUpButtonLabel.setBounds(125, 97, 99, 34);
		idInputPanel.add(SignUpButtonLabel);
		/* id - 아이디입력 텍스트필드 */
		textField = new JTextField();
		textField.setBounds(87, 13, 149, 28);
		idInputPanel.add(textField);
		textField.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(null);
				textField.setForeground(Color.black);
			}
		});
		textField.setForeground(Color.LIGHT_GRAY);
		/* 아이디설정 */
		textField.setText("");
		/* id - 비밀번호 입력 텍스트필드 */
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 53, 149, 32);
		passwordField.setText("");
		idInputPanel.add(passwordField);

		/* id - 배경레이블 */
		idInputPanelLabel = new JLabel("");
		idInputPanelLabel
				.setIcon(new ImageIcon(MainPage.class.getResource("/image/\uB85C\uADF8\uC778\uD328\uB110.png")));
		idInputPanelLabel.setBounds(0, 0, 248, 139);
		idInputPanel.add(idInputPanelLabel);

		/*-----------------------메인패널 설정-----------------------*/
		/* 메인패널 */
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(270, 93, 500, 600);
		getContentPane().add(mainPanel);
		/* 메인 - 첫번째 패널 */
		mainPagePanel = new JPanel();
		mainPagePanel.setBounds(0, 0, 500, 600);
		mainPagePanel.setLayout(null);
		mainPanel.add(mainPagePanel);
		/* 메인 - 오른쪽으로 그림이동 */
		rightLabel = new JLabel("");
		rightLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* count에 변하여 .PNG로 이미지 세팅 */
				if (count < 4) {
					count++;
				} else if (count == 4) {
					count = 1;
				}
				switch (count) {
				case 1:
					show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + count + ".PNG")));
					show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count + 1) + ".PNG")));
					show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count + 3) + ".PNG")));
					break;
				case 2:
				case 3:
					show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + count + ".PNG")));
					show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count + 1) + ".PNG")));
					show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count - 1) + ".PNG")));
					break;
				case 4:
					show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + count + ".PNG")));
					show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count - 3) + ".PNG")));
					show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count - 1) + ".PNG")));
					break;
				}
			}
		});
		/* 메인 - 왼쪽으로 그림이동 */
		leftLabel = new JLabel("");
		leftLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (count < 4) {
					count++;
				} else if (count == 4) {
					count = 1;
				}
				switch (count) {
				case 1:
					show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + count + ".PNG")));
					show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count + 3) + ".PNG")));
					show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count + 1) + ".PNG")));
					break;
				case 2:
				case 3:
					show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + count + ".PNG")));
					show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count - 1) + ".PNG")));
					show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count + 1) + ".PNG")));
					break;
				case 4:
					show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + count + ".PNG")));
					show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count - 1) + ".PNG")));
					show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M" + (count - 3) + ".PNG")));
					break;
				}
			}
		});
		leftLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/black-linen.png")));
		leftLabel.setBounds(14, 75, 52, 210);
		mainPagePanel.add(leftLabel);
		rightLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/black-linen.png")));
		rightLabel.setBounds(228, 75, 52, 210);
		mainPagePanel.add(rightLabel);
		/* 메인 - 주문하기이미지 */
		JLabel mainLabel2 = new JLabel();
		mainLabel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (MemberDTO.SessionId == null) {
					JOptionPane.showMessageDialog(null, "로그인을 해주세요");
				} else {
					new OrderMain();
					dispose();
				}
			}
		});
		mainLabel2.setIcon(new ImageIcon(MainPage.class.getResource("/image/Main" + "PanelImage.jpg")));
		mainLabel2.setBounds(10, 310, 480, 280);
		mainPagePanel.add(mainLabel2);
		/* 메인 - 왼쪽이미지 */
		show1Label = new JLabel("");
		show1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		show1Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M4.PNG")));
		show1Label.setBounds(14, 75, 52, 210);
		mainPagePanel.add(show1Label);
		/* 메인 - 중간이미지 */
		show2Label = new JLabel("");
		show2Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M1.PNG")));
		show2Label.setBackground(Color.GRAY);
		show2Label.setBounds(72, 75, 150, 210);
		mainPagePanel.add(show2Label);
		/* 메인 - 오른쪽이미지 */
		show3Label = new JLabel("");
		show3Label.setBackground(Color.WHITE);
		show3Label.setIcon(new ImageIcon(MainPage.class.getResource("/image/M2.PNG")));
		show3Label.setBounds(228, 75, 52, 210);
		mainPagePanel.add(show3Label);
		/* 메인 - 메인백그라운드이미지 */
		mainLabel = new JLabel("");
		mainLabel.setBounds(0, 0, 500, 600);
		mainPagePanel.add(mainLabel);
		mainLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/메인패널.png")));
		/* 메인 - 리뷰패널 */
		boardPanel = new BoardPanel();
		boardPanel.setSize(500, 600);
		mainPanel.add(boardPanel);
		boardPanel.setVisible(false);
		/* 메인 - 회원가입패널 */
		signUpPanel = new SignUpPanel();
		signUpPanel.setBounds(0, 0, 500, 600);
		mainPanel.add(signUpPanel);
		signUpPanel.setVisible(false);

		/*-----------------------서브패널 설정-----------------------*/
		/* 서브1패널 */
		subPanel = new JPanel();
		subPanel.setBounds(800, 95, 470, 340);
		getContentPane().add(subPanel);
		subPanel.setLayout(null);
		/* 서브1 - 첫번째패널 */
		mainSubPanel = new JPanel();
		mainSubPanel.setBounds(0, 0, 470, 340);
		subPanel.add(mainSubPanel);
		mainSubPanel.setLayout(null);
		/* 서브1 - 첫번째레이블 */
		mainSubPanelLabel = new JLabel("\uBA54\uC778\uC11C\uBE0C\uD328\uB110\uD398\uC774\uC9C0");
		mainSubPanelLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/MainSubPanelImage.jpg")));
		mainSubPanelLabel.setBounds(0, 0, 470, 340);
		mainSubPanel.add(mainSubPanelLabel);
		/* 이용약관패널 */
		useTermPanel = new JPanel();
		useTermPanel.setBounds(0, 0, 470, 340);
		subPanel.add(useTermPanel);
		useTermPanel.setVisible(false);
		useTermPanel.setLayout(null);
		/* 이용약관 textPane */
		useTermTextPane = new JTextPane();
		useTermTextPane.setBounds(0, 0, 458, 411);
		useTermTextPane.setText("요기요 이용약관\r\n" + "제1조 (목적)\r\n"
				+ "본 약관은 유한회사 딜리버리히어로코리아(이하 “회사”라 함)가 운영하는 \"요기요\" 사이트(www.yogiyo.co.kr) 또는 “요기요” 모바일 애플리케이션에서 제공하는 서비스(이하 \"서비스\"라 함)를 이용함에 있어 ”요기요”와 이용자의 권리, 의무 및 책임 사항을 규정함을 목적으로 합니다.\r\n"
				+ "제2조 (정의)\r\n"
				+ "① \"요기요\"란 회사가 “서비스”를 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 배달음식 또는 용역을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 ”요기요”를 운영하는 사업자의 의미로도 사용합니다.\r\n"
				+ "② “요기요서비스”란 회사가 운영하는 사이트나 모바일 애플리케이션을 통해 이용자가 원하는 음식을 주문하면, 주문이 완료된 음식을 음식점(이하 “공급자”라 함)이 이용자에게 배달하는 서비스를 기본으로 하되, 맛집배달대행, 테이크아웃 등 “요기요” 사이트 및 모바일 애플리케이션 상의 제공 서비스 전체를 의미합니다.\r\n"
				+ "③ \"이용자\"란 “요기요”에 접속하여 본 약관에 따라 요기요가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.\r\n"
				+ "④ ”회원”이라 함은 “요기요”에 개인정보를 제공하여 회원등록을 한 자로서, “요기요”의 정보를 지속적으로 제공받으며, “요기요”가 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.\r\n"
				+ "⑤ ”비회원”이라 함은 회원에 가입하지 않고 “요기요”가 제공하는 서비스를 이용하는 자를 말합니다.\r\n"
				+ "⑥ “가맹점”이란 회사와 가맹계약을 맺고 정보통신단말기를 설치하여 회사가 운영하는 요기요서비스에서 음식물을 공급하는 사업자를 말하며, 회사의 대리인이나 피용자로 간주되지 아니합니다.\r\n"
				+ "⑦ “쿠폰”이란 요기요서비스에서 명시된 금액 혹은 비율만큼 할인을 받을 수 있는 일련의 숫자 및 영문자로 되어 있는 코드를 말합니다.\r\n"
				+ "⑧ 본 약관에서 정의되지 않은 용어는 관련법령이 정하는 바에 따릅니다.");
		/* 이용스크롤 */
		useTermScrollPane = new JScrollPane(useTermTextPane);
		useTermScrollPane.setBounds(0, 0, 470, 340);
		useTermPanel.add(useTermScrollPane);
		/* 개인정보패널 */
		infoTermPanel = new JPanel();
		infoTermPanel.setBounds(0, 0, 470, 340);
		subPanel.add(infoTermPanel);
		infoTermPanel.setVisible(false);
		infoTermPanel.setLayout(null);
		/* 개인정보텍스트 */
		infoTermTextPane = new JTextPane();
		infoTermTextPane.setBounds(0, 0, 466, 313);
		infoTermTextPane.setText("개인정보 처리방침\r\n" + "유한회사 딜리버리히어로 코리아(이하 ‘회사’라고 합니다)의 개인정보 처리방침은 아래의 내용을 담고 있습니다.\r\n"
				+ "제1조 목적\r\n" + "제2조 수집하는 개인정보의 항목\r\n" + "제3조 개인정보 수집에 대한 동의\r\n" + "제4조 개인정보의 수집 및 이용목적\r\n"
				+ "제5조 쿠키에 의한 개인정보 수집\r\n" + "제6조 목적 외 사용 및 제3자에 대한 제공 및 공유\r\n" + "제7조 개인정보의 열람, 정정\r\n"
				+ "제8조 개인정보 수집, 이용, 제공에 대한 동의철회(회원탈퇴)\r\n" + "제9조 수집하는 개인정보의 보유 및 이용기간\r\n"
				+ "제10조 개인정보보호를 위한 기술 및 관리적 대책\r\n" + "제11조 링크사이트\r\n" + "제12조 개인정보의 위탁처리\r\n" + "제13조 이용자의 권리와 의무\r\n"
				+ "제14조 의견수렴 및 불만처리\r\n" + "제15조 개인정보관리책임자 및 담당자\r\n" + "제16조 아동의 개인정보보호\r\n"
				+ "제17조 고지의 의무제1조 (목적)\r\n"
				+ "① “개인정보”란 생존하는 개인에 관한 정보로서 당해 정보에 포함되어 있는 성명, 주민등록번호 등의 사항에 의하여 당해 개인을 식별할 수 있는 정보(당해 정보 만으로는 특정 개인을 식별할 수 없더라도 다른 정보와 용이하게 결합하여 식별할 수 있는 것을 포함합니다)를 말합니다.\r\n"
				+ "② 회사는 귀하의 개인정보보호를 매우 중요시하며, 『개인정보보호법』, 『정보통신망 이용촉진 및 정보보호에 관한 법률』 상의 개인정보보호규정 및 정보통신부가 제정한 『개인정보보호지침』을 준수하고 있습니다. 회사는 개인정보처리방침을 통하여 귀하께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.\r\n"
				+ "③ 회사는 개인정보처리방침을 홈페이지 첫 화면에 공개함으로써 귀하께서 언제나 용이하게 보실 수 있도록 조치하고 있습니다.\r\n"
				+ "④ 회사는 개인정보처리방침의 지속적인 개선을 위하여 개인정보처리방침을 개정하는데 필요한 절차를 정하고 있습니다. 그리고 개인정보처리방침을 개정하는 경우 버전번호 등을 부여하여 개정된 사항을 귀하께서 쉽게 알아볼 수 있도록 하고 있습니다.");
		/* 개인정보스크롤 */
		infoTermScrollPane = new JScrollPane(infoTermTextPane);
		infoTermScrollPane.setBounds(0, 0, 470, 340);
		infoTermPanel.add(infoTermScrollPane);

		/* 버튼레이블 */
		/* 리뷰버튼 레이블 */
		boardLabel = new JLabel("");
		boardLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPagePanel.setVisible(false);
				boardPanel.setVisible(true);
			}
		});
		/* 홈버튼 레이블 */
		JLabel homeLabel = new JLabel("");
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPagePanel.setVisible(true);
			}
		});
		homeLabel.setBounds(0, 216, 248, 39);
		getContentPane().add(homeLabel);
		boardLabel.setBounds(0, 348, 248, 31);
		getContentPane().add(boardLabel);
		/* 전체배경 레이블 */
		JLabel BackgroundLabel = new JLabel("");
		BackgroundLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/오리지널폼.png")));
		BackgroundLabel.setBounds(0, 0, 1361, 711);
		getContentPane().add(BackgroundLabel);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}