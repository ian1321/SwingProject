package swing;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import swing2.OrderMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapFrame extends JFrame {
	/*변수설정*/
	/*브라우저*/
	Browser browser3;
	BrowserView view;
	/*패널*/
	private JPanel panel = new JPanel();
	/*텍스트필드*/
	private JTextField textField = new JTextField(30);
	/*버튼*/
	private JButton search = new JButton("검색");
	private JButton useThisAddress = new JButton("이 주소 사용하기");
	/*레이블,스트링*/
	private final JLabel notificationLabel = new JLabel("도로명이나 지역명으로 검색해주세요");
	public static String address; /*주소 입력값 저장*/

	/*MouseListener*/
	public class Event implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				setURL("https://maps.google.com/maps/place/" + URLEncoder.encode(textField.getText(), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}

	}
	/*url 메소드*/
	public void setURL(String URL) {
		browser3.loadURL(URL);
	}
	
	/*Constructor*/
	public MapFrame() {
		/*브라우저설정*/
		BrowserContext context3 = new BrowserContext(new BrowserContextParams("D:\\jx3"));
		browser3 = new Browser(context3);
		view = new BrowserView(browser3);
		view.setBounds(0, 59, 1262, 614);
		
		/*검색패널*/
		panel.setBounds(0, 0, 1082, 58);
		panel.setLayout(null);
		useThisAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*검색한 주소 저장*/
				address = textField.getText();
				/*브라우저스탑*/
				browser3.stop();
				/*주문창 주소*/
				OrderMain.search.setText(address);
				dispose();
			}
		});
		useThisAddress.setBounds(927, 7, 141, 41);
		panel.add(useThisAddress);
		/*글 - 주소를 입력해주세요 레이블*/
		notificationLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		notificationLabel.setBounds(14, 9, 384, 37);
		panel.add(notificationLabel);
		/*검색필드*/
		textField.setFont(new Font("굴림", Font.PLAIN, 20));
		textField.setBounds(434, 7, 376, 40);
		panel.add(textField);
		/*검색버튼*/
		search.setFont(new Font("굴림", Font.PLAIN, 20));
		search.setBounds(824, 7, 89, 41);
		panel.add(search);
		/*검색에 mouseListener*/
		search.addMouseListener(new Event());
		
		/*프레임설정*/
		setTitle("구글맵");
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().add(view, BorderLayout.CENTER);
		setSize(1280, 720);
		setVisible(true);
		getContentPane().add(view, BorderLayout.CENTER);
		/*브라우저 url로드*/
		browser3.loadURL("https://maps.google.com/");
	}
	public static void main(String[] args) {
		new MapFrame();
	}
}