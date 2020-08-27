package DailyScriptsAPIs_Lpaasdemo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EasyPointsAccrualEOSS01 {
	public String data="LpaasDemoExcels/EasyPointsAccrualEOSSJSONdata.xls";
	public String MasterData="LpaasDemoExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Lpaasdemo/10.html");
	public ExtentTest logger =report.startTest("EasyPointsAccrualEOSS");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
  @Test
  public void f() {
  }
  @BeforeMethod
  public void beforeMethod() throws BiffException, IOException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(5l, TimeUnit.SECONDS); 
	  for (int i = 1; i < s.getRows(); i++) {
	  driver.get("http://lpaasdemo.erlpaas.com//apiui//");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsEasyPointsAccrualEOSS");
	  Actions a=new Actions(driver);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  //Reading data from MasterData Excel
	  f=new File(MasterData);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(0,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(1,1).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(2,1).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(3,1).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  //Reading data from EasyPointAccrualEOSS Excel
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  a.sendKeys(Keys.ENTER,"\"TransactionCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Amount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TransactionDate\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ActivityCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(6,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TransactionDescription\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(7,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyPoints\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(9,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Activities\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(10,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EOSSAmount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(11,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"NONEOSSAmount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(12,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"BillCount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(13,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"BillCounter\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(14,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TotalAmount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(15,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TotalPoints\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(16,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"BillTime\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(17,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("screenshots/Lpaasdemo/10."+i+".png"));
	  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	  System.out.println(JSONresponse);
		  /*JSONObject jsonObj = new JSONObject(JSONresponse);
		  String JSONtag = jsonObj.getString("ReturnMessage");
		  System.out.println(JSONtag);*/
	 if(JSONresponse.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Lpaasdemo/10."+i+".png")); 
	 }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Lpaasdemo/10."+i+".png")); 
	 }
		  report.endTest(logger);
			report.flush();
	   
    }
  }
}