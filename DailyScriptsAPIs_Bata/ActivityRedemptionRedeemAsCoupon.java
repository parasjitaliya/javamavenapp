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
import jxl.write.WriteException;
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

public class ActivityRedemptionRedeemAsCoupon {
	//Need to change data path and URL w.r.t instance.
	/*THis change is In case of Activity Redemption Offer and Redeema as Coupon is selected then Generate on Request and Non unique coupon
	 * could issue coupon from respecitve coupon offer. 
	 * Now Redeem as Coupon will work for Unique,Non - Unique and Generate on Request coupon offers.*/ 
	//Multiple activities are not allowed in case of Redeem as coupon is selected in Activity based Redemption offer.
	//This multiple activities case will cover in "ActivityRedemptionEasyPoints.java script.  other cases will cover in this script"
	
	//if in single activity quantity is passed greater than one then coupons will issue as per the quantity.
	//If Coupon use is less than quantity passed then error will display as "No coupon codes are available". This should be checked carefully.
	
	
	
	public String data="DemoAPIExcels\\ActivityRedemptionChangesJSONdata.xls";
	public String MasterData="DemoAPIExcels\\MasterDataDemo.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("D:\\LpaasWorkspace\\DailyScriptBataProd\\Extent reports\\ActivityRedemtption.html");
	public ExtentTest logger =report.startTest("CheckForEasyPointsRedemption");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d); 
  @Test
  public void f() {
  }
  @BeforeMethod
  public void beforeMethod() throws BiffException, IOException, Exception, WriteException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	  for (int i = 22; i <= 25; i++) {
	  driver.get("http://demo.erlpaas.com//apiui//");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsCheckForEasyPointsRedemption");
	  Actions a=new Actions(driver);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
   	  //Reading data From SecurityToken Excel
	  f=new File(MasterData);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\"" +s.getCell(0,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(1,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(3,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	 
     
      //Reading data from CheckforEasypointRedemption Excel
      f=new File(data);
      wb=Workbook.getWorkbook(f);
      s=wb.getSheet(0);
      a.sendKeys(Keys.ENTER,"\"TransactionCode\":").build().perform();
      a.sendKeys(Keys.INSERT,"\"" +s.getCell(3,i).getContents()+"\"").build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
      a.sendKeys(Keys.ENTER,"\"RedemptionDate\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Amount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(5,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"RedemptionType\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(6,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyPoints\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(7,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ActivityCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(8,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TransactionDescription\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(9,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Activities\":").build().perform();
	  a.sendKeys(Keys.ENTER,"{").build().perform();
	  
	  a.sendKeys(Keys.ENTER,"\"Activity\":").build().perform();
	  a.sendKeys(Keys.ENTER,"[").build().perform();
	  
	  a.sendKeys(Keys.ENTER,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ActivityName\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(11,i).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Quantity\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(12,i).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"ActivityCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(13,i).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();

	  a.sendKeys(Keys.ENTER,"]").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("D:\\LpaasWorkspace\\DailyScriptBataProd\\Screenshots\\ActivityRedemtption."+i+".png"));
	  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	  /*System.out.println(JSONresponse);
	  JSONObject jsonObj = new JSONObject(JSONresponse);
	  String JSONtag = jsonObj.getString("ReturnMessage");*/
	  // System.out.println(JSONtag);
	  if(JSONresponse.contains(s.getCell(18, i).getContents())){
			  System.out.println("Pass");
		      logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptBataProd\\Screenshots\\ActivityRedemtption."+i+".png")); 
	  }else{
			  System.out.println("Fail");
		      logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptBataProd\\Screenshots\\ActivityRedemtption."+i+".png")); 
	  }
		  report.endTest(logger);
		  report.flush();
	   
    }
  }
}
