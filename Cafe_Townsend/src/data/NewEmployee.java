package data;

import utilities.Utility;

public class NewEmployee {
	//class used for generating new employee data and turning it into an object which can be instantiated
	
	public String firstName;
	public String lastName;
	public String emailAddress;
	public String fullName;

	public NewEmployee(boolean detailedLogging) {
		firstName = Utility.generateFirstName();
		lastName = Utility.generateLastName();
		emailAddress = Utility.generateEmailAddress(firstName, lastName);
		fullName = firstName + " " + lastName;

		if (detailedLogging) {
			System.out.println("New employee: " + fullName);
		}
	}
}
