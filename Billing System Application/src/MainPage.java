import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPasswordField;

public class MainPage {

	private JFrame frame;
	private JTextField login_user;
	private JPasswordField login_pass;
	private JTextField name_field;
	private JTextField username_field;
	private JTextField org_field;
	private JTextField dot_field;
	private JTextField mobile_field;
	private JPanel panel;
	private JPasswordField password_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	Connection conn=null;
	PreparedStatement statament=null;
	
	
	//how to create table with java application is does not exist in DATABASE.
	public void createTableNew()
	{
		try 
		{
			
				String JDBC_DRIVER="com.mysql.jdbc.Driver";
				String DB_URL="jdbc:mysql://localhost/billing_system";
				String DB_USER="root";
				String DB_Pass="";
				
				String create_table="create table UserTable(Name varchar(20),UserName varchar(20),Password varchar(20),Organisation varchar(20),DOB varchar(20),Mobile_Number(20))";
				try
				{
					Class.forName(JDBC_DRIVER);
					conn = DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
					
					 statament=conn.prepareStatement(create_table);
					
					 statament.executeUpdate();
					 
					 JOptionPane.showMessageDialog(null,"Table is Create Successfull");
			
					 statament.close();
					conn.close();
				}
				catch(Exception e) 
				{
					JOptionPane.showMessageDialog(null,"Table is Already exists");
				}	
				
		}catch(Exception e) {}
		
	}
	private void initialize()
	{
		
		//create Database Connection here 
		
					String JDBC_DRIVER="com.mysql.jdbc.Driver";
					String DB_URL="jdbc:mysql://localhost/billing_system";
					String DB_USER="root";
					String DB_Pass="";
					
					try
					{
						Class.forName(JDBC_DRIVER);
						conn =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
						JOptionPane.showMessageDialog(null,"Connection is Successfull"); 
						conn.close();
						
					}
					catch(Exception e) 
					{
						System.out.println("Error...Connection is faild:"+e);
					}
		
		
					
					
		frame = new JFrame();
		frame.setBounds(100, 100,800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Simple Billing System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(222, 48, 482, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.setVisible(true);
			}
		});
		btnNewButton.setBounds(50, 200, 222, 45);
		frame.getContentPane().add(btnNewButton);
		
		login_user = new JTextField();
		login_user.setBounds(558, 176, 181, 28);
		frame.getContentPane().add(login_user);
		login_user.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(474, 183, 74, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(474, 236, 60, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblAlreadyUser = new JLabel("Already User");
		lblAlreadyUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlreadyUser.setBounds(558, 119, 121, 33);
		frame.getContentPane().add(lblAlreadyUser);
		
		JButton lobinbutton = new JButton("Login");
		lobinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//	new Task().setVisible(true);
				
			//Validate login form
				String JDBC_DRIVER="com.mysql.jdbc.Driver";
				String DB_URL="jdbc:mysql://localhost/billing_system";
				String DB_USER="root";
				String DB_Pass="";
							
				try
				{
					Class.forName(JDBC_DRIVER);
					Connection connection =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
					
					String validate="Select * from usertable where username=? and password=?";
					PreparedStatement stm=connection.prepareStatement(validate);
					
					stm.setString(1,login_user.getText());
					stm.setString(2,login_pass.getText());
					
					ResultSet set=stm.executeQuery();//To retrive the data in the table using resultset
					
					if(set.next())
					{
						JOptionPane.showMessageDialog(null,"Login Successfull");
						new Task().setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid User");
					}
				}
				catch(Exception e1) 
				{
					System.out.println("Invalid..UserName..And ..Password :"+e1);
				}
	
				
			}
		});
		lobinbutton.setBounds(580, 290, 126, 33);
		frame.getContentPane().add(lobinbutton);
		
		JLabel lblNewLabel_3 = new JLabel("New User");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(115, 161, 99, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setVisible(false);
		//Make Variable Global
		panel.setBounds(10, 256, 449, 291);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(62, 14, 46, 14);
		panel.add(lblNewLabel_4);
		
		name_field = new JTextField();
		name_field.setBounds(176, 8, 202, 27);
		panel.add(name_field);
		name_field.setColumns(10);
		
		JLabel lblCreateUsername = new JLabel("Create Username");
		lblCreateUsername.setBounds(62, 52, 104, 14);
		panel.add(lblCreateUsername);
		
		username_field = new JTextField();
		username_field.setBounds(176, 46, 202, 26);
		panel.add(username_field);
		username_field.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Choose Password");
		lblNewLabel_5.setBounds(62, 89, 104, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblOrganisation = new JLabel("Organisation");
		lblOrganisation.setBounds(62, 127, 86, 14);
		panel.add(lblOrganisation);
		
		org_field = new JTextField();
		org_field.setBounds(176, 121, 202, 27);
		panel.add(org_field);
		org_field.setColumns(10);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(62, 165, 46, 14);
		panel.add(lblDob);
		
		dot_field = new JTextField();
		dot_field.setBounds(176, 159, 202, 27);
		panel.add(dot_field);
		dot_field.setColumns(10);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setBounds(62, 203, 104, 14);
		panel.add(lblMobileNumber);
		
		mobile_field = new JTextField();
		mobile_field.setBounds(176, 197, 202, 27);
		panel.add(mobile_field);
		mobile_field.setColumns(10);
		
		JButton create_account = new JButton("Create Account");
		create_account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String JDBC_DRIVER="com.mysql.jdbc.Driver";
				String DB_URL="jdbc:mysql://localhost/billing_system";
				String DB_USER="root";
				String DB_Pass="";
								
				try
				{
					Class.forName(JDBC_DRIVER);
					Connection conn =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
					
					String sql="INSERT INTO `usertable`values(?,?,?,?,?,?)";
					PreparedStatement statament1= conn.prepareStatement(sql);
						
					statament1.setString(1,name_field.getText());
					statament1.setString(2,username_field.getText());
					statament1.setString(3,password_field.getText());
					statament1.setString(4,org_field.getText());
					statament1.setString(5,dot_field.getText());
					statament1.setString(6,mobile_field.getText());
					int inserted =statament1.executeUpdate();	
					if(inserted>0)
					{
						JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
					}
					statament1.close();
					conn.close();
					
				}
				catch(Exception e1) 
				{
					System.out.println("Error...Connection is faild:"+e1);
				}
				
		    }
		});
		create_account.setBounds(83, 246, 202, 34);
		panel.add(create_account);
		
		password_field = new JPasswordField();
		password_field.setBounds(176, 83, 202, 27);
		panel.add(password_field);
		
		login_pass = new JPasswordField();
		login_pass.setBounds(558, 225, 181, 28);
		frame.getContentPane().add(login_pass);
		
		 createTableNew();
		
	}
}
