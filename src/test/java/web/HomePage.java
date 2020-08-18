package web;

import common.Web_Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page_object.LandingPage;
import page_object.LoginPage;

import java.io.IOException;

public class HomePage extends Web_Base {

    private static Logger log = LogManager.getLogger(HomePage.class.getName());
    public WebDriver driver;

    @Test(dataProvider = "getData")
    public void homePageNavigation(String email, String password) throws IOException {

        try {
            log.info("Initialazing driver");
            driver = initializeDriver();

        } catch (IOException e) {

            log.error("something went wrong initializing driver");
            e.printStackTrace();
        }

        driver.get(properties.getProperty("url"));
        log.info("open browsers navigating to url");

        LandingPage landing = new LandingPage(driver);

        LoginPage login =landing.getLoginButton();

        log.info("landed on login page");

        login.getEmailField().sendKeys(email);
        login.getPasswordField().sendKeys(password);
        login.getSignButton().click();

        log.info("signing in");

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[2][2];

        data[0][0] = "leratom.lp@ymail.com";
        data[0][1] = "jumpef";

        data[1][0] = "leratom@command.co.za";
        data[1][1] = "1234523";

        return data;
    }

    @AfterTest
    public void teardown() {
        driver.close();

        log.info("closing browser");

    }

}
