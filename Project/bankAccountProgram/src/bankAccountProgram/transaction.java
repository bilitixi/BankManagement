package bankAccountProgram;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class transaction {
	 private LocalDateTime dateTime;
	    private float amount;
	    private String type;        
	   

	    public transaction(float amount, String type) {
	        this.dateTime = LocalDateTime.now(); 
	        this.amount = amount;
	        this.type = type;
	       
	    }

	    public String getDateTime() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        return dateTime.format(formatter);
	    }
	    

	    public float getAmount() {
	        return amount;
	    }

	    public String getType() {
	        return type;
	    }

	   


}
