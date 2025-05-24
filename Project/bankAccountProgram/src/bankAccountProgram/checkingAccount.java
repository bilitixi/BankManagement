package bankAccountProgram;

public class checkingAccount extends account {
	private float overDraftLimit ;
	checkingAccount(String accountNumTemp, String accountTypeTemp, float balanceTemp){
		super(accountNumTemp, accountTypeTemp,balanceTemp );
		overDraftLimit = 100;
	
	}
	public void setOverDraftLimit(float tempOverDraft) {
		overDraftLimit = tempOverDraft;
	}
	public float getOverDraftLimit() {
		return overDraftLimit;
	}
	public boolean withdraw(float amountWithdraw) {
		if(balance - amountWithdraw >= 0 - overDraftLimit) {
			balance -= amountWithdraw;
			return true;
		}
		else {
			return false;
		}
		
	}

}
