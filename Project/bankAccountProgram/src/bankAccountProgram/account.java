
package bankAccountProgram;
import java.util.ArrayList;

public class account {
	protected String accountNumber;
	protected float balance;
	protected String accountType;
	private ArrayList<transaction> transHistory = new ArrayList <transaction>();
	account(String accountNumTemp, String accountTypeTemp, float balanceTemp ){
		accountNumber = accountNumTemp;
		balance = balanceTemp;
		accountType = accountTypeTemp;
	}
	public void changeAccountNumber(String accountNumTemp) {
		accountNumber = accountNumTemp;
	}
	public void deposit(float amountTemp) {
		balance += amountTemp;
	}
	public boolean withdraw(float amountTemp) {
		if(balance < amountTemp) {// if the withdraw amount is less than the balance
			return false; // return false -> cannot withdraw
		}
		else { // if the withdraw amount is more than the balance
		balance -= amountTemp; // update new balance
		return true; //return true -> cannot withdraw
		}
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getAccountType() {
		return this.accountType;
	}
	public float getBalance() {
		return balance;
	}
	public ArrayList <transaction> getTransHistory(){
		return transHistory;
	}
	public void addTransHistory(transaction tempTran) {
		transHistory.add(tempTran); // add transaction object to list of transaction
	}
	

}
