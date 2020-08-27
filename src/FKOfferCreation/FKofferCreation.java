package FKOfferCreation;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FKofferCreation extends FKReuse {
	public static String OfferCreation = "src\\FKOfferCreation\\offer.xls";
	public Workbook wb;
	public Sheet s;
    File f;

	@Test
	public void OfferCreation() throws InterruptedException, BiffException, IOException
	{
	    f = new File(OfferCreation);
	    wb = Workbook.getWorkbook(f);
        s=wb.getSheet(0);
        Set<String> artifactoryLoggers = new HashSet<>(Arrays.asList("org.apache.http", "groovyx.net.http"));
        for(String log:artifactoryLoggers) {
//            ch.qos.logback.classic.Logger artLogger = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(log);
//            artLogger.setLevel(ch.qos.logback.classic.Level.INFO);
//            artLogger.setAdditive(false);
        }
		driver.get("http://bataprod.erstaging.com");
		
		System.out.println(s.getCell(0,1).getContents());
		driver.findElement(By.xpath("//*[@name='txtMerchantID']")).sendKeys(s.getCell(0,1).getContents());
		driver.findElement(By.xpath("//*[@name='txtPassword']")).sendKeys(s.getCell(1,1).getContents());
		driver.findElement(By.xpath("//*[text()='Login']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ucStorePopup_a2']")).click();
		driver.findElement(By.xpath("//*[@id='ctl00_rptParentModule_ctl01_ancChildRepeater']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Exchange Offer']")).click();
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lbtnAddOffer']")).click();
		Thread.sleep(1000);
		
		if(s.getCell(2, 1).getContents().equalsIgnoreCase("Debit"))
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkPublished']")).click();
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_selDebitWallet']")).click();
			driver.findElement(By.xpath("(//*[text()='BataProd'])[2]")).click();
			driver.findElement(By.xpath("(//*[text()='Close'])[1]"));
			Thread.sleep(5000);
			Select ExpiryDuration=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlExpiryDuration']")));
			ExpiryDuration.selectByVisibleText("Easy Rewardz");
			Thread.sleep(5000);
			CalenderToday();
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtStartDate']")).sendKeys(newDateC);
			CalenderTom();
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtEndDate']")).sendKeys(newDateF);
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlTier']")).click();
			driver.findElement(By.xpath("//*[text()='Silver']")).click();
	        driver.findElement(By.xpath("(//*[text()='Close'])[3]")).click();  	
	        
	        WebElement msg = driver.findElement(By.xpath("(//*[@class='success_message_pop'])[4]"));
	        String success = msg.getText();
	           if(success.contains("Exchange Offer updated successfully."))
	            {
	        	      System.err.println("pass"); 
    	              PassScreenshot(driver, "Debit offer", "Debit offer is created");
	            }else {
	            	System.out.println("Fail");
		              FailScreenshot(driver, "Debit offer", "Debit offer is not created");
	            }
	        
		}
		else if(s.getCell(2, 1).getContents().equalsIgnoreCase("Credit"))
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkPublished']")).click();
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_rbtnOfferList_1']")).click();
			Thread.sleep(5000);
			Select Wallet=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlPartnerWallet']")));
			Wallet.selectByVisibleText("BataProd");
			Thread.sleep(5000);
			Select ExpiryDuration=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlExpiryDuration']")));
			ExpiryDuration.selectByVisibleText("Easy Rewardz");
			Thread.sleep(5000);
			CalenderToday();
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtStartDate']")).sendKeys(newDateC);
			CalenderTom();
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtEndDate']")).sendKeys(newDateF);
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlTier']")).click();
			driver.findElement(By.xpath("//*[text()='Silver']")).click();
	        driver.findElement(By.xpath("(//*[text()='Close'])[3]")).click();  	
	        
	        WebElement msg = driver.findElement(By.xpath("(//*[@class='success_message_pop'])[4]"));
	        String success = msg.getText();
	           if(success.contains("Exchange Offer updated successfully."))
	            {
	        	      System.err.println("pass"); 
    	              PassScreenshot(driver, "Credit offer", "Credit offer is created");
	            }else {
	            	System.out.println("Fail");
		              FailScreenshot(driver, "Credit offer", "Credit offer is not created");
	            }

		}
		else if(s.getCell(2, 1).getContents().equalsIgnoreCase("Reversal Debit"))
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_rbtnOfferList_2']")).click();
			Thread.sleep(5000);
			Select Reversaloffer=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlReversalOffer']")));
			Reversaloffer.selectByVisibleText("ExchangeDebit1");
			Thread.sleep(5000);
			 
	         WebElement msg = driver.findElement(By.xpath("(//*[@class='success_message_pop'])[4]"));
		        String success = msg.getText();
		           if(success.contains("Exchange Offer updated successfully."))
		            {
		        	      System.err.println("pass"); 
	    	              PassScreenshot(driver, "ReversalDebit offer", "Reversal Debit offer is created");
		            }else {
		            	System.out.println("Fail");
			              FailScreenshot(driver, "ReversalDebit offer", "Reversal Debit offer is not created");
		            }

		}
		else
		{
			driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_rbtnOfferList_3']")).click();
			Thread.sleep(5000);
			Select Reversaloffer=new Select(driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlReversalOffer']")));
			Reversaloffer.selectByVisibleText("CreditOffer2");
			Thread.sleep(5000);
			
	         WebElement msg = driver.findElement(By.xpath("(//*[@class='success_message_pop'])[4]"));
		        String success = msg.getText();
		           if(success.contains("Exchange Offer updated successfully."))
		            {
		        	      System.err.println("pass"); 
	    	              PassScreenshot(driver, "ReversalCredit offer", "Reversal Credit offer is created");
		            }else {
		            	System.out.println("Fail");
			              FailScreenshot(driver, "ReversalCredit offer", "Reversal Credit offer is not created");
		            }

		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtOfferName']")).sendKeys(s.getCell(3,1).getContents());
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtOfferCode']")).sendKeys(s.getCell(4,1).getContents());
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lbtnSave1']")).click();
	}
	

}
