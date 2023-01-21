package Utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitConditions {
	
	public WebElement waitForElementTobeVisible(WebDriver driver, WebElement elementToBeLoaded, Duration Time) {
        WebDriverWait wait = new WebDriverWait(driver, Time);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
        return element;
    }

    public WebElement waitForElemntTobeClickable(WebDriver driver, WebElement elementToBeLoaded, Duration Time) {
        WebDriverWait wait = new WebDriverWait(driver, Time);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
        return element;
    }

 
  public WebDriver waitForElementToLoad1(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(19000));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
        return (driver);
    }
  
  public void implicitwait(WebDriver driver)
  {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));  
  }
  
  
  public void fluentWait(WebDriver driver, WebElement element) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(5000))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.visibilityOf(element));
	}
  
  public WebElement waitForElementTobePresent(WebDriver driver, By elementToBeLoaded, Duration Time) {
      WebDriverWait wait = new WebDriverWait(driver, Time);
      WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementToBeLoaded));
      return element;
  }


  
  public void waitSleep(double value) throws InterruptedException
    {
    	 try {
    	Thread.sleep((long) value);
    	  } catch (InterruptedException e) {
              e.printStackTrace();
          }
    }
    
} 
