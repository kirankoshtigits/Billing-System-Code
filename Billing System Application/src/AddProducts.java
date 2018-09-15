import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;

public class AddProducts extends JFrame {

	private JPanel contentPane;
	private JTextField product_name;
	private JTextField product_prise;
	private JTextField product_unit;
	private JTextField product_tax;
	private JTextField product_desc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProducts frame = new AddProducts();
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
	public void createTableNew()
	{
		try 
		{
			
				String JDBC_DRIVER="com.mysql.jdbc.Driver";
				String DB_URL="jdbc:mysql://localhost/billing_system";
				String DB_USER="root";
				String DB_Pass="";
				
				String create_table="create table ProductTable(Product_Name varchar(20),Product_Prise varchar(20),No_of_Units varchar(20),Tax varchar(20),Description varchar(20))";
				try
				{
					Class.forName(JDBC_DRIVER);
					Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
					
					PreparedStatement  statament=conn.prepareStatement(create_table);
					
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
	
	public AddProducts() {
		
		//create Database Connection here 
		
		String JDBC_DRIVER="com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/billing_system";
		String DB_USER="root";
		String DB_Pass="";
		
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
			JOptionPane.showMessageDialog(null,"Connection is Successfull"); 
			conn.close();
			
		}
		catch(Exception e) 
		{
			System.out.println("Error...Connection is faild:"+e);
		}

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Product First");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(256, 31, 193, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(123, 102, 119, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblProductPrise = new JLabel("Product Prise");
		lblProductPrise.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductPrise.setBounds(123, 148, 119, 21);
		contentPane.add(lblProductPrise);
		
		JLabel lblNumberOfUnits = new JLabel("Number of Units");
		lblNumberOfUnits.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfUnits.setBounds(123, 196, 119, 21);
		contentPane.add(lblNumberOfUnits);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTax.setBounds(123, 250, 119, 21);
		contentPane.add(lblTax);
		
		JLabel lblProductDescription = new JLabel("Product Description");
		lblProductDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductDescription.setBounds(123, 301, 140, 21);
		contentPane.add(lblProductDescription);
		
		product_name = new JTextField();
		product_name.setBounds(272, 104, 215, 28);
		contentPane.add(product_name);
		product_name.setColumns(10);
		
		product_prise = new JTextField();
		product_prise.setColumns(10);
		product_prise.setBounds(272, 150, 215, 28);
		contentPane.add(product_prise);
		
		product_unit = new JTextField();
		product_unit.setColumns(10);
		product_unit.setBounds(272, 198, 215, 28);
		contentPane.add(product_unit);
		
		product_tax = new JTextField();
		product_tax.setColumns(10);
		product_tax.setBounds(272, 252, 215, 28);
		contentPane.add(product_tax);
		
		product_desc = new JTextField();
		product_desc.setColumns(10);
		product_desc.setBounds(272, 303, 215, 28);
		contentPane.add(product_desc);
		
		JButton product_submit = new JButton("Submit");
		product_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				String JDBC_DRIVER="com.mysql.jdbc.Driver";
				String DB_URL="jdbc:mysql://localhost/billing_system";
				String DB_USER="root";
				String DB_Pass="";
								
				try
				{
					Class.forName(JDBC_DRIVER);
					Connection conn =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
					
					String sql="INSERT INTO `producttable`values(?,?,?,?,?)";
					PreparedStatement statament1= conn.prepareStatement(sql);
						
					statament1.setString(1,product_name.getText());
					statament1.setString(2,product_prise.getText());
					statament1.setString(3,product_unit.getText());
					statament1.setString(4,product_tax.getText());
					statament1.setString(5,product_desc.getText());
					
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
		product_submit.setBounds(174, 386, 110, 37);
		contentPane.add(product_submit);
		
		JButton product_cancel = new JButton("Cancel");
		product_cancel.setBounds(368, 386, 119, 37);
		contentPane.add(product_cancel);
		
		createTableNew();
	}
}
