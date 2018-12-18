package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class MyPagePanel extends JPanel {

	BrowserContext context = new BrowserContext(new BrowserContextParams("D:\\jx"));
	BrowserContext context2 = new BrowserContext(new BrowserContextParams("D:\\jx2"));
	private Browser browser = new Browser(context);
	private Browser browser2 = new Browser(context2);
	private BrowserView browserView = new BrowserView(browser);
	private BrowserView browserView2 = new BrowserView(browser2);

	public MyPagePanel() throws Exception {
		setBounds(0, 0, 470, 340);
		setLayout(null);

		JLabel updateLabel = new JLabel();
		updateLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				UpdatePanel updatePanel = new UpdatePanel();
				MemberDTO dto = new MemberDTO();
				try {
					dto = new MemberDAO().selectMine(MemberDTO.SessionId);
					updatePanel.idTextField.setText(dto.getId());
					updatePanel.pwTextField.setText(String.valueOf(dto.getPw()));
					updatePanel.rpwTextField.setText(String.valueOf(dto.getPw()));
					updatePanel.nameTextField.setText(dto.getName());
					updatePanel.telTextField.setText(dto.getTel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainPage.mainPanel.add(updatePanel);
				updatePanel.setVisible(true);
				MainPage.mainPagePanel.setVisible(false);
			}
		});

		JLabel quitLabel = new JLabel("");
		quitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// int s에 설정값 ( Yes - 0 , no - 1, 취소 - 2)
				int s = JOptionPane.showConfirmDialog(null, MemberDTO.SessionId + "님 정말 탈퇴하시겠습니까?");
				// Yes누르면 탈퇴
				if (s == 0) {
					try {
						new MemberDAO().delete(MemberDTO.SessionId);
						JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님 지금까지 이용해주셔서 감사합니다");
						MemberDTO.SessionId = null;
						MainPage.mainPagePanel.setVisible(true);
						MainPage.mainSubPanel.setVisible(true);
						MainPage.loginPanel.setVisible(false);
						MainPage.idInputPanel.setVisible(true);
						setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 다른것을 선택하면 취소
				else {
					JOptionPane.showMessageDialog(null, "계속 이용해주셔서 감사합니다, 잘 부탁드리겠습니다", null, 0);
				}
			}
		});

		JPanel chartPanel = new JPanel();
		chartPanel.setBounds(14, 138, 249, 189);
		add(chartPanel);


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
		ArrayList<PieElement> list = new ArrayList<PieElement>();
		list.add(new PieElement("chicken", sort[0]));
		list.add(new PieElement("pizza", sort[1]));
		list.add(new PieElement("Chinese", sort[2]));

		browser2.loadHTML(new PieChart().getPieChart(list));
		chartPanel.setLayout(null);
		browserView2.setBounds(0, 0, 249, 189);
		chartPanel.add(browserView2);

		JPanel barChartPanel = new JPanel();
		barChartPanel.setBounds(269, 138, 187, 189);
		add(barChartPanel);

		browser.addLoadListener(new LoadAdapter() {
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent event) {
				if (event.isMainFrame()) {
					System.out.println("HTML문서가 로드되었습니다.");
				}
			}
		});

		ArrayList<java.sql.Date> dateArr = new FileReadOrder().date(MemberDTO.SessionId);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		int[] month = new int[13];

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
		ArrayList<PieElement> list1 = new ArrayList<PieElement>();
		list1.add(new PieElement("9월", month[9]));
		list1.add(new PieElement("10월", month[10]));
		list1.add(new PieElement("11월", month[11]));
		list1.add(new PieElement("12월", month[12]));

		browser.loadHTML(new barChart().getBarChart(list1));
		barChartPanel.setLayout(null);

		barChartPanel.add(browserView, BorderLayout.CENTER);

		browserView.setBounds(0, 0, 187, 189);

		quitLabel.setBounds(88, 65, 65, 51);
		add(quitLabel);
		updateLabel.setBounds(14, 65, 65, 51);
		add(updateLabel);

		JLabel myPageLabel = new JLabel("");
		myPageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPageLabel.setIcon(new ImageIcon(MyPagePanel.class.getResource("/image/마이페이지패널.png")));
		myPageLabel.setBounds(0, 0, 470, 340);
		add(myPageLabel);

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		orderHistoryLabel.setBounds(238, 65, 62, 51);
		add(orderHistoryLabel);

		JLabel receipt = new JLabel("");
		receipt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame jf= new JFrame();
				jf.setSize(300, 800);
				jf.setVisible(true);
				
				File file = new File(MemberDTO.SessionId + ".txt");
				
				JTextPane jt= new JTextPane();
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
				
				JScrollPane jp= new JScrollPane(jt);
				jf.getContentPane().add(jp,BorderLayout.CENTER);	
			}
		});
		receipt.setBounds(391, 65, 65, 51);
		add(receipt);

		JLabel membershipLabel = new JLabel("New label");
		membershipLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String membership = null;
				int minus = 0;
				int nOrder = 0;
				try {
					nOrder = new FileReadOrder().history(MemberDTO.SessionId).size();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
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
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					JOptionPane.showMessageDialog(null, MemberDTO.SessionId + "님의 멤버쉽 등급은 주문기록 : "
							+ new FileReadOrder().history(MemberDTO.SessionId).size() + "(으)로 " + membership + "단계입니다");
					JOptionPane.showMessageDialog(null, "다음단계까지 " + minus + "번의 주문이 더 필요합니다.");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		membershipLabel.setBounds(315, 65, 62, 51);
		add(membershipLabel);

	}
}
