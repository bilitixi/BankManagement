package bankAccountProgram;
public class savingAccount extends account { // inherit from the account class
	private float interestRate;
	private float withdrawLimit;
	private float balanceNextMonth;
    
	savingAccount(String accountNumTemp, String accountType, float balanceTemp){
		super(accountNumTemp, accountType, balanceTemp );
		interestRate = 0.1F; // set default interest rate
		withdrawLimit = 1000; // set default withdrawLimit
		
		
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRateTemp) {
		interestRate = interestRateTemp;
	}
	public float getWithdrawLimit()
	{
		return withdrawLimit;
	}
	public void setWithdrawLimit(float tempWithdrawLimit) {
		withdrawLimit = tempWithdrawLimit;
		
	}
	 public boolean withdraw(double amountTemp) {
	        if (amountTemp >= withdrawLimit) { // if withdraw amount is more than or equals to winthdrawLimit
	            return false; // cannot withdraw
	        } else if (amountTemp > balance) { // withdraw amount is more than balance
	        	return false; // cannot withdraw
	        } else { 
	            balance -= amountTemp; // update new balance
	            return true; // can withdraw
	        }
	    }
	 
	 public float returnBalanceNextMonth(){	
		 balanceNextMonth = balance + (interestRate * balance); // calculate balance nextmonth
		 return balanceNextMonth; 
	 }
	 
}
