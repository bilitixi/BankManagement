package bankAccountProgram;
public class savingAccount extends account {
	private float interestRate;
	private float withdrawLimit;
	private float balanceNextMonth;
    
	savingAccount(String accountNumTemp, String accountType, float balanceTemp){
		super(accountNumTemp, accountType, balanceTemp );
		interestRate = 0.1F;
		withdrawLimit = 1000;
		
		
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
	        if (amountTemp >= withdrawLimit) {
	            return false;
	        } else if (amountTemp > balance) {
	        	return false;
	        } else {
	            balance -= amountTemp;
	            return true;
	        }
	    }
	 
	 public float returnBalanceNextMonth(){	
		 balanceNextMonth = balance + (interestRate * balance);
		 return balanceNextMonth; 
	 }
	 
}
