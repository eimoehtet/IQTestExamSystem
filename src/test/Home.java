package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.LoginForm;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	JLabel lblId = new JLabel("New label");
	JLabel lblNrc = new JLabel("New label");

	public JButton btnP1 = new JButton("Part1");
	public static boolean plClick=false;
	public static boolean p2Click=false;
	public static boolean p3Click=false;
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
		
		LoginForm form=new LoginForm();
		
		if(!form.data.equals(null)) {
			lblId.setText(form.data[0].toString());;
			lblNrc.setText(form.data[1].toString());
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 11, 649, 319);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plClick=true;
				p2Click=false;
				p3Click=false;
				Test test=new Test();
				test.show();
				
			}
		});
		btnP1.setBounds(74, 203, 89, 23);
		panel.add(btnP1);
		
		JButton btnP2 = new JButton("Part2");
		btnP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2Click=true;
				plClick=false;
				p3Click=false;
				Test test=new Test();
				test.show();
				
			}
		});
		btnP2.setBounds(250, 203, 89, 23);
		panel.add(btnP2);
		
		JButton btnP3 = new JButton("Part3");
		btnP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plClick=false;
				p3Click=true;
				p2Click=false;
				Test test=new Test();
				test.show();

			}
		});
		btnP3.setBounds(436, 203, 89, 23);
		panel.add(btnP3);
		
		JLabel lblNewLabel = new JLabel("Student ID:");
		lblNewLabel.setBounds(74, 32, 77, 35);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NRC No");
		lblNewLabel_1.setBounds(387, 32, 77, 35);
		panel.add(lblNewLabel_1);
		
		
		lblId.setBounds(185, 32, 83, 35);
		panel.add(lblId);
		
		
		lblNrc.setBounds(484, 37, 131, 24);
		panel.add(lblNrc);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome!");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setBounds(207, 126, 146, 14);
		panel.add(lblNewLabel_4);
	}

}
