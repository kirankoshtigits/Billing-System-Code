

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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateInvoice extends JFrame {

	private JPanel contentPane;
	private JTextField prise_field;
	private JTextField textField_1;
	private JComboBox product_combo;
	private JLabel product_left;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateInvoice frame = new GenerateInvoice();
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
	
	private void ProductDropdown()
	{
		
		String JDBC_DRIVER="com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/billing_system";
		String DB_USER="root";
		String DB_Pass="";
		
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection conn =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
			String product="select * from  producttable"; 
			
			Statement statement=conn.createStatement();
			ResultSet set=statement.executeQuery(product);
			
			while(set.next())
			{
				product_combo.addItem(set.getString("Product_Name")); 
			}
			
			conn.close();
			
		}
		catch(Exception e) 
		{
			System.out.println("Error...:"+e);
		}
		
	}
	
	
	public GenerateInvoice() {
		
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
		setBounds(350, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Product");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 33, 105, 14);
		contentPane.add(lblNewLabel);
		
		product_combo = new JComboBox();
		product_combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String JDBC_DRIVER="com.mysql.jdbc.Driver";
				String DB_URL="jdbc:mysql://localhost/billing_system";
				String DB_USER="root";
				String DB_Pass="";
				
				try
				{
					Class.forName(JDBC_DRIVER);
					Connection conn =DriverManager.getConnection(DB_URL,DB_USER,DB_Pass);
					String fetch_row="select * from  producttable where product_name=?"; 
					
					PreparedStatement statement=conn.prepareStatement(fetch_row);
					statement.setString(1,(String)product_combo.getSelectedItem());
					ResultSet set=statement.executeQuery();
					 
					while(set.next())
					{
						product_left.setText(set.getString("No_of_Units"));
						prise_field.setText(set.getString("Product_Prise"));
					}	
				}
				catch(Exception e1) 
				{
					System.out.println("Error...:"+e);
				}
			}
		});
		product_combo.setBounds(23, 58, 185, 27);
		contentPane.add(product_combo);
		
		JLabel lblNewLabel_1 = new JLabel("Units Left");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(228, 33, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(328, 33, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		prise_field = new JTextField();
		prise_field.setBounds(329, 58, 154, 27);
		contentPane.add(prise_field);
		prise_field.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(510, 33, 81, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(510, 58, 146, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(685, 55, 89, 33);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 110, 751, 324);
		contentPane.add(scrollPane);
		
		product_left = new JLabel("");
		product_left.setBounds(218, 58, 105, 27);
		contentPane.add(product_left);
		ProductDropdown();
	}
}
