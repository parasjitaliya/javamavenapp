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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UploadSKUOfferTransaction {
	//Upload SKU Offer Transaction
	
	public WebDriver driver;
	public String credentials="E:\\credentials.xls";
	public Workbook wb;
	public Sheet s;
	File f;
	public ExtentReports report=new ExtentReports("D:\\LpaasWorkspace\\Uploads\\Extent Reports\\UploadSKUOfferTransaction.html");
	public ExtentTest logger =report.startTest("Upload SKU Offer Transaction file");
  @Test
  public void UploadSKUOfferTransactio() throws InterruptedException, AWTException, BiffException, IOException {
	  f=new File(credentials);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", "D:\\Library\\chromedriver.exe");
	  driver=new ChromeDriver();
  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
  driver.get(s.getCell(0,0).getContents());
  driver.manage().window().maximize();
  driver.findElement(By.id("txtMerchantID")).sendKeys(s.getCell(0,1).getContents());
  driver.findElement(By.id("txtPassword")).sendKeys(s.getCell(0,2).getContents());
  driver.findElement(By.id("lnkbtrnLogin")).click();
  //Admin panel
  driver.navigate().to(s.getCell(0,3).getContents());
  Thread.sleep(2000);
  //View Tab
  driver.findElement(By.xpath("//*[text()='View']")).click();
  Thread.sleep(2000);
  //UploadSKUOfferTransaction tab
  driver.findElement(By.xpath("//*[text()='Upload SKU Offer Transaction']")).click();
  Thread.sleep(2000);
  //Upload Activities
  driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_divViewFileUpload']/div[1]/a/span")).click();
  Thread.sleep(2000);
  //Selecting SKU Offer
  Select skuoffer=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlSKUOffersList']")));
  Thread.sleep(2000);
  skuoffer.selectByVisibleText("SKUOffer22");
  Thread.sleep(2000);
  //Choose file button
  driver.findElement(By.id("ctl00_ContentPlaceHolder1_fudTransactions")).click();
  Thread.sleep(2000);
  //Selecting File from path
  StringSelection ss = new StringSelection("D:\\Mirah Uploads\\UploadSKUTransactionService2908.xlsx");
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
	  FileUtils.copyFile(src1, new File("D:\\LpaasWorkspace\\Uploads\\Screenshots\\UploadSKUOfferTransaction.png"));
    logger.log(LogStatus.PASS,"Upload SKU Offer Transaction file is Successfully Uploaded",logger.addScreenCapture("D:\\LpaasWorkspace\\Uploads\\Screenshots\\UploadSKUOfferTransaction.png"));
  }
  else{
	  System.out.println("fail");
	  logger.log(LogStatus.FAIL,"Failed",logger.addScreenCapture("D:\\LpaasWorkspace\\Uploads\\Screenshots\\UploadSKUOfferTransaction.png"));
  }
  report.endTest(logger);
	report.flush();
  }
}
