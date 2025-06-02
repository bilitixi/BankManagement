package bankAccountProgram;
import java.util.ArrayList;

public class customer {
	private int customerID;
	private String name;
	private String address;
	private String phoneNumber;
	private ArrayList <bank> listOfBankAccount;
	customer(int tempID, String nameTemp, String phoneNumberTemp, String addressTemp){
		
		name = nameTemp;
		address = addressTemp;
		phoneNumber = phoneNumberTemp;
		customerID = tempID;
		listOfBankAccount = new ArrayList<bank>();
	}
	public void changeOwnerName(String tempName) {
		name = tempName;
	}
	public String getOwnerName() {
		return name;
	}
	public void changeOwnerAddress(String tempAddress) {
		address = tempAddress;
	}
	
	public String getOwneraddress() {
		return address;
	}
	public void changeOwnerPhoneNumber(String tempPhoneNumber) {
		phoneNumber = tempPhoneNumber;
	}
	public String getOwnerPhoneNumber(){
		return phoneNumber;
	}
	public void addBank(bank bankTemp) {
		listOfBankAccount.add(bankTemp);
	}
	public void deleteBank(int bankIndex) {
		listOfBankAccount.remove(bankIndex);
	}
	public String getBankName(int bankIndex) {
		return listOfBankAccount.get(bankIndex).toString();
	}
	public ArrayList <bank> getListOfBank() {
		return listOfBankAccount;
	}

}
