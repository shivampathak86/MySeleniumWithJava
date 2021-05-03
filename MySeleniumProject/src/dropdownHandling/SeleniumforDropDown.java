

package dropdownHandling;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import  framework.*;



public class SeleniumforDropDown {
	
	private static WebDriver m_Driver;
	
@BeforeClass

public static void InitiTests() throws MalformedURLException
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
	public void StaticDropDownTest_With_Element_Having_Select_Tag()
 {	 			 
		 var currencyDropDown = m_Driver.findElement(By.xpath("//*[@id='ctl00_mainContent_DropDownListCurrency']"));
		  
		 Select select = new Select(currencyDropDown);
		 select.selectByIndex(2);
		 
		Assert.assertTrue(select.getFirstSelectedOption().getText().equalsIgnoreCase("AED"));
		
	

	}
 
 @Test
 public void StaticDropDownTest_Where_Single_DropDownOption_Selected_Multiple_Times_Using_Loop() throws InterruptedException
 {
	 m_Driver.findElement(By.cssSelector(".row1.adult-infant-child")).click();
	 
	 var passengerDropDownBox = m_Driver.findElement(By.xpath("//div[@id='divpaxOptions']"));
	 
	 var wait = new WebDriverWait(m_Driver,4);
	  var addingAdults = wait.until(ExpectedConditions.visibilityOf(passengerDropDownBox.findElement(By.xpath("//div[@id='divAdult']/div[2]/span[3]"))));
	 int i =1;
	 
	 while(i>=0)
	 {
	
		 addingAdults.click();
	 i--;
	 }
	 
	 var noOfAdults =  passengerDropDownBox.findElement(By.xpath("//div[@id='divAdult']/div[2]/span[2]")).getText();
	 
	 Assert.assertEquals(noOfAdults,"3");
	 
	 passengerDropDownBox.findElement(By.xpath("//*[@id='btnclosepaxoption']")).click();
	 
	 var passengerBoxIsNotDisplayed = !passengerDropDownBox.isDisplayed();
	 
	 Assert.assertTrue(passengerBoxIsNotDisplayed);
	 
	 
 }
 
 @Test
 
 public void DropwDownTest_Where_DropDowOptions_AreDynamic_Clicking_On_DropDown_Gives_DynamicList() throws InterruptedException
 {

	 
	 var fromDestionation = m_Driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
	 fromDestionation.click();
	 
	 var getDesitnationBox = m_Driver.findElement(By.xpath("//*[@id='glsctl00_mainContent_ddl_originStation1_CTNR']"));
	 
	 var wait = new WebDriverWait(m_Driver,4);
	 var chennai = wait.until(ExpectedConditions.visibilityOf(getDesitnationBox.findElement(By.xpath("//a[@value='MAA']"))));
	 
	 //var chennai = getDesitnationBox.findElement(By.xpath("//a[@value='MAA']"));
	 chennai.click();
	 var selectedValue = "MAA";
	 
	 Assert.assertTrue(fromDestionation.getAttribute("selectedvalue").equalsIgnoreCase(selectedValue));
	 
	 
	 // selecting to destination drop down
	 var toDestionation = m_Driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));
	 toDestionation.click();
	 
	 var getToDesitnationBox = m_Driver.findElement(By.xpath("//*[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']"));
	 
	

	 var brl  = wait.until(ExpectedConditions.visibilityOf(getToDesitnationBox.findElement(By.cssSelector("[value='BLR']"))));
	 brl.click();
	 var selectedToValue = "BLR";
	
	 Assert.assertTrue(toDestionation.getAttribute("selectedvalue").equalsIgnoreCase(selectedToValue));
	 
	 
	 
 }
 
 @Test
 
 public void AutoSuggestedDropDownTest() throws InterruptedException
 { 
	 
	 String selectedCountry = "India";
	 
	 var countryDropDown = m_Driver.findElement(By.xpath("//*[@id ='autosuggest']"));
	 countryDropDown.sendKeys("ind");
	 
	 var wait = new WebDriverWait(m_Driver,10);
	 
	List<WebElement> autoSuggestedLIst = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class ='ui-menu-item']")));
	
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
