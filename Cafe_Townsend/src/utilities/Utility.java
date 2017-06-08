package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import data.TestData;
import pages.HomePage;
import pages.LoginPage;

public class Utility {

	public static WebDriver setupTestConfig(String browser, int loadTimeOutSec, String testName) {
		
		//initialises driver
		WebDriver driver = null;
		//instantiates driver based on argument
		if (browser == "chrome") {
			System.setProperty("webdriver.chrome.driver", TestData.chromeDriverPath);
			driver = new ChromeDriver();
		} else if (browser == "firefox") {
			System.setProperty("webdriver.firefox.bin", TestData.firefoxDriverPath);
			driver = new FirefoxDriver();
		} else if (browser == "ie" || browser == "internet explorer") {
			System.setProperty("webdriver.ie.driver", TestData.internetExplorerDriverPath);
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("NOT A RECOGNISED BROWSERTYPE. PLEASE CHOOSE EITHER: 'chrome', 'firefox', OR 'ie'");
		}
		//configures driver to wait up to 10 seconds when searching for an element. configuration lasts whole driver lifetime
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("--NEW TEST INITIATED: " + testName + "--");

		return driver;
	}

	public static String generateFirstName() {
		Random rand = new Random();
		int randNum = rand.nextInt(100) + 1;
		String randFirstName = "testFirstName" + String.valueOf(randNum);
		return randFirstName;
	}

