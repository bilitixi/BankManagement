package bankAccountProgram;
import java.util.ArrayList;
public class bank {
	private int bankIndex;
	private String bankName;
	private String branchCode;
	private ArrayList <account> listOfAccounts;
	bank(int indexTemp, String bankNameTemp, String branchCodeTemp){
		bankIndex = indexTemp;
		bankName = bankNameTemp;
		branchCode = branchCodeTemp;
		listOfAccounts = new ArrayList <account>();
		
	}
	public void changeBankName(String tempBankName) {
		bankName = tempBankName;
	}
	public String getBankName() {
		return bankName;
	}
	public void changeBranchCode(String tempBranchCode) {
		branchCode = tempBranchCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void addAccount(account tempAccount){
		listOfAccounts.add(tempAccount);
	}
	public void removeAccount(int accountIndex) {
		listOfAccounts.remove(accountIndex);
	}
	public ArrayList<account> getListOfAccount() {
		return listOfAccounts;
	}

}
