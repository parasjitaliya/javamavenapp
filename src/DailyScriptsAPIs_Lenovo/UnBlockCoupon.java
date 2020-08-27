package DailyScriptsAPIs_Lenovo;

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

public class UnBlockCoupon {
	public String MasterData="RevdemoAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 	
	public ExtentReports report=new ExtentReports("ExtentReports/Revdemo/UnBlockCoupon.html");
	public ExtentTest logger =report.startTest("UnBlockCoupon");
	
	@Test
	public void UnblockCoupon() throws BiffException, IOException {
		
		      f=new File(MasterData);
		      wb = Workbook.getWorkbook(f);
		      s=wb.getSheet(0);
			  System.setProperty("webdriver.chrome.driver","chromedriver");
			  driver = new ChromeDriver();
	          driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	          for (int i= 1; i < 12; i++) {
	          driver.get("http://revdemo.erlpaas.com/apiui/");
	    	  driver.manage().window().maximize();
	    	
	    	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	    	  dropdown.selectByVisibleText("wsUnBlockCoupon");
	    	  Actions a=new Actions(driver);
	    	  a.sendKeys(Keys.TAB,"{").build().perform();
	    	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	    	  a.sendKeys(Keys.INSERT,"{").build().perform();
	    	  //reading data from reuse file
	    	  File f=new File("RevdemoAPIExcels/Reuse.xls");
	          Workbook wb=Workbook.getWorkbook(f);
	          Sheet s=wb.getSheet(0);
	          a.sendKeys(Keys.ENTER,"\"RequestID\":").build().perform();
	          a.sendKeys(Keys.INSERT,"\""+s.getCell(2,i).getContents()+"\"").build().perform();
	          a.sendKeys(Keys.INSERT,",").build().perform();
	          a.sendKeys(Keys.ENTER,"\"CouponCode\":").build().perform();
	          a.sendKeys(Keys.INSERT,"\""+s.getCell(1,i).getContents()+"\"").build().perform();
	          a.sendKeys(Keys.INSERT,",").build().perform();
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
			  FileUtils.copyFile(src, new File("screenshots/Revdemo/UnBlockCoupon."+i+".png"));
			  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);

			  if(JSONresponse.contains("Success")){
				  System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Revdemo/UnBlockCoupon."+i+".png")); 
 
			  }else{
				  System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Revdemo/UnBlockCoupon."+i+".png")); 

			  }
			  report.endTest(logger);
			  report.flush();
		} 
		
	}
}
