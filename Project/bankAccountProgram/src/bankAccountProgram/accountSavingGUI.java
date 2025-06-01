package bankAccountProgram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class accountSavingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField interestRate;
	private JTextField withdrawLimit;
	private JTextField amount;
	private static savingAccount currentAccount; // savingAccount object
	private static BankGUI bankGUI; // BankGUI object
	private DefaultListModel listTransModel = new DefaultListModel(); // listModel to store transaction history

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountSavingGUI frame = new accountSavingGUI(currentAccount, bankGUI); // create accountSavingGUI object with currentAccount object and bankGUI object
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
	public accountSavingGUI(savingAccount currentAccount, BankGUI bankGUI) {
		this.currentAccount = currentAccount; // set currentAccount object as the account object that is passed into the accountSavingGUI constructor
		refreshTransHistory(); // refresh transaction history
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listTrans = new JList(listTransModel); // display items in listTransModel in the JList
		listTrans.setBounds(395, 177, 254, 199);
		contentPane.add(listTrans);
		
		JLabel lblNewLabel = new JLabel("Interest Rate:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(347, 76, 135, 26);
		contentPane.add(lblNewLabel);
		
		interestRate = new JTextField();
		interestRate.setBounds(473, 82, 63, 19);
		interestRate.setText(String.format("%.2f", currentAccount.getInterestRate())); // set the text as  the interest rate of the account object
		contentPane.add(interestRate);
		interestRate.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(546, 76, 34, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWithdrawlLimit = new JLabel("Withdraw limit:");
		lblWithdrawlLimit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWithdrawlLimit.setBounds(349, 111, 135, 26);
		contentPane.add(lblWithdrawlLimit);
		
		withdrawLimit = new JTextField();
		withdrawLimit.setText( String.format("%.2f", currentAccount.getWithdrawLimit())); // set the text as the the withdraw limit of the account object
		withdrawLimit.setColumns(10);
		withdrawLimit.setBounds(473, 117, 63, 19);
		contentPane.add(withdrawLimit);
		
		JLabel lblNewLabel_1_1 = new JLabel("$");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(546, 111, 28, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance.setBounds(385, 257, 77, 26);
		contentPane.add(lblBalance);
		
		JLabel lblBalance_num = new JLabel("Balance:");
		lblBalance_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance())); // set the text as the the balance of the account object
		lblBalance_num.setBounds(463, 257, 77, 26);
		contentPane.add(lblBalance_num);
		
		JLabel lblbalanceNextMonth = new JLabel("New label");
		lblbalanceNextMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblbalanceNextMonth.setText(String.format("%.2f",currentAccount.getBalanceNextMonth())); // set the balance next month of the saving account by calling the function of account object
		lblbalanceNextMonth.setBounds(473, 154, 63, 13);
		contentPane.add(lblbalanceNextMonth);
		
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
		btnSave.addActionListener(new ActionListener() { // user click save button
			public void actionPerformed(ActionEvent e) {
				
				try { // try to change interest rate and withdraw limit of the object account
					if(Float.parseFloat(interestRate.getText()) >= 0 && Float.parseFloat(withdrawLimit.getText()) >= 0) { // if interest rate and withdraw limit is more than 0

				currentAccount.setInterestRate(Float.parseFloat(interestRate.getText())); //set interest rate of the account object based on the retrieved value of the text field
				currentAccount.setWithdrawLimit(Float.parseFloat(withdrawLimit.getText())); //set withdraw limit of the account object based on the retrieved value of the text field
				JOptionPane.showMessageDialog( contentPane , "Details saved", 
                        "Notification", JOptionPane.INFORMATION_MESSAGE); // display success message
				lblbalanceNextMonth.setText(String.format("%.2f", currentAccount.getBalanceNextMonth())); // set the text as the the balance next mpnth of the account object
				
					}
				
					else { // if the interest rate and withdraw limit is the negative number
						JOptionPane.showMessageDialog( contentPane , "Please input positive number", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); // display message
					}
				}
				
				catch (NumberFormatException e1) { // catch errors if the input is not a valid number
					 JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); // display error message
				}
				
				
				
			}
		});
		btnSave.setBounds(600, 94, 109, 21);
		contentPane.add(btnSave);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() { // user clicks deposit button
			public void actionPerformed(ActionEvent e) {
				
				try { // try to retrieve the value of the input text field to see if it can be able to convert to float
					if(Float.parseFloat(amount.getText()) > 0) { // if the input value is a positive number
				currentAccount.deposit(Float.parseFloat(amount.getText())); // call the deposit function of the account object
				transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Deposit"); // create transaction object
				currentAccount.addTransHistory(currentTransaction); // add the newly created transaction object at line 167 into the list of transaction of the account object
				// display time of the transaction in the Jlist
				listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime()); 
				listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
				lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance())); // update the balance of the account object
				lblbalanceNextMonth.setText(String.format("%.2f",currentAccount.getBalanceNextMonth())); // set the balance next month of the saving account after new balance updated by calling the function of account object
				}
					else {
						JOptionPane.showMessageDialog( contentPane , "Please enter positive number", // if the input value is not a positive number
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (NumberFormatException e1) { // catch errors if the input text field can not be able to convert to float
					 JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); //display error message
				}
				
				
			
			}
		});
		btnDeposit.setBounds(584, 262, 109, 21);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() { // user clicks withdraw button
			public void actionPerformed(ActionEvent e) {
				
				try {  // try to retrieve the value of the input text field to see if it can be able to convert to float
					if(Float.parseFloat(amount.getText()) > 0) { // if the input value is a positive number
					boolean isWithdraw = currentAccount.withdraw(Float.parseFloat(amount.getText())); // can the user withdraw?
					if(isWithdraw == false) { // user exceeds withdraw limit
						 JOptionPane.showMessageDialog( contentPane , "You have exceeded withdraw limit or your balance", 
		                            "Input Error", JOptionPane.ERROR_MESSAGE); 
					}
					else { // user did not exceed withdraw limit
						transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Withdraw"); // create transaction object
						currentAccount.addTransHistory(currentTransaction); // add the newly created transaction object at line 203 into the list of transaction of the account object
						// display data of the transaction in the Jlist
						listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
						listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
						lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance()));
						lblbalanceNextMonth.setText(String.format("%.2f",currentAccount.getBalanceNextMonth())); // set the balance next month of the saving account after new balance is updated by calling the function of account object
					}
					}
					else { // if the input value is not a positive number
						JOptionPane.showMessageDialog( contentPane , "Please enter positive number", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch (NumberFormatException e1) { // catch errors if the input text field can not be able to convert to float
					 JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE); //display error message
				}
				}
			
		});
		btnWithdraw.setBounds(574, 293, 135, 21);
		contentPane.add(btnWithdraw);
		
		JButton btnExit = new JButton("Exit"); 
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // user clicks exit button
				 dispose(); //accountSavingGUI window is closed
				 bankGUI.setVisible(true); //bankGUI window is opened
				
			}
		});
		btnExit.setBounds(624, 403, 85, 21);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_2 = new JLabel("Transaction History:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(45, 76, 208, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Balance Next Month:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(309, 147, 155, 26);
		contentPane.add(lblNewLabel_3);
		
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("$");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(546, 147, 28, 26);
		contentPane.add(lblNewLabel_1_1_2);
		
		JScrollPane scrollPane = new JScrollPane(listTrans); // create scroll pane and add jlist so that the list of transaction details can be scrolled
		scrollPane.setBounds(45, 111, 233, 225);
		contentPane.add(scrollPane);
	}
	public void refreshTransHistory() { // refresh function
		listTransModel.removeAllElements(); // remove all elements of listTransModel
		for (transaction trans : currentAccount.getTransHistory() ) { // for loop each for each transaction object in the list of transaction of the current account
			// display transaction detail
			listTransModel.addElement("|Time: "+ trans.getDateTime());
			listTransModel.addElement(trans.getType() + ": "+ String.format("%.2f", trans.getAmount()) );
		}
	}
}
