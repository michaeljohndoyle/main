package tests;

import org.openqa.selenium.WebDriver;

import controllers.Controller;
import data.NewEmployee;
import data.OldEmployee;
import data.TestData;
import pages.LoginPage;
import utilities.Utility;

public class FrontEndTests extends Controller {

	private static String canLogin(boolean detailedLogging) {
		//verify a default user can log in
		
		WebDriver driver = Utility.setupTestConfig("chrome", 20, "canLogin");

		driver.get(LoginPage.url);

		Controller.login(TestData.defaultUsername, TestData.defaultPassword, driver);

		String testResult = Utility.testReport(Utility.checkForCreateUserButton(driver), "canLogin");

		Controller.logout(driver);

		driver.quit();

		return testResult;
	}

	private static String canCreateEmployee(boolean detailedLogging) {
		//verify a new employee can be created
		
		WebDriver driver = Utility.setupTestConfig("chrome", 20, "canCreateEmployee");

		driver.get(LoginPage.url);

		Controller.login(TestData.defaultUsername, TestData.defaultPassword, driver);

		NewEmployee newEmployee = new NewEmployee(detailedLogging);

		Controller.createEmployee(newEmployee.firstName, newEmployee.lastName, newEmployee.emailAddress, driver);

		String testResult = Utility.testReport(Utility.checkForEmployee(newEmployee.fullName, driver),
				"canCreateEmployee");

		if (Utility.checkForEmployee(newEmployee.fullName, driver)) {
			try {
				Utility.writeEmployeeToCSV(newEmployee.firstName, newEmployee.lastName, newEmployee.emailAddress, detailedLogging);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Controller.logout(driver);

		driver.quit();

		return testResult;
	}

	private static String canEditEmployee(boolean detailedLogging) throws Exception {
		//verify an existing employee's details can be edited
		
		WebDriver driver = Utility.setupTestConfig("chrome", 20, "canEditEmployee");

		driver.get(LoginPage.url);

		Controller.login(TestData.defaultUsername, TestData.defaultPassword, driver);

		OldEmployee oldEmployee = new OldEmployee(detailedLogging);
		NewEmployee newEmployee = new NewEmployee(detailedLogging);

		Controller.editEmployee(oldEmployee.fullName, newEmployee.firstName, newEmployee.lastName,
				newEmployee.emailAddress, driver);

		String testResult = Utility.testReport(!Utility.checkForEmployee(oldEmployee.fullName, driver),
				Utility.checkForEmployee(newEmployee.fullName, driver), "canEditEmployee");

		if (!Utility.checkForEmployee(oldEmployee.fullName, driver)) {
			Utility.deleteLastEmployeeFromCSV(detailedLogging);
			if (detailedLogging) {
				System.out.println("oldEmployee: " + oldEmployee.fullName + " is not on page employee list");
			}
		}
		if (Utility.checkForEmployee(newEmployee.fullName, driver)) {
			Utility.writeEmployeeToCSV(newEmployee.firstName, newEmployee.lastName, newEmployee.emailAddress, detailedLogging);
			if (detailedLogging) {
				System.out.println("newEmployee: " + newEmployee.fullName + " is on page employee list");
			}
		}

		Controller.logout(driver);

		driver.quit();

		return testResult;
	}

	private static String canDeleteEmployee(boolean detailedLogging) throws Exception {
		//verify an existing employee can be deleted
		
		WebDriver driver = Utility.setupTestConfig("chrome", 20, "canDeleteEmployee");

		driver.get(LoginPage.url);

		Controller.login(TestData.defaultUsername, TestData.defaultPassword, driver);

		OldEmployee oldEmployee = new OldEmployee(detailedLogging);

		Controller.deleteEmployee(oldEmployee.fullName, driver);

		Controller.logout(driver);

		Controller.login(TestData.defaultUsername, TestData.defaultPassword, driver);

		String testResult = Utility.testReport(!Utility.checkForEmployee(oldEmployee.fullName, driver),
				"canDeleteEmployee");

		if (!Utility.checkForEmployee(oldEmployee.fullName, driver)) {
			Utility.deleteLastEmployeeFromCSV(detailedLogging);
			if(detailedLogging){
				System.out.println("oldEmployee " + oldEmployee.fullName + " deleted! Employee deleted from sheet also");
			}
		}

		Controller.logout(driver);

		driver.quit();

		return testResult;
	}

	public static String canLogout(boolean detailedLogging) {
		//verify default user can log out
		
		
		WebDriver driver = Utility.setupTestConfig("chrome", 20, "canLogout");

		driver.get(LoginPage.url);

		Controller.login(TestData.defaultUsername, TestData.defaultPassword, driver);

		Controller.logout(driver);

		String testResult = Utility.testReport(Utility.checkForLoginButton(driver), "canLogout");

		driver.quit();

		return testResult;
	}

	public static void main(String[] args) throws Exception {
		//entry point for test execution
		
		//to execute a test, put the test method name into the 'generateTestSuite' method as an argument
		Utility.generateTestSuite(
				canLogin(true),
				canLogout(true),
				canCreateEmployee(true),
				canEditEmployee(true),
				canCreateEmployee(true),
				canDeleteEmployee(true)
				);
	}
}
