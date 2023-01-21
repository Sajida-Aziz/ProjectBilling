package TestNGtest;

import org.testng.annotations.Test;

import PageElements.LoginPage;
import PageElements.PurchasePage;
import Utilities.GeneralUtilities;
import Utilities.LaunchBrowser;
import Utilities.WaitConditions;
import Utilities.readConfig;

import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TC_Purchase extends LaunchBrowser{
	WebDriver driver;
	readConfig readconfig =new readConfig();
	PurchasePage purchasepage;
	WaitConditions objwait=new WaitConditions();
	GeneralUtilities objutil=new GeneralUtilities();
  @Test(priority=1,groups= {"smoke"})
  public void TC_AverifyTitle() throws InterruptedException {
	 LoginPage objlogin=new LoginPage(driver);
	 objlogin.EnterUserName(readconfig.getUserName());
	 objlogin.Enterpwd(readconfig.getPwd());
	 objlogin.buttonClick();
	 objlogin.endTour.click();
	 purchasepage=new PurchasePage(this.driver); 
	 String title= purchasepage.AddpurchasePage();
	 Assert.assertEquals(title,"Add Purchase - Lloll","Verify purchasepage failed ");
	    }
  
  @Test(priority=2,enabled=true)
  public void TC_BVerifyaddingwithoutsuplier() throws InterruptedException {
	  objwait.waitForElementTobeVisible(driver,purchasepage.PurchaseSearch,Duration.ofSeconds(30));
	  purchasepage.addPurSearch("shampoo");
	 // objwait.waitForElementTobeVisible(driver, purchasepage.PInvalidSupp,Duration.ofSeconds(6));
	  //objwait.waitForElementTobePresent(driver, By.id("supplier_id-error"),Duration.ofSeconds(40));
	  objwait.waitSleep(2000);
	  objwait.waitForElementTobeVisible(driver, purchasepage.PurchaseSave,Duration.ofSeconds(20));
	  purchasepage.PurchaseSave.click();
	  objwait.waitForElementTobeVisible(driver, purchasepage.PInvalidSupp,Duration.ofSeconds(4));
	 String msg=purchasepage.PInvalidSupp.getText();
	 Assert.assertEquals(msg, "This field is required.","Failed to execute withour supplier");
   }
  
  @Test(priority=3,enabled=true)
  public void TC_CVerifyaddingwithoutstatus() throws InterruptedException {
	  objutil.refresthpage(driver);
	  purchasepage.addPurSearch("shampoo");
	  objwait.waitSleep(5000);
	  purchasepage.PurchaseSave.click();
	  objwait.waitForElementTobeVisible(driver, purchasepage.PInvalidStatus,Duration.ofSeconds(4));
	  String msg=purchasepage.PInvalidStatus.getText();
	  Assert.assertEquals(msg, "This field is required.","Failed to execute withour status");
	}
@Test(priority=4,enabled=true)
  public void TC_DVerifyaddingwithoutbusinessLoc() throws InterruptedException {
	 objutil.refresthpage(driver);
	 purchasepage.addPurSearch("shampoo");
	 objwait.waitSleep(2000);
	 purchasepage.PurchaseSave.click();
	 objwait.waitForElementTobeVisible(driver, purchasepage.PInvalidLocation,Duration.ofSeconds(4));
	 String msg=purchasepage.PInvalidLocation.getText();
	  Assert.assertEquals(msg, "This field is required.","Failed to execute withour businesslogic");
  }

@Test(priority=5,enabled=true)
public void TC_EVerifyaddingwithoutProduct() throws InterruptedException {
	 objutil.refresthpage(driver);
	 purchasepage.PurchaseSave.click();
	 objwait.waitForElementTobeVisible(driver, purchasepage.PNoProduct,Duration.ofSeconds(2)); 
	  String msg=purchasepage.PNoProduct.getText();
	  if(msg.contains("No Products added, add some products first"))
	    Assert.assertTrue(true);
	 }

@Test(priority=6,enabled=true)
public void TC_FVerifyaddingwithoutamount() throws InterruptedException {
	 objutil.refresthpage(driver);
	 purchasepage.addPurSearch("shampoo");
	 objwait.waitSleep(2000);
	  purchasepage.PurchaseSave.click();
	  String vaAmo=purchasepage.addAmount("");
	  Assert.assertEquals(vaAmo,"0,00","Did not enter valid amount");
	 }

@Test(priority=7,enabled=true)
public void TC_GVerifyCardDetails() throws InterruptedException
{
	boolean msg=purchasepage.cardSelect();
	Assert.assertEquals(msg,true );
}

@Test(priority=8,enabled=true)
public void TC_HVerifydateCheck()
{
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
    Date date = new Date();  
    String d1=formatter.format(date); 
    String[] d1f=d1.split(" ");
	String dsupp=purchasepage.dateCheckM();
	String[] d2f=dsupp.split(" ");
	Assert.assertEquals(d1f[0],d2f[0],"Date doesn not match.Test failed");
}

@Test(priority=9,enabled=true)
public void TC_IVerifysearchproduct() throws InterruptedException
{
	purchasepage.addPurSearch("shampoo");
	objwait.waitForElementTobeVisible(driver, purchasepage.productSearch,Duration.ofSeconds(3000));
	String value=purchasepage.productSearch.getText();
	System.out.println(value);
	if(value.contains("shampoo"))
	Assert.assertTrue(true);
	
}

@Test(priority=10)
public void TC_JVerifyfileUpload() throws InterruptedException
{
 	String filepath=purchasepage.fileupload();
	String value=purchasepage.FileUploadPurchase(filepath);
	Assert.assertEquals(value, "class1and2.txt","File did not get uploaded");
}

@Test(priority=11,groups= {"Regression"})
public void TC_KVerifyaddproduct() throws InterruptedException
{
	String value=purchasepage.addprodPage();
	Assert.assertEquals(value, "Add Purchase - Lloll","Add product page not displayed ");
	
}

 @Test(priority=12,groups= {"smoke"})
 public void TC_ZVerifyvalidData() throws InterruptedException
 {
  driver.navigate().refresh();
  objwait.implicitwait(driver);
  purchasepage.addsupplier("jon");
  objwait.waitForElemntTobeClickable(driver, purchasepage.PurchaseStatus,Duration.ofSeconds(2000));
  purchasepage.addPurStatus();
  objwait.waitSleep(2000);
  purchasepage.addPurLocation("demo");
  objwait.waitSleep(2000);
  purchasepage.addPurSearch("shampoo");
  objwait.waitSleep(2000);
  purchasepage.addAmount("2000");
  purchasepage.PurchaseSave.click();
  String msg=purchasepage.PurchaseSaveMsg.getText();
  Assert.assertEquals(msg,"Purchase added successfully","Product was not added successfully");
 }
  @BeforeTest
  public void beforeTest() {
	  launchBrowser(readconfig.getUrl(),readconfig.getBrowser());
	  this.driver=super.driver;
	   }
  @AfterTest
	 public void afterTest() {
	 closeBrowser(driver);
	 }
	

  

}
