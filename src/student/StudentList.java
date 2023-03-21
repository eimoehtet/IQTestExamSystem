package student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.*;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.util.ArrayList;
import java.util.Vector;

import Pack.DBConnection;
import Pack.mySQLQueries;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class StudentList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTable tblStudent=new JTable();

	PreparedStatement pst;
	ResultSet rs;
	Connection conn=null;
	DefaultTableModel dtm =new DefaultTableModel();
	DBConnection connect = new DBConnection();
	
	public static void main(String[] args) {
		
		try {
			
			StudentList dialog = new StudentList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public static String []st=new String[6];
	
	public StudentList() throws ClassNotFoundException, SQLException {
		
		try {
			conn=DBConnection.GetMySQLConnection();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 800, 350);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 800, 300);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 46, 700, 250);
		panel.add(scrollPane);

	
		scrollPane.setViewportView(tblStudent);
//		tblStudent.setModel(new DefaultTableModel(
//			new Object[][] {
//			
//			},
//			new String[] {
//				"ID", "Name", "NRC", "Email", "Phone", "Address"
//			}
//		));

		JLabel lblNewLabel = new JLabel("Student List");
		lblNewLabel.setBounds(301, 11, 170, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel);
		tblStudent.addMouseListener(new MouseAdapter() {
			   @Override
			    public void mouseClicked(final MouseEvent e) {
			        if (e.getClickCount() == 1) {
			             JTable jTable= (JTable)e.getSource();
			             int row = jTable.getSelectedRow();
			            String id = (String)jTable.getValueAt(row, 0);
			            String name=(String)jTable.getValueAt(row, 1);
			            String nrc = (String)jTable.getValueAt(row, 2);
			            String email=(String)jTable.getValueAt(row, 3);
			            String phone = (String)jTable.getValueAt(row, 4);
			            String address=(String)jTable.getValueAt(row, 5);
			            
			            st[0]=id;
			            st[1]=name;
			            st[2]=nrc;
			            st[3]=email;
			            st[4]=phone;
			            st[5]=address;
//			            for(int i=0;i<6;i++) {
//			            	System.out.println(st[i]);
//			            }
			        }
			   }
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(57, 361, 89, 34);
	
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblStudent.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please select row to update");
				}else {
					
					for(int i=0;i<6;i++) {
						System.out.println(st[i]);
					}
					
					StudentUpdate updateform=new StudentUpdate();
					
					updateform.show();
					
				}
			}
		});
		getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(208, 361, 89, 34);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.setBounds(448, 361, 89, 34);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						dispose();
			}
		});
		getContentPane().add(btnNewButton_2);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) tblStudent.getModel();
				tableModel.setRowCount(0);
				loadStudents();
			}
		});
		btnRefresh.setBounds(322, 361, 89, 34);
		getContentPane().add(btnRefresh);
		createTable();
		loadStudents();
	}
	public void createTable() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("NRC");
		dtm.addColumn("Email");
		dtm.addColumn("Phone");
		dtm.addColumn("Address");
		tblStudent.setModel(dtm);
		setColumnWidth(0,100);
		setColumnWidth(1,100);
		setColumnWidth(2,200);
		setColumnWidth(3,250);
		setColumnWidth(4,150);
		setColumnWidth(5,100);
		
	}
	public void setColumnWidth(int index,int width) {
		DefaultTableColumnModel tcm=(DefaultTableColumnModel)tblStudent.getColumnModel();
		TableColumn tc=tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	public void loadStudents() {
		
		String[] students=new String[6];
		String strquery[]=new String[6];
		try {
			Statement stmt= conn.createStatement();
			String str="select * from Student";
			ResultSet rs=stmt.executeQuery(str);
			while(rs.next()) {
				students[0]=rs.getString(1);
				students[1]=rs.getString(2);
				students[2]=rs.getString(3);
				students[3]=rs.getString(4);
				students[4]=rs.getString(5);
				students[5]=rs.getString(6);
				dtm.addRow(students);
			}
			tblStudent.setModel(dtm);
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
