package UIClass;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class Sell_History extends JFrame {

	public Sell_History() {
		getContentPane().setLayout(null);
		
		JButton cancel = new JButton("\uCDE8 \uC18C");
		cancel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		cancel.setBounds(282, 395, 204, 68);
		getContentPane().add(cancel);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "2017/11/11 12:37:44", "3,500(원)"},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uD310\uB9E4 \uBC88\uD638", "\uACC4\uC0B0 \uC2DC\uAC04", "\uD310\uB9E4 \uAE08\uC561"
			}
		));
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		
		tScheduleCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(1).setCellRenderer(tScheduleCellRenderer);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 22, 748, 351);
		getContentPane().add(scroll);

	
		
		
	}
	public static void maine(String[] args) {
		Sell_History test = new Sell_History();
	}
}
