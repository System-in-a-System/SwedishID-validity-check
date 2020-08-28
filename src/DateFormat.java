
public class DateFormat {
	
	// Private data fields
	private int year;
	private int month;
	private int day;
		
	private char punctuation;
	private char format;
		
		
	// Constructors
	public DateFormat() {
		setYear(1900);
		setMonth(1);
		setDay(1);
		setPunctuation('-');
		setFormat('b');
	}
		
	public DateFormat (int theYear, int theMonth, int theDay, char thePunctuation, char theFormat) {
		setYear(theYear);
		setMonth(theMonth);
		setDay(theDay);
		setPunctuation(thePunctuation);
		setFormat(theFormat);
	}
		
		
	
	
	// ---------- Getters and setters for private data fields and their additional properties: ---------- 
		
		
	// YEAR:
	
	public int getYear() {
		return year;
	}
		
	public void setYear(int theYear) {
		if(theYear >= 1900 && theYear <= 2020) 
			year = theYear;
		else 
			year = 0;
	}
	    
	
	// Method to check is the current year is a leap year
	public boolean containsLeapYear() {
			
		boolean isLeapYear = false;
			
		if(getYear() % 4 == 0 && getYear() != 1900 && getYear() != 2000 && getYear() != 2100) 
			isLeapYear = true;
		else if(getYear() % 100 == 0 && getYear() != 1900 && getYear() != 2000 && getYear() != 2100) 
			isLeapYear = true;
		else if(getYear() % 400 == 0) 
			isLeapYear = true;		
			
		return isLeapYear;
	}
		
		
		
	// MONTH:
	
	public int getMonth() {
		return month;
	}
		
	public void setMonth(int theMonth) {
		if(theMonth > 0 && theMonth <= 12) 
			month = theMonth;
		else
			month = 0;
	}
		
	
	// Method to check if the date contains a month that has 31 days
	public boolean containsFullMonth() {
		boolean isFullMonth = false;
			
		if(getMonth() <= 7 && getMonth() != 2 && getMonth() % 2 != 0)
			isFullMonth = true;
			
		if(getMonth() > 7 && getMonth() % 2 == 0) 
			isFullMonth = true;
			
		return isFullMonth;
	}
		
	// Method to check if the date contains February month
	public boolean containsFebruary() {
			
		if(getMonth() == 2) 
			return true;
		else 
			return false;

	}
		
		
		
	// DAY:
	
	public int getDay() {
		return day;
	}
		
	
	public void setDay(int theDay) {
			
		if(theDay > 0 && theDay <= 31) {
			if(this.containsFullMonth())
				day = theDay;	
			else if(!this.containsFullMonth() && !this.containsFebruary() && theDay <= 30) 
				day = theDay;	
			else if(this.containsLeapYear() && this.containsFebruary() && theDay <= 29)
				day = theDay;
			else if(!this.containsLeapYear() && this.containsFebruary() && theDay <= 28)
				day = theDay;
		} else {
			day = 0;
		}
	}
		
		
		
	// Punctuation
	public char getPunctuation() {
		if(punctuation == '\0') 
			return 'e';
	    else 
			return punctuation;
	}
		
	public void setPunctuation(char thePunctuation) {
		if(thePunctuation == '\0') 
			punctuation = 'e';
		else 
			punctuation = thePunctuation;
	}
		
		
		
	// Format
	public char getFormat() {
		if(format == '\0') 
			return 'e';
		else
			return format;	
	}
		
	public void setFormat(char theFormat) {
		if(theFormat == 'b' || theFormat == 'B' || 
		   theFormat == 'm' || theFormat == 'M' ||
		   theFormat == 'l' || theFormat == 'L')
			format = theFormat;
		else 
			format = 'e';
	}
		
		
		
	// ----------------------   Assisting methods: ------------------------------------------------------------------
		
	// Method to wrap the input into a date of customized format
	public String getDate(boolean fullYear) {
			
		String formattedDate = "";
			
			
		// Elements check up for validity 
		if (getYear() == 0 || getMonth() == 0 || getDay() == 0 || getPunctuation() == 'e' || getFormat() == 'e')
			formattedDate = "Invalid date";
		
		
		// If all the elements passed the validity control, formatting starts:
		else {
			// Elements preparation before formatting		
			String year = String.valueOf(getYear());
			if(!fullYear) 
				year = year.substring(2);
			
			
			String month = String.valueOf(getMonth());
			if (getMonth() < 10) 
				month = "0" + month;
			
			
			String day = String.valueOf(getDay());
			if (getDay() < 10) 
				day = "0" + day;
			
			
			String spacing = Character.toString(getPunctuation());
			if (spacing.equals("!")) 
				spacing = "";
			
			
						
			// Format according to a big-endian type
			if (getFormat() == 'b'||getFormat() == 'B') 
				formattedDate = year + spacing + month + spacing + day;
					
			// Format according to a middle-endian type
			else if (getFormat() == 'm'||getFormat() == 'M') 
				formattedDate = month + spacing + day + spacing + year; 
					
			// Format according to a little-endian type
			else if (getFormat() == 'l'||getFormat() == 'L') 
				formattedDate = day + spacing + month + spacing + year;
				
		}
			
		return formattedDate;	
	}

}
