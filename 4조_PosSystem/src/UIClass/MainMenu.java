package UIClass;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {
	public MainMenu() {
		final JFrame mainFrame = new JFrame("Main Menu");
		Font mainText = new Font("궁서",Font.BOLD,20);
		mainFrame.getContentPane().setLayout(null);
		
		JButton button = new JButton("물품 관리");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DAOItemList();
				mainFrame.dispose();
			}
		});
		button.setBounds(151, 132, 174, 67);
		mainFrame.getContentPane().add(button);
		
		JButton btnNewButton = new JButton("매출 조회");
		btnNewButton.setBounds(151, 264, 174, 69);
		mainFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("판매 내역 조회");
		btnNewButton_1.setBounds(402, 132, 174, 67);
		mainFrame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("물품 판매");
		btnNewButton_2.setBounds(402, 264, 174, 69);
		mainFrame.getContentPane().add(btnNewButton_2);
		mainFrame.setSize(800, 500);
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args){
		System.out.println("sfsfs");
		new MainMenu();
	}
}