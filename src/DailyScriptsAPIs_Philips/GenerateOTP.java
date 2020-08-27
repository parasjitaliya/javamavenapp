package DailyScriptsAPIs_Philips;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class GenerateOTP {
	public String data="DemoloyaltyAPIExcels/GenerateOTP.xls";
	public String MasterData="DemoloyaltyAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 
	public String otp;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/GenerateOTP.html");
	public ExtentTest logger =report.startTest("GenerateOTP");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
	@Test
	 public void GeneratOTP() throws IOException, BiffException, JSONException {
	
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver","chromedriver");
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  
		  for (int i = 1; i < s.getRows(); i++) {
		  driver.get("http://demoloyalty.erlpaas.com/apiui/");
		  driver.manage().window().maximize();
		  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
		  dropdown.selectByVisibleText("wsGenerateOTP");
		  Actions a=new Actions(driver);
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.TAB,"{").build().perform();
		  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
		  a.sendKeys(Keys.INSERT,"{").build().perform();
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
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.ENTER,"\"MemberID\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"EmailID\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(1,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/Demoloyalty/GenerateOTP."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);
			  JSONObject jsonObj = new JSONObject(JSONresponse);
			  try {
	              String Requestid = jsonObj.getString("RequestID");
	              System.out.println(Requestid);
	                  File f1=new File("DemoloyaltyAPIExcels/Reuse.xls");
	                  Workbook wb1=Workbook.getWorkbook(f1);
	                  Sheet s1=wb.getSheet(0);
	                  WritableWorkbook wbb1=Workbook.createWorkbook(f1,wb1);
	                  WritableSheet ws1=wbb1.getSheet(0);
	                  Label result=new Label(5,i,Requestid ); 
	                  ws1.addCell(result);
	                  wbb1.write();
	                  wbb1.close();
	      } catch (Exception e) {
	            // TODO: handle exception
	      }
			  
		  if(JSONresponse.contains("Success")){
				    System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/GenerateOTP."+i+".png")); 
		  }
		  else{
				    System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/GenerateOTP."+i+".png")); 
		  } 
			  report.endTest(logger);
			  report.flush();
	    }	  
	}
}
