package basics;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.WebDriverBase;

@Test
public class EcomTests {
	public static WebDriver driver;
	private static WebDriverWait wait;
	private List<String> nameOfVeggiesAddedInCart;

	@BeforeClass
	public static void OneTimeSetupTest() throws MalformedURLException {
		WebDriverBase d = new WebDriverBase();
		driver = d.getDriver();
		wait = new WebDriverWait(driver, 5);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

	}

	@AfterClass
	public static void OneTimeTearDownTest() {
		driver.quit();
	}

	@Test(enabled = false)
	public void AddSingleItemToCartTest() {

		driver.findElement(By.xpath("//*[contains (text(),'Cucumber ')]/..//*//button")).click();
		driver.findElement(By.xpath("//*[@class='cart-icon']")).click();
		var cartItem = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='cart-preview active']//*[@class ='cart-items']")));

		String actualtext = cartItem.getText();

		Assert.assertTrue(actualtext.toLowerCase().contains("cucumber"));
	}

	public void AddMultipleItemToCartTest() throws InterruptedException {
		String[] veggies = new String[] { "Brocolli", "Cauliflower", "Cucumber" };

		for (String item : veggies) {
			driver.findElement(By.xpath("//*[contains (text(),'" + item + "')]/..//*//button")).click();
		}
		driver.findElement(By.xpath("//*[@class='cart-icon']")).click();

		List<WebElement> cartItems = driver
				.findElements(By.xpath("//*[@class='cart-preview active']//*[@class ='cart-items']//li"));

		Assert.assertEquals(cartItems.size(), veggies.length);

	}

	
	
	

}
