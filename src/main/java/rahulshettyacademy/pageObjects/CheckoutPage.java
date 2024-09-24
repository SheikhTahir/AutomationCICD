package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement selectCountry;  	//select india
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) 
	{
		Actions a = new Actions(driver); // Actions class to handle dynamic dropdown
		a.sendKeys(country, countryName).build().perform(); 
		// action command to locate the element and send keys to it "India"
		waitForElementToAppear(results);
		selectCountry.click(); // pick the option "India" from the dynamic dropdown
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
}


//driver.findElement(By.cssSelector(".action__submit")).click(); // click on place order button 
//
//
//
//String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();	
////get the order confirmation message at the final page
//Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//// checking the assert true as we got the text after placing order