package DailyScriptsAPIsMiddleast;

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
import org.testng.annotations.BeforeMethod;
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

public class IssueCoupon01{
	public String data="DemoloyaltyAPIExcels/IssueCouponJSONdata.xls";
	public String MasterData="DemoloyaltyAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/18.html");
	public ExtentTest logger =report.startTest("IssueCoupon");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
  @Test
  public void f() {
  }

  @BeforeMethod
  public void beforeMethod() throws BiffException, IOException, JSONException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS); 
	  
	  for (int i = 1; i < s.getRows(); i++) {
	  driver.get("http://demome.erlpaas.com/apiui/");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsIssueCoupon");
	  Actions a=new Actions(driver);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"MemberID\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Date\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"CouponOfferCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
      a.sendKeys(Keys.ENTER,"\"ThirdParty\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"SegmentCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(5,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TierCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(6,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"CategoryCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(7,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  //Reading data from MasterDta Excel
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
	  //Reading data from IssueCoupon Excel
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0); 
	  a.sendKeys(Keys.ENTER,"\"Communicate\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(10,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TotalPaidAmount\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(11,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"TransactionId\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(12,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"OfferTransactionID\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(13,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"OfferCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(14,i).getContents()).build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	  
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("screenshots/Demoloyalty/18."+i+".png"));
	  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	  System.out.println(JSONresponse);
		  JSONObject jsonObj = new JSONObject(JSONresponse);
		  try {
              String CouponCode = jsonObj.getString("CouponCode");
              System.out.println(CouponCode);
              
                  File f=new File("DemoloyaltyAPIExcels/Reuse.xls");
                  Workbook wb=Workbook.getWorkbook(f);
                  Sheet s=wb.getSheet(0);
                  WritableWorkbook wbb1=Workbook.createWorkbook(f,wb);
                  WritableSheet ws1=wbb1.getSheet(0);
                  Label result=new Label(1, i,CouponCode ); 
                  ws1.addCell(result);
                  wbb1.write();
                  wbb1.close();
         } catch (Exception e) {
            // TODO: handle exception
            }
                /*String JSONtag = jsonObj.getString("ReturnMessage");
		        System.out.println(JSONtag);*/
		  if(JSONresponse.contains("Success")){
	 		  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/18."+i+".png")); 
		  }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/18."+i+".png")); 
		  }
		  report.endTest(logger);
			report.flush();
    }
  }
}