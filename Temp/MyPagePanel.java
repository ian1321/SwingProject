package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import swing2.FoodListDAO;
import swing2.FoodListDTO;

public class MyPagePanel extends JPanel {

	/* 브라우저 설정 */
	BrowserContext context = new BrowserContext(new BrowserContextParams("D:\\jx"));
	BrowserContext context2 = new BrowserContext(new BrowserContextParams("D:\\jx2"));
	private Browser browser = new Browser(context);
	private Browser browser2 = new Browser(context2);
	private BrowserView browserView = new BrowserView(browser);
	private BrowserView browserView2 = new BrowserView(browser2);

	public MyPagePanel() throws Exception {
		/* 패널설정 */
		setBounds(0, 0, 470, 340);
		setLayout(null);

		/* 브라우저 로드 알림메세지 */
		browser.addLoadListener(new LoadAdapter() {
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent event) {
				if (event.isMainFrame()) {
					System.out.println("HTML문서가 로드되었습니다.");
				}
			}
		});

		/*-------------------파이차트-------------------*/
		/* 차트패널 */
		JPanel chartPanel = new JPanel();
		chartPanel.setBounds(14, 138, 249, 189);
		add(chartPanel);
		/* 차트값설정 */
		int[] sort = new int[3];
		/* 주문기록파일 읽어오기 */
		ArrayList<FoodListDTO> arr = new FileReadOrder().history(MemberDTO.SessionId);
		FoodListDTO fDto = new FoodListDTO();
		/* 종류별로 값더해주기 */
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
		ArrayList<ChartElement> list = new ArrayList<ChartElement>();
		list.add(new ChartElement("chicken", sort[0]));
		list.add(new ChartElement("pizza", sort[1]));
		list.add(new ChartElement("Chinese", sort[2]));
		/* html로드 */
		browser2.loadHTML(new PieChart().getPieChart(list));
		chartPanel.setLayout(null);
		browserView2.setBounds(0, 0, 249, 189);
		chartPanel.add(browserView2);

		/*----------------------바차트----------------------*/
		/* 바차트 패널 */
		JPanel barChartPanel = new JPanel();
		barChartPanel.setBounds(269, 138, 187, 189);
		add(barChartPanel);
		/* 주문기록 날짜가져오기 */
		ArrayList<java.sql.Date> dateArr = new FileReadOrder().date(MemberDTO.SessionId);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		/* 12월해당하는 int배열 int[1] = 1 */
		int[] month = new int[13];
		/* 배열값 */
		for (int i = 0; i < dateArr.size(); i++) {
			date = dateArr.get(i);
			cal.setTime(date);
			switch (cal.get(Calendar.MONTH) + 1) {
			case 9:
				month[9] += 1;
				break;
			case 10:
				month[10] += 1;
				break;
			case 11:
				month[11] += 1;
				break;
			case 12:
				month[12] += 1;
				break;
			}
			;
		}
		ArrayList<ChartElement> list1 = new ArrayList<ChartElement>();
		list1.add(new ChartElement("9월", month[9]));
		list1.add(new ChartElement("10월", month[10]));
		list1.add(new ChartElement("11월", month[11]));
		list1.add(new ChartElement("12월", month[12]));
		/* html로드 */
		browser.loadHTML(new BarChart().getBarChart(list1));
		barChartPanel.setLayout(null);
		barChartPanel.add(browserView, BorderLayout.CENTER);
		browserView.setBounds(0, 0, 187, 189);

