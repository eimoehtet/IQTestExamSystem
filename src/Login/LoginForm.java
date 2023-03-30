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

public class LoginForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
		try {
			conn=DBConnection.GetMySQLConnection();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		setBounds(100, 100, 497, 394);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 454, 344);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 11, 428, 241);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Login Form");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(122, 11, 167, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 76, 90, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NRC Number:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(38, 160, 90, 30);
		panel.add(lblNewLabel_2);
		
		
		txtNrc.setBounds(203, 161, 217, 30);
		panel.add(txtNrc);
		txtNrc.setColumns(10);
		
		
		txtId.setBounds(203, 76, 215, 30);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
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
		btnLogin.setBounds(167, 263, 125, 37);
		contentPanel.add(btnLogin);
		
		
		
	}
	
}
