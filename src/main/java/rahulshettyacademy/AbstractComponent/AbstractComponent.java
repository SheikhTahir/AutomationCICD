package rahulshettyacademy.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement HeaderCart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
//	driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();	// clicked on cart button to go to cart page
	public CartPage goToCartPage() 
	{
		HeaderCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() 
	{
		orderHeader.click();
		OrderPage ordersPage = new OrderPage(driver);
		return ordersPage;
	}
	
	public void waitForElementToAppear(By findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); // explicit wait (Globally written for all explicit wait)
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); // explicit wait (Globally written for all explicit wait)
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
//		Thread.sleep(1000);  //Here we can use this thread to fast the execution as there's already a spinner in the BG and this will skip & fasten execution
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));	
	}
}
