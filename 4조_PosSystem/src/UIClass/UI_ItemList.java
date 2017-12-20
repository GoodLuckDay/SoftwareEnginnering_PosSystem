package UIClass;
import PosDAOClass.ItemDAO;
import PosDAOClass.ItemDTO;
import PosDAOClass.ItemListDTO;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.rowAtPoint(e.getPoint());
				if(e.getClickCount() == 2){
					ItemDTO itemDTO = itemListDTO.getItemInfo(rowNum);
					new UI_Item_Detail(rowNum, itemDTO.getItemName(), itemDTO.getItemPrice(), itemDTO.getItemStock());
				}
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
					if(text_price.getText().toString().equals("") || text_name.getText().toString().equals("") || text_quantity.getText().toString().equals("")){
						new UI_Item_Register_Unfilled();
					}else{
						itemListDTO.addItem(text_name.getText(), Integer.parseInt(text_price.getText()), Integer.parseInt(text_quantity.getText()));
						updateTableModel();
						dispose();
					}
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
		class UI_Item_Register_Unfilled extends JFrame { //물품 등록시 미입력값이 있을때 호출
			public UI_Item_Register_Unfilled(){
				getContentPane().setLayout(null);
				this.setSize(580, 200);
				this.setTitle("미입력");
				JButton okButton = new JButton("확 인");
				okButton.setFont(new Font("나눔고딕", Font.BOLD, 19));
				okButton.setBounds(592, 12, 146, 55);
				getContentPane().add(okButton);	
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				
				JLabel ITEMID = new JLabel("미입력 값이 있습니다.");
				ITEMID.setHorizontalAlignment(SwingConstants.CENTER);
				ITEMID.setFont(new Font("나눔고딕", Font.BOLD, 19));
				ITEMID.setBounds(87, 10, 381, 98);
				getContentPane().add(ITEMID);
				this.setVisible(true);
				
			}
		}
	}

	class UI_Item_Detail extends JFrame {
		private JTextField text_price;
		private JTextField text_quantity;
		private JTextField text_name;
		private JTextField text_num;

		class UI_Item_Register_Unfilled extends JFrame { //물품 등록시 미입력값이 있을때 호출
			public UI_Item_Register_Unfilled(){
				getContentPane().setLayout(null);
				this.setSize(580, 200);
				this.setTitle("미입력");
				JButton okButton = new JButton("확 인");
				okButton.setFont(new Font("나눔고딕", Font.BOLD, 19));
				okButton.setBounds(592, 12, 146, 55);
				getContentPane().add(okButton);	
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				
				JLabel ITEMID = new JLabel("미입력 값이 있습니다.");
				ITEMID.setHorizontalAlignment(SwingConstants.CENTER);
				ITEMID.setFont(new Font("나눔고딕", Font.BOLD, 19));
				ITEMID.setBounds(87, 10, 381, 98);
				getContentPane().add(ITEMID);
				this.setVisible(true);
				
			}
		}
		
		public UI_Item_Detail(int itemNo, String itemName, int itemPriece, int itemStock) {
			getContentPane().setLayout(null);

			JButton change = new JButton("\uC218 \uC815");
			change.setFont(new Font("굴림", Font.PLAIN, 24));
			change.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(text_price.getText().toString().equals("") || text_name.getText().toString().equals("") || text_quantity.getText().toString().equals("")){
						new UI_Item_Register_Unfilled();
					}else{
						itemListDTO.updateItemInfo(itemName, text_name.getText(), Integer.parseInt(text_price.getText())
								, Integer.parseInt(text_quantity.getText()));
						updateTableModel();
						dispose();
					}
					
				}
			});
			change.setBounds(411, 77, 190, 63);
			getContentPane().add(change);

			JButton delete = new JButton("\uC0AD \uC81C");
			delete.setFont(new Font("굴림", Font.PLAIN, 24));
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					itemListDTO.deleteItemInfo(itemName);
					updateTableModel();
					dispose();
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
			text_num.setText(String.valueOf(itemNo+1));
			text_num.setEditable(false);
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
			text_name.setText(itemName);
			panel.add(text_name);
			text_name.setColumns(10);

			JLabel item_price = new JLabel("\uAC00 \uACA9");
			item_price.setFont(new Font("굴림", Font.PLAIN, 30));
			item_price.setHorizontalAlignment(JLabel.CENTER);
			panel.add(item_price);
			text_price = new JTextField();
			text_price.setHorizontalAlignment(SwingConstants.RIGHT);
			text_price.setText(String.valueOf(itemPriece));
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
			text_quantity.setText(String.valueOf(itemStock));
			panel.add(text_quantity);
			text_quantity.setColumns(10);
			this.setResizable(false);
			this.setSize(700, 500);
			this.setVisible(true);
		}
	}
}


