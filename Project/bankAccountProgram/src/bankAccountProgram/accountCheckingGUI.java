package bankAccountProgram;

import java.awt.EventQueue;
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

public class accountCheckingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amount;
	private static checkingAccount currentAccount;
	private static BankGUI bankGUI;
	private DefaultListModel listTransModel = new DefaultListModel();
	private JTextField draftLimit;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accountCheckingGUI frame = new accountCheckingGUI(currentAccount, bankGUI);
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
		listTrans.setBounds(45, 53, 254, 199);
		contentPane.add(listTrans);
		
		JLabel lblOverdraftLimit = new JLabel("Overdraft Limit:");
		lblOverdraftLimit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOverdraftLimit.setBounds(319, 111, 135, 26);
		contentPane.add(lblOverdraftLimit);
		
	
		
		JLabel lblNewLabel_1_1 = new JLabel("$");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(562, 111, 41, 26);
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
				currentAccount.setOverDraftLimit(Float.parseFloat(draftLimit.getText()));
				dispose();
				accountCheckingGUI frame = new accountCheckingGUI(currentAccount, bankGUI);
				frame.setVisible(true); }
				catch (NumberFormatException e1) {
				    System.out.println("Invalid float string: " + e1.getMessage());
				}
			}
		});
		btnSave.setBounds(597, 116, 109, 21);
		contentPane.add(btnSave);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				currentAccount.deposit(Float.parseFloat(amount.getText()));
				transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Deposit");
				currentAccount.addTransHistory(currentTransaction);
				listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
				listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
				dispose();
				accountCheckingGUI frame = new accountCheckingGUI(currentAccount, bankGUI);
				frame.setVisible(true); 
				}
				catch (NumberFormatException e1) {
				    System.out.println("Invalid float string: " + e1.getMessage());
				}
			
			}
		});
		btnDeposit.setBounds(597, 262, 109, 21);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean isWithdraw = currentAccount.withdraw(Float.parseFloat(amount.getText()));
					if(isWithdraw == false) {
						System.out.println("Cannot withdraw");
					}
					else {
						transaction currentTransaction = new transaction(Float.parseFloat(amount.getText()),"Withdraw");
						currentAccount.addTransHistory(currentTransaction);
						listTransModel.addElement("|Time: "+ currentAccount.getTransHistory().getLast().getDateTime());
						listTransModel.addElement(currentAccount.getTransHistory().getLast().getType() +": "+ currentAccount.getTransHistory().getLast().getAmount());
						dispose();
						accountCheckingGUI frame = new accountCheckingGUI(currentAccount, bankGUI);
						frame.setVisible(true); 
					}
				}
				catch (NumberFormatException e1) {
				    System.out.println("Invalid float string: " + e1.getMessage());
				}
				
			}
		});
		btnWithdraw.setBounds(589, 298, 135, 21);
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
		
		draftLimit = new JTextField();
		draftLimit.setBounds(446, 117, 96, 19);
		draftLimit.setText(String.format("%.2f", currentAccount.getOverDraftLimit() ));
		contentPane.add(draftLimit);
		draftLimit.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Transaction History:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(45, 32, 208, 13);
		contentPane.add(lblNewLabel_2);
	}


}

