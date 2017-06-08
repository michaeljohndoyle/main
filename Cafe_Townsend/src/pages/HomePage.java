package pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Utility;

public class HomePage {

	public static WebElement getCreateUserButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement createUserButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bAdd")));
		return createUserButton;
	}

	public static WebElement getEditEmployeeButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebElement editEmployeeButton = null;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("subButton")));

		List<WebElement> elms = driver.findElements(By.className("subButton"));
		Iterator<WebElement> iterator = elms.iterator();

		while (iterator.hasNext()) {
			WebElement elm = iterator.next();

			if (elm.getAttribute("innerText").equals("Edit")) {
				editEmployeeButton = elm;
			}
		}
		// Utility.waitSeconds(1);
		return editEmployeeButton;
	}

	public static WebElement getDeleteEmployeeButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebElement deleteEmployeeButton = null;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("subButton")));

		List<WebElement> elms = driver.findElements(By.className("subButton"));
		Iterator<WebElement> iterator = elms.iterator();

		while (iterator.hasNext()) {
			WebElement elm = iterator.next();

			if (elm.getAttribute("innerText").equals("Delete")) {
				deleteEmployeeButton = elm;
			}
		}
		// Utility.waitSeconds(1);
		return deleteEmployeeButton;
	}

	public static WebElement getLogoutButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebElement logoutButton = null;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class='main-button']")));

		List<WebElement> elms = driver.findElements(By.cssSelector("p[class='main-button']"));
		Iterator<WebElement> iterator = elms.iterator();

		while (iterator.hasNext()) {
			WebElement elm = iterator.next();

			if (elm.getAttribute("innerText").equals("Logout")) {
				logoutButton = elm;
			}
		}
		Utility.waitSeconds(1);
		return logoutButton;
	}

	public static WebElement getEmployeeListItem(String employeeName, WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebElement employeeListItem = null;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("li[ng-repeat='employee in employees']")));

		// WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> listItems = driver.findElements(By.cssSelector("li[ng-repeat='employee in employees']"));
		Iterator<WebElement> iterator = listItems.iterator();

		while (iterator.hasNext()) {
			WebElement elm = iterator.next();

			if (elm.getAttribute("innerText").equals(employeeName)) {
				employeeListItem = elm;
			}
		}
		return employeeListItem;
	}

}
