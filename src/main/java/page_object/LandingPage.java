package page_object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    protected WebDriver driver;
    private By signin = By.xpath("//span[contains(text(),'Login')]");
    private By title= By.xpath("//h2[contains(text(),'Featured Courses')]");

    public LandingPage(WebDriver driver) {

        this.driver = driver;

    }

    public LoginPage getLoginButton() {

        /* this method is used to minimize code on the test page if the action taken will send you to a new page after clicking
         * so that you dont create a new pageObject on the testcase we just return that pageObject to the testcase
         */
        driver.findElement(signin).click();
        LoginPage login = new LoginPage(driver);
        return login;
    }

    public WebElement getTitle() {

        return driver.findElement(title);
    }

    //Just added to use with cucumber e.g
    public WebElement getLoginButtonJustForCuCumber() {

        /* this method is used to minimize code on the test page if the action taken will send you to a new page after clicking
         * so that you dont create a new pageObject on the testcase we just return that pageObject to the testcase
         */
        return  driver.findElement(signin);

    }

}
