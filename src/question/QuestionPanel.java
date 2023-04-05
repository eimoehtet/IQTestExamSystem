package question;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class QuestionPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public QuestionPanel() {
		setBackground(new Color(250, 250, 210));
		setLayout(null);
		
		JButton btnCreateType = new JButton("Create Question Type");
		btnCreateType.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCreateType.setForeground(new Color(255, 250, 250));
		btnCreateType.setBackground(new Color(184, 134, 11));
		btnCreateType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionTypeEntry type=new QuestionTypeEntry();
				type.show();
			}
		});
		btnCreateType.setBounds(20, 49, 170, 54);
		add(btnCreateType);
		
		JButton btnCreateQuestions = new JButton("Create Questions");
		btnCreateQuestions.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCreateQuestions.setForeground(new Color(255, 250, 250));
		btnCreateQuestions.setBackground(new Color(184, 134, 11));
		btnCreateQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionEntry question=new QuestionEntry();
				question.show();
			}
		});
		btnCreateQuestions.setBounds(216, 49, 170, 54);
		add(btnCreateQuestions);

	}
}
