package UIClass;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class salesList extends JFrame {
	private final JButton exitButton = new JButton("취소");
	private JScrollPane scroll;
	public salesList() {
		getContentPane().setLayout(null);
		exitButton.setBounds(747, 365, 117, 50);
		getContentPane().add(exitButton);
		
		JTable jtable = new JTable();
		scroll = new JScrollPane(jtable);
		scroll.setLocation(28, 76);
		scroll.setSize(710, 342);
		getContentPane().add(scroll);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 36, 710, 40);
		getContentPane().add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("매출 기간 단위 : ");
		lblNewLabel.setBounds(20, 10, 177, 15);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(747, 80, 115, 245);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblwjd = new JLabel("기간 설정");
		lblwjd.setBounds(30, 0, 91, 59);
		panel_1.add(lblwjd);
		
		JButton btnDlf = new JButton("일");
		btnDlf.setBounds(0, 45, 115, 50);
		panel_1.add(btnDlf);
		
		JButton button = new JButton("주");
		button.setBounds(0, 95, 115, 50);
		panel_1.add(button);
		
		JButton button_1 = new JButton("월");
		button_1.setBounds(0, 145, 115, 50);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("월");
		button_2.setBounds(0, 195, 115, 50);
		panel_1.add(button_2);
	}
}
