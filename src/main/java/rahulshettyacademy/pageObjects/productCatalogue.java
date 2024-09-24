package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class productCatalogue extends AbstractComponent {
	
	WebDriver driver;	// Local driver variable
	
	public productCatalogue(WebDriver driver) {		// constrcutor having driver as parameter, getting it from StandAloneTest
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;					// Assigning real driver to the current class driver variable
		PageFactory.initElements(driver, this);	// setting pageFactory to write find Elements by in a different syntax
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement prod = getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText()
				  .equals(productName)).findFirst().orElse(null);	
			//	filtered the required product
		
		return prod;
	}
	
	public  void addProductToCart(String productName) 
	{

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();	// clicked on add to cart
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		// we can use thread.sleep for the element to disappear to fasten the execution
	}

}
