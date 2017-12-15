package UIClass;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UI_SalesList extends JFrame {
	private final JButton exitButton = new JButton("취소");
	private JScrollPane scroll;
	public UI_SalesList() {
		getContentPane().setLayout(null);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UI_MainMenu();
				dispose();
			}
		});
		exitButton.setBounds(747, 365, 117, 50);
		getContentPane().add(exitButton);
		
		JTable jtableView = new JTable();
		scroll = new JScrollPane(jtableView);
		scroll.setLocation(28, 76);
		scroll.setSize(710, 342);
		getContentPane().add(scroll);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 36, 710, 40);
		getContentPane().add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("매출 기간 단위 : ");
		lblNewLabel.setBounds(20, 10, 177, 15);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(747, 80, 115, 245);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblwjd = new JLabel("기간 설정");
		lblwjd.setBounds(30, 0, 91, 59);
		panel_1.add(lblwjd);
		
		JButton dayBtn = new JButton("일");
		dayBtn.setBounds(0, 45, 115, 50);
		panel_1.add(dayBtn);
		
		JButton weekBtn = new JButton("주");
		weekBtn.setBounds(0, 95, 115, 50);
		panel_1.add(weekBtn);
		
		JButton monthBtn = new JButton("월");
		monthBtn.setBounds(0, 145, 115, 50);
		panel_1.add(monthBtn);
		
		JButton quaterBtn = new JButton("분기");
		quaterBtn.setBounds(0, 195, 115, 50);
		panel_1.add(quaterBtn);
		this.setResizable(false);
		this.setSize(900, 500);
		this.setVisible(true);
	}
	public static void main(String[] args){
		new UI_SalesList();
	}
}
