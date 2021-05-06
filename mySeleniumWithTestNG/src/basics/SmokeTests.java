package basics;

import org.testng.Assert;
import Helpers.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Test
public class SmokeTests extends WebTestBase {

	

	public void RadioButtonTest() {
		List<WebElement> listOfRadioBtn = driver
				.findElements(By.xpath("//*[@id='ctl00_mainContent_rbtnl_Trip']//input"));
		for (var item : listOfRadioBtn) {
			item.click();

			Assert.assertTrue(item.isSelected());
		}
	}

	public void CheckBoxTest() {
		List<WebElement> listOfCheckBox = driver.findElements(By.xpath("//*[@id='discount-checkbox']//input"));
		for (var item : listOfCheckBox) {
			item.click();

			Assert.assertTrue(item.isSelected());
		}
	}

	public void SearchTest() throws InterruptedException {
		driver.get("https://www.spicejet.com/");

		// var pageReadyStatus = ((JavascriptExecutor)driver).executeScript("return
		// document.readyState").equals("complete");

		var OnewwayRadioBtn = driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0"));

		Assert.assertTrue(OnewwayRadioBtn.isSelected(), "one Way radio button nos selected");

		var fromDestination = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"))));
		fromDestination.click();

		var fromDesitinationList = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_originStation1_CTNR']"))));

		var delhi = wait.until(
				ExpectedConditions.visibilityOf(fromDesitinationList.findElement(By.cssSelector("[value='DEL']"))));

		delhi.click();

		Assert.assertTrue(fromDestination.getAttribute("selectedvalue").equalsIgnoreCase("del"),
				" From Desination selected is not Delhi");

		var toDestination = driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
		toDestination.click();

		var toDesitinationList = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']")));

		var blr = wait.until(
				ExpectedConditions.visibilityOf(toDesitinationList.findElement(By.cssSelector("[value='BLR']"))));

		blr.click();

		Assert.assertTrue(toDestination.getAttribute("selectedvalue").equalsIgnoreCase("blr"),
				" From Desination selected is not Bangalore");

		driver.findElement(By.xpath("//label[contains(text(),'PASSENGERS')]")).click();
		Thread.sleep(5000);

		var noOfAdults = driver.findElement(By.xpath("//div[@class='row1 adult-infant-child']/div[@id='divpaxinfo']"));

		noOfAdults.click();

		var paxDropDownDiv = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divpaxOptions']")));

		var paxDropDown = paxDropDownDiv.findElement(By.name("ctl00$mainContent$ddl_Adult"));

		Select pax = new Select(paxDropDown);
		pax.selectByVisibleText("4");

		Assert.assertTrue(noOfAdults.getText().equalsIgnoreCase("4 Adult"), "selected no of adults is not 4");

		var searchBtn = driver.findElement(By.id("ctl00_mainContent_btn_FindFlights"));

		searchBtn.click();

		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://book.spicejet.com/Select.aspx"),
				" user did not reach book page");

	}

	public void AlertTest() throws InterruptedException {
 
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.name("enter-name")).sendKeys("Shivam");
		driver.findElement(By.id("alertbtn")).click();
		Alert alert = driver.switchTo().alert();
		var alertText = alert.getText();

		Assert.assertTrue(alertText.toLowerCase().contains("shivam"));
		alert.accept();

		driver.findElement(By.id("confirmbtn")).click();
		Alert alert2 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert2.accept();
	}

}
