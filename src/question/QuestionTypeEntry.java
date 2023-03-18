package question;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Pack.Checking;
import Pack.mySQLQueries;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class QuestionTypeEntry extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblQtId = new JLabel("New label");
	mySQLQueries msql = new mySQLQueries();
	private JTextField txtType;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuestionTypeEntry dialog = new QuestionTypeEntry();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuestionTypeEntry() {
		AutoID();
		setBounds(100, 100, 462, 319);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 446, 280);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 426, 189);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Question Type Entry");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(108, 11, 206, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Question ID:");
		lblNewLabel_1.setBounds(40, 64, 111, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Question Type:");
		lblNewLabel_2.setBounds(40, 111, 111, 29);
		panel.add(lblNewLabel_2);
		
		
		lblQtId.setBounds(227, 64, 153, 22);
		panel.add(lblQtId);
		
		txtType = new JTextField();
		txtType.setBounds(227, 115, 124, 20);
		panel.add(txtType);
		txtType.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Checking.IsValidName(txtType.getText())) {
					JOptionPane.showMessageDialog(null, "Please Enter valid student name");
					txtType.requestFocus();
					txtType.selectAll();
				}else{
					String[]str=new String[1];
					str[0]=lblQtId.getText();
					

					boolean ee=msql.isduplicate("QuestionType", str);
					if(ee) {
						JOptionPane.showMessageDialog(null, "Duplicate Record");
						txtType.requestFocus();
						txtType.selectAll();
						AutoID();
						
					}else {
					String[]st=new String[2];
					st[0]=lblQtId.getText();
					st[1]=txtType.getText();
					boolean save=msql.insertData("QuestionType", st);
					if(save) {
						JOptionPane.showMessageDialog(null, "Saved Record successfully");
						AutoID();
						clear();
						txtType.requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "Failed to record");
						AutoID();
						
					}
					}
					
					
				}
				
			}
		});
		btnSave.setBounds(28, 211, 89, 40);
		contentPanel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnCancel.setBounds(158, 211, 89, 40);
		contentPanel.add(btnCancel);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						dispose();
			}
		});
		btnClose.setBounds(301, 211, 89, 40);
		contentPanel.add(btnClose);
	}
	private void AutoID() {
		lblQtId.setText(String.valueOf(msql.getAutoID("qtId", "QuestionType", "QT-")));
	}
	private void clear() {
		txtType.setText("");
		
	}
//	private void fillType() {
//		String str[]=msql.getIDForChoice("QuestionType");
//		cboType.addItem("-Selected-");
//		for(int i=0;i<str.length;i++) {
//			cboType.addItem(str[i].toString());
//		}
//	}
}
