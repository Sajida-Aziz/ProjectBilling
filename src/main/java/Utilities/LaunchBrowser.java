package Utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

public class LaunchBrowser {
	public static WebDriver driver;
	readConfig readconfig =new readConfig();
	
	WaitConditions objwait=new WaitConditions();
	
	public void launchBrowser(String url,String browser)
	{		
		if (browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
					driver=new FirefoxDriver();
	
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
	
				driver=new EdgeDriver();
	
		}
		driver.manage().window().maximize();
		driver.get(url);			

	}
	
	
	
	
	@AfterMethod(alwaysRun=true)
	@Attachment(value="Screenshot",type="image/png")
	  public void validateTestCases(ITestResult result) throws IOException
	  {
		
		
		  if (result.getStatus()==ITestResult.FAILURE)
		  {
			ScreenShotCapture objscreenshot=new ScreenShotCapture(driver) ;
			  objscreenshot.screenshots(this.driver,result.getName());
			  
				
		  }
	  }

	public void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	 

	
	
	

}
