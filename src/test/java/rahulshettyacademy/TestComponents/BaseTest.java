package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingPage;
	
		public WebDriver initializeDriver() throws IOException {
			
			 Properties prop = new Properties();
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
						+ "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
				prop.load(fis);
			
				String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
			//	Here we used ternary oprator to check if browser is not null then it'll take nvm command , or else the default is the chrome
//			String browserName = prop.getProperty("browser");
			
			if(browserName.contains("chrome")) 
			{
				ChromeOptions options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
				if(browserName.contains("headless")){
					options.addArguments("headless");
					}		
					driver = new ChromeDriver(options);
					driver.manage().window().setSize(new Dimension(1440,900));	// set dimension for full size during headless mode
//				 driver.manage().window().maximize();
			}
			
			else if(browserName.equalsIgnoreCase("firefox")) {
				// FIREFOR DRIVER CODE
//				System.setProperty("webdriver.gecko.driver","D:\\geckodriver-v0.35.0-win-aarch64\\geckodriver.exe");
//				 driver = new FirefoxDriver();
			}
			
			else if(browserName.equalsIgnoreCase("edge")) {
				// EDGE DRIVER CODE
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	// written outside so that it will be applied after webdriver for all browsers
			return driver;
		}
		
		// GOot this from DataReader.java for getting the details from JSON file
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
			//read json to string
		String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		
		//String to HashMap- Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
		
		//{map, map}
		}
		
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		}
		
		
		@BeforeMethod(alwaysRun=true)
		public LandingPage launchApplication() throws IOException
		{
			
			 driver = initializeDriver();
			  landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
		
		@AfterMethod(alwaysRun=true)
		public void tearDown()
		{
			driver.close();
		}
}
