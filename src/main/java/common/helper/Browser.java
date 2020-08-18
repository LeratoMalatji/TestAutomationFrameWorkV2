package common.helper;

import common.Base;
import common.Web_Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;

//purpose is for Driver selection
public class Browser extends Base {

    private WebDriver driver;
    private static Logger log = LogManager.getLogger(Browser.class.getName());

    public WebDriver browserSelection(String browserName) {

        String systemPath = System.getProperty("user.dir");

        if ("chrome".equalsIgnoreCase(browserName) || "chromeheadless".equalsIgnoreCase(browserName)) {

            ChromeOptions option = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver",
                    systemPath + "/src/main/resources/Drivers/chromedriver");

            if (browserName.contains("headless")) {

                option.addArguments("--headless");
                log.info("Running on Test on browser in headless Mode " + browserName);
            }

            driver = new ChromeDriver(option);
            log.info("Running on Test on browser " + browserName);

        } else if ("firefox".equalsIgnoreCase(browserName) || "firefoxheadless".equalsIgnoreCase(browserName)) {

            FirefoxOptions option = new FirefoxOptions();
            System.setProperty("webdriver.gecko.driver", systemPath + "/src/main/resources/Drivers/geckodriver");

            if (browserName.contains("headless")) {

                option.addArguments("--headless");
                log.info("Running Test on browser in headless mode ");
            }

            driver = new FirefoxDriver(option);
            log.info("Running on Test on browser " + browserName);

        } else if ("IE".equalsIgnoreCase(browserName)) {

            InternetExplorerOptions option = new InternetExplorerOptions();
            System.setProperty("webdriver.ie.driver",
                    systemPath + "/src/main/resources/Drivers/IEDriverServer.exe");

            driver = new InternetExplorerDriver();
            log.info("Running on Test on browser " + browserName);

        } else if ("safari".equalsIgnoreCase(browserName)) {
            driver = new SafariDriver();
            log.info("Running on Test on browser " + browserName);

        } else {

            log.fatal("Something went wrong browser may not be supported");
            throw new RuntimeException("No such driver/browser name found");
        }

        return driver;
    }
}
