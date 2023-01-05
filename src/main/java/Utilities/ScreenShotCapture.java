package Utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenShotCapture {
	WebDriver driver;
	
	public ScreenShotCapture(WebDriver driver)
	{
	this.driver=driver;
	}

    public void  screenshots() throws IOException {
   	 Date date = new Date();
		Timestamp ts=new Timestamp(date.getTime());
   	
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String FileName = ts.toString().replace(":", "_").replace(" ", "_") + ".png";
		File path = new File("C:\\Users\\Sajida\\eclipse-workspace\\ProjectBilling\\src\\test\\java\\Screenshots\\Screenshots"+FileName);
   	//count++;
   		Files.copy(screenshotFile, path);
        }

}
