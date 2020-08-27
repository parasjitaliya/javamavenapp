package DailyScriptsAPIsMiddleast;

import java.io.File;
import java.io.IOException;
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

public class UpdateMemberProfile {
	public String data="DemoloyaltyAPIExcels/UpdateMemberProfile.xls";
	public String MasterData="DemoloyaltyAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/42.html");
	public ExtentTest logger =report.startTest("UpdateMemberprofile");
  @Test
  public void UpdateMemberprofile() throws BiffException, IOException {
	  
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);	
	  System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	  
	  for (int i = 1; i < s.getRows(); i++) { 
	  driver.get("http://revdemo.erlpaas.com/apiui/");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsUpdateMemberProfile");
	  Actions a=new Actions(driver);
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
      //Reading data from RegisterAccount Excel
      f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  a.sendKeys(Keys.ENTER,"\"FirstName\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(1,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"LastName\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(2,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"DateOfBirth\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"PinCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"NumberOfChildren\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(5,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EmailId\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(6,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"MobileNo\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(7,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyPin\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(8,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Gender\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(10,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"MemberShipCardNumber\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(11,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ServicePersonNo\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(13,1).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Address1\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(14,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Address2\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(15,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"AssignMembershipCard\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(16,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"CCIPolicyNo\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(17,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyPinTypeId\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(18,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ReferralCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(19,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ChildName\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(20,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ChildGender\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(21,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ChildDOB\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(22,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"AnniversaryDate\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(23,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ExpectedDateDelivery\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(24,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Twin\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(25,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(26,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TierName\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(27,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Remarks\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(28,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Migratedvisit\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(29,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"MigratedDate\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(30,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Migratedspent\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(31,i).getContents()).build().perform();	 
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	  
	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   FileUtils.copyFile(src, new File("screenshots/Demoloyalty/42."+i+".png"));
	   String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	   System.out.println(JSONresponse);

		  if(JSONresponse.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/42."+i+".png")); 
		  }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/42."+i+".png")); 
		  }
		  report.endTest(logger);
			report.flush();
    }
  }
}
