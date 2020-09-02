package DailyScriptsAPIs_Bata;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class IsCustomer {
	
	// Need to change Security Token of this api only in CustomerProfileJSONdata file in order to cover all the test cases.
	// need to change URL
	public String data="DemoAPIExcels\\CustomerProfileJSONdata.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 	
	public ExtentReports report=new ExtentReports("ExtentReports\\Soch\\IsCustomer.html");
	public ExtentTest logger =report.startTest("IsCustomer API");
	
	

	
  @Test (priority=0)
  public void isCustomer() throws IOException, BiffException {
	      //Reading SecurityToken,store code and user name From SecurityToken Excel
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver",utils.Constants.chromeDriverPath);
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  
		  
		  for (int i = 1; i < s.getRows(); i++) {
		  driver.get("http://demo.erlpaas.com//apiui//");
		  driver.manage().window().maximize();
		  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
		  dropdown.selectByVisibleText("wsIsCustomer");
		  Actions a=new Actions(driver);
		  a.sendKeys(Keys.TAB,"{").build().perform();
		  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
		  a.sendKeys(Keys.INSERT,"{").build().perform();
		  a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(1,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(0,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(4,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(3,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/IsCustomer."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);
			 JSONObject jsonObj = new JSONObject(JSONresponse);
			 
			 
	if (JSONresponse.contains(s.getCell(5,i).getContents())) {
		
		 
//if(JSONresponse.contains("ReturnCode")&&JSONresponse.contains("ReturnMessage")&&JSONresponse.contains("FirstName")&&JSONresponse.contains("LastName")&&JSONresponse.contains("Email")&&JSONresponse.contains("Mobile")&&JSONresponse.contains("ClientID")&&JSONresponse.contains("DateOfBirth")&&JSONresponse.contains("AvailablePoints")&&JSONresponse.contains("MembershipCardNumber")&&JSONresponse.contains("CurrentTier")&&JSONresponse.contains("TotalVisits")&&JSONresponse.contains("TierDiscountType")&&JSONresponse.contains("TierDiscount")&&JSONresponse.contains("CustomerType")&&JSONresponse.contains("ReferralCode")&&JSONresponse.contains("MobileCountryCode")&&JSONresponse.contains("TotalSpends")){
	System.out.println("Pass");
	logger.log(LogStatus.PASS,s.getCell(4,i).getContents(),logger.addScreenCapture("screenshots\\IsCustomer\\TC_001."+i+".png"));		    	
 
	
} 			    
		  else{
				    System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots\\IsCustomer\\TC_001 and TCC_003."+i+".png")); 
		  } 
			  report.endTest(logger);
			  report.flush();
	    }
    }

  }

