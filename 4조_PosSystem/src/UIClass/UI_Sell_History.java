package UIClass;
import PosDAOClass.SaledItemDTO;
import PosDAOClass.SalesInfoDAO;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
	private SalesInfoDAO salesInfoDAO = new SalesInfoDAO();
	private DefaultTableModel model;
	private JPanel listPanel;
	private JTable table;
	private JScrollPane scrollView;
	private Vector userRow = new Vector();
	
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
		
		DefaultTableCellRenderer dter = new DefaultTableCellRenderer();
		dter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		
		for ( int i = 0; i < tcm.getColumnCount(); i++ ) {
			tcm.getColumn(i).setCellRenderer(dter);
		}
		
		
		getContentPane().add(listPanel);
		
		this.setResizable(false);
		this.setSize(770, 500);
		this.setVisible(true);

		ArrayList<SaledItemDTO> itemDTOArrayList = salesInfoDAO.getAllItem();
		for(int i=0; i<itemDTOArrayList.size(); i++){
			userRow = new Vector();
			SaledItemDTO itemDTO = itemDTOArrayList.get(i);
			userRow.add(i+1);
			userRow.add(itemDTO.getPayTime());
			userRow.add(itemDTO.getTotalPrice());
			model.addRow(userRow);
		}
		table.setEnabled(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.rowAtPoint(e.getPoint());
				if(e.getClickCount() == 2){
					int itemNo = (int) table.getValueAt(rowNum, 0);
					String paytime = (String) table.getValueAt(rowNum, 1);
					int totalCost =(int) table.getValueAt(rowNum, 2);
					new UI_DetailedSellRecord(itemNo, paytime, totalCost);
					System.out.println(rowNum);
				}
			}
		});

	}

	class UI_DetailedSellRecord extends JFrame {
		private JTable table;
		private JTable table_1;
		private Vector<String> cols = null;
		private Vector<String> rows = null;
		private SalesInfoDAO salesInfoDAO = new SalesInfoDAO();
		public UI_DetailedSellRecord(int itemNo, String paytime, int totalCost) {
			getContentPane().setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 10, 579, 43);
			getContentPane().add(scrollPane);
			cols = new Vector();
			rows = new Vector();
			cols.add("판매 번호");
			cols.add("계산 시간");
			cols.add("판매 금액");
			rows.add(String.valueOf(itemNo));
			rows.add(String.valueOf(paytime));
			rows.add(String.valueOf(totalCost));
			Vector temp = new Vector();
			temp.add(rows);
			table = new JTable();
			table.setModel(new DefaultTableModel(
					temp, cols
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

			rows = salesInfoDAO.getAllInfo(itemNo);

			table_1 = new JTable();
			table_1.setModel(new DefaultTableModel(
					rows, cols
			));
			scrollPane_1.setViewportView(table_1);
			this.setResizable(false);
			this.setSize(600, 500);
			this.setVisible(true);
		}

	}

}
