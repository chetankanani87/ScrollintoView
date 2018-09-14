package scrollIntoView;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import lib.BrowserDriverUtility;
import lib.ExtentReportUtility;
import lib.ScreenshotUtility;


public class ScrollIntoView {
	WebDriver dr = BrowserDriverUtility.InvokeBrowser("webdriver.chrome.driver",
			"C:\\Chetan\\SeleniumSuite\\WebDrivers\\chromedriver.exe",
			"http://manos.malihu.gr//repository//custom-scrollbar//demo//examples//complete_examples.html");
	ExtentReports report = ExtentReportUtility.InvokeExtentReport();;
	ExtentTest logger = report.createTest("Scrolling Into View");
	String path;
	
	@BeforeTest
	public void InvokeBrowser() {
		try {
			path = ScreenshotUtility.CaptureScreenshot(dr, "MainPage");
			logger.pass("Main Page - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void scrolling() {
		try {
			//To scroll page to a particular 'ele' element
			JavascriptExecutor jse = (JavascriptExecutor) dr;
			WebElement ele = dr.findElement(By.xpath("//div[@id='content-3']//p[11]"));
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			
			path = ScreenshotUtility.CaptureScreenshot(dr, "AfterScrolling");
			logger.pass("After Scrolling Down Content is verified - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			System.out.println(ele.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void tearDown() {
		report.flush();
		dr.close();
	}
}
