package Utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

import io.qameta.allure.Allure;

public class ScreenShotCapture {
	WebDriver driver;
	static String path1=System.getProperty("user.dir");
	 Date date = new Date();
		Timestamp ts=new Timestamp(date.getTime());
	
	public ScreenShotCapture(WebDriver driver)
	{
	this.driver=driver;
	}

    public void  screenshots() throws IOException {
   	 	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String FileName = ts.toString().replace(":", "_").replace(" ", "_") + ".png";
		File path = new File(path1+"\\src\\test\\java\\Screenshots\\Screenshots"+FileName);
   		Files.copy(screenshotFile, path);
        }
    
    
    public void  screenshots(WebDriver driver,String testName) throws IOException {
      	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
   		String FileName = ts.toString().replace(":", "_").replace(" ", "_") + ".png";
   		File path = new File(path1+"\\src\\test\\java\\Screenshots\\Screenshots"+FileName);
        Files.copy(screenshotFile, path);
        System.out.println(testName);
        Allure.addAttachment(testName, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
           }
    }
