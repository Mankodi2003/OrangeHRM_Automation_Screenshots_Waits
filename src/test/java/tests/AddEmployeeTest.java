package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.AddEmployeePage;
import org.openqa.selenium.By;
import utils.WaitUtil;
import utils.ScreenshotUtil;

public class AddEmployeeTest extends BaseClass {

	LoginPage loginPage;
	AddEmployeePage addEmpPage;
	WaitUtil wait;

	@BeforeMethod
	public void setUpBrowser() {
		setUp();
		loginPage = new LoginPage(driver);
		addEmpPage = new AddEmployeePage(driver);
		wait = new WaitUtil(driver);
	}

	@Test
	public void verifyAddEmployee() throws InterruptedException {

		// step-1 : Login...
		loginPage.login("Admin", "admin123");
		wait.waitForUrlContains("dashboard"); // wait till dashboard loads

		// step-2 : Go To Add Employee Page...
		addEmpPage.openAddEmployeePage();

		// step-3 : Enter Details and Save...
		addEmpPage.enterEmployeeDetails("John", "D", "Smith");
		addEmpPage.clickSave();
		wait.waitForElementToBeVisible(By.xpath("//h6[text()='Personal Details']")); // wait for success header

		// step-4 : Verify success...
		String header = driver.findElement(By.xpath("//h6[text()='Personal Details']")).getText();
		System.out.println("Header Text: " + header);

		Assert.assertEquals(header, "Personal Details",
				"Employee not added successfully — Personal Details page not displayed!");
	}

	@AfterMethod
	public void closeBrowser(org.testng.ITestResult result) {
		// Take screenshot if test failed
		if (result.getStatus() == org.testng.ITestResult.SUCCESS) {
			ScreenshotUtil.captureScreenshot(driver, result.getName());
		}
		tearDown();
	}
}