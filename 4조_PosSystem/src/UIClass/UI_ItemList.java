package UIClass;
import PosDAOClass.ItemDAO;
import PosDAOClass.ItemListDTO;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import javax.swing.border.LineBorder;

import java.util.Vector;

public class UI_ItemList extends JFrame {
	JTable table = null;
	ItemListDTO itemListDTO = null;
	Vector cols = null;
	Vector rows = null;
	public UI_ItemList() {
		itemListDTO = new ItemListDTO();
		table = new JTable();

		cols = new Vector();
		cols.add("상품 번호");
		cols.add("상품명");
		cols.add("재고");
		cols.add("가격");
		rows = itemListDTO.getItemListData();

		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("물품 등록");
		btnNewButton.setFont(new Font("나눔고딕", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UI_Item_Register();
			}
		});
		btnNewButton.setBounds(181, 406, 131, 39);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UI_MainMenu();
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("나눔고딕", Font.BOLD, 18));
		btnNewButton_1.setBounds(450, 406, 125, 39);
		getContentPane().add(btnNewButton_1);

		table.setModel(new DefaultTableModel(rows,cols){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 22, 748, 351);
		getContentPane().add(scroll);
		this.setResizable(false);
		this.setSize(770, 500);
		this.setVisible(true);
	}

	public void updateTableModel(){
		rows = itemListDTO.getItemListData();
		table.setModel(new DefaultTableModel(rows,cols){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	private void ui_Item_Resister(){
		JTextField text_price;
		JTextField text_quantity;
		JTextField text_name;
	}

	class UI_Item_Register extends JFrame {
		private JTextField text_price;
		private JTextField text_quantity;
		private JTextField text_name;

		public UI_Item_Register() {
			getContentPane().setLayout(null);

			JButton complete = new JButton("\uC644\uB8CC");
			complete.setFont(new Font("굴림", Font.PLAIN, 24));
			complete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					itemListDTO.addItem(text_name.getText(), Integer.parseInt(text_price.getText()), Integer.parseInt(text_quantity.getText()));
					updateTableModel();
					dispose();
				}
			});
			complete.setBounds(393, 30, 190, 63);
			getContentPane().add(complete);

			JButton cancel = new JButton("\uCDE8\uC18C");
			cancel.setFont(new Font("굴림", Font.PLAIN, 24));
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
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
			panel.add(text_name);
			text_name.setColumns(10);

			JLabel item_quantity = new JLabel("\uC7AC \uACE0");
			item_quantity.setFont(new Font("굴림", Font.PLAIN, 30));
			item_quantity.setHorizontalAlignment(JLabel.CENTER);
			panel.add(item_quantity);
			text_quantity = new JTextField();
			text_quantity.setHorizontalAlignment(SwingConstants.RIGHT);
			text_quantity.setFont(new Font("굴림", Font.PLAIN, 20));
			panel.add(text_quantity);
			text_quantity.setColumns(10);

			JLabel item_price = new JLabel("\uAC00 \uACA9");
			item_price.setFont(new Font("굴림", Font.PLAIN, 30));
			item_price.setHorizontalAlignment(JLabel.CENTER);
			panel.add(item_price);
			text_price = new JTextField();
			text_price.setHorizontalAlignment(SwingConstants.RIGHT);
			text_price.setFont(new Font("굴림", Font.PLAIN, 20));
			panel.add(text_price);
			text_price.setColumns(10);
			this.setResizable(false);
			this.setSize(600, 280);
			this.setVisible(true);
		}
	}
}


