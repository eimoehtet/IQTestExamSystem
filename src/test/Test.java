package test;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Login.LoginForm;
import Pack.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
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
	JButton btnPre = new JButton("Previous");
	JPanel bpanel = new JPanel();
	JButton btnNext = new JButton("Next");
	PreparedStatement pst;
	ResultSet rs;
	Connection conn=null;
	Statement stmt;
	DBConnection connect = new DBConnection();
	DefaultTableModel dtm=new DefaultTableModel();
	JPanel testpanel = new JPanel();
	JLabel lblNo = new JLabel("New label");
	JLabel lblId = new JLabel("");
	static int i=0;
	 public static String givenAns=null;
	 public  int mark=0;
	 public String correctanswer;
	 public String questionId=null;
	 ArrayList userAns=new ArrayList<>();
	ArrayList corAns=new ArrayList<>();
	
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
			 stmt=conn.createStatement();
			String str="Select * from question where qtId='QT-0000001'";
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
	public void loadPart2Questions() throws SQLException {
		try {
			createTable();
			 stmt=conn.createStatement();
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
		System.out.println("it called");
		try {
			createTable();
			 stmt=conn.createStatement();
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
		dtm.addColumn("Q ID");
		dtm.addColumn("Q Type ID:");
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
			Home home=new Home();
			
			if(home.plClick) {
				
				loadP1Questions();
				increase();
				
			}else if(home.p3Click){
				loadPart3Questions();
				increase();
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		testpanel.setBounds(10, 11, 752, 320);
		contentPane.add(testpanel);
		testpanel.setLayout(null);
		bpanel.setBounds(20, 342, 755, 41);
		contentPane.add(bpanel);
		
		textArea.setBounds(22, 40, 700, 111);
		testpanel.add(textArea);
		
		bg.add(rdo1);
		bg.add(rdo2);
		bg.add(rdo3);
		bg.add(rdo4);
		rdo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//givenAns=rdo1.getText();
				
				
			}
		});
		
		rdo1.setBounds(22, 158, 407, 23);
		testpanel.add(rdo1);
		rdo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//givenAns=rdo2.getText();
				
			}
		});
		
		
		rdo2.setBounds(22, 208, 407, 23);
		testpanel.add(rdo2);
		rdo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//givenAns=rdo3.getText();
				
			}
		});
		
		
		rdo3.setBounds(21, 255, 408, 23);
		testpanel.add(rdo3);
		rdo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//givenAns=rdo4.getText();
				
			}
		});
		
		
		rdo4.setBounds(22, 295, 408, 23);
		testpanel.add(rdo4);
		
		JLabel lblNewLabel = new JLabel("No.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(25, 11, 32, 23);
		testpanel.add(lblNewLabel);
		
		
		lblNo.setBounds(69, 11, 72, 19);
		testpanel.add(lblNo);
		
		btnPre.addActionListener(new ActionListener() {
		
	
			public void actionPerformed(ActionEvent e) {
				
				
				if(i>0 ) {
					lblNo.setText(String.valueOf(i));
					i--;
					
					getUserAns();
					
					fillfield();
					
				}
				
				
			}
		});
		bpanel.setLayout(null);
		btnPre.setBounds(235, 5, 73, 23);
		
		bpanel.add(btnPre);
		
		
		lblId.setBounds(331, 11, 161, 18);
		testpanel.add(lblId);
		btnNext.setBounds(349, 5, 73, 23);
		
	
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isAnswered();
				bg.clearSelection();
				getUserAns();
				increase();
			
				submitAnswered();
			}
		});
		
		
		
		
		bpanel.add(btnNext);
		
		
		createTable();		
		
	}
	public void submitAnswered() {

		LoginForm form=new LoginForm();
			
		
		try {

			 stmt = conn.createStatement();
			 System.out.println("user ans::"+givenAns);
			String query1="Insert Into testDetail Values('" + form.data[0].toString() + "','" + lblId.getText() + "','"+ givenAns +"')";
			if (stmt.executeUpdate(query1) == 1) {
				JOptionPane.showMessageDialog(null, "Insert success");
			}else {
				JOptionPane.showMessageDialog(null, "fail");

			}
		} catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
//	public void getUserAnswers() {
//		String sql1="Select useranswer from question where qtId='QT-0000003'";
//		
//		try {
//			ResultSet rs1=stmt.executeQuery(sql1);
//			while(rs1.next()) {
//				userAns.add(rs1.getString(1));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public void getCorrectAnswers() {
		String sql2="Select answer from question where qtId='QT-0000003'";
		
		try {
			ResultSet rs2=stmt.executeQuery(sql2);
			while(rs2.next()) {
				corAns.add(rs2.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void checkAnswer() {
//			getUserAnswers();
//			getCorrectAnswers();
//			
//			for(int i=0;i<corAns.size();i++) {
//				if(userAns.get(i).equals(corAns.get(i))) {
//					mark=mark+1;
//				}
//			}
//				
//	}
//	
	public void increase() {
		lblNo.setText(String.valueOf(i+1));
			if(i<dtm.getRowCount()) {
				fillfield();
				i++;
				
			}
			else if(i==dtm.getRowCount()) {
			
				
				testpanel.hide();
				bpanel.hide();

				JPanel panel = new JPanel();
				panel.setBounds(10, 11, 752, 262);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("You answerd all questions !\r\n");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
				lblNewLabel.setBounds(254, 41, 238, 35);
				panel.add(lblNewLabel);
				
				JButton btnCheck = new JButton("Previous");
				btnCheck.setBounds(163, 96, 161, 35);
				panel.add(btnCheck);
				btnCheck.addActionListener(new ActionListener() {
					
					
					public void actionPerformed(ActionEvent e) {
						panel.hide();
						testpanel.show();
						bpanel.show();
						lblNo.setText(String.valueOf(i));
						
						if(i>0 ) {
							i--;
							getUserAns();
							
							fillfield();
							
						}
						
						
					}
				});
				
				JButton btnsubmit = new JButton("Submit");
				btnsubmit.setBounds(408, 96, 145, 35);
				panel.add(btnsubmit);

				btnsubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//checkAnswer();
						System.out.println("your mark="+mark);
					}
				});

				panel.show();
			}
				
			
			
			
		
		
	}
	public void isAnswered() {
		System.out.println("it called...");
		if(rdo1.isSelected()) {
			givenAns=rdo1.getText();
		}else if(rdo2.isSelected()) {
			givenAns=rdo2.getText();
		}else if(rdo3.isSelected()) {
			givenAns=rdo3.getText();
		}else if(rdo4.isSelected()) {
			givenAns=rdo4.getText();
		}else {
			givenAns=null;
		}
	}
	
	public void fillfield() {
		
		
		lblId.setText(dtm.getValueAt(i, 0).toString());
		textArea.setText(dtm.getValueAt(i, 2).toString());
		rdo1.setText(dtm.getValueAt(i, 3).toString());
		rdo2.setText(dtm.getValueAt(i, 4).toString());
		rdo3.setText(dtm.getValueAt(i, 5).toString());
		rdo4.setText(dtm.getValueAt(i, 6).toString());
		correctanswer=dtm.getValueAt(i, 7).toString();
	}

	public void getUserAns() {
		System.out.println("called");
	
		String q[]=new String[1];
		String sql1="Select userans from testDetail where qId='"+lblId.getText()+"'";
		try {
			ResultSet rs=stmt.executeQuery(sql1);
			if(rs.next()) {
				System.out.println("rs:"+rs.getString(1));
				if(rdo1.getText().equals(rs.getString(1))) {
					rdo1.setSelected(true);
				}
				else if(rdo2.getText().equals(rs.getString(1))) {
					rdo2.setSelected(true);
				}
				else if(rdo3.getText().equals(rs.getString(1))) {
					rdo3.setSelected(true);
				}
				else if(rdo4.getText().equals(rs.getString(1))) {
					rdo4.setSelected(true);
				}else {
					bg.clearSelection();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
