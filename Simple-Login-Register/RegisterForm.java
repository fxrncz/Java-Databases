package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class RegisterForm extends JFrame {

	Connection con;
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txt2;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	public RegisterForm() {
		createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(32, 30, 203, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(32, 72, 203, 28);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String uname = txtUsername.getText();
					String pass = passwordField.getText();
					
					PreparedStatement stmt = con.prepareStatement("insert into userdata (username, password) values(?,?)");
					stmt.setString(1, uname);
					stmt.setString(2, pass);
					stmt.execute();
					System.out.println("Insertion Completed.");
					
					LoginForm obj1 = new LoginForm();
					obj1.setLocationRelativeTo(null);
					obj1.setResizable(false);
					obj1.setVisible(true);
					dispose();
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				if(txtUsername.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete the Textfields", "WARNING", JOptionPane.YES_OPTION);
				}
			}
		});
		btnNewButton.setBounds(32, 128, 104, 34);
		contentPane.add(btnNewButton);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// THIS IS THE CONNECTION INTO YOUR DATABASE : do NOT delete this statement!
		static String url = "jdbc:mysql://localhost:3306/mydb";
		static String user = "root";
		static String pass = "goldwood123";
		
		void createConnection() {	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, pass);
				
				System.out.println("Connected Successfully");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
