package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainBoard2 extends JFrame {


	public MainBoard2() throws Exception {
		setSize(505, 647);
		setLayout(null);
		
		String columnNames2[] = { "No", "제목", "ID", "글 내용" };
		Object rowData2[][] = new Object[5][4];
		
		JTable jTable2 = new JTable(rowData2, columnNames2);
		//JScrollPane scroll2 = new JScrollPane(jTable2);
		
		jTable2.setBounds(12, 242, 465, 366);
		//scroll2.setBounds(12, 242, 465, 366);
		getContentPane().add(jTable2);
//		getContentPane().add(scroll2);

		setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		new MainBoard2();
	}
}
