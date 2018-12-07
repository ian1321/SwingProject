package swing;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardLayoutDemo extends JFrame { 

    private CardLayout cLayout; 
    private JPanel mainPane; 

    public CardLayoutDemo(){ 

     setTitle("Card Layout Demo"); 
     setSize(400,250); 
     setDefaultCloseOperation(EXIT_ON_CLOSE); 

     mainPane = new JPanel(); 
     cLayout = new CardLayout(); 
     mainPane.setLayout(cLayout); 

     JPanel yellowPane = new JPanel(); 
     yellowPane.setBackground(Color.YELLOW); 
     JPanel redPane = new JPanel(); 
     redPane.setBackground(Color.RED); 

}
    public static void main(String[] args) {
		new CardLayout();
	}
    
}