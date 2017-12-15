package UIClass;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class UI_Item_Detail extends JFrame {
	private JTextField text_price;
	private JTextField text_quantity;
	private JTextField text_name;
	private JTextField text_num;
	public UI_Item_Detail() {
		getContentPane().setLayout(null);	
		
		JButton change = new JButton("\uC218 \uC815");
		change.setFont(new Font("굴림", Font.PLAIN, 24));
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		change.setBounds(411, 77, 190, 63);
		getContentPane().add(change);
		
		JButton delete = new JButton("\uC0AD \uC81C");
		delete.setFont(new Font("굴림", Font.PLAIN, 24));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		delete.setBounds(411, 178, 190, 63);
		getContentPane().add(delete);
		
		JButton cancel = new JButton("\uCDE8 \uC18C");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setFont(new Font("굴림", Font.PLAIN, 24));
		cancel.setBounds(411, 274, 190, 63);
		getContentPane().add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 12, 330, 407);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 2));
		
		JLabel item_num = new JLabel("\uC81C\uD488 \uBC88\uD638");
		item_num.setHorizontalAlignment(SwingConstants.CENTER);
		item_num.setFont(new Font("굴림", Font.PLAIN, 30));
		panel.add(item_num);
		
		text_num = new JTextField();
		text_num.setHorizontalAlignment(SwingConstants.RIGHT);
		text_num.setText("1");
		text_num.setFont(new Font("굴림", Font.PLAIN, 20));
		panel.add(text_num);
		text_num.setColumns(10);
		
		JLabel Item_name = new JLabel("\uC0C1\uD488\uBA85");
		Item_name.setFont(new Font("굴림", Font.PLAIN, 30));
		Item_name.setHorizontalAlignment(JLabel.CENTER);
		panel.add(Item_name);		
		text_name = new JTextField();
		text_name.setHorizontalAlignment(SwingConstants.CENTER);
		text_name.setFont(new Font("굴림", Font.PLAIN, 20));
		text_name.setText("\uC815\uC724\uC218");
		panel.add(text_name);
		text_name.setColumns(10);
		
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
		this.setResizable(false);
		this.setSize(700, 500);
		this.setVisible(true);
	}
	/*
	 * for test code
	 * 현재 클래스에서 실행을 하면 바로 현재 UI가 실행이 가능하게 한다. 
	 */
	public static void main(String[] args){
		new UI_Item_Detail();
	}
}
