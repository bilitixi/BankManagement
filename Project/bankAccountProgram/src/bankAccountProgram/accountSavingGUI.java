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

public class accountSavingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField interestRate;
	private JTextField withdrawLimit;
	private JTextField amount;
	private static savingAccount currentAccount;
	private static BankGUI bankGUI;
	private DefaultListModel listTransModel = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountSavingGUI frame = new accountSavingGUI(currentAccount, bankGUI);
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
		for (transaction trans : currentAccount.getTransHistory() ) {
			listTransModel.addElement("|Time: "+ trans.getDateTime());
			listTransModel.addElement(trans.getType() + ": "+ String.format("%.2f", trans.getAmount()) );
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listTrans = new JList(listTransModel);
		listTrans.setBounds(45, 119, 254, 199);
		contentPane.add(listTrans);
		
		JLabel lblNewLabel = new JLabel("Interest Rate:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(347, 76, 135, 26);
		contentPane.add(lblNewLabel);
		
		interestRate = new JTextField();
		interestRate.setBounds(463, 82, 63, 19);
		interestRate.setText( String.format("%.2f", currentAccount.getInterestRate()));
		contentPane.add(interestRate);
		interestRate.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(536, 76, 51, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWithdrawlLimit = new JLabel("Withdraw limit:");
		lblWithdrawlLimit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWithdrawlLimit.setBounds(347, 111, 135, 26);
		contentPane.add(lblWithdrawlLimit);
		
		withdrawLimit = new JTextField();
		withdrawLimit.setText( String.format("%.2f", currentAccount.getWithdrawlLimit()));
		withdrawLimit.setColumns(10);
		withdrawLimit.setBounds(463, 112, 63, 19);
		contentPane.add(withdrawLimit);
		
		JLabel lblNewLabel_1_1 = new JLabel("$");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(546, 106, 41, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance.setBounds(385, 257, 77, 26);
		contentPane.add(lblBalance);
		
		JLabel lblBalance_num = new JLabel("Balance:");
		lblBalance_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance_num.setText(String.format("%.2f", currentAccount.getBalance()));
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
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(Float.parseFloat(interestRate.getText()) >= 0 && Float.parseFloat(withdrawLimit.getText()) >= 0) {
				currentAccount.setInterestRate(Float.parseFloat(interestRate.getText()));
				currentAccount.setWithdrawlLimt(Float.parseFloat(withdrawLimit.getText()));
				JOptionPane.showMessageDialog( contentPane , "Details saved", 
                        "Notification", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				accountSavingGUI frame = new accountSavingGUI(currentAccount, bankGUI);
				frame.setVisible(true); }
					else {
						JOptionPane.showMessageDialog( contentPane , "Please input positive number", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				catch (NumberFormatException e1) {
					 JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnSave.setBounds(600, 94, 109, 21);
		contentPane.add(btnSave);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(Float.parseFloat(amount.getText()) > 0) {
				currentAccount.deposit(Float.parseFloat(amount.getText()));
				transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Deposit");
				currentAccount.addTransHistory(currentTransaction);
				listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
				listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
				dispose();
				accountSavingGUI frame = new accountSavingGUI(currentAccount, bankGUI);
				frame.setVisible(true); 
					}
					else {
						JOptionPane.showMessageDialog( contentPane , "Please enter positive number", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (NumberFormatException e1) {
					 JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			
			}
		});
		btnDeposit.setBounds(584, 262, 109, 21);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(Float.parseFloat(amount.getText()) > 0) {
					boolean isWithdraw = currentAccount.withdraw(Float.parseFloat(amount.getText()));
					if(isWithdraw == false) {
						 JOptionPane.showMessageDialog( contentPane , "You have exceeded withdraw limit or your balance", 
		                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Withdraw");
						currentAccount.addTransHistory(currentTransaction);
						listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
						listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
						dispose();
						accountSavingGUI frame = new accountSavingGUI(currentAccount, bankGUI);
						frame.setVisible(true); 
					}
					}
					else {
						JOptionPane.showMessageDialog( contentPane , "Please enter positive number", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch (NumberFormatException e1) {
					 JOptionPane.showMessageDialog( contentPane , "Invalid input! Please enter a valid number.", 
	                            "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				}
			
		});
		btnWithdraw.setBounds(574, 293, 135, 21);
		contentPane.add(btnWithdraw);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 bankGUI.setVisible(true);
				
			}
		});
		btnExit.setBounds(624, 403, 85, 21);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_2 = new JLabel("Transaction History:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(38, 83, 208, 13);
		contentPane.add(lblNewLabel_2);
	}
}
