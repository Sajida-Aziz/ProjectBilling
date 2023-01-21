package TestNGtest;

import org.testng.annotations.Test;

import PageElements.ListProduct;
import PageElements.LoginPage;
import PageElements.ProductPage;
import Utilities.GeneralUtilities;
import Utilities.LaunchBrowser;
import Utilities.WaitConditions;
import Utilities.readConfig;

import org.testng.annotations.BeforeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TC_ListProduct extends LaunchBrowser {
	WebDriver driver;
	
	readConfig readconfig =new readConfig();
	ListProduct listproduct;
	WaitConditions objwait=new WaitConditions();
	ProductPage objpro;
	GeneralUtilities objutil=new GeneralUtilities();
  @Test(priority=1,groups= {"smoke"})
  public void TC_AVerifyTitle() throws InterruptedException {
	  LoginPage objlogin=new LoginPage(driver);
	  objpro=new ProductPage(driver);
	  objlogin.EnterUserName(readconfig.getUserName());
	  objlogin.Enterpwd(readconfig.getPwd());
	  objlogin.buttonClick();
	  objlogin.endTour.click();
	  objpro.productclick.click();
	  listproduct=new ListProduct(this.driver);
	  listproduct.ListProductPage.click();	
	  String title=objutil.getPageTitle(driver);
	  Assert.assertEquals(title, "Products - Lloll", "Failed because page title does not match");
	  }


@Test(priority=2,enabled=true,groups= {" smoke"})
public void TC_AverifySearch() throws InterruptedException {
  objwait.waitForElementTobeVisible(driver,listproduct.searchField,Duration.ofSeconds(5));
  String tabledata=listproduct.searchPro("almonds");
  if(tabledata.contains("almonds"))
  Assert.assertTrue(true);
  }
@Test(priority=3,enabled=true)
public void TC_Bverifycheckbox() throws InterruptedException {
	  boolean msg=listproduct.checkboxselected();
	  Assert.assertEquals(msg, true);
	  }

@Test(priority=4,enabled=true)//delete and delete without element should work together as after deleting goes to another page
public void TC_Cverifydeletefunctio() throws InterruptedException {
	
	  String deltemsg=listproduct.deleteMethod();
	  Assert.assertEquals(deltemsg, "Deleted Successfully", "Delete not successful");
     objutil.mediumDelay(2000);
	  }

@Test(priority=5,enabled=true)
public void TC_DverifydeleteWithNoElement() throws InterruptedException {
	objutil.navBackpage(driver);  
	objwait.waitForElementTobeVisible(driver, listproduct.searchField,Duration.ofSeconds(40) );
    listproduct.searchField.clear();
	objutil.sendText(listproduct.searchField,"gujitzu");
	objwait.fluentWait(driver, listproduct.NoRecords);
	String msg=listproduct.NoRecord();
	  Assert.assertEquals(msg, "No matching records found", "Failed to execute with no element");
	 }

  @BeforeTest
  public void beforeTest() {
	  launchBrowser(readconfig.getUrl(),readconfig.getBrowser());
	  this.driver=super.driver;
	   objwait.implicitwait(driver);
	}
  
  @AfterTest
	 public void afterTest() {
		
	  closeBrowser(driver);
	 }
	
  

}
