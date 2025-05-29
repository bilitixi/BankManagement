package bankAccountProgram;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import bankAccountProgram.BankGUI.accountType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class BankGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static bank currentBank; // bank object
	private static CustomerGUI customerGUI; //customerGUI object
	private JTextField bankName;
	private JTextField accountNumber;
	private JTextField branchCode;
	private DefaultListModel listAccountModel = new DefaultListModel(); //list model
	JComboBox comboBox;
	public enum accountType { // enum for group of constant value
	    Checking, Saving
	} 


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankGUI frame = new BankGUI(currentBank,  customerGUI); // create new BankGUI object with bank object and customerGUI object
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
	public BankGUI(bank currentBank, CustomerGUI customerGUI) { // BankGUI constructor
		this.currentBank = currentBank; // set currentBank as the bank object that is passed into BankGUI constructor 
		refreshListOfAccount(); // refresh Jlist
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JList list = new JList(listAccountModel); // add items from listmodel to Jlist
		list.setBounds(23, 70, 241, 123);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("List Account");
		lblNewLabel.setBounds(23, 47, 131, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnSaveButton = new JButton("Save details"); //update changes in the bank object
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bankName.getText().trim().equals("") || branchCode.getText().trim().equals("")) 
				{// fields are empty
					 JOptionPane.showMessageDialog( contentPane , "Please fill in all the fields.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); // display notification
				}
				else {
					// fields are fulfilled , call functions in the bank object
				currentBank.changeBankName(bankName.getText());
				currentBank.changeBranchCode(branchCode.getText());
				JOptionPane.showMessageDialog( contentPane , "Details saved", 
                        "Notification", JOptionPane.INFORMATION_MESSAGE); // display success message
				}
			}
		});
		btnSaveButton.setBounds(460, 220, 117, 21);
		contentPane.add(btnSaveButton);
		
		JButton btnExitButton = new JButton("Exit"); // return to the CustomerGUI
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose(); // remove BankGUI window
				 customerGUI.setVisible(true); //display customerGUI window by calling the customerGUI object
				 customerGUI.refreshBankList(); // refresh banklist in BankGUI
				
				 
			}
		});
		btnExitButton.setBounds(589, 389, 106, 21);
		contentPane.add(btnExitButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(404, 286, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// condition to check if the user wanted to add "Checking" or "Saving" account
				if(comboBox.getSelectedItem().toString().equals("Saving") && !(accountNumber.getText().trim().equals(""))) { // if the user added saving account and no fields are empty
				savingAccount currentAccount = new savingAccount(accountNumber.getText(),comboBox.getSelectedItem().toString(),0); // create saving account object
				currentBank.addAccount(currentAccount); //add saving account object to the list of account of the bank object
				listAccountModel.addElement(currentBank.getListOfAccount().getLast().getAccountNumber()); // retrieve the account number from the last account object in the list of account and add to the JList
				clearFields();// clear text fields
				
				}
				else if ( comboBox.getSelectedItem().toString().equals("Checking") && !(accountNumber.getText().trim().equals(""))){ // if the user added checking account and no fields are empty
				checkingAccount currentAccount = new	checkingAccount(accountNumber.getText(),comboBox.getSelectedItem().toString(),0);  // create checking account object
					currentBank.addAccount(currentAccount); //add checking account object to the list of account of the bank object
					listAccountModel.addElement(currentBank.getListOfAccount().getLast().getAccountNumber()); // retrieve the account number from the last account object in the list of account and add to the JList
					clearFields();// clear text fields
				}
				else { // if the user does not fill in all fields
					JOptionPane.showMessageDialog( contentPane , "Please fill in account number.", 
                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddAccount.setBounds(6, 349, 148, 30);
		contentPane.add(btnAddAccount);
		
		JLabel lblBankname = new JLabel("Bank Name:");
		lblBankname.setBounds(404, 92, 95, 13);
		contentPane.add(lblBankname);
		
		JLabel lblBranchCode = new JLabel("Branch Code:");
		lblBranchCode.setBounds(404, 150, 95, 13);
		contentPane.add(lblBranchCode);
		
		bankName = new JTextField();
		bankName.setBounds(484, 89, 148, 19);
		bankName.setColumns(10);
		bankName.setText(currentBank.getBankName());
		contentPane.add(bankName);
		
		
		branchCode = new JTextField();
		branchCode.setBounds(484, 147, 148, 19);
		branchCode.setText(currentBank.getBranchCode());
		contentPane.add(branchCode);
		branchCode.setColumns(10);
		
		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { // try to get the account object from the Jlist
				if(currentBank.getListOfAccount().get(list.getSelectedIndex()).getAccountType().equals("Saving")) { //if the account type is Saving
				accountSavingGUI currentAccount = new accountSavingGUI((savingAccount) currentBank.getListOfAccount().get(list.getSelectedIndex()),BankGUI.this); //create accountSavingGUI object by passing BankGUI object (the current window) and the saving account object which retrieves from the list of account
				currentAccount.setVisible(true); //display accountSavingGUI window
				BankGUI.this.setVisible(false); // remove the BankGUI window
				}
				else { //if the account type is Checking
					accountCheckingGUI currentAccount = new accountCheckingGUI((checkingAccount)currentBank.getListOfAccount().get(list.getSelectedIndex()),BankGUI.this); //create checkingSavingGUI object by passing BankGUI object (the current window) and the checking account object which retrieves from the list of account
					currentAccount.setVisible(true);  //display accountSavingGUI window
					BankGUI.this.setVisible(false); // remove the BankGUI window
				}
				}
				catch(IndexOutOfBoundsException e1) { // catch error when the user does not select any account in the list
					  JOptionPane.showMessageDialog( contentPane , "Please choose an account in the list.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					
				}
			
			}
		});
		btnEditAccount.setBounds(79, 389, 148, 35);
		contentPane.add(btnEditAccount);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				currentBank.getListOfAccount().remove(list.getSelectedIndex()); // get the list of account from the bank object and find the account that has the index which is similar to the selected index in Jlist. After that, remove that account from the list of account
				listAccountModel.remove(list.getSelectedIndex()); // remove the account number in the Jlist
			}
		});
		btnRemoveAccount.setBounds(161, 349, 141, 30);
		contentPane.add(btnRemoveAccount);
		
		accountNumber = new JTextField();
		accountNumber.setBounds(116, 256, 141, 19);
		contentPane.add(accountNumber);
		accountNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Account Number:");
		lblNewLabel_2.setBounds(6, 259, 112, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Account Type:");
		lblNewLabel_2_1.setBounds(23, 307, 112, 13);
		contentPane.add(lblNewLabel_2_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(accountType.values()));
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.setModel(new DefaultComboBoxModel(accountType.values()));
			}
		});
		
		comboBox.setBounds(116, 303, 95, 21);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane(list); // add list to the scroll pane
		scrollPane.setBounds(22, 70, 242, 123);
		contentPane.add(scrollPane);
	}
	private void clearFields() { // clear accountNumber field
		accountNumber.setText("");
		
	}
	private void refreshListOfAccount() { // refresh list of account
		listAccountModel.removeAllElements(); // remove all elements in listAccountModel -> remove all elements in Jlist
		for(account a : currentBank.getListOfAccount()) { // for each loop to loop through list of account of the bank object
			listAccountModel.addElement(a.getAccountNumber()); // retrieve the account number of the account object and add to the list model -> account number is displayed on the Jlist
		}
	}
}
