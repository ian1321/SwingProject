package swing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.xml.crypto.dsig.SignatureProperties;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class MainPage extends JFrame implements ActionListener {
	// 변수설정
	static JTextField textField; // ID입력 텍스트필드
	private JPasswordField passwordField;
	static JPanel idInputPanel;
	private JLabel lblNewLabel_1;

	private JLabel mainLabel;
	private JLabel loginButtonLabel;
	private JLabel SignUpButtonLabel;
	static JPanel mainPagePanel;
	static SignUpPanel signUpPanel;
	static JPanel subPanel;
	static JPanel useTermPanel;
	static JTextPane useTermTextPane;
	JScrollPane useTermScrollPane;
	static JPanel mainSubPanel;
	private JLabel lblNewLabel_2;
	static JPanel infoTermPanel;
	private JTextPane infoTermTextPane;
	private JScrollPane infoTermScrollPane;
	static JPanel idPanel;
	private JLabel boardLabel;
	static JPanel mainPanel;
	static UpdatePanel updatePanel;
	static LoginPanel loginPanel;
	static BoardPanel boardPanel;
	// 메인페이지
	public MainPage() throws Exception {
		// 기본프레임설정
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\yogiyo (1).jpg"));
		setTitle("메인페이지");
		setSize(1318, 754);
		getContentPane().setLayout(null);

		// 아이디패널
		idPanel = new JPanel();
		idPanel.setBounds(0, 76, 248, 139);
		getContentPane().add(idPanel);
		idPanel.setLayout(null);
	

		// 아이디입력패널
		idInputPanel = new JPanel();
		idInputPanel.setBounds(0, 0, 248, 139);
		idPanel.add(idInputPanel);
		idInputPanel.setLayout(null);

		SignUpButtonLabel = new JLabel("");
		SignUpButtonLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 회원가입
				signUpPanel.setVisible(true);
				mainPagePanel.setVisible(false);
			}
		});
		SignUpButtonLabel
				.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\\uD68C\uC6D0\uAC00\uC785\uBC84\uD2BC.png"));
		SignUpButtonLabel.setBounds(125, 97, 99, 34);
		idInputPanel.add(SignUpButtonLabel);

		loginButtonLabel = new JLabel("");
		loginButtonLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 로그인
				try {
					// ID,Pw가 공백일경우에
					if (textField.getText().equals("") || passwordField.getText().equals("")
							|| textField.getText().equals("  아이디") || passwordField.equals("  패스워드")) {
						JOptionPane.showMessageDialog(null, "항목을 모두 입력해주세요");
						// 공백은 아니지만 ID와 PW값이 틀릴때
					} else if (new MemberDAO().select(textField.getText(), passwordField.getText()) == false) {
						JOptionPane.showMessageDialog(null, "아이디,패스워드를 확인하세요", "로그인 오류", 0);
						// 정상적으로 로그인될때
					} else if (new MemberDAO().select(textField.getText(), passwordField.getText()) == true) {
						MemberDTO.SessionId = textField.getText(); // SessionId를 넘겨줌
						loginPanel = new LoginPanel();
						idPanel.add(loginPanel);

						loginPanel.setVisible(true);
						idInputPanel.setVisible(false);
						mainPagePanel.setVisible(true);
						MyPagePanel myPagePanel = new MyPagePanel();
						subPanel.add(myPagePanel);
						myPagePanel.setVisible(true);
						mainSubPanel.setVisible(false);
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		loginButtonLabel
				.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\\uB85C\uADF8\uC778\uBC84\uD2BC.png"));
		loginButtonLabel.setBounds(24, 97, 89, 32);
		idInputPanel.add(loginButtonLabel);

		// ID 입력 텍스트필드
		textField = new JTextField();
		textField.setBounds(87, 13, 149, 28);
		idInputPanel.add(textField);
		textField.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(null); // 클릭하면 공백처리
				textField.setForeground(Color.black);
			}
		});

		textField.setForeground(Color.LIGHT_GRAY);
		textField.setText("  아이디");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(87, 53, 149, 32);
		idInputPanel.add(passwordField);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainPage.class.getResource("/image/\uB85C\uADF8\uC778\uD328\uB110.png")));
		lblNewLabel_1.setBounds(0, 0, 248, 139);
		idInputPanel.add(lblNewLabel_1);

		subPanel = new JPanel();
		subPanel.setBounds(796, 110, 467, 313);
		getContentPane().add(subPanel);
		subPanel.setLayout(null);
		mainSubPanel = new JPanel();
		mainSubPanel.setBounds(0, 0, 466, 312);
		subPanel.add(mainSubPanel);
		mainSubPanel.setLayout(null);

		lblNewLabel_2 = new JLabel("\uBA54\uC778\uC11C\uBE0C\uD328\uB110\uD398\uC774\uC9C0");
		lblNewLabel_2.setBounds(0, 0, 466, 312);
		mainSubPanel.add(lblNewLabel_2);

		infoTermPanel = new JPanel();
		infoTermPanel.setBounds(0, 10, 467, 312);
		subPanel.add(infoTermPanel);
		infoTermPanel.setLayout(null);
		infoTermPanel.setVisible(false);
		infoTermPanel.setBounds(0, 0, 466, 313);

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

		infoTermScrollPane = new JScrollPane(infoTermTextPane);
		infoTermScrollPane.setBounds(0, 0, 466, 313);
		infoTermPanel.add(infoTermScrollPane);

		useTermPanel = new JPanel();
		useTermPanel.setBounds(0, 0, 466, 312);
		subPanel.add(useTermPanel);
		useTermPanel.setLayout(null);
		useTermPanel.setVisible(false);

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

		useTermScrollPane = new JScrollPane(useTermTextPane);
		useTermScrollPane.setBounds(3, 0, 466, 313);
		useTermPanel.add(useTermScrollPane);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(276, 110, 480, 583);
		getContentPane().add(mainPanel);

		boardPanel = new BoardPanel();
		mainPanel.add(boardPanel);
		boardPanel.setVisible(false);
		
		mainPagePanel = new JPanel();
		mainPagePanel.setBounds(0, 0, 480, 592);
		mainPanel.add(mainPagePanel);
		
				
		// 메인레이블
		mainLabel = new JLabel("");
		mainPagePanel.add(mainLabel);
		mainLabel.setIcon(new ImageIcon("D:\\\uC2A4\uC719\uC774\uBBF8\uC9C0\\\uBA54\uC778\uD398\uC774\uC9C0.png"));

		signUpPanel = new SignUpPanel();
		signUpPanel.setBounds(0, 0, 480, 595);
		mainPanel.add(signUpPanel);
		signUpPanel.setVisible(false);

		// 배경화면 레이블
		JLabel BackgroundLabel = new JLabel("New label");
		BackgroundLabel.setIcon(new ImageIcon(MainPage.class.getResource("/image/\uC624\uB9AC\uC9C0\uB110\uD3FC.png")));
		BackgroundLabel.setBounds(0, 0, 1361, 711);
		getContentPane().add(BackgroundLabel);

		boardLabel = new JLabel("New label");
		boardLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		boardLabel.setBounds(12, 348, 236, 31);
		getContentPane().add(boardLabel);

		// setvisible
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}