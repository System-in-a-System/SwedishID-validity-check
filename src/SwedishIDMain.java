import java.util.Scanner;

public class SwedishIDMain {

	public static void main(String[] args) {
		
		// Prompt user to enter an id number
		System.out.print("Input an id (YYYYMMDD-XXXX): ");
		
		// Intake the input
		Scanner input = new Scanner(System.in);
		String inputID = input.nextLine();
		
		input.close();
		
		
		// Instantiate a new SwedishID object
		SwedishID id = new SwedishID(inputID);
		
		
		
		// Process and output the results		
		System.out.print(id.showID() + " is a ");
		if(id.isFemale())
			System.out.println("female, " + id.calculateAge() + " years old");
		else
			System.out.println("male, " + id.calculateAge() + " years old");
		
		
		
		if(id.validID())
			System.out.println(id.showID() + " is valid");
		else
			System.out.println(id.showID() + " is not valid");
		
	}

}
