package FKOfferCreation;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Sheet;
import jxl.Workbook;

public class FKReuse {

	public static WebDriver driver;
	public static ExtentReports report = new ExtentReports("/home/trainee/Soumya b/LpaasProg/Report/report.html");
	public static ExtentTest logger = report.startTest("Collecta");
	public static String ScreenshotSavingPath = "/home/trainee/Soumya b/LpaasProg/Report";
	public Workbook wb;
	public Sheet s;
	File f, f1;
	Integer i;
	public static String newDateC;
	public static String newDateF;

	@Test
	public void f() {
	}

	public static void PassScreenshot(WebDriver driver, String screenshotname, String data) throws IOException {

		File f1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f1, new File(ScreenshotSavingPath + "/pass/" + screenshotname + ".png"));
		logger.log(LogStatus.PASS, screenshotname,
				logger.addScreenCapture(ScreenshotSavingPath + "/pass/" + screenshotname + ".png"));
		logger.log(LogStatus.PASS, data);
		report.endTest(logger);
		report.flush();
	}

	public static void FailScreenshot(WebDriver driver, String screenshotname, String data) throws IOException {

		File f1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f1, new File(ScreenshotSavingPath + "/fail/" + screenshotname + ".png"));
		logger.log(LogStatus.FAIL, screenshotname,
				logger.addScreenCapture(ScreenshotSavingPath + "/fail/" + screenshotname + ".png"));
		logger.log(LogStatus.FAIL, data);
		report.endTest(logger);
		report.flush();
	}

	public static void CalenderToday() {
		DateFormat dff = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		newDateC = dff.format(cal.getTime());
		System.out.println(newDateC);
	}

	public static void CalenderTom() { 
		DateFormat dff = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		newDateF = dff.format(cal.getTime());
		System.out.println(newDateF);
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

}
