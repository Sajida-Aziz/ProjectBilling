package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public void MouseH(WebDriver driver, By BY) {
        Actions actions = new Actions(driver);
        //Retrieve WebElemnt 'slider' to perform mouse hover
        WebElement slider = driver.findElement(BY);
        //Move mouse to x offset 50 i.e. in horizontal direction
        actions.moveToElement(slider, 50, 0).perform();
        slider.click();
    }

  public WebDriver waitForElementToLoad1(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
        return (driver);
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
