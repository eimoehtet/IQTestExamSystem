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
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class QuestionEntry extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtOpt1;
	private JTextField txtOpt2;
	private JTextField txtOpt3;
	private JTextField txtOpt4;
	private JTextField txtAnswer;
	private JLabel lblQId = new JLabel("New label");
	private JComboBox cboTypeId = new JComboBox();
	private JTextArea textArea = new JTextArea();
	mySQLQueries msql = new mySQLQueries();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuestionEntry dialog = new QuestionEntry();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuestionEntry() {
		AutoID();
		fillType();
		setBounds(100, 100, 606, 410);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 565, 306);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(38, 0, 517, 316);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Question Entry");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel.setBounds(142, 0, 209, 26);
				panel.add(lblNewLabel);
			}
			
			JLabel lblNewLabel_1 = new JLabel("Question ID:");
			lblNewLabel_1.setBounds(22, 30, 111, 27);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Question Type ID:");
			lblNewLabel_2.setBounds(22, 92, 111, 26);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Option 1");
			lblNewLabel_3.setBounds(22, 130, 111, 24);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Option 2");
			lblNewLabel_4.setBounds(22, 165, 111, 26);
			panel.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Option 3");
			lblNewLabel_5.setBounds(22, 202, 111, 23);
			panel.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Option 4");
			lblNewLabel_6.setBounds(22, 236, 111, 27);
			panel.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Answer");
			lblNewLabel_7.setBounds(22, 272, 94, 19);
			panel.add(lblNewLabel_7);
			
			
			lblQId.setBounds(259, 30, 237, 27);
			panel.add(lblQId);
			cboTypeId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
		
			cboTypeId.setBounds(259, 90, 237, 30);
			panel.add(cboTypeId);
			
			txtOpt1 = new JTextField();
			txtOpt1.setBounds(259, 129, 237, 26);
			panel.add(txtOpt1);
			txtOpt1.setColumns(10);
			
			txtOpt2 = new JTextField();
			txtOpt2.setBounds(259, 165, 237, 26);
			panel.add(txtOpt2);
			txtOpt2.setColumns(10);
			
			txtOpt3 = new JTextField();
			txtOpt3.setBounds(259, 200, 237, 26);
			panel.add(txtOpt3);
			txtOpt3.setColumns(10);
			
			txtOpt4 = new JTextField();
			txtOpt4.setBounds(259, 236, 237, 26);
			panel.add(txtOpt4);
			txtOpt4.setColumns(10);
			
			txtAnswer = new JTextField();
			txtAnswer.setBounds(259, 269, 237, 25);
			panel.add(txtAnswer);
			txtAnswer.setColumns(10);
			
			JLabel lblNewLabel_8 = new JLabel("Question:");
			lblNewLabel_8.setBounds(22, 67, 74, 14);
			panel.add(lblNewLabel_8);
			
			
			textArea.setBounds(259, 57, 237, 26);
			panel.add(textArea);
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboTypeId.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Please Select Question Type");
					cboTypeId.requestFocus();
					
				} else if (Checking.IsNull(txtOpt1.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Option 1");
					txtOpt1.requestFocus();
					txtOpt1.selectAll();
				} else if (Checking.IsNull(txtOpt2.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Option 2");
					txtOpt2.requestFocus();
					txtOpt2.selectAll();
				} else if (Checking.IsNull(txtOpt3.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Option 3 ");
					txtOpt3.requestFocus();
					txtOpt3.selectAll();
				}else if (Checking.IsNull(txtAnswer.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Answer ");
					txtAnswer.requestFocus();
					txtAnswer.selectAll();
				}
				else {
					
					
					String[]st=new String[8];
					st[0]=lblQId.getText();
					st[1]=msql.GetTypeID((String)cboTypeId.getSelectedItem());
					
					st[2]=textArea.getText();
					st[3]=txtOpt1.getText();
					st[4]=txtOpt2.getText();
					st[5]=txtOpt3.getText();
					st[6]=txtOpt4.getText();
					st[7]=txtAnswer.getText();
					boolean save=msql.insertData("Question", st);
					if(save) {
						JOptionPane.showMessageDialog(null, "Saved Record successfully");
						AutoID();
						clear();
						cboTypeId.requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "Failed to record");
						AutoID();
					}
					}
				}
				
			
		});
		
		btnSave.setBounds(61, 324, 89, 36);
		getContentPane().add(btnSave);
		
		JButton btnClose = new JButton("Cancel");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClose.setBounds(257, 324, 89, 36);
		getContentPane().add(btnClose);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						dispose();
			}
		});
		btnCancel.setBounds(476, 324, 89, 36);
		getContentPane().add(btnCancel);
	}
	private void AutoID() {
		lblQId.setText(String.valueOf(msql.getAutoID("qId", "Question", "Q-")));
	}
	private void clear() {
		cboTypeId.setSelectedIndex(0);
		textArea.setText("");
		txtOpt1.setText("");
		txtOpt2.setText("");
		txtOpt3.setText("");
		txtOpt4.setText("");
		txtAnswer.setText("");
		
	}
	public void fillType() {
		String []str=msql.getNameForChoice("QuestionType");
		
		cboTypeId.addItem("-Selected-");
		for(int i=0;i<str.length;i++) {
			
			cboTypeId.addItem(str[i]);
		}
		
	}
}