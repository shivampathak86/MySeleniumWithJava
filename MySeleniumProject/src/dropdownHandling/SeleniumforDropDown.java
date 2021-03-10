

package dropdownHandling;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import  framework.*;



public class SeleniumforDropDown {
	
	private static WebDriver m_Driver;
	
@BeforeClass

public static void InitiTests()
{
	
	WebDriverBase driver = new WebDriverBase();
	
	m_Driver = driver.getDriver();
	m_Driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
	 
	 
}

@AfterClass

public  static void TearDownTests()
{
	m_Driver.quit();
}


	
 @Test
	public void FirstTest()
 {	 			 
		 var currencyDropDown = m_Driver.findElement(By.xpath("//*[@id='ctl00_mainContent_DropDownListCurrency']"));
		  
		 Select select = new Select(currencyDropDown);
		 select.selectByIndex(2);
		 
		Assert.assertTrue(select.getFirstSelectedOption().getText().equalsIgnoreCase("AED"));
		
	

	}
 
 @Test
 public void SecondTest() throws InterruptedException
 {
	 m_Driver.findElement(By.cssSelector(".row1.adult-infant-child")).click();
	 
	 var passengerDropDownBox = m_Driver.findElement(By.xpath("//div[@id='divpaxOptions']"));
	 
	 Thread.sleep(2000);
	 int i =1;
	 
	 while(i>=0)
	 {
	 
	 passengerDropDownBox.findElement(By.xpath("//div[@id='divAdult']/div[2]/span[3]")).click();
	 i--;
	 }
	 
	 var noOfAdults =  passengerDropDownBox.findElement(By.xpath("//div[@id='divAdult']/div[2]/span[2]")).getText();
	 
	 Assert.assertEquals(noOfAdults,"3");
	 
	 passengerDropDownBox.findElement(By.xpath("//*[@id='btnclosepaxoption']")).click();
	 
	 var passengerBoxIsNotDisplayed = !passengerDropDownBox.isDisplayed();
	 
	 Assert.assertTrue(passengerBoxIsNotDisplayed);
	 
	 
 }
 
 @Test
 
 public void ThirdTest() throws InterruptedException
 {

	 // selecting from destination drop down
	 
	 var fromDestionation = m_Driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
	 fromDestionation.click();
	 
	 var getDesitnationBox = m_Driver.findElement(By.xpath("//*[@id='glsctl00_mainContent_ddl_originStation1_CTNR']"));
	 
	 Thread.sleep(2000);

	 var chennai = getDesitnationBox.findElement(By.xpath("//a[@value='MAA']"));
	 chennai.click();
	 var selectedValue = "MAA";
	 
	 Assert.assertTrue(fromDestionation.getAttribute("selectedvalue").equalsIgnoreCase(selectedValue));
	 
	 
	 // selecting to destination drop down
	 var toDestionation = m_Driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));
	 toDestionation.click();
	 
	 var getToDesitnationBox = m_Driver.findElement(By.xpath("//*[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']"));
	 
	 Thread.sleep(2000);

	 var brl = getToDesitnationBox.findElement(By.cssSelector("[value='BLR']"));
	 brl.click();
	 var selectedToValue = "BLR";
	
	 Assert.assertTrue(toDestionation.getAttribute("selectedvalue").equalsIgnoreCase(selectedToValue));
	 
	 
	 
 }
 
 @Test
 
 public void FourthTest() throws InterruptedException
 { 
	 
	 String selectedCountry = "India";
	 
	 var countryDropDown = m_Driver.findElement(By.xpath("//*[@id ='autosuggest']"));
	 countryDropDown.sendKeys("ind");
	 
	 Thread.sleep(2000);
	 
	List<WebElement> autoSuggestedLIst = m_Driver.findElements(By.xpath("//li[@class ='ui-menu-item']"));
	
	for( WebElement  country : autoSuggestedLIst)
	{
		if(country.getText().equalsIgnoreCase(selectedCountry))
		{
			country.click();
			break;
			
		}
		
	}
	
	Thread.sleep(2000);

	 

 }
}