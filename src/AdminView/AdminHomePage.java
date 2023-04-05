package AdminView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import question.QuestionPanel;
import student.StuListPanel;
import test.TestResult;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomePage extends JFrame {

	private JPanel contentPane;
	StuListPanel listpanel=new StuListPanel();
	TestResult resultpanel=new TestResult();
	JScrollPane resscrollPane = new JScrollPane();
	JScrollPane stuscrollPane = new JScrollPane();
	QuestionPanel qPanel=new QuestionPanel();
	DashboardPanel dashboard=new DashboardPanel();
	JPanel panel_2 = new JPanel();
	JLabel lblAdmin = new JLabel("New label");
	AdminLogin form=new AdminLogin();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomePage frame = new AdminHomePage();
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
	public AdminHomePage() {
		if(!form.data.equals(null)) {
			lblAdmin.setText(form.data[0].toString());;
			
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
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(0, 57, 257, 646);
		contentPane.add(panel);
		panel.setLayout(null);
		resscrollPane.setVisible(false);
		stuscrollPane.setVisible(false);
		qPanel.setVisible(false);
		listpanel.setVisible(false);
		resultpanel.setVisible(false);
		panel_2.setVisible(false);
		
		JPanel dpanel = new JPanel();
		dpanel.setBounds(261, 61, 803, 604);
		contentPane.add(dpanel);
		dpanel.setLayout(null);
		dashboard.setBounds(0, 0, 803, 604);
		dpanel.add(dashboard);
		
		dashboard.setVisible(true);	
		dashboard.setForeground(new Color(255, 255, 255));
		dashboard.setBorder(new EmptyBorder(0, 0, 0, 0));
		dashboard.setBackground(new Color(250, 250, 210));
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listpanel.setVisible(true);
				dashboard.setVisible(false);
				resscrollPane.setVisible(false);
				stuscrollPane.setVisible(true);
				resultpanel.setVisible(false);
				qPanel.setVisible(false);
				panel_2.setVisible(false);
				dpanel.setVisible(false);
				stuscrollPane.setBounds(300, 130, 750, 500);
				contentPane.add(stuscrollPane);
				listpanel.setForeground(new Color(255, 255, 255));
				listpanel.setBackground(new Color(250, 250, 210));
				stuscrollPane.setViewportView(listpanel);
				stuscrollPane.setViewportBorder(new LineBorder(new Color(250, 250, 210)));
				
			}
		});
		
		
		
		btnStudent.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnStudent.setForeground(new Color(255, 250, 250));
		btnStudent.setBackground(new Color(184, 134, 11));
		btnStudent.setBounds(0, 60, 255, 50);
		panel.add(btnStudent);
		
		JButton btnQuestions = new JButton("Questions");
		btnQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dashboard.setVisible(false);
				listpanel.setVisible(false);
				resscrollPane.setVisible(false);
				stuscrollPane.setVisible(false);
				resultpanel.setVisible(false);
				dpanel.setVisible(false);
				panel_2.setBounds(334, 61, 401, 300);
				contentPane.add(panel_2);
				panel_2.setLayout(null);
				qPanel.setBounds(0, 0, 401, 300);
				panel_2.add(qPanel);
				qPanel.setVisible(true);
				panel_2.setVisible(true);
				qPanel.setForeground(new Color(255, 255, 255));
				qPanel.setBackground(new Color(250, 250, 210));
				
				
			}
		});
		
		btnQuestions.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnQuestions.setForeground(new Color(255, 250, 250));
		btnQuestions.setBackground(new Color(184, 134, 11));
		btnQuestions.setBounds(0, 120, 255, 50);
		panel.add(btnQuestions);
		
		
		JButton btnResult = new JButton("Test Results");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultpanel.setVisible(true);
				dashboard.setVisible(false);
				listpanel.setVisible(false);
				stuscrollPane.setVisible(false);
				resscrollPane.setVisible(true);
				qPanel.setVisible(false);
				panel_2.setVisible(false);
				dpanel.setVisible(false);
				resscrollPane.setBounds(300, 130, 750, 500);
				
				contentPane.add(resscrollPane);
				resultpanel.setForeground(new Color(255, 255, 255));
				dashboard.setBackground(new Color(250, 250, 210));
				resscrollPane.setViewportView(resultpanel);
				resultpanel.setLayout(null);
				
			}
		});
		btnResult.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnResult.setForeground(new Color(255, 250, 250));
		btnResult.setBackground(new Color(184, 134, 11));
		btnResult.setBounds(0, 180, 255, 50);
		panel.add(btnResult);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboard.setVisible(true);
				dpanel.setVisible(true);
				panel_2.setVisible(false);
				stuscrollPane.setVisible(false);
				resscrollPane.setVisible(false);
				
			}
		});
		btnDashboard.setForeground(new Color(255, 250, 250));
		btnDashboard.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDashboard.setBackground(new Color(184, 134, 11));
		btnDashboard.setBounds(0, 1, 255, 50);
		panel.add(btnDashboard);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.windowText);
		panel_1.setBounds(0, 0, 1364, 55);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IQ Test Exam System");
		lblNewLabel.setBounds(523, 11, 242, 33);
		lblNewLabel.setBackground(new Color(184, 134, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(218, 165, 32));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		panel_1.add(lblNewLabel);
		lblAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmin.setBounds(1098, 24, 91, 14);
		
		
		lblAdmin.setForeground(new Color(255, 250, 250));
		panel_1.add(lblAdmin);
		

		
	
	}
}