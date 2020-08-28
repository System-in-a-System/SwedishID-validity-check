
public class SwedishID {
		
		// Private data fields
		private DateFormat birthday;
		private int checksum;
		private boolean valid;
		
		// Constructor
		public SwedishID(String personalNumber) {
			
			// Derive birthday information from the input parameters
			String birthdayYear = personalNumber.substring(0, 4);
			int year = Integer.parseInt(birthdayYear);
			
			String birthdayMonth = personalNumber.substring(4, 6); 
			int month = Integer.parseInt(birthdayMonth);
			
			String birthdayDay = personalNumber.substring(6, 8);
			int day = Integer.parseInt(birthdayDay);
			
			
			// Derive checksum number from the input parameters
			String checksumNumbers = personalNumber.substring(9);
			int checksumNumbersInt = Integer.parseInt(checksumNumbers);
			
			
			
			// Use derived birthday information while DateFormat object instantiation 
			birthday = new DateFormat(year, month, day, '!', 'b');
			
			// Attribute derived checksum number to current object's respective data field 
			checksum = checksumNumbersInt;
			
		}
		
		
		
		// Define methods:
		
		// Method to return the entire ID as a string on the format YYMMDD-XXXX.
		public String showID() {
			
			String checksumStringified; 
			
			// 'Stringify' checksum
			if(checksum < 999) 
				checksumStringified = "0" + checksum;
			else
				checksumStringified = String.valueOf(checksum);
			
			
			// Put all pieces together in the correct format
			String ID = birthday.getDate(false) + "-" + checksumStringified;
			
			return ID;
		}
		
		
		// Method to specify if the ID holder is a female
		public boolean isFemale() {
			
			// Check if the third digit of four-digit checksum is even (then it is a female)
			if(checksum / 10 % 2 == 0) 
				return true;
			else
				return false;
		}
		
		
		// Method to compare IDs (based on birthdate)
		public int comparedTo(SwedishID otherID) {
			
			// Derive birth information from both ID holders
			int currentIDBirthYear = this.birthday.getYear();
			int otherIDBirthYear = otherID.birthday.getYear();
			
			int currentIDBirthMonth = this.birthday.getMonth();
			int otherIDBirthMonth = otherID.birthday.getMonth();
			
			int currentIDBirthDay = this.birthday.getDay();
			int otherIDBirthDay = otherID.birthday.getDay();
			
			// Compare:
			
			// by year
			if(currentIDBirthYear - otherIDBirthYear > 0) 
				return 1;
			else if(currentIDBirthYear - otherIDBirthYear < 0) 
				return -1;
			
			// if the years are equal, by month
			else {
				if(currentIDBirthMonth - otherIDBirthMonth > 0) 
					return 1;
				else if(currentIDBirthMonth - otherIDBirthMonth < 0)
					return -1;
				
				// if the months are equal, by day
				else { 
					if(currentIDBirthDay - otherIDBirthDay > 0) 
						return 1;
					else if(currentIDBirthDay - otherIDBirthDay < 0)
						return -1;
					else 
						return 0;
				}
			}
			
		}
		
		// Method to check ID for validity based on Luhn algorithm
		public boolean validID() {
			
			// Get rid of a hyphen ('-') in the identity number
			String stringifiedID = this.showID().substring(0, 6) + this.showID().substring(7, 10);
			
			// Apply Luhn algorithm:
			int currentProduct;
			int sum = 0;
			
			// Multiply every second number with 2 and in case the product is two-digit, sum up those digits
			for(int i = 0; i < stringifiedID.length(); i+=2) {
				currentProduct = Character.getNumericValue(stringifiedID.charAt(i)) * 2;
				if(currentProduct > 9) {
					String twoDigits = Integer.toString(currentProduct);
					int firstDigit = Character.getNumericValue(twoDigits.charAt(0));
					int secondDigit = Character.getNumericValue(twoDigits.charAt(1));
					currentProduct = firstDigit + secondDigit;
				}
				sum += currentProduct;
			}
			
			// Add unmultiplied numbers to the total sum
			for(int i = 1; i < stringifiedID.length(); i+=2) {
				currentProduct = Character.getNumericValue(stringifiedID.charAt(i));
				sum += currentProduct;
			}
			
			// Take out the last digit of the sum
			String stringifiedSum = Integer.toString(sum);
			int lastDigit = Character.getNumericValue(stringifiedSum.charAt(1));
			
			// Calculate the valid checksum
			int calculatedChecksumLastNumber = 10 - lastDigit;
			int currentChecksumLastNumber = Character.getNumericValue(this.showID().charAt(10));
			
			// Compare the calculated checksum to the current checksum 
			if(calculatedChecksumLastNumber == currentChecksumLastNumber) 
				valid = true;
			else 
				valid = false;
			
			
			return valid;
		}

	}

