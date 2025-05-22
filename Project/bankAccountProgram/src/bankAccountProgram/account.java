package bankAccountProgram;

public class account {
	private String accountNumber;
	private float balance;
	private String accountType;
	account(String accountNumTemp, String accountTypeTemp ){
		accountNumber = accountNumTemp;
		balance = 0;
		accountType = accountTypeTemp;
	}
	public void changeAccountNumber(String accountNumTemp) {
		accountNumber = accountNumTemp;
	}
	public void deposit(float amountTemp) {
		balance += amountTemp;
	}
	public void withdrawl(float amountTemp) {
		balance -= amountTemp;
	}
	public String getAccountNumber() {
		return accountNumber;
	}

}
