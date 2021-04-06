package basics;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import framework.WebDriverBase;

public class WebTestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;

	@BeforeClass
	public static void OneTimeSetupTest() throws MalformedURLException {
		WebDriverBase d = new WebDriverBase();
		driver = d.getDriver();
		wait = new WebDriverWait(driver, 15);
	
	}

	@AfterClass
	public static void OneTimeTearDownTest() {
		driver.quit();
	}

	public WebTestBase() {
		super();
	}

}