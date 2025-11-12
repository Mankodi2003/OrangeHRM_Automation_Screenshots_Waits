package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static void captureScreenshot(WebDriver driver, String fileName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir") + "/screenshots/" + fileName + ".png");
			FileUtils.copyFile(src, dest);
			System.out.println("Screenshot Saved at : " + dest.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}