package basics;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.expectThrows;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.WebDriverBase;

@Test
public class EcomTests {
	public static WebDriver driver;
	private static WebDriverWait wait;
	private List<String> nameOfVeggiesAddedInCart;

	@BeforeClass
	public static void OneTimeSetupTest() {
		WebDriverBase d = new WebDriverBase();
		driver = d.getDriver();
		wait = new WebDriverWait(driver,5);
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
	
	public void AjxLoadingTestWithExplicitWait()
	{
		
		driver.get("https://www.itgeared.com/demo/1506-ajax-loading.html");
		driver.findElement(By.linkText("Click to load get data via Ajax!")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='modal']")));
		var result = driver.findElement(By.id("results"));
		Assert.assertTrue(result.isDisplayed());
		
	}
	
	public void AjxLoadingTestwithFluentWait()
	{
		driver.get("https://www.itgeared.com/demo/1506-ajax-loading.html");
		driver.findElement(By.linkText("Click to load get data via Ajax!")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver,Boolean>(){
        	
        	public Boolean apply(WebDriver driver) {
        	 return	! driver.findElement(By.xpath("//div[@id='modal']")).isDisplayed();
        	}		
        	
        });
		var result = driver.findElement(By.id("results"));
		Assert.assertTrue(result.isDisplayed());
		
	}
	
	public void WindowHandleTest() throws InterruptedException
	{
		String childWindowTitle= new String();
		String parentdWindowTitle ;
		driver.get("https://the-internet.herokuapp.com/windows");
	 String  currentWindow = driver.getWindowHandle();
	  driver.findElement(By.linkText("Click Here")).click();
	  Set<String> windows = driver.getWindowHandles();
	  
	  Thread.sleep(5000);
	  if(windows.iterator().hasNext())
	  {
		 // driver.switchTo().window(windows.iterator().next()).switchTo().window(windows.iterator().next());
		  
		  childWindowTitle = driver.findElement(By.cssSelector("div.example>h3")).getText();
		  
		  System.out.println(childWindowTitle);
	  }
	   driver.switchTo().window(currentWindow);
	   parentdWindowTitle = driver.findElement(By.cssSelector("div.example>h3")).getText();
	   System.out.println(parentdWindowTitle);
	   
	   Assert.assertEquals(parentdWindowTitle, "Opening a new window");
	   Assert.assertEquals(childWindowTitle, "New Window");
	   windows.clear();
	    
	  }

}
