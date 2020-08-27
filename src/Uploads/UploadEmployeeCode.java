package Uploads;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UploadEmployeeCode {
	//Upload Employee
	
	public WebDriver driver;
	public String credentials="E:\\credentials.xls";
	public Workbook wb;
	public Sheet s;
	File f;
	public ExtentReports report=new ExtentReports("D:\\LpaasWorkspace\\Uploads\\Extent Reports\\UploadEmployeeCode.html");
	public ExtentTest logger =report.startTest("Upload Employee code file");
  @Test
  public void UploadEmployeeCod() throws BiffException, IOException, InterruptedException, AWTException {
	  f=new File(credentials);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", "D:\\Library\\chromedriver.exe");
	  driver=new ChromeDriver();
  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
  driver.get("http://demo.erlpaas.com/");
  driver.manage().window().maximize();
  driver.findElement(By.id("txtMerchantID")).sendKeys("devig@revalsys.com");
  driver.findElement(By.id("txtPassword")).sendKeys("password");
  driver.findElement(By.id("lnkbtrnLogin")).click();
  //Admin panel
  driver.navigate().to(s.getCell(0,3).getContents());
  Thread.sleep(2000);
  //Employee Discount Settings Tab
  driver.findElement(By.xpath("//*[text()='Employee Discount Settings']")).click();
  Thread.sleep(2000);
  //Uploads tab
  driver.findElement(By.xpath("//*[text()='Uploads']")).click();
  Thread.sleep(2000);
  //Upload Activities
  driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_divViewFileUpload']/div[2]/a/span")).click();
  Thread.sleep(2000);
  //Choose file button
  driver.findElement(By.id("ctl00_ContentPlaceHolder1_fudUserFile")).click();
  Thread.sleep(2000);
  //Selecting File from path
  StringSelection ss = new StringSelection("D:\\Mirah Uploads\\UploadEmployee290821.xlsx");
  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
  Thread.sleep(2000);
  
  Robot robot = new Robot();

  robot.keyPress(KeyEvent.VK_CONTROL);
  robot.keyPress(KeyEvent.VK_V);
  robot.keyRelease(KeyEvent.VK_V);
  robot.keyRelease(KeyEvent.VK_CONTROL);
  robot.keyPress(KeyEvent.VK_ENTER);
  robot.keyRelease(KeyEvent.VK_ENTER);
  //Import button
  driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lbtnImport']/span")).click();
  WebElement Successmsg=driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lblSuccess']"));
   if(Successmsg.isDisplayed() )
   {
	  System.out.println("Pass");
	  File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src1, new File("D:\\LpaasWorkspace\\Uploads\\Screenshots\\UploadEmployeeCode.png"));
    logger.log(LogStatus.PASS,"Upload Employee Code is Successfully Uploaded",logger.addScreenCapture("D:\\LpaasWorkspace\\Uploads\\Screenshots\\UploadEmployeeCode.png"));
  }
  else{
	  System.out.println("fail");
	  logger.log(LogStatus.FAIL,"Failed",logger.addScreenCapture("D:\\LpaasWorkspace\\Uploads\\Screenshots\\UploadEmployeeCode.png"));
  }
  report.endTest(logger);
	report.flush();
  }
}
