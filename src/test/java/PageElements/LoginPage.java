package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.GeneralUtilities;
import Utilities.readConfig;

public class LoginPage {
	WebDriver driver;
	GeneralUtilities objutil=new GeneralUtilities();

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
	@FindBy(tagName="strong")
    public WebElement invalidmsg;
	@FindBy(css="button.btn-default:nth-child(3)")
	public WebElement endTour;
	
	public void EnterUserName(String uname)
	{
		objutil.sendText(UserName, uname);
	}
	public void Enterpwd(String paswd)
	{
		objutil.sendText(pwd, paswd);
		
	}
	
	public String buttonClick()
	{
		objutil.clickOnElement(buttonclick);
		String titleexp=objutil.getPageTitle(driver);
		return titleexp;
	}
}

