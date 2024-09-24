package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;	// Local driver variable
	
	public LandingPage(WebDriver driver) {		// constrcutor having driver as parameter, getting it from StandAloneTest
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;					// Assigning real driver to the current class driver variable
		PageFactory.initElements(driver, this);	// setting pageFactory to write find Elements by in a different syntax
	}

	
	
//	driver.findElement(By.id("login")).click();
	@FindBy(id="userEmail")
	WebElement userEmail;
	
//	driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
	@FindBy(id="userPassword")
	WebElement userPassword;
	
//	driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		Login.click();
		productCatalogue ProductCatalogue = new productCatalogue(driver);
		return ProductCatalogue;
	}
	
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
