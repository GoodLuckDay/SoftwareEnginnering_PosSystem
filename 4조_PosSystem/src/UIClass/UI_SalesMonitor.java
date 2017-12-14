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
	private final JScrollPane scroll;
	private final JTable jTable = new JTable();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
	public UI_SalesMonitor() {
		setResizable(false);
		setTitle("판매 화면");
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

		scroll = new JScrollPane(jTable);
		scroll.setBounds(40, 35, 870, 340);
//		scroll.setSize(870, 200);
		scroll.setPreferredSize(new Dimension(200, 60));
		getContentPane().add(scroll);
		
		textField = new JTextField();
		textField.setBounds(692, 387, 225, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(692, 404, 225, 19);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(692, 422, 225, 19);
		getContentPane().add(textField_2);
		
		lblNewLabel = new JLabel("총 금액");
		lblNewLabel.setBounds(637, 387, 106, 15);
		getContentPane().add(lblNewLabel);
		
		label = new JLabel("받은 금액");
		label.setBounds(637, 404, 106, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("거스름 돈");
		label_1.setBounds(637, 422, 106, 15);
		getContentPane().add(label_1);
	}

	
	public static void main(String[] args){
		UI_SalesMonitor mytest = new UI_SalesMonitor();
	}
}
