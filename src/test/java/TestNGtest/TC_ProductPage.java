package TestNGtest;

import org.testng.annotations.Test;

import PageElements.LoginPage;
import PageElements.ProductPage;
import Utilities.LaunchBrowser;
import Utilities.ScreenShotCapture;
import Utilities.readConfig;

import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class TC_ProductPage {
	WebDriver driver;
	LaunchBrowser obj=new LaunchBrowser();
	readConfig readconfig =new readConfig();
	ProductPage productpage;

	
  @Test(enabled=true)
  public void TC_FverifyTitle() {
	 String title= driver.getTitle();
	 if (title.equalsIgnoreCase("Add new product - Lloll"))
	 Assert.assertTrue(true);
	 }
  
  @Test(enabled=true)
  public void TC_GmanageCheck() {
	  boolean  value = driver.findElement(By.xpath("//ins[@class='iCheck-helper']")).isSelected();
	 }
  
  @Test(enabled=false)
  public void TC_HUnitName() throws InterruptedException {
		 String msg= productpage.EnterUnitName();
		 if((msg.contains("Please Select")))
				 {
		 Assert.assertTrue(false);
				 }
		 else
		  {
			 Assert.assertTrue(true);
		 }
  
  }
  @Test(enabled=true)
  public void TC_skuTooltip() {
		 String msg= productpage.skuTooltip();
		 if(msg.contains("Unique product id or Stock Keeping Unit"))
		 Assert.assertTrue(true);
		 }
 
  
 
  @Test(priority=6 ,enabled=true)
  public void TC_IinclusiveTax()
  {
	 String valueExc=productpage.ExclusiveTax("32");
	 productpage.inclusiveTax.click();
	 String valueInc=productpage.inclusiveTax.getAttribute("value");
	/* System.out.println(valueInc);
	 String de=valueInc.replace(',',' ');
	 System.out.println(Integer.parseInt(de.trim()));*/
	if(valueInc.contains("32,00"))
	 {
		 Assert.assertTrue(true);
	 }
	 
  }
  
  @Test(priority=7, enabled=false)
  public void TC_save()
  {
	 String msgFromap= productpage.SaveButton();
	 if (msgFromap.contains("//Product added successfully"))
		 Assert.assertTrue(true);
	  
	//Product added successfully  
  }
  
  @Test(priority=1, enabled=true)
  public void TC_AwithoutProName() throws InterruptedException
  {
	  driver.navigate().refresh();
	  productpage.ProductName.sendKeys("");
	  productpage.EnterUnitName();
	  productpage.alertQuantity("21");
	  productpage.ExclusiveTax("23");
	  productpage.inclusiveTax.click();
	  productpage.SaveButton();
	  String msg=driver.findElement(By.xpath("//label[@id=\"name-error\"]")).getText();
	  System.out.println(msg);
	 if (msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }else
	  {
		  Assert.assertTrue(false);
	  }
  }
  @Test(priority=2, enabled=false)
  
  public void TC_BwithoutUnit()
  {
	  driver.navigate().refresh();
	  productpage.ProductName.sendKeys("shoes");
	  //productpage.EnterUnitName();
	  productpage.alertQuantity("21");
	  productpage.ExclusiveTax("23");
	  productpage.inclusiveTax.click();
	  productpage.SaveButton();
	  String msg=driver.findElement(By.id("unit_id-error")).getText();
	  System.out.println(msg);
	 if (msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }else
	  {
		  Assert.assertTrue(false);
	  }
  }
  
  @Test(priority=3, enabled=true)
  public void TC_CwithoutalertQuantity() throws InterruptedException
  {
	  driver.navigate().refresh();
	  productpage.ProductName.sendKeys("slippers");
	  productpage.EnterUnitName();
	  productpage.alertQuantity("");
	  productpage.ExclusiveTax("23");
	  productpage.inclusiveTax.click();
	  productpage.SaveButton();
	  String msg=driver.findElement(By.id("alert_quantity-error")).getText();
	  System.out.println(msg);
	 if (msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }else
	  {
		  Assert.assertTrue(false);
	  }
  }
  @Test(priority=4, enabled=true)
  public void TC_DwithoutExclusiveTax()
  {
	  driver.navigate().refresh();
	  productpage.ProductName.sendKeys("slippers");
	  productpage.unitField.click();
	  productpage.unitNo.click();
	  productpage.alertQuantity("12");
	  productpage.ExclusiveTax("");
	  productpage.inclusiveTax.click();
	  productpage.SaveButton();
	  String msg=driver.findElement(By.id("single_dpp-error")).getText();
	  System.out.println(msg);
	 if (msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }else
	  {
		  Assert.assertTrue(false);
	  }
  }
  
  
  @Test(enabled=true)
  public void TC_Ewithoutnayfield()
  {
	  driver.navigate().refresh();
	  productpage.ProductName.sendKeys("");
	  productpage.unitField.click();
	 
	  productpage.alertQuantity("");
	  productpage.ExclusiveTax("");
	  productpage.inclusiveTax.click();
	  productpage.SaveButton();
	  String msg=driver.findElement(By.id("single_dpp-error")).getText();
	  System.out.println(msg);
	  String  errormsg=(driver.findElement(By.xpath("//div[@class='toast-message']")).getText());
	  if(errormsg.contains("Invalid inputs, Check & try again!"))
	  {
		  Assert.assertTrue(true);
	  }
	  
	 
  }
  
  
  @Test(enabled=true,groups= {" group1"})
  public void TC_ZAllInputs()
  {
	  driver.navigate().refresh();
	  productpage.ProductName.sendKeys("almonds");
	  productpage.unitField.click();
	  productpage.unitNo.click();
	 
	  productpage.alertQuantity("23");
	  productpage.ExclusiveTax("34");
	  productpage.inclusiveTax.click();
	  productpage.SaveButton();
	  String msg=productpage.productAddedSuccessfully.getText();
	  if (msg.contains("Product Added Successfully"))
	  {
		  Assert.assertEquals(true, true);
	  }
	  
	  
  }
	 
	

  
  
  public void errorMsg(String msg)
  {
	  if (msg.contains("This field is required"))
	  {
		  Assert.assertTrue(true);
	  }else
	  {
		  Assert.assertTrue(false);
	  }
  }

  @AfterMethod
  public void validateTestCases(ITestResult result) throws IOException
  {
	  if (result.getStatus()==ITestResult.FAILURE)
	  {
		  ScreenShotCapture objscreenshot=new ScreenShotCapture(driver);
			objscreenshot.screenshots();
	  }
  }
  
  
  
  @BeforeTest(groups= {" group1"})
  public void beforeTest() {
	  obj.launchBrowser(readconfig.getUrl(),readconfig.getBrowser());
	  this.driver=obj.driver;
	   LoginPage objlogin=new LoginPage(driver);
	   productpage=new ProductPage(this.driver);
		

	   objlogin.EnterUserName(readconfig.getUserName());
		objlogin.Enterpwd(readconfig.getPwd());
		objlogin.buttonClick();
		driver.findElement(By.cssSelector("button.btn-default:nth-child(3)")).click();
		driver.findElement(By.xpath("//span[text()='Products']")).click();//product click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement addpro=driver.findElement(By.cssSelector("li#tour_step5>ul>li:nth-of-type(2)>a"));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addpro));
		addpro.click();
		
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
