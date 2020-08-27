package GVOfferCreation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class GVCreation  {
	public String str1;
	public String baseUrl;
	public String u;
		public String nodeURL;
		public String nodeURl1;
		public WebDriver driver;
		public FileInputStream fi;
		public Workbook wb;
		public Sheet s;
		public Sheet s1;
		public static String str;
 
  @Test(priority = 0)
  public void general() throws BiffException, IOException, Exception {
	  
			
	  System.setProperty("webdriver.chrome.driver",utils.Constants.chromeDriverPath);
		driver=new ChromeDriver();
		fi=new FileInputStream("src\\GVOfferCreation\\GV offer creation.xls");
		wb=Workbook.getWorkbook(fi);
		s=wb.getSheet(0);
		
		 driver.get("http://mirah.erzqa.com/");
		 driver.findElement(By.id("txtMerchantID")).sendKeys(s.getCell(1,1).getContents());
			driver.findElement(By.id("txtPassword")).sendKeys(s.getCell(1,2).getContents());
			driver.findElement(By.xpath(".//*[@id='lnkbtrnLogin']/span")).click();
			
			 Thread.sleep(3000);
			 driver.manage().window().maximize();
		   	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ucStorePopup_a2']")).click();;
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[text()='Manage'])[1]")).click();
		Thread.sleep(2000);
		//Click on Gift Vouchar
		driver.findElement(By.xpath("(//*[text()='Gift Voucher'])[1]")).click();
		Thread.sleep(2000);
		//add GV offer
		driver.findElement(By.xpath("(//*[text()='Add GV Offer'])[1]")).click();
		Thread.sleep(2000);
		//publish check box
		if(s.getCell(1,3).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_chkPublish'])")).click();
		}
		//Merge GV check box
		if(s.getCell(1,4).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_chkMergeGV'])")).click();
		}
		//3rd party check box
		if(s.getCell(1,5).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_ChkThirdparty'])")).click();
		}
		//Generate GV On Request check box
		if(s.getCell(1,6).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_chkGenerateGVOnRequest'])")).click();
		}
		//Allow Accrual check box
		if(s.getCell(1,7).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_chkIsAllowAccrual'])")).click();
		}
		//offer name
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtGVCouponOfferName'])")).sendKeys(s.getCell(1,8).getContents());
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtGVCouponOfferCode'])")).sendKeys(s.getCell(1,9).getContents());
		Thread.sleep(2000);
		//select issue type
		Select IssueType= new Select(driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_ddlIssueType'])"))); 
		IssueType.selectByVisibleText(s.getCell(1,10).getContents());
		//select validity type
		Thread.sleep(3000);
		Select ValidityType= new Select(driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_ddlValidityType'])"))); 
		ValidityType.selectByVisibleText(s.getCell(1,11).getContents());
		Thread.sleep(5000);
		//GV prifix code
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtCouponCode'])")).sendKeys(s.getCell(1,12).getContents());
		Thread.sleep(4000);
		//start date
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtStartDate'])")).sendKeys(s.getCell(1,13).getContents());
		//end date
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtEndDate'])")).sendKeys(s.getCell(1,14).getContents());
		Thread.sleep(3000);
		//validity days
		try {
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtValidityDays'])")).sendKeys(s.getCell(1,15).getContents());
		}
		catch (Exception e) {}
		//Gv code length
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtReferralCodeLength'])")).sendKeys(s.getCell(1,16).getContents());
		//Gv use
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtCouponUse'])")).sendKeys(s.getCell(1,17).getContents());
		//denomination type
		Select denominationtype= new Select(driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_ddlDenominationType'])"))); 
		denominationtype.selectByVisibleText(s.getCell(1,18).getContents());
		Thread.sleep(4000);
		//enter value
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtAmount'])")).sendKeys(s.getCell(1,19).getContents());
		//value type
		Select Valuetype= new Select(driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_ddlAmountType'])"))); 
		Valuetype.selectByVisibleText(s.getCell(1,20).getContents());
		Thread.sleep(4000);
		//discount value
		try {
		driver.findElement(By.xpath("(//*[@id='ctl00_ContentPlaceHolder1_txtAmountValue'])")).sendKeys(s.getCell(1,21).getContents());
		}catch (Exception e) {}
		//multi use check box
		if(s.getCell(1,22).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIsMultiUse']")).click();
		}
		Thread.sleep(2000);
		//maximum gv issueance per day
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtMaxCouponIssuance']")).sendKeys(s.getCell(1,23).getContents());
		//narration
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtNarration']")).sendKeys(s.getCell(1,24).getContents());
		Thread.sleep(4000);
		//click on save
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lbtnSaveTop']")).click();
		Thread.sleep(5000);
  }
  //@Test(priority = 1)
  public void filters() throws BiffException, IOException, Exception {
	  driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lbtnFilters']")).click();
		Thread.sleep(5000); 
	//enrollment check box
		if(s.getCell(1,25).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIsCouponOfferEnrollment']")).click();
		}
		//profile update
		if(s.getCell(1,26).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIsProfileUpdate']")).click();
		}
		//first transaction
		if(s.getCell(1,27).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkFirstTransaction']")).click();
		}
		//feedback
		if(s.getCell(1,28).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ChkIsFeedback']")).click();
		}
		//Offers
		if(s.getCell(1,29).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIsOffers']")).click();
		}
		//share
		if(s.getCell(1,30).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIsShare']")).click();
		}
		//SKU Rules
		if(s.getCell(1,31).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIsSKURules']")).click();
		}
		//Issue To AnyOne
		if(s.getCell(1,32).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIssueToAnyOne']")).click();
		}
		//Issue To Any store
		if(s.getCell(1,33).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkIssuedAnyStore']")).click();
		}
		Select Member= new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlMemberActivity']"))); 
		Member.selectByVisibleText(s.getCell(1,34).getContents());
		Thread.sleep(4000);
		//select brands
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_selBrand']")).click();
		Thread.sleep(2000);
		//select all brands
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkSelectAllBrands']")).click();
		Thread.sleep(2000);
		//select stores
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_selStores']")).click();
		Thread.sleep(2000);
		//select all stores
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkSelectAllStoress']")).click();
		Thread.sleep(2000);
		//Redeemed At Any Store
		if(s.getCell(1,35).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkRedeemedAnyStore']")).click();
		}
		//Match Store
		if(s.getCell(1,36).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkMatchstore']")).click();
		}
		//Required pin
		if(s.getCell(1,37).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkRequirePIN']")).click();
		}
		//Required otp
		if(s.getCell(1,38).getContents().equalsIgnoreCase("ok")) 
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkRequiredOTP']")).click();
		}
		Thread.sleep(3000);
		//click on save
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lnkSaveFilterTop']")).click();
  }
}	
		
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	