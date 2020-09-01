package DailyScriptsAPIs_Bata;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
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

public class BillRewardDetailsAPI {
	//This package script can be copied to any environment package like ERZQAWorkFlow, UATWorkFlow, Preprod and Production work flow and follow the below steps.
	/* Step 1-> Check Master Data excel file of particular instance like soch and update Security token, store code and member id if required.
	 * Step 2-> Change url of particular instance
	 * Step 3-> Call EasyPointsAccrualEOSS api and call This script i.e BillRewardDetails api.
	
	*/
	
	public String MasterData="DemoAPIExcels\\MasterDataDemo.xls";
	public String Reuse="DemoAPIExcels\\Reuse.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag; 	
	public ExtentReports report=new ExtentReports("ExtentReports\\BillRewardDetails\\BillRewardDetails1.html");
	public ExtentTest logger =report.startTest("wsBillRewardDetails");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY");
	Date d=new Date();
	String time=df.format(d);
	DateFormat df1=new SimpleDateFormat("dd/MMM/YYYY_hhmmss");
	Date d1=new Date();
	String time1=df.format(d);
	
  @Test
  public void f() throws IOException, BiffException {
	      //Reading SecurityToken,store code and user name From SecurityToken Excel
		  f=new File(MasterData);
		  wb = Workbook.getWorkbook(f);
		  s=wb.getSheet(0);
		  System.setProperty("webdriver.chrome.driver",utils.Constants.chromeDriverPath);
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
		  
		  for (int i = 1; i < 2 ; i++) {
			  //url
		  driver.get("http://demo.erlpaas.com//apiui//");
		  driver.manage().window().maximize();
		  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
		  dropdown.selectByVisibleText("wsBillRewardDetails");
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
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(1,1).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"StoreCode\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(2,1).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"TransactionDate\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+time+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  a.sendKeys(Keys.ENTER,"\"MemberId\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(4,1).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.INSERT,",").build().perform();
		  
		  //Reading bill id from Reuse Excel
	      f=new File(Reuse);
	      wb=Workbook.getWorkbook(f);
	      s=wb.getSheet(0);
		  a.sendKeys(Keys.ENTER,"\"BillId\":").build().perform();
		  a.sendKeys(Keys.INSERT,"\""+s.getCell(0,i).getContents()+"\"").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  a.sendKeys(Keys.ENTER,"}").build().perform();
		  driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
		   
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("screenshots\\BillRewardDetails\\BillRewardDetails_TC_021."+time1+".png"));
		  String JSONresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
			  System.out.println(JSONresponse);
			  JSONObject jsonObj = new JSONObject(JSONresponse);
			/*  String JSONtag = jsonObj.getString("Res");
			  System.out.println(JSONtag);*/
			
				if (JSONresponse.contains("Success")) {
					 System.out.println("Pass");
				    	logger.log(LogStatus.PASS,"TC_01 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_1."+time1+".png"));
				    	
				    	try {
							 if (JSONresponse.contains("ArrayOfPointsIssued")) {
								 Thread.sleep(3000);
								  f=new File(Reuse);
									wb=Workbook.getWorkbook(f);
									s=wb.getSheet(0);
								  JSONArray arr = jsonObj.getJSONArray("ArrayOfPointsIssued");
							       for (int j = 0; i < arr.length(); i++) {
							    	   if (arr.length()==0) {
							    		   System.out.println("No Points issued");
									}
							            String PointsIssued = arr.getJSONObject(j).getString("PointsIssued");
							            System.out.println(PointsIssued);
							            
							  			  System.out.println("Pass");
							  		    	logger.log(LogStatus.PASS,"TC_02 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_2_TC_02."+time1+".png")); 
							  		  
							}
							  }
						} catch (Exception e) {
							System.out.println("No points issued");
						}
						 try {
							  if (JSONresponse.contains("ArrayOfPointsUsed")) {
								  Thread.sleep(3000);
									f=new File(Reuse);
									wb=Workbook.getWorkbook(f);
									s=wb.getSheet(0);
								  JSONArray arr = jsonObj.getJSONArray("ArrayOfPointsUsed");
							       for (int j = 0; i < arr.length(); i++) {
							    	   if (arr.length()==0) {
							    		   System.out.println("No Points Used");
									}
							            String PointsUsed = arr.getJSONObject(j).getString("PointsUsed");
							            System.out.println(PointsUsed);
							            
							  			  System.out.println("Pass");
							  		    	logger.log(LogStatus.PASS,"TC_03 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_TC_03."+time1+".png")); 
								}
							}
						} catch (Exception e) {
							System.out.println("No points used");
						}
						try {
							 if (JSONresponse.contains("ArrayOfCouponIssued")) {
								 Thread.sleep(3000);
								  f=new File(Reuse);
									wb=Workbook.getWorkbook(f);
									s=wb.getSheet(0);
								  JSONArray arr = jsonObj.getJSONArray("ArrayOfCouponIssued");
							       for (int j = 0; i < arr.length(); i++) {
							    	   if (arr.length()==0) {
							    		   System.out.println("No Coupon issued");
									}
							            String CouponCode = arr.getJSONObject(j).getString("CouponCode");
							            System.out.println(CouponCode);
							            if(CouponCode.contains("s.getCell(1,i).getContents()")) {
							  			  System.out.println("Pass");
							  		    	logger.log(LogStatus.PASS,"TC_04 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_TC_04."+time1+".png")); 
							  		  }
								}
							  }
						} catch (Exception e) {
							System.out.println("No Coupon issued");
						}
						 try {
							 if (JSONresponse.contains("ArrayOfCouponUsed")) {
								 Thread.sleep(3000);
								  f=new File(Reuse);
									wb=Workbook.getWorkbook(f);
									s=wb.getSheet(0);
								  JSONArray arr = jsonObj.getJSONArray("CouponGVDetails");
							       for (int j = 0; i < arr.length(); i++) {
							    	   System.out.println(arr.length());
							    	   if (arr.length()==0) {
							    		   System.out.println("No Coupon Used");
									}
							            String CouponCode = arr.getJSONObject(j).getString("CouponCode");
							            System.out.println(CouponCode);
							            if(CouponCode.contains("s.getCell(1,i).getContents()")) {
							  			  System.out.println("Pass");
							  		    	logger.log(LogStatus.PASS,"TC_05 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_TC_05."+time1+".png")); 
							  		  }
								}
							  }
						} catch (Exception e) {
							System.out.println("No Coupon Used");
						}
						 try {
							  if (JSONresponse.contains("ArrayofGVIssued")) {
								  Thread.sleep(3000);
								  f=new File(Reuse);
									wb=Workbook.getWorkbook(f);
									s=wb.getSheet(0);
								  JSONArray arr = jsonObj.getJSONArray("IssuedGVDetails");
							       for (int j = 0; i < arr.length(); i++) {
							            String gvcode = arr.getJSONObject(j).getString("GVCode");
							            System.out.println(gvcode);
							            if(gvcode.contains("s.getCell(3,i).getContents()")) {
							  			  System.out.println("Pass");
							  		    	logger.log(LogStatus.PASS,"TC_06 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_TC_06."+time1+".png")); 
							  		  }
								}
							  }
						} catch (Exception e) {
							System.out.println("No GV issued");
						}
						 
						  try {
							  if (JSONresponse.contains("ArrayofGVUsed")) {
								  Thread.sleep(3000);
								  f=new File(Reuse);
									wb=Workbook.getWorkbook(f);
									s=wb.getSheet(0);
								  JSONArray arr = jsonObj.getJSONArray("UsedGVDetails");
							       for (int j = 0; i < arr.length(); i++) {
							    	   if (arr.length()==0) {
							    		   System.out.println("No GV Used");
									}
							            String gvcode = arr.getJSONObject(j).getString("GVCode");
							            System.out.println(gvcode);
							            if(gvcode.contains("s.getCell(3,i).getContents()")) {
							  			  System.out.println("Pass");
							  		    	logger.log(LogStatus.PASS,"TC_07 Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails_TC_07."+time1+".png")); 
							  		  }
								}
							  }
						} catch (Exception e) {
							System.out.println("No GV Used");
						}
						 
				}
			 
		  /*if(JSONresponse.contains("Success")){
				    System.out.println("Pass");
			    	logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails."+time+".png"));
			    	
		  }*/
		  else{
				    System.out.println("Fail");
			    	logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots\\BillRewardDetails\\BillRewardDetails."+time1+".png")); 
		  } 
			  report.endTest(logger);
			  report.flush();
	    }
    }

  }

