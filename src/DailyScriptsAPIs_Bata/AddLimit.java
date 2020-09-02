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

public class AddLimit {
	public String data="DemoAPIExcels\\AddlimitJSON.xls";
	public String dataUseCoupon="DemoAPIExcels\\UseCouponJSONdata.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Demo/Addlimit.html");
	public ExtentTest logger =report.startTest("Addlimit");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
  @Test
  public void addlimit() throws IOException, BiffException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS); 
	  for (int i = 1; i < s.getRows(); i++) {
	  driver.get("http://demo.erlpaas.com/apiui/");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsAddLimit");
	  Actions a=new Actions(driver);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	   a.sendKeys(Keys.ENTER,"\"MemberID\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"BillDate\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(2,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"IsAlert\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"ReferenceBillNo\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"CouponAmount\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(5,i).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(6,i).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(7,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   
	   		f=new File(dataUseCoupon);
	      wb = Workbook.getWorkbook(f);
	      s=wb.getSheet(0);    
	   a.sendKeys(Keys.ENTER,"\"BillNo\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(2,22).getContents()+"\"").build().perform();
	  
	 
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/Demo/Addlimit."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
		  System.out.println(JSONresponse);
		  JSONObject jsonObj = new JSONObject(JSONresponse);
		  
		  String JSONtag = jsonObj.getString("ReturnMessage");
		  System.out.println(JSONtag);
		  if(JSONtag.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demo/Addlimit."+i+".png")); 
		  }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demo/Addlimit."+i+".png")); 
		  }
		  report.endTest(logger);
			report.flush();
  }
  }
  }
 