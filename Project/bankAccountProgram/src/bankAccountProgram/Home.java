package bankAccountProgram;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter ;

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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField phone;
	private JTextArea address;
	private JTextArea name ;
	private ArrayList <customer> listOfCustomer = new ArrayList <customer>(); // store list of customer object
	private DefaultListModel listCustomerModel = new DefaultListModel(); // listmodel to update Jlist

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
		//generate sample data
		addCustomer(); // create customer object and add data for each customer object
		refreshList(); // refresh the listModel to update JList
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList(listCustomerModel); // add items in listModel to the JList
		list.setBounds(59, 73, 229, 276);
		contentPane.add(list);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(496, 139, 124, 19);
		contentPane.add(phone);
		
	    address = new JTextArea();
	    address.setLineWrap(true);
		address.setBounds(496, 185, 124, 54);
		contentPane.add(address);
		
		name = new JTextArea();
		name.setLineWrap(true);
		name.setBounds(496, 87, 124, 29);
		contentPane.add(name);
		
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
				// condition to check if the user enter data in all of the text fields
				if(name.getText().trim().equals("") || phone.getText().trim().equals("") || address.getText().trim().equals("")) {
					  JOptionPane.showMessageDialog( contentPane , "Please fill in all the fields.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					  // display error message if the user does not fill in all the text fields
				}
				else {
					  
					  customer Currentcustomer = new customer(listOfCustomer.size()+1,name.getText(),phone.getText(),address.getText()); //create customer object by retrieving data from the text field
						listOfCustomer.add(Currentcustomer); // add the customer object to the list of customer
						listCustomerModel.addElement(listOfCustomer.getLast().getOwnerName()); // add element to the listModel by getting the last object in the list of Customer. After that we get customer name from that cuatomer object
						clearFields(); // clear all of the text fields
						
				}
				
				
				
			}
		});
		btnAddCustomer.setBounds(315, 284, 124, 21);
		contentPane.add(btnAddCustomer);
		
		JButton btnRemoveCustomer = new JButton("Remove Customer");
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// try to get the selected item in the Jlist
				try {
				listOfCustomer.remove(list.getSelectedIndex()); // get the index of the selected index in the JList and remove the object that has that index in the listOfCustomer.
				listCustomerModel.removeElement(list.getSelectedValue()); // remove the selected index from the Jlist
				} 
				catch(IndexOutOfBoundsException e1) {
					  JOptionPane.showMessageDialog( contentPane , "Please choose a customer in the list.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					  // error message if user did not click to any customer in the list
				}
			}
		
		});
		btnRemoveCustomer.setBounds(630, 284, 151, 21);
		contentPane.add(btnRemoveCustomer);
		
		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				
				// try to get the selected item in the Jlist
				try {
					CustomerGUI currentCust = new CustomerGUI(listOfCustomer.get(list.getSelectedIndex()),Home.this); // create CustomerGUI object by passing the selected customer object and the current Home object
					currentCust.setVisible(true); // display CustomerGUI window
					Home.this.setVisible(false); // close the Home window
					}
					catch(IndexOutOfBoundsException e1) {
						  JOptionPane.showMessageDialog( contentPane , "Please choose a customer in the list.", 
		                            "Input Error", JOptionPane.ERROR_MESSAGE);
						  // error message when the customer does not choose a customer in the list
					}
					
			}
		});
		btnEditCustomer.setBounds(471, 284, 124, 21);
		contentPane.add(btnEditCustomer);
		
		JButton btnNewButton = new JButton("Get Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveReport(); // Generate report when user click the button
				 JOptionPane.showMessageDialog( contentPane , "Report Saved.", 
                         "Notification", JOptionPane.CLOSED_OPTION); // notification to user
				
			}
		});
		btnNewButton.setBounds(471, 328, 124, 21);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane(list); // put jlist in the scroll pane
		scrollPane.setBounds(59, 73, 229, 276);
		contentPane.add(scrollPane);
		
	
		
		
		
		
		
	}
	public void refreshCustomerList() {
		 listCustomerModel.removeAllElements();
		    for (customer c : listOfCustomer) {
		    	listCustomerModel.addElement(c.getOwnerName()); // or update JTable model
		    }
	}
	// function to clear textfield
	private void clearFields() {
		name.setText("");
		phone.setText("");
		address.setText("");
	}
	public void saveReport() {
		PrintWriter out; // create printerWriter object
		try {
			out = new PrintWriter(new FileWriter("D:\\Desktop\\uni\\Second Year\\ISCG - 5421 (Programming)\\Week 10\\customer.txt")); // directory to save the customer.txt file
			for(customer c : listOfCustomer ) { // loop through every customer in listOfCustomer
				 out.println("Start"); 
				 out.println("Name: "+ c.getOwnerName() + "|Address: " +c.getOwneraddress());
				 for(bank b : c.getListOfBank()) { // for each customer object, retrieve list of bank and get each bank object
					 out.println("Bank Name: "+ b.getBankName() + " |Branch code: " +b.getBranchCode());
					 for(account acc : b.getListOfAccount() ) { // for each bank object, retrieve list of account and get account object
						 out.println("Account Number: "+ acc.getAccountNumber() + " |Account Type: " + acc.getAccountType()+ " |Account Balance: "+ acc.getBalance());
						 out.println("");
						 
					 }
					 out.println("End");
				 }

				}
			out.close(); 
		} catch (IOException e) {
			  JOptionPane.showMessageDialog( contentPane , "There is something wrong.", 
                      "Input Error", JOptionPane.ERROR_MESSAGE);
		// display error message if the customer.txt file is not saved
		}
	
		
	}
	public void refreshList() {
		listCustomerModel.removeAllElements(); // remove all elements in the Jlist
		for(customer c : listOfCustomer) {
			listCustomerModel.addElement(c.getOwnerName());
			// for each loop to retrieve owner name of each customer object and add the name to the listModel
		}
	}
	public void addCustomer() {
		//generate sample data
		

		// Customer 1
		customer customer1 = new customer(1, "John Doe", "20 Alberton Avenue, Mount Albert", "02040189255"); // create customer object

		bank bank1_1 = new bank(1, "ANZ", "01"); // create bank object
		bank bank1_2 = new bank(2, "Westpac", "02");

		bank1_1.addAccount(new savingAccount("01-0222222-05", "Saving", 200)); // add the account object to the list of account of each bank object
		bank1_1.addAccount(new checkingAccount("01-0222222-06", "Checking", 1000));

		bank1_2.addAccount(new savingAccount("02-0222222-05", "Saving", 400));
		bank1_2.addAccount(new checkingAccount("02-0222222-06", "Checking", 2000));

		customer1.addBank(bank1_1); // add the bank object to the list of bank in the customer object
		customer1.addBank(bank1_2);
		listOfCustomer.add(customer1);

		// Customer 2
		customer customer2 = new customer(2, "Jane Smith", "55 Remuera Road, Remuera", "02133445566");

		bank bank2_1 = new bank(1, "BNZ", "03");
		bank bank2_2 = new bank(2, "Kiwibank", "04");

		bank2_1.addAccount(new savingAccount("03-1234567-01", "Saving", 1500));
		bank2_1.addAccount(new checkingAccount("03-1234567-02", "Checking", 2500));

		bank2_2.addAccount(new savingAccount("04-7654321-01", "Saving", 800));
		bank2_2.addAccount(new checkingAccount("04-7654321-02", "Checking", 1200));

		customer2.addBank(bank2_1);
		customer2.addBank(bank2_2);
		listOfCustomer.add(customer2);

		// Customer 3
		customer customer3 = new customer(3, "Michael Tan", "89 Queen Street, Auckland Central", "02777788899");

		bank bank3_1 = new bank(1, "ASB", "05");
		bank bank3_2 = new bank(2, "TSB", "06");

		bank3_1.addAccount(new savingAccount("05-9988776-03", "Saving", 3000));
		bank3_1.addAccount(new checkingAccount("05-9988776-04", "Checking", 4000));

		bank3_2.addAccount(new savingAccount("06-3344556-07", "Saving", 500));
		bank3_2.addAccount(new checkingAccount("06-3344556-08", "Checking", 950));

		customer3.addBank(bank3_1);
		customer3.addBank(bank3_2);
		listOfCustomer.add(customer3);

	}
}
