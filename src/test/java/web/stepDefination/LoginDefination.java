package web.stepDefination;

import common.Web_Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import page_object.LandingPage;
import page_object.LoginPage;
import page_object.UserHomePage;

import java.io.IOException;

public class LoginDefination extends Web_Base {

    private static Logger log = LogManager.getLogger(LoginDefination.class.getName());
    protected WebDriver driver;
    private LandingPage landing ;

    @Given("^Initiazile the browser with chrome$")
    public void initiazile_the_browser_with_chrome() {

        try {
            driver =initializeDriver();
            log.info("Initializing driver in Login definition");
        } catch (IOException e) {
            log.error("Something went wrong initializing driver in step Definition");
            e.printStackTrace();
        }
    }

    @Given("^Navigate to \"([^\"]*)\" site$")
    public void navigate_to_site(String url) {
        // Write code here that turns the phrase above into concrete actions
        driver.get(url);
        log.info("open browers navigating to url");

    }

    @Given("^Click on login button in homepage to land on sign page$")
    public void click_on_login_button_in_homepage_to_land_on_sign_page() {
        // Write code here that turns the phrase above into concrete actions

        landing = new LandingPage(driver);
        landing.getLoginButtonJustForCuCumber().click();
        log.info("clicked on login button");

    }

    @When("^User enters Username (.+) and password (.+) and logs in$")
    public void user_enters_Username_and_password_and_logs_in(String userName, String password) {
        // Write code here that turns the phrase above into concrete actions
        LoginPage login = new LoginPage(driver);
        login.getEmailField().sendKeys(userName);
        login.getPasswordField().sendKeys(password);
        login.getSignButton().click();
        log.info("submitted login info");

    }

    @Then("^Varify that a user has succesfully logged in$")
    public void varify_that_a_user_has_succesfully_logged_in()  {

        UserHomePage userHomePage = new  UserHomePage(driver);
        Boolean username= userHomePage.getNavUserName().isDisplayed();

        Assert.assertTrue(username);
        log.info("Username on the nav bar is displayed and correct");

    }

    @When("^User gets on a landing page Title \"([^\"]*)\" should be displayed$")
    public void user_gets_on_a_landing_page_title_something_should_be_displayed(String expectedTitle) {

        landing = new LandingPage(driver);
        String title = landing.getTitle().getText();
        AssertJUnit.assertEquals(expectedTitle, title);
        log.info("Title found on Homepage "+title +" Test passed");

    }

    @And("^close browers$")
    public void close_browers() {

        driver.close();

        log.info("closing browser StepDefinition");

    }

}
