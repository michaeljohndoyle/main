package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	public final static String url = "http://cafetownsend-angular-rails.herokuapp.com/login";

	public static WebElement getUsernameField(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))));

		WebElement usernameField = driver.findElement(By.cssSelector("input[ng-model='user.name']"));
		return usernameField;
	}

	public static WebElement getPasswordField(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))));

		WebElement passwordField = driver.findElement(By.cssSelector("input[ng-model='user.password']"));
		return passwordField;
	}

	public static WebElement getLoginButton(WebDriver driver) {
		//waits for element to be ready for use, and returns element
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")),
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))));

		WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
		return loginButton;
	}

}
