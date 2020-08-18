package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import common.helper.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Web_Base extends Base {

    protected WebDriver driver;
    protected Properties properties;
    private static Logger log = LogManager.getLogger(Web_Base.class.getName());

    public WebDriver initializeDriver() throws IOException {

        properties = new Properties();
        FileInputStream input = null;

        String browser_Name = null;

        try {
            // loading data dynamically form external file
            input = new FileInputStream(new File(
                    System.getProperty("user.dir") + "/src/main/resources/data.properties"));

        } catch (FileNotFoundException e) {
            log.info("Resource File was not located");
            e.printStackTrace();
        }

        properties.load(input);

        if (System.getProperty("browser") != null) {
            // from MVN parameterizing with Jenkins
            browser_Name = System.getProperty("browser");
        } else {
            // from the property file
            browser_Name = properties.getProperty("browser");
        }

        driver=new Browser().browserSelection(browser_Name);

        log.info("Maximizing browser");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // waiting for a period of 10seconds before

        return driver;
    }

}
