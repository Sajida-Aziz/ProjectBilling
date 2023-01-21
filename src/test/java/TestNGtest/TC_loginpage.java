package TestNGtest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageElements.LoginPage;
import Utilities.ExcelRead;
import Utilities.GeneralUtilities;
import Utilities.LaunchBrowser;
import Utilities.ScreenShotCapture;
import Utilities.WaitConditions;
import Utilities.readConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class TC_loginpage extends LaunchBrowser {
	WebDriver driver;
	readConfig readconfig =new readConfig();
	LoginPage loginpage;
	GeneralUtilities objutil=new GeneralUtilities();
	WaitConditions objwait=new WaitConditions();
	  @Severity(SeverityLevel.BLOCKER)
	  @Description("Verify page title")
	  @Story("Test verify page title")
	  @Test(enabled=true,priority=1,groups= {"smoke"})
	  public void TC_AVerifyTitle() {
		  String title=objutil.getPageTitle(driver);
		  Assert.assertEquals(title, "Login - Demo POS","Login page title does not match");
		  	  }
	  
	  @Severity(SeverityLevel.NORMAL)
	  @Description("Verify login In functionality")
	  @Story("Test verify valid login")
      @Test(enabled=true,priority=5,groups= {"smoke"})
  public void TC_CVerifyloginwithValidData() throws IOException {
		  loginpage=new LoginPage(driver);
		 loginpage.EnterUserName(readconfig.getUserName());
		 loginpage.Enterpwd(readconfig.getPwd());
		String titleExpected=loginpage.buttonClick();
		ScreenShotCapture objscreenshot=new ScreenShotCapture(driver);
		objscreenshot.screenshots();
		Assert.assertEquals(titleExpected,"Home - Lloll","Title does not match");
		loginpage.endTour.click();
	}
	  
	  @Severity(SeverityLevel.BLOCKER)
	  @Description("verify login In functionality")
	  @Story("Test verify invalid login")
  @Test(dataProvider="loginData",enabled=true,priority=3) 
  public void TC_BVerifyloginDDwithInvalidData(String uname, String pwd) throws Throwable{
	  LoginPage loginpage=new LoginPage(driver);
		loginpage.EnterUserName(uname);
		loginpage.Enterpwd(pwd);
		loginpage.buttonClick();
		String actual=objutil.getElementText(loginpage.invalidmsg);
		Assert.assertEquals(actual,"These credentials do not match our records.","Verify login with invalid data failed" );
	    objutil.clearText(loginpage.UserName);
		objutil.clearText(loginpage.pwd);
		objutil.mediumDelay(2000);
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
  
  @Test(priority=4)
  public void VerifyHomePageTitle()
  {   
	  String actual=objutil.getPageTitle(driver);
	  Assert.assertEquals(actual,"Home - Lloll","TestCase failed as driver did not to land homepage");
  }
   @BeforeTest()
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
