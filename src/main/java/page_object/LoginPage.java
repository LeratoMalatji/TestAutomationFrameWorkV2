package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	private By emailField = By.id("user_email");
	private By passField = By.id("user_passwordx");
	private By signField = By.xpath("//input[@name='commit']");

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	public WebElement getEmailField() {

		return driver.findElement(emailField);
	}

	public WebElement getPasswordField() {

		return driver.findElement(passField);
	}

	public WebElement getSignButton() {

		return driver.findElement(signField);
	}

}
