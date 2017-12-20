package UIClass;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import PosDAOClass.ItemDAO;
import PosDAOClass.ItemDTO;
import PosDAOClass.ItemListDTO;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Color;


public class UI_SalesMonitor extends JFrame {
    private final JButton exitButton = new JButton("취소");
    private final JButton registButton = new JButton("등록");
    private final JButton calculateButton = new JButton("계산");
    private final Vector<String> userColumn = new Vector<String>();
    private final JPanel listPanel;
    private JScrollPane scrollView;
    private JTable jTable;
    private DefaultTableModel model;
    private Vector<String> userRow;
    private JTextField chargedMoney;
    private JTextField receivedMoney;
    private JTextField remainedMoney;
    private JLabel chagedMoneylabel;
    private JLabel receivedMoneylabel;
    private JLabel remainedMoneylabel;

    ItemListDTO itemListDTO = null;

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

        calculateButton.addActionListener(new ActionListener() { //계산을 수행하는 버튼
            public void actionPerformed(ActionEvent arg0) {
                new UI_Calculate();
            }

        });
        calculateButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
        calculateButton.setBounds(55, 385, 128, 50);
        getContentPane().add(calculateButton);

        userColumn.addElement("물품 번호");
        userColumn.addElement("물품 이름");
        userColumn.addElement("물품 수량");
        userColumn.addElement("물품 가격");
        model = new DefaultTableModel(userColumn, 0);

        jTable = new JTable(model);
        jTable.setFillsViewportHeight(true);
        jTable.setBounds(0, 0, 860, 300);
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

        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dter);
        }

        chargedMoney = new JTextField();
        chargedMoney.setHorizontalAlignment(SwingConstants.RIGHT);
        chargedMoney.setText("0");
        chargedMoney.setBounds(692, 387, 225, 19);
        getContentPane().add(chargedMoney);
        chargedMoney.setColumns(10);

        receivedMoney = new JTextField();
        receivedMoney.setHorizontalAlignment(SwingConstants.RIGHT);
        receivedMoney.setColumns(10);
        receivedMoney.setBounds(692, 404, 225, 19);
        receivedMoney.setText("0");
        getContentPane().add(receivedMoney);

        remainedMoney = new JTextField();
        remainedMoney.setHorizontalAlignment(SwingConstants.RIGHT);
        remainedMoney.setColumns(10);
        remainedMoney.setBounds(692, 422, 225, 19);
        remainedMoney.setText("0");
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


    class UI_SalesRegister extends JFrame {
        private JTextField itemid;
        private JTextField quantity;
        private JLabel msg;
        ItemListDTO itemListDTO;

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
                    itemListDTO = new ItemListDTO();
                    ArrayList<ItemDTO> items = itemListDTO.items;
                    Vector tempVector = new Vector();

                    int id = Integer.parseInt(itemid.getText());
                    int count = Integer.parseInt(quantity.getText());

                    ItemDTO tempItemDTO = items.get(id - 1);

                    if (count <= tempItemDTO.getItemStock()) {
                        tempVector.add(id);
                        tempVector.add(tempItemDTO.getItemName());
                        tempVector.add(count);
                        tempVector.add(tempItemDTO.getItemPrice());

                        model.addRow(tempVector);
                        int price = Integer.parseInt(chargedMoney.getText()) + count * tempItemDTO.getItemPrice();
                        chargedMoney.setText(price + "");

                        msg.setText("");
                    } else {
                        msg.setText("현재 보유한 재고량보다 신청한 수량이 많습니다.");
                    }

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
            msg = new JLabel();
            msg.setBounds(150, 120, 400, 20);
            msg.setForeground(Color.RED);
            msg.setFont(new Font("나눔고딕", Font.BOLD, 13));
            getContentPane().add(msg);
            this.setVisible(true);

        }
    }

    public class UI_Calculate extends JFrame{
        private JTextField itemid;

        public UI_Calculate() {
            getContentPane().setLayout(null);
            this.setSize(580, 200);
            this.setTitle("계산");
            JButton okButton = new JButton("확 인");
            okButton.setFont(new Font("나눔고딕", Font.BOLD, 19));
            okButton.setBounds(592, 12, 146, 55);
            getContentPane().add(okButton);
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    //판매 화면에서 총 금액,받은 금액,거스름 돈 을 기입해주고 현재의 정보를 SaledItemDAO에 등록하여 튜플을 추가.
                    //여기서 추가로 초기화 버튼을 만들어 판매화면의 정보를 초기화 하는 기능을 추가할 것을 추천
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

            JButton btnNewButton = new JButton("확인");
            btnNewButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
            btnNewButton.setBounds(374, 39, 146, 68);
            getContentPane().add(btnNewButton);

            JLabel ITEMID = new JLabel("받은 금액");
            ITEMID.setHorizontalAlignment(SwingConstants.CENTER);
            ITEMID.setFont(new Font("나눔고딕", Font.BOLD, 19));
            ITEMID.setBounds(14, 37, 140, 68);
            getContentPane().add(ITEMID);

            itemid = new JTextField();
            itemid.setHorizontalAlignment(SwingConstants.CENTER);
            itemid.setFont(new Font("나눔고딕", Font.BOLD, 15));
            itemid.setBounds(152, 51, 132, 44);
            getContentPane().add(itemid);
            itemid.setColumns(10);
            this.setVisible(true);
        }
    }
}