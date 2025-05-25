package bankAccountProgram;
import java.util.ArrayList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Font;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField address;
	private ArrayList <customer> listOfCustomer = new ArrayList <customer>();
	private DefaultListModel listCustomerModel = new DefaultListModel();

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
	 */
	public Home() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList(listCustomerModel);
		list.setBounds(59, 73, 229, 276);
		contentPane.add(list);
		
		name = new JTextField();
		name.setBounds(496, 92, 124, 19);
		contentPane.add(name);
		name.setColumns(10);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(496, 139, 124, 19);
		contentPane.add(phone);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(496, 182, 124, 19);
		contentPane.add(address);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(394, 95, 45, 13);
		contentPane.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(394, 142, 93, 13);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(394, 185, 92, 13);
		contentPane.add(lblAddress);
		
		JLabel lblNewLabel_1 = new JLabel("List of customer:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(59, 50, 173, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().trim().equals("") || phone.getText().trim().equals("") || address.getText().trim().equals("")) {
					  JOptionPane.showMessageDialog( contentPane , "Please fill in all the fields.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					  customer Currentcustomer = new customer(listOfCustomer.size()+1,name.getText(),phone.getText(),address.getText());
						listOfCustomer.add(Currentcustomer);
						listCustomerModel.addElement(listOfCustomer.getLast().getOwnerName());
						clearFields();
				}
				
				
				
			}
		});
		btnAddCustomer.setBounds(315, 284, 124, 21);
		contentPane.add(btnAddCustomer);
		
		JButton btnRemoveCustomer = new JButton("Remove Customer");
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				listOfCustomer.remove(list.getSelectedIndex());
				listCustomerModel.removeElement(list.getSelectedValue());
				} 
				catch(IndexOutOfBoundsException e1) {
					  JOptionPane.showMessageDialog( contentPane , "Please choose a customer in the list.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		});
		btnRemoveCustomer.setBounds(630, 284, 151, 21);
		contentPane.add(btnRemoveCustomer);
		
		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				
				
				try {
					CustomerGUI currentCust = new CustomerGUI(listOfCustomer.get(list.getSelectedIndex()),Home.this);
					currentCust.setVisible(true);
					Home.this.setVisible(false);
					}
					catch(IndexOutOfBoundsException e1) {
						  JOptionPane.showMessageDialog( contentPane , "Please choose a customer in the list.", 
		                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
					
			}
		});
		btnEditCustomer.setBounds(471, 284, 124, 21);
		contentPane.add(btnEditCustomer);
		
		
		
		
		
	}
	public void refreshCustomerList() {
		 listCustomerModel.removeAllElements();
		    for (customer c : listOfCustomer) {
		    	listCustomerModel.addElement(c.getOwnerName()); // or update JTable model
		    }
	}
	private void clearFields() {
		name.setText("");
		phone.setText("");
		address.setText("");
	}
	

}
