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
import jxl.write.biff.RowsExceededException;

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

public class GetValidRedemptionTransaction {
	public String data= "DemoAPIExcels\\CheckForEasyPointsRedemptionJSONdata.xls";
	public String Masterdata="DemoAPIExcels\\MasterDataDemo.xls";
	public String Reuse="DemoAPIExcels\\Reuse.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("D:\\LpaasWorkspace\\DailyScriptMirah\\Extent Reports\\TC04008.html");
	public ExtentTest logger =report.startTest("GetValidRedemptionTransaction");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d); 
	
	 @Test (priority=0)
	  public void checkRedemption() throws BiffException, IOException, RowsExceededException, WriteException, JSONException  {
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  for (int i = 1; i < 2; i++) {
		  driver.get("http://demo.erlpaas.com/apiui/");
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
		   a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
		   a.sendKeys(Keys.INSERT,s.getCell(1,i).getContents()).build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,s.getCell(2,i).getContents()).build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   f=new File("Excels\\DemoAPIExcels\\Reuse.xls");
	       wb=Workbook.getWorkbook(f);
	       s=wb.getSheet(0);
	       WritableWorkbook wbb=Workbook.createWorkbook(f,wb);
	       WritableSheet ws=wbb.getSheet(0);
	       String s4=s.getCell(0,i ).getContents();
	       long y = Long.parseLong(s4);
	       y=++y;
	       System.out.println(y);
	       s4= Long.toString(y) ;
	       Label result=new Label(0, i,s4 ); 
	       ws.addCell(result);
	       wbb.write();
	       wbb.close();
	       f=new File("Excels\\DemoAPIExcels\\Reuse.xls");
	       wb=Workbook.getWorkbook(f);
	       s=wb.getSheet(0);
		   a.sendKeys(Keys.ENTER,"\"TransactionCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\"" +s.getCell(0,i).getContents()+"\"").build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
	       
	       
	       f=new File(data);
	       wb=Workbook.getWorkbook(f);
	       s=wb.getSheet(0);

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
		   a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\"" +s.getCell(10,i).getContents()+"\"").build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   a.sendKeys(Keys.ENTER,"\"Activities\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\"\"").build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,s.getCell(12,i).getContents()).build().perform();
		   
		   a.sendKeys(Keys.ENTER,"}").build().perform();
		   a.sendKeys(Keys.ENTER,"}").build().perform();
		   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(src, new File("screenshots/Demo/CheckForEasyPointsRedemption."+i+".png"));
			  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);
			  JSONObject jsonObj = new JSONObject(JSONresponse);
			  String JSONtag = jsonObj.getString("ReturnMessage");
			  System.out.println(JSONtag);
			  if(JSONtag.contains("Success")){
				  System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demo/CheckForEasyPointsRedemption."+i+".png")); 
			  }else{
				  System.out.println("Fail");
			    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demo/CheckForEasyPointsRedemption."+i+".png")); 
			  }
			  try {
				  String Points= jsonObj.getString("EasyPoints");
				  System.out.println(Points);
				  File f=new File(Reuse);
		          Workbook wb=Workbook.getWorkbook(f);
		          Sheet s=wb.getSheet(0);
		          WritableWorkbook wbb1=Workbook.createWorkbook(f,wb);
		          WritableSheet ws1=wbb1.getSheet(0);
		          Label result2=new Label(7, 1, Points); 
		          ws1.addCell(result2);
		          wbb1.write();
		          wbb1.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			  report.endTest(logger);
				report.flush();
		   
	  }
	  }
	 
	 @Test (priority=1)
	  public void f() throws Exception {
		  f=new File(data);
		  wb = Workbook.getWorkbook(f);
		  	s=wb.getSheet(0);
		 /* System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		  driver=new ChromeDriver();*/
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  for (int i = 1; i < 2; i++) {
		  driver.get("http://demo.erlpaas.com/apiui/");
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
		   a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
		   a.sendKeys(Keys.INSERT,s.getCell(1,i).getContents()).build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		 //Takes TransactionCode from Reuse file
		   f=new File("Excels\\DemoAPIExcels\\Reuse.xls");
			wb=Workbook.getWorkbook(f);
			s=wb.getSheet(0);
		   a.sendKeys(Keys.ENTER,"\"TransactionCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\"" +s.getCell(0,i).getContents()+"\"").build().perform();
		 //Takes data from ConfirmEasyPointsRedemption file
				f=new File(data);
				wb = Workbook.getWorkbook(f);
				s=wb.getSheet(0); 
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   
		  
			/*try {
				//To open a input box
			     otp= JOptionPane.showInputDialog("Enter CODE Here");
			     Thread.sleep(1000);
			     Thread.sleep(2000); 
			} catch (Exception e) {
				// TODO: handle exception
			} 
		   a.sendKeys(Keys.ENTER,"\"RedemptionCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\""+otp+"\"").build().perform();
		   */
		   a.sendKeys(Keys.ENTER,"\"RedemptionCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\"\"").build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   
		   f=new File(Masterdata);
			wb = Workbook.getWorkbook(f);
			s=wb.getSheet(0); 
		   
		   a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
		   a.sendKeys(Keys.INSERT,"\"" +s.getCell(0,i).getContents()+"\"").build().perform();
		   a.sendKeys(Keys.INSERT,",").build().perform();
		   a.sendKeys(Keys.ENTER,"\"CountryCode\":").build().perform();
		   a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
		   
		   a.sendKeys(Keys.ENTER,"}").build().perform();
		   a.sendKeys(Keys.ENTER,"}").build().perform();
		   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(src, new File("screenshots/Demo/ConfirmEasyPointsRedemption."+i+".png"));
			  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);
			  JSONObject jsonObj = new JSONObject(JSONresponse);
			  String JSONtag = jsonObj.getString("ReturnMessage");
			  System.out.println(JSONtag);
			  if(JSONtag.contains("Success")){
				  System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Demo/ConfirmEasyPointsRedemption."+i+".png")); 
			  }else{
				  System.out.println("Fail");
			    	 logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Demo/ConfirmEasyPointsRedemption."+i+".png")); 
			  }
			  report.endTest(logger);
				report.flush();
	  }
	  }
	  
  @Test(priority=2)
  public void VerifyRedeemptionTransaction() throws BiffException, IOException {
	  f=new File(data);
	  wb = Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	 /* System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
	  driver=new ChromeDriver();*/
	  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	  
	  driver.get("http://demo.erlpaas.com//apiui//");
	  driver.manage().window().maximize();
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	  dropdown.selectByVisibleText("wsGetValidRedemptionTransaction");
	  f= new File(Masterdata);
	  wb= Workbook.getWorkbook(f);
	  s=wb.getSheet(0);
	  Actions a=new Actions(driver);
	  a.sendKeys(Keys.TAB,"{").build().perform();
	  a.sendKeys(Keys.ENTER,"\"Request\":").build().perform();
	  a.sendKeys(Keys.INSERT,"{").build().perform();
	  
	   a.sendKeys(Keys.ENTER,"\"MemberId\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(4,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"SecurityToken\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(1,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(2,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"BillDate\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   a.sendKeys(Keys.ENTER,"\"UserName\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(0,1).getContents()+"\"").build().perform();
	   a.sendKeys(Keys.INSERT,",").build().perform();
	   f=new File(Reuse);
       wb=Workbook.getWorkbook(f);
       s=wb.getSheet(0);
	   a.sendKeys(Keys.ENTER,"\"Points\":").build().perform();
	   a.sendKeys(Keys.INSERT,"\""+s.getCell(7,1).getContents()+"\"").build().perform();
	   
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   a.sendKeys(Keys.ENTER,"}").build().perform();
	   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("D:\\LpaasWorkspace\\DailyScriptMirah\\Screenshots\\TC04008."+1+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
		  System.out.println(JSONresponse);
		  JSONObject jsonObj = new JSONObject(JSONresponse);
		 // String JSONtag = jsonObj.getString("ReturnMessage");
		  try {
			  String retnmsg = jsonObj.getJSONObject("RedemptionResponse").getString("ReturnMessage");
			  System.out.println(retnmsg);
			  String validotp = jsonObj.getJSONObject("RedemptionResponse").getString("IsOTPValid");
			  System.out.println(validotp);
			  String OldBillID = jsonObj.getJSONObject("RedemptionResponse").getString("OldBillID");
			  System.out.println(OldBillID);
			  if(retnmsg.contains("Success")&&(OldBillID.contains(s.getCell(0,1).getContents()))){
				  System.out.println("Pass");
				  logger.log(LogStatus.PASS,"TC04038 Response is Success",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptMirah\\Screenshots\\TC04038."+1+".png"));
			  }else{
				  System.out.println("Fail");
				  logger.log(LogStatus.FAIL ,"TC04038 Failed",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptMirah\\Screenshots\\TC04038."+1+".png"));
			   }
		} catch (Exception e) {
			System.out.println(e);
		}
		  
		  try {
			  JSONArray arr = jsonObj.getJSONArray("RedemptionResponse");
		        for (int i = 0; i < arr.length(); i++) {
		        	
		        	String retnmsg = arr.getJSONObject(i).getString("ReturnMessage");
		            System.out.println(retnmsg);
		            String validotp = arr.getJSONObject(i).getString("IsOTPValid");
		            System.out.println(validotp);
		            String OldBillID = arr.getJSONObject(i).getString("OldBillID");
		            System.out.println(OldBillID);
		          	        
		  if(retnmsg.contains("Success")){
				  System.out.println("Pass");
				  logger.log(LogStatus.PASS,"TC04038 Response is Success",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptMirah\\Screenshots\\TC04038."+1+".png"));
			    	
			  }
		 /* System.out.println(JSONtag);
		  if(JSONtag.contains("Success")){
			  System.out.println("Pass");
		    	logger.log(LogStatus.PASS," Response is Success",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptMirah\\Screenshots\\TC04008."+1+".png")); 
		  }*/else{
			  System.out.println("Fail");
		    	 logger.log(LogStatus.FAIL ," Failed",logger.addScreenCapture("D:\\LpaasWorkspace\\DailyScriptMirah\\Screenshots\\TC04008."+1+".png")); 
		  }
		        }
		  } catch (Exception e) {
				System.out.println(e);
			}
			 
		  report.endTest(logger);
			report.flush();
	   
  }
  
		  
}
