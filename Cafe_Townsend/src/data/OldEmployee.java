package data;

import utilities.Utility;

public class OldEmployee {
	//class used for getting the most recently used employee data and turning it into an object which can be instantiated

	public String firstName;
	public String lastName;
	public String emailAddress;
	public String fullName;
	public String employeeTotal;

	public OldEmployee(boolean detailedLogging) {
		String employeeTotal = Utility.getLastCreatedEmployee();

		firstName = Utility.parseOutName(employeeTotal, true, false, false);

		lastName = Utility.parseOutName(employeeTotal, false, true, false);

		fullName = Utility.parseOutName(employeeTotal, false, false, true);

		emailAddress = Utility.generateEmailAddress(firstName, lastName);

		if (detailedLogging) {
			System.out.println("Old employee: " + fullName);
		}
	}
}