		/* 수정 */
		JLabel updateLabel = new JLabel();
		updateLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* 업데이트패널생성 */
				UpdatePanel updatePanel = new UpdatePanel();
				MemberDTO dto = new MemberDTO();
				try {
					/* 패널에 해당아이디값 넣어주기 */
					dto = new MemberDAO().select(MemberDTO.SessionId);
					UpdatePanel.idTextField.setText(dto.getId());
					updatePanel.pwTextField.setText(String.valueOf(dto.getPw()));
					updatePanel.rpwTextField.setText(String.valueOf(dto.getPw()));
					updatePanel.nameTextField.setText(dto.getName());
					updatePanel.telTextField.setText(dto.getTel());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				/* 패널세팅 */
				MainPage.mainPanel.add(updatePanel);
				updatePanel.setVisible(true);
				MainPage.mainPagePanel.setVisible(false);
			}
		});

		/* 탈퇴기능 */
		JLabel quitLabel = new JLabel("");
		quitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* int s에 설정값 ( Yes - 0 , no - 1, 취소 - 2) */
				int s = JOptionPane.showConfirmDialog(null, MemberDTO.SessionId + "님 정말 탈퇴하시겠습니까?");
				/* yes = 탈퇴 */
				if (s == 0) {
					try {
						/* 해당아이디삭제 */
						new MemberDAO().delete(MemberDTO.SessionId);
						JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님 지금까지 이용해주셔서 감사합니다");
						MemberDTO.SessionId = null;
						MainPage.mainPagePanel.setVisible(true);
						MainPage.mainSubPanel.setVisible(true);
						MainPage.loginPanel.setVisible(false);
						MainPage.idInputPanel.setVisible(true);
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				/* 다른것 = 취소 */
				else {
					JOptionPane.showMessageDialog(null, "계속 이용해주셔서 감사합니다, 잘 부탁드리겠습니다");
				}
			}
		});
		quitLabel.setBounds(88, 65, 65, 51);
		add(quitLabel);
		updateLabel.setBounds(14, 65, 65, 51);
		add(updateLabel);

		JLabel boardLabel = new JLabel("");
		boardLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPage.mainPagePanel.setVisible(false);
				MainPage.boardPanel.setVisible(true);
			}
		});
		boardLabel.setBounds(163, 65, 65, 51);
		add(boardLabel);

		/* 주문기록 */
		JLabel orderHistoryLabel = new JLabel("");
		orderHistoryLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(MemberDTO.SessionId + ".txt");
				try {
					Scanner sc = new Scanner(file);
					while (sc.hasNextLine()) {
						System.out.println(sc.nextLine());
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});
		orderHistoryLabel.setBounds(238, 65, 62, 51);
		add(orderHistoryLabel);

		/* 영수증 */
		JLabel receipt = new JLabel("");
		receipt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* 프레임 */
				JFrame jf = new JFrame();
				jf.setSize(300, 800);
				jf.setVisible(true);
				/* 주문기록 읽어오기 */
				File file = new File(MemberDTO.SessionId + ".txt");
				JTextPane jt = new JTextPane();
				jt.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
				Scanner sc;
				try {
					sc = new Scanner(file);
					String content = new String();
					while (sc.hasNextLine()) {
						content += sc.nextLine() + "\r\n";
					}
					jt.setText(content);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JScrollPane jp = new JScrollPane(jt);
				jf.getContentPane().add(jp, BorderLayout.CENTER);
			}
		});
		receipt.setBounds(391, 65, 65, 51);
		add(receipt);
		/* 멤버쉽 등급 확인 */
		JLabel membershipLabel = new JLabel("");
		membershipLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String membership = null;
				int minus = 0; // 다음 등급까지 필요한 주문횟수
				int nOrder = 0; // 주문횟수
				try {
					nOrder = new FileReadOrder().history(MemberDTO.SessionId).size();
					/* 각등급별 카운트 */
					if (nOrder < 7) {
						membership = "식객";
						minus = 7 - nOrder;
					} else if (nOrder < 12) {
						membership = "미식가";
						minus = 12 - nOrder;
					} else if (nOrder < 22) {
						membership = "미식왕";
						minus = 22 - nOrder;
					} else if (nOrder >= 23) {
						membership = "식신";
					}
					JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님의 멤버쉽 등급은 주문기록 : "
							+ new FileReadOrder().history(MemberDTO.SessionId).size() + "(으)로 " + membership + "단계입니다");
					JOptionPane.showMessageDialog(null, "다음단계까지 " + minus + "번의 주문이 더 필요합니다.");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		membershipLabel.setBounds(315, 65, 62, 51);
		add(membershipLabel);

		/* 백그라운드 레이블 */
		JLabel myPageLabel = new JLabel("");
		myPageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPageLabel.setIcon(new ImageIcon(MyPagePanel.class.getResource("/image/마이페이지패널.png")));
		myPageLabel.setBounds(0, 0, 470, 340);
		add(myPageLabel);

	}
}
