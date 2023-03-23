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
import javax.swing.ButtonGroup;
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
	private ButtonGroup bg=new ButtonGroup();
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
	JLabel lblNo = new JLabel("New label");
	int i=0;
	 public String useranswer=null;
	 public  int mark=0;
	 public String correctanswer;

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
			String question[]=new String[7];
			
				while(rs.next()) {
					question[0]=rs.getString(3);
					question[1]=rs.getString(4);
					question[2]=rs.getString(5);
					question[3]=rs.getString(6);
					question[4]=rs.getString(7);
					question[5]=rs.getString(8);
					question[6]=rs.getString(1);
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
			String question[]=new String[8];
			
				while(rs.next()) {
					question[0]=rs.getString(1);
					question[1]=rs.getString(2);
					question[2]=rs.getString(3);
					question[3]=rs.getString(4);
					question[4]=rs.getString(5);
					question[5]=rs.getString(6);
					question[6]=rs.getString(7);
					question[7]=rs.getString(8);
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
		dtm.addColumn("ID");
	
	}
	public Test() {
		
		try {
			conn=DBConnection.GetMySQLConnection();
			
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
		
		bg.add(rdo1);
		bg.add(rdo2);
		bg.add(rdo3);
		bg.add(rdo4);
		rdo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useranswer=rdo1.getText();
				checkAnswer();
			}
		});
		
		rdo1.setBounds(22, 158, 407, 23);
		testpanel.add(rdo1);
		rdo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useranswer=rdo2.getText();
				checkAnswer();
			}
		});
		
		
		rdo2.setBounds(22, 208, 407, 23);
		testpanel.add(rdo2);
		rdo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useranswer=rdo3.getText();
				checkAnswer();
			}
		});
		
		
		rdo3.setBounds(21, 255, 408, 23);
		testpanel.add(rdo3);
		rdo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useranswer=rdo4.getText();
				checkAnswer();
			}
		});
		
		
		rdo4.setBounds(22, 295, 408, 23);
		testpanel.add(rdo4);
		
		//btnNext.setVisible(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					bg.clearSelection();
							if(i<dtm.getRowCount())	{
								textArea.setText(dtm.getValueAt(i, 2).toString());
								rdo1.setText(dtm.getValueAt(i, 3).toString());
								rdo2.setText(dtm.getValueAt(i, 4).toString());
								rdo3.setText(dtm.getValueAt(i, 5).toString());
								rdo4.setText(dtm.getValueAt(i, 6).toString());
								correctanswer=dtm.getValueAt(i, 7).toString();
								i++;
							}else {
								JOptionPane.showMessageDialog(null, "Your mark is "+mark);
							}
						//checkAnswer();
						
		}
		});
		btnNext.setBounds(459, 331, 89, 30);
		testpanel.add(btnNext);
		
		JLabel lblNewLabel = new JLabel("No.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(25, 11, 32, 23);
		testpanel.add(lblNewLabel);
		
		
		lblNo.setBounds(69, 16, 104, 14);
		testpanel.add(lblNo);
		
		
		createTable();		
		
	}
	
	public void checkAnswer() {
	
		

		System.out.println("User Ans"+useranswer);
		System.out.println("Correct Ans"+correctanswer);
		if(useranswer !=null && useranswer.equals(correctanswer)) {
			mark+=1;
		}
	System.out.println("mark="+mark);
				
	}
	
}
