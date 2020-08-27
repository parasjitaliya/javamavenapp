package DailyScriptsAPIsMiddleast;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
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

public class GetCustomerTransactionDetails {
	public String data="DemoloyaltyAPIExcels/GetCustomerTransactionDetails.xls";
	public String MasterData="DemoloyaltyAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 
	public String otp;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/41.html");
	public ExtentTest logger =report.startTest("wsGetCustomerTransactionDetails");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
	@Test
	 public void wsGetCustomerTransactionDetails() throws IOException, BiffException, JSONException {
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver","/home/harish/Harish/Jar files/chromedriver_linux64 (3)/chromedriver");
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  
		  for (int i = 1; i <s.getRows(); i++) {
		  driver.get("http://demome.erlpaas.com/apiui/");
		  driver.manage().window().maximize();
		  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
		  dropdown.selectByVisibleText("wsGetCustomerTransactionDetails");
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
		  a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(1,1).getContents()).build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
		  a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  a.sendKeys(Keys.ENTER,"\"TransactionTypeId\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(0,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"TransactionDetailsCount\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(1,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"PageSize\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"PageNumber\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(3,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"StartDate\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(4,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"EndDate\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"BillNo\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(6,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"Wallet\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(7,i).getContents()+"\"").build().perform();		
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/Demoloyalty/41."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
		  System.out.println(JSONresponse);
		  if(JSONresponse.contains("Member ID does not exists.")){
			    System.out.println("Fail");
		    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/41."+i+".png")); 
	       }  else  {
		   JSONObject jsonObj = new JSONObject(JSONresponse);
		   System.out.println(jsonObj);
		   JSONArray arr = jsonObj.getJSONArray("MemberTransactionResponseListDTO");
 		   for(int i1 = 0; i1 < arr.length(); i1++) {
 			  String retnmsg =	arr.getJSONObject(i1).getString("TotalAccruedPoints"); 
 			  System.out.println(retnmsg);
 			     System.out.println("Pass");
			     logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/41."+i+".png")); 
 		   }
	       }
			  report.endTest(logger);
			  report.flush();
 		   }  
	}
}
