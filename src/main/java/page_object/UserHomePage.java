package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserHomePage {

	private WebDriver driver;
	private By NavUserName = By.xpath("//span[@class='navbar-current-user']");
	
	public UserHomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	public WebElement getNavUserName() {
		return driver.findElement(NavUserName);

	}
	
}
