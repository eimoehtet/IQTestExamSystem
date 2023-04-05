package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import Pack.DBConnection;
import Pack.mySQLQueries;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Dimension;

public class TestResult extends JPanel {
	private JTable resultTable = new JTable();
	JComboBox combo = new JComboBox();
	PreparedStatement pst;
	ResultSet rs;
	Connection conn=null;
	DefaultTableModel dtm =new DefaultTableModel();
	DBConnection connect = new DBConnection();
	mySQLQueries msql=new mySQLQueries();
	/**
	 * Create the panel.
	 */
	public TestResult() {
		setBackground(new Color(250, 250, 210));
		
		try {
			conn=DBConnection.GetMySQLConnection();
		}catch(Exception e) {
			System.out.println(e);
		}
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 722, 337);
		add(scrollPane);
		resultTable.setModel(new DefaultTableModel(
				new Object[][] {
					{"BR-000001", "Aung Aung", "4/kata(N)8989", 10, 6, 8},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
					"No","ID", "Name", "NRC", "PartI Mark", "PartII Mark", "PartIII Mark","Total"
				}
			));
		
		scrollPane.setViewportView(resultTable);
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String id=combo.getSelectedItem().toString();
				searchData(id);
			}
		});
		
	
		combo.setBounds(10, 56, 150, 25);
		add(combo);
		
		JLabel lblNewLabel = new JLabel("Test Results");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(317, 11, 141, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search by Id");
		lblNewLabel_1.setBounds(170, 59, 150, 19);
		add(lblNewLabel_1);
		createTable();
		loadResults();
		loadStudentData();
		fillType();
	}
	public void createTable() {
		dtm.addColumn("No");
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("NRC");
		dtm.addColumn("PartI Mark");
		dtm.addColumn("PartII Mark");
		dtm.addColumn("PartIII Mark");
		dtm.addColumn("Total");
		resultTable.setModel(dtm);
		
		setColumnWidth(0,50);
		setColumnWidth(1,150);
		setColumnWidth(2,200);
		setColumnWidth(3,250);
		setColumnWidth(4,100);
		setColumnWidth(5,100);
		setColumnWidth(6,100);
		setColumnWidth(7, 100);
		resultTable.setRowHeight(30);
		resultTable.setTableHeader(new JTableHeader(resultTable.getColumnModel()) {
			  @Override public Dimension getPreferredSize() {
			    Dimension d = super.getPreferredSize();
			    d.height = 35;
			    return d;
			  }
		});
	}
	public void setColumnWidth(int index,int width) {
		DefaultTableColumnModel tcm=(DefaultTableColumnModel)resultTable.getColumnModel();
		TableColumn tc=tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	String[] results=new String[8];
	public void loadStudentData() {
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			for(int i=0;i<dtm.getRowCount();i++) {
				String query="select studentName,nrcNo from Student where studentId='"+dtm.getValueAt(i, 1)+"'";
				ResultSet rs2=stmt.executeQuery(query);
				while(rs2.next()) {
					dtm.setValueAt(rs2.getString(1), i,2);
					dtm.setValueAt(rs2.getString(2), i,3);

				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadResults() {
		
		
		try {
			Statement stmt= conn.createStatement();
			String str="select * from TestResult";
			
			ResultSet rs=stmt.executeQuery(str);
			while(rs.next()) {
				results[0]=rs.getString(1);
				results[1]=rs.getString(2);
				results[4]=rs.getString(4);
				results[5]=rs.getString(5);
				int p1=rs.getInt(3);
				int p2=rs.getInt(4);
				int p3=rs.getInt(5);
				
				results[7]=String.valueOf(p1+p2+p3);
				dtm.addRow(results);
				
			}
			
			
			resultTable.setModel(dtm);
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	public void searchData(String query) {
		TableRowSorter<DefaultTableModel>tr=new TableRowSorter<DefaultTableModel>(dtm);
		
		String txt="-Selected-";
		if(query.equals(txt)) {
			resultTable.setRowSorter(tr);
		}
		else {
			resultTable.setRowSorter(tr);
			tr.setRowFilter(RowFilter.regexFilter(query));
			
			
		}
	}
	public void fillType() {
		
		combo.addItem("-Selected-");
		for(int i=0;i<dtm.getRowCount();i++) {
			combo.addItem(dtm.getValueAt(i, 1));
		}
		
	}
}
