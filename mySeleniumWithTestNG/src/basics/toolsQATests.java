package basics;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import Helpers.*;

public class toolsQATests extends WebTestBase {

	@Test
	public void SubmitForm() throws InterruptedException, IOException {

		driver.get("https://demoqa.com/automation-practice-form");
		driver.findElement(By.id("firstName")).sendKeys("Shivam");
		;
		driver.findElement(By.id("lastName")).sendKeys("Pathak");
		driver.findElement(By.id("userEmail")).sendKeys("Test@gmail.com");
		driver.findElement(By.id("userNumber")).sendKeys("9036612244");
		driver.findElement(By.id("dateOfBirthInput")).sendKeys("08 May 2021");

		driver.findElement(By.xpath("//*[@for='gender-radio-1']")).click();

		driver.findElement(By.id("subjectsInput")).sendKeys("C");

		WebElement autoCompleteMenu = driver
				.findElement(By.xpath("//*[@class='subjects-auto-complete__menu css-26l3qy-menu']"));

		List<WebElement> subList = autoCompleteMenu
				.findElements(By.xpath("//*[@class='subjects-auto-complete__option css-yt9ioa-option']"));

		WebElement ChemSubj = subList.stream().filter(sub -> sub.getText().contains("Chemistry")).findAny().get();
		// .collect(Collectors.toList());
		if (ChemSubj != null)
			ChemSubj.click();

		driver.findElement(By.xpath("//*[@for='hobbies-checkbox-1']")).click();

		driver.findElement(By.xpath("//*[@for='uploadPicture']")).click();
		// driver.findElement(By.name("Choose File")).sendKeys(Keys.ENTER);

		File image = new File("src/jenkins.svg");
		String imagePath = image.getAbsolutePath();
		File autoItFile = new File("src/ToolsQaUpoad.exe");
		String autoItFilePath = autoItFile.getAbsolutePath();

	 Process proc=	Runtime.getRuntime().exec(autoItFilePath + " " + imagePath);
	 proc.waitFor();

		driver.findElement(By.id("currentAddress")).sendKeys("Test Address");
		driver.findElement(By.id("state")).click();

		// driver.findElement(By.className("css-26l3qy-menu"));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("react-select-3-option-0"))).click().build().perform();

		driver.findElement(By.id("city")).click();

		action.moveToElement(driver.findElement(By.id("react-select-4-option-0"))).click();

		// driver.findElement(By.xpath("//div[@id='city']//div[@class='css-26l3qy-menu']"));
		driver.findElement(By.id("submit")).click();
	 String expectedText =	driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
	 String actualText ="Thanks for submitting the form";
	 Assert.assertTrue(expectedText.equalsIgnoreCase(actualText));
	}

}
