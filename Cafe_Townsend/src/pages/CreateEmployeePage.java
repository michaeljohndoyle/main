package pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Utility;

public class CreateEmployeePage {

	public static WebElement getFirstNameField(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[ng-model='selectedEmployee.firstName']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("input[ng-model='selectedEmployee.firstName']"))));
		
		WebElement firstNameField = driver.findElement(By.cssSelector("input[ng-model='selectedEmployee.firstName']"));
		return firstNameField;
	}

	public static WebElement getLastNameField(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[ng-model='selectedEmployee.lastName']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("input[ng-model='selectedEmployee.lastName']"))));
		
		WebElement lastNameField = driver.findElement(By.cssSelector("input[ng-model='selectedEmployee.lastName']"));
		return lastNameField;
	}

	public static WebElement getStartDateField(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[ng-model='selectedEmployee.startDate']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("input[ng-model='selectedEmployee.startDate']"))));
		
		WebElement startDateField = driver.findElement(By.cssSelector("input[ng-model='selectedEmployee.startDate']"));
		return startDateField;
	}

	public static WebElement getEmailField(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[ng-model='selectedEmployee.email']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("input[ng-model='selectedEmployee.email']"))
				));
		
		WebElement emailField = driver.findElement(By.cssSelector("input[ng-model='selectedEmployee.email']"));
		return emailField;
	}

	public static WebElement getAddButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebElement addButton = null;

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main-button")),
				ExpectedConditions.elementToBeClickable(By.className("main-button"))
				));
		
		List<WebElement> elms = driver.findElements(By.className("main-button"));
		Iterator<WebElement> iterator = elms.iterator();

		while (iterator.hasNext()) {
			WebElement elm = iterator.next();

			if (elm.getAttribute("innerText").equals("Add")) {
				addButton = elm;
			}
		}
		return addButton;
	}

	public static WebElement getUpdateButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebElement updateButton = null;
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main-button")),
				ExpectedConditions.elementToBeClickable(By.className("main-button"))
				));
		
		List<WebElement> elms = driver.findElements(By.className("main-button"));
		Iterator<WebElement> iterator = elms.iterator();

		while (iterator.hasNext()) {
			WebElement elm = iterator.next();

			if (elm.getAttribute("innerText").equals("Update")) {
				updateButton = elm;
			}
		}
		return updateButton;
	}

}
