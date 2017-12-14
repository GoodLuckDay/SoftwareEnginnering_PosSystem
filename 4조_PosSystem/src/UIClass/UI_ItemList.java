package UIClass;
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

public class UI_ItemList extends JFrame {
	public UI_ItemList() {
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("물품 등록");
		btnNewButton.setFont(new Font("나눔고딕", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(181, 406, 131, 39);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setFont(new Font("나눔고딕", Font.BOLD, 18));
		btnNewButton_1.setBounds(450, 406, 125, 39);
		getContentPane().add(btnNewButton_1);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "GOD", "1", "500"},
			},
			new String[] {
				"\uC0C1\uD488 \uBC88\uD638", "\uC0C1\uD488\uBA85", "\uC7AC\uACE0", "\uAC00\uACA9"
			}
		));
		table.getColumnModel().getColumn(3).setMinWidth(20);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 22, 748, 351);
		getContentPane().add(scroll);
		this.setSize(1000, 1000);
		this.setVisible(true);
	}
}
