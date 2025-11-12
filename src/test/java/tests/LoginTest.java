package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import utils.WaitUtil;
import utils.ScreenshotUtil;

public class LoginTest extends BaseClass {

	LoginPage loginPage;
	WaitUtil wait;

	@BeforeMethod
	public void setUpBrowser() {
		setUp();
		loginPage = new LoginPage(driver);
		wait = new WaitUtil(driver);
	}

	@Test(priority = 1)
	public void verifyValidLogin() {
		loginPage.login("Admin", "admin123");
		wait.waitForUrlContains("dashboard"); // wait till dashboard URL appears
		String title = loginPage.getPageTitle();
		Assert.assertTrue(title.contains("OrangeHRM"));
	}

	@Test(priority = 2)
	public void verifyInvalidLogin() {
		loginPage.login("Admin", "wrongpass");
		String msg = loginPage.getErrorMessage();
		Assert.assertTrue(msg.contains("Invalid credentials"));
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