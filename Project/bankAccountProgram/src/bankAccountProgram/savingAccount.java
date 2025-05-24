package bankAccountProgram;

public class savingAccount extends account {
	private float interestRate;
	private float withdrawaLimit;
    
	savingAccount(String accountNumTemp, String accountType, float balanceTemp){
		super(accountNumTemp, accountType, balanceTemp );
		interestRate = 10/100;
		withdrawaLimit = 1000;
			
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRateTemp) {
		interestRate = interestRateTemp;
	}
	public float getWithdrawlLimit()
	{
		return withdrawaLimit;
	}
	public void setWithdrawlLimt(float tempWithdrawlLimit) {
		withdrawaLimit = tempWithdrawlLimit;
		
	}
	 public boolean withdraw(double amountTemp) {
	        if (amountTemp >= withdrawaLimit) {
	            return false;
	        } else if (amountTemp > balance) {
	        	return false;
	        } else {
	            balance -= amountTemp;
	            return true;
	        }
	    }
	 
}
