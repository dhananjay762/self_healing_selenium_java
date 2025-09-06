package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.LoginPage;
import utility.LLMHelper;

public class TestSeleniumGemini {
	
	WebDriver driver;
	String url = "http://eaapp.somee.com/Account/Login";
	
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testLogin() {
		System.out.println("Testing user login from AI locators");
		String jsonResponse = LLMHelper.getPageLocatorsAsJsonFromGemini(driver.getPageSource());
		
		LoginPage loginPage = new LoginPage(driver, jsonResponse);
		loginPage.performLogin("admin", "password");
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
}