	public static String generateLastName() {
		Random rand = new Random();
		int randNum = rand.nextInt(100) + 1;
		String randLastName = "testLastName" + String.valueOf(randNum);
		return randLastName;
	}

	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		return currentDate;
	}

	public static String generateEmailAddress(String firstName, String lastName) {
		String randEmailAddress = firstName + lastName + "@mailinator.com";
		return randEmailAddress;
	}

	public static String parseOutName(String original, boolean first, boolean last, boolean full) {
		//takes a string (formatted as an employee entry from the csv) and returns either the first, last, or full name
		
		int firstComma = original.indexOf(",", 0);
		int secondComma = original.indexOf(",", firstComma + 1);

		String firstName = original.substring(0, firstComma);
		String lastName = original.substring(firstComma + 1, secondComma);
		String fullName = firstName + " " + lastName;
		fullName = fullName.replace("\n", "");

		if (first && !last && !full)
			return firstName;
		else if (!first && last && !full) {
			return lastName;
		} else if (!first && !last && full) {
			return fullName;
		} else {
			System.out.println("INVALID ARGUMENTS. ONLY ONE TRUE STATEMENT CAN BE PASSED TO METHOD. RETURNING ORIGINAL STRING...");
			return original;
		}
	}

	public static boolean checkForEmployee(String employeeName, WebDriver driver) {
		//searches the home page to verify whether an employee is present
		
		boolean employeePresent = false;

		if (HomePage.getEmployeeListItem(employeeName, driver) != null) {
			employeePresent = true;
		}
		return employeePresent;
	}

	public static boolean checkForCreateUserButton(WebDriver driver) {
		//searches the home page to verify whether the 'create user' button is present
		
		boolean createUserButtonPresent;

		try {
			HomePage.getCreateUserButton(driver).isDisplayed();
			createUserButtonPresent = true;
		} catch (StaleElementReferenceException e) {
			createUserButtonPresent = false;
		} catch (NoSuchElementException a) {
			createUserButtonPresent = false;
		}
		return createUserButtonPresent;
	}

	public static boolean checkForLoginButton(WebDriver driver) {
		//searches the page to verify whether the 'login' button is present
		
		boolean loginButtonPresent;

		try {
			LoginPage.getLoginButton(driver).isDisplayed();
			loginButtonPresent = true;
		} catch (StaleElementReferenceException e) {
			loginButtonPresent = false;
		} catch (NoSuchElementException a) {
			loginButtonPresent = false;
		}
		return loginButtonPresent;
	}

	public static boolean checkForLogoutButton(WebDriver driver) {
		//searches the page to verify whether the 'logout' button is present
		
		boolean logoutButtonPresent;
		try {
			HomePage.getLogoutButton(driver).isDisplayed();
			logoutButtonPresent = true;
		} catch (StaleElementReferenceException e) {
			logoutButtonPresent = false;
		} catch (NoSuchElementException a) {
			logoutButtonPresent = false;
		}
		return logoutButtonPresent;
	}

	public static void waitSeconds(int seconds) {
		//causes the test execution to wait for a number of seconds
		
		long timeToWait = seconds * 1000;
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeEmployeeToCSV(String firstName, String lastName, String emailAddress, boolean detailedLogging) throws IOException {
		//writes an employee's first name, last name, and email address into a csv on the first blank row
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String data = "\n" + firstName + "," + lastName + "," + emailAddress;
			//defines csv filepath location
			File CSVFile = new File(TestData.CSVFilePath);

			// if file doesn't exist, create new file
			if (!CSVFile.exists()) {
				CSVFile.createNewFile();
				if (detailedLogging) {
					System.out.println("CSV did not exist, a new CSV was created.");
				}
			}
			byte[] encoded = null;
			try {
				encoded = Files.readAllBytes(Paths.get(TestData.CSVFilePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String docStr = new String(encoded, Charset.defaultCharset());
			String colHeaders = "FirstName,LastName,EmailAddress";

			fw = new FileWriter(CSVFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			//if headings don't exist in the file, write headings
			if (docStr.length() == 0) {
				bw.write(colHeaders);
				if (detailedLogging) {
					System.out.println("CSV was blank, headers were written to CSV.");
				}
			}
			bw.write(data);

			if (detailedLogging) {
				System.out.println("CSV updated. Added " + firstName + " " + lastName + " to CSV.");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void deleteLastEmployeeFromCSV(boolean detailedLogging) throws IOException {
		//deletes the last employee record from the csv. CSV file with at least one employee must exist
		
		// convert CSV file contents to a string
		byte[] encoded = Files.readAllBytes(Paths.get(TestData.CSVFilePath));
		String docStr = new String(encoded, Charset.defaultCharset());
		
		// cut the last employee off the string
		int lastNL = docStr.lastIndexOf("\n");
		String newDocStr = docStr.substring(0, lastNL);
		String deletedString = docStr.substring(lastNL, docStr.length());
		File oldFile = new File(TestData.CSVFilePath);
		oldFile.delete();

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File CSVFile = new File(TestData.CSVFilePath);

			// if file does exist, append existing file
			fw = new FileWriter(CSVFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(newDocStr);
			String deletedEmployeeFullName = Utility.parseOutName(deletedString, false, false, true);

			if (detailedLogging) {
				System.out.println("CSV updated. Deleted last employee (" + deletedEmployeeFullName + ") from CSV");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static String getLastCreatedEmployee() {
		//returns the last employee record from the CSV as a string
		
		//converts whole document into a string
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(TestData.CSVFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String docStr = new String(encoded, Charset.defaultCharset());
		
		// cut the last employee off the string
		int lastNL = docStr.lastIndexOf("\n", docStr.length() - 5);
		String lastCreatedEmployee = docStr.substring(lastNL, docStr.length());
		lastCreatedEmployee = lastCreatedEmployee.replace("\n", "");

		return lastCreatedEmployee;
	}

	
	public static String testReport(boolean validationCondition, String testName) {
		//generate a test report dictating whether test has passed or failed based on a given condition. Prints result to console and returns 'pass' or 'fail' accordingly
		
		String passFail = null;
		if (validationCondition) {
			System.out.println("Test '" + testName + "': PASSED");
			passFail = "pass";
		} else {
			System.out.println("Test '" + testName + "': FAILED");
			passFail = "fail";
		}
		return passFail;
	}
	// overloaded method to handle multiple test validation conditions
	public static String testReport(boolean validationCondition, boolean validationCondition2, String testName) {
		//generate a test report dictating whether test has passed or failed based on a given condition. Prints result to console and returns 'pass' or 'fail' accordingly

		String passFail = null;
		if (validationCondition && validationCondition2) {
			System.out.println("Test '" + testName + "': PASSED");
			passFail = "pass";
		} else {
			System.out.println("Test '" + testName + "': FAILED");
			passFail = "fail";
		}
		return passFail;
	}

	public static void generateTestSuite(String... tests) {
		//generates a test suite report which takes in the return values of tests passed as arguments. Prints report to console
		
		int totalTests = tests.length;
		int passCount = 0;
		int failCount = 0;
		for (int i = 0; i < totalTests; ++i) {
			if (tests[i] == "pass") {
				passCount++;
			} else {
				failCount++;
			}
		}
		System.out.println("TEST SUITE EXECUTION FINISHED.");
		System.out.println("----TEST REPORT----\n" + totalTests + " tests run. Passed: " + passCount + ". Failed: " + failCount);

	}
}
