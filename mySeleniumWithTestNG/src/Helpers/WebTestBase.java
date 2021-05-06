package Helpers;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebTestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;

	@BeforeClass
	public static void OneTimeSetupTest() throws MalformedURLException {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 15);

	}

	@AfterClass
	public static void OneTimeTearDownTest() {
		driver.quit();
	}

	public WebTestBase() {

	}

}