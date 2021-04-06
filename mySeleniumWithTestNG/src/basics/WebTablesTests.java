package basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Test
public class WebTablesTests extends WebTestBase {

	

	public void Given_A_WebTable_For_Course_When_Price_Each_Course_Is_Summed_Then_It_Should_Be_235() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> coursesPrice = driver.findElements(By.cssSelector("[name='courses'] td+td+td"));
		int expectedCoursesSum = 0;
		for (var price : coursesPrice) {
			expectedCoursesSum = expectedCoursesSum + Integer.parseInt(price.getText());
		}

		int actualCourseSum = 235;
		Assert.assertEquals(expectedCoursesSum, actualCourseSum);

	}

	public void Given_A_WebTable_For_Course_When_Number_Rows_Is_Counted_Then_It_Should_11()
			throws InterruptedException {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

		var countOfRows = driver.findElements(By.xpath("//*[@name='courses']//tr")).size();

		Assert.assertEquals(countOfRows, 11);
	}

	public void Given_A_WebTable_For_Course_When_Number_Column_Is_Counted_Then_It_Should_3() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		var countOfRows = driver.findElements(By.xpath("//*[@name='courses']//th")).size();

		Assert.assertEquals(3, countOfRows);
	}

	public void Given_A_WebTable_For_Course_When_AnyRow_Is_Selected_Then_Row_Details_Is_Printed() {
		int rowNumber = 6;
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		var secondRowColumns = driver.findElements(By.xpath("//*[@name='courses']//tr[" + rowNumber + "]//td"));

		for (var column : secondRowColumns) {
			System.out.println(column.getText());
		}

	}

	public void Given_Dates_Table_When_User_Want_To_Select_Particular_Date_Then_Date_Should_Be_Selectect()
			throws InterruptedException {

		driver.get("https://www.yatra.com/");
		driver.findElement(By.id("BE_flight_origin_date")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.opaque-layer.abs.show-depart")));
		String month = "April";
		String day = "10";
		List<WebElement> daysList = driver.findElements(By.xpath(
				"//*[contains(text(), '" + month + "')][1]//following-sibling::div[@class='day-container']//td"));

		for (WebElement dayItem : daysList) {
			var datesAndAmountList = dayItem.getText().split("\n");

			if (datesAndAmountList[0].equalsIgnoreCase(day)) {
				dayItem.click();
				
				System.out.println(datesAndAmountList[0]);
				break;
			}

		}
		var selectedOriginDate = driver
				.findElement(By.cssSelector("input[name='flight_origin_date']~div>p.cutom-day-year")).getText();

		Assert.assertTrue(selectedOriginDate.contains(day));
	}

}
