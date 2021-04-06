package basics;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Test
public class SmokeTests2 extends WebTestBase {

	public void AjxLoadingTestWithExplicitWait() {

		driver.get("https://www.itgeared.com/demo/1506-ajax-loading.html");
		driver.findElement(By.linkText("Click to load get data via Ajax!")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='modal']")));
		var result = driver.findElement(By.id("results"));
		Assert.assertTrue(result.isDisplayed());

	}

	public void AjxLoadingTestwithFluentWait() {
		driver.get("https://www.itgeared.com/demo/1506-ajax-loading.html");
		driver.findElement(By.linkText("Click to load get data via Ajax!")).click();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.xpath("//div[@id='modal']")).isDisplayed();
			}

		});
		var result = driver.findElement(By.id("results"));
		Assert.assertTrue(result.isDisplayed());

	}

	public void WindowHandleTest() {
		String childWindowTitle = new String();
		String parentdWindowTitle;
		driver.get("https://the-internet.herokuapp.com/windows");
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.linkText("Click Here")).click();
		Set<String> windows = driver.getWindowHandles();

		if (windows.iterator().hasNext()) {
			for (String s : windows) {
				String windowTitle = driver.switchTo().window(s).findElement(By.cssSelector("div.example>h3"))
						.getText();

				System.out.println(windowTitle);
			}

		}

		childWindowTitle = driver.findElement(By.cssSelector("div.example>h3")).getText();
		System.out.println(childWindowTitle);
		driver.switchTo().window(parentWindow);
		parentdWindowTitle = driver.findElement(By.cssSelector("div.example>h3")).getText();
		System.out.println(parentdWindowTitle);

		Assert.assertEquals(parentdWindowTitle, "Opening a new window");
		Assert.assertEquals(childWindowTitle, "New Window");
		windows.clear();

	}

	public void FrameTest() {
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.switchTo().frame(driver.findElement(By.name("frame-top"))).switchTo()
				.frame(driver.findElement(By.name("frame-middle")));
		String middleFrameText = driver.findElement(By.id("content")).getText();
		Assert.assertEquals(middleFrameText, "MIDDLE");
	}

	public void CheckbxPlusDropDwnPlusTextBxPlusAlertTest() throws MalformedURLException, InterruptedException {
		driver.navigate().to(new URL("https://www.rahulshettyacademy.com/AutomationPractice/"));
		driver.findElement(By.id("checkBoxOption2")).click();
		String option = driver.findElement(By.xpath("//*[@for='benz']")).getText();
		System.out.println(option);

		Select se = new Select(driver.findElement(By.id("dropdown-class-example")));
		se.selectByVisibleText(option);
		String text = se.getFirstSelectedOption().getText();
		System.out.println(text);
		driver.findElement(By.id("name")).sendKeys(text);
		driver.findElement(By.id("alertbtn")).click();

		String textOnAlert = driver.switchTo().alert().getText();

		Assert.assertTrue(textOnAlert.contains(text));
		driver.switchTo().alert().accept();
	}

}
