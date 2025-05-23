package bankAccountProgram;

public class savingAccount extends account {
	private float interestRate;
	private float withdrawalLimit;
    
	savingAccount(String accountNumTemp, String accountType, float balanceTemp){
		super(accountNumTemp, accountType, balanceTemp );
		interestRate = 10/100;
		withdrawalLimit = 1000;
			
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRateTemp) {
		interestRate = interestRateTemp;
	}
	public float getWithdrawlLimit()
	{
		return withdrawalLimit;
	}
	public void setWithdrawlLimt(float tempWithdrawlLimit) {
		withdrawalLimit = tempWithdrawlLimit;
		
	}
	 public boolean withdraw(double amountTemp) {
	        if (amountTemp >= withdrawalLimit) {
	            return false;
	        } else if (amountTemp > balance) {
	        	return false;
	        } else {
	            balance -= amountTemp;
	            return true;
	        }
	    }
	 
}
