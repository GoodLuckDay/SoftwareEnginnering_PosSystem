package UIClass;
import PosDAOClass.ItemDAO;
import PosDAOClass.ItemListDTO;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLayeredPane;

import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.CardLayout;
import java.util.Vector;

public class UI_ItemList extends JFrame {
	ItemListDTO itemListDTO = null;
	Vector cols = null;
	Vector rows = null;
	public UI_ItemList() {
		itemListDTO = new ItemListDTO();
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

		Vector cols = new Vector();
		cols.add("상품 번호");
		cols.add("상품명");
		cols.add("재고");
		cols.add("가격");
		JTable table = new JTable();

		Vector rows = itemListDTO.getItemListData();
		table.setModel(new DefaultTableModel(rows,cols){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		table.getColumnModel().getColumn(3).setMinWidth(20);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 22, 748, 351);
		getContentPane().add(scroll);
		this.setResizable(false);
		this.setSize(770, 500);
		this.setVisible(true);
	}

	/*
	 * for test code
	 * 현재 클래스에서 실행을 하면 바로 현재 UI가 실행이 가능하게 한다. 
	 */
//	public static void main(String[] args){
//		new UI_ItemList();
//	}
}
