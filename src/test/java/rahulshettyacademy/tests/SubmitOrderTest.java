package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.productCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		productCatalogue ProductCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));	// Object calling from Landing Page.java to sending email & password
		
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(input.get("product")); // Adding the product to cart using the productName
		
		CartPage cartPage = ProductCatalogue.goToCartPage();	// clicking on cart from the header by calling it from abstract class goToCartPage method
		
		Boolean Match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(Match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		checkoutPage.submitOrder();
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// checking the assert true as we got the text after placing order
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		productCatalogue ProductCatalogue = landingPage.loginApplication("sheikh786tahir@gmail.com", "Tahir@55555");	// Object calling from Landing Page.java to sending email & password
		OrderPage ordersPage = ProductCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	// getScreenshot code shifted to BaseComponent

	
	@DataProvider
	public Object[][] getData() throws IOException {


		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}

//		*	Commenting this as this is used using data provider and below we're using HashMap for more optimized option		*
//@DataProvider
//public Object[][] getData(){
//	return new Object[][] {{"sheikh786tahir@gmail.com","Tahir@55555","ZARA COAT 3"},{"chuimuinkapuin@gmail.com","Chuimuinkapuin@5","ADIDAS ORIGINAL"}};
//}

//		*	Commenting this as we'll get the data from the JSON file	*	
//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "sheikh786tahir@gmail.com");
//map.put("password", "Tahir@55555");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "chuimuinkapuin@gmail.com");
//map1.put("password", "Chuimuinkapuin@5");
//map1.put("product", "ADIDAS ORIGINAL");