package bankAccountProgram;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BankGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static bank currentBank;
	private static CustomerGUI customerGUI;
	private JTextField bankName;
	private JTextField branchCode;

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
		
		
		
		JList list = new JList();
		list.setBounds(23, 70, 241, 123);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("List Account");
		lblNewLabel.setBounds(23, 47, 131, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnSaveButton = new JButton("Save details");
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSaveButton.setBounds(460, 220, 117, 21);
		contentPane.add(btnSaveButton);
		
		JButton btnExitButton = new JButton("Exit");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 customerGUI.setVisible(true);
				 BankGUI.this.setVisible(false);
				
				 
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
		bankName.setBounds(116, 256, 148, 19);
		contentPane.add(bankName);
		bankName.setColumns(10);
		
		branchCode = new JTextField();
		branchCode.setBounds(116, 304, 148, 19);
		contentPane.add(branchCode);
		branchCode.setColumns(10);
		
		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.setBounds(79, 389, 148, 35);
		contentPane.add(btnEditAccount);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRemoveAccount.setBounds(161, 349, 141, 30);
		contentPane.add(btnRemoveAccount);
		
		bankName = new JTextField();
		bankName.setBounds(484, 89, 141, 19);
		contentPane.add(bankName);
		bankName.setColumns(10);
		
		branchCode = new JTextField();
		branchCode.setColumns(10);
		branchCode.setBounds(484, 147, 141, 19);
		contentPane.add(branchCode);
		
		JLabel lblNewLabel_2 = new JLabel("Account Number:");
		lblNewLabel_2.setBounds(6, 259, 95, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Account Type:");
		lblNewLabel_2_1.setBounds(23, 307, 95, 13);
		contentPane.add(lblNewLabel_2_1);
	}

}
