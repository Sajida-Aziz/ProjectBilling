package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitConditions;

public class ProductPage {
	WebDriver driver;
	WaitConditions objwait=new WaitConditions();
	
	public ProductPage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="name")
	public WebElement ProductName;
	
	@FindBy(id="name-error")
	public WebElement ProductNameErrormsg;
	
	
	
	@FindBy(id="select2-unit_id-container")
	public WebElement unitField;
	

	@FindBy(xpath="//span/ul[@class='select2-results__options']/li[2]")
			public WebElement unitNo;

	

	@FindBy(xpath="//i[contains(@class,'fa fa-info-circle')]	")
			public WebElement skuMouseover;

	@FindBy(id="//div[@class='popover-content']")
	public WebElement mouseOverText;
	
	
	@FindBy(id="alert_quantity")
	public WebElement alertQuatity;
	

	@FindBy(id="single_dpp")
	public WebElement exclusiveTax;
	
	@FindBy(xpath="//*[@id=\"single_dpp_inc_tax\"]")
	public WebElement inclusiveTax;
	
	@FindBy(xpath="//button[contains(@class,'btn btn-primary')]")
	public WebElement saveButton;
	
	@FindBy(xpath="//div[@class='toast-message']")
	public WebElement msgSave;
	@FindBy(xpath="//div[@class='popover-content']")
	public WebElement skuText;
	

	@FindBy(className="toast-message")
	public WebElement productAddedSuccessfully  ;
	
	
		
	public boolean EnterProductName(String proname)
	{
		ProductName.sendKeys(proname);
		return ProductName.isEnabled();
		
	}
	
	public String EnterUnitName() throws InterruptedException
	{
		unitField.click();;
		objwait.waitSleep(2000);
		unitNo.click();
		return unitField.getAttribute("title");
		}
	public String skuTooltip()
	{
		Actions act=new Actions(driver);
		act.moveToElement(skuMouseover).build().perform();
		String tooltipVal =skuText.getText();
		return tooltipVal;
	}
	public String alertQuantity(String quantitiy)
	{
		alertQuatity.sendKeys(quantitiy);	
		return alertQuatity.getAttribute("value");
		}
	
	public String ExclusiveTax(String eTax)
	{
		exclusiveTax.sendKeys(eTax);	
		return exclusiveTax.getAttribute("value");		
	}
	public String SaveButton()
	{
		saveButton.click();
		return msgSave.getText();
	}
}
