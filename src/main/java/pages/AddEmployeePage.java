package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {

	WebDriver driver;

	// Constructor...
	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators...
	By pimMenu = By.xpath("//span[text()='PIM']");
	By addEmployeeOption = By.xpath("//a[text()='Add Employee']");
	By firstName = By.name("firstName");
	By middleName = By.name("middleName");
	By lastName = By.name("lastName");
	By saveBtn = By.xpath("//button[@type='submit']");
	By employeeNameHeader = By.xpath("//h6[contains(text(),'Personal Details')]");

	// Actions...
	public void openAddEmployeePage() throws InterruptedException {
		driver.findElement(pimMenu).click();
		Thread.sleep(2000);

		driver.findElement(addEmployeeOption).click();
		Thread.sleep(2000);
	}

	public void enterEmployeeDetails(String fName, String mName, String lName) throws InterruptedException {
		driver.findElement(firstName).sendKeys(fName);
		Thread.sleep(1000);

		driver.findElement(middleName).sendKeys(mName);
		Thread.sleep(1000);

		driver.findElement(lastName).sendKeys(lName);
		Thread.sleep(1000);
	}

	public void clickSave() throws InterruptedException {
		driver.findElement(saveBtn).click();
		Thread.sleep(2000);
	}

	public String getEmployeeHeaderText() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // Wait until URL changes to personal details page
	    wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));

	    // Now wait for the header element
	    WebElement headerElement = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6"))
	    );

	    return headerElement.getText();
	}

}