package basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Helpers.WebTestBase;

public class resizableTests extends WebTestBase {

	@BeforeClass

	public void OnetimeSetup() {
		driver.get("https://demoqa.com/resizable");
	}

	@Test
	public void ResizeElement_To_Bigger_Size() throws InterruptedException {
		WebElement reziableElement = driver.findElement(By.id("resizableBoxWithRestriction"));
		WebElement draggableElement = driver.findElement(By.xpath(
				"//div[@id='resizableBoxWithRestriction']//span[@class='react-resizable-handle react-resizable-handle-se']"));

		Actions action = new Actions(driver);

		action.clickAndHold(draggableElement).moveByOffset(250, 50).release().build().perform();

		int expectedXCoord = 450;
		int expectedYCoord = 250;

		/*
		 * String style = reziableElement.getAttribute("style"); String[] newSize =
		 * style.split(":|;|px");
		 * 
		 * int actualXCoord = Integer.parseInt(newSize[1].trim()); int actualYCoord
		 * =Integer.parseInt(newSize[4].trim());
		 */
		int actualXCoord = reziableElement.getRect().getDimension().getWidth();
		int actualYCoord = reziableElement.getRect().getDimension().getHeight();

		Assert.assertEquals(actualXCoord, expectedXCoord);
		Assert.assertEquals(actualYCoord, expectedYCoord);

		wait.until(ExpectedConditions.elementToBeClickable(draggableElement));

		action.clickAndHold(draggableElement).moveByOffset(-300, -100).build().perform();
		int expectedXCoord2 = 150;
		int expectedYCoord2 = 150;

		/*
		 * String style2 = reziableElement.getAttribute("style"); String[] newSize2 =
		 * style2.split(":|;|px");
		 * 
		 * int actualXCoord2 = Integer.parseInt(newSize2[1].trim());
		 * 
		 * int actualYCoord2 = Integer.parseInt(newSize2[4].trim());
		 */
		int actualXCoord2 = reziableElement.getRect().getDimension().getWidth();
		int actualYCoord2 = reziableElement.getRect().getDimension().getHeight();
		Assert.assertEquals(actualXCoord2, expectedXCoord2);
		Assert.assertEquals(actualYCoord2, expectedYCoord2);

	}
}
