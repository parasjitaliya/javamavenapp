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

public class CustomerProfile {
	
	// Need to change Security Token of this api only in CustomerProfileJSONdata file in order to cover all the test cases.
	// need to change URL
	public String data="Excels\\DemoAPIExcels\\CustomerProfileJSONdata.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 	
	public ExtentReports report=new ExtentReports("ExtentReports\\Soch\\CustomerProfile.html");
	public ExtentTest logger =report.startTest("CustomerProfile API");
	
  @Test
  public void f() throws IOException, BiffException {
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
		  dropdown.selectByVisibleText("wsCustomerProfile");
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
		 
		  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(3,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots/CustomerProfile."+i+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);
			 JSONObject jsonObj = new JSONObject(JSONresponse);
			 
			 
			 
if(JSONresponse.contains("ReturnCode")&&JSONresponse.contains("ReturnMessage")&&JSONresponse.contains("FirstName")&&JSONresponse.contains("LastName")&&JSONresponse.contains("Email")&&JSONresponse.contains("Mobile")&&JSONresponse.contains("ClientID")&&JSONresponse.contains("DateOfBirth")&&JSONresponse.contains("MembershipCardNumber")&&JSONresponse.contains("Address1")&&JSONresponse.contains("Address2")&&JSONresponse.contains("Gender")&&JSONresponse.contains("CustomerType")&&JSONresponse.contains("ReferralCode")&&JSONresponse.contains("ProfileStatus")){
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_001 and TC_008 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_001 and TC_008."+i+".png"));		    	
 
}  
else if (JSONresponse.contains("Security token verification failed")) {
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_003 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_003."+i+".png"));

}
else if (JSONresponse.contains("Invalid User Name")) {
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_004 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_004."+i+".png"));

}			 
else if (JSONresponse.contains("Invalid Member id")) {
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_005 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_005."+i+".png"));
	
}
else if (JSONresponse.contains("Invalid Country Code")) {
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_007 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_007."+i+".png"));
	
}
else if (JSONresponse.contains("T")) {
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_009 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_009."+i+".png"));
	
}
else if (JSONresponse.contains("T")) {
	System.out.println("Pass");
	logger.log(LogStatus.PASS,"TC_010 Response is Success",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_010."+i+".png"));
	
}
			    
		  /*else{
				    System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots\\CustomerProfile\\TC_001."+i+".png")); 
		  } */
			  report.endTest(logger);
			  report.flush();
	    }
    }

  }

