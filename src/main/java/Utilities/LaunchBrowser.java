package Utilities;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class LaunchBrowser {
	public WebDriver driver;
	public void launchBrowser(String url,String browser)
	{		
		if (browser.equalsIgnoreCase("chrome"))
		{
		 System.setProperty("webdriver.chrome.driver", "e:\\libraries\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			 System.setProperty("webdriver.gecko.driver", "E:\\libraries\\geckodriver-v0.32.0-win64\\geckodriver.exe");
				driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			 System.setProperty("webdriver.edge.driver", "E:\\libraries\\edgedriver_win64\\msedgedriver.exe");
				driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(url);
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

	
	
	

}
