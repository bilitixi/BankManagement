package bankAccountProgram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static customer currentCustomer; // customer object
	private static Home parent; // HomeGUI object
	private JTextField name;
	private JTextField phone;
	private JTextField address;
	private JTextField bankName;
	private JTextField branchCode;
	public DefaultListModel listBankAccountModel = new DefaultListModel(); // listmodel to update Jlist
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI(currentCustomer,parent); // create new CustomerGUI object with customer object and Home object
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
	public CustomerGUI(customer customerTemp, Home parent ) { //customerGUI constructor
		
		this.currentCustomer = customerTemp; // currentCustomer object is passed into the parameters of CustomerGUI constructor
		refreshBankList(); // call this function to refresh the Jlist

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(503, 128, 148, 19);
		name.setColumns(10);
		name.setText(currentCustomer.getOwnerName());
		contentPane.add(name);
		
		phone = new JTextField();
		phone.setBounds(503, 165, 148, 19);
		phone.setText(currentCustomer.getOwnerPhoneNumber());
		contentPane.add(phone);
		phone.setColumns(10);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(503, 207, 148, 19);
		address.setText(currentCustomer.getOwneraddress());
		contentPane.add(address);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(404, 131, 45, 13);
		contentPane.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(404, 168, 89, 13);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(404, 210, 89, 13);
		contentPane.add(lblAddress);
		
		JList list = new JList(listBankAccountModel); // add items in listModel to the JList
		list.setBounds(23, 70, 241, 156);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("List of bank ");
		lblNewLabel.setBounds(23, 47, 131, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnSaveButton = new JButton("Save details");
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// condition to check if the user enter data in all of the text fields
				if(name.getText().trim().equals("") || phone.getText().trim().equals("") || address.getText().trim().equals("")) { 
					  JOptionPane.showMessageDialog( contentPane , "Please fill in all the fields.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					// display error message if the user does not fill in all the text fields
				
				}
				else {
					// call function from the customer object to change data
					currentCustomer.changeOwnerAddress(address.getText());
					currentCustomer.changeOwnerName(name.getText());
					currentCustomer.changeOwnerPhoneNumber(phone.getText());
					JOptionPane.showMessageDialog( contentPane , "Details saved", 
                            "Notification", JOptionPane.INFORMATION_MESSAGE); // display success message
					
					
				}
				
			}
		});
		btnSaveButton.setBounds(465, 255, 117, 21);
		contentPane.add(btnSaveButton);
		
		JButton btnExitButton = new JButton("Exit"); // execute exit button to return Home page
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose(); // remove CustomerGUI window
				 parent.setVisible(true); // display Home window
				 parent.refreshCustomerList(); // refresh customer list in Jlist
				 
			}
		});
		btnExitButton.setBounds(589, 389, 106, 21);
		contentPane.add(btnExitButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(404, 286, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAddBank = new JButton("Add Bank");
		btnAddBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// condition to check if user has filled in all the fields
				if(bankName.getText().trim().equals("")|| branchCode.getText().trim().equals("")) {
					JOptionPane.showMessageDialog( contentPane , "Please fill in all the fields.", 
                            "Input Error", JOptionPane.ERROR_MESSAGE); // display error button if there is blank space
				
				}
				else {
					bank currentBank = new bank(currentCustomer.getListOfBank().size()+1,bankName.getText(),branchCode.getText()); //  create bank object
					currentCustomer.addBank(currentBank); // add bank object to the list of bank of currentCustomer object
					listBankAccountModel.addElement(currentCustomer.getListOfBank().getLast().getBankName()); // add to the listModel to update Jlist
					clearFields(); // clear enter fields
				}
				
			}
		});
		btnAddBank.setBounds(6, 349, 148, 30);
		contentPane.add(btnAddBank);
		
		JLabel lblBankname = new JLabel("Bank Name:");
		lblBankname.setBounds(23, 259, 95, 13);
		contentPane.add(lblBankname);
		
		JLabel lblBranchCode = new JLabel("Branch Code:");
		lblBranchCode.setBounds(23, 307, 95, 13);
		contentPane.add(lblBranchCode);
		
		bankName = new JTextField();
		bankName.setBounds(116, 256, 148, 19);
		contentPane.add(bankName);
		bankName.setColumns(10);
		
		branchCode = new JTextField();
		branchCode.setBounds(116, 304, 148, 19);
		contentPane.add(branchCode);
		branchCode.setColumns(10);
		
		JButton btnEditBank = new JButton("Edit Bank"); // open BankGUI to edit Bank details
		btnEditBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { // try to create BankGUI object and pass bank Object from listOfbank of the customer object and CustomerGUI object
				BankGUI currentBank = new BankGUI(currentCustomer.getListOfBank().get(list.getSelectedIndex()),CustomerGUI.this);
				currentBank.setVisible(true); // display BankGUI window
				CustomerGUI.this.setVisible(false); // disable visual of CustomerGUI window
				}
				catch(IndexOutOfBoundsException e1) {// catch error when the user does not select a bank in the Jlist
					  JOptionPane.showMessageDialog( contentPane , "Please choose a bank in the list.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); // display error notification
				}
			}
		});
		btnEditBank.setBounds(79, 389, 148, 35);
		contentPane.add(btnEditBank);
		
		JButton btnRemoveBank = new JButton("Remove Bank"); // remove selected bank from Jlist
		btnRemoveBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				currentCustomer.getListOfBank().remove(list.getSelectedIndex()); // get the bank object by retrieving index of bank list
				listBankAccountModel.removeElement(list.getSelectedValue()); // remove the name of the bank from the Jlist
				}
				catch (IndexOutOfBoundsException e1) { // catch errors when the user does not select bank name in the list
					 JOptionPane.showMessageDialog( contentPane , "Please choose a bank in the list.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); // display error message
				}
			}
		});
		btnRemoveBank.setBounds(161, 349, 141, 30);
		contentPane.add(btnRemoveBank);
		
	}
	public void refreshBankList() {
		 listBankAccountModel.removeAllElements(); // remove all elements in the Jlist
		 for(bank b : currentCustomer.getListOfBank()) { // for each loop to access each bank object from the list of bank in the customer object
			 listBankAccountModel.addElement(b.getBankName()); // retrieve name of the bank from the bank object and display in on the Jlist
		 }
	}
	private void clearFields() { // clear text field function
		bankName.setText("");
		branchCode.setText("");
		
	}
	
	
}
