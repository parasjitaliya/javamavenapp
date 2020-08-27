package DailyScriptsAPIs_Lenovo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

public class UpdateGVStatus01 {
	public String data="RevdemoAPIExcels/UpdateGVStatusJSONdata.xls";
	public String MasterData="RevdemoAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Revdemo/UpdateGVStatus01.html");
	public ExtentTest logger =report.startTest("UpdateGVStatus");
  @Test
  public void f() {
  }
  @BeforeMethod
  public void beforeMethod() throws BiffException, IOException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver", "chromedriver");
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	  
	  for (int i = 1; i < 13; i++) {
	  driver.get("http://revdemo.erlpaas.com/apiui/");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsUpdateGVStatus");
	  Actions a=new Actions(driver);
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"MemberID\":").build().perform();
	  a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
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
      a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
      a.sendKeys(Keys.INSERT,s.getCell(2,1).getContents()).build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();  
	  //reading GV code from Reuse file
      File f=new File("RevdemoAPIExcels/Reuse.xls");
      Workbook wb=Workbook.getWorkbook(f);
      Sheet s=wb.getSheet(0);
      a.sendKeys(Keys.ENTER,"\"GVCode\":").build().perform();
      a.sendKeys(Keys.INSERT,"\"" +s.getCell(3,i).getContents()+"\"").build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
      //Reading data from UpdateGVstatus Excel
      f=new File(data);
      wb = Workbook.getWorkbook(f);
      s=wb.getSheet(0);
	   a.sendKeys(Keys.ENTER,"\"ToStatus\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"BillNo\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"AmountUsed\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(5,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"TotalPaidAmount\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(6,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(9,i).getContents()).build().perform();
       a.sendKeys(Keys.ENTER,"}").build().perform();
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	   
	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   FileUtils.copyFile(src, new File("screenshots/Revdemo/UpdateGVStatus01."+i+".png"));
	   String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	   System.out.println(JSONresponse);
		  /*JSONObject jsonObj = new JSONObject(JSONresponse);
		  String JSONtag = jsonObj.getString("ReturnMessage");
		  System.out.println(JSONtag);*/
	   
		  if(JSONresponse.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Revdemo/UpdateGVStatus01."+i+".png")); 
		  }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Revdemo/UpdateGVStatus01."+i+".png")); 
		  }
		  report.endTest(logger);
			report.flush();
    }
  }
}
