package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;

	// Constructor...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators...
	By username = By.name("username");
	By password = By.name("password");
	By loginBtn = By.xpath("//button[@type='submit']");
	By errorMsg = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

	// Actions...
	public void enterUsername(String user) {
		driver.findElement(username).sendKeys(user);
	}

	public void enterPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
	}

	public void clickLogin() {
		driver.findElement(loginBtn).click();
	}

	public String getErrorMessage() {
		return driver.findElement(errorMsg).getText();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void login(String user, String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLogin();
	}
}