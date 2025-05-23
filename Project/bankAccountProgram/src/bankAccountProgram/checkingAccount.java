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
	public boolean withdrawl(float amountWithdrawl) {
		if(balance - amountWithdrawl < overDraftLimit) {
			balance -= amountWithdrawl;
			return true;
		}
		else {
			return false;
		}
		
	}

}
