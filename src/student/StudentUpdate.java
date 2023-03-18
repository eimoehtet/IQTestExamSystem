package student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Pack.Checking;
import Pack.DBConnection;
import Pack.mySQLQueries;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StudentUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStudentName=new JTextField();
	private JTextField txtNrc=new JTextField();
	private JTextField txtPhone=new JTextField();
	private JTextField txtAddress=new JTextField();
	private JTextField txtEmail=new JTextField();
	private JLabel lblStudentId = new JLabel();
	

	mySQLQueries msql = new mySQLQueries();
	Connection conn;
	DBConnection db=new DBConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StudentEntry dialog = new StudentEntry();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ClassNotFoundException 
	 */
	public void getStudent() {
		try {
			StudentList list=new StudentList();
			for(int i=0;i<6;i++) {
				System.out.println(list.st[i]);
			}
			lblStudentId.setText(list.st[0]);
			txtStudentName.setText(list.st[1]);
			txtNrc.setText(list.st[2]);
			txtEmail.setText(list.st[3]);
			txtPhone.setText(list.st[4]);
			txtAddress.setText(list.st[5]);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
	public StudentUpdate() {
		
		getStudent();
		
		setBounds(100, 100, 567, 381);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 544, 324);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 524, 264);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student ID:");
		lblNewLabel.setBounds(21, 49, 113, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Student Name:");
		lblNewLabel_1.setBounds(21, 74, 113, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("NRC Number:");
		lblNewLabel_2.setBounds(21, 108, 101, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(21, 145, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Phone Number:");
		lblNewLabel_4.setBounds(21, 179, 80, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Address:");
		lblNewLabel_5.setBounds(21, 218, 46, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Student Update Form");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_6.setBounds(159, 0, 208, 27);
		panel.add(lblNewLabel_6);

		
		txtStudentName.setBounds(281, 71, 182, 26);
		panel.add(txtStudentName);
		txtStudentName.setColumns(10);

		lblStudentId.setBounds(281, 38, 182, 25);
		panel.add(lblStudentId);

		
		txtNrc.setBounds(281, 105, 182, 26);
		panel.add(txtNrc);
		txtNrc.setColumns(10);

		
		txtPhone.setBounds(281, 176, 182, 26);
		panel.add(txtPhone);
		txtPhone.setColumns(10);

	
		txtAddress.setBounds(281, 215, 182, 28);
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		
		txtEmail.setColumns(10);
		txtEmail.setBounds(281, 142, 182, 26);
		panel.add(txtEmail);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Checking.IsValidName(txtStudentName.getText())) {
					JOptionPane.showMessageDialog(null, "Please Enter valid student name");
					txtStudentName.requestFocus();
					txtStudentName.selectAll();
				} else if (Checking.IsNull(txtNrc.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Student NRC Number");
					txtNrc.requestFocus();
					txtNrc.selectAll();
				} else if (Checking.IsNull(txtPhone.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Phone Number");
					txtPhone.requestFocus();
					txtPhone.selectAll();
				} else if (Checking.IsNull(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Email ");
					txtEmail.requestFocus();
					txtEmail.selectAll();
				}else if (Checking.IsNull(txtAddress.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter Address ");
					txtAddress.requestFocus();
					txtAddress.selectAll();
				}else {
					String[]st=new String[5];
					st[0]=txtStudentName.getText();
					st[1]=txtNrc.getText();
					st[2]=txtEmail.getText();
					st[3]=txtPhone.getText();
					st[4]=txtAddress.getText();
					boolean save=msql.updateRecord("Student",lblStudentId.getText(), st);
					if(save) {
						JOptionPane.showMessageDialog(null, "Updated Record successfully");
					
						clear();
						txtStudentName.requestFocus();
					}else {
						JOptionPane.showMessageDialog(null, "Failed to update record");
						
					}
					
				}

			}
		});
		btnUpdate.setBounds(51, 275, 89, 38);
		contentPanel.add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				txtStudentName.requestFocus();
			}
		});
		btnCancel.setBounds(206, 275, 89, 38);
		contentPanel.add(btnCancel);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						dispose();
			
			}
		});
		btnClose.setBounds(355, 275, 89, 38);
		contentPanel.add(btnClose);
	}


	private void clear() {
		txtStudentName.setText("");
		txtNrc.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		
	}
}
