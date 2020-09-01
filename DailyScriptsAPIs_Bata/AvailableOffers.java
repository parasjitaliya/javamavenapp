package DailyScriptsAPIs_Bata;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
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

public class AvailableOffers {
	public String data="DemoAPIExcels\\AvailableOffers.xls";
	public String MasterData="DemoAPIExcels\\MasterDataDemo.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Demo/AvailablOffers.html");
	public ExtentTest logger =report.startTest("AvailablOffers");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
  @Test
  public void addlimit() throws IOException, BiffException {
	   f=new File(data);
	   wb = Workbook.getWorkbook(f);
	   s=wb.getSheet(0);
	   System.setProperty("webdriver.chrome.driver",utils.Constants.chromeDriverPath);
	   driver=new ChromeDriver();
	   driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	   driver.get("http://demo.erlpaas.com//apiui//");
	   driver.manage().window().maximize();
	   Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	   dropdown.selectByVisibleText("wsAvailableOffers");
	   Actions a=new Actions(driver);
	   a.sendKeys(Keys.TAB,"{").build().perform();
	   a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	   a.sendKeys(Keys.INSERT,"{").build().perform();
	   //Reading data from MasterData Excel	  
	   f=new File(MasterData);
       wb = Workbook.getWorkbook(f);
       s=wb.getSheet(0);
	   a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(1,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(3,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(0,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
       //Reading data from SearchMember Excel
	   f=new File(data);
	   wb = Workbook.getWorkbook(f);
	   s=wb.getSheet(0);
	   a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(1,1).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(2,1).getContents()).build().perform();
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(src, new File("Screenshots\\Demo\\AvailableOffers.png"));
	   String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
		  /*System.out.println(JSONresponse);
		  JSONObject jsonObj = new JSONObject(JSONresponse);
		  String JSONtag = jsonObj.getString("AccrualPoints");
		  System.out.println(JSONtag);
		  int points= Integer.parseInt(JSONtag);*/
		  if(JSONresponse.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("Screenshots\\Demo\\AvailableOffers.png")); 
		  }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("Screenshots\\Demo\\AvailableOffers.png")); 
		  }
		  report.endTest(logger);
			report.flush();
    }
  }
 