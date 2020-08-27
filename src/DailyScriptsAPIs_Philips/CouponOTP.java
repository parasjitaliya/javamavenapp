package DailyScriptsAPIs_Philips;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CouponOTP {
	public String MasterData="DemoloyaltyAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 
	public String otp;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/CouponOTP.html");
	public ExtentTest logger =report.startTest("CouponOTP");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d); 
 @Test
 public void wsCouponOTP() throws BiffException, IOException {
		  f=new File(MasterData);
		  wb=Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver","chromedriver");
		  driver=new ChromeDriver();
		  for(int i=1; i< 10; i++)  {
		  driver.get("http://demoloyalty.erlpaas.com/apiui/");
		  driver.manage().window().maximize();
		  Select s1= new Select(driver.findElement(By.name("ddlSelectMethods")));
		  s1.selectByVisibleText("wsCouponOTP");		
		  Actions a= new Actions(driver);
		  f=new File("DemoloyaltyAPIExcels/Reuse.xls");
		  wb=Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.TAB,"{").build().perform();
		  a.sendKeys(Keys.ENTER,"\"RequestID\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  f=new File(MasterData);
		  wb=Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(0,1).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(1,1).getContents()).build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
			try {
	    		 otp=JOptionPane.showInputDialog("Enter OTP");
			} catch (Exception e) {
				// TODO: handle exception
			}
		  a.sendKeys(Keys.ENTER,"\"OTP\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+otp+"\"").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.name("btnSubmit")).click();
		 
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/Demoloyalty/CouponOTP."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			System.out.println(JSONresponse);
			
		  if(JSONresponse.contains("Success")){
				    System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/CouponOTP."+i+".png")); 
		  }
		  else{
				    System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/CouponOTP."+i+".png")); 
		  } 
			  report.endTest(logger);
			  report.flush();
	    }	  
		  }
 }