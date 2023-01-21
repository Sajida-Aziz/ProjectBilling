package TestNGtest;

import org.testng.annotations.Test;

import PageElements.LoginPage;
import PageElements.ProductPage;
import Utilities.GeneralUtilities;
import Utilities.LaunchBrowser;
import Utilities.ScreenShotCapture;
import Utilities.WaitConditions;
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

public class TC_ProductPage extends LaunchBrowser {
	WebDriver driver;
	readConfig readconfig =new readConfig();
	ProductPage productpage;
	WaitConditions objwait=new WaitConditions();
	GeneralUtilities objutil=new GeneralUtilities();

	
  @Test(enabled=true,groups= {"smoke"},priority=5)
  public void TC_FverifyTitle() {
	 String title=objutil.getPageTitle(driver);
	 Assert.assertEquals("Add new product - Lloll", title,"Title of product page does not match");
	}
  
  @Test(enabled=true,priority=6)
  public void TC_VerifyskuTooltip() {
		 String msg= productpage.skuTooltip();
		 boolean exp= productpage.tootltiptext(msg);
		 Assert.assertEquals(exp, true, "Tool tip does not match");
		 }
 @Test(enabled=true,priority=7)
  public void TC_IverifyinclusiveTax()
  {
	 objutil.sendText(productpage.exclusiveTax,"32");
	 objutil.clickOnElement(productpage.inclusiveTax);
	 String valueInc=productpage.inclusiveTax.getAttribute("value");
	 Assert.assertEquals(valueInc,"32,00","incorrect inclusive tax");
 	 }
  @Test(enabled=true,priority=1)
  public void TC_AverifywithoutProName() throws InterruptedException
  { 
	   objutil.refresthpage(driver);
	    objutil.sendText( productpage.ProductName,"");
	    productpage.EnterUnitName();
	    productpage.alertQuantity("21");
	    productpage.ExclusiveTax("23");
	    objutil.clickOnElement(productpage.inclusiveTax);
	    productpage.SaveButton();
        String msg=objutil.getElementText(productpage.saveErrmsg);
	    Assert.assertEquals(msg, "This field is required.", "Failed to execute without productname");
 }
 
  
  @Test(enabled=true,priority=2)
  public void TC_CVerifywithoutalertQuantity() throws InterruptedException
  {
		  objutil.refresthpage(driver);
	      objutil.sendText(productpage.ProductName,"slippers"+objutil.getRandomNumberF());
	      productpage.EnterUnitName();
	      productpage.alertQuantity("");
	      productpage.ExclusiveTax("23");
	      productpage.inclusiveTax.click();
	      productpage.SaveButton();
	      String msg=objutil.getElementText(productpage.alertQuErro);
	      Assert.assertEquals(msg, "This field is required.", "Failed to execute without alertQuantity");
}
  @Test(enabled=true,priority=3)
  public void TC_DVerifywithoutExclusiveTax()
  {
	  objutil.refresthpage(driver);
	  objutil.sendText(productpage.ProductName,"slippers");
	  objutil.clickOnElement(productpage.unitField);
	  objutil.clickOnElement(productpage.unitNo);
	  productpage.alertQuantity("12");
	  productpage.ExclusiveTax("");
	  objutil.clickOnElement(productpage.inclusiveTax);
	  productpage.SaveButton();
	  String msg=objutil.getElementText(productpage.sindppError);
	  Assert.assertEquals(msg, "This field is required.", "Failed to execute without alertExcluTax");
	
	}
  @Test(priority=4)
  public void TC_EVerifywithoutnayfield()
  {
	 objutil.refresthpage(driver);
	 objutil.sendText(productpage.ProductName,"");
	 productpage.unitField.click();
	 productpage.alertQuantity("");
	 productpage.ExclusiveTax("");
	 objutil.clickOnElement(productpage.inclusiveTax);
	 productpage.SaveButton();
	 String errormsg=productpage.noValError.getText();
	 Assert.assertEquals(errormsg, "Invalid inputs, Check & try again!!", "Failed to execute without any field");
  }
  
  @Test(enabled=true,groups= {"smoke"},priority=8)
  public void TC_ZverifyAllInputs()
  {
	  objutil.refresthpage(driver);
	  objutil.sendText(productpage.ProductName,"almonds");
	  productpage.unitField.click();
	  productpage.unitNo.click();
	  productpage.alertQuantity("23");
	  productpage.ExclusiveTax("34");
	  objutil.clickOnElement(productpage.inclusiveTax);
	  productpage.SaveButton();
	  String msg=objutil.getElementText(productpage.productAddedSuccessfully);
	  Assert.assertEquals(msg,"Product added successfully","Product was not added");
	
  }
 @BeforeTest(groups= {"smoke"})
  public void beforeTest() {
	 launchBrowser(readconfig.getUrl(),readconfig.getBrowser()); 
	 this.driver=super.driver;
	 objwait.implicitwait(driver);
	   LoginPage objlogin=new LoginPage(driver);
	   productpage=new ProductPage(this.driver);
	   	objlogin.EnterUserName(readconfig.getUserName());
		objlogin.Enterpwd(readconfig.getPwd());
		objlogin.buttonClick();
		productpage.endtour.click();
	   productpage.productclick.click();
		objwait.waitForElemntTobeClickable(driver,productpage.addpro,Duration.ofSeconds(20));
		productpage.addpro.click();
	}
 
 @AfterTest
 public void afterTest() {
	 closeBrowser(driver);
 }
}
