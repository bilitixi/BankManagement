package bankAccountProgram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static customer currentCustomer;
	private static Home parent;
	private JTextField name;
	private JTextField phone;
	private JTextField address;
	private JTextField bankName;
	private JTextField branchCode;
	private DefaultListModel listBankAccountModel = new DefaultListModel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI(currentCustomer,parent);
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
	public CustomerGUI(customer customerTemp, Home parent ) {
		currentCustomer = customerTemp;

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
		
		JList list = new JList(listBankAccountModel);
		list.setBounds(23, 70, 241, 156);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("List of bank ");
		lblNewLabel.setBounds(23, 47, 131, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnSaveButton = new JButton("Save details");
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentCustomer.changeOwnerAddress(address.getText());
				currentCustomer.changeOwnerName(name.getText());
				currentCustomer.changeOwnerPhoneNumber(phone.getText());
			}
		});
		btnSaveButton.setBounds(465, 255, 117, 21);
		contentPane.add(btnSaveButton);
		
		JButton btnExitButton = new JButton("Exit");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 parent.setVisible(true);
				 parent.refreshCustomerList();
				 
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
				bank currentBank = new bank(currentCustomer.getListOfBank().size()+1,bankName.getText(),branchCode.getText());
				currentCustomer.addBank(currentBank);
				listBankAccountModel.addElement(currentCustomer.getListOfBank().getLast().getBankName());
				clearFields();
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
		
		JButton btnEditBank = new JButton("Edit Bank");
		btnEditBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankGUI currentBank = new BankGUI(currentCustomer.getListOfBank().get(list.getSelectedIndex()),CustomerGUI.this);
				currentBank.setVisible(true);
				CustomerGUI.this.setVisible(false);
			}
		});
		btnEditBank.setBounds(79, 389, 148, 35);
		contentPane.add(btnEditBank);
		
		JButton btnRemoveBank = new JButton("Remove Bank");
		btnRemoveBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentCustomer.getListOfBank().remove(list.getSelectedIndex());
				listBankAccountModel.removeElement(list.getSelectedValue());
			}
		});
		btnRemoveBank.setBounds(161, 349, 141, 30);
		contentPane.add(btnRemoveBank);
		
	}
	public void refreshBankList() {
		 listBankAccountModel.removeAllElements();
		 for(bank b : currentCustomer.getListOfBank()) {
			 listBankAccountModel.addElement(b.getBankName());
		 }
	}
	private void clearFields() {
		bankName.setText("");
		branchCode.setText("");
		
	}
	
	
}
