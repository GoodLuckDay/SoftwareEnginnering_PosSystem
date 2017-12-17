package UIClass;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class UI_Sell_History extends JFrame {
	private Vector<String> userColumn = new Vector<String>();
	private DefaultTableModel model;
	private JPanel listPanel;
	private JTable table;
	private JScrollPane scrollView;
	private Vector<String> userRow;
	
	public UI_Sell_History() {
		getContentPane().setLayout(null);
		JButton cancel = new JButton("\uCDE8 \uC18C");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UI_MainMenu();
				dispose();
			}
		});
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		cancel.setBounds(282, 395, 204, 68);
		getContentPane().add(cancel);
//		판매 번호, 계산 시간, 판매금액
		userColumn.addElement("판매 번호");
		userColumn.addElement("계산 시간");
		userColumn.addElement("판매 금액");
		
		model = new DefaultTableModel(userColumn,0);
		table = new JTable(model);

		listPanel = new JPanel();
		listPanel.setLayout(null);
		scrollView = new JScrollPane(table);		
		listPanel.add(scrollView);
		listPanel.setBounds(30, 30, 710, 300);
		scrollView.setBounds(0, 0, 710, 300);
		
	
		
		
		getContentPane().add(listPanel);
		
		this.setResizable(false);
		this.setSize(770, 500);
		this.setVisible(true);
		
		userRow = new Vector<String>();
		userRow.addElement("정윤수");
		userRow.addElement("17/12/19 23시 55분 34초");
		userRow.addElement("100,000,000");
		model.addRow(userRow);

	}
//	public static void main(String[] args) {
//		new UI_Sell_History();
//	}
}
