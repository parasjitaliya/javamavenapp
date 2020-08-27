package DailyScriptsAPIsMiddleast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

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

public class UseCoupon01 {
	public String data="DemoloyaltyAPIExcels/UseCouponJSONdata.xls";
	public String MasterData="DemoloyaltyAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public String otp;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Demoloyalty/31.html");
	public ExtentTest logger =report.startTest("UseCoupon");
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
	  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	  
	  for (int i = 1; i < 2; i++) {
	  driver.get("http://demome.erlpaas.com/apiui/");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsUseCoupon");
	  Actions a=new Actions(driver);
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  //Reading coupon code, request ID from Reuse file
      f=new File("DemoloyaltyAPIExcels/Reuse.xls");
      wb = Workbook.getWorkbook(f);
      s=wb.getSheet(0);
      a.sendKeys(Keys.ENTER,"\"RequestID\":").build().perform();
      a.sendKeys(Keys.INSERT,"\""+s.getCell(2,i).getContents()+"\"").build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
      a.sendKeys(Keys.ENTER,"\"CouponCode\":").build().perform();
      a.sendKeys(Keys.INSERT,"\""+s.getCell(1,i).getContents()+"\"").build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
      //Reading data from UseCoupon file
      f=new File(data);
      wb = Workbook.getWorkbook(f);
      s=wb.getSheet(0);
      a.sendKeys(Keys.ENTER,"\"BillNo\":").build().perform();
      a.sendKeys(Keys.INSERT,s.getCell(2,i).getContents()).build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
      a.sendKeys(Keys.ENTER,"\"Discount\":").build().perform();
      a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
  	try {
		 otp=JOptionPane.showInputDialog("Enter OTP");
	} catch (Exception e) {
		// TODO: handle exception
	}
      a.sendKeys(Keys.ENTER,"\"OTP\":").build().perform();
      a.sendKeys(Keys.INSERT,"\""+otp+"\"").build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
      a.sendKeys(Keys.ENTER,"\"TotalPaidAmount\":").build().perform();
      a.sendKeys(Keys.INSERT,s.getCell(5,i).getContents()).build().perform();
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
      a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
      a.sendKeys(Keys.INSERT,s.getCell(3,1).getContents()).build().perform();
      a.sendKeys(Keys.ENTER,"}").build().perform();
      a.sendKeys(Keys.ENTER,"}").build().perform();
      driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
      
      File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("screenshots/Demoloyalty/31."+i+".png"));
	  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	  System.out.println(JSONresponse);	  
	        /*JSONObject jsonObj = new JSONObject(JSONresponse);
	        String JSONtag = jsonObj.getString("ReturnMessage");
	        System.out.println(JSONtag);*/
	  
	  if(JSONresponse.contains("Success")){
		  System.out.println("Pass");
	    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demoloyalty/31."+i+".png")); 
	  }else{
		  System.out.println("Fail");
	    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demoloyalty/31."+i+".png")); 
	  }
	  report.endTest(logger);
		report.flush();

      }
  }
}
