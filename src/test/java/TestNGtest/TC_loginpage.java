package TestNGtest;

import org.testng.annotations.Test;

import PageElements.LoginPage;
import Utilities.ExcelRead;
import Utilities.LaunchBrowser;
import Utilities.ScreenShotCapture;
import Utilities.WaitConditions;
import Utilities.readConfig;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class TC_loginpage {
	
	WebDriver driver;
	LaunchBrowser obj=new LaunchBrowser();
	readConfig readconfig =new readConfig();
	LoginPage loginpage;
	
	  @Test(enabled=true)
	  public void TC_AVerifyTitle() {
		  String title=driver.getTitle();
		  if(title.equals("Login - Demo POS"))
			  Assert.assertTrue(true);	  
		  	  }
	  
	  
  @Test(enabled=true)
  public void TC_Clogin() throws IOException {
		LoginPage loginpage=new LoginPage(driver);
		loginpage.EnterUserName(readconfig.getUserName());
		loginpage.Enterpwd(readconfig.getPwd());
		String titleExpected=loginpage.buttonClick();
		ScreenShotCapture objscreenshot=new ScreenShotCapture(driver);
		objscreenshot.screenshots();
		if(titleExpected.equals("Home - Lloll"))
				Assert.assertTrue(true);
		else
			System.out.println("test failed");
		loginpage.endTour.click();
		//driver.findElement(By.xpath("//span[text()='Aju Mathew']")).click();
		//driver.findElement(By.xpath("(//a[contains(@class,'btn btn-default')])[2]")).click();
}
  
  
  @Test(dataProvider="loginData",enabled=true) 
  public void TC_BloginDDwithInvalidData(String uname, String pwd) throws Throwable
  {
	  LoginPage loginpage=new LoginPage(driver);
		loginpage.EnterUserName(uname);
		loginpage.Enterpwd(pwd);
		loginpage.buttonClick();
		String titleExpected=loginpage.buttonClick();
		String invalidmsg= driver.findElement(By.tagName("strong")).getText();
		System.out.println(invalidmsg);
		if(invalidmsg.contains("These credentials do not match our records."))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false, "TestFailed");
		loginpage.UserName.clear();
		loginpage.pwd.clear();
		Thread.sleep(2000);
		  }
  
  @DataProvider(name="loginData")
  public Object[][] getData() throws IOException
  {
	 ExcelRead excelread=new ExcelRead("Sheet1");
	 Object logindata[][]=new String [ExcelRead.row][ExcelRead.column];
	 for(int i=1;i<=ExcelRead.row;i++)
	 {
	for(int j=0;j<ExcelRead.column;j++) {	 
	
	logindata[i-1][j]=excelread.ReadData(i, j);
	}
	 }
	 
	 return logindata;	  
  }
  
  @Test
  public void itestresultdemo()
  {
	if(loginpage.UserName.isDisplayed())
	{
		Assert.assertFalse(true);
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
  
   
  @BeforeTest()
  public void beforeTest() {
	  obj.launchBrowser(readconfig.getUrl(),readconfig.getBrowser());
	  this.driver=obj.driver;
	  loginpage=new LoginPage(driver);
	  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }
}
