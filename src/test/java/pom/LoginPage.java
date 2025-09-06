package pom;

import org.openqa.selenium.WebDriver;
import utility.LocatorReader;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver, String pageJson) {
		this.driver = driver;
		LocatorReader.loadLocatorsFromJson(pageJson);
	}
	
	public void performLogin(String userName, String password) {
		driver.findElement(LocatorReader.findLocatorByPartialName("UserName")).sendKeys(userName);
		driver.findElement(LocatorReader.findLocatorByPartialName("Password")).sendKeys(password);
		driver.findElement(LocatorReader.findLocatorByPartialName("Log in")).click();
	}

}
