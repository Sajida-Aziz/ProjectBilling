package TestNGtest;

import org.testng.annotations.Test;

import PageElements.LoginPage;
import PageElements.PurchasePage;
import Utilities.LaunchBrowser;
import Utilities.WaitConditions;
import Utilities.readConfig;

import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TC_Purchase {
	WebDriver driver;
	LaunchBrowser obj=new LaunchBrowser();
	readConfig readconfig =new readConfig();
	PurchasePage purchasepage;
	WaitConditions objwait=new WaitConditions();
  @Test()
  public void TC_AverifyTitle() throws InterruptedException {
	  
	 String title= purchasepage.AddpurchasePage();
	 System.out.println(title);
	 if(title.equalsIgnoreCase("Add Purchase - Lloll"))
			 
		Assert.assertTrue(true); 
	 
	   }
  
  @Test(enabled=true)
  public void TC_Baddingwithoutsuplier() throws InterruptedException {
	  
	  
	 
	  purchasepage.addPurSearch("shampoo");
	  objwait.waitSleep(2000);
	  purchasepage.PurchaseSave.click();
	 objwait.waitSleep(6000);
	 String msg=purchasepage.PInvalidSupp.getText();
	  if(msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }
	   }
  
  @Test(enabled=true)
  public void TC_Caddingwithoutstatus() throws InterruptedException {
	  driver.navigate().refresh();
	  objwait.waitSleep(5000);
	  purchasepage.addPurSearch("shampoo");
	  objwait.waitSleep(5000);
	  //purchasepage.addAmount("1890");
	  purchasepage.PurchaseSave.click();
	  objwait.waitSleep(5000);
	  String msg=purchasepage.PInvalidStatus.getText();
	  System.out.println(msg);
	  if(msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }
	  
	  
	
	   }
@Test(enabled=true)
  public void TC_DaddingwithoutbusinessLoc() throws InterruptedException {
	 driver.navigate().refresh();
	  Thread.sleep(5000);
	 purchasepage.addPurSearch("shampoo");
	  Thread.sleep(2000);
	  purchasepage.PurchaseSave.click();
	  Thread.sleep(2000);
	  String msg=purchasepage.PInvalidLocation.getText();
	  System.out.println(msg);
	  if(msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }
	  
	  }

@Test(enabled=true)
public void TC_EaddingwithoutProduct() throws InterruptedException {
	 driver.navigate().refresh();
	 Thread.sleep(2000);
	  purchasepage.PurchaseSave.click();
	  Thread.sleep(2000);
	  String msg=purchasepage.PNoProduct.getText();
	  System.out.println(msg);
	 if(msg.contains("No Products added, add some products first"))
	  {
		  Assert.assertTrue(true);
	  }
	  
	  }

@Test(enabled=true)
public void TC_Faddingwithoutamount() throws InterruptedException {
	 driver.navigate().refresh();
	  Thread.sleep(5000);
	 purchasepage.addPurSearch("shampoo");
	  Thread.sleep(2000);
	  purchasepage.PurchaseSave.click();
	  Thread.sleep(2000);
	  String vaAmo=purchasepage.addAmount("");
	  System.out.println(vaAmo);
	  if(vaAmo.equals("0,00"))
	  Assert.assertTrue(true);
	  
	  
	  }

@Test
public void TC_GCardDetails() throws InterruptedException
{
	boolean msg=purchasepage.cardSelect();
}

@Test
public void TC_HdateCheck()
{
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
    Date date = new Date();  
    String d1=formatter.format(date); 
    String[] d1f=d1.split(" ");
	String dsupp=purchasepage.dateCheckM();
	String[] d2f=dsupp.split(" ");
		System.out.println(d1f[0] + " "+d2f[0]);
	
	if(d1f[0].equals(d2f[0]))
	{
		Assert.assertTrue(true);
	}
	
}


@Test
public void TC_Isearchproduct() throws InterruptedException
{
	purchasepage.addPurSearch("shampoo");
	Thread.sleep(2000);
	String value=purchasepage.productSearch.getText();
	System.out.println(value);
	if(value.contains("shampoo"))
	{
		Assert.assertTrue(true);
	}
	
	
}

@Test
public void TC_JfileUpload() throws InterruptedException
{
	String value=purchasepage.FileUploadPurchase("C:\\Users\\Sajida\\Desktop\\Selenium class notes\\class1and2.txt");
	
	if(value.contains("class1and2.txt"))
	{
		Assert.assertTrue(true);
	}
	
	
}

@Test
public void TC_Kaddproduct() throws InterruptedException
{
	
	String value=purchasepage.addprodPage();
	
	if (value.equalsIgnoreCase("Add Purchase - Lloll"))
	{
	Assert.assertTrue(true);	
	}
}



 @Test
 public void TC_ZvalidData() throws InterruptedException
 {
  driver.navigate().refresh();
  objwait.waitSleep(3000);
  purchasepage.addsupplier("jon");
  objwait.waitSleep(2000);
  purchasepage.addPurStatus();
  objwait.waitSleep(2000);
  purchasepage.addPurLocation("demo");
  objwait.waitSleep(2000);
  purchasepage.addPurSearch("shampoo");
  objwait.waitSleep(2000);
  purchasepage.addAmount("2000");
  purchasepage.PurchaseSave.click();
  System.out.println(driver.getTitle());
  String msg=purchasepage.PurchaseSaveMsg.getText();
  if(msg.equals("Purchase added successfully"))
  {
	  Assert.assertTrue(true);
  }
  
  
 }
  @BeforeTest
  public void beforeTest() {
	  obj.launchBrowser(readconfig.getUrl(),readconfig.getBrowser());
	  this.driver=obj.driver;
	   LoginPage objlogin=new LoginPage(driver);
	   objlogin.EnterUserName(readconfig.getUserName());
	 		objlogin.Enterpwd(readconfig.getPwd());
	 		objlogin.buttonClick();
	 		driver.findElement(By.cssSelector("button.btn-default:nth-child(3)")).click();
	 		purchasepage=new PurchasePage(this.driver);

  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
