package PageElements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.WaitConditions;

public class PurchasePage {
WebDriver driver;
WaitConditions objwait=new WaitConditions();
	
	public PurchasePage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
    @FindBy(xpath="//span[text()='Purchases']")
	public WebElement PurPage;
	@FindBy(css="li#tour_step6>ul>li:nth-of-type(2)>a")
	public WebElement addPurchase;
	@FindBy(xpath="//span[@role='presentation']//b")
	public WebElement addSupplierText;                            
	@FindBy(className="select2-search__field")
	public WebElement addSupplierInput;
	@FindBy(xpath="//*[@id=\"select2-status-container\"]")
	public WebElement PurchaseStatus;
	@FindBy(xpath="//span/ul[@id='select2-status-results']/li[3]")
	public WebElement PurchaseStatusOrderd;
	@FindBy(id="select2-location_id-container")
	public WebElement PurchaseLocation;
	@FindBy(xpath="//input[@role='textbox']")
	public WebElement PurchaseLocationValue;
	@FindBy(id="search_product")
	public WebElement PurchaseSearch;
	@FindBy(id="amount_0")
	public WebElement PurchaseAmount ;
	@FindBy(xpath="//button[contains(@class,'btn btn-primary')]")
	public WebElement PurchaseSave;
	@FindBy(xpath="//div[text()='Purchase added successfully']")
	public WebElement PurchaseSaveMsg;
	@FindBy(className="toast-message")
	public WebElement InvalidData;
	@FindBy(id="supplier_id-error")         
	public WebElement PInvalidSupp ;
	@FindBy(id="status-error")
	public WebElement PInvalidStatus ;
	@FindBy(id="location_id-error")
	public WebElement PInvalidLocation ;
	@FindBy(xpath="//div[text()='No Products added, add some products first']")
	public WebElement PNoProduct;
	@FindBy(id="amount_0-error")
	public WebElement PInvalidAmount ;
	@FindBy(id="transaction_date")
	public WebElement PDateCheck ;
	@FindBy(xpath="//select[@id=\"method_0\"]")
	public WebElement PCardSelect;
	@FindBy(id="card_number_0")
	public WebElement PCardNofield ;
	@FindBy(xpath="//table[@id='purchase_entry_table']/tbody/tr/td[2]")
	public WebElement productSearch;
	@FindBy(xpath="//button[@data-container='.quick_add_product_modal']")
	public WebElement addProductlink ;
	@FindBy(xpath="//button[@class='btn btn-default']")
	public WebElement addProductClose ;
	@FindBy(name="document")
	public WebElement fileUpload;
	@FindBy(xpath="//div[contains(@class,'form-control file-caption')]")
	public WebElement fileCaption;
	
	
public String FileUploadPurchase(String path) throws InterruptedException
{
	objwait.waitSleep(2000);
	fileUpload.sendKeys(path);
	return fileCaption.getText();
}
		public String AddpurchasePage() throws InterruptedException
	{
		PurPage.click();
		objwait.waitSleep(2000);
		addPurchase.click();
			return driver.getTitle();
		}
	public void addsupplier(String sname) throws InterruptedException
	{
		objwait.waitSleep(2000);
		addSupplierText.click();
		addSupplierInput.sendKeys(sname);
	    objwait.waitSleep(2000);
		addSupplierInput.sendKeys(Keys.ENTER);
	}
	public void addPurStatus()
	{
		PurchaseStatus.click();
		PurchaseStatusOrderd.click();
	}
	public void addPurLocation(String location)
	{
		PurchaseLocation.click();
		PurchaseLocationValue.sendKeys(location);
		PurchaseLocationValue.sendKeys(Keys.ENTER);
	}
	
	public void addPurSearch(String search) throws InterruptedException
	{
		PurchaseSearch.sendKeys(search);
		objwait.waitSleep(9000);
		PurchaseSearch.sendKeys(Keys.ARROW_DOWN);
		PurchaseSearch.sendKeys(Keys.ENTER);
	}
	public String addAmount(String amount) throws InterruptedException
	{
		 objwait.waitSleep(2000);
		PurchaseAmount.sendKeys(amount);
		return PurchaseAmount.getAttribute("value");
	}
	public String dateCheckM()
	{
		
		return PDateCheck.getAttribute("value");
	}
	public boolean cardSelect() throws InterruptedException
	{
		Select objselect=new Select(PCardSelect);
		objselect.selectByIndex(1);
		objwait.waitSleep(2000);
		return PCardNofield.isEnabled();
	}
	
	
	public String addprodPage() throws InterruptedException
	{
		addProductlink.click();
		String link=driver.getTitle();
		objwait.waitSleep(3000);
		addProductClose.click();
		return link;
	}
	public String fileupload()
	{
		String path1 = System.getProperty("user.dir");
		String filepath=path1 + "\\src\\main\\resources\\class1and2.txt";
		return filepath;
	}
}
