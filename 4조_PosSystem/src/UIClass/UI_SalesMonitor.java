package UIClass;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JTextField;


public class UI_SalesMonitor extends JFrame {
	private final JButton exitButton = new JButton("취소");
	private final JButton registButton = new JButton("등록");
	private final Vector<String> userColumn = new Vector<String>();
	private final JPanel listPanel;
	private  JScrollPane scrollView;	
	private  JTable jTable;	
	private DefaultTableModel model;
	private Vector<String> userRow;
	private JTextField chargedMoney;
	private JTextField receivedMoney;
	private JTextField remainedMoney;
	private JLabel chagedMoneylabel;
	private JLabel receivedMoneylabel;
	private JLabel remainedMoneylabel;

	public UI_SalesMonitor() {
		setResizable(false);
		setTitle("판매 화면");

		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UI_SalesRegister();
			}
		});
		registButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		registButton.setBounds(191, 385, 120, 50);
		getContentPane().add(registButton);
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UI_MainMenu();
				dispose();
			}
		});
		exitButton.setBounds(421, 385, 120, 50);
		exitButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		getContentPane().setLayout(null);
		getContentPane().add(exitButton);
		
		
		userColumn.addElement("물품 번호");
		userColumn.addElement("물품 이름");
		userColumn.addElement("물품 수량");
		userColumn.addElement("물품 가격");
		model = new DefaultTableModel(userColumn, 0);		
		
		jTable = new JTable(model);
		jTable.setFillsViewportHeight(true);
		jTable.setBounds(0,  0,  860, 300);
		scrollView = new JScrollPane(jTable);	
		
		listPanel = new JPanel();
		listPanel.setBounds(30, 30, 860, 300);
		listPanel.add(scrollView);
		scrollView.setBounds(0, 0, 860, 300);
		listPanel.setLayout(null);
		getContentPane().add(listPanel);
		
		DefaultTableCellRenderer dter = new DefaultTableCellRenderer();
		dter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jTable.getColumnModel();
		
		for ( int i = 0; i < tcm.getColumnCount(); i++ ) {
			tcm.getColumn(i).setCellRenderer(dter);
		}
		
		//임의로 값을 주가해봄
		userRow = new Vector<String>();
		userRow.addElement("1");
		userRow.addElement("2");
		userRow.addElement("3");
		userRow.addElement("4");
		model.addRow(userRow);
		
		
		
			
		chargedMoney = new JTextField();
		chargedMoney.setBounds(692, 387, 225, 19);
		getContentPane().add(chargedMoney);
		chargedMoney.setColumns(10);
		
		receivedMoney = new JTextField();
		receivedMoney.setColumns(10);
		receivedMoney.setBounds(692, 404, 225, 19);
		getContentPane().add(receivedMoney);
		
		remainedMoney = new JTextField();
		remainedMoney.setColumns(10);
		remainedMoney.setBounds(692, 422, 225, 19);
		getContentPane().add(remainedMoney);
		
		chagedMoneylabel = new JLabel("총 금액");
		chagedMoneylabel.setBounds(594, 391, 106, 15);
		getContentPane().add(chagedMoneylabel);
		
		receivedMoneylabel = new JLabel("받은 금액");
		receivedMoneylabel.setBounds(594, 408, 106, 15);
		getContentPane().add(receivedMoneylabel);
		
		remainedMoneylabel = new JLabel("거스름 돈");
		remainedMoneylabel.setBounds(594, 426, 106, 15);
		getContentPane().add(remainedMoneylabel);
		
		
		this.setResizable(false);
		this.setSize(950, 500);
		this.setVisible(true);
	}
	
	

}
