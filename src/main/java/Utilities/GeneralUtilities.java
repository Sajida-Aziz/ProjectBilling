package Utilities;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralUtilities {

public String getElementText(WebElement element)
{    String txt=element.getText();
	return txt ;
	
}
public void clickOnElement(WebElement element) 
{
	element.click();
}
public void refresthpage(WebDriver driver)
{
	driver.navigate().refresh();
}
public void navBackpage(WebDriver driver)
{
	driver.navigate().back();
}
public void sendText(WebElement element,String text)
{
element.sendKeys(text); 
}

public void clearText(WebElement element)
{
	element.clear();
}

public String toolTip(WebElement element)
{
	return element.getAttribute("title");
	
}
	
public void scrollToTheElement(WebElement element, WebDriver driver) {
JavascriptExecutor js = (JavascriptExecutor) driver;
 js.executeScript("arguments[0].scrollIntoView();",element);
 }

 public void mediumDelay(int x) throws InterruptedException {
Thread.sleep(x);
}

public boolean isEnabled(WebElement element) {
 return element.isEnabled();
}

public String getPageTitle(WebDriver driver) {
return driver.getTitle();
}
public boolean isElementDisplayed(WebElement element) {
	return element.isDisplayed();
}
 
public boolean isElementSelected(WebElement element) {
 return element.isSelected();
}

public static int getRandomNumberF(){
    Random randomGenerator = new Random();
    int low = 10;
    int high = 99;
    int randomInt = randomGenerator.nextInt(high-low)+ low;
    return randomInt;
}


}
