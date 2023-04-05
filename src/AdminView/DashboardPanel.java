package AdminView;

import javax.swing.JPanel;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class DashboardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DashboardPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DashboardPanel.class.getResource("/AdminView/albert-einstein-png-15.png")));
		lblNewLabel.setBounds(0, 30, 434, 356);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\"Imagination is everything.");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(464, 57, 249, 27);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("It's the preview of life's coming attractions\"");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(444, 98, 351, 27);
		add(lblNewLabel_2);
		ImageIcon image=new ImageIcon("istine.jpg");
		
	}
}
