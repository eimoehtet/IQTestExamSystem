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
import java.awt.Color;
import javax.swing.SwingConstants;

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
		getContentPane().setBackground(new Color(250, 250, 210));
		setBackground(new Color(250, 250, 210));
		AutoID();
		fillType();
		setBounds(400, 90, 688, 500);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBounds(0, 0, 662, 390);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(250, 250, 210));
			panel.setBounds(10, 0, 662, 379);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Question Entry");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNewLabel.setBounds(185, 0, 209, 26);
				panel.add(lblNewLabel);
			}
			
			JLabel lblNewLabel_1 = new JLabel("Question ID:");
			lblNewLabel_1.setBounds(43, 42, 111, 27);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Question Type ID:");
			lblNewLabel_2.setBounds(43, 148, 111, 26);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Option 1");
			lblNewLabel_3.setBounds(43, 188, 111, 24);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Option 2");
			lblNewLabel_4.setBounds(43, 229, 111, 26);
			panel.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Option 3");
			lblNewLabel_5.setBounds(43, 266, 111, 23);
			panel.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Option 4");
			lblNewLabel_6.setBounds(45, 300, 111, 27);
			panel.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Answer");
			lblNewLabel_7.setBounds(45, 338, 94, 19);
			panel.add(lblNewLabel_7);
			
			
			lblQId.setBounds(347, 42, 237, 27);
			panel.add(lblQId);
			cboTypeId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
		
			cboTypeId.setBounds(285, 146, 299, 30);
			panel.add(cboTypeId);
			
			txtOpt1 = new JTextField();
			txtOpt1.setBounds(285, 187, 299, 26);
			panel.add(txtOpt1);
			txtOpt1.setColumns(10);
			
			txtOpt2 = new JTextField();
			txtOpt2.setBounds(285, 224, 299, 26);
			panel.add(txtOpt2);
			txtOpt2.setColumns(10);
			
			txtOpt3 = new JTextField();
			txtOpt3.setBounds(285, 261, 299, 26);
			panel.add(txtOpt3);
			txtOpt3.setColumns(10);
			
			txtOpt4 = new JTextField();
			txtOpt4.setBounds(285, 298, 299, 26);
			panel.add(txtOpt4);
			txtOpt4.setColumns(10);
			
			txtAnswer = new JTextField();
			txtAnswer.setBounds(285, 335, 299, 25);
			panel.add(txtAnswer);
			txtAnswer.setColumns(10);
			
			JLabel lblNewLabel_8 = new JLabel("Question:");
			lblNewLabel_8.setBounds(46, 96, 74, 14);
			panel.add(lblNewLabel_8);
			
			
			textArea.setBounds(285, 69, 367, 66);
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
		
		btnSave.setBounds(59, 401, 89, 36);
		getContentPane().add(btnSave);
		
		JButton btnClose = new JButton("Cancel");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClose.setBounds(254, 401, 89, 36);
		getContentPane().add(btnClose);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						dispose();
			}
		});
		btnCancel.setBounds(482, 401, 89, 36);
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