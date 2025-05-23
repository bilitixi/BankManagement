package bankAccountProgram;

import java.awt.EventQueue;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import bankAccountProgram.BankGUI.accountType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BankGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static bank currentBank;
	private static CustomerGUI customerGUI;
	private JTextField bankName;
	private JTextField accountNumber;
	private JTextField branchCode;
	private DefaultListModel listAccountModel = new DefaultListModel();
	JComboBox comboBox;
	public enum accountType {
	    Checking, Saving
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankGUI frame = new BankGUI(currentBank,  customerGUI);
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
	public BankGUI(bank currentBank, CustomerGUI customerGUI) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JList list = new JList(listAccountModel);
		list.setBounds(23, 70, 241, 123);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("List Account");
		lblNewLabel.setBounds(23, 47, 131, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnSaveButton = new JButton("Save details");
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentBank.changeBankName(bankName.getText());
				currentBank.changeBranchCode(branchCode.getText());
			}
		});
		btnSaveButton.setBounds(460, 220, 117, 21);
		contentPane.add(btnSaveButton);
		
		JButton btnExitButton = new JButton("Exit");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 customerGUI.setVisible(true);
				 customerGUI.refreshBankList();
				
				 
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
				if(comboBox.getSelectedItem().toString().equals("Saving")) {
				savingAccount currentAccount = new savingAccount(accountNumber.getText(),comboBox.getSelectedItem().toString(),0);
				currentBank.addAccount(currentAccount);
				listAccountModel.addElement(currentBank.getListOfAccount().getLast().getAccountNumber());
				clearFields();
				
				}
				else {
				checkingAccount currentAccount = new	checkingAccount(accountNumber.getText(),comboBox.getSelectedItem().toString(),0);
					currentBank.addAccount(currentAccount);
					listAccountModel.addElement(currentBank.getListOfAccount().getLast().getAccountNumber());
					clearFields();
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
				
				if(currentBank.getListOfAccount().get(list.getSelectedIndex()).getAccountType().equals("Saving")) {
				accountSavingGUI currentAccount = new accountSavingGUI((savingAccount) currentBank.getListOfAccount().get(list.getSelectedIndex()),BankGUI.this);
				currentAccount.setVisible(true);
				BankGUI.this.setVisible(false);
				}
			
			}
		});
		btnEditAccount.setBounds(79, 389, 148, 35);
		contentPane.add(btnEditAccount);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				currentBank.getListOfAccount().remove(list.getSelectedIndex());
				listAccountModel.remove(list.getSelectedIndex());
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
	}
	private void clearFields() {
		accountNumber.setText("");
		
	}
}
