package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverBase 



{
   public WebDriver getDriver()
   {
    System.setProperty("webdriver.chrome.driver","C:\\seleniumdrivers\\chromedriver.exe");
	   
        WebDriver driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        return driver;
//	driver.get("https://login.salesforce.com/");
//	driver.manage().window().maximize();
//	System.out.println(driver.getTitle());
//	 driver.findElement(By.id("username")).sendKeys("test");
//	 driver.findElement(By.id("password")).sendKeys("test");
//	 driver.findElement(By.id("Login")).click();
//	 var loginError = driver.findElement(By.id("error"));
//	 if( loginError.isDisplayed())
//	 {
//		 System.out.println(loginError.getText());
//		
//	 }
//	 else {
//	 System.out.println("Test failed");}
//	 driver.quit();
	 	
	// System.out.println(userName.getAttribute("value name"));
	

	 //System.setProperty("webdriver.gecko.driver","C:\\seleniumdrivers\\geckodriver.exe");
//	driver = new FirefoxDriver();
//	driver.get("https://www.selenium.dev/downloads/");
//   }
   }
}
