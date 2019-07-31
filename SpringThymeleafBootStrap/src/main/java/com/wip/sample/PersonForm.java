package com.wip.sample;

	import java.util.Date;
 
import org.springframework.format.annotation.DateTimeFormat;

	public class PersonForm {

    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date startDate;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date endDate;
	    
	    private String dateDiff;

			public Date getStartDate() {
				return startDate;
			}

			public void setStartDate(Date startDate) {
				this.startDate = startDate;
			}

			public Date getEndDate() {
				return endDate;
			}

			public void setEndDate(Date endDate) {
				this.endDate = endDate;
			}

			public String getDateDiff() {
				return dateDiff;
			}

			public void setDateDiff(String dateDiff) {
				this.dateDiff = dateDiff;
			}
			
}
	
