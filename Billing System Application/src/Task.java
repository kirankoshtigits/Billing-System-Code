

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Task extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task frame = new Task();
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
	public Task() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,700,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addproduct = new JButton("Add Product");
		addproduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AddProducts().setVisible(true);
			}
		});
		addproduct.setBounds(52, 84, 132, 53);
		contentPane.add(addproduct);
		
		JButton btnNewButton_1 = new JButton("Generate Invoice");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new GenerateInvoice().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(297, 84, 138, 53);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Report");
		btnNewButton_2.setBounds(52, 219, 132, 53);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("History");
		btnNewButton_3.setBounds(297, 219, 138, 53);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Payment");
		btnNewButton_4.setBounds(487, 154, 125, 53);
		contentPane.add(btnNewButton_4);
	}
}
