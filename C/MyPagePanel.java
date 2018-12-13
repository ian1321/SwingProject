package swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyPagePanel extends JPanel {

	private Browser browser = new Browser();
	private Browser browser2 = new Browser();
	private BrowserView browserView = new BrowserView(browser);
	private BrowserView browserView2= new BrowserView(browser2);

	public MyPagePanel() {
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
		chartPanel.setBounds(14, 128, 249, 189);
		add(chartPanel);
		
		browser2.loadHTML(new PieChart().getPieChart());
		chartPanel.setLayout(null);
		browserView2.setBounds(0, 0, 249, 189);
		chartPanel.add(browserView2);
		

		JPanel barChartPanel = new JPanel();
		barChartPanel.setBounds(269, 128, 187, 189);
		add(barChartPanel);
		
		browser.addLoadListener(new LoadAdapter() {
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent event) {
				if (event.isMainFrame()) {
					System.out.println("HTML문서가 로드되었습니다.");
				}
			}
		});
		browser.loadHTML(new barChart().getPieChart());
		barChartPanel.setLayout(null);
		
		barChartPanel.add(browserView,BorderLayout.CENTER);
		
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

	}
}
