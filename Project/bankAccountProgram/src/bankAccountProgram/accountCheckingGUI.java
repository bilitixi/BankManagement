package bankAccountProgram;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class accountCheckingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amount;
	private static checkingAccount currentAccount;  // checkingAccount object
	private static BankGUI bankGUI; // BankGUI object
	private DefaultListModel listTransModel = new DefaultListModel();  // listModel to store transaction history
	private JTextField draftLimit;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountCheckingGUI frame = new accountCheckingGUI(currentAccount, bankGUI);// create accountCheckingGUI object with currentAccount object and bankGUI object
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
	public accountCheckingGUI(checkingAccount currentAccount, BankGUI bankGUI) {
		this.currentAccount = currentAccount; // set currentAccount object as the account object that is passed into the checkingSavingGUI constructor
		refreshTransHistory();// refresh transaction history
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList listTrans = new JList(listTransModel); // display items in listTransModel in the JList
		listTrans.setBounds(45, 141, 254, 199);
		contentPane.add(listTrans);
		
		JLabel lblOverdraftLimit = new JLabel("Overdraft Limit:");
		lblOverdraftLimit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOverdraftLimit.setBounds(327, 134, 135, 26);
		contentPane.add(lblOverdraftLimit);
		
	
		
		JLabel lblNewLabel_1_1 = new JLabel("$");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(561, 134, 41, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance.setBounds(385, 257, 77, 26);
		contentPane.add(lblBalance);
		
		JLabel lblBalance_num = new JLabel("Balance:");
		lblBalance_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance())); // set the text as the balance of the account object
		lblBalance_num.setBounds(463, 257, 77, 26);
		contentPane.add(lblBalance_num);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("$");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(546, 257, 41, 26);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(385, 293, 77, 26);
		contentPane.add(lblAmount);
		
		amount = new JTextField();
		amount.setColumns(10);
		amount.setBounds(463, 299, 63, 19);
		contentPane.add(amount);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("$");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(546, 293, 41, 26);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() { // user clicks save button
			public void actionPerformed(ActionEvent e) {
				
				try { // try to convert draft limit from string to float
				if(Float.parseFloat(draftLimit.getText()) > 0) { // if the overdraft limit is a positive number
				currentAccount.setOverDraftLimit(Float.parseFloat(draftLimit.getText())); // call the function in the checking account object to change overdraft limit
				JOptionPane.showMessageDialog( contentPane , "Details saved", 
                        "Notification", JOptionPane.INFORMATION_MESSAGE); // display success message
				 }
				else { // user does not enter positive number
					 JOptionPane.showMessageDialog( contentPane , "Please enter positive number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); //display error message
				}
				}
				
				catch (NumberFormatException e1) { //catch errors if the input overdraft limit is not a number
				   
				    JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
                            "Input Error", JOptionPane.ERROR_MESSAGE); //display error message


				}
				
				
					
				
				
			
			}
		});
		btnSave.setBounds(597, 139, 127, 21);
		contentPane.add(btnSave);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() { // user clicks deposit button
			public void actionPerformed(ActionEvent e) { 
			
				try { // try to convert amount from string to float
				if(Float.parseFloat(amount.getText()) > 0) { // if amount input is a positive number
				currentAccount.deposit(Float.parseFloat(amount.getText())); // call deposit function of the checking account object
				transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Deposit"); //create transaction object
				currentAccount.addTransHistory(currentTransaction); // add the newly created transaction object to the list of transaction of the checking account object
				// display data of the transaction in the Jlist
				listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
				listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
				lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance()));
			
				}
				else {// if amount input is not a positive number
					 JOptionPane.showMessageDialog( contentPane , "Please enter positive number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); //display error message
				}
				}
				catch (NumberFormatException e1) { //catch errors if the input amount is not a number
					
					    JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			
			}
		});
		btnDeposit.setBounds(589, 262, 135, 21);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() { // user clicks withdraw button
			public void actionPerformed(ActionEvent e) {
				
				try {// try to convert amount from string to float
					if(Float.parseFloat(amount.getText()) > 0) { // if amount input is a positive number
					boolean isWithdraw = currentAccount.withdraw(Float.parseFloat(amount.getText())); // can the user withdraw ?
					if(isWithdraw == false) { // user exceeded overdraft limit
						 JOptionPane.showMessageDialog( contentPane , "You have exceeded overdraft limit", 
		                            "Input Error", JOptionPane.ERROR_MESSAGE); // display error message
					}
					else { // user does not exceed overdraft limit
						transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Withdraw");
						currentAccount.addTransHistory(currentTransaction);
						listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
						listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
						lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance()));
					}
					}
					else {
						// if amount input is a negative number
						 JOptionPane.showMessageDialog( contentPane , "Please enter positive number.", 
		                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (NumberFormatException e1) {  //catch errors if the input amount is not a number
					    JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}
		});
		btnWithdraw.setBounds(589, 298, 135, 21);
		contentPane.add(btnWithdraw);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() { // close accountCheckingGUI window
			public void actionPerformed(ActionEvent e) {
				 dispose(); // close the accountCheckingGUI window
				 bankGUI.setVisible(true); //display bankGUI window
				
			}
		});
		btnExit.setBounds(624, 403, 85, 21);
		contentPane.add(btnExit);
		
		draftLimit = new JTextField();
		draftLimit.setBounds(455, 140, 96, 19);
		draftLimit.setText(String.format("%.2f", currentAccount.getOverDraftLimit() ));// set the text as the overdraft limit of the checking account object
		contentPane.add(draftLimit);
		draftLimit.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Transaction History:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(51, 102, 208, 13);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane(listTrans);
		scrollPane.setBounds(45, 141, 254, 199);
		contentPane.add(scrollPane);
		
		
	}
	public void refreshTransHistory() { // refresh transaction history
		listTransModel.removeAllElements(); // remove all elements in the list model -> empty Klist
		for (transaction trans : currentAccount.getTransHistory() ) { // for each loop to loop through each transaction object in transaction list of account checking object
			// display transaction data
			listTransModel.addElement("|Time: "+ trans.getDateTime());
			listTransModel.addElement(trans.getType() + ": "+ String.format("%.2f", trans.getAmount()) );
		}
	}


}

