package controllers;

import org.openqa.selenium.WebDriver;

import pages.CreateEmployeePage;
import pages.HomePage;
import pages.LoginPage;
import utilities.Utility;

public class Controller extends Utility {

	public static void login(String username, String password, WebDriver driver) {

		LoginPage.getUsernameField(driver).clear();
		LoginPage.getUsernameField(driver).sendKeys("Luke");
		LoginPage.getPasswordField(driver).clear();
		LoginPage.getPasswordField(driver).sendKeys("Skywalker");
		LoginPage.getLoginButton(driver).click();

	}

	public static void createEmployee(String firstName, String lastName, String emailAddress, WebDriver driver) {

		HomePage.getCreateUserButton(driver).click();

		CreateEmployeePage.getFirstNameField(driver).sendKeys(firstName);
		CreateEmployeePage.getLastNameField(driver).sendKeys(lastName);
		CreateEmployeePage.getStartDateField(driver).sendKeys(Utility.getCurrentDate());
		CreateEmployeePage.getEmailField(driver).sendKeys(emailAddress);
		CreateEmployeePage.getAddButton(driver).click();

	}

	public static void editEmployee(String employeeFullName, String newFirstName, String newLastName,
			String newEmailAddress, WebDriver driver) throws InterruptedException {

		HomePage.getEmployeeListItem(employeeFullName, driver).click();

		HomePage.getEditEmployeeButton(driver).click();

		CreateEmployeePage.getFirstNameField(driver).clear();
		CreateEmployeePage.getFirstNameField(driver).sendKeys(newFirstName);
		CreateEmployeePage.getLastNameField(driver).clear();
		CreateEmployeePage.getLastNameField(driver).sendKeys(newLastName);
		CreateEmployeePage.getEmailField(driver).clear();
		CreateEmployeePage.getEmailField(driver).sendKeys(newEmailAddress);

		CreateEmployeePage.getUpdateButton(driver).click();

	}

	public static void deleteEmployee(String employeeName, WebDriver driver) {

		HomePage.getEmployeeListItem(employeeName, driver).click();

		HomePage.getDeleteEmployeeButton(driver).click();

		driver.switchTo().alert().accept();

	}

	public static void logout(WebDriver driver) {

		HomePage.getLogoutButton(driver).click();

	}

}
