package PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.LaunchBrowser;
import Utilities.WaitConditions;

public class ListProduct {
WebDriver driver;
WaitConditions objwait=new WaitConditions();
	
	public ListProduct(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='https://qalegend.com/billing/public/products']")
	public WebElement ListProductPage;
	
	@FindBy(xpath="//input[@class='form-control input-sm']")
	public WebElement searchField;
	
	
	@FindBy(xpath="//table[@id='product_table']/tbody/tr[1]/td[3]")
	public WebElement TableData;
	
	@FindBy(xpath="//*[@id=\"product_table\"]/tbody/tr/td[1]/input")
	public WebElement checkBox;
	
	@FindBy(id="delete-selected")
	public WebElement deleteButton;
	
	@FindBy(xpath="//button[contains(@class,'swal-button swal-button--confirm')]")
	public WebElement AlertOK;
	
	@FindBy(className="toast-message")
	public WebElement deletedSuccess ;
	
	@FindBy(className="dataTables_empty")
	public WebElement NoRecords ;
	
	public String searchPro(String proname) throws InterruptedException
	{
		searchField.sendKeys(proname);
		objwait.waitSleep(12000);
		return TableData.getText();
		}	
	public boolean checkboxselected()
	{
		checkBox.click();
		return checkBox.isSelected();
		}
	
	public String deleteMethod() throws InterruptedException
	{
		if(checkBox.isSelected())
		{
		deleteButton.click();
		AlertOK.click();
		objwait.waitSleep(1000);
		}
		return deletedSuccess.getText();
		}
	
	
	public String NoRecord()
	{
	return NoRecords.getText();
	}
	
}
