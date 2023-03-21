package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Pack.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test extends JFrame {

	private JPanel contentPane;
	JTextArea textArea = new JTextArea();
	JRadioButton rdo1 = new JRadioButton("");
	JRadioButton rdo2 = new JRadioButton("");
	JRadioButton rdo3 = new JRadioButton("");
	JRadioButton rdo4 = new JRadioButton("");
	JButton btnNext = new JButton("Next");
	PreparedStatement pst;
	ResultSet rs;
	Connection conn=null;
	DBConnection connect = new DBConnection();
	DefaultTableModel dtm=new DefaultTableModel();
	JPanel testpanel = new JPanel();
	 int i=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	int rowCount=0;
	
	public void loadP1Questions() {
		try {
			createTable();
			Statement stmt=conn.createStatement();
			String str="Select * from question where qtId='QT-0000001'";
			ResultSet rs=stmt.executeQuery(str);
			String question[]=new String[6];
			
				while(rs.next()) {
					question[0]=rs.getString(3);
					question[1]=rs.getString(4);
					question[2]=rs.getString(5);
					question[3]=rs.getString(6);
					question[4]=rs.getString(7);
					question[5]=rs.getString(8);
					dtm.addRow(question);
					
					
				}
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadPart2Questions() throws SQLException {
		try {
			createTable();
			Statement stmt=conn.createStatement();
			String str="Select * from question where qtId='QT-0000002'";
			ResultSet rs=stmt.executeQuery(str);
			String question[]=new String[6];
			
				while(rs.next()) {
					question[0]=rs.getString(3);
					question[1]=rs.getString(4);
					question[2]=rs.getString(5);
					question[3]=rs.getString(6);
					question[4]=rs.getString(7);
					question[5]=rs.getString(8);
					dtm.addRow(question);
					
					
				}
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadPart3Questions() throws SQLException {
		try {
			createTable();
			Statement stmt=conn.createStatement();
			String str="Select * from question where qtId='QT-0000003'";
			ResultSet rs=stmt.executeQuery(str);
			String question[]=new String[6];
			
				while(rs.next()) {
					question[0]=rs.getString(3);
					question[1]=rs.getString(4);
					question[2]=rs.getString(5);
					question[3]=rs.getString(6);
					question[4]=rs.getString(7);
					question[5]=rs.getString(8);
					dtm.addRow(question);
					
					
				}
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createTable() {
		dtm.addColumn("Question");
		dtm.addColumn("Option 1");
		dtm.addColumn("Option 2");
		dtm.addColumn("Option 3");
		dtm.addColumn("Option 4");
		dtm.addColumn("Answer");
	
	}
	public Test() {
		
		try {
			conn=DBConnection.GetMySQLConnection();
			//loadQuestions();
		}catch(Exception e) {
			System.out.println(e);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 145, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnp2 = new JButton("PART II");
		btnp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadPart2Questions();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnp2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnp2.setBounds(0, 146, 145, 62);
		panel.add(btnp2);
		
		JButton btnp1 = new JButton("PART I");
		btnp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadP1Questions();
			}
		});
		btnp1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnp1.setBounds(0, 54, 145, 62);
		panel.add(btnp1);
		
		JButton btnp3 = new JButton("PART III");
		btnp3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnNext.setVisible(true);
				try {
					loadPart3Questions();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnp3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnp3.setBounds(0, 244, 145, 62);
		panel.add(btnp3);
		
		
		testpanel.setBounds(165, 11, 597, 372);
		contentPane.add(testpanel);
		testpanel.setLayout(null);
		
		
		textArea.setBounds(22, 40, 565, 111);
		testpanel.add(textArea);
		
		
		rdo1.setBounds(22, 158, 407, 23);
		testpanel.add(rdo1);
		
		
		rdo2.setBounds(22, 208, 407, 23);
		testpanel.add(rdo2);
		
		
		rdo3.setBounds(21, 255, 408, 23);
		testpanel.add(rdo3);
		
		
		rdo4.setBounds(22, 295, 408, 23);
		testpanel.add(rdo4);
		
		//btnNext.setVisible(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

							if(i<dtm.getRowCount())	{
								textArea.setText(dtm.getValueAt(i, 0).toString());
								rdo1.setText(dtm.getValueAt(i, 1).toString());
								rdo2.setText(dtm.getValueAt(i, 2).toString());
								rdo3.setText(dtm.getValueAt(i, 3).toString());
								rdo4.setText(dtm.getValueAt(i, 4).toString());
								i++;
							}else {
								JOptionPane.showMessageDialog(null, "You answerd all questions");
							}
							
							
								
		}
		});
		btnNext.setBounds(459, 331, 89, 30);
		testpanel.add(btnNext);
		
		JLabel lblNewLabel = new JLabel("No.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(25, 11, 32, 23);
		testpanel.add(lblNewLabel);
		
		JLabel lblNo = new JLabel("New label");
		lblNo.setBounds(69, 16, 46, 14);
		testpanel.add(lblNo);
		
		
		createTable();		
		
	}
}
