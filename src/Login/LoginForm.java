package Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Pack.DBConnection;
import test.Home;
import test.Test;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class LoginForm extends JDialog {
	private JTextField txtNrc = new JTextField();
	private JTextField txtId = new JTextField();
	public static String[]data=new String[2];
	PreparedStatement pst;
	ResultSet rs;
	Connection conn=null;
	DBConnection connect = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginForm dialog = new LoginForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginForm() {
		getContentPane().setBackground(new Color(25, 25, 112));
		try {
			conn=DBConnection.GetMySQLConnection();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		setBounds(400, 100, 512, 386);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 496, 347);
		getContentPane().add(panel);
		panel.setForeground(new Color(153, 180, 209));
		panel.setBackground(new Color(250, 250, 210));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Login Form");
		lblNewLabel.setForeground(new Color(184, 134, 11));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(154, 11, 184, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID:");
		lblNewLabel_1.setForeground(new Color(184, 134, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(38, 76, 90, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NRC Number:");
		lblNewLabel_2.setForeground(new Color(184, 134, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(38, 160, 90, 30);
		panel.add(lblNewLabel_2);
		
		
		txtNrc.setBounds(203, 161, 217, 30);
		panel.add(txtNrc);
		txtNrc.setColumns(10);
		
		
		txtId.setBounds(203, 76, 215, 30);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogin.setForeground(new Color(248, 248, 255));
		btnLogin.setBackground(new Color(184, 134, 11));
		btnLogin.setBounds(304, 246, 116, 37);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stuId=txtId.getText();
				String nrc=txtNrc.getText();
				try {
					Statement stmt= conn.createStatement();
					String sql="Select studentId,nrcNo From Student Where studentId='" + stuId + "' and nrcNo='" + nrc+"'";
					ResultSet rs=stmt.executeQuery(sql);
					
					if(rs.next()) {
						data[0]=rs.getString(1);
						data[1]=rs.getString(2);
						JOptionPane.showMessageDialog(null,"You have successfully logged in");
						dispose();
						Home hm=new Home();
						hm.show();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Student ID or Nrc Number is wrong");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
		
	}
	
}
