package DailyScriptsAPIs_Revdemo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SaveSKUBillDetails {
	public String data="RevdemoAPIExcels/SaveSKUBillDetailsXMLdata.xls";
	public String MasterData="RevdemoAPIExcels/MasterData.xls";
	public WebDriver driver;
	public Workbook wb;
	public Sheet s;
	File f;
	String JSONtag;
	public ExtentReports report=new ExtentReports("ExtentReports/Revdemo/20.html");
	public ExtentTest logger =report.startTest("SaveSKUBillDetails");
	DateFormat df=new SimpleDateFormat("dd MMM YYYY hh:mm:ss.F");
	Date d=new Date();
	String time=df.format(d); 
	
  @Test
  public void SaveSKUBillDetail() throws BiffException, IOException, ParserConfigurationException, SAXException, InterruptedException {
	   f=new File(data);
	   wb = Workbook.getWorkbook(f);
	   s=wb.getSheet(0);
	   System.setProperty("webdriver.chrome.driver", utils.Constants.chromeDriverPath);
	   driver=new ChromeDriver();
	   driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS); 
	   
	   for (int i = 1; i < s.getRows(); i++) {
	   driver.get("http://revdemo.erlpaas.com/apiui/");
	   driver.manage().window().maximize();
	   Thread.sleep(2000);   
	   Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='ddlSelectMethods']")));
	   dropdown.selectByVisibleText("wsSaveSKUBillDetails");
	   Actions a=new Actions(driver);
	   a.sendKeys(Keys.TAB,"<Request> ").build().perform();
	   a.sendKeys(Keys.ENTER,"<SecurityToken>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(0,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</SecurityToken>").build().perform();
	   a.sendKeys(Keys.ENTER,"<StoreCode>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(1,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</StoreCode>").build().perform();
	   a.sendKeys(Keys.ENTER,"<TransactionDate>").build().perform();
	   a.sendKeys(Keys.INSERT,time).build().perform();
	   a.sendKeys(Keys.INSERT,"</TransactionDate>").build().perform();
	   a.sendKeys(Keys.ENTER,"<BillNo>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(3,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</BillNo>").build().perform();
	   a.sendKeys(Keys.ENTER,"<EasyId>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(4,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</EasyId>").build().perform();
	   a.sendKeys(Keys.ENTER,"<UserName>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(5,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</UserName>").build().perform();
	   a.sendKeys(Keys.ENTER,"<Channel>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(6,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</Channel>").build().perform();
	   a.sendKeys(Keys.ENTER,"<CustomerType>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(7,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</CustomerType>").build().perform();
	   a.sendKeys(Keys.ENTER,"<BillValue>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(8,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</BillValue>").build().perform();
	   a.sendKeys(Keys.ENTER,"<PointsRedeemed>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(9,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</PointsRedeemed>").build().perform();
	   a.sendKeys(Keys.ENTER,"<PointsValueRedeemed>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(10,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</PointsValueRedeemed>").build().perform();
	   a.sendKeys(Keys.ENTER,"<SKUOfferCode>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(11,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</SKUOfferCode>").build().perform();
	   a.sendKeys(Keys.ENTER,"<CountryCode>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(12,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</CountryCode>").build().perform(); 
	   a.sendKeys(Keys.ENTER,"<TransactionItems>").build().perform();
	   a.sendKeys(Keys.ENTER,"<TransactionItem>").build().perform();
	   a.sendKeys(Keys.ENTER,"<ItemType>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(13,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</ItemType>").build().perform();
	   a.sendKeys(Keys.ENTER,"<ItemQty>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(14,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</ItemQty>").build().perform();
	   a.sendKeys(Keys.ENTER,"<Unit>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(15,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</Unit>").build().perform();
	   a.sendKeys(Keys.ENTER,"<ItemTax>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(16,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</ItemTax>").build().perform();
	   a.sendKeys(Keys.ENTER,"<TotalPrice>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(17,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</TotalPrice>").build().perform();
	   a.sendKeys(Keys.ENTER,"<BilledPrice>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(18,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</BilledPrice>").build().perform();
	   a.sendKeys(Keys.ENTER,"<Department>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(19,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</Department>").build().perform();
	   a.sendKeys(Keys.ENTER,"<Category>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(20,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</Category>").build().perform();
	   a.sendKeys(Keys.ENTER,"<Group>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(21,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</Group>").build().perform();
	   a.sendKeys(Keys.ENTER,"<ItemId>").build().perform();
	   a.sendKeys(Keys.INSERT,s.getCell(22,i).getContents()).build().perform();
	   a.sendKeys(Keys.INSERT,"</ItemId>").build().perform();
	   a.sendKeys(Keys.ENTER,"</TransactionItem>").build().perform();
	   a.sendKeys(Keys.ENTER,"</TransactionItems>").build().perform(); 
	   a.sendKeys(Keys.ENTER,"<PaymentMode>").build().perform();
       a.sendKeys(Keys.ENTER,"</PaymentMode>").build().perform();
       a.sendKeys(Keys.ENTER,"</Request>").build().perform();
	   driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();
	   
	   File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   FileUtils.copyFile(src1, new File("screenshots/Revdemo/20."+i+".png"));
	   String XMLresponse=driver.findElement(By.xpath("//*[@id='txtResponse']")).getText();
       System.out.println(XMLresponse);
		  /*DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		  InputSource sr = new InputSource();
		  sr.setCharacterStream(new StringReader(XMLresponse));
		  org.w3c.dom.Document doc = builder.parse(sr);
		  String msg = doc.getElementsByTagName("ReturnMessage").item(0).getTextContent();
		  System.out.println(msg);*/
       
		  if(XMLresponse.contains("Success")){
			  System.out.println("Pass");
			  logger.log(LogStatus.PASS,"Response is Success",logger.addScreenCapture("screenshots/Revdemo/20."+i+".png")); 
		  }else{
			  System.out.println("Fail");
			  logger.log(LogStatus.FAIL ,"Failed",logger.addScreenCapture("screenshots/Revdemo/20."+i+".png")); 
		  }
		  report.endTest(logger);
			report.flush(); 
  }
}
}
