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
    private TableColumnModel tcm  = null;
    private Vector<String> Row;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JLabel button_bar = new JLabel("湲곌컙 꽕젙");
    private JButton dayBtn = new JButton("씪");
    private JButton weekBtn = new JButton("二");
    private JButton monthBtn = new JButton("썡");
    private JButton quaterBtn = new JButton("遺꾧린");
    private JButton exitButton = new JButton("痍⑥냼");

    public UI_SalesList() {
        getContentPane().setLayout(null);

        //긽떒 panel
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 710, 40);
        getContentPane().add(panel);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        panel.setLayout(null);

        JLabel label1 = new JLabel("留ㅼ텧 湲곌컙 떒쐞 : ");
        label1.setFont(new Font("굹닎怨좊뵓", Font.BOLD, 12));
        label1.setBounds(12, 12, 92, 15);
        panel.add(label1);

        JLabel credit = new JLabel("씪");
        credit.setForeground(SystemColor.textHighlight);
        credit.setFont(new Font("굹닎怨좊뵓", Font.BOLD, 12));
        credit.setBounds(98, 11, 62, 16);
        panel.add(credit);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        panel_1.setBounds(747, 80, 115, 245);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);


        //踰꾪듉뱾 젙쓽 씠踰ㅽ듃 泥섎━
        button_bar.setBounds(0, 0, 115, 50);
        panel_1.add(button_bar);
        button_bar.setForeground(Color.BLACK);
        button_bar.setFont(new Font("굹닎怨좊뵓", Font.BOLD, 14));
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


        weekBtn.setBounds(0, 95, 115, 50);
        panel_1.add(weekBtn);


        monthBtn.setBounds(0, 145, 115, 50);
        panel_1.add(monthBtn);

        quaterBtn.setBounds(0, 195, 115, 50);
        panel_1.add(quaterBtn);


        //븯떒 panel(뙋留ㅻ궡뿭 由ъ뒪듃 떎젣 異쒕젰 쐞移)
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panel_2.setBounds(30, 75, 710, 350);
        panel_2.setLayout(null);

        Column.addElement("湲 媛");
        Column.addElement("珥 븸");

        model = new DefaultTableModel(Column, 0);

        JTable jtableView = new JTable(model);
        scroll = new JScrollPane(jtableView);
        scroll.setBounds(0, 0, 710, 350);
        panel_2.add(scroll);

        getContentPane().add(panel_2);

        DefaultTableCellRenderer dterCenter = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dterRight = new DefaultTableCellRenderer();
        dterCenter.setHorizontalAlignment(SwingConstants.CENTER);
        dterRight.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tcm = jtableView.getColumnModel();
        tcm.getColumn(0).setCellRenderer(dterCenter);
        tcm.getColumn(1).setCellRenderer(dterRight);

        //default (씪)
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
        
        this.setTitle("留ㅼ텧 議고쉶");
        this.setResizable(false);
        this.setSize(900, 500);
        this.setVisible(true);

        dayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("씪");
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
                
                tcm = jtableView.getColumnModel();
                tcm.getColumn(0).setCellRenderer(dterCenter);
                tcm.getColumn(1).setCellRenderer(dterRight);
                
            }
     
        });
        
        weekBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("二");
            }
        });
        
        monthBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("썡");
                
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
                        one_time = o_time.substring(0, 7);

                    }
                    else {
                        // 2017-12-12 12:12:00
                        two_time = o_time.substring(0, 7);
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
                
                tcm = jtableView.getColumnModel();
                tcm.getColumn(0).setCellRenderer(dterCenter);
                tcm.getColumn(1).setCellRenderer(dterRight);
            }
        });
        
        quaterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit.setText("遺꾧린");

                model = new DefaultTableModel(Column, 0);
                jtableView.setModel(model);

                ArrayList<SaledItemDTO> list = salesInfoDAO.getAllItem();

                SaledItemDTO items = null;
                String o_time = "";

                int totalprice = 0;
                String quater = "";
                
                int one_m_time = -1;
                String one_y_time = "";
                int two_m_time = -1;
                String two_y_time = "";
                Vector row = new Vector();
                
                int listNum = 1;
                for( int i = 0; i < list.size(); i++ ) {
                    items = list.get(i);
                    o_time = items.getPayTime();
                    if ( i == 0 ) {
                        totalprice = items.getTotalPrice();
                        one_y_time = o_time.substring(2, 4);
                        one_m_time = (Integer.parseInt(o_time.substring(5, 7)) -1 ) / 3 + 1;
                        quater = one_y_time + "뀈 " + one_m_time  + "遺꾧린";
                    }
                    else {
                        // 2017-12-12 12:12:00
                    	o_time.substring(5, 7);
                        two_y_time = o_time.substring(2, 4);
                        two_m_time = (Integer.parseInt(o_time.substring(5, 7)) -1 ) / 3 + 1;
                        
                        if ( one_y_time.equals(two_y_time) && one_m_time == two_m_time) {
                            totalprice += items.getTotalPrice();

                        }
                        else {
                            row.add(quater);
                            row.add(totalprice);
                            model.addRow(row);
                            row = new Vector();

                            quater = two_y_time + "뀈 " + two_m_time + "遺꾧린";
                            one_y_time = two_y_time;
                            one_m_time = two_m_time;
                            totalprice = items.getTotalPrice();
                        }

                    }
                }
                row.add(quater);
                row.add(totalprice);
                model.addRow(row);
                row = new Vector();
                
                tcm = jtableView.getColumnModel();
                tcm.getColumn(0).setCellRenderer(dterCenter);
                tcm.getColumn(1).setCellRenderer(dterRight);
            }
        });
    }
}
