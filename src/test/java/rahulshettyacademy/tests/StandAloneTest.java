package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
//		 TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LandingPage landPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		
		String prodName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); // explicit wait (Globally written for all explicit wait)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //storing all products
		
		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText()
						  .equals(prodName)).findFirst().orElse(null);	
		//	filtered the required product
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();	// clicked on add to cart
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));	
		// checked for the visible text "added to cart"
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));	
		// explicitly checked for the invisibilty of the "added to cart" page to proceed further
		
		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();	// clicked on cart button to go to cart page
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); 
		// saved the products name present in cart page  
		Boolean Match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(prodName));
		// filtering and matching it with the actual product we added to cart in the first page
		Assert.assertTrue(Match);
		// Checking if assert returns true and the product name matched
		
		driver.findElement(By.cssSelector(".totalRow button")).click(); // clicked on checkout button 
		
		
		Actions a = new Actions(driver); // Actions class to handle dynamix dropdown
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform(); 
		// action command to locate the element and send keys to it "India"
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click(); // pick the option "India" from the dynamic dropdown
		driver.findElement(By.cssSelector(".action__submit")).click(); // click on place order button 
		
		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();	
		//get the order confirmation message at the final page
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// checking the assert true as we got the text after placing order

	}

}
