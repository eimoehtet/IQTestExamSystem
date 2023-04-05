package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Pack.DBConnection;
import Pack.mySQLQueries;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class StuListPanel extends JPanel {

	
	DefaultTableModel dtm=new DefaultTableModel();
	PreparedStatement pst;
	ResultSet rs;
	Connection conn=null;
	private JTable table = new JTable();
	mySQLQueries msql=new mySQLQueries();
	public static String []st=new String[6];
	/**
	 * Create the panel.
	 */
	public StuListPanel() {
		setBorder(null);
		setBackground(SystemColor.textHighlightText);
		
		try {
			conn=DBConnection.GetMySQLConnection();
		}catch(Exception e) {
			System.out.println(e);
		}
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(250, 250, 210)));
		scrollPane.setBounds(20, 64, 713, 349);
		add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "NRC", "Email", "Phone", "Address"
			}
		));
		table.addMouseListener(new MouseAdapter() {
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

			        }
			   }
		});
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 250, 250));
		btnUpdate.setBackground(new Color(255, 204, 51));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(table.getSelectedRow()<0) {
						JOptionPane.showMessageDialog(null, "Please select row to update");
					}else {
						
					
						StudentUpdate updateform=new StudentUpdate();
						
						updateform.show();
						
					}
				}
			
		});
		btnUpdate.setBounds(89, 424, 89, 34);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 250, 250));
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("delete call");
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Please select row to delete");
				}else {
					System.out.println("else work");
					
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{
						
						msql.deleteRecord("Student",st[0]);
					}
				
					
				}
			}
		});
		btnDelete.setBounds(283, 424, 89, 34);
		add(btnDelete);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(new Color(144, 238, 144));
		btnRefresh.setForeground(new Color(255, 250, 250));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				loadStudents();
			}
		});
		btnRefresh.setBounds(500, 424, 89, 34);
		add(btnRefresh);
		
		JButton btnCreate = new JButton("Create Student");
		btnCreate.setForeground(new Color(255, 250, 250));
		btnCreate.setBackground(new Color(30, 144, 255));
		btnCreate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentEntry stuEntry=new StudentEntry();
				stuEntry.show();
			}
		});
		btnCreate.setBounds(589, 11, 142, 42);
		add(btnCreate);
		
		JLabel lblNewLabel = new JLabel("Student List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(183, 18, 148, 29);
		add(lblNewLabel);
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
	
		
		setColumnWidth(0,50);
		setColumnWidth(1,100);
		setColumnWidth(2,200);
		setColumnWidth(3,250);
		setColumnWidth(4,150);
		setColumnWidth(5,100);
		
		table.setRowHeight(30);
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
			  @Override public Dimension getPreferredSize() {
			    Dimension d = super.getPreferredSize();
			    d.height = 35;
			    return d;
			  }
		});
	}
	public void setColumnWidth(int index,int width) {
		DefaultTableColumnModel tcm=(DefaultTableColumnModel)table.getColumnModel();
		TableColumn tc=tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

public void loadStudents() {
		System.out.println("called");
		String[] students=new String[6];
		String strquery[]=new String[6];
		try {
			Statement stmt= conn.createStatement();
			String str="select * from Student";
			ResultSet rs=stmt.executeQuery(str);
			
			while(rs.next()) {
				//System.out.print(rs.getString(1));
				students[0]=rs.getString(1);
				students[1]=rs.getString(2);
				students[2]=rs.getString(3);
				students[3]=rs.getString(4);
				students[4]=rs.getString(5);
				students[5]=rs.getString(6);
				
				
				dtm.addRow(students);
			}
			table.setModel(dtm);
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
