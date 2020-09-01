package DailyScriptsAPIs_Bata;

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

public class ActivityConfirmRedemption {
	public String data="DemoAPIExcels\\ActivityRedemptionChangesJSONdata.xls";
	public String MasterData="DemoAPIExcels\\MasterDataDemo.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	String otp;
	public ExtentReports report=new ExtentReports("D:\\LpaasWorkspace\\DailyScriptBataProd\\Extent reports\\ActivityRedemptionChangesJSONdata.html");
	public ExtentTest logger =report.startTest("ConfirmEasyPointsRedemption");
  @Test
  public void f() {
  }
  @BeforeMethod
  public void beforeMethod() throws IOException, BiffException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  System.setProperty("webdriver.chrome.driver",utils.Constants.chromeDriverPath);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	  for (int i = 26; i < s.getRows(); i++) {
	  driver.get("http:/demo.erlpaas.com/apiui/");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsConfirmEasyPointsRedemption");
	  Actions a=new Actions(driver);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"EasyId\":").build().perform();
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
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(1,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\""+s.getCell(3,1).getContents()+"\"").build().perform();
	  a.sendKeys(Keys.INSERT,",").build().perform();
	  //Takes TransactionCode from Reuse file
	  f=new File(data);
      wb=Workbook.getWorkbook(f);
      s=wb.getSheet(0);
      a.sendKeys(Keys.ENTER,"\"TransactionCode\":").build().perform();
      a.sendKeys(Keys.INSERT,"\"" +s.getCell(3,i).getContents()+"\"").build().perform();
      a.sendKeys(Keys.INSERT,",").build().perform();
	  
	 /* try {
			//To open a input box
		     otp= JOptionPane.showInputDialog("Enter CODE Here");
		     Thread.sleep(1000);
		     Thread.sleep(2000); 
		} catch (Exception e) {
			// TODO: handle exception
		} 
	   a.sendKeys(Keys.ENTER,"\"RedemptionCode\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+otp+"\"").build().perform();*/
	  
	  //Takes data from ConfirmEasyPointsRedemption file
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0); 
	  a.sendKeys(Keys.ENTER,"\"RedemptionCode\":").build().perform();
	  a.sendKeys(Keys.INSERT,"\"\"").build().perform();
	  	 
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  a.sendKeys(Keys.ENTER,"}").build().perform();
	  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("D:\\LpaasWorkspace\\DailyScriptBataProd\\Screenshots\\ActivityConfirmRedemption."+i+".png"));
	  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
	  System.out.println(JSONresponse);
	  /*JSONObject jsonObj = new JSONObject(JSONresponse);
	  String JSONtag = jsonObj.getString("ReturnMessage");
	  System.out.println(JSONtag);*/
	  if(JSONresponse.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptBataProd\\Screenshots\\ActivityConfirmRedemption."+i+".png")); 
	  }else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptBataProd\\Screenshots\\ActivityConfirmRedemption."+i+".png")); 
	  }
		  report.endTest(logger);
			report.flush();
    }
  }
}
