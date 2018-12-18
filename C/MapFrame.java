package swing;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MapFrame extends JFrame{
	BrowserContext context3 = new BrowserContext(new BrowserContextParams("D:\\jx3"));
	Browser browser3;
	BrowserView view;
	private JTextField textField = new JTextField(30);
	private JButton button = new JButton("검색");
	private JButton button1 = new JButton("이 주소 사용하기");
	private JPanel panel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("도로명이나 지역명으로 검색해주세요");

	public class Event implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				setURL("https://maps.google.com/maps/place/" + URLEncoder.encode(textField.getText(), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
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
	
	public void setURL(String URL) {
		browser3.loadURL(URL);
	}
	
	public MapFrame() {
		browser3 = new Browser();
		view = new BrowserView(browser3);
		view.setBounds(0, 59, 1262, 614);
		setTitle("구글맵");
		getContentPane().setLayout(null);
		panel.setBounds(0, 0, 1082, 58);
		panel.setLayout(null);
		button1.setBounds(927, 7, 141, 41);
		panel.add(button1);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 9, 384, 37);
		
		panel.add(lblNewLabel);
		textField.setFont(new Font("굴림", Font.PLAIN, 20));
		textField.setBounds(434, 7, 376, 40);
		
		panel.add(textField);
		button.setFont(new Font("굴림", Font.PLAIN, 20));
		button.setBounds(824, 7, 89, 41);
		panel.add(button);
		button.addMouseListener(new Event());
		getContentPane().add(panel);
		getContentPane().add(view, BorderLayout.CENTER);
		setSize(1280, 720);
		setVisible(true);
		getContentPane().add(view, BorderLayout.CENTER);
		
		browser3.loadURL("https://maps.google.com/");
	}

	public static void main(String[] args) {
		MapFrame map= new MapFrame();
	}
}