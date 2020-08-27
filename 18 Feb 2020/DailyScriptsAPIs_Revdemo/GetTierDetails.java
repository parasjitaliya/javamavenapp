package DailyScriptsAPIs_Revdemo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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

public class GetTierDetails {
	public String data="RevdemoAPIExcels/GetTierdetails.xls";
	public String MasterData="RevdemoAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 
	public String otp;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/14.html");
	public ExtentTest logger =report.startTest("GetTierDetails");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
	@Test
	 public void wsGetTierDetails() throws IOException, BiffException {
		  f=new File(MasterData);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver","/home/harish/Harish/Jar files/chromedriver_linux64 (3)/chromedriver");
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  
		  for (int i = 1; i < 14; i++) {
		  driver.get("http://revdemo.erlpaas.com/apiui/");
		  driver.manage().window().maximize();
		  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
		  dropdown.selectByVisibleText("wsGetTierDetails");
		  Actions a=new Actions(driver);
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.TAB,"{").build().perform();
		  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
		  a.sendKeys(Keys.INSERT,"{").build().perform();
		  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(1,i).getContents()).build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  f=new File(MasterData);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(0,1).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(1,1).getContents()).build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(3,1).getContents()).build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/Demoloyalty/38."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			System.out.println(JSONresponse);
		  if(JSONresponse.contains("TierName")){
				    System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/38."+i+".png")); 
		  }
		  else{
				    System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/38."+i+".png")); 
		  } 
			  report.endTest(logger);
			  report.flush();
	    }	  
	}
}
