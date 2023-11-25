package test;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	Connection con;

	public LoginForm() {
		createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 11, 88, 36);
		contentPane.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(20, 58, 201, 29);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 98, 201, 29);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String luname = txtUsername.getText();
					String lpass = passwordField.getText();
					
					if (txtUsername.getText().isEmpty() || passwordField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Fill all the textfields", "WARNING", JOptionPane.OK_OPTION);
					}
					
					Statement stmt = con.createStatement();
					String sql = "select * from userdata where username='"+ luname +"' and password='"+ lpass +"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						JOptionPane.showConfirmDialog(null, "LOGIN SUCCESSFULLY", "", JOptionPane.OK_OPTION);
					} else  {
						JOptionPane.showMessageDialog(null, "Username or Password is incorrect", "WARNING", JOptionPane.OK_OPTION);
					}
					
					
				} catch (SQLException ex) {
					
				}
			}
		});
		btnNewButton.setBounds(20, 138, 108, 29);
		contentPane.add(btnNewButton);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	private JTextField txtUsername;
	private JPasswordField passwordField;
			
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
