package UIClass;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JTextField;


public class UI_SalesMonitor extends JFrame {
	private final JButton exitButton = new JButton("취소");
	private final JButton registButton = new JButton("등록");
	private final JScrollPane scrollView;
	private final JTable jTable = new JTable();
	private JTextField chargedMoney;
	private JTextField receivedMoney;
	private JTextField remainedMoney;
	private JLabel chagedMoneylabel;
	private JLabel receivedMoneylabel;
	private JLabel remainedMoneylabel;

	public UI_SalesMonitor() {
		setResizable(false);
		setTitle("판매 화면");

		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UI_SalesRegister();
			}
		});
		registButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		registButton.setBounds(191, 385, 120, 50);
		getContentPane().add(registButton);
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UI_MainMenu();
				dispose();
			}
		});
		exitButton.setBounds(421, 385, 120, 50);
		exitButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		getContentPane().setLayout(null);
		getContentPane().add(exitButton);
		jTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "GOD", "2", "4500"},
			},
			new String[] {
				"\uBB3C\uD488\uBC88\uD638", "\uBB3C\uD488 \uC774\uB984", "\uBB3C\uD488 \uC218\uB7C9", "\uAC00\uACA9"
			}
		));

		scrollView = new JScrollPane(jTable);
		scrollView.setBounds(40, 35, 870, 340);
//		scroll.setSize(870, 200);
		scrollView.setPreferredSize(new Dimension(200, 60));
		getContentPane().add(scrollView);
		
		chargedMoney = new JTextField();
		chargedMoney.setBounds(692, 387, 225, 19);
		getContentPane().add(chargedMoney);
		chargedMoney.setColumns(10);
		
		receivedMoney = new JTextField();
		receivedMoney.setColumns(10);
		receivedMoney.setBounds(692, 404, 225, 19);
		getContentPane().add(receivedMoney);
		
		remainedMoney = new JTextField();
		remainedMoney.setColumns(10);
		remainedMoney.setBounds(692, 422, 225, 19);
		getContentPane().add(remainedMoney);
		
		chagedMoneylabel = new JLabel("총 금액");
		chagedMoneylabel.setBounds(594, 391, 106, 15);
		getContentPane().add(chagedMoneylabel);
		
		receivedMoneylabel = new JLabel("받은 금액");
		receivedMoneylabel.setBounds(594, 408, 106, 15);
		getContentPane().add(receivedMoneylabel);
		
		remainedMoneylabel = new JLabel("거스름 돈");
		remainedMoneylabel.setBounds(594, 426, 106, 15);
		getContentPane().add(remainedMoneylabel);
		
		
		this.setResizable(false);
		this.setSize(950, 500);
		this.setVisible(true);
	}

}
