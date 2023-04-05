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
import java.awt.HeadlessException;
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
import javax.swing.JRootPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test extends JFrame {

	private JPanel contentPane;
	JTextArea textArea = new JTextArea();
	private ButtonGroup bg = new ButtonGroup();
	JRadioButton rdo1 = new JRadioButton("");
	JRadioButton rdo2 = new JRadioButton("");
	JRadioButton rdo3 = new JRadioButton("");
	JRadioButton rdo4 = new JRadioButton("");
	JButton btnPre = new JButton("Previous");
	JPanel bpanel = new JPanel();
	JButton btnNext = new JButton("Next");
	JButton btnReNext=new JButton("Next");
	PreparedStatement pst;
	ResultSet rs;
	Connection conn = null;
	Statement stmt;
	DBConnection connect = new DBConnection();
	DefaultTableModel dtm = new DefaultTableModel();
	JPanel testpanel = new JPanel();
	JLabel lblNo = new JLabel("New label");
	JLabel lblId = new JLabel("");
	static int i = 0;
	public static String givenAns = null;
	public int p1mark = 0;
	public int p2mark = 0;
	public int p3mark = 0;
	public String correctanswer;
	public String questionId = null;
	ArrayList p1userAns = new ArrayList<>();
	ArrayList p3userAns = new ArrayList<>();
	ArrayList p1Ans = new ArrayList<>();
	ArrayList p2Ans = new ArrayList<>();
	ArrayList p3Ans = new ArrayList<>();
	String partId;
	LoginForm form = new LoginForm();
	boolean alreadyAnswerd=false;

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

	int rowCount = 0;

	public void loadP1Questions() {
		try {
			createTable();
			stmt = conn.createStatement();
			String str = "Select * from question where qtId='QT-0000001'";
			ResultSet rs = stmt.executeQuery(str);
			String question[] = new String[8];

			while (rs.next()) {

				question[0] = rs.getString(1);
				question[1] = rs.getString(2);
				question[2] = rs.getString(3);
				question[3] = rs.getString(4);
				question[4] = rs.getString(5);
				question[5] = rs.getString(6);
				question[6] = rs.getString(7);
				question[7] = rs.getString(8);
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
			stmt = conn.createStatement();
			String str = "Select * from question where qtId='QT-0000002'";
			ResultSet rs = stmt.executeQuery(str);
			String question[] = new String[8];

			while (rs.next()) {
				question[0] = rs.getString(1);
				question[1] = rs.getString(2);
				question[2] = rs.getString(3);
				question[3] = rs.getString(4);
				question[4] = rs.getString(5);
				question[5] = rs.getString(6);
				question[6] = rs.getString(7);
				question[7] = rs.getString(8);
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
			stmt = conn.createStatement();
			String str = "Select * from question where qtId='QT-0000003'";
			ResultSet rs = stmt.executeQuery(str);
			String question[] = new String[8];

			while (rs.next()) {

				question[0] = rs.getString(1);
				question[1] = rs.getString(2);
				question[2] = rs.getString(3);
				question[3] = rs.getString(4);
				question[4] = rs.getString(5);
				question[5] = rs.getString(6);
				question[6] = rs.getString(7);
				question[7] = rs.getString(8);

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				i=0;
			}
		});
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			conn = DBConnection.GetMySQLConnection();
			Home home = new Home();

			if (home.plClick) {
				i=0;
				loadP1Questions();
				increase();

			} else if (home.p3Click) {
				i=0;
				loadPart3Questions();
				increase();
			} else if (home.p2Click) {
				i=0;
				loadPart2Questions();
				increase();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		setBounds(500, 100, 801, 433);
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
				 givenAns=rdo1.getText();
				

			}
		});

		rdo1.setBounds(22, 158, 407, 23);
		testpanel.add(rdo1);
		rdo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 givenAns=rdo2.getText();
				

			}
		});

		rdo2.setBounds(22, 208, 407, 23);
		testpanel.add(rdo2);
		rdo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 givenAns=rdo3.getText();
				

			}
		});

		rdo3.setBounds(21, 255, 408, 23);
		testpanel.add(rdo3);
		rdo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 givenAns=rdo4.getText();
				

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
				btnNext.setVisible(false);
				btnReNext.setVisible(true);

				if (i > 0) {
					lblNo.setText(String.valueOf(i));
					i--;
					
					System.out.println("given ans=="+givenAns);
					getUserAns();
					updateAnswer();
					
					fillfield();
					
					
					

				}

			}
		});
		bpanel.setLayout(null);
		btnPre.setBounds(10, 5, 86, 23);

		bpanel.add(btnPre);

		lblId.setBounds(331, 11, 161, 18);
		testpanel.add(lblId);
				btnReNext.setBounds(626, 5, 86, 23);
				bpanel.add(btnReNext);
						btnNext.setBounds(626, 5, 86, 23);
						bpanel.add(btnNext);
				
						btnNext.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								btnReNext.setVisible(false);
								isAnswered();
								bg.clearSelection();
								submitAnswered();
								increase();
				
							}
						});
				btnReNext.setVisible(false);
				btnReNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						getUserAns();
						updateAnswer();
						increase();

					}
				});
		
		
		createTable();

	}


	public void submitAnswered() {
		try {

			stmt = conn.createStatement();

			String query1 = "Insert Into testDetail Values('" + form.data[0].toString() + "','" + lblId.getText()
					+ "','" + givenAns + "','" + partId + "')";
			
			if (stmt.executeUpdate(query1) == 1) {
				alreadyAnswerd=true;
			} else {
				JOptionPane.showMessageDialog(null, "fail");

			}
		} catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getP3UserAnswers() {
		String sql1 = "Select userans from testDetail where studentId= '" + form.data[0].toString() +  "'&& partId='QT-0000003'";

		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				p3userAns.add(rs1.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getP1UserAnswers() {
		String sql1 = "Select userans from testDetail where studentId= '" + form.data[0].toString() + "'&& partId='QT-0000001'";

		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				p1userAns.add(rs1.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void getP3Answers() {
		String sql2 = "Select answer from question where qtId='QT-0000003'";

		try {
			ResultSet rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				p3Ans.add(rs2.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getP2Answers() {
		String sql2 = "Select answer from question where qtId='QT-0000002'";

		try {
			ResultSet rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				p2Ans.add(rs2.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getP1Answers() {
		String sql2 = "Select answer from question where qtId='QT-0000001'";

		try {
			ResultSet rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				p1Ans.add(rs2.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkAnswer() {
		getP1UserAnswers();
		getP3UserAnswers();
		getP1Answers();
		getP2Answers();
		getP3Answers();

		for (int i = 0; i < p1userAns.size(); i++) {
			if (p1userAns.get(i).equals(p1Ans.get(i))) {
				p1mark = p1mark + 1;
			}
			
		}
//		for (int i = 0; i < userAns.size(); i++) {
//
//			if (userAns.get(i).equals(p2Ans.get(i))) {
//				p2mark = p2mark + 1;
//			}
//		}
		for (int i = 0; i < p3userAns.size(); i++) {

			if (p3userAns.get(i).equals(p3Ans.get(i))) {
				p3mark = p3mark + 1;
			}
		}

	}

	public void increase() {
		lblNo.setText(String.valueOf(i + 1));
		if (i < dtm.getRowCount()) {
			fillfield();
			i++;

		} else if (i == dtm.getRowCount()) {

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
					lblNo.setText(String.valueOf(dtm.getRowCount()));

					if (i > 0) {
						i--;
						getUserAns();

						fillfield();

					}

				}
			});

			JButton btncontinue = new JButton("Next Part");
			btncontinue.setBounds(408, 96, 145, 35);
			panel.add(btncontinue);

			btncontinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 setVisible(false);
					 i=0;
					

				}
			});
			JButton btnSubmit = new JButton("Submit");
			btnSubmit.setBounds(637, 228, 89, 23);
			btnSubmit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					checkAnswer();
					
					String sql = "Insert Into testresult(stuId,part1,part2,part3) Values('" + form.data[0].toString()
							+ "','" + p1mark + "','" + p2mark + "','" + p3mark + "')";
					try {
						if (stmt.executeUpdate(sql) == 1) {
							JOptionPane.showMessageDialog(null, "Insert success");
						} else {
							JOptionPane.showMessageDialog(null, "fail");

						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

			panel.add(btnSubmit);
			panel.show();
		}

	}

	public void isAnswered() {
		if (rdo1.isSelected()) {
			givenAns = rdo1.getText();
		} else if (rdo2.isSelected()) {
			givenAns = rdo2.getText();
		} else if (rdo3.isSelected()) {
			givenAns = rdo3.getText();
		} else if (rdo4.isSelected()) {
			givenAns = rdo4.getText();
		} else {
			givenAns = null;
		}

	}

	public void fillfield() {

		lblId.setText(dtm.getValueAt(i, 0).toString());
		partId = dtm.getValueAt(i, 1).toString();
		textArea.setText(dtm.getValueAt(i, 2).toString());
		rdo1.setText(dtm.getValueAt(i, 3).toString());
		rdo2.setText(dtm.getValueAt(i, 4).toString());
		rdo3.setText(dtm.getValueAt(i, 5).toString());
		rdo4.setText(dtm.getValueAt(i, 6).toString());
		correctanswer = dtm.getValueAt(i, 7).toString();
	}

	public void updateAnswer() {

		String sql = "update testDetail set userans= '" + givenAns + "' where studentId= '" + form.data[0].toString()
				+ "'&& qId= '" + lblId.getText() + "'";
		try {
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			if (count == 1) {
				JOptionPane.showMessageDialog(null, "update success");
			} else {
				JOptionPane.showMessageDialog(null, "update fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void getUserAns() {
		String q[] = new String[1];
		String sql1 = "Select userans from testDetail where qId='" + lblId.getText() + "'&& studentId= '"
				+ form.data[0].toString() + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				
				if (rdo1.getText().equals(rs.getString(1))) {
					rdo1.setSelected(true);
				} else if (rdo2.getText().equals(rs.getString(1))) {
					rdo2.setSelected(true);
				} else if (rdo3.getText().equals(rs.getString(1))) {
					rdo3.setSelected(true);
				} else if (rdo4.getText().equals(rs.getString(1))) {
					rdo4.setSelected(true);
				} else {
					JOptionPane.showMessageDialog(null, "something wrong");
				}
			} else {
				JOptionPane.showMessageDialog(null, "cannot retrieve");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
