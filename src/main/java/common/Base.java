package common;

import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class Base {

    protected WebDriver driver;
    protected Properties properties;
    protected static Logger log ;

}
