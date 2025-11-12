package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import pages.DashboardPage;
import utils.WaitUtil;
import utils.ScreenshotUtil;

public class DashboardTest extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	WaitUtil wait;

	@BeforeMethod
	public void setUpBrowser() {
		setUp();
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		wait = new WaitUtil(driver);
	}

	@Test
	public void verifyDashboardAndLogout() throws InterruptedException {
		// Login...
		loginPage.login("Admin", "admin123");
		wait.waitForUrlContains("dashboard"); // wait till dashboard loads

		// Verify dashboard header...
		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard not displayed!");
		System.out.println("Dashboard Header: " + dashboardPage.getHeaderText());

		// Logout...
		dashboardPage.logout();
		wait.waitForUrlContains("login"); // wait till login URL appears

		// Verify user is back to login page...
		String title = loginPage.getPageTitle();
		Assert.assertTrue(title.contains("OrangeHRM"), "Logout failed!");
		System.out.println("Successfully logged out!");
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