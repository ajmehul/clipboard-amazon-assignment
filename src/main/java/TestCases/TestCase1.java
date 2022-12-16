package TestCases;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;
import Utils.*;

public class TestCase1 {
	public static void main(String[] args) {
	detectPlatform dp = new detectPlatform();
	String os = dp.detectOS();
	String driverinit = dp.initialiseDriver(os);
	if(driverinit.contains("Null")) {
		System.out.println("not able to detect platoform");
		System.exit(1);
	}else
	System.setProperty("webdriver.chrome.driver", dp.initialiseDriver(os));         
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.amazon.in");
	driver.manage().window().maximize();
	//Assert Homepage Title
	Utils utils = new Utils();
	String actualTitle = driver.getTitle();
	String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	utils.assertText(actualTitle, expectedTitle);
	HomePage hp = new HomePage();
	TelevisionPage tp = new TelevisionPage();
	ProductPage pp = new ProductPage();
	//Click Hamburger Menu
	driver.findElement(By.id(hp.hamburgerID)).click();
	//Find TV, Appliance, electronics and Click
	driver.findElement(By.xpath(hp.tvApplianceMenuxPath)).click();
	//Find Television and click
	utils.waitForElement(hp.TelevisionMenuxPath, driver);
	driver.findElement(By.xpath(hp.TelevisionMenuxPath)).click();
	//WebElement review = driver.findElement(By.xpath(tp.movetoElementxpath));
	//Actions actions = new Actions(driver); 
	//actions.moveToElement(review);
	//actions.perform();
	//Select Samsung in Brands
	utils.waitForElement(tp.samsungBrandxPath, driver);
	driver.findElement(By.xpath(tp.samsungBrandxPath)).click();
	//Click on sort dropdown
	utils.waitForElement(tp.sortDropdownxPath, driver);
	driver.findElement(By.xpath(tp.sortDropdownxPath)).click();
	//select High to low in sorting
	utils.waitForElement(tp.sortByHightoLowxPath, driver);
	driver.findElement(By.xpath(tp.sortByHightoLowxPath)).click();
	//click on 2nd item
	utils.waitForElement(tp.getItemTwoxPath, driver);
	driver.findElement(By.xpath(tp.getItemTwoxPath)).click();
	//Move to new opened tab
	ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));
	//assert About this section
    System.out.println(driver.getTitle());
	String aboutText = driver.findElement(By.xpath(pp.aboutThisItem)).getText();
	if(utils.assertText(aboutText, "About this item")) {
		System.out.println("About this item Section Exist on product page...");
	} else
		System.out.println("About this item Section does not Exist on product page...");
	
	String log = driver.findElement(By.xpath(pp.aboutTextLog)).getText();
	System.out.println(log);
	driver.quit();
	
	}
}
