package TestNGtest;

import org.testng.annotations.Test;

import PageElements.ListProduct;
import PageElements.LoginPage;
import Utilities.LaunchBrowser;
import Utilities.WaitConditions;
import Utilities.readConfig;

import org.testng.annotations.BeforeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TC_ListProduct extends LaunchBrowser{
	WebDriver driver;
	LaunchBrowser obj=new LaunchBrowser();
	readConfig readconfig =new readConfig();
	ListProduct listproduct;
	WaitConditions objwait=new WaitConditions();	
	 @Test(enabled=true)
	  public void TC_AVerifyTitle() throws InterruptedException {
		  String title=driver.getTitle();
				  if(title.equalsIgnoreCase("Products - Lloll"))
		  {
			  Assert.assertTrue(false);
		  }
		  }

	
  @Test(enabled=true,groups= {" group1"},priority=1)
  public void TC_A() throws InterruptedException {
	  objwait.waitForElementTobeVisible(driver,listproduct.searchField,Duration.ofSeconds(20));
	  String tabledata=listproduct.searchPro("almonds");
	  if(tabledata.contains("almonds"))
	  {
		  Assert.assertTrue(true);
	  }
	  }
  
  @Test(enabled=true)
  public void TC_Bcheckbox() throws InterruptedException {
	  boolean msg=listproduct.checkboxselected();
	  Assert.assertEquals(msg, true);
	  }
  
  @Test(enabled=true)//delete and delete without element should work together as after deleting goes to another page
  public void TC_Cdelete() throws InterruptedException {
	  String deltemsg=listproduct.deleteMethod();
	  
	  if(deltemsg.contains("Deleted Successfully"))
	  {
		  Assert.assertTrue(true);
	  }
	   // driver.navigate().back();
	  objwait.waitSleep(2000);
	  }
  
  @Test(enabled=true)
  public void TC_DdeleteWithNoElement() throws InterruptedException {
	  driver.navigate().back();
	  objwait.waitSleep(2000);
	 driver.navigate().refresh();
	  listproduct.searchField.clear();
	  listproduct.searchField.sendKeys("gujitzu");
	  objwait.waitSleep(2000);
	  String msg=listproduct.NoRecord();
	  if (msg.contains("no record "))
	  {
		  Assert.assertTrue(true);
	  }
	 }

  @BeforeTest(groups= {" group1"})
  public void beforeTest() {
	  obj.launchBrowser(readconfig.getUrl(),readconfig.getBrowser());
	  this.driver=obj.driver;
	   LoginPage objlogin=new LoginPage(driver);
	   objlogin.EnterUserName(readconfig.getUserName());
		objlogin.Enterpwd(readconfig.getPwd());
		objlogin.buttonClick();
		driver.findElement(By.cssSelector("button.btn-default:nth-child(3)")).click();
		driver.findElement(By.xpath("//span[text()='Products']")).click();//product click
		listproduct=new ListProduct(this.driver);
		listproduct.ListProductPage.click();
	}

  @AfterTest(groups= {" group1"})
  public void afterTest() {
	  driver.quit();
  }

}
