package UIClass;
import PosDAOClass.SaledItemDTO;
import PosDAOClass.SalesInfoDAO;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	}
//	public static void main(String[] args) {
//		new UI_Sell_History();
//	}
}
