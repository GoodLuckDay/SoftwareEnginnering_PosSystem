package UIClass;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI_DetailedSellRecord extends JFrame {
	private JTable table;
	private JTable table_1;
	public UI_DetailedSellRecord() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 579, 43);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1543125", "2017/11/11 12:37:44", "45,800 (원)"},
			},
			new String[] {
				"\uD310\uB9E4\uBC88\uD638", "\uACB0\uC7AC\uC2DC\uAC04", "\uD310\uB9E4\uAE08\uC561"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton exitButton = new JButton("\uCDE8\uC18C");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setFont(new Font("나눔고딕", Font.BOLD, 18));
		exitButton.setBounds(231, 418, 127, 43);
		getContentPane().add(exitButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 63, 579, 306);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uC815\uC724\uC218", "1", "39800"},
				{"\uBC30\uBEB4\uB85C", "2", "1500"},
				{"\uACE0\uAE54\uCF58", "2", "1500"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uC81C\uD488\uBA85", "\uC218\uB7C9", "\uAC00\uACA9"
			}
		));
		scrollPane_1.setViewportView(table_1);
		this.setResizable(false);
		this.setSize(600, 500);
		this.setVisible(true);
	}
	
//	public static void main(String[] args){
//		new UI_DetailedSellRecord();
//	}
}
