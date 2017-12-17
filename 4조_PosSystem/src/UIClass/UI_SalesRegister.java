package UIClass;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class UI_SalesRegister extends JFrame {
	private JTextField itemid;
	private JTextField quantity;
	
	public UI_SalesRegister() {
		getContentPane().setLayout(null);
		this.setSize(780, 200);
		this.setTitle("판매 물품 등록");
		JButton okButton = new JButton("확 인");
		okButton.setFont(new Font("나눔고딕", Font.BOLD, 19));
		okButton.setBounds(592, 12, 146, 55);
		getContentPane().add(okButton);	
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
			}
		});
		
		JButton cancelButton = new JButton("취 소");
		cancelButton.setFont(new Font("나눔고딕", Font.BOLD, 19));
		cancelButton.setBounds(592, 79, 146, 55);
		getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JLabel ITEMID = new JLabel("물품 번호");
		ITEMID.setHorizontalAlignment(SwingConstants.CENTER);
		ITEMID.setFont(new Font("나눔고딕", Font.BOLD, 19));
		ITEMID.setBounds(14, 37, 140, 68);
		getContentPane().add(ITEMID);
		
		JLabel QUANTITY = new JLabel("수 량");
		QUANTITY.setHorizontalAlignment(SwingConstants.CENTER);
		QUANTITY.setFont(new Font("나눔고딕", Font.BOLD, 19));
		QUANTITY.setBounds(271, 37, 140, 68);
		getContentPane().add(QUANTITY);
		
		itemid = new JTextField();
		itemid.setHorizontalAlignment(SwingConstants.CENTER);
		itemid.setFont(new Font("나눔고딕", Font.BOLD, 15));
		itemid.setBounds(152, 51, 132, 44);
		getContentPane().add(itemid);
		itemid.setColumns(10);
		
		quantity = new JTextField();
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.setFont(new Font("나눔고딕", Font.BOLD, 15));
		quantity.setColumns(10);
		quantity.setBounds(395, 51, 132, 44);
		getContentPane().add(quantity);
		this.setVisible(true);
	}
}
