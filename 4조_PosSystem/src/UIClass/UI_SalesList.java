package UIClass;

import PosDAOClass.SaledItemDTO;
import PosDAOClass.SalesInfoDAO;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;


public class UI_SalesList extends JFrame {
    private Vector<String> Column = new Vector<String>();
    private SalesInfoDAO salesInfoDAO = new SalesInfoDAO();
    private Vector<String> Row;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JLabel button_bar = new JLabel("기간 설정");
    private JButton dayBtn = new JButton("일");
    private JButton weekBtn = new JButton("주");
    private JButton monthBtn = new JButton("월");
    private JButton quaterBtn = new JButton("분기");
    private JButton exitButton = new JButton("취소");

    public UI_SalesList() {
        getContentPane().setLayout(null);

        //상단 panel
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 710, 40);
        getContentPane().add(panel);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        panel.setLayout(null);

        JLabel label1 = new JLabel("매출 기간 단위 : ");
        label1.setFont(new Font("나눔고딕", Font.BOLD, 12));
        label1.setBounds(12, 12, 92, 15);
        panel.add(label1);

        JLabel credit = new JLabel("일");
        credit.setForeground(SystemColor.textHighlight);
        credit.setFont(new Font("나눔고딕", Font.BOLD, 12));
        credit.setBounds(98, 11, 62, 16);
        panel.add(credit);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        panel_1.setBounds(747, 80, 115, 245);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);


        //버튼들 정의와 이벤트 처리
        button_bar.setBounds(0, 0, 115, 50);
        panel_1.add(button_bar);
        button_bar.setForeground(Color.BLACK);
        button_bar.setFont(new Font("나눔고딕", Font.BOLD, 14));
        button_bar.setHorizontalAlignment(SwingConstants.CENTER);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UI_MainMenu();
                dispose();
            }
        });
        exitButton.setBounds(747, 365, 115, 50);
        getContentPane().add(exitButton);
        dayBtn.setBounds(0, 45, 115, 50);
        panel_1.add(dayBtn);


        weekBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("주");
            }
        });
        weekBtn.setBounds(0, 95, 115, 50);
        panel_1.add(weekBtn);


        monthBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("월");
            }
        });
        monthBtn.setBounds(0, 145, 115, 50);
        panel_1.add(monthBtn);


        quaterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("분기");
            }
        });
        quaterBtn.setBounds(0, 195, 115, 50);
        panel_1.add(quaterBtn);


        //하단 panel(판매내역 리스트 실제 출력 위치)
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new

                LineBorder(Color.LIGHT_GRAY));
        panel_2.setBounds(30, 75, 710, 350);
        panel_2.setLayout(null);

        Column.addElement("기 간");
        Column.addElement("총 액");

        model = new

                DefaultTableModel(Column, 0);

        JTable jtableView = new JTable(model);
        scroll = new

                JScrollPane(jtableView);
        scroll.setBounds(0, 0, 710, 350);
        panel_2.add(scroll);

        getContentPane().

                add(panel_2);

        DefaultTableCellRenderer dterCenter = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dterRight = new DefaultTableCellRenderer();
        dterCenter.setHorizontalAlignment(SwingConstants.CENTER);
        dterRight.setHorizontalAlignment(SwingConstants.RIGHT);
        TableColumnModel tcm = jtableView.getColumnModel();

        tcm.getColumn(0).

                setCellRenderer(dterCenter);
        tcm.getColumn(1).

                setCellRenderer(dterRight);


        Row = new Vector<String>();
        Row.addElement("17년 10월");
        Row.addElement("1,400,000 (원)");
        model.addRow(Row);

        this.setTitle("매출 조회");
        this.setResizable(false);
        this.setSize(900, 500);
        this.setVisible(true);

        dayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("일");
                model = new DefaultTableModel(Column, 0);
                jtableView.setModel(model);

                ArrayList<SaledItemDTO> list = salesInfoDAO.getAllItem();

                SaledItemDTO items = null;
                String o_time = "";
                int totalprice = 0;
                String one_time = "";
                String two_time = "";
                Vector row = new Vector();
                int listNum = 1;
                for( int i = 0; i < list.size(); i++ ) {
                    items = list.get(i);
                    o_time = items.getPayTime();
                    if ( i == 0 ) {
                        totalprice = items.getTotalPrice();
                        one_time = o_time.substring(0, 10);

                    }
                    else {
                        // 2017-12-12 12:12:00
                        two_time = o_time.substring(0, 10);
                        if ( one_time.equals(two_time) ) {
                            totalprice += items.getTotalPrice();

                        }
                        else {
                            row.add(one_time);
                            row.add(totalprice);
                            model.addRow(row);
                            row = new Vector();

                            one_time = two_time;
                            totalprice = items.getTotalPrice();
                        }

                    }
                }
                row.add(one_time);
                row.add(totalprice);
                model.addRow(row);
                row = new Vector();
            }
        });
    }
}
