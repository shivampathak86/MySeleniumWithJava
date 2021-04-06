package framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.DrbgParameters.Capability;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverBase

{
	String username = "shivam.pathak";
	String accessKey = "ugJV2VblSMxE6lJmBIePZ9JTDl7hYjvfsc28zvVkju6yKdiCcc";

	public WebDriver getDriver() throws MalformedURLException {
		// System.setProperty("webdriver.chrome.driver","C:\\seleniumdrivers\\chromedriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		// options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking:", "--disable-notifications");
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

		//DesiredCapabilities options = new DesiredCapabilities();
		options.setCapability(ChromeOptions.CAPABILITY,options);
		options.setCapability("platform", "Windows 10");
		options.setCapability("browserName", "Firefox");
		options.setCapability("version", "87 .0"); // If this cap isn't specified, it will just get the any
														// available one
		options.setCapability("resolution", "1024x768");
		options.setCapability("build", "PraticeBuild");
		options.setCapability("name", "Using ChromeOptions Instead DesiredCap");
		options.setCapability("network", true); // To enable network logs
		options.setCapability("visual", true); // To enable step by step screenshot
		options.setCapability("video", true); // To enable video recording
		options.setCapability("console", true); // To capture console logs

		WebDriver driver = new RemoteWebDriver(
				new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), options);

		// new RemoteWebDriver(new URL("http://10.0.0.5:5555/wd/hub"),cap);
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

		// System.setProperty("webdriver.gecko.driver","C:\\seleniumdrivers\\geckodriver.exe");
//	driver = new FirefoxDriver();
//	driver.get("https://www.selenium.dev/downloads/");
//   }
	}
}
