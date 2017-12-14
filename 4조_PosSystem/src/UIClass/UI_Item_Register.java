package UIClass;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;


public class UI_Item_Register extends JFrame {
	private JTextField text_price;
	private JTextField text_quantity;
	private JTextField text_name;
	public UI_Item_Register() {
		getContentPane().setLayout(null);	
		
		JButton complete = new JButton("\uC644\uB8CC");
		complete.setFont(new Font("굴림", Font.PLAIN, 24));
		complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		complete.setBounds(393, 30, 190, 63);
		getContentPane().add(complete);
		
		JButton cancel = new JButton("\uCDE8\uC18C");
		cancel.setFont(new Font("굴림", Font.PLAIN, 24));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancel.setBounds(393, 140, 190, 63);
		getContentPane().add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 12, 330, 215);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 2));
		
		JLabel item_name = new JLabel("\uC0C1\uD488\uBA85");
		item_name.setFont(new Font("굴림", Font.PLAIN, 30));
		item_name.setHorizontalAlignment(JLabel.CENTER);
		panel.add(item_name);		
		text_name = new JTextField();
		text_name.setHorizontalAlignment(SwingConstants.CENTER);
		text_name.setFont(new Font("굴림", Font.PLAIN, 20));
		text_name.setText("\uC815\uC724\uC218");
		panel.add(text_name);
		text_name.setColumns(10);
		
		JLabel item_quantity = new JLabel("\uC7AC \uACE0");
		item_quantity.setFont(new Font("굴림", Font.PLAIN, 30));
		item_quantity.setHorizontalAlignment(JLabel.CENTER);
		panel.add(item_quantity);
		text_quantity = new JTextField();		
		text_quantity.setHorizontalAlignment(SwingConstants.RIGHT);
		text_quantity.setFont(new Font("굴림", Font.PLAIN, 20));
		text_quantity.setText("10");
		panel.add(text_quantity);
		text_quantity.setColumns(10);
		
		JLabel item_price = new JLabel("\uAC00 \uACA9");
		item_price.setFont(new Font("굴림", Font.PLAIN, 30));
		item_price.setHorizontalAlignment(JLabel.CENTER);
		panel.add(item_price);
		text_price = new JTextField();
		text_price.setHorizontalAlignment(SwingConstants.RIGHT);
		text_price.setText("39,800\uC6D0");
		text_price.setFont(new Font("굴림", Font.PLAIN, 20));
		panel.add(text_price);		
		text_price.setColumns(10);
		

	}
}
