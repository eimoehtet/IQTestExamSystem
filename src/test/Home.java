package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.LoginForm;
import Pack.DBConnection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel contentPane;
	JPanel panel = new JPanel();
	JLabel lblId = new JLabel("New label");
	JLabel lblNrc = new JLabel("New label");
	public JButton btnP1 = new JButton("PART I");
	JButton btnP2 = new JButton("PART II");
	JButton btnP3 = new JButton("PART III");
	public static boolean plClick=false;
	public static boolean p2Click=false;
	public static boolean p3Click=false;
	//public boolean answered=false;
	ResultSet rs;
	Connection conn=null;
	DBConnection connect = new DBConnection();
	private final JLabel lblNewLabel = new JLabel("Easy Quick Calculation");
	private final JLabel lblNewLabel_1 = new JLabel("Thinking problems");
	private final JLabel lblNewLabel_3 = new JLabel("Check your Programming Level");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	
	public Home() {
		setBackground(new Color(250, 250, 210));
		
		LoginForm form=new LoginForm();
		try {
			conn=DBConnection.GetMySQLConnection();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		if(!form.data.equals(null)) {
			lblId.setText(form.data[0].toString());;
			lblNrc.setText(form.data[1].toString());
			checkExistStudent();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setMaximumSize(getMaximumSize());
		
		this.setExtendedState(MAXIMIZED_BOTH);
		
		panel.setBackground(new Color(250, 250, 210));
	
		panel.setBounds(66, 90, 964, 319);
		contentPane.add(panel);
		btnP1.setBounds(27, 31, 132, 50);
		
		btnP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnP1.setEnabled(false);
				plClick=true;
				p2Click=false;
				p3Click=false;
				Test test=new Test();
				test.show();
				
			}
		});
		panel.setLayout(null);
		panel.add(btnP1);
		btnP2.setBounds(27, 102, 132, 50);
		
		
		btnP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnP2.setEnabled(false);
				p2Click=true;
				plClick=false;
				p3Click=false;
				Test test=new Test();
				test.show();
				
			}
		});
		panel.add(btnP2);
		btnP3.setBounds(27, 171, 132, 50);
		
		
		btnP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnP3.setEnabled(false);
				plClick=false;
				p3Click=true;
				p2Click=false;
				Test test=new Test();
				test.show();

			}
		});
		panel.add(btnP3);
		lblNewLabel.setBounds(184, 42, 170, 21);
		
		panel.add(lblNewLabel);
		lblNewLabel_1.setBounds(184, 120, 150, 14);
		
		panel.add(lblNewLabel_1);
		lblNewLabel_3.setBounds(184, 189, 206, 14);
		
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 1360, 42);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("IQ Test Exam System");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_2.setForeground(new Color(184, 134, 11));
		lblNewLabel_2.setBounds(525, 5, 228, 26);
		panel_1.add(lblNewLabel_2);
		lblId.setForeground(new Color(255, 250, 250));
		lblId.setBounds(807, 5, 83, 35);
		panel_1.add(lblId);
		lblNrc.setForeground(new Color(255, 250, 250));
		lblNrc.setBounds(900, 10, 131, 24);
		panel_1.add(lblNrc);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm exiting",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
					dispose();
					form.show();
				}
			}
		});
		btnLogout.setBounds(1073, 11, 83, 23);
		panel_1.add(btnLogout);
	}
	public void checkExistStudent() {
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from testresult where stuId= '"+lblId.getText()+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()) {
				
				btnP1.setEnabled(false);
				btnP2.setEnabled(false);
				btnP3.setEnabled(false);
				JLabel lbl = new JLabel("You have already answered!");
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
				lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lbl.setBounds(400, 50, 400, 30);
				panel.add(lbl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
