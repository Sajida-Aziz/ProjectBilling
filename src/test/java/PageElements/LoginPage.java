package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	@FindBy(id="username")
	public WebElement UserName;
	@FindBy(id="password")
	public WebElement pwd;
	@FindBy(tagName="button")
	public WebElement buttonclick;
	@FindBy(css="button.btn-default:nth-child(3)")
	public WebElement endTour;
	
	public void EnterUserName(String uname)
	{
		UserName.sendKeys(uname);
	}
	public void Enterpwd(String paswd)
	{
		pwd.sendKeys(paswd);
	}
	
	public String buttonClick()
	{
		buttonclick.click();
		String titleexp=driver.getTitle();
		return titleexp;
		
	}
	
	
	
	
	
	
	
	
	

}
