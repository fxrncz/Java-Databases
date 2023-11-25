package test;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertData extends JFrame {

	Connection con;
	private JPanel contentPane;

	public InsertData() {
		// this is the database
		createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtname = new JTextField();
		txtname.setBounds(33, 28, 209, 34);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JButton btn1 = new JButton("Insert Users 2");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					String name = txtname.getText();
					int age = Integer.parseInt(txt2.getText());
					PreparedStatement stmt = con.prepareStatement("insert into users2 values(?,?)");
					stmt.setString(1, name);
					stmt.setInt(2, age);
					stmt.execute();
					System.out.println("Insertion Completed.");
					
					stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		btn1.setBounds(263, 28, 101, 34);
		contentPane.add(btn1);
		
		txt2 = new JTextField();
		txt2.setColumns(10);
		txt2.setBounds(33, 73, 209, 34);
		contentPane.add(txt2);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertData frame = new InsertData();
					frame.setLocationRelativeTo(null);
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
	private JTextField txtname;
	private JTextField txt2;
	
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
